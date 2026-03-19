package de.metas.ui.web.view;

import java.time.ZoneId;
import java.util.Optional;
import java.util.Properties;

import de.metas.organization.OrgId;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatees;

import de.metas.security.UserRolePermissionsKey;
import de.metas.security.impl.AccessSqlStringExpression;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.user.UserId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

 

@ToString
public final class ViewEvaluationCtx
{
	public static ViewEvaluationCtx newInstanceFromCurrentContext()
	{
		final Properties ctx = Env.getCtx();

		return _builder()
				.loggedUserId(Env.getLoggedUserIdIfExists(ctx))
				.adLanguage(Env.getAD_Language(ctx))
				.timeZone(UserSession.getTimeZoneOrSystemDefault())
				.permissionsKey(UserRolePermissionsKey.fromContext(ctx))
				.orgId(Env.getOrgId(ctx))
				.build();
	}

	@Getter
	private final Optional<UserId> loggedUserId;
	@Getter
	private final OrgId orgId;
	@Getter
	private final String adLanguage;
	@Getter
	private final ZoneId timeZone;
	@Getter
	private final UserRolePermissionsKey permissionsKey;

	private transient Evaluatee _evaluatee; // lazy
	private transient JSONOptions _jsonOptions; // lazy

	@Builder(builderMethodName = "_builder")
	private ViewEvaluationCtx(
			@NonNull final Optional<UserId> loggedUserId,
			@NonNull final OrgId orgId,
			@NonNull final String adLanguage,
			@NonNull final ZoneId timeZone,
			@NonNull final UserRolePermissionsKey permissionsKey)
	{
		this.loggedUserId = loggedUserId;
		this.orgId = orgId;
		this.adLanguage = adLanguage;
		this.timeZone = timeZone;
		this.permissionsKey = permissionsKey;
	}

	public Evaluatee toEvaluatee()
	{
		Evaluatee evaluatee = _evaluatee;
		if (evaluatee == null)
		{
			evaluatee = _evaluatee = createEvaluatee();
		}
		return evaluatee;
	}

	private Evaluatee createEvaluatee()
	{
		return Evaluatees.mapBuilder()
				.put(Env.CTXNAME_AD_User_ID, loggedUserId.map(UserId::getRepoId).orElse(-1))
				.put(Env.CTXNAME_AD_Org_ID, OrgId.toRepoIdOrAny(orgId))
				.put(Env.CTXNAME_AD_Language, adLanguage)
				.put(AccessSqlStringExpression.PARAM_UserRolePermissionsKey.getName(), permissionsKey.toPermissionsKeyString())
				.build();
	}

	public JSONOptions toJSONOptions()
	{
		JSONOptions jsonOptions = this._jsonOptions;
		if (jsonOptions == null)
		{
			jsonOptions = _jsonOptions = createJSONOptions();
		}
		return jsonOptions;
	}

	private JSONOptions createJSONOptions()
	{
		return JSONOptions.builder()
				.adLanguage(adLanguage)
				.zoneId(timeZone)
				.build();
	}

}
