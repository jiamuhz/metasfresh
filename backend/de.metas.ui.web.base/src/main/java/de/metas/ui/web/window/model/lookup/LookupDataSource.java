package de.metas.ui.web.window.model.lookup;

import de.metas.cache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.NonNull;
import org.compiere.util.Evaluatee;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

  
public interface LookupDataSource extends LookupValueByIdSupplier
{
	int FIRST_ROW = 0;
	int DEFAULT_PageLength = 10;

	LookupValuesPage findEntities(Evaluatee ctx, int pageLength);

	LookupValuesPage findEntities(Evaluatee ctx, String filter, int firstRow, int pageLength);

	default LookupValuesPage findEntities(final Evaluatee ctx, final String filter)
	{
		return findEntities(ctx, filter, FIRST_ROW, DEFAULT_PageLength);
	}

	/**
	 * @return all lookup values
	 */
	default LookupValuesPage findEntities(final Evaluatee ctx)
	{
		return findEntities(ctx, Integer.MAX_VALUE);
	}

	@Override
	@Nullable
	LookupValue findById(Object id);

	default Optional<LookupValue> findByIdOptional(Object id) {return Optional.ofNullable(findById(id));}

	/**
	 * @return lookup values in the same order as the collection order
	 */
	@NonNull
	LookupValuesList findByIdsOrdered(@NonNull final Collection<?> ids);

	List<CCacheStats> getCacheStats();

	DocumentZoomIntoInfo getDocumentZoomInto(final int id);

	/**
	 * @return optional WindowId to be used when zooming into
	 */
	Optional<WindowId> getZoomIntoWindowId();

	void cacheInvalidate();
}
