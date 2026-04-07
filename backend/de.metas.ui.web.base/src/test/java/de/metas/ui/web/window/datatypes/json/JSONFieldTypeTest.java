package de.metas.ui.web.window.datatypes.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.metas.ui.web.test.util.EnumTestUtils;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElementField.JSONFieldType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.FieldType;

 

public class JSONFieldTypeTest
{
	@Test
	public void test_fromNullable_fullyCovered()
	{
		EnumTestUtils.assertMappingFullyCovered(FieldType.values(), JSONFieldType::fromNullable);
	}

	@Test
	public void test_fromNullable()
	{
		assertThat(JSONFieldType.fromNullable((FieldType)null)).isNull();
		assertThat(JSONFieldType.fromNullable(FieldType.ActionButtonStatus)).isSameAs(JSONFieldType.ActionButtonStatus);
		assertThat(JSONFieldType.fromNullable(FieldType.ActionButton)).isSameAs(JSONFieldType.ActionButton);
	}

}
