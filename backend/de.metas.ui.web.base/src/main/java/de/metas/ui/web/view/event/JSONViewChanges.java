

package de.metas.ui.web.view.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.websocket.WebsocketEndpointAware;
import de.metas.websocket.WebsocketTopicName;
import de.metas.ui.web.websocket.WebsocketTopicNames;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewChanges implements WebsocketEndpointAware
{
	public static JSONViewChanges of(@NonNull final ViewChanges changes)
	{
		return new JSONViewChanges(changes);
	}

	@JsonProperty("viewId")
	String viewId;
	@JsonProperty("windowId")
	WindowDocumentTypeId windowId;

	@JsonProperty("fullyChanged")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Boolean fullyChanged;

	@JsonProperty("changedIds")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	Set<String> changedIds;

	@JsonProperty("headerPropertiesChanged")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Boolean headerPropertiesChanged;

	@JsonIgnore
	WebsocketTopicName websocketEndpoint;

	private JSONViewChanges(@NonNull final ViewChanges changes)
	{
		viewId = changes.getViewId().getViewId();
		windowId = changes.getViewId().getWindowId();

		final DocumentIdsSelection changedRowIds = changes.getChangedRowIds();
		if (changedRowIds.isAll())
		{
			fullyChanged = Boolean.TRUE;
			this.changedIds = null;
		}
		else if (changedRowIds.isEmpty())
		{
			// TODO: shall we throw an exception in this case? ...because basically it's not valid!
			fullyChanged = null;
			changedIds = null;
		}
		else
		{
			fullyChanged = Boolean.FALSE;
			this.changedIds = changedRowIds.toJsonSet();
		}
		headerPropertiesChanged = changes.isHeaderPropertiesChanged() ? true : null;

		websocketEndpoint = WebsocketTopicNames.buildViewNotificationsTopicName(viewId);
	}
}

