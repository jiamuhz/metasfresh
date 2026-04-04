 

package de.metas.ui.web.view;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class ViewHeaderProperties
{
	public static final ViewHeaderProperties EMPTY = builder().build();

	@NonNull
	@Singular
	ImmutableList<ViewHeaderPropertiesGroup> groups;

	public boolean isEmpty()
	{
		return groups.isEmpty();
	}

	public ViewHeaderProperties combine(@NonNull final ViewHeaderProperties other)
	{
		if (isEmpty())
		{
			return other;
		}
		else if (other.isEmpty())
		{
			return this;
		}
		else
		{
			return ViewHeaderProperties.builder()
					.groups(ImmutableList.<ViewHeaderPropertiesGroup>builder()
							.addAll(this.groups)
							.addAll(other.groups)
							.build())
					.build();
		}
	}
}
