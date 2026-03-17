package org.compiere.util;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

/** */

@Immutable
public final class ValueNamePairList
{
	@JsonCreator
	public static final ValueNamePairList of(@JsonProperty("l") final List<ValueNamePair> values)
	{
		if (values == null || values.isEmpty())
		{
			return EMPTY;
		}

		return new ValueNamePairList(values);
	}
	
	public static final ValueNamePairList of(final ValueNamePair[] arr)
	{
		if (arr == null || arr.length == 0)
		{
			return EMPTY;
		}
		return new ValueNamePairList(ImmutableList.copyOf(arr));
	}

	
	public static final ValueNamePairList of()
	{
		return EMPTY;
	}

	public static final ValueNamePairList EMPTY = new ValueNamePairList(ImmutableList.<ValueNamePair> of());

	@JsonProperty("l")
	private final List<ValueNamePair> values;

	private ValueNamePairList(final List<ValueNamePair> values)
	{
		super();
		this.values = ImmutableList.copyOf(values);
	}

	public List<ValueNamePair> getValues()
	{
		return values;
	}

}
