package de.metas.ui.web.board.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@Value
public class JSONBoard
{
	private final int boardId;
	private final String caption;
	@Singular
	private final List<JSONBoardLane> lanes;
	
	private final String websocketEndpoint;
}
