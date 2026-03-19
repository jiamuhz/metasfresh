package de.metas.ui.web.document.references;

import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.util.lang.Priority;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.time.Duration;


@Value
public class WebuiDocumentReference
{
	WebuiDocumentReferenceId id;
	String internalName;
	ITranslatableString caption;
	WebuiDocumentReferenceTargetWindow targetWindow;
	Priority priority;
	int documentsCount;
	DocumentFilter filter;
	Duration loadDuration;

	@Builder
	private WebuiDocumentReference(
			@NonNull final WebuiDocumentReferenceId id,
			@Nullable final String internalName,
			@NonNull final ITranslatableString caption,
			@NonNull final WebuiDocumentReferenceTargetWindow targetWindow,
			@NonNull final Priority priority,
			final int documentsCount,
			@NonNull final DocumentFilter filter,
			@Nullable final Duration loadDuration)
	{
		this.id = id;
		this.internalName = internalName;
		this.caption = caption;
		this.targetWindow = targetWindow;
		this.priority = priority;
		this.documentsCount = documentsCount;
		this.filter = filter;
		this.loadDuration = loadDuration;
	}

	public String getCaption(final String adLanguage)
	{
		return caption.translate(adLanguage);
	}
}
