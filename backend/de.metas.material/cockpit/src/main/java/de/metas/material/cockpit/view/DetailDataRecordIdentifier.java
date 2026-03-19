package de.metas.material.cockpit.view;

import de.metas.material.cockpit.model.I_MD_Cockpit_DocumentDetail;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.ad.dao.IQueryBuilder;
import org.compiere.model.IQuery;



@Value
public class DetailDataRecordIdentifier
{
	public static DetailDataRecordIdentifier createForShipmentSchedule(
			@NonNull final MainDataRecordIdentifier mainDataRecordIdentifier,
			final int shipmentScheduleId)
	{
		return new DetailDataRecordIdentifier(mainDataRecordIdentifier, shipmentScheduleId, 0);
	}

	public static DetailDataRecordIdentifier createForReceiptSchedule(
			@NonNull final MainDataRecordIdentifier mainDataRecordIdentifier,
			final int receiptScheduleId)
	{
		return new DetailDataRecordIdentifier(mainDataRecordIdentifier, 0, receiptScheduleId);
	}

	int shipmentScheduleId;
	int receiptScheduleId;
	MainDataRecordIdentifier mainDataRecordIdentifier;

	private DetailDataRecordIdentifier(
			@NonNull final MainDataRecordIdentifier mainDataRecordIdentifier,
			final int shipmentScheduleId,
			final int receiptScheduleId)
	{
		this.receiptScheduleId = receiptScheduleId;
		this.shipmentScheduleId = shipmentScheduleId;
		this.mainDataRecordIdentifier = mainDataRecordIdentifier;

		final boolean shipmentScheduleIdSet = shipmentScheduleId > 0;
		final boolean receiptScheduleIdSet = receiptScheduleId > 0;
		Check.errorUnless(shipmentScheduleIdSet ^ receiptScheduleIdSet,
				"Either shipmentScheduleId or receipScheduleId (but not both!) needs to be < 0");
	}

	public IQuery<I_MD_Cockpit_DocumentDetail> createQuery()
	{
		final IQueryBuilder<I_MD_Cockpit_DocumentDetail> queryBuilder = mainDataRecordIdentifier.createQueryBuilder()
				.andCollectChildren(I_MD_Cockpit_DocumentDetail.COLUMN_MD_Cockpit_ID);
		if (shipmentScheduleId > 0)
		{
			queryBuilder.addEqualsFilter(I_MD_Cockpit_DocumentDetail.COLUMN_M_ShipmentSchedule_ID, shipmentScheduleId);
		}
		if (receiptScheduleId > 0)
		{
			queryBuilder.addEqualsFilter(I_MD_Cockpit_DocumentDetail.COLUMN_M_ReceiptSchedule_ID, receiptScheduleId);
		}
		return queryBuilder.create();
	}
}
