package de.metas.ui.web.view;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.NonNull;
import lombok.Value;



@Value
public final class ViewProfileId
{
	public static final ViewProfileId NULL = null;

	public static boolean isNull(final ViewProfileId profileId)
	{
		return profileId == null || Objects.equals(profileId, NULL);
	}

	@JsonCreator
	public static final ViewProfileId fromJson(final String profileIdStr)
	{
		if (profileIdStr == null)
		{
			return NULL;
		}

		final String profileIdStrNorm = profileIdStr.trim();
		if (profileIdStrNorm.isEmpty())
		{
			return NULL;
		}

		return new ViewProfileId(profileIdStrNorm);
	}

	private final String id;

	private ViewProfileId(@NonNull final String id)
	{
		this.id = id;
	}

	@JsonValue
	public String toJson()
	{
		return id;
	}
}
