package org.adempiere.ad.expression.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.ad.expression.api.IExpression;

import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStringBuilder;
import de.metas.i18n.TranslatableStrings;
import de.metas.util.Check;

/** */

/**
 * Exception thrown when expression evaluation fails
 *
 * @author tsa
 *
 */
public class ExpressionEvaluationException extends ExpressionException
{
	public static final ExpressionEvaluationException wrapIfNeeded(final Throwable throwable)
	{
		Check.assumeNotNull(throwable, "throwable not null");

		if (throwable instanceof ExpressionEvaluationException)
		{
			return (ExpressionEvaluationException)throwable;
		}

		final Throwable cause = extractCause(throwable);
		if (cause != throwable)
		{
			return wrapIfNeeded(cause);
		}

		return new ExpressionEvaluationException(extractMessage(throwable), cause);
	}

	private static final long serialVersionUID = -4311254481298308224L;

	private final List<IExpression<?>> expressions = new ArrayList<>();

	private String partialEvaluatedExpression;

	public ExpressionEvaluationException(final String msg)
	{
		super(msg);
	}

	public ExpressionEvaluationException(final String msg, final Throwable cause)
	{
		super(msg, cause);
	}

	public ExpressionEvaluationException addExpression(final IExpression<?> expression)
	{
		if (expression == null)
		{
			return this;
		}

		expressions.add(expression);
		resetMessageBuilt();
		return this;
	}

	public ExpressionEvaluationException setPartialEvaluatedExpression(final String partialEvaluatedExpression)
	{
		this.partialEvaluatedExpression = partialEvaluatedExpression;
		resetMessageBuilt();
		return this;
	}

	@Override
	protected ITranslatableString buildMessage()
	{
		final TranslatableStringBuilder message = TranslatableStrings.builder();

		final ITranslatableString originalMessage = getOriginalMessage();
		if (!TranslatableStrings.isBlank(originalMessage))
		{
			message.append(originalMessage);
		}
		else
		{
			message.append("Unknown evaluation error");
		}

		if (!expressions.isEmpty())
		{
			message.append("\nExpressions trace:");

			for (final IExpression<?> expression : expressions)
			{
				message.append("\n * ").appendObj(expression);
			}
		}

		if (!Check.isEmpty(partialEvaluatedExpression))
		{
			message.append("\nPartial evaluated expression: ").append(partialEvaluatedExpression);
		}

		return message.build();
	}
}
