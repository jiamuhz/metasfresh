package de.metas.ui.web.window.descriptor;

import javax.annotation.Nullable;

import de.metas.ui.web.window.model.DocumentsRepository;

public interface DocumentEntityDataBindingDescriptor
{
	/**
	 * @return repository or might throw exception if the repository is not configured
	 */
	DocumentsRepository getDocumentsRepository();

	/**
	 * @return true if repository versioning is supported for this entity
	 */
	default boolean isVersioningSupported()
	{
		return false;
	}

	@FunctionalInterface
	public interface DocumentEntityDataBindingDescriptorBuilder
	{
		final DocumentEntityDataBindingDescriptorBuilder NULL = () -> null;

		@Nullable
		DocumentEntityDataBindingDescriptor getOrBuild();
	}
}
