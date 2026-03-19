package de.metas.ui.web.window.datatypes.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;

import java.util.List;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONPatchEvent<PathType>
{
	@Schema(description = "operation")
	public static enum JSONOperation
	{
		replace;
	}

	@JsonProperty("op")
	private final JSONOperation op;
	@JsonProperty("path")
	private final PathType path;
	@JsonProperty("value")
	private final Object value;

	private JSONPatchEvent(
			@JsonProperty("op") final JSONOperation op,
			@JsonProperty("path") final PathType path,
			@JsonProperty("value") final Object value)
	{
		this.op = op;
		this.path = path;
		this.value = value;
	}

	public boolean isReplace()
	{
		return op == JSONOperation.replace;
	}

	public String getValueAsString()
	{
		return value != null ? value.toString() : null;
	}

	public Boolean getValueAsBoolean(final Boolean defaultValue)
	{
		return DisplayType.toBoolean(value, defaultValue);
	}

	public int getValueAsInteger(final int defaultValueIfNull)
	{
		if (value == null)
		{
			return defaultValueIfNull;
		}
		else if (value instanceof Number)
		{
			return ((Number)value).intValue();
		}
		else
		{
			return Integer.parseInt(value.toString());
		}
	}

	public List<Integer> getValueAsIntegersList()
	{
		if (value == null)
		{
			return ImmutableList.of();
		}

		if (value instanceof List<?>)
		{
			@SuppressWarnings("unchecked")
			final List<Integer> intList = (List<Integer>)value;
			return intList;
		}
		else if (value instanceof String)
		{
			return ImmutableList.copyOf(DocumentIdsSelection.ofCommaSeparatedString(value.toString()).toIntSet());
		}
		else
		{
			throw new AdempiereException("Cannot convert value to int list").setParameter("event", this);
		}
	}

	public <ET extends Enum<ET>> ET getValueAsEnum(final Class<ET> enumType)
	{
		if (value == null)
		{
			return null;
		}

		if (enumType.isAssignableFrom(value.getClass()))
		{
			@SuppressWarnings("unchecked")
			final ET valueConv = (ET)value;
			return valueConv;
		}
		else if (value instanceof String)
		{
			return Enum.valueOf(enumType, value.toString());
		}
		else
		{
			throw new AdempiereException("Cannot convert value " + value + " to " + enumType);
		}
	}
}
