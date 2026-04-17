package de.metas.ui.web.window.descriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.util.Check;
import lombok.NonNull;

 

/**
 * Single row layout (applies for header document but also for included document, when editing in advanced mode).
 *
 * 表单布局，用于 Header Tab  and  Advanced Edit Dialog
 * @作者  嘉木
 */
public class DocumentFormLayout
{
	public static Builder builder()
	{
		return new Builder();
	}

	private final WindowDocumentTypeId windowDocumentTypeId;
	private final ITranslatableString caption;
	private final ITranslatableString description;

	private final List<DocumentLayoutUISectionDescriptor> sections;
	// sections 压扁的内容
	private transient List<DocumentLayoutElementDescriptor> _elements = null;

	private DocumentFormLayout(final Builder builder)
	{
		windowDocumentTypeId = builder.windowId;
		Check.assumeNotNull(windowDocumentTypeId, "Parameter windowId is not null");

		caption = TranslatableStrings.nullToEmpty(builder.caption);
		description = TranslatableStrings.nullToEmpty(builder.description);
		sections = ImmutableList.copyOf(builder.buildSections());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("caption", caption)
				.add("sections", sections.isEmpty() ? null : sections)
				.toString();
	}

	public WindowDocumentTypeId getWindowDocumentTypeId()
	{
		return windowDocumentTypeId;
	}

	public String getCaption(final String adLanguage)
	{
		return caption.translate(adLanguage);
	}

	public String getDescription(final String adLanguage)
	{
		return description.translate(adLanguage);
	}

	public List<DocumentLayoutUISectionDescriptor> getSections()
	{
		return sections;
	}

	public List<DocumentLayoutElementDescriptor> getElements()
	{
		List<DocumentLayoutElementDescriptor> elements = _elements;
		if (elements == null)
		{
			elements = sections.stream()
					.flatMap(section -> section.getColumns().stream())
					.flatMap(column -> column.getElementGroups().stream())
					.flatMap(elementGroup -> elementGroup.getElementLines().stream())
					.flatMap(elementLine -> elementLine.getElements().stream())
					.collect(ImmutableList.toImmutableList());
		}
		_elements = elements;
		return elements;
	}

	public boolean isEmpty()
	{
		return sections.isEmpty();
	}

	public static final class Builder
	{
		public WindowDocumentTypeId windowId;
		private ITranslatableString caption;
		private ITranslatableString description;

		private final ArrayList<DocumentLayoutUISectionDescriptor.Builder> sectionBuilders = new ArrayList<>();

		private Builder()
		{
			super();
		}

		public DocumentFormLayout build()
		{
			return new DocumentFormLayout(this);
		}

		private List<DocumentLayoutUISectionDescriptor> buildSections()
		{
			return sectionBuilders
					.stream()
					.filter(DocumentLayoutUISectionDescriptor.Builder::isValid)
					.map(DocumentLayoutUISectionDescriptor.Builder::build)
					.filter(DocumentLayoutUISectionDescriptor::hasColumns)
					.collect(ImmutableList.toImmutableList());
		}

		@Override
		public String toString()
		{
			return MoreObjects.toStringHelper(this)
					.add("caption", caption)
					.add("sections-count", sectionBuilders.size())
					.toString();
		}

		public Builder setWindowId(final WindowDocumentTypeId windowId)
		{
			this.windowId = windowId;
			return this;
		}

		public Builder setCaption(final ITranslatableString caption)
		{
			this.caption = caption;
			return this;
		}

		public Builder setDescription(final ITranslatableString description)
		{
			this.description = description;
			return this;
		}

		public Builder addSection(@NonNull final DocumentLayoutUISectionDescriptor.Builder sectionBuilderToAdd)
		{
			sectionBuilders.add(sectionBuilderToAdd);
			return this;
		}

		public Builder addSections(@NonNull final Collection<DocumentLayoutUISectionDescriptor.Builder> sectionBuildersToAdd)
		{
			sectionBuilders.addAll(sectionBuildersToAdd);
			return this;
		}

		public boolean isEmpty()
		{
			return sectionBuilders.isEmpty();
		}
	}

}
