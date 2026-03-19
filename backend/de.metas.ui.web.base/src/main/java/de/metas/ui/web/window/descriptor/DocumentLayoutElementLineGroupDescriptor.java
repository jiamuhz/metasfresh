package de.metas.ui.web.window.descriptor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import de.metas.logging.LogManager;
import de.metas.util.GuavaCollectors;
import de.metas.common.util.CoalesceUtil;
import lombok.Getter;
import lombok.NonNull;

  
@SuppressWarnings("serial")
public final class DocumentLayoutElementLineGroupDescriptor implements Serializable
{
	public static Builder builder()
	{
		return new Builder();
	}

	/** Element group type (primary aka bordered, transparent etc) */
	@Getter
	private final LayoutType layoutType;

	@Getter
	private final List<DocumentLayoutElementLineDescriptor> elementLines;

	@Getter
	private final Integer columnCount;

	@Getter
	private final String internalName;

	private DocumentLayoutElementLineGroupDescriptor(final Builder builder)
	{
		layoutType = builder.layoutType;
		elementLines = ImmutableList.copyOf(builder.buildElementLines());
		columnCount = builder.columnCount;
		internalName = builder.internalName;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("type", layoutType)
				.add("columnCount", columnCount)
				.add("internalName", internalName)
				.add("elements", elementLines.isEmpty() ? null : elementLines)
				.toString();
	}

	public boolean hasElementLines()
	{
		return !elementLines.isEmpty();
	}

	public static final class Builder
	{
		private static final Logger logger = LogManager.getLogger(DocumentLayoutElementLineGroupDescriptor.Builder.class);

		private String internalName;
		private LayoutType layoutType;
		public Integer columnCount = null;

		private final List<DocumentLayoutElementLineDescriptor.Builder> elementLinesBuilders = new ArrayList<>();

		private Builder()
		{
		}

		@Override
		public String toString()
		{
			return MoreObjects.toStringHelper(this)
					.omitNullValues()
					.add("internalName", internalName)
					.add("layoutType", layoutType)
					.add("elementsLines-count", elementLinesBuilders.size())
					.toString();
		}

		public DocumentLayoutElementLineGroupDescriptor build()
		{
			final DocumentLayoutElementLineGroupDescriptor result = new DocumentLayoutElementLineGroupDescriptor(this);

			logger.trace("Built {} for {}", result, this);
			return result;
		}

		private List<DocumentLayoutElementLineDescriptor> buildElementLines()
		{
			return elementLinesBuilders
					.stream()
					.map(elementLinesBuilder -> elementLinesBuilder.build())
					.collect(GuavaCollectors.toImmutableList());
		}

		public Builder setInternalName(final String internalName)
		{
			this.internalName = internalName;
			return this;
		}

		public Builder setLayoutType(final LayoutType layoutType)
		{
			this.layoutType = layoutType;
			return this;
		}

		public Builder setLayoutType(final String layoutTypeStr)
		{
			layoutType = LayoutType.fromNullable(layoutTypeStr);
			return this;
		}

		public Builder setColumnCount(final int columnCount)
		{
			this.columnCount = CoalesceUtil.firstGreaterThanZero(columnCount, 1);
			return this;
		}

		public Builder addElementLine(@NonNull final DocumentLayoutElementLineDescriptor.Builder elementLineBuilder)
		{
			elementLinesBuilders.add(elementLineBuilder);
			return this;
		}

		public Builder addElementLines(@NonNull final List<DocumentLayoutElementLineDescriptor.Builder> elementLineBuilders)
		{
			elementLinesBuilders.addAll(elementLineBuilders);
			return this;
		}

		public boolean hasElementLines()
		{
			return !elementLinesBuilders.isEmpty();
		}

		public Stream<DocumentLayoutElementDescriptor.Builder> streamElementBuilders()
		{
			return elementLinesBuilders.stream().flatMap(DocumentLayoutElementLineDescriptor.Builder::streamElementBuilders);
		}
	}
}
