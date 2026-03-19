package de.metas.ui.web.window.datatypes.json;

import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.window.descriptor.LayoutType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

  
@Schema(enumAsRef = true, description = "JSONLayoutType: \n" +
		"* `primary` - Primary layout: it will render the label and beneath the field\n" +
		"* `primaryLongLabels` - Same as <code>primary</code> but will advice the frontend to render long labels. At the moment it's used for filter checkboxes\n" +
		"* `secondary` - It will render the label and the field on same line\n" +
		"")
public enum JSONLayoutType
{
	primary,

	primaryLongLabels,

	secondary;

	public static JSONLayoutType fromNullable(final LayoutType layoutType)
	{
		if (layoutType == null)
		{
			return null;
		}
		final JSONLayoutType jsonLayoutType = layoutType2json.get(layoutType);
		if (jsonLayoutType == null)
		{
			throw new IllegalArgumentException("Cannot convert " + layoutType + " to " + JSONLayoutType.class);
		}
		return jsonLayoutType;
	}

	private static final Map<LayoutType, JSONLayoutType> layoutType2json = ImmutableMap.<LayoutType, JSONLayoutType> builder()
			.put(LayoutType.primary, JSONLayoutType.primary)
			.put(LayoutType.secondary, JSONLayoutType.secondary)
			.build();

}
