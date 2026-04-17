package de.metas.ui.web.bankstatement_reconciliation.actions;

import com.google.common.collect.ImmutableList;
import de.metas.bpartner.BPartnerId;
import de.metas.cache.CCacheStats;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.model.lookup.DocumentZoomIntoInfo;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Evaluatee;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



final class MockedBPartnerLookupDataSource implements LookupDataSource
{
	@Override
	public IntegerLookupValue findById(@Nullable final Object idObj)
	{
		return createIntegerLookupValue(idObj);
	}

	@Override
	public @NonNull LookupValuesList findByIdsOrdered(final @NonNull Collection<?> ids)
	{
		return ids.stream()
				.map(MockedBPartnerLookupDataSource::createIntegerLookupValue)
				.filter(Objects::nonNull)
				.collect(LookupValuesList.collect());
	}

	@Nullable
	private static IntegerLookupValue createIntegerLookupValue(@Nullable final Object idObj)
	{
		if (idObj == null)
		{
			return null;
		}
		else
		{
			final int idInt = convertIdToInt(idObj);
			return IntegerLookupValue.of(idInt, "BPartner " + idInt);
		}
	}

	private static int convertIdToInt(@NonNull final Object idObj)
	{
		if (idObj instanceof Number)
		{
			return ((Number)idObj).intValue();
		}
		else if (idObj instanceof BPartnerId)
		{
			return ((BPartnerId)idObj).getRepoId();
		}
		else
		{
			throw new AdempiereException("Cannot convert `" + idObj + "` (" + idObj.getClass() + ") to `int`.");
		}
	}

	@Override
	public LookupValuesPage findEntities(final Evaluatee ctx, final int pageLength)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public LookupValuesPage findEntities(final Evaluatee ctx, final String filter, final int firstRow, final int pageLength)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public List<CCacheStats> getCacheStats()
	{
		return ImmutableList.of();
	}

	@Override
	public DocumentZoomIntoInfo getDocumentZoomInto(final int id)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<WindowDocumentTypeId> getZoomIntoWindowId()
	{
		return Optional.empty();
	}

	@Override
	public void cacheInvalidate()
	{
		// nothing
	}
}
