 

package de.metas.ui.web.edi_desadv;

import com.google.common.collect.ImmutableSet;
import de.metas.edi.api.EDIDocOutBoundLogService;
import de.metas.edi.api.EDIExportStatus;
import de.metas.esb.edi.model.I_EDI_Desadv;
import de.metas.invoice.InvoiceId;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.IProcessPrecondition;
import de.metas.process.Param;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import org.compiere.SpringContextHolder;

import javax.annotation.Nullable;

public class ChangeEDI_ExportStatus_C_Invoice_GridView
		extends ViewBasedProcessTemplate
		implements IProcessPrecondition, IProcessDefaultParametersProvider
{
	private final EDIDocOutBoundLogService ediDocOutBoundLogService = SpringContextHolder.instance.getBean(EDIDocOutBoundLogService.class);

	protected static final String PARAM_TargetExportStatus = I_EDI_Desadv.COLUMNNAME_EDI_ExportStatus;
	@Param(parameterName = PARAM_TargetExportStatus)
	private String p_TargetExportStatus;

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		final ImmutableSet<InvoiceId> invoiceIds = getSelectedInvoiceIds();
		if (invoiceIds.isEmpty())
		{
			return ProcessPreconditionsResolution.rejectBecauseNoSelection();
		}

		// technical detail: all records must have the same Export Status
		EDIExportStatus sameExportStatus = null;

		for (final InvoiceId invoiceId : invoiceIds)
		{
			final de.metas.edi.model.I_C_Invoice invoice = ediDocOutBoundLogService.retreiveById(invoiceId);

			final EDIExportStatus fromExportStatus = EDIExportStatus.ofNullableCode(invoice.getEDI_ExportStatus());

			if (fromExportStatus == null)
			{
				return ProcessPreconditionsResolution.rejectWithInternalReason("Selected record is not an EDI Invoice: " + invoice);
			}

			if (ChangeEDI_ExportStatusHelper.getAvailableTargetExportStatuses(fromExportStatus).isEmpty())
			{
				return ProcessPreconditionsResolution.rejectWithInternalReason("Cannot change ExportStatus from the current one: " + fromExportStatus);
			}

			if (sameExportStatus == null)
			{
				sameExportStatus = fromExportStatus;
			}

			if (!sameExportStatus.equals(fromExportStatus))
			{
				return ProcessPreconditionsResolution.rejectWithInternalReason("All records must have the same EDI ExportStatus");
			}
		}

		return ProcessPreconditionsResolution.accept();
	}

	@ProcessParamLookupValuesProvider(parameterName = PARAM_TargetExportStatus, numericKey = false, lookupSource = LookupSource.list)
	private LookupValuesList getTargetExportStatusLookupValues(final LookupDataSourceContext context)
	{
		final de.metas.edi.model.I_C_Invoice invoice = ediDocOutBoundLogService.retreiveById(getSelectedInvoiceIds().iterator().next());

		final EDIExportStatus fromExportStatus = EDIExportStatus.ofCode(invoice.getEDI_ExportStatus());

		return ChangeEDI_ExportStatusHelper.computeTargetExportStatusLookupValues(fromExportStatus);
	}

	@Override
	@Nullable
	public Object getParameterDefaultValue(final IProcessDefaultParameter parameter)
	{
		final de.metas.edi.model.I_C_Invoice invoice = ediDocOutBoundLogService.retreiveById(getSelectedInvoiceIds().iterator().next());

		final EDIExportStatus fromExportStatus = EDIExportStatus.ofCode(invoice.getEDI_ExportStatus());

		return ChangeEDI_ExportStatusHelper.computeParameterDefaultValue(fromExportStatus);
	}

	@Override
	protected String doIt() throws Exception
	{
		final EDIExportStatus targetExportStatus = EDIExportStatus.ofCode(p_TargetExportStatus);

		for (final InvoiceId invoiceId : getSelectedInvoiceIds())
		{
			ChangeEDI_ExportStatusHelper.C_InvoiceDoIt(invoiceId, targetExportStatus);
		}

		return MSG_OK;
	}

	private ImmutableSet<InvoiceId> getSelectedInvoiceIds()
	{
		final DocumentIdsSelection selectedRowIds = getSelectedRowIds();
		return selectedRowIds.toIds(InvoiceId::ofRepoId);
	}
}
