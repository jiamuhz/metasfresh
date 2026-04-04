package org.compiere.model;

import com.google.common.collect.ImmutableList;
import de.metas.acct.api.IFactAcctDAO;
import de.metas.bpartner.BPartnerContactId;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.BPartnerLocationAndCaptureId;
import de.metas.bpartner.exceptions.BPartnerNoBillToAddressException;
import de.metas.bpartner.exceptions.BPartnerNoShipToAddressException;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.bpartner.service.IBPartnerDAO.BPartnerLocationQuery;
import de.metas.bpartner.service.IBPartnerDAO.BPartnerLocationQuery.Type;
import de.metas.common.util.CoalesceUtil;
import de.metas.common.util.time.SystemTime;
import de.metas.currency.CurrencyPrecision;
import de.metas.document.DocBaseType;
import de.metas.document.DocTypeId;
import de.metas.document.IDocTypeBL;
import de.metas.document.IDocTypeDAO;
import de.metas.document.engine.DocStatus;
import de.metas.document.engine.IDocument;
import de.metas.document.engine.IDocumentBL;
import de.metas.document.location.DocumentLocation;
import de.metas.document.sequence.IDocumentNoBL;
import de.metas.document.sequence.IDocumentNoBuilder;
import de.metas.document.sequence.IDocumentNoBuilderFactory;
import de.metas.freighcost.FreightCostRule;
import de.metas.i18n.IMsgBL;
import de.metas.order.*;
import de.metas.order.location.adapter.OrderDocumentLocationAdapterFactory;
import de.metas.order.payment_reservation.OrderPaymentReservationCreateResult;
import de.metas.order.payment_reservation.OrderPaymentReservationService;
import de.metas.organization.IOrgDAO;
import de.metas.organization.InstantAndOrgId;
import de.metas.organization.OrgId;
import de.metas.payment.PaymentRule;
import de.metas.payment.paymentterm.IPaymentTermRepository;
import de.metas.payment.paymentterm.PaymentTermId;
import de.metas.payment.paymentterm.impl.PaymentTermQuery;
import de.metas.pricing.PriceListId;
import de.metas.pricing.service.IPriceListDAO;
import de.metas.product.IProductBL;
import de.metas.product.IProductDAO;
import de.metas.product.IStorageBL;
import de.metas.product.ProductId;
import de.metas.report.DocumentReportService;
import de.metas.report.ReportResultData;
import de.metas.report.StandardDocumentReportType;
import de.metas.tax.api.CalculateTaxResult;
import de.metas.tax.api.Tax;
import de.metas.tax.api.TaxUtils;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.LegacyAdapters;
import org.adempiere.warehouse.WarehouseId;
import org.adempiere.warehouse.api.IWarehouseBL;
import org.adempiere.warehouse.api.IWarehouseDAO;
import org.adempiere.warehouse.spi.IWarehouseAdvisor;
import org.compiere.SpringContextHolder;
import org.compiere.util.DB;
import org.compiere.util.Env;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Pattern;

/**
 * MyDoc Model.
 * Please do not set DocStatus and C_DocType_ID directly.
 * They are set in the process() method.
 * Use DocAction and C_DocTypeTarget_ID instead.
 *
 * @author majianbo
 */
public class MMyDoc extends X_C_MyDoc implements IDocument
{
	private static final long serialVersionUID = -1575204995898726572L;

	private static final String NO_DELIVARABLE_LINES_FOUND = "NoDeliverableLinesFound";

	private final IWarehouseAdvisor warehouseAdvisor = Services.get(IWarehouseAdvisor.class);
	private final transient IOrderBL orderBL = Services.get(IOrderBL.class);
	private final IBPartnerDAO bPartnerDAO = Services.get(IBPartnerDAO.class);

	/**************************************************************************
	 * Default Constructor
	 *
	 * @param ctx context
	 * @param C_MyDoc_ID order to load, (0 create new order)
	 * @param trxName trx name
	 */
	public MMyDoc(final Properties ctx, final int C_MyDoc_ID, final String trxName)
	{
		super(ctx, C_MyDoc_ID, trxName);
		// New
		if (is_new())
		{
			setDocStatus(DocStatus.Drafted.getCode());
			setDocAction(DOCACTION_Prepare);

			setDocumentNo( "6767" );

			//
			//setDeliveryRule(DeliveryRule.AVAILABILITY.getCode());
			//setFreightCostRule(FreightCostRule.FreightIncluded.getCode());

			//
			setIsSelected(false);
			setIsSOTrx(true);
			//
			setIsApproved(false);
			//
			super.setProcessed(false);
			setProcessing(false);
		}
	}    // MMyDoc

	/**
	 * Load Constructor
	 *
	 * @param ctx     context
	 * @param rs      result set record
	 * @param trxName transaction
	 */
	public MMyDoc(final Properties ctx, final ResultSet rs, final String trxName)
	{
		super(ctx, rs, trxName);
	}    // MOrder

	/**
	 * Order Lines
	 */
	private ImmutableList<MOrderLine> _lines = null;
	/**
	 * Tax Lines
	 */
	private MOrderTax[] m_taxes = null;

	/**
	 * Overwrite Client/Org if required
	 *
	 * @param AD_Client_ID client
	 * @param AD_Org_ID    org
	 */
	@Override
	public void setClientOrg(final int AD_Client_ID, final int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}    // setClientOrg

	/**
	 * Add to Description
	 *
	 * @param description text
	 */
	public void addDescription(final String description)
	{
		final String desc = getDescription();
		if (desc == null)
		{
			setDescription(description);
		}
		else
		{
			setDescription(desc + " | " + description);
		}
	}    // addDescription


	/**
	 * Set Warehouse
	 *
	 * @param M_Warehouse_ID warehouse
	 */
	@Override
	public void setM_Warehouse_ID(final int M_Warehouse_ID)
	{
		super.setM_Warehouse_ID(M_Warehouse_ID);
	}    // setM_Warehouse_ID


	/*************************************************************************/

	/**
	 * Sales Order Sub Type - SO
	 */
	public static final String DocSubType_Standard = "SO";
	/**
	 * Sales Order Sub Type - OB
	 */
	public static final String DocSubType_Quotation = "OB";
	/**
	 * Sales Order Sub Type - ON
	 */
	public static final String DocSubType_Proposal = "ON";
	/**
	 * Sales Order Sub Type - PR
	 */
	public static final String DocSubType_Prepay = "PR";
	/**
	 * Sales Order Sub Type - WR
	 */
	public static final String DocSubType_POS = "WR";
	/**
	 * Sales Order Sub Type - WP
	 */
	public static final String DocSubType_Warehouse = "WP";
	/**
	 * Sales Order Sub Type - WI
	 */
	public static final String DocSubType_OnCredit = "WI";
	/**
	 * Sales Order Sub Type - RM
	 */
	public static final String DocSubType_RMA = "RM";

	
	/**************************************************************************
	 * String Representation
	 *
	 * @return info
	 */
	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder("C_MyDoc[ID=").append(get_ID())
				.append("-DocumentNo=").append(getDocumentNo())
				.append(",IsSOTrx=").append(isSOTrx())
				.append(",C_DocType_ID=").append(getC_DocType_ID())
				.append("]");
		return sb.toString();
	}    // toString

	/**
	 * Get Document Info
	 *
	 * @return document info (untranslated)
	 */
	@Override
	public String getDocumentInfo()
	{
		final StringBuilder documentInfo = new StringBuilder();

		//
		// DocType
		DocTypeId docTypeId = DocTypeId.ofRepoIdOrNull(getC_DocType_ID());
		if (docTypeId == null)
		{
			docTypeId = DocTypeId.ofRepoIdOrNull(getC_DocTypeTarget_ID());
		}
		if (docTypeId != null)
		{
			final I_C_DocType docType = Services.get(IDocTypeDAO.class).getById(docTypeId);
			documentInfo.append(docType.getName());
		}

		//
		// DocumentNo
		if (documentInfo.length() > 0)
		{
			documentInfo.append(" ");
		}
		documentInfo.append(getDocumentNo());

		return documentInfo.toString();
	}    // getDocumentInfo

	@Override
	public File createPDF()
	{
		final DocumentReportService documentReportService = SpringContextHolder.instance.getBean(DocumentReportService.class);
		final ReportResultData report = documentReportService.createStandardDocumentReportData(getCtx(), StandardDocumentReportType.ORDER, getc_mydoc_ID());
		return report.writeToTemporaryFile(get_TableName() + get_ID());
	}    // getPDF

	/**************************************************************************
	 * Get <b>active</b> Lines of Order
	 *
	 * @param orderClause order clause
	 * @return lines
	 */
	private ImmutableList<MOrderLine> retrieveLines(final String orderClause)
	{
		// red1 - using new Query class from Teo / Victor's MDDOrder.java implementation
		final StringBuilder whereClauseFinal = new StringBuilder("(" + MOrderLine.COLUMNNAME_C_Order_ID + "=? AND " + MOrderLine.COLUMNNAME_IsActive + "='Y' )");

		final String orderBy = orderClause.length() == 0 ? MOrderLine.COLUMNNAME_Line : orderClause;

		//
		return new Query(getCtx(), MOrderLine.Table_Name, whereClauseFinal.toString(), get_TrxName())
				.setParameters(get_ID())
				.setOrderBy(orderBy)
				.listImmutable(MOrderLine.class);
	}

	public void invalidateLines()
	{
		_lines = null;
	}

	/**
	 * Get <b>active</b> Lines of Order
	 *
	 * @param requery requery
	 * @param orderBy optional order by column
	 * @return lines
	 */
	private List<MOrderLine> getLines(final boolean requery, final String orderBy)
	{
		ImmutableList<MOrderLine> lines = _lines;
		if (lines != null && !requery)
		{
			InterfaceWrapperHelper.setThreadInheritedTrxName(lines);
			return lines;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
		{
			orderClause += orderBy;
		}
		else
		{
			orderClause += "Line";
		}
		lines = _lines = retrieveLines(orderClause);
		return lines;
	}    // getLines

	/**
	 * Get Lines of Order.
	 * (used by web store)
	 *
	 * @return lines
	 */
	public List<MOrderLine> getLines()
	{
		return getLines(false, null);
	}    // getLines

	private List<MOrderLine> getLinesRequeryOrderedByProduct()
	{
		return getLines(true, I_C_OrderLine.COLUMNNAME_M_Product_ID);
	}

	public List<MOrderLine> getLinesRequery()
	{
		return getLines(true, null);
	}

	/**
	 * Renumber Lines
	 *
	 * @param step start and step
	 */
	public void renumberLines(final int step)
	{
		int number = step;
		final List<MOrderLine> lines = getLinesRequery();    // Line is default
		for (final MOrderLine line : lines)
		{
			line.setLine(number);
			line.save(get_TrxName());
			number += step;
		}
		invalidateLines();
	}    // renumberLines

	/**
	 * Get Taxes of Order
	 *
	 * @param requery requery
	 * @return array of taxes
	 */
	public MOrderTax[] getTaxes(final boolean requery)
	{
		if (m_taxes != null && !requery)
		{
			return m_taxes;
		}
		//
		final List<MOrderTax> list = new Query(getCtx(), MOrderTax.Table_Name, "C_Order_ID=?", get_TrxName())
				.setParameters(get_ID())
				.list(MOrderTax.class);
		m_taxes = list.toArray(new MOrderTax[list.size()]);
		return m_taxes;
	}    // getTaxes

	/**
	 * Get Invoices of Order
	 *
	 * @return invoices
	 */
	public static MInvoice[] getInvoices(@NonNull final OrderId orderId)
	{
		final String whereClause = "EXISTS (SELECT 1 FROM C_InvoiceLine il, C_OrderLine ol"
				+ " WHERE il.C_Invoice_ID=C_Invoice.C_Invoice_ID"
				+ " AND il.C_OrderLine_ID=ol.C_OrderLine_ID"
				+ " AND ol.C_Order_ID=?)";
		final List<MInvoice> list = new Query(Env.getCtx(), MInvoice.Table_Name, whereClause, ITrx.TRXNAME_ThreadInherited)
				.setParameters(orderId)
				.setOrderBy("C_Invoice_ID DESC")
				.list(MInvoice.class);
		return list.toArray(new MInvoice[list.size()]);
	}    // getInvoices

	/**
	 * Get latest Invoice of Order
	 *
	 * @return invoice id or 0
	 */
	public int getC_Invoice_ID()
	{
		final String sql = "SELECT C_Invoice_ID FROM C_Invoice "
				+ "WHERE C_Order_ID=? AND DocStatus IN ('CO','CL') "
				+ "ORDER BY C_Invoice_ID DESC";
		final int C_Invoice_ID = DB.getSQLValue(get_TrxName(), sql, get_ID());
		return C_Invoice_ID;
	}    // getC_Invoice_ID

	/**
	 * Get Shipments of Order
	 *
	 * @return shipments
	 * @deprecated Please use
	 */
	@Deprecated
	public MInOut[] getShipments()
	{
		// 05768: moved to
		final List<I_M_InOut> inOuts = Services.get(IOrderDAO.class).retrieveInOutsForMatchingOrderLines(InterfaceWrapperHelper.create(this, I_C_Order.class));
		return LegacyAdapters.convertToPOArray(inOuts, MInOut.class);
	}    // getShipments

	/**
	 * Set Processed.
	 * Propagate to Lines/Taxes
	 *
	 * @param processed processed
	 */
	@Override
	public void setProcessed(final boolean processed)
	{
		super.setProcessed(processed);
		if (get_ID() == 0)
		{
			return;
		}
		final String set = "SET Processed='"
				+ (processed ? "Y" : "N")
				+ "' WHERE C_Order_ID=" + getc_mydoc_ID();
		final int noLine = DB.executeUpdateAndThrowExceptionOnFail("UPDATE C_OrderLine " + set, get_TrxName());
		final int noTax = DB.executeUpdateAndThrowExceptionOnFail("UPDATE C_OrderTax " + set, get_TrxName());
		invalidateLines();
		m_taxes = null;
		log.debug("setProcessed - " + processed + " - Lines=" + noLine + ", Tax=" + noTax);
	}    // setProcessed

	/**************************************************************************
	 * Before Save
	 *
	 * @param newRecord new
	 * @return save
	 */
	@Override
	protected boolean beforeSave(final boolean newRecord)
	{
		// Client/Org Check
		if (getAD_Org_ID() == 0)
		{
			final int context_AD_Org_ID = Env.getAD_Org_ID(getCtx());
			if (context_AD_Org_ID != 0)
			{
				setAD_Org_ID(context_AD_Org_ID);
				log.warn("Changed Org to Context=" + context_AD_Org_ID);
			}
		}
		if (getAD_Client_ID() == 0)
		{
			m_processMsg = "AD_Client_ID = 0";
			return false;
		}

		// New Record Doc Type - make sure DocType set to 0
		if (newRecord && getC_DocType_ID() == 0)
		{
			setC_DocType_ID(0);
		}

		// Warehouse Org
		if (newRecord
				|| is_ValueChanged("AD_Org_ID") || is_ValueChanged("M_Warehouse_ID"))
		{
			// metas: allow different warehouse-org
			// MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			// if (wh.getAD_Org_ID() != getAD_Org_ID())
			// log.saveWarning("WarehouseOrgConflict", "");
			//
		}
		// Reservations in Warehouse
		if (!newRecord && is_ValueChanged("M_Warehouse_ID"))
		{
			for (final MOrderLine line : getLinesRequery())
			{
				if (!line.canChangeWarehouse(true))
				{
					return false;
				}
			}
		}

		// Default Sales Rep
		// NOTE: we shall not set the SalesRep from context if is not set.
		// This is not a mandatory field, so leave it like it is.

		// Default Document Type
		if (getC_DocTypeTarget_ID() <= 0)
		{
			setIsSOTrx(true);
			//orderBL.setSODocTypeTargetId(this, DocSubType_Standard);
		}

		return true;
	}    // beforeSave

	/**
	 * After Save
	 *
	 * @param newRecord new
	 * @param success   success
	 * @return true if can be saved
	 */
	@Override
	protected boolean afterSave(final boolean newRecord, final boolean success)
	{
		if (!success || newRecord)
		{
			return success;
		}

		// Propagate Description changes
		if (is_ValueChanged("Description") || is_ValueChanged("POReference"))
		{
			final String sql = DB.convertSqlToNative("UPDATE C_Invoice i"
															 + " SET (Description,POReference)="
															 + "(SELECT Description,POReference "
															 + "FROM C_Order o WHERE i.C_Order_ID=o.C_Order_ID) "
															 + "WHERE DocStatus NOT IN ('RE','CL') AND C_Order_ID=" + getc_mydoc_ID());
			final int no = DB.executeUpdateAndThrowExceptionOnFail(sql, get_TrxName());
			log.debug("Description -> #" + no);
		}

		// Propagate Changes of Payment Info to existing (not reversed/closed) invoices
		if (is_ValueChanged("PaymentRule") || is_ValueChanged("C_PaymentTerm_ID")
				|| is_ValueChanged("DateAcct") || is_ValueChanged("C_Payment_ID")
				|| is_ValueChanged("C_CashLine_ID"))
		{
			final String sql = DB.convertSqlToNative("UPDATE C_Invoice i "
															 + "SET (PaymentRule,C_PaymentTerm_ID,DateAcct,C_Payment_ID,C_CashLine_ID)="
															 + "(SELECT PaymentRule,C_PaymentTerm_ID,DateAcct,C_Payment_ID,C_CashLine_ID "
															 + "FROM C_Order o WHERE i.C_Order_ID=o.C_Order_ID)"
															 + "WHERE DocStatus NOT IN ('RE','CL') AND C_Order_ID=" + getc_mydoc_ID());
			// Don't touch Closed/Reversed entries
			final int no = DB.executeUpdateAndSaveErrorOnFail(sql, get_TrxName());
			log.debug("Payment -> #" + no);
		}

		return true;
	}    // afterSave

	/**
	 * Before Delete
	 *
	 * @return true of it can be deleted
	 */
	@Override
	protected boolean beforeDelete()
	{
		if (isProcessed())
		{
			return false;
		}

		for (final MOrderLine line : getLinesRequery())
		{
			line.setC_Order_CompensationGroup_ID(-1);
			line.deleteEx(true);
		}

		Services.get(IQueryBL.class)
				.createQueryBuilder(I_C_Order_CompensationGroup.class)
				.addEqualsFilter(I_C_Order_CompensationGroup.COLUMN_C_Order_ID, getc_mydoc_ID())
				.create()
				.delete();

		return true;
	}    // beforeDelete

	@Override
	public boolean processIt(final String processAction)
	{
		m_processMsg = null;
		return Services.get(IDocumentBL.class).processIt(this, processAction); // task 09824
	}

	/**
	 * Process Message
	 */
	private String m_processMsg = null;
	/**
	 * Just Prepared Flag
	 */
	private boolean m_justPrepared = false;

	/**
	 * Unlock Document.
	 *
	 * @return true if success
	 */
	@Override
	public boolean unlockIt()
	{
		log.debug("unlockIt - {}", this);
		setProcessing(false);
		return true;
	}    // unlockIt

	/**
	 * Invalidate Document
	 *
	 * @return true if success
	 */
	@Override
	public boolean invalidateIt()
	{
		log.debug("{}", this);
		setDocAction(DOCACTION_Prepare);
		return true;
	}    // invalidateIt

	/**************************************************************************
	 * Prepare Document
	 *
	 * @return new status (In Progress or Invalid)
	 */
	@Override
	public String prepareIt()
	{
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
		{
			return IDocument.STATUS_Invalid;
		}

		final I_C_DocType dt = Services.get(IDocTypeDAO.class).getById(getC_DocTypeTarget_ID());


		// Lines
		final List<MOrderLine> lines = getLinesRequeryOrderedByProduct();
		if (lines.isEmpty())
		{
			m_processMsg = "@NoLines@";
			return IDocument.STATUS_Invalid;
		}
		
		// Convert DocType to Target
		if (getC_DocType_ID() != getC_DocTypeTarget_ID())
		{
			// Cannot change Std to anything else if different warehouses
			if (getC_DocType_ID() != 0)
			{
				final I_C_DocType dtOld = Services.get(IDocTypeDAO.class).getById(getC_DocType_ID());
				if (X_C_DocType.DOCSUBTYPE_StandardOrder.equals(dtOld.getDocSubType())        // From SO
						&& !X_C_DocType.DOCSUBTYPE_StandardOrder.equals(dt.getDocSubType()))    // To !SO
				{
					for (final MOrderLine line : lines)
					{
						if (line.getM_Warehouse_ID() != getM_Warehouse_ID())
						{
							log.warn("different Warehouse " + line);
							m_processMsg = "@CannotChangeDocType@";
							return IDocument.STATUS_Invalid;
						}
					}
				}
			}

			// New or in Progress/Invalid
			final DocStatus docStatus = DocStatus.ofCode(getDocStatus());
			if (docStatus.isDraftedInProgressOrInvalid()
					|| getC_DocType_ID() <= 0)
			{
				setC_DocType_ID(getC_DocTypeTarget_ID());
			}
			else
			// convert only if offer
			{
				if (Services.get(IDocTypeBL.class).isSalesProposalOrQuotation(dt))
				{
					setC_DocType_ID(getC_DocTypeTarget_ID());
				}
				else
				{
					m_processMsg = "@CannotChangeDocType@";
					return IDocument.STATUS_Invalid;
				}
			}
		}    // convert DocType

		// Mandatory Product Attribute Set Instance
		final String mandatoryType = "='Y'";    // IN ('Y','S')
		final String sql = "SELECT COUNT(*) "
				+ "FROM C_OrderLine ol"
				+ " INNER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN M_AttributeSet pas ON (p.M_AttributeSet_ID=pas.M_AttributeSet_ID) "
				+ "WHERE pas.MandatoryType" + mandatoryType
				+ " AND (ol.M_AttributeSetInstance_ID is NULL OR ol.M_AttributeSetInstance_ID = 0)"
				+ " AND ol.C_Order_ID=?";
		final int no = DB.getSQLValue(get_TrxName(), sql, getc_mydoc_ID());
		if (no != 0)
		{
			m_processMsg = "@LinesWithoutProductAttribute@ (" + no + ")";
			return IDocument.STATUS_Invalid;
		}

		// Lines
		// task 09030: we don't really want to explode the BOM, least of all this uncontrolled way.
		// if (explodeBOM())
		// lines = getLines(true, MOrderLine.COLUMNNAME_M_Product_ID); Note: if we don't explode, we don't need to reload the lines
		if (!reserveStock(dt, lines))
		{
			m_processMsg = "Cannot reserve Stock";
			return IDocument.STATUS_Invalid;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
		{
			return IDocument.STATUS_Invalid;
		}

		m_justPrepared = true;
		// if (!DOCACTION_Complete.equals(getDocAction())) don't set for just prepare
		// setDocAction(DOCACTION_Complete);
		return IDocument.STATUS_InProgress;
	}    // prepareIt

	/**
	 * Reserve Inventory.
	 * Counterpart: MInOut.completeIt()
	 *
	 * @param docType document type or null
	 * @param lines   order lines (ordered by M_Product_ID for deadlock prevention)
	 * @return true if (un) reserved
	 */
	// metas: make reserveStock visible from MOrderLine to allow un-reservation
	// of stocks before delete.
	public boolean reserveStock(final I_C_DocType docType, final List<MOrderLine> lines)
	{
		int docTypeId = getC_DocType_ID(); // in case of draft, doctype is 0
		if (docTypeId <= 0 )
		{
			// check DocTypeTarget
			docTypeId= getC_DocTypeTarget_ID();
		}

		final I_C_DocType dt = docType == null
				? Services.get(IDocTypeDAO.class).getById(docTypeId)
				: docType;

		// Binding
		boolean binding = dt != null && !Services.get(IDocTypeBL.class).isSalesProposal(dt);
		final String docSubType = dt == null ? null : dt.getDocSubType();

		// Not binding - i.e. Target=0
		if (DOCACTION_Void.equals(getDocAction())
				// Closing Binding Quotation
				|| (X_C_DocType.DOCSUBTYPE_Quotation.equals(docSubType)
				&& DOCACTION_Close.equals(getDocAction())))   // || isDropShip() )
		{

			binding = false;
		}
		final boolean isSOTrx = isSOTrx();

		log.debug("Binding=" + binding + " - IsSOTrx=" + isSOTrx);

		final WarehouseId dropShipWarehouseId = Services.get(IOrgDAO.class).getOrgDropshipWarehouseId(OrgId.ofRepoId(getAD_Org_ID()));

		// Force same WH for all but SO/PO
		WarehouseId headerWarehouseId = WarehouseId.ofRepoId(getM_Warehouse_ID());
		if (X_C_DocType.DOCSUBTYPE_StandardOrder.equals(docSubType)
				|| X_C_DocType.DOCBASETYPE_PurchaseOrder.equals(docSubType))
		{
			headerWarehouseId = null;        // don't enforce
		}

		BigDecimal Volume = BigDecimal.ZERO;
		BigDecimal Weight = BigDecimal.ZERO;

		final IWarehouseDAO warehousesRepo = Services.get(IWarehouseDAO.class);
		final IWarehouseBL warehouseBL = Services.get(IWarehouseBL.class);

		// Always check and (un) Reserve Inventory
		for (final MOrderLine line : lines)
		{
			final WarehouseId lineWarehouseIdAdviced = warehouseAdvisor.evaluateWarehouse(line);

			// Check/set WH/Org
			if (headerWarehouseId != null) // enforce WH
			{
				if (!Objects.equals(headerWarehouseId, lineWarehouseIdAdviced)
						&& !Objects.equals(lineWarehouseIdAdviced, dropShipWarehouseId))   // metas 01658 removing 'isDropShip' flag
				{
					line.setM_Warehouse_ID(headerWarehouseId.getRepoId());
				}
				if (getAD_Org_ID() != line.getAD_Org_ID())
				{
					line.setAD_Org_ID(getAD_Org_ID());
				}
			}

			// Binding
			final BigDecimal target = binding ? line.getQtyOrdered() : BigDecimal.ZERO;
			final BigDecimal difference = target
					.subtract(line.getQtyReserved())
					.subtract(line.getQtyDelivered());
			if (difference.signum() == 0 && !line.isDeliveryClosed())
			{
				final MProduct product = line.getProduct();
				if (product != null)
				{
					Volume = Volume.add(product.getVolume().multiply(line.getQtyOrdered()));
					Weight = Weight.add(product.getWeight().multiply(line.getQtyOrdered()));
				}
				continue;
			}

			log.debug("Line=" + line.getLine()
							  + " - Target=" + target + ",Difference=" + difference
							  + " - Ordered=" + line.getQtyOrdered()
							  + ",Reserved=" + line.getQtyReserved() + ",Delivered=" + line.getQtyDelivered());

			// Check Product - Stocked and Item
			final MProduct product = line.getProduct();
			if (product != null)
			{
				if (Services.get(IProductBL.class).isStocked(product))
				{
					final BigDecimal ordered = isSOTrx ? BigDecimal.ZERO : difference;
					final BigDecimal reserved = isSOTrx ? difference : BigDecimal.ZERO;
					final WarehouseId lineWarehouseId = line.getM_Warehouse_ID() > 0
							? WarehouseId.ofRepoId(line.getM_Warehouse_ID())
							: Services.get(IWarehouseAdvisor.class).evaluateWarehouse(line);
					int M_Locator_ID = 0;
					// Get Locator to reserve
					if (line.getM_AttributeSetInstance_ID() != 0)    // Get existing Location
					{
						M_Locator_ID = MStorage.getM_Locator_ID(line.getM_Warehouse_ID(),
																line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
																ordered, get_TrxName());
					}
					// Get default Location
					if (M_Locator_ID <= 0)
					{
						// try to take default locator for product first
						// if it is from the selected warehouse
						M_Locator_ID = product.getM_Locator_ID();
						if (M_Locator_ID > 0)
						{
							final I_M_Locator locator = warehousesRepo.getLocatorByRepoId(M_Locator_ID);
							// product has default locator defined but is not from the order warehouse
							if (locator.getM_Warehouse_ID() != lineWarehouseId.getRepoId())
							{
								M_Locator_ID = warehouseBL.getOrCreateDefaultLocatorId(lineWarehouseId).getRepoId();
							}
						}
						else
						{
							M_Locator_ID = warehouseBL.getOrCreateDefaultLocatorId(lineWarehouseId).getRepoId();
						}
					}
					// Update Storage
					// task 08999: update it async
					Services.get(IStorageBL.class).addAsync(
							getCtx(),
							lineWarehouseId.getRepoId(),
							M_Locator_ID,
							line.getM_Product_ID(),
							line.getM_AttributeSetInstance_ID(),
							line.getM_AttributeSetInstance_ID(),
							BigDecimal.ZERO,
							line.isDeliveryClosed() ? line.getQtyDelivered().subtract(line.getQtyOrdered()) : reserved,
							ordered,
							get_TrxName());
				}    // stockec
				// update line

				// task 09358: get rid of this; instead, update qtyReserved only in IOrderLineBL.
				// line.setQtyReserved(line.getQtyReserved().add(difference));
				// line.saveEx(get_TrxName()); // metas: use saveEx

				//
				Volume = Volume.add(product.getVolume().multiply(line.getQtyOrdered()));
				Weight = Weight.add(product.getWeight().multiply(line.getQtyOrdered()));
			}    // product
		}    // reverse inventory

		setVolume(Volume);
		setWeight(Weight);
		return true;
	}    // reserveStock

	/**
	 * Approve Document
	 *
	 * @return true if success
	 */
	@Override
	public boolean approveIt()
	{
		log.debug("approveIt - {}", this);
		setIsApproved(true);
		return true;
	}    // approveIt

	/**
	 * Reject Approval
	 *
	 * @return true if success
	 */
	@Override
	public boolean rejectIt()
	{
		log.debug("rejectIt - {}", this);
		setIsApproved(false);
		return true;
	}    // rejectIt

	/**************************************************************************
	 * Complete Document
	 *
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	@Override
	public String completeIt()
	{
		final DocStatus docStatus = completeIt0();
		return docStatus.getCode();
	}

	private DocStatus completeIt0()
	{
		//
		// Just prepare
		if (DOCACTION_Prepare.equals(getDocAction()))
		{
			setProcessed(false);
			return DocStatus.InProgress;
		}

		final I_C_DocType dt = Services.get(IDocTypeDAO.class).getById(getC_DocType_ID());
		final String docSubType = dt.getDocSubType();

		//
		// Offers
		if (X_C_DocType.DOCSUBTYPE_Proposal.equals(docSubType)
				|| X_C_DocType.DOCSUBTYPE_Quotation.equals(docSubType)
				|| X_C_DocType.DOCSUBTYPE_FrameAgrement.equals(docSubType))
		{
			// Binding
			if (X_C_DocType.DOCSUBTYPE_Quotation.equals(docSubType))
			{
				reserveStock(dt, getLinesRequeryOrderedByProduct());
			}
			m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
			if (m_processMsg != null)
			{
				return DocStatus.Invalid;
			}
			m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
			if (m_processMsg != null)
			{
				return DocStatus.Invalid;
			}
			// Set the definite document number after completed (if needed)
			setDefiniteDocumentNo();
			setProcessed(true);
			return DocStatus.Completed;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
		{
			return DocStatus.Invalid;
		}

		// Re-Check
		if (!m_justPrepared)
		{
			final DocStatus docStatus = DocStatus.ofCode(prepareIt());
			if (!docStatus.isInProgress())
			{
				return docStatus;
			}
		}

		// Implicit Approval
		if (!isApproved())
		{
			approveIt();
		}

		invalidateLines();

		log.debug("Completed: {}", this);

		final StringBuilder info = new StringBuilder();
		final boolean realTimePOS = false;

		// User Validation
		final String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			if (info.length() > 0)
			{
				info.append(" - ");
			}
			info.append(valid);
			m_processMsg = info.toString();
			return DocStatus.Invalid;
		}

		// Set the definite document number after completed (if needed)
		setDefiniteDocumentNo();

		setProcessed(true);
		m_processMsg = info.toString();
		//
		setDocAction(DOCACTION_Re_Activate); // issue #347
		return DocStatus.Completed;
	}    // completeIt

	/**
	 * Set the definite <code>DocumentNo</code> and <code>DateOrdered</code> after completed, both according to this order's <code>C_DocType</code>.<br>
	 */
	private void setDefiniteDocumentNo()
	{
		final I_C_DocType docType = Services.get(IDocTypeDAO.class).getById(getC_DocType_ID());

		if (docType.isOverwriteDateOnComplete())
		{
			//setDateOrdered(SystemTime.asTimestamp());
		}

		if (docType.isOverwriteSeqOnComplete())
		{
			final IDocumentNoBuilderFactory documentNoFactory = Services.get(IDocumentNoBuilderFactory.class);
			final String documentNo = documentNoFactory.forDocType(getC_DocType_ID(), true) // useDefiniteSequence=true
					.setDocumentModel(this)
					.setFailOnError(false)
					.build();
			if (documentNo != null && documentNo != IDocumentNoBuilder.NO_DOCUMENTNO)
			{
				setDocumentNo(documentNo);
				Services.get(IDocumentNoBL.class).fireDocumentNoChange(this, documentNo); // task 09776
			}
		}
	}

	/**
	 * Void Document.
	 * Set Qtys to 0 - Sales: reverse all documents
	 *
	 * @return true if success
	 */
	@Override
	public boolean voidIt()
	{
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
		{
			return false;
		}

		final List<MOrderLine> lines = getLinesRequeryOrderedByProduct();
		for (final MOrderLine line : lines)
		{
			final BigDecimal old = line.getQtyOrdered();
			if (old.signum() != 0)
			{
				line.addDescription(Services.get(IMsgBL.class).getMsg(getCtx(), "Voided") + " (" + old + ")");
				line.setQty(BigDecimal.ZERO);
				line.setLineNetAmt(BigDecimal.ZERO);
				line.save(get_TrxName());
			}
		}

		// update taxes
		final MOrderTax[] taxes = getTaxes(true);
		for (final MOrderTax tax : taxes)
		{
			if (!(tax.calculateTaxFromLines() && tax.save()))
			{
				return false;
			}
		}

		addDescription(Services.get(IMsgBL.class).getMsg(getCtx(), "Voided"));
		// Clear Reservations
		if (!reserveStock(null, lines))
		{
			m_processMsg = "Cannot unreserve Stock (void)";
			return false;
		}

		// UnLink All Requisitions
		MRequisitionLine.unlinkC_Order_ID(getCtx(), get_ID(), get_TrxName());

		if (!createReversals())
		{
			return false;
		}

		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
		{
			return false;
		}

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}    // voidIt

	/**
	 * Create Shipment/Invoice Reversals
	 *
	 * @return true if success
	 */
	private boolean createReversals()
	{
		// Cancel only Sales
		if (!isSOTrx())
		{
			return true;
		}

		log.debug("createReversals");
		final StringBuilder info = new StringBuilder();

		// Reverse All *Shipments*
		info.append("@M_InOut_ID@:");
		final MInOut[] shipments = getShipments();
		for (final MInOut shipment : shipments)
		{
			final MInOut ship = shipment;
			// if closed - ignore
			if (MInOut.DOCSTATUS_Closed.equals(ship.getDocStatus())
					|| MInOut.DOCSTATUS_Reversed.equals(ship.getDocStatus())
					|| MInOut.DOCSTATUS_Voided.equals(ship.getDocStatus()))
			{
				continue;
			}
			ship.set_TrxName(get_TrxName());

			// If not completed - void - otherwise reverse it
			if (!MInOut.DOCSTATUS_Completed.equals(ship.getDocStatus()))
			{
				if (ship.voidIt())
				{
					ship.setDocStatus(MInOut.DOCSTATUS_Voided);
				}
			}
			else if (ship.reverseCorrectIt())    // completed shipment
			{
				ship.setDocStatus(MInOut.DOCSTATUS_Reversed);
				info.append(" ").append(ship.getDocumentNo());
			}
			else
			{
				m_processMsg = "Could not reverse Shipment " + ship;
				return false;
			}
			ship.setDocAction(MInOut.DOCACTION_None);
			ship.save(get_TrxName());
		}    // for all shipments

		// Reverse All *Invoices*
		info.append(" - @C_Invoice_ID@:");
		for (final MInvoice invoice : getInvoices(OrderId.ofRepoId(getc_mydoc_ID())))
		{
			// if closed - ignore
			final DocStatus invoiceDocStatus = DocStatus.ofCode(invoice.getDocStatus());
			if (invoiceDocStatus.isClosedReversedOrVoided())
			{
				continue;
			}
			invoice.set_TrxName(get_TrxName());

			// If not completed - void - otherwise reverse it
			if (!invoiceDocStatus.isCompleted())
			{
				if (invoice.voidIt())
				{
					invoice.setDocStatus(DocStatus.Voided.getCode());
				}
			}
			else if (invoice.reverseCorrectIt())    // completed invoice
			{
				invoice.setDocStatus(DocStatus.Reversed.getCode());
				info.append(" ").append(invoice.getDocumentNo());
			}
			else
			{
				m_processMsg = "Could not reverse Invoice " + invoice;
				return false;
			}
			invoice.setDocAction(MInvoice.DOCACTION_None);
			invoice.save(get_TrxName());
		}    // for all shipments

		m_processMsg = info.toString();
		return true;
	}    // createReversals

	/**
	 * Close Document.
	 * Cancel not delivered Qunatities
	 *
	 * @return true if success
	 */
	@Override
	public boolean closeIt()
	{
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
		{
			return false;
		}

		// Close Not delivered Qty - SO/PO
		final List<MOrderLine> lines = getLinesRequeryOrderedByProduct();
		for (final MOrderLine line : lines)
		{
			final BigDecimal old = line.getQtyOrdered();
			if (old.compareTo(line.getQtyDelivered()) != 0)
			{
				line.setQtyLostSales(line.getQtyOrdered().subtract(line.getQtyDelivered()));
				line.setQtyOrdered(line.getQtyDelivered());
				// QtyEntered unchanged
				line.addDescription("Close (" + old + ")");
				InterfaceWrapperHelper.save(line, get_TrxName());
			}
		}
		// Clear Reservations
		if (!reserveStock(null, lines))
		{
			m_processMsg = "Cannot unreserve Stock (close)";
			return false;
		}
		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
		{
			return false;
		}

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}    // closeIt

	/**
	 * @author: phib
	 * re-open a closed order
	 * (reverse steps of close())
	 */
	public String reopenIt()
	{
		final DocStatus docStatus = DocStatus.ofCode(getDocStatus());
		if (!docStatus.isClosed())
		{
			return "Not closed - can't reopen";
		}

		//
		final List<MOrderLine> lines = getLinesRequeryOrderedByProduct();
		for (final MOrderLine line2 : lines)
		{
			final MOrderLine line = line2;
			if (BigDecimal.ZERO.compareTo(line.getQtyLostSales()) != 0)
			{
				line.setQtyOrdered(line.getQtyLostSales().add(line.getQtyDelivered()));
				line.setQtyLostSales(BigDecimal.ZERO);
				// QtyEntered unchanged

				// Strip Close() tags from description
				String desc = line.getDescription();
				if (desc == null)
				{
					desc = "";
				}
				final Pattern pattern = Pattern.compile("( \\| )?Close \\(.*\\)");
				final String[] parts = pattern.split(desc);
				desc = "";
				for (final String s : parts)
				{
					desc = desc.concat(s);
				}
				line.setDescription(desc);
				if (!line.save(get_TrxName()))
				{
					return "Couldn't save orderline";
				}
			}
		}
		// Clear Reservations
		if (!reserveStock(null, lines))
		{
			m_processMsg = "Cannot unreserve Stock (close)";
			return "Failed to update reservations";
		}

		setDocStatus(DocStatus.Completed.getCode());
		setDocAction(DOCACTION_Close);
		if (!this.save(get_TrxName()))
		{
			return "Couldn't save reopened order";
		}
		else
		{
			return "";
		}
	}    // reopenIt

	/**
	 * Reverse Correction - same void
	 *
	 * @return true if success
	 */
	@Override
	public boolean reverseCorrectIt()
	{
		// Before reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
		{
			return false;
		}

		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
		{
			return false;
		}

		return voidIt();
	}    // reverseCorrectionIt

	/**
	 * Reverse Accrual - none
	 *
	 * @return false
	 */
	@Override
	public boolean reverseAccrualIt()
	{
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
		{
			return false;
		}

		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
		{
			return false;
		}

		return false;
	}    // reverseAccrualIt

	/**
	 * Re-activate.
	 *
	 * @return true if success
	 */
	@Override
	public boolean reActivateIt()
	{
		ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_REACTIVATE);

		final DocTypeId docTypeId = DocTypeId.ofRepoId(getC_DocType_ID());
		final I_C_DocType dt = Services.get(IDocTypeDAO.class).getById(docTypeId);
		final String docSubType = dt.getDocSubType();

		if (X_C_DocType.DOCSUBTYPE_PrepayOrder.equals(docSubType))
		{
			// Replace Prepay with POS to revert all doc
			I_C_DocType newDT = null;
			for (final I_C_DocType type : MDocType.getOfClient(getCtx()))
			{
				if (X_C_DocType.DOCSUBTYPE_PrepayOrder.equals(type.getDocSubType()))
				{
					if (type.isDefault() || newDT == null)
					{
						newDT = type;
					}
				}
			}
			if (newDT == null)
			{
				return false;
			}
			else
			{
				setC_DocType_ID(newDT.getC_DocType_ID());
			}
		}

		// PO - just re-open
		if (!isSOTrx())
		{
			log.debug("Existing documents not modified - {}", dt);
		}
		else if (X_C_DocType.DOCSUBTYPE_OnCreditOrder.equals(docSubType)    // (W)illCall(I)nvoice
				|| X_C_DocType.DOCSUBTYPE_WarehouseOrder.equals(docSubType)    // (W)illCall(P)ickup
				|| X_C_DocType.DOCSUBTYPE_POSOrder.equals(docSubType))            // (W)alkIn(R)eceipt
		{
			if (!createReversals())
			{
				return false;
			}
		}
		else
		{
			log.debug("Existing documents not modified - SubType=" + docSubType);
		}

		/* globalqss - 2317928 - Reactivating/Voiding order must reset posted */
		Services.get(IFactAcctDAO.class).deleteForDocument(this);

		// metas: after reactivate put to the end of this method
		setDocAction(DOCACTION_Complete);
		setProcessed(false);

		// After reActivate
		ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REACTIVATE);

		// metas: commented out (legacy purposes)
		// TODO: metas: evaluate if we can uncommented this and remove the setDocAction above
		// setDocAction(DOCACTION_Complete);
		// setProcessed(false);
		return true;
	}    // reActivateIt

	/*************************************************************************
	 * Get Summary
	 *
	 * @return Summary of Document
	 */
	@Override
	public String getSummary()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append(getDocumentNo());
		// : Grand Total = 123.00 (#1)
		sb.append(": ").append(Services.get(IMsgBL.class).translate(getCtx(), "GrandTotal")).append("=");

		final List<MOrderLine> lines = _lines;
		if (lines != null)
		{
			sb.append(" (#").append(lines.size()).append(")");
		}

		// - Description
		if (getDescription() != null && getDescription().length() > 0)
		{
			sb.append(" - ").append(getDescription());
		}
		return sb.toString();
	}    // getSummary

	@Override
	public InstantAndOrgId getDocumentDate()
	{
		return InstantAndOrgId.ofTimestamp( getCreated(), OrgId.ofRepoId(getAD_Org_ID()));
	}

	/**
	 * Get Process Message
	 *
	 * @return clear text error message
	 */
	@Override
	public String getProcessMsg()
	{
		return m_processMsg;
	}    // getProcessMsg

	/**
	 * Get Document Owner (Responsible)
	 */
	@Override
	public int getDoc_User_ID()
	{
		return 100;
	}

	@Override
	public int getC_Currency_ID() {
		return 0;
	}

	/**
	 * Get Document Approval Amount
	 *
	 * @return amount
	 */
	@Override
	public BigDecimal getApprovalAmt()
	{
		return new BigDecimal(888888);
	}    // getApprovalAmt

	/**
	 * Document Status is Complete or Closed
	 *
	 * @return true if CO, CL or RE
	 */
	public boolean isComplete()
	{
		final DocStatus docStatus = DocStatus.ofCode(getDocStatus());
		return docStatus.isCompletedOrClosedOrReversed();
	}    // isComplete
} // MMyDoc
