package de.metas.document;

/** */


import org.adempiere.exceptions.AdempiereException;

import de.metas.i18n.ITranslatableString;

import lombok.NonNull;

/**
 * Exception thrown by {@link DocumentNoBuilderException} when building the document number failed.
 *
 * @author tsa
 *
 */
public class DocumentNoBuilderException extends AdempiereException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 2107154047622830909L;

	/**
	 * Wraps given <code>throwable</code> as {@link DocumentNoBuilderException}, if it's not already an {@link DocumentNoBuilderException}.
	 *
	 * @param throwable
	 * @return {@link DocumentNoBuilderException} or <code>null</code> if the throwable was null.
	 */
	public static DocumentNoBuilderException wrapIfNeeded(final Throwable throwable)
	{
		if (throwable == null)
		{
			return null;
		}
		else if (throwable instanceof DocumentNoBuilderException)
		{
			return (DocumentNoBuilderException)throwable;
		}
		else
		{
			return new DocumentNoBuilderException(throwable.getLocalizedMessage(), throwable);
		}
	}

	private boolean skipGenerateDocumentNo = false;

	public DocumentNoBuilderException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	// NOTE: please keep this constructor because it's used in Check.assume methods
	public DocumentNoBuilderException(final String message)
	{
		super(message);
	}

	public DocumentNoBuilderException(@NonNull final ITranslatableString message)
	{
		super(message);
	}

	public DocumentNoBuilderException setSkipGenerateDocumentNo(final boolean skipGenerateDocumentNo)
	{
		this.skipGenerateDocumentNo = skipGenerateDocumentNo;
		return this;
	}

	public boolean isSkipGenerateDocumentNo()
	{
		return skipGenerateDocumentNo;
	}

}
