package de.metas.ui.web.window.datatypes;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;



/**
 * Describes how a panel of elements shall be rendered.
 * 
 *
 *
 */
public enum PanelLayoutType
{
	/** Render the elements in a regular panel (default) */
	Panel("panel"),
	/** Render the single element as an full screen overlay having displayed only that field and everything behind it's blurry */
	SingleOverlayField("singleOverlayField");

	private final String json;

	private PanelLayoutType(final String json)
	{
		this.json = json;
	}

	@JsonValue
	public String toJson()
	{
		return json;
	}

	@JsonCreator
	public static PanelLayoutType fromNullableJson(final String json)
	{
		if (json == null)
		{
			return null;
		}

		final PanelLayoutType type = json2type.get(json);
		if (type == null)
		{
			throw new IllegalArgumentException("No " + PanelLayoutType.class + " found for json: " + json);
		}

		return type;
	}

	private static final ImmutableMap<String, PanelLayoutType> json2type = Maps.uniqueIndex(Arrays.asList(values()), PanelLayoutType::toJson);
}
