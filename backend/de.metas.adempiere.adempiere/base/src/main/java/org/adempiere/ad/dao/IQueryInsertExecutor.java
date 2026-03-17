package org.adempiere.ad.dao;

import com.google.common.base.MoreObjects;
import de.metas.process.PInstanceId;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.concurrent.Immutable;

/** */

/**
 * Mass INSERT executor
 *
 * @author tsa
 *
 * @param <ToModelType> target model (i.e. in which table you want to insert)
 * @param <FromModelType> source model (i.e. from which table are you selecting)
 */
public interface IQueryInsertExecutor<ToModelType, FromModelType>
{
	/**
	 * Execute mass INSERT
	 *
	 * @return how many rows were inserted
	 */
	QueryInsertExecutorResult execute();

	/**
	 * Map all common columns of "To Model" and "From Model".
	 */
	IQueryInsertExecutor<ToModelType, FromModelType> mapCommonColumns();

	/**
	 * Map a column name in target model to a column name of "From Model".
	 *
	 */
	IQueryInsertExecutor<ToModelType, FromModelType> mapColumn(String toColumnName, String fromColumnName);

	/**
	 * Map a column name in target model and set it to a constant.
	 */
	IQueryInsertExecutor<ToModelType, FromModelType> mapColumnToConstant(String toColumnName, Object constantValue);

	/**
	 * Map a column name in target model to a given SQL (which is based on from model).
	 * <p/>
	 * NOTE: calling this method is discouraged. Used mainly to port old code.
	 *
	 */
	IQueryInsertExecutor<ToModelType, FromModelType> mapColumnToSql(String toColumnName, String fromSql);

	/**
	 * Map the primary key column (so the ID will be generated)
	 */
	IQueryInsertExecutor<ToModelType, FromModelType> mapPrimaryKey();

	/**
	 * Advice this executor to also create a selection of the inserted rows.
	 */
	IQueryInsertExecutor<ToModelType, FromModelType> creatingSelectionOfInsertedRows();

	@Immutable
	final class QueryInsertExecutorResult
	{
		public static QueryInsertExecutorResult of(final int rowsInserted, final PInstanceId insertSelectionId)
		{
			return new QueryInsertExecutorResult(rowsInserted, insertSelectionId);
		}

		private final int rowsInserted;
		private final PInstanceId insertSelectionId;

		private QueryInsertExecutorResult(final int rowsInserted, final PInstanceId insertSelectionId)
		{
			this.rowsInserted = rowsInserted;
			this.insertSelectionId = insertSelectionId;
		}

		@Override
		public String toString()
		{
			return MoreObjects.toStringHelper(this)
					.omitNullValues()
					.add("rowsInserted", rowsInserted)
					.add("insertSelectionId", insertSelectionId)
					.toString();
		}

		public int getRowsInserted()
		{
			return rowsInserted;
		}

		public PInstanceId getInsertSelectionId()
		{
			if (insertSelectionId == null)
			{
				throw new AdempiereException("No insert selection defined");
			}
			return insertSelectionId;
		}
	}
}
