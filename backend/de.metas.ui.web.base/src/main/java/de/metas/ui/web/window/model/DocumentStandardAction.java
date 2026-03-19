package de.metas.ui.web.window.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;



/**
 * Document's standard action (e.g. new, delete, print etc).
 *
 *
 * @task https://github.com/metasfresh/metasfresh-webui-api/issues/583
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public enum DocumentStandardAction
{
	New("new"), //
	AdvancedEdit("advancedEdit"), //
	Clone("clone"), //
	Email("email"), //
	Letter("letter"), //
	Print("print"), //
	Delete("delete"), //
	Comments("comments")//
	;

	private final String json;

	DocumentStandardAction(@NonNull final String json)
	{
		this.json = json;
	}

	@JsonValue
	public String toJson()
	{
		return json;
	}
}
