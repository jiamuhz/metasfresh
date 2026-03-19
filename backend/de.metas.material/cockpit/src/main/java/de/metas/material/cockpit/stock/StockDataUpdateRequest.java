package de.metas.material.cockpit.stock;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;



@Value
@Builder
public class StockDataUpdateRequest
{
	@NonNull
	StockDataRecordIdentifier identifier;

	@Default
	BigDecimal onHandQtyChange = BigDecimal.ZERO;

	@NonNull
	StockChangeSourceInfo sourceInfo;
}
