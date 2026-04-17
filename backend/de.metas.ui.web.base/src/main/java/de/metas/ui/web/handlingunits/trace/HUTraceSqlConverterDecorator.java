package de.metas.ui.web.handlingunits.trace;

import org.springframework.stereotype.Component;

import de.metas.handlingunits.trace.HUTraceRepository;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterDecorator;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import lombok.NonNull;

 

/**
 * Shall add to the "normal" result all connected HU-trace records
 */
@Component
public class HUTraceSqlConverterDecorator implements SqlDocumentFilterConverterDecorator
{
	private final HUTraceRepository huTraceRepository;

	public HUTraceSqlConverterDecorator(final HUTraceRepository huTRaceRepository)
	{
		this.huTraceRepository = huTRaceRepository;
	}

	@Override
	public WindowDocumentTypeId getWindowId()
	{
		return WEBUI_HU_Constants.WEBUI_HU_Trace_Window_ID;
	}

	public SqlDocumentFilterConverter decorate(@NonNull final SqlDocumentFilterConverter converter)
	{
		return HUTraceResultExtender.createForRepositoryAndconverter(huTraceRepository, converter);
	}
}
