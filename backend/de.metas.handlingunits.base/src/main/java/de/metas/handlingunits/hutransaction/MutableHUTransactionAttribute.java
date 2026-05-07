package de.metas.handlingunits.hutransaction;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Nullable;

import org.adempiere.mm.attributes.AttributeId;

import de.metas.handlingunits.HuPackingInstructionsAttributeId;
import de.metas.handlingunits.model.I_M_HU_Attribute;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class MutableHUTransactionAttribute implements IHUTransactionAttribute
{
	private final HUTransactionAttributeOperation operation;

	private final AttributeId attributeId;

	private final String valueString;
	private final String valueStringInitial;

	private final BigDecimal valueNumber;
	private final BigDecimal valueNumberInitial;

	private final Date valueDate;
	private final Date valueDateInitial;

	//
	// Updateable attributes
	private HuPackingInstructionsAttributeId piAttributeId;
	private Object referencedObject = null;
	private I_M_HU_Attribute huAttribute = null;

	@Builder
	private MutableHUTransactionAttribute(
			@NonNull final HUTransactionAttributeOperation operation,
			@NonNull final AttributeId attributeId,
			@Nullable final String valueString,
			@Nullable final String valueStringInitial,
			@Nullable final BigDecimal valueNumber,
			@Nullable final BigDecimal valueNumberInitial,
			@Nullable final Date valueDate,
			@Nullable final Date valueDateInitial)
	{
		this.operation = operation;

		this.attributeId = attributeId;

		this.valueString = valueString;
		this.valueStringInitial = valueStringInitial;
		this.valueNumber = valueNumber;
		this.valueNumberInitial = valueNumberInitial;
		this.valueDate = valueDate;
		this.valueDateInitial = valueDateInitial;
	}

}
