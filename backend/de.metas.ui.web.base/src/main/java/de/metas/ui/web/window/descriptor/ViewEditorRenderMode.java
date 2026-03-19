package de.metas.ui.web.window.descriptor;

 

import de.metas.util.lang.ReferenceListAwareEnum;
import de.metas.util.lang.ReferenceListAwareEnums;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nullable;

/**
 * Decides if and how a given field may be edited by a user.
 */
public enum ViewEditorRenderMode implements ReferenceListAwareEnum
{
	/**
	 * Read-only
	 */
	NEVER("N", "never"),

	/**
	 * after a right-click
	 */
	ON_DEMAND("D", "on-demand"),

	ALWAYS("Y", "always");

	private static final ReferenceListAwareEnums.ValuesIndex<ViewEditorRenderMode> index = ReferenceListAwareEnums.index(values());

	@Getter
	private final String code;
	private final String json;

	ViewEditorRenderMode(@NonNull final String code, @NonNull final String json)
	{
		this.code = code;
		this.json = json;
	}

	public static ViewEditorRenderMode ofCode(@NonNull final String code) { return index.ofCode(code); }

	public static ViewEditorRenderMode ofNullableCode(@Nullable final String code) { return index.ofNullableCode(code); }

	public String toJson()
	{
		return json;
	}

	public boolean isEditable()
	{
		return this == ON_DEMAND || this == ALWAYS;
	}
}
