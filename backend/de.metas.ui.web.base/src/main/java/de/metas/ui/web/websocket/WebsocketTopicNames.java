package de.metas.ui.web.websocket;

import com.google.common.base.Preconditions;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.user.UserId;
import de.metas.util.Check;
import de.metas.websocket.WebsocketTopicName;
import lombok.NonNull;
import lombok.experimental.UtilityClass;



@UtilityClass
public class WebsocketTopicNames
{
	static final String TOPIC_UserSession = "/userSession";
	static final String TOPIC_Notifications = "/notifications";
	static final String TOPIC_View = "/view";
	static final String TOPIC_Document = "/document";
	static final String TOPIC_Board = "/board";
	public static final String TOPIC_Dashboard = "/dashboard";

	public static WebsocketTopicName buildUserSessionTopicName(@NonNull final UserId adUserId)
	{
		return WebsocketTopicName.ofString(TOPIC_UserSession + "/" + adUserId.getRepoId());
	}

	public static WebsocketTopicName buildNotificationsTopicName(@NonNull final UserId adUserId)
	{
		return WebsocketTopicName.ofString(TOPIC_Notifications + "/" + adUserId.getRepoId());
	}

	public static WebsocketTopicName buildViewNotificationsTopicName(@NonNull final String viewId)
	{
		Check.assumeNotEmpty(viewId, "viewId is not empty");
		return WebsocketTopicName.ofString(TOPIC_View + "/" + viewId);
	}

	public static WebsocketTopicName buildDocumentTopicName(
			@NonNull final WindowDocumentTypeId windowId,
			@NonNull final DocumentId documentId)
	{
		return WebsocketTopicName.ofString(TOPIC_Document + "/" + windowId.toJson() + "/" + documentId.toJson());
	}

	public static WebsocketTopicName buildBoardTopicName(final int boardId)
	{
		Preconditions.checkArgument(boardId > 0);
		return WebsocketTopicName.ofString(TOPIC_Board + "/" + boardId);
	}
}
