package de.metas.security.permissions.record_access;

import javax.annotation.Nullable;

import org.adempiere.util.lang.impl.TableRecordReference;

import de.metas.user.UserId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class RecordAccessCopyRequest
{
	@NonNull
	TableRecordReference target;

	@Nullable
	TableRecordReference grantFrom;

	@Nullable
	TableRecordReference revokeFrom;

	@NonNull
	PermissionIssuer issuer;

	@NonNull
	UserId requestedBy;
}
