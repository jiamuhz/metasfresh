package de.metas.ui.web.document.filter.provider;

import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.Collection;


/**
 * Document filter descriptors provider
 * 
 *
 *
 */
public interface DocumentFilterDescriptorsProvider
{
	/**
	 * @return all available filter descriptors
	 */
	Collection<DocumentFilterDescriptor> getAll();

	/**
	 * @return filter descriptor or <code>null</code>
	 */
	@Nullable
	DocumentFilterDescriptor getByFilterIdOrNull(final String filterId);

	/**
	 * @return filter descriptor
	 */
	default DocumentFilterDescriptor getByFilterId(final String filterId)
	{
		final DocumentFilterDescriptor filterDescriptor = getByFilterIdOrNull(filterId);
		if (filterDescriptor == null)
		{
			throw new AdempiereException("Filter '" + filterId + "' was not found in " + this);
		}
		return filterDescriptor;
	}

	default DocumentFilter unwrap(@NonNull final JSONDocumentFilter jsonFilter)
	{
		final String filterId = jsonFilter.getFilterId();
		final DocumentFilterDescriptor filterDescriptor = getByFilterIdOrNull(filterId);

		// Ad-hoc filters (e.g. zoom references)
		if (filterDescriptor == null)
		{
			return JSONDocumentFilter.unwrapAsGenericFilter(jsonFilter);
		}
		// Filter with descriptor
		else
		{
			return filterDescriptor.unwrap(jsonFilter);
		}
	}
}
