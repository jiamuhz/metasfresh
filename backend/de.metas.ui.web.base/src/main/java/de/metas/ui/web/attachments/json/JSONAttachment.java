package de.metas.ui.web.attachments.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.metas.ui.web.attachments.IDocumentAttachmentEntry;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@ToString
public class JSONAttachment implements Serializable
{
	public static JSONAttachment of(IDocumentAttachmentEntry entry)
	{
		return new JSONAttachment(entry.getId(), entry.getFilename());
	}

	@JsonProperty("id")
	@Getter
	private final String id;

	@JsonProperty("name")
	@Getter
	private final String name;

	@JsonProperty("allowDelete")
	@Setter
	@Getter
	private boolean allowDelete = false;

	/* package */ JSONAttachment(@JsonProperty("id") final DocumentId id, @JsonProperty("name") final String name)
	{
		this.id = id.toJson();
		this.name = name;
	}
}
