 

package de.metas.ui.web.pickingV2.productsToPick.rows;

import de.metas.i18n.ITranslatableString;
import de.metas.product.ProductId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class ProductInfo
{
	@NonNull
	ProductId productId;
	@NonNull
	String code;
	@NonNull
	ITranslatableString name;

	@NonNull
	String stockUOM;

	String packageSize;
	String packageSizeUOM;

}
