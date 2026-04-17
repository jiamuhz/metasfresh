package de.metas.ui.web.window.descriptor;

import java.util.function.Supplier;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import de.metas.ui.web.cache.ETag;
import de.metas.ui.web.cache.ETagAware;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;


public final class DocumentDescriptor implements ETagAware
{
	public static Builder builder()
	{
		return new Builder();
	}

	private final WindowDocumentLayoutDescriptor layout;
	private final DocumentEntityDescriptor entityDescriptor;

	// ETag support
	private static final Supplier<ETag> nextETagSupplier = ETagAware.newETagGenerator();
	private final ETag eTag = nextETagSupplier.get();

	private DocumentDescriptor(final Builder builder)
	{
		layout = Preconditions.checkNotNull(builder.layout, "layout not null");
		entityDescriptor = Preconditions.checkNotNull(builder.entityDescriptor, "entityDescriptor not null");
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("entity", entityDescriptor)
				.add("layout", layout)
				.add("eTag", eTag)
				.toString();
	}

	public WindowDocumentLayoutDescriptor getLayout()
	{
		return layout;
	}

	public ViewLayout getViewLayout(final JSONViewDataType viewDataType)
	{
		switch (viewDataType)
		{
			case grid:
			{
				return layout.getGridViewLayout();
			}
			case list:
			{
				return layout.getSideListViewLayout();
			}
			default:
			{
				throw new IllegalArgumentException("Invalid viewDataType: " + viewDataType);
			}
		}
	}

	public DocumentEntityDescriptor getEntityDescriptor()
	{
		return entityDescriptor;
	}

	@Override
	public ETag getETag()
	{
		return eTag;
	}

	//
	public static final class Builder
	{
		private WindowDocumentLayoutDescriptor layout;
		private DocumentEntityDescriptor entityDescriptor;

		private Builder()
		{
		}

		public DocumentDescriptor build()
		{
			return new DocumentDescriptor(this);
		}

		public Builder setLayout(final WindowDocumentLayoutDescriptor layout)
		{
			this.layout = layout;
			return this;
		}

		public Builder setEntityDescriptor(final DocumentEntityDescriptor entityDescriptor)
		{
			this.entityDescriptor = entityDescriptor;
			return this;
		}
	}
}
