package de.metas.ui.web.window.datatypes.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

 

public class JSONDocumentFieldTest
{
	private ObjectMapper jsonObjectMapper;

	@BeforeEach
	public void init()
	{
		jsonObjectMapper = new ObjectMapper();
	}

	@Test
	public void test_deserialize_PreliminaryTest() throws Exception
	{
		final JSONDocumentField field = new JSONDocumentField("field1")
				.setWidgetType(null); // N/A

		final String json = jsonObjectMapper.writeValueAsString(field);

		final JSONDocumentField field2 = jsonObjectMapper.readValue(json, JSONDocumentField.class);
		assertThat(field2.getField()).isEqualTo(field.getField());
	}
}
