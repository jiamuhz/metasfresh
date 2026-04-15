package de.metas.ui.web.view.descriptor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;


public class SqlAndParamsTest
{
	@Nested
	public class andNullables
	{
		@Test
		public void empty()
		{
			final Optional<SqlAndParams> result = SqlAndParams.andNullables(ImmutableList.of());
			assertThat(result).isEmpty();
		}

		@Test
		public void onlyNullElements()
		{
			final ArrayList<SqlAndParams> collection = new ArrayList<>();
			collection.add(null);
			collection.add(null);
			collection.add(null);

			final Optional<SqlAndParams> result = SqlAndParams.andNullables(collection);
			assertThat(result).isEmpty();
		}

		@Test
		public void singleElement()
		{
			final SqlAndParams element = SqlAndParams.of("test");
			final ArrayList<SqlAndParams> collection = new ArrayList<>();
			collection.add(element);

			final Optional<SqlAndParams> result = SqlAndParams.andNullables(collection);
			assertThat(result).containsSame(element);
		}

		@Test
		public void singleElement_and_someNullValues()
		{
			final SqlAndParams element = SqlAndParams.of("test");
			final ArrayList<SqlAndParams> collection = new ArrayList<>();
			collection.add(null);
			collection.add(element);
			collection.add(null);
			collection.add(null);

			final Optional<SqlAndParams> result = SqlAndParams.andNullables(collection);
			assertThat(result).containsSame(element);
		}

		@Test
		public void twoElements_and_someNullValues()
		{
			final SqlAndParams element1 = SqlAndParams.of("test1");
			final SqlAndParams element2 = SqlAndParams.of("test2");
			final ArrayList<SqlAndParams> collection = new ArrayList<>();
			collection.add(null);
			collection.add(element1);
			collection.add(null);
			collection.add(element2);
			collection.add(null);
			collection.add(null);

			final Optional<SqlAndParams> result = SqlAndParams.andNullables(collection);
			assertThat(result).contains(SqlAndParams.of("(test1) AND (test2)"));
		}

		@Test
		public void fiveElements_and_someNullValues()
		{
			final SqlAndParams element1 = SqlAndParams.of("test1");
			final SqlAndParams element2 = SqlAndParams.of("test2");
			final SqlAndParams element3 = SqlAndParams.of("test3");
			final SqlAndParams element4 = SqlAndParams.of("test4");
			final SqlAndParams element5 = SqlAndParams.of("test5");
			final ArrayList<SqlAndParams> collection = new ArrayList<>();
			collection.add(null);
			collection.add(element1);
			collection.add(null);
			collection.add(element2);
			collection.add(null);
			collection.add(element3);
			collection.add(null);
			collection.add(element4);
			collection.add(null);
			collection.add(element5);
			collection.add(null);

			final Optional<SqlAndParams> result = SqlAndParams.andNullables(collection);
			assertThat(result).contains(SqlAndParams.of("(test1) AND (test2) AND (test3) AND (test4) AND (test5)"));
		}
	}
}
