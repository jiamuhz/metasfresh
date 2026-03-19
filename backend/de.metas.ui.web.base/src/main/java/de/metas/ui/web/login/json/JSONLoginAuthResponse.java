package de.metas.ui.web.login.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.collect.ImmutableList;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;
import java.util.List;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
@Builder
@Jacksonized
public class JSONLoginAuthResponse
{
	private static final JSONLoginAuthResponse REQUIRES_2FA = builder().requires2FA(true).roles(ImmutableList.of()).loginComplete(false).build();

	boolean requires2FA;
	List<JSONLoginRole> roles;
	boolean loginComplete;

	public static JSONLoginAuthResponse requires2FA() {return REQUIRES_2FA;}

	public static JSONLoginAuthResponse of(@NonNull final Collection<JSONLoginRole> roles)
	{
		Check.assumeNotEmpty(roles, "roles is not empty");
		return builder().roles(ImmutableList.copyOf(roles)).loginComplete(false).build();
	}

	public static JSONLoginAuthResponse loginComplete(@NonNull final JSONLoginRole role)
	{
		return builder().roles(ImmutableList.of(role)).loginComplete(true).build();
	}
}
