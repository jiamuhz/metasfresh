

package de.metas.ui.web.view.descriptor;

import de.metas.cache.CCache;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.geo_location.GeoLocationDocumentService;
import de.metas.ui.web.view.SqlViewCustomizer;
import de.metas.ui.web.view.ViewProfile;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;
import de.metas.ui.web.window.descriptor.factory.DocumentDescriptorFactory;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class SqlViewLayoutFactory
{
	private final DocumentDescriptorFactory documentDescriptorFactory;
	private final SqlViewBindingFactory viewBindingsFactory;
	private final SqlViewCustomizerMap viewCustomizers;
	private final GeoLocationDocumentService geoLocationDocumentService;

	private final transient CCache<ViewLayoutKey, ViewLayout> cache = CCache.newCache("SqlViewLayouts", 20, 0);

	@Builder
	private SqlViewLayoutFactory(
			@NonNull final DocumentDescriptorFactory documentDescriptorFactory,
			@NonNull final SqlViewBindingFactory viewBindingsFactory,
			@NonNull final SqlViewCustomizerMap viewCustomizers,
			@NonNull final GeoLocationDocumentService geoLocationDocumentService)
	{
		this.documentDescriptorFactory = documentDescriptorFactory;
		this.viewBindingsFactory = viewBindingsFactory;
		this.viewCustomizers = viewCustomizers;
		this.geoLocationDocumentService = geoLocationDocumentService;
	}

	public ViewLayout getViewLayout(
			@NonNull final WindowId windowId,
			@NonNull final JSONViewDataType viewDataType,
			@Nullable final ViewProfileId profileId)
	{
		final ViewLayoutKey viewLayoutKey = new ViewLayoutKey(windowId, viewDataType, profileId);
		return cache.getOrLoad(viewLayoutKey, this::createViewLayout);
	}

	private ViewLayout createViewLayout(final ViewLayoutKey viewLayoutKey)
	{
		final ViewLayout viewLayoutOrig = documentDescriptorFactory.getDocumentDescriptor(viewLayoutKey.getWindowId())
				.getViewLayout(viewLayoutKey.getViewDataType());

		final SqlViewBinding sqlViewBinding = getViewBinding(
				viewLayoutKey.getWindowId(),
				viewLayoutKey.getViewDataType().getRequiredFieldCharacteristic(),
				viewLayoutKey.getProfileId());
		final Collection<DocumentFilterDescriptor> filters = sqlViewBinding.getViewFilterDescriptorsProvider().getAll();
		final boolean hasTreeSupport = sqlViewBinding.hasGroupingFields();

		final ViewLayout.ChangeBuilder viewLayoutBuilder = viewLayoutOrig.toBuilder()
				.profileId(viewLayoutKey.getProfileId())
				.filters(filters)
				.treeSupport(hasTreeSupport, true/* treeCollapsible */, ViewLayout.TreeExpandedDepth_AllCollapsed)
				.geoLocationSupport(geoLocationDocumentService.containsGeoLocationFilter(filters));

		//
		// Customize the view layout
		// NOTE to developer: keep it last, right before build().
		final SqlViewCustomizer sqlViewCustomizer = viewCustomizers.getOrNull(viewLayoutKey.getWindowId(), viewLayoutKey.getProfileId());
		if (sqlViewCustomizer != null)
		{
			sqlViewCustomizer.customizeViewLayout(viewLayoutBuilder);
		}

		return viewLayoutBuilder.build();
	}

	public SqlViewBinding getViewBinding(
			@NonNull final WindowId windowId,
			@Nullable final Characteristic requiredFieldCharacteristic,
			@Nullable final ViewProfileId profileId)
	{
		return viewBindingsFactory.getViewBinding(windowId, requiredFieldCharacteristic, profileId);
	}

	public List<ViewProfile> getAvailableProfiles(final WindowId windowId)
	{
		return viewCustomizers.getViewProfilesByWindowId(windowId);
	}

	@Value
	private static class ViewLayoutKey
	{
		@NonNull WindowId windowId;
		@NonNull JSONViewDataType viewDataType;
		@Nullable ViewProfileId profileId;
	}

}
