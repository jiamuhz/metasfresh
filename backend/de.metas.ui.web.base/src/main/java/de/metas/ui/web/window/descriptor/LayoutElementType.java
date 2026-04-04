 

package de.metas.ui.web.window.descriptor;

import de.metas.util.lang.ReferenceListAwareEnum;
import de.metas.util.lang.ReferenceListAwareEnums;
import lombok.Getter;
import lombok.NonNull;
import org.compiere.model.X_AD_UI_Element;

import javax.annotation.Nullable;

public enum LayoutElementType implements ReferenceListAwareEnum
{
	Field(X_AD_UI_Element.AD_UI_ELEMENTTYPE_Field),
	Labels(X_AD_UI_Element.AD_UI_ELEMENTTYPE_Labels),
	InlineTab(X_AD_UI_Element.AD_UI_ELEMENTTYPE_InlineTab);

	private static final ReferenceListAwareEnums.ValuesIndex<LayoutElementType> index = ReferenceListAwareEnums.index(values());

	@Getter
	private final String code;

	LayoutElementType(@NonNull final String code)
	{
		this.code = code;
	}

	public static LayoutElementType ofCode(@NonNull final String code)
	{
		return index.ofCode(code);
	}

	@Nullable
	public static LayoutElementType ofNullableCode(@Nullable final String code)
	{
		return index.ofNullableCode(code);
	}
}
