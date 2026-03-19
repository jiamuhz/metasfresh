package de.metas.ui.web.handlingunits.process;

import com.google.common.collect.ImmutableList;
import de.metas.handlingunits.inout.returns.ReturnsServiceFacade;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.process.IProcessPrecondition;
import de.metas.process.ProcessPreconditionsResolution;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.handlingunits.HUEditorRowFilter.Select;
import de.metas.ui.web.handlingunits.WEBUI_HU_Constants;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.SpringContextHolder;
import org.compiere.util.Env;

import java.sql.Timestamp;
import java.util.List;



/**
 * Return the selected HUs back to vendor.
 *
 * 
 * Initial task https://github.com/metasfresh/metasfresh-webui-api/issues/396
 */
public class WEBUI_M_HU_ReturnToVendor extends HUEditorProcessTemplate implements IProcessPrecondition
{
	private final ReturnsServiceFacade returnsServiceFacade = SpringContextHolder.instance.getBean(ReturnsServiceFacade.class);

	private List<I_M_HU> husToReturn = null;

	@Override
	protected ProcessPreconditionsResolution checkPreconditionsApplicable()
	{
		if(!isHUEditorView())
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
	protected String doIt() throws Exception
	{
		husToReturn = streamSelectedHUs(Select.ONLY_TOPLEVEL).collect(ImmutableList.toImmutableList());
		if (husToReturn.isEmpty())
		{
			throw new AdempiereException("@NoSelection@");
		}

		final Timestamp movementDate = Env.getDate(getCtx());
		returnsServiceFacade.createVendorReturnInOutForHUs(husToReturn, movementDate);
		return MSG_OK;
	}

	@Override
	protected void postProcess(final boolean success)
	{
		if (husToReturn != null && !husToReturn.isEmpty())
		{
			getView().removeHUsAndInvalidate(husToReturn);
		}
	}

}
