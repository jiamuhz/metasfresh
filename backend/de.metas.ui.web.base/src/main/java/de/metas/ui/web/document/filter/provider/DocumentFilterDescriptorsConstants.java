

package de.metas.ui.web.document.filter.provider;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DocumentFilterDescriptorsConstants
{
	public final int SORT_NO_DEFAULT_DATE = Integer.MIN_VALUE;
	public final int SORT_NO_DEFAULT_FILTERS_GROUP = 10000;
	public final int SORT_NO_INLINE_FILTERS = 11000;
	public final int SORT_NO_USER_QUERY_START = 20000;
	public final int SORT_NO_FULL_TEXT_SEARCH = 30000;
	public final int SORT_NO_GEO_LOCATION = 40000;
	public final int SORT_NO_FACT_ACCT = 50000;

	public final int SORT_NO_FACETS_START = Integer.MAX_VALUE / 10000 * 10000;
}
