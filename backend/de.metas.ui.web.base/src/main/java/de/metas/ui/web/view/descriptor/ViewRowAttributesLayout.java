package de.metas.ui.web.view.descriptor;

import java.util.List;

import com.google.common.collect.ImmutableList;

import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import lombok.NonNull;
import lombok.Value;

  
@Value
public final class ViewRowAttributesLayout
{
	public static ViewRowAttributesLayout of(@NonNull final List<DocumentLayoutElementDescriptor> elements)
	{
		return new ViewRowAttributesLayout(elements);
	}

	// 属性项 集合
	private final ImmutableList<DocumentLayoutElementDescriptor> elements;

	private ViewRowAttributesLayout(@NonNull final List<DocumentLayoutElementDescriptor> elements)
	{
		this.elements = ImmutableList.copyOf(elements);
	}
}
