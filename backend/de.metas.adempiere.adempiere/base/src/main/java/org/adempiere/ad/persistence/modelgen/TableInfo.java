package org.adempiere.ad.persistence.modelgen;

/** */

import com.google.common.collect.ImmutableList;
import de.metas.security.TableAccessLevel;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import java.util.List;

/**
 * AD_Table/AD_Column related meta data.
 *
 * @author tsa
 */
@Value
public class TableInfo
{
	int adTableId;
	String tableName;
	TableAccessLevel accessLevel;
	String entityType;
	ImmutableList<ColumnInfo> columnInfos;

	@lombok.Builder
	private TableInfo(
			final int adTableId,
			@NonNull final String tableName,
			@NonNull final TableAccessLevel accessLevel,
			@NonNull String entityType,
			@Singular @NonNull final List<ColumnInfo> columnInfos)
	{
		Check.assume(adTableId > 0, "AD_Table_ID > 0");
		Check.assumeNotEmpty(tableName, "tableName not empty");
		Check.assumeNotEmpty(entityType, "entityType not empty");

		this.adTableId = adTableId;
		this.tableName = tableName;
		this.accessLevel = accessLevel;
		this.entityType = entityType;
		this.columnInfos = ImmutableList.copyOf(columnInfos);
	}
}
