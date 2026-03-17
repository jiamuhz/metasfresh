package org.adempiere.ad.callout.exceptions;

/** */

import lombok.NonNull;
import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.callout.api.ICalloutInstance;
import org.adempiere.exceptions.AdempiereException;

public class CalloutException extends AdempiereException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 2766621229698377244L;

	private ICalloutInstance calloutInstance = null;
	private ICalloutExecutor calloutExecutor = null;

	private ICalloutField field;

	public CalloutException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public CalloutException(final String message)
	{
		super(message);
	}

	public CalloutException(final Throwable cause)
	{
		super(cause);
	}

	@Override
	public CalloutException setParameter(final @NonNull String name, final Object value)
	{
		super.setParameter(name, value);
		return this;
	}

	public ICalloutInstance getCalloutInstance()
	{
		return calloutInstance;
	}

	public CalloutException setCalloutInstance(final ICalloutInstance calloutInstance)
	{
		this.calloutInstance = calloutInstance;
		setParameter("calloutInstance", calloutInstance);
		return this;
	}

	public CalloutException setCalloutInstanceIfAbsent(final ICalloutInstance calloutInstance)
	{
		if (this.calloutInstance == null)
		{
			setCalloutInstance(calloutInstance);
		}
		return this;
	}

	public ICalloutExecutor getCalloutExecutor()
	{
		return calloutExecutor;
	}

	public CalloutException setCalloutExecutor(final ICalloutExecutor calloutExecutor)
	{
		this.calloutExecutor = calloutExecutor;
		return this;
	}

	public CalloutException setCalloutExecutorIfAbsent(final ICalloutExecutor calloutExecutor)
	{
		if (this.calloutExecutor == null)
		{
			setCalloutExecutor(calloutExecutor);
		}
		return this;
	}

	public CalloutException setField(final ICalloutField field)
	{
		this.field = field;
		setParameter("field", field);
		return this;
	}

	public CalloutException setFieldIfAbsent(final ICalloutField field)
	{
		if (this.field == null)
		{
			setField(field);
		}
		return this;
	}

	public ICalloutField getCalloutField()
	{
		return field;
	}
}
