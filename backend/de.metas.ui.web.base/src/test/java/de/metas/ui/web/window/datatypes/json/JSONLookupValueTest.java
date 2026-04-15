package de.metas.ui.web.window.datatypes.json;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.metas.JsonObjectMapperHolder;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;


public class JSONLookupValueTest
{
	private ObjectMapper jsonObjectMapper;

	@BeforeEach
	public void init()
	{
		jsonObjectMapper = JsonObjectMapperHolder.newJsonObjectMapper();
	}

	@Test
	public void testSerializeDeserialize() throws Exception
	{
		testSerializeDeserialize(
				JSONLookupValue.ofLookupValue(
						StringLookupValue.of("Y", "Yes"),
						"en_US"));
	}

	private void testSerializeDeserialize(final JSONLookupValue obj) throws IOException
	{
		final String json = jsonObjectMapper.writeValueAsString(obj);
		System.out.println("JSON: " + json);

		final JSONLookupValue objDeserialized = jsonObjectMapper.readValue(json, JSONLookupValue.class);
		assertThat(objDeserialized.toStringLookupValue()).isEqualTo(obj.toStringLookupValue());
	}

}
