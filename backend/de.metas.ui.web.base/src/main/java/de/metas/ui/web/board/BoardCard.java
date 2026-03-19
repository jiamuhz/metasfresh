package de.metas.ui.web.board;

import com.google.common.collect.ImmutableList;

import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.window.datatypes.DocumentPath;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

 

@Builder
@Value
public class BoardCard
{
	private final int cardId;
	private final int laneId;
	
	@NonNull
	private final ITranslatableString caption;
	@NonNull
	private final ITranslatableString description;

	@NonNull
	private final DocumentPath documentPath;

	@Singular
	private ImmutableList<BoardCardUser> users;
}
