package de.metas.ui.web.window.descriptor.sql;

import de.metas.ui.web.window.descriptor.LookupDescriptor;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;

 

/**
 * Retrieves a particular field from given {@link ResultSet}.
 * 
 * To create specific instances of this interface, please use {@link DocumentFieldValueLoaders}.
 */
@FunctionalInterface
public interface DocumentFieldValueLoader
{
	@Nullable
	Object retrieveFieldValue(ResultSet rs, boolean isDisplayColumnAvailable, String adLanguage, LookupDescriptor lookupDescriptor) throws SQLException;
}
