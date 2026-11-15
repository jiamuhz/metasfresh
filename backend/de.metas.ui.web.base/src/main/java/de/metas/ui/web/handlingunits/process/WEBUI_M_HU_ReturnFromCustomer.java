package de.metas.ui.web.handlingunits.process;

import java.util.List;

import de.metas.handlingunits.inout.returns.ReturnsServiceFacade;
import de.metas.handlingunits.model.I_M_InOut;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.i18n.AdMessageKey;
import org.adempiere.exceptions.AdempiereException;

import com.google.common.collect.ImmutableList;

import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import org.compiere.SpringContextHolder;

 

/**
 * Return the selected HUs back to customer.
 *
 *
 * @task initial task https://github.com/metasfresh/metasfresh/issues/1306
 */
public class WEBUI_M_HU_ReturnFromCustomer extends HUEditorProcessTemplate implements IProcessPrecondition
{
	private static final AdMessageKey MSG_ONLY_SHIPPED_HU = AdMessageKey.of("WEBUI_Only_ShippedHU");

	private final ReturnsServiceFacade returnsServiceFacade = SpringContextHolder.instance.getBean(ReturnsServiceFacade.class);

	private List<I_M_HU> husMoved = null;

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if (!isHUEditorView())
		{
			return ProcessPreconditionsResolution.rejectWithInternalReason("not the HU view");
		}

		if (!streamSelectedHUIds(Select.ONLY_TOPLEVEL).findAny().isPresent())
		{
			return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(WEBUI_HU_Constants.MSG_WEBUI_ONLY_TOP_LEVEL_HU));
		}

		//
		// Only HUs which were already shipped to the customer can be returned.
		// HUs which are still on stock (active/picked etc) must not be "returned from customer":
		// doing so would either silently create nothing or, even worse, create a customer return for stock HUs.
		final boolean hasHUWhichIsNotShipped = streamSelectedRows(HUEditorRowFilter.select(Select.ONLY_TOPLEVEL))
				.anyMatch(row -> !X_M_HU.HUSTATUS_Shipped.equals(row.getHUStatus()));
		if (hasHUWhichIsNotShipped)
		{
			return ProcessPreconditionsResolution.reject(msgBL.getTranslatableMsgText(MSG_ONLY_SHIPPED_HU));
		}

		return ProcessPreconditionsResolution.accept();
	}

	@Override
	protected String doIt()
	{
		final ImmutableList<I_M_HU> husToReturn = streamSelectedHUs(Select.ONLY_TOPLEVEL).collect(ImmutableList.toImmutableList());
		if (husToReturn.isEmpty())
		{
			throw new AdempiereException("@NoSelection@");
		}

		final List<I_M_InOut> customerReturns = returnsServiceFacade.createCustomerReturnInOutForHUs(husToReturn);
		if (customerReturns.isEmpty())
		{
			// shall not happen; guarded by the producer as well.
			// We don't want to report success and remove the HUs from the view if nothing was returned.
			throw new AdempiereException(msgBL.getTranslatableMsgText(MSG_ONLY_SHIPPED_HU));
		}

		husMoved = husToReturn;

		return MSG_OK;
	}

	@Override
	protected void postProcess(final boolean success)
	{
		if (husMoved != null && !husMoved.isEmpty())
		{
			getView().removeHUsAndInvalidate(husMoved);
		}
	}
}
