package de.metas.ui.web.exceptions;

import org.adempiere.exceptions.AdempiereException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.metas.i18n.TranslatableStrings;

/**
 * Exception thrown when a deprecated REST API is called but this is not allowed.
 *
 *
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "You are using deprecated REST API, which is not allowed. Please check configuration.")
public class DeprecatedRestAPINotAllowedException extends AdempiereException
{
	public DeprecatedRestAPINotAllowedException()
	{
		super(TranslatableStrings.empty());
	}
}
