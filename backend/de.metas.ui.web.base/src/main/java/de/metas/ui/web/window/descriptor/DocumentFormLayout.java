package de.metas.ui.web.window.descriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.util.Check;
import lombok.NonNull;

/*
 * #%L
 * metasfresh-webui-api
 * %%
 * Copyright (C) 2017 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

/**
 * Single row layout (applies for header document but also for included document, when editing in advanced mode).
 *
 * @author metas-dev <dev@metasfresh.com>
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

	private final WindowId windowId;
	private final ITranslatableString caption;
	private final ITranslatableString description;

	private final List<DocumentLayoutUISectionDescriptor> sections;
	// sections 压扁的内容
	private transient List<DocumentLayoutUIControlDescriptor> _elements = null;

	private DocumentFormLayout(final Builder builder)
	{
		windowId = builder.windowId;
		Check.assumeNotNull(windowId, "Parameter windowId is not null");

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

	public WindowId getWindowId()
	{
		return windowId;
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

	public List<DocumentLayoutUIControlDescriptor> getElements()
	{
		List<DocumentLayoutUIControlDescriptor> elements = _elements;
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
		public WindowId windowId;
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

		public Builder setWindowId(final WindowId windowId)
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
