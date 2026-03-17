package de.metas.document;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;
import org.compiere.util.Env;

import javax.annotation.Nullable;

/** */

@Value
@Builder
public class DocTypeQuery
{
	public static final String DOCSUBTYPE_Any = "DOCSUBTYPE_Any";
	public static final String DOCSUBTYPE_NONE = null;

	@NonNull
	DocBaseType docBaseType;

	@Nullable
	@Default
	String docSubType = DOCSUBTYPE_Any;

	@NonNull
	Integer adClientId;

	/**
	 * Even if specified, the system will still try to fallback to {@code AD_Org_ID=0} if there is no doctype with a matching org-id.
	 */
	@Default
	int adOrgId = Env.CTXVALUE_AD_Org_ID_System;

	@Nullable
	Boolean isSOTrx;

	@Nullable
	Boolean defaultDocType;

	@Nullable
	String name;

	//
	//
	//

	public static class DocTypeQueryBuilder
	{
		public DocTypeQueryBuilder docSubTypeAny()
		{
			return docSubType(DOCSUBTYPE_Any);
		}

		public DocTypeQueryBuilder docSubTypeNone()
		{
			return docSubType(DOCSUBTYPE_NONE);
		}
	}

}
