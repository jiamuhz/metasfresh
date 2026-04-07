package de.metas.ui.web.window.datatypes.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.metas.ui.web.test.util.EnumTestUtils;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElementField.JSONLookupSource;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;

 

public class JSONLookupSourceTest
{
	@Test
	public void test_fromNullable_fullyCovered()
	{
		EnumTestUtils.assertMappingFullyCovered(LookupSource.values(), JSONLookupSource::fromNullable);
	}

	@Test
	public void test_fromNullable()
	{
		assertThat(JSONLookupSource.fromNullable((LookupSource)null)).isNull();
		assertThat(JSONLookupSource.fromNullable(LookupSource.list)).isSameAs(JSONLookupSource.list);
		assertThat(JSONLookupSource.fromNullable(LookupSource.lookup)).isSameAs(JSONLookupSource.lookup);
	}

}
