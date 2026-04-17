package de.metas.ui.web.window.descriptor;

public interface DocumentFieldDataBindingDescriptor
{
	String getColumnName();

	/**
	 * @return true if mandatory in underlying database/repository
	 */
	boolean isMandatory();

	default <T extends DocumentFieldDataBindingDescriptor> T cast(final Class<T> bindingClass)
	{
		@SuppressWarnings("unchecked")
		final T thisCasted = (T)this;
		return thisCasted;
	}

	/**
	 * @return true if this field has [ORDER BY] instructions
	 */
	default boolean isDefaultOrderBy()
	{
		return false;
	}

	default int getDefaultOrderByPriority()
	{
		return 0;
	}

	default boolean isDefaultOrderByAscending()
	{
		return true;
	}
}
