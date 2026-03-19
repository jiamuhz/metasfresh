package de.metas.ui.web.login.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;


@Value
public class JSONLoginRole
{
	@JsonCreator
	public static JSONLoginRole of(
			@JsonProperty("caption") final String caption,
			@JsonProperty("roleId") final int roleId,
			@JsonProperty("tenantId") final int tenantId,
			@JsonProperty("orgId") final int orgId
	)
	{
		return builder()
				.caption(caption)
				.roleId(roleId)
				.tenantId(tenantId)
				.orgId(orgId)
				.build();
	}

	@JsonProperty("key") String key;
	@JsonProperty("caption") String caption;
	@JsonProperty("roleId") int roleId;
	@JsonProperty("tenantId") int tenantId;
	@JsonProperty("orgId") int orgId;

	@Builder
	private JSONLoginRole(
			final String caption,
			final int roleId,
			final int tenantId,
			final int orgId)
	{
		this.caption = caption;
		this.roleId = roleId;
		this.tenantId = tenantId;
		this.orgId = orgId;
		this.key = roleId + "_" + tenantId + "_" + orgId;
	}
}
