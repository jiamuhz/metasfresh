package de.metas.ui.web.handlingunits.trace;

import de.metas.handlingunits.trace.HUTraceEventQuery;
import de.metas.handlingunits.trace.HUTraceRepository;
import de.metas.process.PInstanceId;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.sql.FilterSql;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.NonNull;



final class HUTraceResultExtender implements SqlDocumentFilterConverter
{
	private static final String WHERE_IN_T_SELECTION = "(M_HU_Trace_ID IN (select T_Selection_ID from T_Selection where AD_PInstance_ID=?))";

	public static HUTraceResultExtender createForRepositoryAndconverter(
			@NonNull final HUTraceRepository huTraceRepository,
			@NonNull final SqlDocumentFilterConverter converter)
	{
		return new HUTraceResultExtender(huTraceRepository, converter);
	}

	private final HUTraceRepository huTraceRepository;
	private final SqlDocumentFilterConverter converter;

	private HUTraceResultExtender(
			@NonNull final HUTraceRepository huTraceRepository,
			@NonNull final SqlDocumentFilterConverter converter)
	{
		this.huTraceRepository = huTraceRepository;
		this.converter = converter;
	}

	@Override
	public boolean canConvert(final String filterId)
	{
		return true;
	}

	@Override
	public FilterSql getSql(
			@NonNull final DocumentFilter filter,
			@NonNull final SqlOptions sqlOpts,
			@NonNull final SqlDocumentFilterConverterContext context)
	{
		if (!filter.hasParameters())
		{
			return converter.getSql(filter, sqlOpts, context); // do whatever the system usually does
		}
		else
		{
			final HUTraceEventQuery huTraceQuery = HuTraceQueryCreator.createTraceQueryFromDocumentFilter(filter);
			final PInstanceId selectionId = huTraceRepository.queryToSelection(huTraceQuery);

			return FilterSql.ofWhereClause(WHERE_IN_T_SELECTION, selectionId);
		}
	}
}
