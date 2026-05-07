package de.metas.error;

import javax.annotation.Nullable;

import org.compiere.model.X_AD_Issue;

import de.metas.util.lang.ReferenceListAwareEnum;
import de.metas.util.lang.ReferenceListAwareEnums;
import de.metas.util.lang.ReferenceListAwareEnums.ValuesIndex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public enum IssueCategory implements ReferenceListAwareEnum
{
	ACCOUNTING(X_AD_Issue.ISSUECATEGORY_Accounting), //
	OTHER(X_AD_Issue.ISSUECATEGORY_Other) //
	;

	public static final int AD_REFERENCE_ID = X_AD_Issue.ISSUECATEGORY_AD_Reference_ID;

	@NonNull
	@Getter
	private final String code;

	private static final ValuesIndex<IssueCategory> index = ReferenceListAwareEnums.index(values());

	public static IssueCategory ofCode(@NonNull final String code)
	{
		return index.ofCode(code);
	}

	public static IssueCategory ofNullableCodeOrOther(@Nullable final String code)
	{
		final IssueCategory type = index.ofNullableCode(code);
		return type != null ? type : OTHER;
	}
}
