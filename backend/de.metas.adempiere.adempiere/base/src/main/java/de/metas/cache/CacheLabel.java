package de.metas.cache;

import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;

/** */

@Value
public class CacheLabel
{
	public static final String NO_TABLENAME_PREFIX = "$NoTableName$";

	@NonNull String name;

	private CacheLabel(@NonNull final String name)
	{
		this.name = name;
	}

	public static CacheLabel ofTableName(@NonNull final String tableName)
	{
		return new CacheLabel(tableName);
	}

	public static CacheLabel ofString(@NonNull final String string)
	{
		return new CacheLabel(string);
	}

	@Override
	@Deprecated
	public String toString() {return getName();}

	public boolean equalsByName(@Nullable final String otherName)
	{
		return this.name.equals(otherName);
	}

	public boolean isApplicationDictionaryTableName()
	{
		return name.startsWith("AD_");
	}

	public boolean containsNoTableNameMarker()
	{
		return name.contains(NO_TABLENAME_PREFIX);
	}
}
