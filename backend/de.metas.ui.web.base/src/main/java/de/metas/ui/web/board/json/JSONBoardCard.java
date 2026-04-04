package de.metas.ui.web.board.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableList;

import de.metas.ui.web.board.BoardCard;
import de.metas.ui.web.view.json.JSONViewRowBase;
import de.metas.ui.web.window.datatypes.json.JSONDocumentPath;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@Value
public class JSONBoardCard implements JSONViewRowBase
{
	public static JSONBoardCard of(final BoardCard card, final String adLanguage)
	{
		final JSONBoardCardBuilder jsonCard = JSONBoardCard.builder()
				.cardId(card.getCardId())
				.laneId(card.getLaneId())
				//
				.caption(card.getCaption().translate(adLanguage))
				.description(card.getDescription().translate(adLanguage))
				.documentPath(JSONDocumentPath.ofWindowDocumentPath(card.getDocumentPath()));

		// Users
		card.getUsers()
				.stream()
				.map(JSONBoardCardUser::of)
				.forEach(jsonCard::user);

		return jsonCard.build();
	}

	private final int cardId;
	private final int laneId;

	private final String caption;
	private final String description;

	private final JSONDocumentPath documentPath;

	@Singular
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private ImmutableList<JSONBoardCardUser> users;
}
