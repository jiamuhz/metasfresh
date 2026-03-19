package de.metas.ui.web.window.datatypes.json;

import org.junit.jupiter.api.Test;

import de.metas.ui.web.test.util.EnumTestUtils;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;

  
public class JSONLayoutWidgetTypeTest
{
	@Test
	public void test_fromNullable_fullyCovered()
	{
		final boolean checkAlreadyMatchedValues = false; // because ZonedDateTime is mapped to same JSONLayoutWidgetType as DateTime
		EnumTestUtils.assertMappingFullyCovered(
				DocumentFieldWidgetType.values(),
				JSONLayoutWidgetType::fromNullable,
				checkAlreadyMatchedValues);
	}

}
