package de.metas.ui.web.window.datatypes.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.compiere.model.Null;

import javax.annotation.Nullable;

  
/**
 * JSON null marker.
 *
 *
 *
 */
@Schema(description = "null-value")
@JsonSerialize(using = JSONNullValueSerializer.class)
public final class JSONNullValue
{
	public static Object wrapIfNull(@Nullable final Object value)
	{
		return value != null && !isNull(value) ? value : instance;
	}

	public static boolean isNull(@Nullable final Object value)
	{
		return value == null || value instanceof JSONNullValue || value instanceof Null;
	}

	public static final transient JSONNullValue instance = new JSONNullValue();

	private JSONNullValue()
	{
	}

	@Override
	public String toString()
	{
		return "null";
	}

	@Nullable
	public static Object toNullIfInstance(@Nullable final Object jsonValueObj)
	{
		return !isNull(jsonValueObj) ? jsonValueObj : null;
	}
}
