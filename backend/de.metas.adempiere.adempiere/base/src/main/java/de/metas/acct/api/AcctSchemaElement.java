package de.metas.acct.api;

import de.metas.acct.api.impl.AcctSchemaElementId;
import de.metas.organization.OrgId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;

/** */

@Builder
@Getter
@ToString
public class AcctSchemaElement
{
	@NonNull final AcctSchemaId acctSchemaId;
	@NonNull final AcctSchemaElementType elementType;
	@NonNull final String name;
	final int seqNo;
	final int defaultValue;
	final @NonNull OrgId OrgId;
	final @NonNull String displayColumnName;
	final boolean mandatory;
	final boolean displayedInEditor;
	final boolean balanced;

	@Nullable
	@Setter
	AcctSchemaElementId id;
	@Setter
	@Nullable ChartOfAccountsId chartOfAccountsId;

	public String getColumnName()
	{
		return getElementType().getColumnName();
	}
}
