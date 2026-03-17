package de.metas.document.engine;

import com.google.common.collect.ImmutableSet;
import de.metas.document.DocTypeId;
import de.metas.lang.SOTrx;
import de.metas.security.UserRolePermissionsKey;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.ad.validationRule.IValidationContext;
import org.adempiere.service.ClientId;

import javax.annotation.Nullable;

/** */

@Data
@Builder
public final class DocActionOptionsContext
{
	@NonNull
	private final UserRolePermissionsKey userRolePermissionsKey;

	@NonNull
	private final String tableName;

	@NonNull
	private final String docStatus;

	// NOTE: we are tolerating null/not set C_DocType_ID because not all of our documents have this column.
	@Nullable
	private final DocTypeId docTypeId;

	private final boolean processing;
	private final String orderType;

	@NonNull
	private final SOTrx soTrx;

	private String docActionToUse;

	@NonNull
	@Default
	private ImmutableSet<String> docActions = ImmutableSet.of();

	@Getter(AccessLevel.NONE)
	@NonNull
	private final IValidationContext validationContext;

	public ClientId getAdClientId()
	{
		return getUserRolePermissionsKey().getClientId();
	}

	public String getParameterValue(final String parameterName)
	{
		return validationContext.get_ValueAsString(parameterName);
	}
}
