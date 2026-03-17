package de.metas.bpartner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Splitter;
import de.metas.user.UserId;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

/** */

@Value
public class BPartnerContactId implements RepoIdAware
{
	@NonNull
	BPartnerId bpartnerId;
	@NonNull
	UserId userId;

	public static BPartnerContactId ofRepoId(@NonNull final BPartnerId bpartnerId, final int contactRepoId)
	{
		final UserId userId = toValidContactUserIdOrNull(contactRepoId);
		if (userId == null)
		{
			throw new AdempiereException("@Invalid@ @Contact_ID@")
					.appendParametersToMessage()
					.setParameter("bpartnerId", bpartnerId)
					.setParameter("contactRepoId", contactRepoId);
		}

		return of(bpartnerId, userId);
	}

	public static BPartnerContactId of(@NonNull final BPartnerId bpartnerId, @NonNull final UserId userId)
	{
		return new BPartnerContactId(bpartnerId, userId);
	}

	public static BPartnerContactId ofRepoId(final int bpartnerRepoId, final int contactRepoId)
	{
		final BPartnerId bpartnerId = BPartnerId.ofRepoId(bpartnerRepoId);

		final UserId userId = toValidContactUserIdOrNull(contactRepoId);
		if (userId == null)
		{
			throw new AdempiereException("@Invalid@ @Contact_ID@")
					.appendParametersToMessage()
					.setParameter("bpartnerId", bpartnerId)
					.setParameter("contactRepoId", contactRepoId);
		}

		return of(bpartnerId, userId);
	}

	@Nullable
	public static BPartnerContactId ofRepoIdOrNull(
			@Nullable final BPartnerId bpartnerId,
			@Nullable final Integer contactRepoId)
	{
		if(bpartnerId == null)
		{
			return null;
		}

		final UserId userId = toValidContactUserIdOrNull(contactRepoId);
		return userId != null ? of(bpartnerId, userId) : null;
	}

	@Nullable
	public static BPartnerContactId ofRepoIdOrNull(
			@Nullable final Integer bpartnerRepoId,
			@Nullable final Integer contactRepoId)
	{
		final BPartnerId bpartnerId = BPartnerId.ofRepoIdOrNull(bpartnerRepoId);
		if (bpartnerId == null)
		{
			return null;
		}

		final UserId userId = toValidContactUserIdOrNull(contactRepoId);
		if (userId == null)
		{
			return null;
		}

		return of(bpartnerId, userId);
	}

	@Nullable
	private static UserId toValidContactUserIdOrNull(@Nullable final Integer userRepoId)
	{
		final UserId userId = userRepoId != null ? UserId.ofRepoIdOrNull(userRepoId) : null;

		// NOTE: system user is not a valid BP contact
		return userId != null && userId.isRegularUser() ? userId : null;
	}

	private BPartnerContactId(@NonNull final BPartnerId bpartnerId, @NonNull final UserId userId)
	{
		this.bpartnerId = bpartnerId;
		this.userId = userId;
	}

	@Override
	public int getRepoId()
	{
		return userId.getRepoId();
	}

	public static int toRepoId(@Nullable final BPartnerContactId id)
	{
		return id != null ? id.getRepoId() : -1;
	}

	@Nullable
	public static UserId toUserIdOrNull(@Nullable final BPartnerContactId id)
	{
		return id != null ? id.getUserId() : null;
	}

	public static boolean equals(@Nullable final BPartnerContactId id1, @Nullable final BPartnerContactId id2)
	{
		return Objects.equals(id1, id2);
	}

	@JsonCreator
	public static BPartnerContactId ofJsonString(@NonNull final String idStr)
	{
		try
		{
			final List<String> parts = Splitter.on("-").splitToList(idStr);
			return of(
					BPartnerId.ofRepoId(Integer.parseInt(parts.get(0))),
					UserId.ofRepoId(Integer.parseInt(parts.get(1))));
		}
		catch (Exception ex)
		{
			throw new AdempiereException("Invalid BPartnerContactId string: " + idStr, ex);
		}
	}

	@JsonValue
	public String toJson()
	{
		return bpartnerId.getRepoId() + "-" + userId.getRepoId();
	}
}
