package org.adempiere.ad.callout.api.impl;

import java.util.UUID;
import java.util.function.Supplier;

import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.callout.api.ICalloutInstance;
import org.junit.Ignore;

@Ignore
public class MockedCalloutInstance2 implements ICalloutInstance
{
	private String id = "MockedCallout-" + UUID.randomUUID();
	private boolean called = false;
	private Supplier<Exception> onExecuteFailException = null;

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public void execute(ICalloutExecutor executor, ICalloutField field) throws Exception
	{
		this.called = true;
		if (onExecuteFailException != null)
		{
			final Exception ex = onExecuteFailException.get();
			throw ex;
		}
	}

	public boolean isCalled()
	{
		return called;
	}

	public void setCalled(boolean called)
	{
		this.called = called;
	}

	public MockedCalloutInstance2 setOnExecuteFailException(Supplier<Exception> onExecuteFailException)
	{
		this.onExecuteFailException = onExecuteFailException;
		return this;
	}
}
