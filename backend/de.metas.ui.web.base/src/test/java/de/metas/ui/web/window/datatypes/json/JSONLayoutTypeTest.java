package de.metas.ui.web.window.datatypes.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.metas.ui.web.test.util.EnumTestUtils;
import de.metas.ui.web.window.descriptor.LayoutType;


public class JSONLayoutTypeTest
{
	@Test
	public void test_fromNullable_fullyCovered()
	{
		EnumTestUtils.assertMappingFullyCovered(LayoutType.values(), JSONLayoutType::fromNullable);
	}

	@Test
	public void test_fromNullable()
	{
		assertThat(JSONLayoutType.fromNullable((LayoutType)null)).isNull();
		assertThat(JSONLayoutType.fromNullable(LayoutType.primary)).isSameAs(JSONLayoutType.primary);
		assertThat(JSONLayoutType.fromNullable(LayoutType.secondary)).isSameAs(JSONLayoutType.secondary);
	}

}
