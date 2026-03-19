package de.metas.ui.web.pattribute;

import javax.annotation.Nullable;

import org.adempiere.mm.attributes.AttributeSetId;

import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

  
@Value
public final class ASIDescriptor
{
	private final AttributeSetId attributeSetId;
	private final DocumentEntityDescriptor entityDescriptor;
	private final ASILayout layout;

	private final DocumentPath contextDocumentPath;

	@Builder
	private ASIDescriptor(
			@NonNull final AttributeSetId attributeSetId,
			@NonNull final DocumentEntityDescriptor entityDescriptor,
			@NonNull final ASILayout layout,
			@Nullable final DocumentPath contextDocumentPath)
	{
		this.attributeSetId = attributeSetId;
		this.entityDescriptor = entityDescriptor;
		this.layout = layout;

		this.contextDocumentPath = contextDocumentPath;
	}
}
