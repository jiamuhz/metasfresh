 

package de.metas.ui.web.edi_desadv;

import de.metas.edi.api.EDIDocOutBoundLogService;
import de.metas.edi.api.EDIExportStatus;
import de.metas.esb.edi.model.I_EDI_Desadv;
import de.metas.invoice.InvoiceId;
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
import lombok.NonNull;
import org.compiere.SpringContextHolder;

import javax.annotation.Nullable;

public class ChangeEDI_ExportStatus_C_Invoice_SingleView
		extends JavaProcess
		implements IProcessPrecondition, IProcessDefaultParametersProvider
{
	private final EDIDocOutBoundLogService ediDocOutBoundLogService = SpringContextHolder.instance.getBean(EDIDocOutBoundLogService.class);

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

		final de.metas.edi.model.I_C_Invoice invoice = ediDocOutBoundLogService.retreiveById(InvoiceId.ofRepoId(context.getSingleSelectedRecordId()));

		final EDIExportStatus fromExportStatus = EDIExportStatus.ofNullableCode(invoice.getEDI_ExportStatus());

		if (fromExportStatus == null)
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("Selected record is not an EDI Invoice: " + invoice);
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
		final de.metas.edi.model.I_C_Invoice invoice = ediDocOutBoundLogService.retreiveById(InvoiceId.ofRepoId(getRecord_ID()));

		final EDIExportStatus fromExportStatus = EDIExportStatus.ofCode(invoice.getEDI_ExportStatus());

		return ChangeEDI_ExportStatusHelper.computeTargetExportStatusLookupValues(fromExportStatus);
	}

	@Override
	@Nullable
	public Object getParameterDefaultValue(final IProcessDefaultParameter parameter)
	{
		final de.metas.edi.model.I_C_Invoice invoice = ediDocOutBoundLogService.retreiveById(InvoiceId.ofRepoId(getRecord_ID()));

		final EDIExportStatus fromExportStatus = EDIExportStatus.ofCode(invoice.getEDI_ExportStatus());

		return ChangeEDI_ExportStatusHelper.computeParameterDefaultValue(fromExportStatus);
	}

	@Override
	protected String doIt() throws Exception
	{
		final InvoiceId invoiceId = InvoiceId.ofRepoId(getRecord_ID());
		final EDIExportStatus targetExportStatus = EDIExportStatus.ofCode(p_TargetExportStatus);

		ChangeEDI_ExportStatusHelper.C_InvoiceDoIt(invoiceId, targetExportStatus);

		return MSG_OK;
	}

}
