package de.metas.handlingunits.attributes.sscc18.impl;

import java.util.Properties;

import org.adempiere.mm.attributes.api.IAttributeSet;
import org.adempiere.mm.attributes.spi.AbstractAttributeValueGenerator;
import org.compiere.model.I_M_Attribute;
import org.compiere.model.X_M_Attribute;

import de.metas.handlingunits.attribute.IHUAttributesBL;
import de.metas.handlingunits.attributes.sscc18.ISSCC18CodeBL;
import de.metas.handlingunits.attributes.sscc18.SSCC18;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.organization.OrgId;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;

public class SSCC18AttributeValueGenerator extends AbstractAttributeValueGenerator
{

	private ISSCC18CodeBL sscc18CodeBL = Services.get(ISSCC18CodeBL.class);

	@Override
	public String getAttributeValueType()
	{
		return X_M_Attribute.ATTRIBUTEVALUETYPE_StringMax40;
	}

	@Override
	public boolean canGenerateValue(final Properties ctx, final IAttributeSet attributeSet, final I_M_Attribute attribute)
	{
		return true;
	}

	@Override
	public String generateStringValue(
			final Properties ctx_IGNORED,
			@NonNull final IAttributeSet attributeSet,
			final I_M_Attribute attribute_IGNORED)
	{
		final I_M_HU hu = Services.get(IHUAttributesBL.class).getM_HU(attributeSet);

		// We use M_HU_ID for SSCC18 serial number (06852)
		final int serialNumber = hu.getM_HU_ID();
		Check.errorIf(serialNumber <= 0, "M_HU_ID={} for M_HU={}", serialNumber, hu);

		final SSCC18 sscc18 = sscc18CodeBL.generate(OrgId.ofRepoIdOrAny(hu.getAD_Org_ID()), serialNumber);
		return sscc18.asString();
	}
}
