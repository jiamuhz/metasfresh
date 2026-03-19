package de.metas.ui.web.window.datatypes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

 

public class DocumentIdTest
{
	private ObjectMapper jsonObjectMapper;

	@BeforeEach
	public void init()
	{
		jsonObjectMapper = new ObjectMapper();
	}

	@Test
	public void toJson_Int() throws JsonProcessingException
	{
		final DocumentId documentId = DocumentId.of(12345);
		final String json = jsonObjectMapper.writeValueAsString(documentId);
		assertThat(json).isEqualTo("\"12345\"");
	}

	@Test
	public void toJson_String() throws JsonProcessingException
	{
		final DocumentId documentId = DocumentId.of("12345string");
		final String json = jsonObjectMapper.writeValueAsString(documentId);
		assertThat(json).isEqualTo("\"12345string\"");
	}

	@Test
	public void fromJson_Int() throws Exception
	{
		final DocumentId documentId = jsonObjectMapper.readValue("12345", DocumentId.class);
		assertThat(documentId).isEqualTo(DocumentId.of(12345));
	}

	@Test
	public void fromJson_String() throws Exception
	{
		final DocumentId documentId = jsonObjectMapper.readValue("\"12345\"", DocumentId.class);
		assertThat(documentId).isEqualTo(DocumentId.of(12345));
	}

	@Test
	public void fromJson_StrictString() throws Exception
	{
		final DocumentId documentId = jsonObjectMapper.readValue("\"12345string\"", DocumentId.class);
		assertThat(documentId).isEqualTo(DocumentId.of("12345string"));
	}
}
