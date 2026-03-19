package de.metas.ui.web.view;

import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.ToString;

import java.util.List;
import java.util.Objects;



@ToString
public final class CompositeDefaultViewProfileIdProvider implements DefaultViewProfileIdProvider
{
	public static final CompositeDefaultViewProfileIdProvider of(final List<DefaultViewProfileIdProvider> providers)
	{
		return new CompositeDefaultViewProfileIdProvider(providers);
	}

	private final PlainDefaultViewProfileIdProvider overrides;
	private final ImmutableList<DefaultViewProfileIdProvider> providers;
	private final PlainDefaultViewProfileIdProvider fallback;

	private CompositeDefaultViewProfileIdProvider(final List<DefaultViewProfileIdProvider> providers)
	{
		overrides = new PlainDefaultViewProfileIdProvider();
		fallback = new PlainDefaultViewProfileIdProvider();
		this.providers = ImmutableList.<DefaultViewProfileIdProvider> builder()
				.add(overrides)
				.addAll(providers)
				.add(fallback)
				.build();
	}

	@Override
	public ViewProfileId getDefaultProfileIdByWindowId(final WindowId windowId)
	{
		return providers.stream()
				.map(provider -> provider.getDefaultProfileIdByWindowId(windowId))
				.filter(Objects::nonNull)
				.findFirst()
				.orElse(ViewProfileId.NULL);
	}

	public void setDefaultProfileIdOverride(WindowId windowId, ViewProfileId profileId)
	{
		overrides.setDefaultProfileId(windowId, profileId);
	}

	public void setDefaultProfileIdFallback(WindowId windowId, ViewProfileId profileId)
	{
		fallback.setDefaultProfileId(windowId, profileId);
	}
	
	public void setDefaultProfileIdFallbackIfAbsent(WindowId windowId, ViewProfileId profileId)
	{
		fallback.setDefaultProfileIdIfAbsent(windowId, profileId);
	}

}
