package de.metas.ui.web.quickinput;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.util.Check;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


public class QuickInputLayoutDescriptor
{
	public static Builder builder()
	{
		return new Builder();
	}

	public static QuickInputLayoutDescriptor onlyFields(
			@NonNull final DocumentEntityDescriptor entityDescriptor,
			@NonNull final String[][] fieldNames)
	{
		Check.assumeNotEmpty(fieldNames, "fieldNames is not empty");

		final Builder layoutBuilder = builder();

		for (final String[] elementFieldNames : fieldNames)
		{
			if (elementFieldNames == null || elementFieldNames.length == 0)
			{
				continue;
			}

			DocumentLayoutElementDescriptor
					.builderOrEmpty(entityDescriptor, elementFieldNames)
					.ifPresent(layoutBuilder::element);
		}
		return layoutBuilder.build();
	}

	/**
	 * @deprecated please use {@link #onlyFields(DocumentEntityDescriptor, String[][])}
	 */
	@Deprecated
	public static QuickInputLayoutDescriptor build(@NonNull final DocumentEntityDescriptor entityDescriptor, @NonNull final String[][] fieldNames) {return onlyFields(entityDescriptor, fieldNames);}

	public static QuickInputLayoutDescriptor onlyFields(
			@NonNull final DocumentEntityDescriptor entityDescriptor,
			@NonNull final List<String> fieldNames)
	{
		Check.assumeNotEmpty(fieldNames, "fieldNames is not empty");

		final Builder layoutBuilder = builder();

		for (final String fieldName : fieldNames)
		{
			if (Check.isBlank(fieldName))
			{
				continue;
			}

			DocumentLayoutElementDescriptor
					.builderOrEmpty(entityDescriptor, fieldName)
					.ifPresent(layoutBuilder::element);
		}

		return layoutBuilder.build();
	}

	@SuppressWarnings("unused")
	public static QuickInputLayoutDescriptor allFields(@NonNull final DocumentEntityDescriptor entityDescriptor)
	{
		final Builder layoutBuilder = builder();

		for (final DocumentFieldDescriptor field : entityDescriptor.getFields())
		{
			final DocumentLayoutElementDescriptor.Builder element = DocumentLayoutElementDescriptor.builder(field);
			layoutBuilder.element(element);
		}

		return layoutBuilder.build();
	}

	private final List<DocumentLayoutElementDescriptor> elements;

	private QuickInputLayoutDescriptor(final List<DocumentLayoutElementDescriptor> elements)
	{
		this.elements = ImmutableList.copyOf(elements);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("elements", elements.isEmpty() ? null : elements)
				.toString();
	}

	public List<DocumentLayoutElementDescriptor> getElements()
	{
		return elements;
	}

	public static final class Builder
	{
		private final List<DocumentLayoutElementDescriptor> elements = new ArrayList<>();

		private Builder()
		{
		}

		public QuickInputLayoutDescriptor build()
		{
			return new QuickInputLayoutDescriptor(elements);
		}

		public Builder element(@NonNull final DocumentLayoutElementDescriptor.Builder elementBuilder)
		{
			elements.add(elementBuilder.build());
			return this;
		}
	}

}
