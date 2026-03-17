package de.metas.acct.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.ToString;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/** */

@ToString(of = "elements")
public class AcctSchemaElementsMap implements Iterable<AcctSchemaElement>
{
	public static AcctSchemaElementsMap of(@NonNull final List<AcctSchemaElement> elements)
	{
		if (elements.isEmpty())
		{
			return EMPTY;
		}

		return new AcctSchemaElementsMap(elements);
	}

	private static final AcctSchemaElementsMap EMPTY = new AcctSchemaElementsMap(ImmutableList.of());

	private final ImmutableList<AcctSchemaElement> elements;
	private final ImmutableMap<AcctSchemaElementType, AcctSchemaElement> elementsByType;

	private AcctSchemaElementsMap(final List<AcctSchemaElement> elements)
	{
		this.elements = elements.stream()
				.sorted(Comparator.comparing(AcctSchemaElement::getSeqNo) // NOTE: ordering by SeqNo first it's uber important! (07539)
						.thenComparing(AcctSchemaElement::getElementType))
				.collect(ImmutableList.toImmutableList());

		this.elementsByType = Maps.uniqueIndex(this.elements, AcctSchemaElement::getElementType);
	}

	public boolean isEmpty()
	{
		return elements.isEmpty();
	}

	public AcctSchemaElementsMap onlyDisplayedInEditor()
	{
		final ImmutableList<AcctSchemaElement> elementsFiltered = elements.stream()
				.filter(AcctSchemaElement::isDisplayedInEditor)
				.collect(ImmutableList.toImmutableList());

		if (elementsFiltered.size() == elements.size())
		{
			return this;
		}
		else
		{
			return of(elementsFiltered);
		}
	}

	public boolean isElementEnabled(@NonNull final AcctSchemaElementType elementType)
	{
		return elementsByType.get(elementType) != null;
	}

	@Nullable
	public AcctSchemaElement getByElementType(@NonNull final AcctSchemaElementType elementType)
	{
		return elementsByType.get(elementType);
	}

	public ImmutableSet<AcctSchemaElementType> getElementTypes()
	{
		return elementsByType.keySet();
	}

	@Override
	public Iterator<AcctSchemaElement> iterator()
	{
		return elements.iterator();
	}

	public ChartOfAccountsId getChartOfAccountsId()
	{
		final AcctSchemaElement accountSchemaElement = getByElementType(AcctSchemaElementType.Account);
		if (accountSchemaElement == null)
		{
			throw new AdempiereException("No schema element of type " + AcctSchemaElementType.Account + " found");
		}

		final ChartOfAccountsId chartOfAccountsId = accountSchemaElement.getChartOfAccountsId();
		if (chartOfAccountsId == null)
		{
			throw new AdempiereException("No Chart of Accounts defined for " + accountSchemaElement);
		}

		return chartOfAccountsId;
	}

}
