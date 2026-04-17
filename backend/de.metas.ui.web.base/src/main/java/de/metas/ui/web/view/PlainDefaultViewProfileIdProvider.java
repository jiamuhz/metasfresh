package de.metas.ui.web.view;

import java.util.HashMap;

import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import lombok.NonNull;
import lombok.ToString;

 

@ToString
public final class PlainDefaultViewProfileIdProvider implements DefaultViewProfileIdProvider
{
	private final HashMap<WindowDocumentTypeId, ViewProfileId> defaultProfileIdByWindowId = new HashMap<>();

	@Override
	public ViewProfileId getDefaultProfileIdByWindowId(final WindowDocumentTypeId windowId)
	{
		return defaultProfileIdByWindowId.get(windowId);
	}

	public void setDefaultProfileId(@NonNull final WindowDocumentTypeId windowId, final ViewProfileId profileId)
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

	public void setDefaultProfileIdIfAbsent(@NonNull final WindowDocumentTypeId windowId, @NonNull final ViewProfileId profileId)
	{
		defaultProfileIdByWindowId.putIfAbsent(windowId, profileId);
	}
}
