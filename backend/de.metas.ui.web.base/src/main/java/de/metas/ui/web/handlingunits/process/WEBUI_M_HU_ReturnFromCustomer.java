package de.metas.ui.web.handlingunits.process;

import java.util.List;

import de.metas.handlingunits.inout.returns.ReturnsServiceFacade;
import org.adempiere.exceptions.AdempiereException;

import com.google.common.collect.ImmutableList;

import de.metas.handlingunits.inout.IHUInOutBL;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import de.metas.util.Services;
import org.compiere.SpringContextHolder;

 

/**
 * Return the selected HUs back to customer.
 *
 *
 * @task initial task https://github.com/metasfresh/metasfresh/issues/1306
 */
public class WEBUI_M_HU_ReturnFromCustomer extends HUEditorProcessTemplate implements IProcessPrecondition
{
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

		returnsServiceFacade.createCustomerReturnInOutForHUs(husToReturn);
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
