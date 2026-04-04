

package de.metas.ui.web.view.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.ViewHeaderPropertiesGroup;
import lombok.NonNull;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewHeaderPropertiesGroup
{
	@JsonProperty("entries")
	private ImmutableList<JSONViewHeaderProperty> entries;

	private static final JSONViewHeaderPropertiesGroup EMPTY = new JSONViewHeaderPropertiesGroup(ImmutableList.of());

	public static JSONViewHeaderPropertiesGroup of(@NonNull final ViewHeaderPropertiesGroup group, final String adLanguage)
	{
		if (group.getEntries().isEmpty())
		{
			return EMPTY;
		}

		final ImmutableList<JSONViewHeaderProperty> jsonEntries = group.getEntries()
				.stream()
				.map(entry -> JSONViewHeaderProperty.of(entry, adLanguage))
				.collect(ImmutableList.toImmutableList());

		return new JSONViewHeaderPropertiesGroup(jsonEntries);
	}

	private JSONViewHeaderPropertiesGroup(
			@JsonProperty("entries") final List<JSONViewHeaderProperty> entries)
	{
		this.entries = entries != null ? ImmutableList.copyOf(entries) : ImmutableList.of();
	}
}
