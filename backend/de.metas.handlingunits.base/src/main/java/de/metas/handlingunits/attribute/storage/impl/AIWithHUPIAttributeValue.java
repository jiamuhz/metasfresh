package de.metas.handlingunits.attribute.storage.impl;

import com.google.common.annotations.VisibleForTesting;
import de.metas.handlingunits.attribute.impl.AbstractHUAttributeValue;
import de.metas.handlingunits.attribute.storage.IAttributeStorage;
import de.metas.handlingunits.model.I_M_HU_PI_Attribute;
import lombok.NonNull;
import org.compiere.model.I_M_AttributeInstance;
import org.compiere.util.TimeUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Wraps an {@link I_M_AttributeInstance} and uses definition from {@link I_M_HU_PI_Attribute}
 *
 * @author tsa
 */
@VisibleForTesting
public class AIWithHUPIAttributeValue extends AbstractHUAttributeValue
{
	private final I_M_AttributeInstance attributeInstance;
	private final boolean isGeneratedAttribute;

	public AIWithHUPIAttributeValue(
			@NonNull final IAttributeStorage attributeStorage,
			@NonNull final I_M_AttributeInstance attributeInstance,
			@NonNull final I_M_HU_PI_Attribute piAttribute,
			final boolean isGeneratedAttribute)
	{
		super(attributeStorage,
				piAttribute,
				Boolean.TRUE // ASI attributes are ALWAYS created from template attributes
		);

		this.attributeInstance = attributeInstance;
		this.isGeneratedAttribute = isGeneratedAttribute;
	}

	@Override
	protected void setInternalValueString(final String value)
	{
		attributeInstance.setValue(value);
	}

	@Override
	protected void setInternalValueNumber(final BigDecimal value)
	{
		attributeInstance.setValueNumber(value);
	}

	@Override
	protected String getInternalValueString()
	{
		return attributeInstance.getValue();
	}

	@Override
	protected BigDecimal getInternalValueNumber()
	{
		return attributeInstance.getValueNumber();
	}

	@Override
	protected String getInternalValueStringInitial()
	{
		return null;
	}

	/**
	 * @return <code>null</code>.
	 */
	@Override
	protected BigDecimal getInternalValueNumberInitial()
	{
		return null;
	}

	@Override
	protected void setInternalValueStringInitial(final String value)
	{
		throw new UnsupportedOperationException("Setting initial value not supported");
	}

	@Override
	protected void setInternalValueNumberInitial(final BigDecimal value)
	{
		throw new UnsupportedOperationException("Setting initial value not supported");
	}

	@Override
	public boolean isNew()
	{
		return isGeneratedAttribute;
	}

	@Override
	protected void setInternalValueDate(Date value)
	{
		attributeInstance.setValueDate(TimeUtil.asTimestamp(value));
	}

	@Override
	protected Date getInternalValueDate()
	{
		return attributeInstance.getValueDate();
	}

	@Override
	protected void setInternalValueDateInitial(Date value)
	{
		throw new UnsupportedOperationException("Setting initial value not supported");
	}

	@Override
	protected Date getInternalValueDateInitial()
	{
		return null;
	}

	@Override
	public boolean isOnlyIfInProductAttributeSet()
	{
		// FIXME tsa: figure out why this returns false instead of using the flag from M_HU_PI_Attribute?!
		return false;
	}
}
