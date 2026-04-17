package de.metas.ui.web.window.exceptions;

import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nullable;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class DocumentFieldReadonlyException extends AdempiereException
{
	public DocumentFieldReadonlyException(@NonNull final String fieldName, @Nullable final Object value)
	{
		super(buildMsg(fieldName, value));
	}

	private static String buildMsg(@NonNull final String fieldName, @Nullable final Object value)
	{
		return "Changing " + fieldName + " to '" + value + "' is not allowed because the field is readonly";
	}
}
