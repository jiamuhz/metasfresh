package de.metas.ui.web.window.datatypes.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.metas.ui.web.test.util.EnumTestUtils;
import de.metas.ui.web.window.descriptor.LayoutAlign;


public class JSONLayoutAlignTest
{
	@Test
	public void test_fromNullable_fullyCovered()
	{
		EnumTestUtils.assertMappingFullyCovered(LayoutAlign.values(), JSONLayoutAlign::fromNullable);
	}

	@Test
	public void test_fromNullable()
	{
		assertThat(JSONLayoutAlign.fromNullable((LayoutAlign)null)).isNull();
		assertThat(JSONLayoutAlign.fromNullable(LayoutAlign.Left)).isSameAs(JSONLayoutAlign.left);
		assertThat(JSONLayoutAlign.fromNullable(LayoutAlign.Center)).isSameAs(JSONLayoutAlign.center);
		assertThat(JSONLayoutAlign.fromNullable(LayoutAlign.Right)).isSameAs(JSONLayoutAlign.right);
		assertThat(JSONLayoutAlign.fromNullable(LayoutAlign.Justify)).isSameAs(JSONLayoutAlign.justify);
	}

}
