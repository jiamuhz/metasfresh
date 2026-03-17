package org.compiere.apps.search;

import org.junit.Ignore;

import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/** */

@Ignore
@Value
@Builder
final class PlainUserQueryField implements IUserQueryField
{
	public static PlainUserQueryField ofColumnName(final String columnName)
	{
		return builder()
				.columnName(columnName)
				.displayName(TranslatableStrings.constant(columnName))
				.build();
	}

	@NonNull
	String columnName;
	ITranslatableString displayName;

	@Override
	public String getColumnSQL()
	{
		return null;
	}

	@Override
	public Object convertValueToFieldType(final Object valueObj)
	{
		return valueObj;
	}

	@Override
	public String getValueDisplay(final Object valueObj)
	{
		return valueObj != null ? valueObj.toString() : null;
	}

}
