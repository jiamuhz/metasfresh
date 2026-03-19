package de.metas.ui.web.handlingunits.process;

import de.metas.handlingunits.HuId;
import de.metas.handlingunits.IHandlingUnitsBL;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.QtyTU;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.qrcodes.model.HUQRCode;
import de.metas.handlingunits.qrcodes.service.HUQRCodesService;
import de.metas.process.IProcessPrecondition;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.JavaProcess;
import de.metas.process.Param;
import de.metas.process.ParamBarcodeScannerType;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.process.adprocess.WebuiProcess;
import de.metas.ui.web.window.datatypes.PanelLayoutType;
import de.metas.util.Services;
import lombok.NonNull;
import org.compiere.SpringContextHolder;



/**
 * It takes selected HU, checks if is one piece and then assigns scanned QR Code to it.
 *
 */
@WebuiProcess(layoutType = PanelLayoutType.SingleOverlayField)
public class WEBUI_M_HU_Assign_QRCode extends JavaProcess implements IProcessPrecondition
{
	private final IHandlingUnitsBL handlingUnitsBL = Services.get(IHandlingUnitsBL.class);
	private final IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);
	private final HUQRCodesService huQRCodesService = SpringContextHolder.instance.getBean(HUQRCodesService.class);

	@Param(parameterName = "Barcode", mandatory = true, barcodeScannerType = ParamBarcodeScannerType.QRCode)
	private String p_Barcode;

	@Override
	public ProcessPreconditionsResolution checkPreconditionsApplicable(final @NonNull IProcessPreconditionsContext context)
	{
		if (context.isMoreThanOneSelected())
		{
			return ProcessPreconditionsResolution.rejectBecauseNotSingleSelection();
		}

		final int huId = context.getSingleSelectedRecordId();
		final I_M_HU hu = handlingUnitsDAO.getById(HuId.ofRepoId(huId));
		final QtyTU qtyTU = handlingUnitsBL.getTUsCount(hu);

		if (handlingUnitsBL.isAggregateHU(hu) && qtyTU.toInt() > 1)
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("HU is aggregated and Qty is bigger then 1. Cannot assign QR code to it.");
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		final HUQRCode huQRCode = HUQRCode.fromGlobalQRCodeJsonString(p_Barcode);

		huQRCodesService.assign(huQRCode, HuId.ofRepoId(getRecord_ID()));

		return MSG_OK;
	}

}