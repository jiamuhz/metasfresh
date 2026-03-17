package org.compiere.apps.search;

import java.util.Objects;

import de.metas.i18n.ITranslatableString;

/** */

public interface IUserQueryField
{
	static IUserQueryField castToUserQueryField(final Object value)
	{
		return (IUserQueryField)value;
	}

	String getColumnName();

	/**
	 * @return display name; never null
	 */
	ITranslatableString getDisplayName();

	String getColumnSQL();

	default boolean matchesColumnName(String columnName)
	{
		return Objects.equals(getColumnName(), columnName);
	}

	/**
	 * @deprecated Please use {@link UserQueryFieldHelper#parseValueObjectByColumnDisplayType(Object, int, String)} instead
	 */
	@Deprecated
	Object convertValueToFieldType(Object valueObj);

	String getValueDisplay(Object value);
}
