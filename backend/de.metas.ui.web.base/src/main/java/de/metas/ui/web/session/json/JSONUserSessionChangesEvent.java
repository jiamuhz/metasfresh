package de.metas.ui.web.session.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.common.util.time.SystemTime;
import de.metas.contracts.ConditionsId;
import de.metas.letter.BoilerPlateId;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import lombok.Builder;
import lombok.ToString;

 

/**
 * User session changed event to be sent on websocket.
 * 
 *
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@ToString
public class JSONUserSessionChangesEvent
{
	/** user's full name/display name */
	@JsonProperty("fullname")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String fullname;

	@JsonProperty("email")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String email;

	@JsonProperty("avatarId")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	// IMPORTANT: empty avatarId is perfectly valid and means the avatar was removed 
	private final String avatarId;

	@JsonProperty("language")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final JSONLookupValue language;

	@JsonProperty("timestamp")
	private final String timestamp = DateTimeConverters.toJson(SystemTime.asInstant(), de.metas.common.util.time.SystemTime.zoneId());

	@JsonProperty("defaultBoilerPlateId")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final BoilerPlateId defaultBoilerPlateId;

	@JsonProperty("defaultFlatrateConditionsId")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final ConditionsId defaultFlatrateConditionsId;



	public boolean isEmpty()
	{
		return fullname == null
				&& email == null
				&& avatarId == null
				&& language == null
				&& defaultBoilerPlateId == null
				&& defaultFlatrateConditionsId == null;
	}
}
