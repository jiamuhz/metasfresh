

package de.metas.ui.web.comments.json;

import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.util.JSONObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class JSONCommentTest
{
	@Test
	void testSerialisationDeserialisation()
	{
		// expected
		final ZonedDateTime zonedDateTime = ZonedDateTime.of(2020, Month.APRIL.getValue(), 22, 11, 11, 11, 0, ZoneId.of("UTC+8"));
		final String zonedDateTimeString = DateTimeConverters.toJson(zonedDateTime, ZoneId.of("UTC+8"));

		final JSONComment expected = JSONComment.builder()
				.createdBy("who created this?")
				.created(zonedDateTimeString)
				.text("This is a test Comment.\nTra la la.")
				.build();

		// actual
		final JSONObjectMapper<JSONComment> jsonObjectMapper = JSONObjectMapper.forClass(JSONComment.class);
		final String json = jsonObjectMapper.writeValueAsString(expected);
		final JSONComment deserialisedRequest = jsonObjectMapper.readValue(json);

		assertThat(deserialisedRequest).isEqualToIgnoringGivenFields(expected);
	}
}
