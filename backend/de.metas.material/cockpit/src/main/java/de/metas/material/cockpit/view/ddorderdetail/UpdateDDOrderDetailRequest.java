package de.metas.material.cockpit.view.ddorderdetail;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

 

@Value
@Builder
public class UpdateDDOrderDetailRequest
{
	@NonNull
	DDOrderDetailIdentifier ddOrderDetailIdentifier;

	@Builder.Default
	@NonNull
	BigDecimal qtyPending = BigDecimal.ZERO;
}
