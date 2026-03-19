package de.metas.material.cockpit.view.ddorderdetail;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;



@Value
@Builder
public class InsertDDOrderDetailRequest
{
	@NonNull
	DDOrderDetailIdentifier ddOrderDetailIdentifier;

	@NonNull
	DDOrderDetailType detailType;

	@NonNull
	BigDecimal qtyPending;
}
