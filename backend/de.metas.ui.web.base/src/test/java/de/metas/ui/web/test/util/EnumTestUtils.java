package de.metas.ui.web.test.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.junit.Ignore;

  
@Ignore
public class EnumTestUtils
{
	public static <JSONEnumType, EnumType> void assertMappingFullyCovered(final EnumType[] values, final Function<EnumType, JSONEnumType> toJson)
	{
		final boolean checkAlreadyMatchedValues = true;
		assertMappingFullyCovered(values, toJson, checkAlreadyMatchedValues);
	}

	public static <JSONEnumType, EnumType> void assertMappingFullyCovered(
			final EnumType[] values,
			final Function<EnumType, JSONEnumType> toJson,
			final boolean checkAlreadyMatchedValues)
	{
		assertThat(toJson.apply(null)).isNull();

		final Set<JSONEnumType> jsonValuesAlreadyMatched = new HashSet<>();
		for (final EnumType value : values)
		{
			final JSONEnumType jsonValue = toJson.apply(value);
			assertThat(jsonValue)
					.withFailMessage("JSON shall not be null for " + value)
					.isNotNull();

			if (checkAlreadyMatchedValues && !jsonValuesAlreadyMatched.add(jsonValue))
			{
				fail("JSON value " + jsonValue + " was already matched");
			}
		}
	}
}
