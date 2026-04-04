 

package de.metas.ui.web.edi_desadv;

import de.metas.edi.api.EDIDesadvId;
import de.metas.edi.api.EDIExportStatus;
import de.metas.edi.api.IDesadvDAO;
import de.metas.esb.edi.model.I_EDI_Desadv;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.util.Services;
import lombok.NonNull;

import javax.annotation.Nullable;

public class ChangeEDI_ExportStatus_EDI_Desadv_SingleView
		extends JavaProcess
		implements IProcessPrecondition, IProcessDefaultParametersProvider
{
	private final IDesadvDAO desadvDAO = Services.get(IDesadvDAO.class);

	protected static final String PARAM_TargetExportStatus = I_EDI_Desadv.COLUMNNAME_EDI_ExportStatus;
	@Param(parameterName = PARAM_TargetExportStatus)
	private String p_TargetExportStatus;

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final @NonNull IProcessPreconditionsContext context)
	{
		if (context.isNoSelection())
		{
			return ProcessPreconditionsResolution.rejectBecauseNoSelection();
		}

		if (!context.isSingleSelection())
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
		}

		final I_EDI_Desadv desadv = desadvDAO.retrieveById(EDIDesadvId.ofRepoId(context.getSingleSelectedRecordId()));

		final EDIExportStatus fromExportStatus = EDIExportStatus.ofNullableCode(desadv.getEDI_ExportStatus());

		if (fromExportStatus == null)
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("Cannot change ExportStatus from the current one for: " + desadv);
		}

		if (ChangeEDI_ExportStatusHelper.getAvailableTargetExportStatuses(fromExportStatus).isEmpty())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("Cannot change ExportStatus from the current one: " + fromExportStatus);
		}

		return ProcessPreconditionsResolution.accept();
	}

	@ProcessParamLookupValuesProvider(parameterName = PARAM_TargetExportStatus, numericKey = false, lookupSource = LookupSource.list)
	private LookupValuesList getTargetExportStatusLookupValues(final LookupDataSourceContext context)
	{
		final I_EDI_Desadv desadv = desadvDAO.retrieveById(EDIDesadvId.ofRepoId(getRecord_ID()));

		final EDIExportStatus fromExportStatus = EDIExportStatus.ofCode(desadv.getEDI_ExportStatus());

		return ChangeEDI_ExportStatusHelper.computeTargetExportStatusLookupValues(fromExportStatus);
	}

	@Override
	@Nullable
	public Object getParameterDefaultValue(final IProcessDefaultParameter parameter)
	{
		final I_EDI_Desadv desadv = desadvDAO.retrieveById(EDIDesadvId.ofRepoId(getRecord_ID()));

		final EDIExportStatus fromExportStatus = EDIExportStatus.ofCode(desadv.getEDI_ExportStatus());

		return ChangeEDI_ExportStatusHelper.computeParameterDefaultValue(fromExportStatus);
	}

	@Override
	protected String doIt() throws Exception
	{
		final EDIExportStatus targetExportStatus = EDIExportStatus.ofCode(p_TargetExportStatus);
		final boolean isProcessed = ChangeEDI_ExportStatusHelper.computeIsProcessedByTargetExportStatus(targetExportStatus);
		ChangeEDI_ExportStatusHelper.EDI_DesadvDoIt(EDIDesadvId.ofRepoId(getRecord_ID()),
													targetExportStatus,
													isProcessed);

		return MSG_OK;
	}

}
