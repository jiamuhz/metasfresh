package de.metas.dao.selection;

import org.adempiere.ad.dao.impl.TypedSqlQuery;
import org.adempiere.ad.trx.api.ITrx;
import org.compiere.model.I_C_OrderLine;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class QuerySelectionHelperTest
{
	@Nested
	public class buildUUIDSelectionSqlSelectFrom
	{
		@Test
		public void simpleQuery()
		{
			final Properties ctx = new Properties();
			final String trxName = ITrx.TRXNAME_None;

			final TypedSqlQuery<I_C_OrderLine> query = new TypedSqlQuery<>(
					ctx,
					I_C_OrderLine.class,
					"M_Product_ID=1",
					trxName);

			assertThat(QuerySelectionHelper.buildUUIDSelectionSqlSelectFrom("uuid", query, "C_OrderLine_ID"))
					.isEqualTo("INSERT INTO T_Query_Selection (UUID, Line, Record_ID) SELECT 'uuid', row_number() OVER (), C_OrderLine.C_OrderLine_ID  FROM C_OrderLine" +
							"\n WHERE (M_Product_ID=1)");
		}

		@Test
		public void simpleQueryWithOrderBy()
		{
			final Properties ctx = new Properties();
			final String trxName = ITrx.TRXNAME_None;

			final TypedSqlQuery<I_C_OrderLine> query = new TypedSqlQuery<>(
					ctx,
					I_C_OrderLine.class,
					"M_Product_ID=1",
					trxName);
			query.setOrderBy("C_OrderLine_ID");

			assertThat(QuerySelectionHelper.buildUUIDSelectionSqlSelectFrom("uuid", query, "C_OrderLine_ID"))
					.isEqualTo("INSERT INTO T_Query_Selection (UUID, Line, Record_ID) SELECT 'uuid', row_number() OVER (ORDER BY C_OrderLine_ID), C_OrderLine.C_OrderLine_ID  FROM C_OrderLine"
							+ "\n WHERE (M_Product_ID=1)"
							+ "\n ORDER BY C_OrderLine_ID");
		}

		@Test
		public void unionDistinctWithOrderBy()
		{
			final Properties ctx = new Properties();
			final String trxName = ITrx.TRXNAME_None;

			final TypedSqlQuery<I_C_OrderLine> query = new TypedSqlQuery<>(ctx, I_C_OrderLine.class, "M_Product_ID=1000002", trxName).setOrderBy("C_OrderLine_ID");
			query.addUnion(new TypedSqlQuery<>(ctx, I_C_OrderLine.class, "M_Product_ID=1000003", trxName).setOrderBy("C_OrderLine_ID"), /* distinct */true);
			query.addUnion(new TypedSqlQuery<>(ctx, I_C_OrderLine.class, "M_Product_ID=1000007", trxName).setOrderBy("C_OrderLine_ID"), /* distinct */true);

			assertThat(QuerySelectionHelper.buildUUIDSelectionSqlSelectFrom("uuid", query, "C_OrderLine_ID"))
					.isEqualTo("INSERT INTO T_Query_Selection (UUID, Line, Record_ID)\n" +
							"SELECT 'uuid', row_number() over (), C_OrderLine_ID\n" +
							"FROM (\n" +
							"SELECT C_OrderLine_ID  FROM C_OrderLine\n" +
							" WHERE (M_Product_ID=1000002)\n" +
							"UNION DISTINCT\n" +
							"(\n" +
							"SELECT C_OrderLine_ID  FROM C_OrderLine\n" +
							" WHERE (M_Product_ID=1000003)\n" +
							")\n" +
							"\n" +
							"UNION DISTINCT\n" +
							"(\n" +
							"SELECT C_OrderLine_ID  FROM C_OrderLine\n" +
							" WHERE (M_Product_ID=1000007)\n" +
							")\n" +
							"\n" +
							" ORDER BY C_OrderLine_ID\n" +
							") t");
		}

	}
}
