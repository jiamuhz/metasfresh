package de.metas.ui.web.dashboard.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.metas.JsonObjectMapperHolder;
import de.metas.Profiles;
import de.metas.elasticsearch.IESSystem;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.kpi.data.KPIDataContext;
import de.metas.ui.web.kpi.data.KPIDataProvider;
import de.metas.ui.web.kpi.data.KPIDataRequest;
import de.metas.ui.web.kpi.data.KPIDataResult;
import de.metas.ui.web.kpi.descriptor.KPI;
import de.metas.ui.web.kpi.descriptor.KPIId;
import de.metas.ui.web.kpi.descriptor.KPIRepository;
import de.metas.util.Services;
import org.adempiere.service.ISysConfigBL;
import org.compiere.SpringContextHolder;
import org.springframework.context.annotation.Profile;

import java.time.Instant;



@Profile(Profiles.PROFILE_Webui)
public class WEBUI_KPI_TestQuery extends JavaProcess implements IProcessPrecondition
{
	private final IESSystem esSystem = Services.get(IESSystem.class);
	private final ISysConfigBL sysConfigBL = Services.get(ISysConfigBL.class);
	private final KPIRepository kpisRepo = SpringContextHolder.instance.getBean(KPIRepository.class);
	private final ObjectMapper jsonObjectMapper = JsonObjectMapperHolder.sharedJsonObjectMapper();

	@Param(parameterName = "DateFrom")
	private Instant p_DateFrom;
	@Param(parameterName = "DateTo")
	private Instant p_DateTo;

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final IProcessPreconditionsContext context)
	{
		if (!context.isSingleSelection())
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
		}
		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt() throws JsonProcessingException
	{
		final KPIId kpiId = KPIId.ofRepoId(getRecord_ID());
		kpisRepo.invalidateCache();

		final KPI kpi = kpisRepo.getKPI(kpiId);

		final KPIDataResult kpiData = KPIDataProvider.builder()
				.kpiRepository(kpisRepo)
				.esSystem(esSystem)
				.sysConfigBL(sysConfigBL)
				.build()
				.getKPIData(KPIDataRequest.builder()
						.kpiId(kpiId)
						.timeRangeDefaults(kpi.getTimeRangeDefaults())
						.context(KPIDataContext.ofEnvProperties(getCtx())
								.toBuilder()
								.from(p_DateFrom)
								.to(p_DateTo)
								.build())
						.build());

		final String jsonData = jsonObjectMapper.writeValueAsString(kpiData);
		log.info("jsonData:\n {}", jsonData);

		return jsonData;
	}
}
