package de.metas.ui.web.board;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;



@Builder
@Value
public class BoardCardChangeRequest
{
	@Default
	private final int newLaneId = -1;
	@Default
	private final int newPosition = -1;
}
