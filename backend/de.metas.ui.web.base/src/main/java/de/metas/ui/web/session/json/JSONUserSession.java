package de.metas.ui.web.session.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import de.metas.contracts.ConditionsId;
import de.metas.i18n.Language;
import de.metas.letter.BoilerPlateId;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Map;



@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONUserSession
{
	@JsonProperty("loggedIn")
	private final boolean loggedIn;

	/**
	 * login user
	 */
	@JsonProperty("username")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String username;

	/**
	 * user's full name/display name
	 */
	@JsonProperty("fullname")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String fullname;

	@JsonProperty("email")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String email;

	@JsonProperty("avatarId")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String avatarId;

	@JsonProperty("rolename")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String rolename;

	@JsonProperty("language")
	private final JSONLookupValue language;
	@JsonProperty("locale")
	private final JSONUserSessionLocale locale;

	@JsonProperty("timeZone")
	private final String timeZone;

	@JsonProperty("defaultFlatrateConditionsId")
	private final ConditionsId defaultFlatrateConditionsId;

	@JsonProperty("defaultBoilerPlateId")
	private final BoilerPlateId defaultBoilerPlateId;

	@JsonProperty("websocketEndpoint")
	private final String websocketEndpoint;

	@JsonProperty("userProfileWindowId")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final WindowId userProfileWindowId;
	@JsonProperty("userProfileId")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final Integer userProfileId;

	@JsonProperty("settings")
	private final Map<String, String> settings;

	public JSONUserSession(
			@NonNull final UserSession userSession,
			@Nullable final Map<String, String> settings)
	{
		loggedIn = userSession.isLoggedIn();
		if (loggedIn)
		{
			username = userSession.getUserName();
			rolename = userSession.getRoleName();

			fullname = userSession.getUserFullname();
			email = userSession.getUserEmail();
			avatarId = userSession.getAvatarId();

			defaultFlatrateConditionsId = userSession.getDefaultFlatrateConditionsId();
			defaultBoilerPlateId = userSession.getDefaultBoilerPlateId();

			userProfileWindowId = WindowConstants.WINDOWID_UserProfile;
			userProfileId = userSession.getLoggedUserId().getRepoId();

			websocketEndpoint = userSession.getWebsocketEndpoint().getAsString();
		}
		else
		{
			username = null;
			rolename = null;
			fullname = null;
			email = null;
			avatarId = null;

			defaultFlatrateConditionsId = null;
			defaultBoilerPlateId = null;

			userProfileWindowId = null;
			userProfileId = null;
			websocketEndpoint = null;
		}

		final Language language = userSession.getLanguage();
		this.language = JSONLookupValue.of(language.getAD_Language(), language.getName());
		this.locale = JSONUserSessionLocale.of(userSession.getUserSessionLocale());

		timeZone = DateTimeConverters.toJson(userSession.getTimeZone());

		this.settings = settings != null ? settings : ImmutableMap.of();
	}
}
