package de.metas.ui.web.document.filter.sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.compiere.db.Database;
import org.compiere.util.DisplayType;
import org.junit.jupiter.api.Test;



public class SqlParamsCollectorTest
{
	@Test
	public void newInstance()
	{
		final SqlParamsCollector newInstance = SqlParamsCollector.newInstance();
		assertThat(newInstance).isNotNull();
		assertThat(newInstance.isCollecting()).isTrue();
		assertThat(newInstance.toList()).isEmpty();

		assertThat(newInstance.placeholder("string")).isEqualTo("?");
		assertThat(newInstance.placeholder(10)).isEqualTo("?");
		assertThat(newInstance.placeholder(null)).isEqualTo("?");
		assertThat(newInstance.placeholder(BigDecimal.TEN)).isEqualTo("?");

		final List<Object> collectedParams = newInstance.toList();
		assertThat(collectedParams).containsExactly("string", 10, null, BigDecimal.TEN);
	}

	@Test
	public void notCollecting()
	{
		final SqlParamsCollector newInstance = SqlParamsCollector.notCollecting();
		assertThat(newInstance).isNotNull();
		assertThat(newInstance.isCollecting()).isFalse();
		assertThat(newInstance.toList()).isNull();

		assertThat(newInstance.placeholder("string")).isEqualTo("'string'");
		assertThat(newInstance.placeholder(10)).isEqualTo("10");
		assertThat(newInstance.placeholder(null)).isEqualTo("NULL");

		assertThat(newInstance.placeholder(BigDecimal.TEN)).isEqualTo(Database.TO_NUMBER(BigDecimal.TEN, DisplayType.Number));

		final List<Object> collectedParams = newInstance.toList();
		assertThat(collectedParams).isNull();
	}

	@Test
	public void wrapNullable_nullList()
	{
		final SqlParamsCollector fromNullable = SqlParamsCollector.wrapNullable(null);
		assertThat(fromNullable).isNotNull();
		assertThat(fromNullable.isCollecting()).isFalse();
		assertThat(fromNullable.toList()).isNull();
		assertThat(fromNullable.placeholder("string")).isEqualTo("'string'");
	}

	@Test
	public void wrapNullable()
	{
		final ArrayList<Object> list = new ArrayList<>();
		list.add("preexistingString");

		final SqlParamsCollector fromNullable = SqlParamsCollector.wrapNullable(list);
		assertThat(fromNullable).isNotNull();
		assertThat(fromNullable.isCollecting()).isTrue();
		assertThat(fromNullable.toList()).isNotNull();
		assertThat(fromNullable.placeholder("anotherString")).isEqualTo("?");

		assertThat(list).containsExactly("preexistingString", "anotherString");
	}

}
