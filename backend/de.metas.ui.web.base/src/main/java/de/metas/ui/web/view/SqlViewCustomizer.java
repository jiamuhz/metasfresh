package de.metas.ui.web.view;

import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.window.datatypes.WindowId;



public interface SqlViewCustomizer extends ViewRowCustomizer
{
	WindowId getWindowId();

	ViewProfile getProfile();

	default void customizeSqlViewBinding(final SqlViewBinding.Builder sqlViewBindingBuilder)
	{
		// nothing
	}

	default void customizeViewLayout(final ViewLayout.ChangeBuilder viewLayoutBuilder)
	{
		// nothing
	}

	@Override
	default void customizeViewRow(final ViewRow.Builder rowBuilder)
	{
		// nothing
	}
}
