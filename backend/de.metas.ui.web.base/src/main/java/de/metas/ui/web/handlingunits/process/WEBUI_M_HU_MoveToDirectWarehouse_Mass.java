package de.metas.ui.web.handlingunits.process;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Iterator;

import org.adempiere.ad.dao.impl.TypedSqlQueryFilter;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.util.api.IRangeAwareParams;
import org.adempiere.warehouse.WarehouseId;
import org.compiere.model.IQuery;
import org.springframework.beans.factory.annotation.Autowired;

import de.metas.handlingunits.IHUQueryBuilder;
import de.metas.handlingunits.IHandlingUnitsDAO;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.process.RunOutOfTrx;
import de.metas.ui.web.handlingunits.HUEditorProcessTemplate;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.Check;
import de.metas.util.Services;

 

/**
 * HU Editor: Move selected HUs to direct warehouse (aka Materialentnahme)
 *
 *
 *
 */
public class WEBUI_M_HU_MoveToDirectWarehouse_Mass extends HUEditorProcessTemplate
{
	// services
	private final transient IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);
	@Autowired
	private DocumentCollection documentsCollection;

	// parameters
	private int p_M_Warehouse_ID = -1; // the source warehouse
	private String p_huWhereClause = null;
	private Instant p_MovementDate = null;
	private String p_Description = null;

	@Override
	protected void prepare()
	{
		final IRangeAwareParams parameterAsIParams = getParameterAsIParams();
		p_M_Warehouse_ID = parameterAsIParams.getParameterAsInt("M_Warehouse_ID", -1);
		p_huWhereClause = parameterAsIParams.getParameterAsString("WhereClause");
		p_MovementDate = parameterAsIParams.getParameterAsInstant("MovementDate");
		p_Description = parameterAsIParams.getParameterAsString("Description");
	}

	@Override
	@RunOutOfTrx
	protected final String doIt()
	{
		HUMoveToDirectWarehouseService.newInstance()
				.setDocumentsCollection(documentsCollection)
				.setHUView(getView())
				.setMovementDate(p_MovementDate)
				.setDescription(p_Description)
				.setFailOnFirstError(false)
				.setLoggable(this)
				.move(retrieveHUs());

		return MSG_OK;
	}

	/**
	 * @return HUs that will be moved
	 */
	protected Iterator<I_M_HU> retrieveHUs()
	{
		final IHUQueryBuilder huQueryBuilder = handlingUnitsDAO.createHUQueryBuilder()
				.setContext(getCtx(), ITrx.TRXNAME_None);

		// Only top level HUs
		huQueryBuilder.setOnlyTopLevelHUs();

		// Only Active HUs
		huQueryBuilder.addHUStatusToInclude(X_M_HU.HUSTATUS_Active);

		// Only for preselected warehouse
		if (p_M_Warehouse_ID > 0)
		{
			huQueryBuilder.addOnlyInWarehouseId(WarehouseId.ofRepoId(p_M_Warehouse_ID));
		}

		// Only for given SQL where clause
		if (!Check.isEmpty(p_huWhereClause, true))
		{
			huQueryBuilder.addFilter(TypedSqlQueryFilter.of(p_huWhereClause));
		}

		// Fetch the HUs iterator
		return huQueryBuilder
				.createQuery()
				.setOption(IQuery.OPTION_GuaranteedIteratorRequired, true) // because we might change the hu's locator
				.setOption(IQuery.OPTION_IteratorBufferSize, 1000)
				.iterate(I_M_HU.class);
	}
}
