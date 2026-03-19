package de.metas.ui.web.board.json.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;



@AllArgsConstructor
@EqualsAndHashCode
public abstract class JSONBoardChangedEvent
{
	public static enum ChangeType
	{
		laneCardsChanged, cardChanged,
	}

	@NonNull
	private final ChangeType changeType;
	private final int boardId;
}
