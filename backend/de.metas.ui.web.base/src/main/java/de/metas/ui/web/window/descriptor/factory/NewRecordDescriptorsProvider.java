package de.metas.ui.web.window.descriptor.factory;

import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.quick_input.service.BPartnerQuickInputService;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.NewRecordDescriptor;
import lombok.NonNull;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_QuickInput;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;



/**
 * Provides {@link NewRecordDescriptor}s.
 * <p>
 * Task https://github.com/metasfresh/metasfresh/issues/1090
 */
@Component
public class NewRecordDescriptorsProvider
{
	// services
	private static final Logger logger = LogManager.getLogger(NewRecordDescriptorsProvider.class);
	private final DocumentDescriptorFactory documentDescriptors;

	private final ConcurrentHashMap<String, NewRecordDescriptor> newRecordDescriptorsByTableName = new ConcurrentHashMap<>();

	NewRecordDescriptorsProvider(
			@NonNull final DocumentDescriptorFactory documentDescriptors,
			@NonNull final BPartnerQuickInputService bpartnerQuickInputService)
	{
		this.documentDescriptors = documentDescriptors;

		final AdWindowId bpartnerQuickInputAdWindowId = bpartnerQuickInputService.getNewBPartnerWindowId().orElse(null);
		if (bpartnerQuickInputAdWindowId != null)
		{
			addNewRecordDescriptor(NewRecordDescriptor.of(
					I_C_BPartner.Table_Name,
					WindowDocumentTypeId.of(bpartnerQuickInputAdWindowId),
					(document, newRecordContext) -> {
						final I_C_BPartner_QuickInput template = InterfaceWrapperHelper.getPO(document);
						final BPartnerId bpartnerId = bpartnerQuickInputService.createBPartnerFromTemplate(template, newRecordContext);
						return bpartnerId.getRepoId();
					}));
		}
		else
		{
			logger.warn("No window found for " + I_C_BPartner_QuickInput.Table_Name);
		}
	}

	public void addNewRecordDescriptor(@NonNull final NewRecordDescriptor newRecordDescriptor)
	{
		newRecordDescriptorsByTableName.put(newRecordDescriptor.getTableName(), newRecordDescriptor);
		logger.info("Registered {}", newRecordDescriptor);
	}

	public NewRecordDescriptor getNewRecordDescriptorOrNull(@NonNull final String tableName)
	{
		return newRecordDescriptorsByTableName.get(tableName);
	}

	/**
	 * @param entityDescriptor the entity descriptor of the quick input window (e.g. for BPartner that is C_BPartner_QuickInput)
	 * @return new record descriptor
	 */
	public NewRecordDescriptor getNewRecordDescriptor(final DocumentEntityDescriptor entityDescriptor)
	{
		final WindowDocumentTypeId newRecordWindowId = entityDescriptor.getWindowId();

		return newRecordDescriptorsByTableName.values()
				.stream()
				.filter(descriptor -> WindowDocumentTypeId.equals(newRecordWindowId, descriptor.getNewRecordWindowId()))
				.findFirst()
				.orElseThrow(() -> new AdempiereException("No new record quick input defined windowId=" + newRecordWindowId));
	}

	@Nullable
	public DocumentEntityDescriptor getNewRecordEntityDescriptorIfAvailable(@NonNull final String tableName)
	{
		final NewRecordDescriptor newRecordDescriptor = getNewRecordDescriptorOrNull(tableName);
		if (newRecordDescriptor == null)
		{
			return null;
		}

		try
		{
			return documentDescriptors.getDocumentEntityDescriptor(newRecordDescriptor.getNewRecordWindowId());
		}
		catch (final Exception ex)
		{
			logger.warn("Failed fetching document entity descriptor for {}. Ignored", newRecordDescriptor, ex);
			return null;
		}
	}
}
