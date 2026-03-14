/**
 *
 */
package org.compiere.model;

/** */

import de.metas.util.Check;
import de.metas.util.Services;
import org.adempiere.ad.table.api.IADTableDAO;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * AD Index Column
 *
 * @author Teo Sarca, teo.sarca@gmail.com
 */
public class MIndexColumn extends X_AD_Index_Column
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1907712672821691643L;

	@SuppressWarnings("unused")
	public MIndexColumn(Properties ctx, int AD_Index_Column_ID, String trxName)
	{
		super(ctx, AD_Index_Column_ID, trxName);
	}

	@SuppressWarnings("unused")
	public MIndexColumn(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	/**
	 * Get Column Name
	 *
	 * @return column name
	 */
	public String getColumnName()
	{
		String sql = getColumnSQL();
		if (!Check.isEmpty(sql, true))
		{
			return sql;
		}
		return Services.get(IADTableDAO.class).retrieveColumnName(getAD_Column_ID());
	}

	@Override
	public String toString()
	{
		return "MIndexColumn[" + get_ID()
				+ ", AD_Column_ID=" + getAD_Column_ID()
				+ "]";
	}

}
