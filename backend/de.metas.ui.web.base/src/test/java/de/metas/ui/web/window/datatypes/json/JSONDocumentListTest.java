package de.metas.ui.web.window.datatypes.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.adempiere.exceptions.AdempiereException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.metas.JsonObjectMapperHolder;


public class JSONDocumentListTest
{
	private ObjectMapper jsonObjectMapper;

	@BeforeEach
	public void init()
	{
		jsonObjectMapper = JsonObjectMapperHolder.newJsonObjectMapper();
	}

	@Test
	public void testSerialize()
	{
		assertThat(toJson(JSONDocumentList.builder().build()))
				.isEqualTo("{\"result\":[],\"missingIds\":[]}");
	}

	private String toJson(final JSONDocumentList obj)
	{
		try
		{
			return jsonObjectMapper.writeValueAsString(obj);
		}
		catch (final JsonProcessingException e)
		{
			throw new AdempiereException("Failed serializing " + obj, e);
		}
	}

}
