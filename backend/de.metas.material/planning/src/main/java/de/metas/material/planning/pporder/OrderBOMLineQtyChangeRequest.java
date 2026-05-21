package de.metas.material.planning.pporder;

import de.metas.quantity.Quantity;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.mm.attributes.AttributeSetInstanceId;
import org.eevolution.api.PPOrderBOMLineId;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.function.UnaryOperator;

 
@Value
@Builder(toBuilder = true)
public class OrderBOMLineQtyChangeRequest
{
	@NonNull
	PPOrderBOMLineId orderBOMLineId;

	boolean usageVariance;

	@NonNull
	Quantity qtyIssuedOrReceivedToAdd;
	@Nullable
	Quantity qtyScrappedToAdd;
	@Nullable
	Quantity qtyRejectedToAdd;

	@NonNull
	@Builder.Default
	AttributeSetInstanceId asiId = AttributeSetInstanceId.NONE;

	@NonNull
	ZonedDateTime date;

	public OrderBOMLineQtyChangeRequest convertQuantities(@NonNull final UnaryOperator<Quantity> converter)
	{
		final UnaryOperator<Quantity> convertNullable = qty -> qty != null ? converter.apply(qty) : null;

		return toBuilder()
				.qtyIssuedOrReceivedToAdd(convertNullable.apply(getQtyIssuedOrReceivedToAdd()))
				.qtyScrappedToAdd(convertNullable.apply(getQtyScrappedToAdd()))
				.qtyRejectedToAdd(convertNullable.apply(getQtyRejectedToAdd()))
				.build();
	}

}
