package de.metas.ui.web.window.model.lookup;

import de.metas.ui.web.window.datatypes.LookupValue;

import javax.annotation.Nullable;

 

@FunctionalInterface
public interface LookupValueByIdSupplier
{
	LookupValue findById(@Nullable Object id);
}
