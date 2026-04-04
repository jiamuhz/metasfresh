package de.metas.ui.web.document.references.json;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.NonNull;
import lombok.Value;



@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDocumentReferencesEvent
{
	public static final JSONDocumentReferencesEvent COMPLETED = new JSONDocumentReferencesEvent(Type.COMPLETED, null);

	public static JSONDocumentReferencesEvent partialResult(@NonNull final JSONDocumentReferencesGroup partialGroup)
	{
		return new JSONDocumentReferencesEvent(Type.PARTIAL_RESULT, partialGroup);
	}

	private enum Type
	{
		PARTIAL_RESULT, COMPLETED,
	}

	private final Type type;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final JSONDocumentReferencesGroup partialGroup;

	private JSONDocumentReferencesEvent(
			@NonNull final Type type,
			@Nullable final JSONDocumentReferencesGroup partialGroup)
	{
		this.type = type;
		this.partialGroup = partialGroup;
	}
}
