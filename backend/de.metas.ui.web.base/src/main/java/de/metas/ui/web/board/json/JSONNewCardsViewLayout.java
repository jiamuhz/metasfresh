package de.metas.ui.web.board.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import de.metas.ui.web.document.filter.json.JSONDocumentFilterDescriptor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@Value
public class JSONNewCardsViewLayout
{
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String caption;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String description;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String emptyResultText;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String emptyResultHint;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Singular
	private final List<JSONDocumentFilterDescriptor> filters;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Singular
	private final List<JSONBoardCardOrderBy> orderBys;
}
