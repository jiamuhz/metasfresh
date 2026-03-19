package de.metas.ui.web.board;

import java.util.Collection;

import org.adempiere.exceptions.AdempiereException;

import com.google.common.collect.ImmutableMap;

import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.websocket.WebsocketTopicName;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;



@Builder
@Value
public final class BoardDescriptor
{
	private final int boardId;
	@NonNull
	private final ITranslatableString caption;
	@NonNull
	private final WebsocketTopicName websocketEndpoint;

	@Singular
	private final ImmutableMap<Integer, BoardLaneDescriptor> lanes;

	@Singular("cardFieldByFieldName")
	final ImmutableMap<String, BoardCardFieldDescriptor> cardFieldsByFieldName;

	// Source document info
	@NonNull
	private final WindowId documentWindowId;
	@NonNull
	private LookupDescriptorProvider documentLookupDescriptorProvider;

	/** document sticky filters (those will be applied no matter what; can come from WEBUI_Dashboard.AD_Val_Rule_ID for example) */
	@Default
	private DocumentFilterList documentFilters = DocumentFilterList.EMPTY;

	// Source record mapping
	@NonNull
	private final String tableName;
	@NonNull
	private final String tableAlias;
	@NonNull
	private final String keyColumnName;
	@NonNull
	private final String userIdColumnName;

	public void assertLaneIdExists(final int laneId)
	{
		if (lanes.get(laneId) == null)
		{
			throw new AdempiereException("Lane ID=" + laneId + " found for board ID=" + getBoardId())
					.setParameter("board", this)
					.setParameter("laneId", laneId);
		}
	}

	public Collection<BoardCardFieldDescriptor> getCardFields()
	{
		return cardFieldsByFieldName.values();
	}

	public BoardCardFieldDescriptor getCardFieldByName(final String fieldName)
	{
		final BoardCardFieldDescriptor cardField = cardFieldsByFieldName.get(fieldName);
		if (cardField == null)
		{
			throw new AdempiereException("No card field found for " + fieldName)
					.setParameter("board", this);
		}
		return cardField;
	}

	public LookupDescriptor getLookupDescriptor()
	{
		return getDocumentLookupDescriptorProvider()
				.provide()
				.get();
	}
}
