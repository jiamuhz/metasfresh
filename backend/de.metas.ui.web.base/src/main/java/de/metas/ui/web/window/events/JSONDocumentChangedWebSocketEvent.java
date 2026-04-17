package de.metas.ui.web.window.events;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.common.util.time.SystemTime;
import de.metas.ui.web.websocket.WebsocketTopicNames;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONIncludedTabInfo;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.websocket.WebsocketEndpointAware;
import de.metas.websocket.WebsocketTopicName;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.exceptions.AdempiereException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;



/**
 * Document changed websocket event.
 * <p>
 * Event sent by backend when a document was changed.
 *
 * 
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@EqualsAndHashCode
@ToString
final class JSONDocumentChangedWebSocketEvent implements WebsocketEndpointAware
{
	public static JSONDocumentChangedWebSocketEvent rootDocument(final WindowDocumentTypeId windowId, final DocumentId documentId)
	{
		return new JSONDocumentChangedWebSocketEvent(windowId, documentId);
	}

	@JsonProperty("windowId")
	private final WindowDocumentTypeId windowId;

	@JsonProperty("id")
	private final DocumentId id;

	/**
	 * Event's timestamp.
	 */
	@JsonProperty("timestamp")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String timestamp;

	@JsonProperty("stale")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean stale;

	/**
	 * {@link JSONIncludedTabInfo}s indexed by tabId
	 */
	@JsonProperty("includedTabsInfo")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private HashMap<String, JSONIncludedTabInfo> includedTabsInfoByTabId;

	@JsonProperty("activeTabStaled")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean activeTabStaled;

	private JSONDocumentChangedWebSocketEvent(
			@NonNull final WindowDocumentTypeId windowId,
			@NonNull final DocumentId id)
	{
		this.windowId = windowId;
		this.id = id;

		timestamp = DateTimeConverters.toJson(SystemTime.asInstant(), de.metas.common.util.time.SystemTime.zoneId());
	}

	private JSONDocumentChangedWebSocketEvent(@NonNull final JSONDocumentChangedWebSocketEvent from)
	{
		windowId = from.windowId;
		id = from.id;
		timestamp = from.timestamp;

		stale = from.stale;

		if (from.includedTabsInfoByTabId != null)
		{
			includedTabsInfoByTabId = new HashMap<>();
			from.includedTabsInfoByTabId.forEach((key, tabInfo) -> includedTabsInfoByTabId.put(key, tabInfo.copy()));
		}

		activeTabStaled = from.activeTabStaled;
	}

	public JSONDocumentChangedWebSocketEvent copy()
	{
		return new JSONDocumentChangedWebSocketEvent(this);
	}

	JSONDocumentChangedWebSocketEvent markRootDocumentAsStaled()
	{
		stale = Boolean.TRUE;
		return this;
	}

	JSONDocumentChangedWebSocketEvent markActiveTabStaled()
	{
		activeTabStaled = Boolean.TRUE;
		return this;
	}

	private HashMap<String, JSONIncludedTabInfo> getIncludedTabsInfo()
	{
		if (includedTabsInfoByTabId == null)
		{
			includedTabsInfoByTabId = new HashMap<>();
		}
		return includedTabsInfoByTabId;
	}

	private JSONIncludedTabInfo getIncludedTabInfo(final DetailId tabId)
	{
		return getIncludedTabsInfo().computeIfAbsent(tabId.toJson(), k -> JSONIncludedTabInfo.newInstance(tabId));
	}

	void addIncludedTabInfo(@NonNull final JSONIncludedTabInfo tabInfo)
	{
		getIncludedTabsInfo().compute(tabInfo.getTabId().toJson(), (tabId, existingTabInfo) -> {
			if (existingTabInfo == null)
			{
				return tabInfo.copy();
			}
			else
			{
				existingTabInfo.mergeFrom(tabInfo);
				return existingTabInfo;
			}
		});
	}

	@Override
	@JsonIgnore
	public WebsocketTopicName getWebsocketEndpoint()
	{
		return WebsocketTopicNames.buildDocumentTopicName(windowId, id);
	}

	public void staleTabs(@NonNull final Collection<DetailId> tabIds)
	{
		tabIds.stream().map(this::getIncludedTabInfo).forEach(JSONIncludedTabInfo::markAllRowsStaled);
	}

	public void staleIncludedRows(@NonNull final DetailId tabId, @NonNull final DocumentIdsSelection rowIds)
	{
		getIncludedTabInfo(tabId).staleRows(rowIds);
	}

	void mergeFrom(@NonNull final JSONDocumentChangedWebSocketEvent from)
	{
		if (!Objects.equals(windowId, from.windowId)
				|| !Objects.equals(id, from.id))
		{
			throw new AdempiereException("Cannot merge events because they are not matching")
					.setParameter("from", from)
					.setParameter("to", this)
					.appendParametersToMessage();
		}

		if (from.stale != null && from.stale)
		{
			stale = from.stale;
		}

		from.getIncludedTabsInfo().values().forEach(this::addIncludedTabInfo);

		if (from.activeTabStaled != null && from.activeTabStaled)
		{
			activeTabStaled = from.activeTabStaled;
		}
	}
}
