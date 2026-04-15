package de.metas.ui.web.window.datatypes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.ImmutableMap;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public final class DebugProperties
{
	public static final DebugProperties EMPTY = new DebugProperties();

	private final ImmutableMap<String, Object> map;

	private DebugProperties(@NonNull final ImmutableMap<String, Object> map)
	{
		this.map = map;
	}

	private DebugProperties()
	{
		this.map = ImmutableMap.of();
	}

	public static DebugProperties ofNullableMap(final Map<String, ?> map)
	{
		if (map == null || map.isEmpty())
		{
			return EMPTY;
		}

		return new DebugProperties(ImmutableMap.copyOf(map));
	}

	public boolean isEmpty()
	{
		return map.isEmpty();
	}

	public Map<String, Object> toMap()
	{
		return map;
	}

	public DebugProperties withProperty(@NonNull final String propertyName, final Object propertyValue)
	{
		final Object existingValue = map.get(propertyName);
		if (Objects.equals(propertyValue, existingValue))
		{
			return this;
		}

		final LinkedHashMap<String, Object> newMap = new LinkedHashMap<>(map);
		if (propertyValue == null)
		{
			newMap.remove(propertyName);
		}
		else
		{
			newMap.put(propertyName, propertyValue);
		}

		return ofNullableMap(newMap);
	}
}
