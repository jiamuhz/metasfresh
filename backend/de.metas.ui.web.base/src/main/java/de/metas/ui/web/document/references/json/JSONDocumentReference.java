package de.metas.ui.web.document.references.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import de.metas.ui.web.document.references.WebuiDocumentReference;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import lombok.NonNull;
import lombok.ToString;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@ToString(of = { "id", "caption", "targetCategory", "targetWindowId" })
public final class JSONDocumentReference
{
	@Nullable
	public static JSONDocumentReference of(final WebuiDocumentReference documentReference, final JSONOptions jsonOpts)
	{
		try
		{
			return new JSONDocumentReference(documentReference, jsonOpts);
		}
		catch (Exception ex)
		{
			logger.warn("Failed converting {} to {}. Skipped", documentReference, JSONDocumentReference.class, ex);
			return null;
		}
	}

	public static List<JSONDocumentReference> ofList(final Collection<WebuiDocumentReference> documentReferences, final JSONOptions jsonOpts)
	{
		if (documentReferences.isEmpty())
		{
			return ImmutableList.of();
		}

		return documentReferences.stream()
				.map(documentReference -> of(documentReference, jsonOpts))
				.filter(Objects::nonNull)
				.collect(ImmutableList.toImmutableList());
	}

	private static final transient Logger logger = LogManager.getLogger(JSONDocumentReference.class);

	@JsonProperty("id")
	private final String id;

	@JsonProperty("priority")
	private final int priority;

	@JsonProperty("internalName")
	private final String internalName;

	@JsonProperty("caption")
	private final String caption;

	@JsonProperty("targetWindowId")
	private final WindowId targetWindowId;
	@JsonProperty("targetCategory")
	private final String targetCategory;

	@JsonProperty("documentsCount")
	private final int documentsCount;

	@JsonProperty("filter")
	private final JSONDocumentFilter filter;

	@JsonProperty("loadDuration")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String loadDuration;

	private JSONDocumentReference(
			@NonNull final WebuiDocumentReference documentReference,
			@NonNull final JSONOptions jsonOpts)
	{
		final String adLanguage = jsonOpts.getAdLanguage();

		id = documentReference.getId().toJson();
		priority = documentReference.getPriority().toInt();

		internalName = documentReference.getInternalName();
		caption = documentReference.getCaption(adLanguage);
		targetWindowId = documentReference.getTargetWindow().getWindowId();
		targetCategory = documentReference.getTargetWindow().getCategory();
		documentsCount = documentReference.getDocumentsCount();

		final DocumentFilter filter = documentReference.getFilter();
		this.filter = JSONDocumentFilter.of(filter, jsonOpts);

		final Duration loadDuration = documentReference.getLoadDuration();
		this.loadDuration = loadDuration != null ? TimeUtil.formatElapsed(loadDuration) : null;
	}
}
