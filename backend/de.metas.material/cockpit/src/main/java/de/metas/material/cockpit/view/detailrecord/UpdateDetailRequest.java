package de.metas.material.cockpit.view.detailrecord;

import de.metas.material.cockpit.view.DetailDataRecordIdentifier;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

 

@Value
public class UpdateDetailRequest
{
	DetailDataRecordIdentifier detailDataRecordIdentifier;

	 BigDecimal qtyOrdered;

	 BigDecimal qtyReserved;

	@Builder
	public UpdateDetailRequest(
			final @NonNull DetailDataRecordIdentifier detailDataRecordIdentifier,
			@NonNull final BigDecimal qtyOrdered,
			@NonNull final BigDecimal qtyReserved)
	{
		this.qtyOrdered = qtyOrdered;
		this.qtyReserved = qtyReserved;

		this.detailDataRecordIdentifier = detailDataRecordIdentifier;
	}
}
