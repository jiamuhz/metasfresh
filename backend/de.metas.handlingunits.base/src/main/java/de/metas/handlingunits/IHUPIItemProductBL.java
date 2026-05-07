package de.metas.handlingunits;

import java.util.List;

import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;

import de.metas.handlingunits.model.I_M_HU_PI_Item;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.handlingunits.model.I_M_HU_PI_Version;
import de.metas.i18n.ITranslatableString;
import de.metas.uom.IUOMDAO;
import de.metas.uom.UomId;
import de.metas.util.ISingletonService;
import de.metas.util.Services;

public interface IHUPIItemProductBL extends ISingletonService
{
	I_M_HU_PI_Item_Product getById(HUPIItemProductId id);

	List<I_M_HU_PI_Item_Product> getCompatibleItemDefProducts(I_M_HU_PI_Version version, I_M_Product product);

	/**
	 * @return <code>true</code> if product is available in the version (or IsAllowAnyProduct), false otherwise
	 */
	boolean isCompatibleProduct(I_M_HU_PI_Version version, I_M_Product product);

	void deleteForItem(I_M_HU_PI_Item packingInstructionsItem);

	/**
	 * Returns <code>true</code> if the given <code>piip</code> is the "virtual" one, i.e. the one referencing the virtual packing instruction.
	 */
	boolean isVirtualHUPIItemProduct(I_M_HU_PI_Item_Product piip);

	boolean isInfiniteCapacity(HUPIItemProductId id);

	/**
	 * @return builder used to create the display name
	 */
	IHUPIItemProductDisplayNameBuilder buildDisplayName();

	/**
	 * Builds and set Name and Description field.
	 *
	 * Name will be build using {@link IHUPIItemProductDisplayNameBuilder#buildItemProductDisplayName()} via {@link #buildDisplayName()}.
	 *
	 * @see #buildDisplayName()
	 */
	void setNameAndDescription(I_M_HU_PI_Item_Product itemProduct);

	ITranslatableString getDisplayName(HUPIItemProductId piItemProductId);

	static I_C_UOM extractUOMOrNull(final I_M_HU_PI_Item_Product itemProduct)
	{
		final UomId uomId = UomId.ofRepoIdOrNull(itemProduct.getC_UOM_ID());
		return uomId != null ? Services.get(IUOMDAO.class).getById(uomId) : null;
	}
}
