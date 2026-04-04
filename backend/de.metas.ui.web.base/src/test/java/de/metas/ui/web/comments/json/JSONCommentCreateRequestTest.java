 

package de.metas.ui.web.comments.json;

import de.metas.util.JSONObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JSONCommentCreateRequestTest
{
	@Test
	void testSerialisationDeserialisation()
	{
		final JSONObjectMapper<JSONCommentCreateRequest> jsonObjectMapper = JSONObjectMapper.forClass(JSONCommentCreateRequest.class);

		final JSONCommentCreateRequest expected = new JSONCommentCreateRequest("This is a test Comment.\nTra la la.");

		final String json = jsonObjectMapper.writeValueAsString(expected);
		final JSONCommentCreateRequest deserialisedRequest = jsonObjectMapper.readValue(json);
		assertThat(deserialisedRequest).isEqualToIgnoringGivenFields(expected);
	}

}
