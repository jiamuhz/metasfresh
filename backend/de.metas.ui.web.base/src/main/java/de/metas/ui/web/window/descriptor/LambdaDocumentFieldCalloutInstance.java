package de.metas.ui.web.window.descriptor;

import java.util.Set;

import org.adempiere.ad.callout.api.ICalloutExecutor;
import org.adempiere.ad.callout.api.ICalloutField;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

import de.metas.util.Check;

/* package */class LambdaDocumentFieldCalloutInstance implements IDocumentFieldCalloutInstance
{
	private final String id;
	private final Set<String> dependsOnFieldNames;
	private final ILambdaDocumentFieldCallout lambdaCallout;

	public LambdaDocumentFieldCalloutInstance(final String triggeringFieldName, final ILambdaDocumentFieldCallout lambdaCallout)
	{
		super();

		Check.assumeNotEmpty(triggeringFieldName, "triggeringFieldName is not empty");
		Check.assumeNotNull(lambdaCallout, "Parameter lambdaCallout is not null");

		id = "lambda-" + triggeringFieldName + "-" + lambdaCallout.toString();
		dependsOnFieldNames = ImmutableSet.of(triggeringFieldName);
		this.lambdaCallout = lambdaCallout;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.toString();
	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public Set<String> getDependsOnFieldNames()
	{
		return dependsOnFieldNames;
	}

	@Override
	public void execute(final ICalloutExecutor executor, final ICalloutField field) throws Exception
	{
		lambdaCallout.execute(field);
	}

}
