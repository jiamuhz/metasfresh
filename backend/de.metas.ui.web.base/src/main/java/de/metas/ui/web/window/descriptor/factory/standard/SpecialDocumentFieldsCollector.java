package de.metas.ui.web.window.descriptor.factory.standard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import de.metas.logging.LogManager;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;

/*package */class SpecialDocumentFieldsCollector
{
	private static final Logger logger = LogManager.getLogger(SpecialDocumentFieldsCollector.class);

	private static final Set<String> COLUMNNAMES_DocumentNos = ImmutableSet.of(WindowConstants.FIELDNAME_DocumentNo, WindowConstants.FIELDNAME_Value, WindowConstants.FIELDNAME_Name);
	private static final Set<String> COLUMNNAMES_DocumentSummary = ImmutableSet.of(WindowConstants.FIELDNAME_DocumentSummary, WindowConstants.FIELDNAME_Name);

	/** All column names which might be special fields */
	private static final Set<String> COLUMNNAMES = ImmutableSet.<String> builder()
			.addAll(COLUMNNAMES_DocumentNos)
			.addAll(COLUMNNAMES_DocumentSummary)
			.add(WindowConstants.FIELDNAME_DocStatus)
			.add(WindowConstants.FIELDNAME_DocAction)
			.build();

	private boolean allowCollecting = true;
	private final Map<String, DocumentFieldDescriptor.Builder> collectedFields = new HashMap<>();

	public void collect(final DocumentFieldDescriptor.Builder field)
	{
		Preconditions.checkState(allowCollecting, "allowCollecting shall be true");
		
		final String fieldName = field.getFieldName();
		if (!COLUMNNAMES.contains(fieldName))
		{
			return;
		}

		final DocumentFieldDescriptor.Builder fieldAlreadyCollected = collectedFields.get(fieldName);
		if (fieldAlreadyCollected != null)
		{
			logger.warn("Skip collecting {} because we already collected {} for same field name", field, fieldAlreadyCollected);
			return;
		}

		collectedFields.put(fieldName, field);
	}
	
	public void collectFinish()
	{
		Preconditions.checkState(allowCollecting, "allowCollecting shall be true");
		allowCollecting = false;

		//
		// Update DocumentNo field flags
		for (final String fieldName : COLUMNNAMES_DocumentNos)
		{
			final DocumentFieldDescriptor.Builder field = collectedFields.get(fieldName);
			if (field == null)
			{
				continue;
			}

			field.addCharacteristic(Characteristic.PublicField);
			field.addCharacteristic(Characteristic.SpecialField_DocumentNo);
			break; // only first field shall be elected as DocumentNo
		}
	}

	public DocumentFieldDescriptor.Builder getDocumentSummary()
	{
		for (final String fieldName : COLUMNNAMES_DocumentSummary)
		{
			final DocumentFieldDescriptor.Builder field = collectedFields.get(fieldName);
			if (field == null)
			{
				continue;
			}

			field.addCharacteristic(Characteristic.PublicField);
			//field.addCharacteristic(Characteristic.SpecialField_DocumentSummary);
			return field;
		}

		return null;
	}

	public Map<Characteristic, DocumentFieldDescriptor.Builder> getDocStatusAndDocAction()
	{
		final DocumentFieldDescriptor.Builder fieldDocStatus = collectedFields.get(WindowConstants.FIELDNAME_DocStatus);
		final DocumentFieldDescriptor.Builder fieldDocAction = collectedFields.get(WindowConstants.FIELDNAME_DocAction);
		if (fieldDocStatus == null || fieldDocAction == null)
		{
			return null;
		}

		fieldDocStatus.addCharacteristic(Characteristic.PublicField);
		fieldDocStatus.addCharacteristic(Characteristic.SpecialField_DocStatus);

		fieldDocAction.addCharacteristic(Characteristic.PublicField);
		fieldDocAction.addCharacteristic(Characteristic.SpecialField_DocAction);

		return ImmutableMap.<Characteristic, DocumentFieldDescriptor.Builder> builder()
				.put(Characteristic.SpecialField_DocStatus, fieldDocStatus)
				.put(Characteristic.SpecialField_DocAction, fieldDocAction)
				.build();
	}
}
