package de.metas.ui.web.window.descriptor;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class DocumentLayoutElementLineDescriptor
{
	public static final Builder builder()
	{
		return new Builder();
	}

	private final String internalName;
	private final List<DocumentLayoutElementDescriptor> elements;

	private DocumentLayoutElementLineDescriptor(@NonNull final Builder builder)
	{
		internalName = builder.internalName;
		elements = ImmutableList.copyOf(builder.buildElements());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("internalName", internalName)
				.add("elements", elements.isEmpty() ? null : elements)
				.toString();
	}

	public List<DocumentLayoutElementDescriptor> getElements()
	{
		return elements;
	}

	public static final class Builder
	{
		private static final Logger logger = LogManager.getLogger(DocumentLayoutElementLineDescriptor.Builder.class);

		private String internalName;
		private final List<DocumentLayoutElementDescriptor.Builder> elementsBuilders = new ArrayList<>();

		private Builder()
		{
		}

		@Override
		public String toString()
		{
			return MoreObjects.toStringHelper(this)
					.omitNullValues()
					.add("internalName", internalName)
					.add("elements-count", elementsBuilders.size())
					.toString();
		}

		public DocumentLayoutElementLineDescriptor build()
		{
			final DocumentLayoutElementLineDescriptor result = new DocumentLayoutElementLineDescriptor(this);

			logger.trace("Built {} for {}", result, this);
			return result;
		}

		private List<DocumentLayoutElementDescriptor> buildElements()
		{
			return elementsBuilders
					.stream()
					.filter(elementBuilder -> checkValid(elementBuilder))
					.map(elementBuilder -> elementBuilder.build())
					.filter(element -> checkValid(element))
					.collect(GuavaCollectors.toImmutableList());
		}

		private final boolean checkValid(final DocumentLayoutElementDescriptor.Builder elementBuilder)
		{
			if (elementBuilder.isConsumed())
			{
				logger.trace("Skip adding {} to {} because it's already consumed", elementBuilder, this);
				return false;
			}

			if (elementBuilder.isEmpty())
			{
				logger.trace("Skip adding {} to {} because it's empty", elementBuilder, this);
				return false;
			}

			return true;
		}

		private final boolean checkValid(final DocumentLayoutElementDescriptor element)
		{
			if (element.isEmpty())
			{
				logger.trace("Skip adding {} to {} because it does not have fields", element, this);
				return false;
			}

			return true;
		}

		public Builder setInternalName(final String internalName)
		{
			this.internalName = internalName;
			return this;
		}

		public Builder addElement(final DocumentLayoutElementDescriptor.Builder elementBuilder)
		{
			elementsBuilders.add(elementBuilder);
			return this;
		}

		public boolean hasElements()
		{
			return !elementsBuilders.isEmpty();
		}

		public Stream<DocumentLayoutElementDescriptor.Builder> streamElementBuilders()
		{
			return elementsBuilders.stream();
		}

	}

}
