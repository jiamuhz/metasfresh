package de.metas.ui.web.view.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import de.metas.util.GuavaCollectors;

  
/**
 * JSON representation of {@link DocumentQueryOrderBy}.
 * 
 * 
 *
 */
public class JSONViewOrderBy
{
	public static List<JSONViewOrderBy> ofList(final DocumentQueryOrderByList orderBys)
	{
		if (orderBys == null || orderBys.isEmpty())
		{
			return ImmutableList.of();
		}

		return orderBys
				.stream()
				.map(orderBy -> of(orderBy))
				.filter(jsonOrderBy -> jsonOrderBy != null)
				.collect(GuavaCollectors.toImmutableList());
	}

	private static JSONViewOrderBy of(final DocumentQueryOrderBy orderBy)
	{
		return new JSONViewOrderBy(orderBy.getFieldName(), orderBy.isAscending());
	}

	@JsonProperty("fieldName")
	private final String fieldName;
	@JsonProperty("ascending")
	private final boolean ascending;

	@JsonCreator
	public JSONViewOrderBy(
			@JsonProperty("fieldName") final String fieldName //
			, @JsonProperty("ascending") final boolean ascending //
	)
	{
		super();
		this.fieldName = fieldName;
		this.ascending = ascending;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("fieldName", fieldName)
				.add("asc", ascending)
				.toString();
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public boolean isAscending()
	{
		return ascending;
	}
}
