package de.metas.ui.web.address;

import de.metas.cache.CCache;
import de.metas.location.ILocationBL;
import de.metas.location.ILocationDAO;
import de.metas.location.LocationId;
import de.metas.logging.LogManager;
import de.metas.ui.web.address.AddressDescriptorFactory.AddressFieldBinding;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentType;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.exceptions.DocumentNotFoundException;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.ui.web.window.model.DocumentFieldLogicExpressionResultRevaluator;
import de.metas.ui.web.window.model.IDocumentChangesCollector;
import de.metas.ui.web.window.model.IDocumentChangesCollector.ReasonSupplier;
import de.metas.ui.web.window.model.NullDocumentChangesCollector;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxListenerManager.TrxEventTiming;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_Location;
import org.compiere.util.Env;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class AddressRepository
{
	//
	// services
	private static final Logger logger = LogManager.getLogger(AddressRepository.class);
	private final ILocationBL locationBL = Services.get(ILocationBL.class);
	private final ILocationDAO locationDAO = Services.get(ILocationDAO.class);
	private final ITrxManager trxManager = Services.get(ITrxManager.class);
	private final AddressDescriptorFactory descriptorsFactory;

	//
	private final AtomicInteger nextAddressDocId = new AtomicInteger(1);
	private final CCache<DocumentId, Document> id2addressDoc = CCache.newLRUCache("AddressDocuments", 50, 0);

	private static final String VERSION_DEFAULT = "0";
	private static final ReasonSupplier REASON_ProcessAddressDocumentChanges = () -> "process Address document changes";

	public AddressRepository(
			@NonNull final AddressDescriptorFactory descriptorsFactory)
	{
		this.descriptorsFactory = descriptorsFactory;
	}

	public Document createNewFrom(@Nullable final LocationId fromC_Location_ID)
	{
		final DocumentEntityDescriptor entityDescriptor = descriptorsFactory.getAddressDescriptor()
				.getEntityDescriptor();

		final Document addressDoc = Document.builder(entityDescriptor)
				.initializeAsNewDocument(nextAddressDocId::getAndIncrement, VERSION_DEFAULT);

		final I_C_Location fromLocation = fromC_Location_ID != null ? locationDAO.getById(fromC_Location_ID) : null;
		if (fromLocation != null)
		{
			addressDoc.getFieldViews()
					.forEach(field -> {
						final Object value = field
								.getDescriptor()
								.getDataBindingNotNull(AddressFieldBinding.class)
								.readValue(fromLocation);

						addressDoc.processValueChange(
								field.getFieldName(),
								value,
								() -> "update from " + fromLocation,
								DocumentFieldLogicExpressionResultRevaluator.ALWAYS_RETURN_FALSE
						);

					});
		}

		addressDoc.checkAndGetValidStatus();

		logger.trace("Created from C_Location_ID={}: {}", fromC_Location_ID, addressDoc);

		putAddressDocument(addressDoc);

		return addressDoc;
	}

	public AddressLayout getLayout()
	{
		return descriptorsFactory.getAddressDescriptor().getLayout();
	}

	private void putAddressDocument(final Document addressDoc)
	{
		final Document addressDocReadonly = addressDoc.copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance);
		id2addressDoc.put(addressDoc.getDocumentId(), addressDocReadonly);

		logger.trace("Added to repository: {}", addressDocReadonly);
	}

	private void removeAddressDocumentById(final DocumentId addressDocId)
	{
		final Document addressDocRemoved = id2addressDoc.remove(addressDocId);

		logger.trace("Removed from repository by ID={}: {}", addressDocId, addressDocRemoved);
	}

	private Document getInnerAddressDocument(final DocumentId addressDocId)
	{
		final Document addressDoc = id2addressDoc.get(addressDocId);
		if (addressDoc == null)
		{
			throw new DocumentNotFoundException(DocumentType.Address, AddressDescriptor.DocumentTypeId, addressDocId);
		}
		return addressDoc;
	}

	public Document getAddressDocumentForReading(final int addressDocIdInt)
	{
		final DocumentId addressDocId = DocumentId.of(addressDocIdInt);
		return getInnerAddressDocument(addressDocId).copy(CopyMode.CheckInReadonly, NullDocumentChangesCollector.instance);
	}

	private Document getAddressDocumentForWriting(final DocumentId addressDocId, final IDocumentChangesCollector changesCollector)
	{
		return getInnerAddressDocument(addressDocId).copy(CopyMode.CheckOutWritable, changesCollector);
	}

	public void processAddressDocumentChanges(final int addressDocIdInt, final List<JSONDocumentChangedEvent> events, final IDocumentChangesCollector changesCollector)
	{
		final DocumentId addressDocId = DocumentId.of(addressDocIdInt);
		final Document addressDoc = getAddressDocumentForWriting(addressDocId, changesCollector);
		addressDoc.processValueChanges(events, REASON_ProcessAddressDocumentChanges);

		trxManager
				.getCurrentTrxListenerManagerOrAutoCommit().newEventListener(TrxEventTiming.AFTER_COMMIT)
				.registerHandlingMethod(trx -> putAddressDocument(addressDoc));

	}

	public LookupValue complete(final int addressDocIdInt, @Nullable final List<JSONDocumentChangedEvent> events)
	{
		final DocumentId addressDocId = DocumentId.of(addressDocIdInt);
		final Document addressDoc = getAddressDocumentForWriting(addressDocId, NullDocumentChangesCollector.instance);

		if (events != null && !events.isEmpty())
		{
			addressDoc.processValueChanges(events, REASON_ProcessAddressDocumentChanges);
		}

		final I_C_Location locationRecord = createC_Location(addressDoc);

		trxManager
				.getCurrentTrxListenerManagerOrAutoCommit()
				.newEventListener(TrxEventTiming.AFTER_COMMIT)
				.registerHandlingMethod(trx -> removeAddressDocumentById(addressDocId));

		final String locationStr = locationBL.mkAddress(locationRecord);
		return IntegerLookupValue.of(locationRecord.getC_Location_ID(), locationStr);
	}

	private I_C_Location createC_Location(final Document locationDoc)
	{
		final I_C_Location locationRecord = InterfaceWrapperHelper.create(Env.getCtx(), I_C_Location.class, ITrx.TRXNAME_ThreadInherited);

		locationDoc.getFieldViews()
				.forEach(locationField -> locationField
						.getDescriptor()
						.getDataBindingNotNull(AddressFieldBinding.class)
						.writeValue(locationRecord, locationField));

		locationDAO.save(locationRecord);

		return locationRecord;
	}
}
