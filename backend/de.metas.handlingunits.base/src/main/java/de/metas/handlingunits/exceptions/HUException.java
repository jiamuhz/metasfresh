package de.metas.handlingunits.exceptions;

import org.adempiere.exceptions.AdempiereException;

import de.metas.i18n.AdMessageKey;
import de.metas.util.Check;
import lombok.NonNull;

import javax.annotation.Nullable;

/**
 * Root of Handling Units module exceptions hierarchy.
 *
 * @author tsa
 *
 */
public class HUException extends AdempiereException
{

	private static final long serialVersionUID = 800341714184424257L;

	public static final HUException ofAD_Message(final String adMessage)
	{
		final String adMessageEffective = !Check.isEmpty(adMessage, true) ? adMessage : "Error";
		return new HUException("@" + adMessageEffective + "@");
	}

	public HUException(final String message, final Throwable cause)
	{
		super(message, cause);
		appendParametersToMessage(); // preserve HUException's historical behavior
	}

	public HUException(final String message)
	{
		super(message);
		appendParametersToMessage(); // preserve HUException's historical behavior
	}

	public HUException(final Throwable cause)
	{
		super(cause);
		appendParametersToMessage(); // preserve HUException's historical behavior
	}
	
	public HUException(@NonNull final AdMessageKey message)
	{
		super(message);
	}

	@Override
	public HUException setParameter(final @NonNull String name, @Nullable final Object value)
	{
		super.setParameter(name, value);
		return this;
	}

	@Override
	public final @NonNull HUException appendParametersToMessage()
	{
		super.appendParametersToMessage();
		return this;
	}
}
