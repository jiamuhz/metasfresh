package de.metas.ui.web.window.descriptor;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.window.datatypes.DebugProperties;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.exceptions.DocumentLayoutDetailNotFoundException;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

  
/**
 *
 * 文档布局 ：包含各种文档布局描述
 *
 */

public final class DocumentLayoutDescriptor
{
	public static Builder builder()
	{
		return new Builder();
	}

	private final WindowDocumentTypeId windowDocumentTypeId;
	private final ITranslatableString caption;

	/**
	 * Special element: Document summary
	 */
	private final DocumentLayoutElementDescriptor documentSummaryElementDescriptor;
	/**
	 * Special element: DocStatus/DocAction
	 */
	private final DocumentLayoutElementDescriptor docActionElementDescriptor;

	/**
	 * 文档的 Header Tab Layout方案
	 */
	private final DocumentFormLayout formLayout;

	/**
	 * 文档的 文档列表 Layout方案
	 */
	private final ViewLayout gridViewLayout;

	/**
	 * Side list layout
	 * 右侧边栏的 文档列表 Layout方案
	 */
	private final ViewLayout sideListViewLayout;

	/**
	 * Single row layout: included tabs.
	 * 文档 Header Tab 下方的 Level=1 详情Tabs
	 */
	private final Map<DetailId, DocumentLayoutDetailDescriptor> details;

	/**
	 * {@link #details} plus their included details.
	 * 文档 Header Tab 下方的 ALL Level Tabs (详情Tabs + 后代详情Tabs)
	 */
	private final Map<DetailId, DocumentLayoutDetailDescriptor> allDetails;

	/**
	 * Misc debugging properties
	 */
	@Getter
	private final DebugProperties debugProperties;

	private DocumentLayoutDescriptor(@NonNull final Builder builder)
	{
		windowDocumentTypeId = builder.windowId;
		Check.assumeNotNull(windowDocumentTypeId, "builder.windowId may not be null; builder={}", builder);

		caption = builder.caption;

		documentSummaryElementDescriptor = builder.documentSummaryElement;
		docActionElementDescriptor = builder.docActionElement;

		Check.assumeNotNull(builder.getSingleRowLayout(), "builder.singleRowLayout may not be null; builder={}", builder);
		formLayout = builder.getSingleRowLayout()
				.setWindowId(windowDocumentTypeId)
				.build();
		gridViewLayout = builder.getGridView()
				.setWindowId(windowDocumentTypeId)
				.build();
		details = ImmutableMap.copyOf(builder.buildDetails());
		allDetails = ImmutableMap.copyOf(builder.buildAllDetails());
		sideListViewLayout = builder.getSideList();

		debugProperties = DebugProperties.ofNullableMap(builder.debugProperties);
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("windowId", windowDocumentTypeId)
				.add("singleRowLayout", formLayout)
				.add("gridView", gridViewLayout)
				.add("details", details.isEmpty() ? null : details)
				.add("sideList", sideListViewLayout)
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

	public DocumentLayoutElementDescriptor getDocumentSummaryElementDescriptor()
	{
		return documentSummaryElementDescriptor;
	}

	public DocumentLayoutElementDescriptor getDocActionElementDescriptor()
	{
		return docActionElementDescriptor;
	}

	public DocumentFormLayout getFormLayout()
	{
		return formLayout;
	}

	/**
	 * @return the layout for grid view (for header documents)
	 */
	public ViewLayout getGridViewLayout()
	{
		return gridViewLayout;
	}

	public ViewLayout getSideListViewLayout()
	{
		return sideListViewLayout;
	}

	/**
	 * the this instance's "direct" details, without their included sub-details.
	 */
	public Collection<DocumentLayoutDetailDescriptor> getDetails()
	{
		return details.values();
	}

	/**
	 * @throws DocumentLayoutDetailNotFoundException
	 */
	public DocumentLayoutDetailDescriptor getDetail(final DetailId detailId)
	{
		final DocumentLayoutDetailDescriptor detail = allDetails.get(detailId);
		if (detail == null)
		{
			throw new DocumentLayoutDetailNotFoundException("Tab '" + detailId + "' was not found. Available tabs are: " + details.keySet());
		}

		return detail;
	}

	public static final class Builder
	{
		private static final Logger logger = LogManager.getLogger(DocumentLayoutDescriptor.Builder.class);

		private WindowDocumentTypeId windowId;
		private ITranslatableString caption = TranslatableStrings.empty();
		@Nullable private DocumentLayoutElementDescriptor documentSummaryElement;
		@Nullable private DocumentLayoutElementDescriptor docActionElement;

		private DocumentFormLayout.Builder singleRowLayout;
		private ViewLayout.Builder _gridView;
		private ViewLayout _sideListView;

		private final List<DocumentLayoutDetailDescriptor> details = new ArrayList<>();

		private final Map<String, String> debugProperties = new LinkedHashMap<>();
		private Stopwatch stopwatch;

		private Builder()
		{
		}

		@Override
		public String toString()
		{
			return MoreObjects.toStringHelper(this)
					.add("windowId", windowId)
					.toString();
		}

		public DocumentLayoutDescriptor build()
		{
			//
			// Debug informations:
			putDebugProperty("generator-thread", Thread.currentThread().getName());
			putDebugProperty("generator-timestamp", Instant.now().toString());
			if (stopwatch != null)
			{
				putDebugProperty("generator-duration", stopwatch.toString());
			}

			return new DocumentLayoutDescriptor(this);
		}

		private Map<DetailId, DocumentLayoutDetailDescriptor> buildDetails()
		{
			final ImmutableMap.Builder<DetailId, DocumentLayoutDetailDescriptor> map = ImmutableMap.builder();
			for (final DocumentLayoutDetailDescriptor detail : details)
			{
				putIfNotEmpty(detail, map);
			}
			return map.build();
		}

		private Map<DetailId, DocumentLayoutDetailDescriptor> buildAllDetails()
		{
			final ImmutableMap.Builder<DetailId, DocumentLayoutDetailDescriptor> map = ImmutableMap.builder();
			for (final DocumentLayoutDetailDescriptor detail : details)
			{
				buildDetailsRecurse(detail, map);
			}
			return map.build();
		}

		private void buildDetailsRecurse(
				@NonNull final DocumentLayoutDetailDescriptor detail,
				@NonNull final ImmutableMap.Builder<DetailId, DocumentLayoutDetailDescriptor> map)
		{
			putIfNotEmpty(detail, map);
			for (final DocumentLayoutDetailDescriptor subDetail : detail.getSubTabLayouts())
			{
				buildDetailsRecurse(subDetail, map);
			}
		}

		private void putIfNotEmpty(final DocumentLayoutDetailDescriptor detail, final ImmutableMap.Builder<DetailId, DocumentLayoutDetailDescriptor> map)
		{
			if (detail.isEmpty())
			{
				return;
			}
			map.put(detail.getDetailId(), detail);
		}

		public Builder setWindowId(final WindowDocumentTypeId windowId)
		{
			this.windowId = windowId;
			return this;
		}

		public Builder setCaption(final ITranslatableString caption)
		{
			this.caption = TranslatableStrings.nullToEmpty(caption);
			return this;
		}

		public Builder setDocumentSummaryElement(@Nullable final DocumentLayoutElementDescriptor documentSummaryElement)
		{
			this.documentSummaryElement = documentSummaryElement;
			return this;
		}

		public Builder setDocActionElement(@Nullable final DocumentLayoutElementDescriptor docActionElement)
		{
			this.docActionElement = docActionElement;
			return this;
		}

		public Builder setGridView(final ViewLayout.Builder gridView)
		{
			this._gridView = gridView;
			return this;
		}

		public Builder setSingleRowLayout(@NonNull final DocumentFormLayout.Builder singleRowLayout)
		{
			this.singleRowLayout = singleRowLayout;
			return this;
		}

		private DocumentFormLayout.Builder getSingleRowLayout()
		{
			return singleRowLayout;
		}

		private ViewLayout.Builder getGridView()
		{
			return _gridView;
		}

		public Builder addDetail(@Nullable final DocumentLayoutDetailDescriptor detail)
		{
			if (detail == null)
			{
				return this;
			}

			if (detail.isEmpty())
			{
				logger.trace("Skip adding detail to layout because it is empty; detail={}", detail);
				return this;
			}
			details.add(detail);

			return this;
		}

		public Builder setSideListView(final ViewLayout sideListViewLayout)
		{
			this._sideListView = sideListViewLayout;
			return this;
		}

		private ViewLayout getSideList()
		{
			Preconditions.checkNotNull(_sideListView, "sideList");
			return _sideListView;
		}

		public Builder putDebugProperty(final String name, final String value)
		{
			debugProperties.put(name, value);
			return this;
		}

		public Builder setStopwatch(final Stopwatch stopwatch)
		{
			this.stopwatch = stopwatch;
			return this;
		}
	}
}
