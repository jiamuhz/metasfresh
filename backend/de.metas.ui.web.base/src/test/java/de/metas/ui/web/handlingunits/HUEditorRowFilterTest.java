package de.metas.ui.web.handlingunits;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

 

public class HUEditorRowFilterTest
{
	@Test
	public void testListsNotNullInEmptyQuery()
	{
		final HUEditorRowFilter emptyQuery = HUEditorRowFilter.builder().build();

		assertThat(emptyQuery.getExcludeHUIds()).isNotNull();
		assertThat(emptyQuery.getExcludeHUIds()).isEmpty();

		assertThat(emptyQuery.getExcludeHUStatuses()).isNotNull();
		assertThat(emptyQuery.getExcludeHUStatuses()).isEmpty();
	}
}
