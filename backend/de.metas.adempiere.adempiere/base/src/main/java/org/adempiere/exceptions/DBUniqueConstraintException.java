/**
 *
 */
package org.adempiere.exceptions;

import static de.metas.util.Check.isEmpty;

/** */

import java.sql.SQLException;

import org.adempiere.ad.service.IDeveloperModeBL;
import org.compiere.model.MIndexTable;
import org.compiere.util.DB;

import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStringBuilder;
import de.metas.i18n.TranslatableStrings;
import de.metas.util.Check;
import de.metas.util.Services;

/**
 * Unique Constraint Exception
 *
 * @author Teo Sarca, teo.sarca@gmail.com
 */
public class DBUniqueConstraintException extends DBException
{
	private static final long serialVersionUID = -1436774241410586947L;

	private String constraintName = null;
	private MIndexTable index = null;

	public DBUniqueConstraintException(Throwable e)
	{
		super("@SaveErrorNotUnique@:" + e.getLocalizedMessage(), e);
		setConstraintInfo(e);
	}

	public DBUniqueConstraintException(MIndexTable index)
	{
		super("@SaveErrorNotUnique@ @AD_Index_Table_ID@:" + index.getName());
		this.constraintName = index.getName();
		this.index = index;
	}

	public DBUniqueConstraintException(SQLException e, String sql, Object[] params)
	{
		super(e, sql, params);
		setConstraintInfo(e);
	}

	public static String getConstraintName(SQLException e)
	{
		return null;
	}

	private void setConstraintInfo(Throwable e)
	{
		if (!isUniqueContraintError(e))
		{
			return; // not unique constraint, nothing to do
		}
		else if (DB.isPostgreSQL())
		{
			//
			//
			final String msg = e.getMessage();
			this.constraintName = parseConstraintName(msg, "\"", "\"");
			// Check for german errors (e.g. Bitte Informationen �ndern.
			// org.postgresql.util.PSQLException: FEHLER: duplizierter Schl�ssel
			// verletzt Unique-Constraint �bpl_billto_unique�)
			if (Check.isEmpty(this.constraintName))
			{
				this.constraintName = parseConstraintName(msg, "»", "«");
			}
		}
		else
		{
			// FIXME implement for Oracle
		}
		//
		if (!Check.isEmpty(this.constraintName, true))
		{
			this.index = MIndexTable.getByNameIgnoringCase(this.constraintName);
		}
	}

	@Override
	protected ITranslatableString buildMessage()
	{
		if (index != null && !isEmpty(index.getErrorMsg(), true))
		{
			final TranslatableStringBuilder message = TranslatableStrings.builder();

			final ITranslatableString indexErrorMsg = index.get_ModelTranslationMap().getColumnTrl(MIndexTable.COLUMNNAME_ErrorMsg, index.getErrorMsg());
			message.append(indexErrorMsg);

			if (Services.get(IDeveloperModeBL.class).isEnabled())
			{
				message.append("(AD_Index_Table:" + index.getName() + ")");
			}

			return message.build();
		}
		else
		{
			return super.buildMessage();
		}
	}

	private static String parseConstraintName(String sqlException, String quoteStart, String quoteEnd)
	{
		int i = sqlException.indexOf(quoteStart);
		if (i >= 0)
		{
			int i2 = sqlException.indexOf(quoteEnd, i + quoteStart.length());
			if (i2 >= 0)
			{
				return sqlException.substring(i + quoteStart.length(), i2);
			}
		}
		return null;
	}

}
