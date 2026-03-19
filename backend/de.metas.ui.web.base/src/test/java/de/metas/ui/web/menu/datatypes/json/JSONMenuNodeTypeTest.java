package de.metas.ui.web.menu.datatypes.json;

import de.metas.ui.web.menu.MenuNode.MenuNodeType;
import de.metas.ui.web.test.util.EnumTestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

  
public class JSONMenuNodeTypeTest
{
	@Test
	public void test_ofNullable_fullyCovered()
	{
		Assertions.assertThat(JSONMenuNodeType.ofNullable(null)).isNull();
		EnumTestUtils.assertMappingFullyCovered(MenuNodeType.values(), JSONMenuNodeType::ofNullable);
	}

}
