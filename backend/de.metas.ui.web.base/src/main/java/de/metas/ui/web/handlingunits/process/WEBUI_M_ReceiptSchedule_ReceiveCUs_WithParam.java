package de.metas.ui.web.handlingunits.process;

import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.process.IProcessDefaultParameter;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.Param;
import de.metas.quantity.Quantity;
import de.metas.quantity.Quantitys;
import de.metas.uom.UomId;
import org.adempiere.exceptions.FillMandatoryException;

import java.math.BigDecimal;
import java.util.stream.Stream;

 

public class WEBUI_M_ReceiptSchedule_ReceiveCUs_WithParam extends WEBUI_M_ReceiptSchedule_ReceiveCUs implements IProcessDefaultParametersProvider
{
	private static final String PARAM_QtyCU = "QtyCU";
	@Param(parameterName = PARAM_QtyCU, mandatory = true)
	private BigDecimal p_QtyCU;

	public WEBUI_M_ReceiptSchedule_ReceiveCUs_WithParam()
	{
		// configure defaults
		setDisallowMultipleReceiptsSchedules();
		setAllowNoQuantityAvailable();
	}

	@Override
	public Object getParameterDefaultValue(final IProcessDefaultParameter parameter)
	{
		if (PARAM_QtyCU.equals(parameter.getColumnName()))
		{
			final I_M_ReceiptSchedule receiptSchedule = getM_ReceiptSchedule();
			return getDefaultAvailableQtyToReceive(receiptSchedule);
		}
		else
		{
			return DEFAULT_VALUE_NOTAVAILABLE;
		}
	}

	@Override
	protected Stream<I_M_ReceiptSchedule> streamReceiptSchedulesToReceive()
	{
		return Stream.of(getM_ReceiptSchedule());
	}

	private I_M_ReceiptSchedule getM_ReceiptSchedule()
	{
		return getRecord(I_M_ReceiptSchedule.class);
	}

	@Override
	protected Quantity getEffectiveQtyToReceive(final I_M_ReceiptSchedule rs)
	{
		if (p_QtyCU == null || p_QtyCU.signum() <= 0)
		{
			throw new FillMandatoryException(PARAM_QtyCU);
		}

		final UomId uomId = UomId.ofRepoId(rs.getC_UOM_ID());
		return Quantitys.create(p_QtyCU, uomId);
	}
}
