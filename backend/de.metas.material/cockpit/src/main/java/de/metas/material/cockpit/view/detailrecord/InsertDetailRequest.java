package de.metas.material.cockpit.view.detailrecord;

import de.metas.material.cockpit.view.DetailDataRecordIdentifier;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;



@Value
public class InsertDetailRequest
{
	DetailDataRecordIdentifier detailDataRecordIdentifier;

	int orderLineId;
	int orderId;

	int subscriptionLineId;
	int subscriptionId;

	int docTypeId;

	/**
	 * May be zero because shipment schedules initially have a zero bpartnerId.
	 */
	int bPartnerId;

	BigDecimal qtyOrdered;

	BigDecimal qtyReserved;

	@Builder
	public InsertDetailRequest(
			@NonNull final DetailDataRecordIdentifier detailDataRecordIdentifier,
			final int orderLineId,
			final int orderId,
			final int subscriptionLineId,
			final int subscriptionId,
			final int bPartnerId,
			final int docTypeId,
			@NonNull final BigDecimal qtyOrdered,
			@NonNull final BigDecimal qtyReserved)
	{
		this.detailDataRecordIdentifier = detailDataRecordIdentifier;

		this.bPartnerId = bPartnerId;
		this.qtyOrdered = qtyOrdered;
		this.qtyReserved = qtyReserved;

		this.orderLineId = orderLineId;
		this.orderId = orderId;

		this.subscriptionLineId = subscriptionLineId;
		this.subscriptionId = subscriptionId;

		this.docTypeId = docTypeId;

		validate();
	}

	public final void validate()
	{
		final boolean orderLineIdSet = orderLineId > 0;
		final boolean subscriptionLineIdSet = subscriptionLineId > 0;

		Check.errorUnless(orderLineIdSet ^ subscriptionLineIdSet,
				"Either orderLineId or subscriptionLineId need to be > 0 (but not both!); orderLineId={}; subscriptionLineId={}",
				orderLineId, subscriptionLineId);

		Check.errorIf(orderLineIdSet && orderId <= 0,
				"If orderLineId is > 0, then orderId also needs to be > 0; orderLineId={}, orderId={}",
				orderLineId, orderId);

		Check.errorIf(subscriptionLineIdSet && subscriptionId <= 0,
				"If subscriptionLineId is > 0, then orderId also needs to be > 0; subscriptionLineId={}, subscriptionId={}",
				subscriptionLineId, subscriptionId);

	}
}
