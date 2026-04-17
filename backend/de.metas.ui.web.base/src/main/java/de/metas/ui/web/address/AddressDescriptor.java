package de.metas.ui.web.address;

import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;

public class AddressDescriptor
{
	public static final DocumentId DocumentTypeId = DocumentId.of(1);
	
	/* package */public static AddressDescriptor of(final DocumentEntityDescriptor entityDescriptor, final AddressLayout layout)
	{
		return new AddressDescriptor(entityDescriptor, layout);
	}

	private final DocumentEntityDescriptor entityDescriptor;
	private final AddressLayout layout;

	private AddressDescriptor(final DocumentEntityDescriptor entityDescriptor, final AddressLayout layout)
	{
		this.entityDescriptor = entityDescriptor;
		this.layout = layout;
	}

	public DocumentEntityDescriptor getEntityDescriptor()
	{
		return entityDescriptor;
	}

	public AddressLayout getLayout()
	{
		return layout;
	}
}
