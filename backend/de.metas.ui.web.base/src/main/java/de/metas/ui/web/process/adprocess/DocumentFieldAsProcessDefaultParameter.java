package de.metas.ui.web.process.adprocess;

import org.compiere.util.Env;

import com.google.common.base.MoreObjects;

import de.metas.process.IProcessDefaultParameter;
import de.metas.ui.web.window.model.IDocumentFieldView;

 

/* package */ final class DocumentFieldAsProcessDefaultParameter implements IProcessDefaultParameter
{
	public static final DocumentFieldAsProcessDefaultParameter of(final int windowNo, final IDocumentFieldView field)
	{
		return new DocumentFieldAsProcessDefaultParameter(windowNo, field);
	}

	private final int windowNo;
	private final IDocumentFieldView field;

	private DocumentFieldAsProcessDefaultParameter(final int windowNo, final IDocumentFieldView field)
	{
		super();
		this.windowNo = windowNo;
		this.field = field;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("windowNo", windowNo)
				.addValue(field)
				.toString();
	}

	@Override
	public String getColumnName()
	{
		return field.getFieldName();
	}

	@Override
	public int getContextAsInt(final String name)
	{
		return Env.getContextItemAsInt(Env.getCtx(), windowNo, name);
	}

}
