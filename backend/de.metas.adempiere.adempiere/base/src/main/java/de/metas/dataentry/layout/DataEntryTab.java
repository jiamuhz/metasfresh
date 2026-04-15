package de.metas.dataentry.layout;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.dataentry.DataEntrySubTabId;
import de.metas.dataentry.DataEntryTabId;
import de.metas.i18n.ITranslatableString;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import org.adempiere.exceptions.AdempiereException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static de.metas.util.Check.assumeNotEmpty;


@Value
public class DataEntryTab
{
	DataEntryTabId id;

	ITranslatableString caption;
	ITranslatableString description;

	String internalName;
	boolean availableInApi;

	ImmutableList<DataEntrySubTab> subTabs;

	/** The column name of the record/document (e.g. C_BPartner_ID) against {@link I_DataEntry_Record_Assignment}s are linked. */
	DocumentLinkColumnName documentLinkColumnName;

	@Builder
	private DataEntryTab(
			@NonNull final DataEntryTabId id,
			@NonNull final ITranslatableString caption,
			@NonNull final ITranslatableString description,
			@NonNull final String internalName,
			final boolean availableInApi,
			@NonNull final DocumentLinkColumnName documentLinkColumnName,
			@Singular final List<DataEntrySubTab> subTabs)
	{
		this.id = id;
		this.caption = caption;
		this.description = description;
		this.internalName = internalName;
		this.availableInApi = availableInApi;
		this.documentLinkColumnName = documentLinkColumnName;
		this.subTabs = ImmutableList.copyOf(subTabs);
	}

	Stream<DataEntrySubTabId> streamSubTabIds()
	{
		return subTabs.stream().map(DataEntrySubTab::getId);
	}

	public static Set<DataEntrySubTabId> getSubTabIds(final Collection<DataEntryTab> tabs)
	{
		return tabs.stream()
				.flatMap(DataEntryTab::streamSubTabIds)
				.collect(ImmutableSet.toImmutableSet());
	}

	public DataEntrySubTab getSubTabById(@NonNull final DataEntrySubTabId subTabId)
	{
		return getSubTabByIdIfPresent(subTabId)
				.orElseThrow(() -> new AdempiereException("@NotFound@ " + subTabId + " in " + this));
	}

	Optional<DataEntrySubTab> getSubTabByIdIfPresent(@NonNull final DataEntrySubTabId subTabId)
	{
		return getFirstSubTabMatching(subTab -> DataEntrySubTabId.equals(subTab.getId(), subTabId));
	}

	public Optional<DataEntrySubTab> getFirstSubTabMatching(@NonNull final Predicate<DataEntrySubTab> predicate)
	{
		return subTabs.stream().filter(predicate).findFirst();
	}

	@Value
	public static class DocumentLinkColumnName
	{
		public static DocumentLinkColumnName of(final String columnName)
		{
			return new DocumentLinkColumnName(columnName);
		}

		String asString;

		private DocumentLinkColumnName(final String columnName)
		{
			asString = assumeNotEmpty(columnName, "Given columnName may not be empty");
		}
	}
}
