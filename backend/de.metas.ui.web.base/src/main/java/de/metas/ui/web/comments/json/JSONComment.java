 

package de.metas.ui.web.comments.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonDeserialize(builder = JSONComment.JSONCommentBuilder.class)
public class JSONComment
{
	@NonNull String createdBy;

	@NonNull String created;

	@NonNull String text;

	@JsonPOJOBuilder(withPrefix = "")
	public static class JSONCommentBuilder
	{
	}
}

