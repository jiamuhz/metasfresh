package de.metas.ui.web.board.json.events;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@EqualsAndHashCode(callSuper = true)
public class JSONBoardLaneChangedEvent extends JSONBoardChangedEvent
{
	public static final JSONBoardLaneChangedEvent of(final int boardId, final int laneId, final List<Integer> cardIds)
	{
		return new JSONBoardLaneChangedEvent(boardId, laneId, cardIds);
	}

	private final int laneId;
	private final List<Integer> cardIds;

	@Builder
	private JSONBoardLaneChangedEvent(final int boardId, final int laneId, @NonNull final List<Integer> cardIds)
	{
		super(ChangeType.laneCardsChanged, boardId);
		this.laneId = laneId;
		this.cardIds = cardIds;
	}
}
