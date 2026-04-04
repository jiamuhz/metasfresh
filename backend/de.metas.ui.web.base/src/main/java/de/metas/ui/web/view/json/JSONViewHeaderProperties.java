 

package de.metas.ui.web.view.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.view.ViewHeaderProperties;
import lombok.NonNull;

import java.util.List;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewHeaderProperties
{
	public static JSONViewHeaderProperties of(@NonNull final ViewHeaderProperties properties, final String adLanguage)
	{
		if (properties.getGroups().isEmpty())
		{
			return EMPTY;
		}

		final ImmutableList<JSONViewHeaderPropertiesGroup> jsonEntries = properties.getGroups()
				.stream()
				.map(group -> JSONViewHeaderPropertiesGroup.of(group, adLanguage))
				.collect(ImmutableList.toImmutableList());

		return new JSONViewHeaderProperties(jsonEntries);
	}

	private static final JSONViewHeaderProperties EMPTY = new JSONViewHeaderProperties(ImmutableList.of());

	@JsonProperty("groups")
	private ImmutableList<JSONViewHeaderPropertiesGroup> groups;

	private JSONViewHeaderProperties(
			@JsonProperty("groups") final List<JSONViewHeaderPropertiesGroup> groups)
	{
		this.groups = groups != null ? ImmutableList.copyOf(groups) : ImmutableList.of();
	}

}
