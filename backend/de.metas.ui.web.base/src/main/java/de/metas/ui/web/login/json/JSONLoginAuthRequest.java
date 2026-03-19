package de.metas.ui.web.login.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.util.hash.HashableString;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import javax.annotation.Nullable;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@ToString(exclude = "password")
public class JSONLoginAuthRequest
{
	public enum Type { password, token }

	@JsonProperty("type")
	Type type;

	@JsonProperty("username")
	String username;

	@JsonProperty("password")
	String password;

	@JsonProperty("token")
	String token;

	@JsonCreator
	@Builder
	private JSONLoginAuthRequest(
			@JsonProperty("type") @Nullable final Type type,
			@JsonProperty("username") @Nullable final String username,
			@JsonProperty("password") @Nullable final String password,
			@JsonProperty("token") final String token)
	{
		// Tolerate null/empty values; we will validate them later and we will throw nice error messages
		// Check.assumeNotEmpty(username, "username is not empty");
		// Check.assumeNotEmpty(password, "password is not empty");

		this.type = type != null ? type : Type.password;
		this.username = username;
		this.password = password;
		this.token = token;
	}

	public HashableString getPasswordAsEncryptableString()
	{
		return HashableString.ofPlainValue(password);
	}
}
