package de.metas.ui.web.window.descriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import de.metas.logging.LogManager;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;

/**
 * 列容器 @DocumentLayoutSectionDescriptor
 */
public class DocumentLayoutUIColumnDescriptor
{
	public static final Builder builder()
	{
		return new Builder();
	}

	private final String internalName;
	private final List<DocumentLayoutElementLineGroupDescriptor> elementGroups;

	private DocumentLayoutUIColumnDescriptor(final Builder builder)
	{
		internalName = builder.internalName;
		elementGroups = ImmutableList.copyOf(builder.buildElementGroups());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("internalName", internalName)
				.add("elementGroups", elementGroups.isEmpty() ? null : elementGroups)
				.toString();
	}

	public List<DocumentLayoutElementLineGroupDescriptor> getElementGroups()
	{
		return elementGroups;
	}

	public boolean hasElementGroups()
	{
		return !elementGroups.isEmpty();
	}

	public static final class Builder
	{
		private static final Logger logger = LogManager.getLogger(DocumentLayoutUIColumnDescriptor.Builder.class);

		private String internalName;
		private final List<DocumentLayoutElementLineGroupDescriptor.Builder> elementGroupsBuilders = new ArrayList<>();

		private Builder()
		{
		}

		@Override
		public String toString()
		{
			return MoreObjects.toStringHelper(this)
					.omitNullValues()
					.add("internalName", internalName)
					.add("elementGroups-count", elementGroupsBuilders.size())
					.toString();
		}

		public DocumentLayoutUIColumnDescriptor build()
		{
			final DocumentLayoutUIColumnDescriptor result = new DocumentLayoutUIColumnDescriptor(this);

			logger.trace("Built {} for {}", result, this);
			return result;
		}

		private List<DocumentLayoutElementLineGroupDescriptor> buildElementGroups()
		{
			return elementGroupsBuilders
					.stream()
					.map(elementGroupBuilder -> elementGroupBuilder.build())
					.filter(elementGroup -> checkValid(elementGroup))
					.collect(GuavaCollectors.toImmutableList());
		}

		private boolean checkValid(final DocumentLayoutElementLineGroupDescriptor elementGroup)
		{
			if(!elementGroup.hasElementLines())
			{
				logger.trace("Skip adding {} to {} because it does not have element line", elementGroup, this);
				return false;
			}

			return true;
		}

		public Builder setInternalName(String internalName)
		{
			this.internalName = internalName;
			return this;
		}

		public Builder addElementTabs(@NonNull final List<DocumentLayoutElementLineGroupDescriptor.Builder> elementGroupBuilders)
		{
			elementGroupsBuilders.addAll(elementGroupBuilders);
			return this;
		}

		public Builder addElementGroup(@NonNull final DocumentLayoutElementLineGroupDescriptor.Builder elementGroupBuilder)
		{
			elementGroupsBuilders.add(elementGroupBuilder);
			return this;
		}

		public Stream<DocumentLayoutElementDescriptor.Builder> streamElementBuilders()
		{
			return elementGroupsBuilders.stream().flatMap(DocumentLayoutElementLineGroupDescriptor.Builder::streamElementBuilders);
		}
	}
}
