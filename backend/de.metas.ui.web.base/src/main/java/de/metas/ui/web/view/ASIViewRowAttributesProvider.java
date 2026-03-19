package de.metas.ui.web.view;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.AttributeSetInstanceId;

import de.metas.ui.web.pattribute.ASIDocument;
import de.metas.ui.web.pattribute.ASILayout;
import de.metas.ui.web.pattribute.ASIRepository;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.NonNull;

 

public class ASIViewRowAttributesProvider implements IViewRowAttributesProvider
{
	public static ASIViewRowAttributesProvider newInstance(final ASIRepository asiRepository)
	{
		return new ASIViewRowAttributesProvider(asiRepository);
	}

	private final ASIRepository asiRepository;
	private final Map<DocumentId, ASIViewRowAttributes> attributesById = new ConcurrentHashMap<>();

	private ASIViewRowAttributesProvider(@NonNull final ASIRepository asiRepository)
	{
		this.asiRepository = asiRepository;
	}

	@Override
	public IViewRowAttributes getAttributes(final DocumentId rowId_NOTUSED, final DocumentId asiId)
	{
		return attributesById.computeIfAbsent(asiId, this::createAttributes);
	}

	private ASIViewRowAttributes createAttributes(final DocumentId asiDocumentId)
	{
		final AttributeSetInstanceId asiId = AttributeSetInstanceId.ofRepoIdOrNull(asiDocumentId.toInt());
		if (asiId == null)
		{
			throw new AdempiereException("Invalid ASI document ID: " + asiDocumentId);
		}

		final ASIDocument asiDoc = asiRepository.loadReadonly(asiId);
		final ASILayout asiLayout = asiDoc.getLayout();
		return new ASIViewRowAttributes(asiDoc, asiLayout);
	}

	@Override
	public void invalidateAll()
	{
		attributesById.clear();
	}

}
