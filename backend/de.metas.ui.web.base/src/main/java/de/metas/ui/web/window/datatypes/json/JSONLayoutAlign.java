package de.metas.ui.web.window.datatypes.json;

import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.window.descriptor.LayoutAlign;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(description = "layout-align")
public enum JSONLayoutAlign
{
	left, center, right, justify;

	public static JSONLayoutAlign fromNullable(final LayoutAlign align)
	{
		if (align == null)
		{
			return null;
		}
		final JSONLayoutAlign jsonWidgetType = type2json.get(align);
		if (jsonWidgetType == null)
		{
			throw new IllegalArgumentException("Cannot convert " + align + " to " + JSONLayoutAlign.class);
		}
		return jsonWidgetType;
	}

	private static final Map<LayoutAlign, JSONLayoutAlign> type2json = ImmutableMap.<LayoutAlign, JSONLayoutAlign> builder()
			.put(LayoutAlign.Left, left)
			.put(LayoutAlign.Center, center)
			.put(LayoutAlign.Right, right)
			.put(LayoutAlign.Justify, justify)
			.build();
}
