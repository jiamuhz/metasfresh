package de.metas.i18n;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/** */

public class TranslatableStringBuilderTest
{
	@Test
	public void testStringsAreAggregated_1()
	{
		final ITranslatableString actual = TranslatableStringBuilder.newInstance()
				.append("a")
				.append("b")
				.append("c")
				.build();

		final ITranslatableString expected = TranslatableStrings.constant("abc");
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testStringsAreAggregated_2()
	{
		final ITranslatableString actual = TranslatableStringBuilder.newInstance()
				.append("a")
				.append("b")
				.append("c")
				.append(66)
				.build();

		final ITranslatableString expected = TranslatableStrings.join("",
				TranslatableStrings.constant("abc"),
				TranslatableStrings.number(66));

		assertThat(actual).isEqualTo(expected);
	}

	@Nested
	public class isEmpty
	{
		@Test
		public void whenCreated()
		{
			final TranslatableStringBuilder builder = TranslatableStringBuilder.newInstance();
			assertThat(builder.isEmpty()).isTrue();
		}

		@Test
		public void whenSingleStringAppended()
		{
			final TranslatableStringBuilder builder = TranslatableStringBuilder.newInstance();
			builder.append("string constant");
			assertThat(builder.isEmpty()).isFalse();
		}

		@Test
		public void whenEmptyStringAppended()
		{
			final TranslatableStringBuilder builder = TranslatableStringBuilder.newInstance();
			builder.append("");
			assertThat(builder.isEmpty()).isTrue();
		}

	}
}
