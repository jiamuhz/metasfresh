package de.metas.ui.web.view.descriptor;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import lombok.experimental.UtilityClass;

 

public class SqlViewRowIdsConverters
{
	public static final transient SqlViewRowIdsConverter TO_INT_STRICT = new StrictToIntConverter();
	public static final transient SqlViewRowIdsConverter TO_INT_EXCLUDING_STRINGS = new ToIntExcludingStringsConverter();

	private static final class StrictToIntConverter implements SqlViewRowIdsConverter
	{
		@Override
		public Set<Integer> convertToRecordIds(DocumentIdsSelection rowIds)
		{
			return rowIds.toIntSet();
		}
	}

	private static class ToIntExcludingStringsConverter implements SqlViewRowIdsConverter
	{
		@Override
		public Set<Integer> convertToRecordIds(final DocumentIdsSelection rowIds)
		{
			return rowIds.stream()
					.filter(DocumentId::isInt) // exclude non-int values
					.map(DocumentId::toInt)
					.collect(ImmutableSet.toImmutableSet());
		}
	}

}
