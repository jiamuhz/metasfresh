package de.metas.ui.web.document.references.controller;

import java.io.IOException;
import java.util.Collection;

import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import de.metas.logging.LogManager;
import de.metas.ui.web.document.references.json.JSONDocumentReferencesEvent;
import de.metas.ui.web.document.references.json.JSONDocumentReferencesGroup;
import lombok.Getter;
import lombok.NonNull;

 

class JSONDocumentReferencesEventPublisher
{
	public static JSONDocumentReferencesEventPublisher newInstance()
	{
		return new JSONDocumentReferencesEventPublisher();
	}

	private static final Logger logger = LogManager.getLogger(JSONDocumentReferencesEventPublisher.class);

	@Getter
	private final SseEmitter sseEmiter = new SseEmitter();

	private JSONDocumentReferencesEventPublisher()
	{
	}

	public void publishPartialResults(@NonNull final Collection<JSONDocumentReferencesGroup> groups)
	{
		if (groups.isEmpty())
		{
			return;
		}

		for (final JSONDocumentReferencesGroup group : groups)
		{
			publishPartialResult(group);
		}
	}

	public void publishPartialResult(@NonNull final JSONDocumentReferencesGroup group)
	{
		try
		{
			sseEmiter.send(JSONDocumentReferencesEvent.partialResult(group), MediaType.APPLICATION_JSON);
		}
		catch (final IOException ex)
		{
			throw new AdempiereException("Failed sending partial result: " + group, ex);
		}
	}

	public void publishCompleted()
	{
		try
		{
			sseEmiter.send(JSONDocumentReferencesEvent.COMPLETED, MediaType.APPLICATION_JSON);
			sseEmiter.complete();
		}
		catch (final IOException ex)
		{
			logger.warn("Failed publishing the COMPLETED event. Ignored.", ex);
		}
	}

	public void publishCompletedWithError(final Throwable ex)
	{
		try
		{
			sseEmiter.send(JSONDocumentReferencesEvent.COMPLETED, MediaType.APPLICATION_JSON);
			sseEmiter.completeWithError(ex);
		}
		catch (final IOException ioe)
		{
			ioe.addSuppressed(ex);
			logger.warn("Failed publishing the COMPLETED event. Ignored.", ioe);
		}
	}

}
