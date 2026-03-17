package org.adempiere.mm.attributes.spi;

/** */

import org.adempiere.mm.attributes.api.IAttributeSet;
import org.compiere.model.I_M_Attribute;

/**
 * "Null" callout. Use <code>NullAttributeValueCallout.instance</code> if you need an instance.
 *
 */
public final class NullAttributeValueCallout implements IAttributeValueCallout
{
	public static final NullAttributeValueCallout instance = new NullAttributeValueCallout();

	private NullAttributeValueCallout()
	{
		super();
	}

	/**
	 * Method does nothing
	 */
	@Override
	public void onValueChanged(final IAttributeValueContext attributeValueContext, final IAttributeSet attributeSet, final I_M_Attribute attribute, final Object valueOld, final Object valueNew)
	{
		// nothing
	}

	/**
	 * @return <code>bull</code>
	 */
	@Override
	public Object generateSeedValue(final IAttributeSet attributeSet, final I_M_Attribute attribute, final Object valueInitialDefault)
	{
		return valueInitialDefault;
	}

	/**
	 * @return <code>false</code>
	 */
	@Override
	public boolean isReadonlyUI(final IAttributeValueContext ctx, final IAttributeSet attributeSet, final I_M_Attribute attribute)
	{
		return false;
	}

	@Override
	public boolean isAlwaysEditableUI(final IAttributeValueContext ctx, final IAttributeSet attributeSet, final I_M_Attribute attribute)
	{
		return false;
	}

	@Override
	public boolean isDisplayedUI(final IAttributeSet attributeSet, final I_M_Attribute attribute)
	{
		return true;
	}
}
