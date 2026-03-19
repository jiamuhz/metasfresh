package de.metas.ui.web.view;

import java.util.HashMap;

import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
import lombok.ToString;

 

@ToString
public final class PlainDefaultViewProfileIdProvider implements DefaultViewProfileIdProvider
{
	private final HashMap<WindowId, ViewProfileId> defaultProfileIdByWindowId = new HashMap<>();

	@Override
	public ViewProfileId getDefaultProfileIdByWindowId(final WindowId windowId)
	{
		return defaultProfileIdByWindowId.get(windowId);
	}

	public void setDefaultProfileId(@NonNull final WindowId windowId, final ViewProfileId profileId)
	{
		if (ViewProfileId.isNull(profileId))
		{
			defaultProfileIdByWindowId.remove(windowId);
		}
		else
		{
			defaultProfileIdByWindowId.put(windowId, profileId);
		}
	}

	public void setDefaultProfileIdIfAbsent(@NonNull final WindowId windowId, @NonNull final ViewProfileId profileId)
	{
		defaultProfileIdByWindowId.putIfAbsent(windowId, profileId);
	}
}
