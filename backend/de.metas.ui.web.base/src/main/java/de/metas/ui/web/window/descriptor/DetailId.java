package de.metas.ui.web.window.descriptor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableSet;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.ad.element.api.AdTabId;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

import static de.metas.util.Check.assumeNotEmpty;

  
@Immutable
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public final class DetailId implements Comparable<DetailId>
{
	private static final String PARTS_SEPARATOR = "-";

	private static final String PREFIX_AD_TAB_ID = "AD_Tab";

	public static DetailId fromAD_Tab_ID(@NonNull final AdTabId adTabId)
	{
		return new DetailId(PREFIX_AD_TAB_ID, adTabId.getRepoId());
	}


	public static DetailId fromPrefixAndId(final String prefix, final int id)
	{
		return new DetailId(prefix, id);
	}

	@JsonCreator
	@Nullable
	public static DetailId fromJson(@Nullable final String json)
	{
		if (json == null)
		{
			return null;
		}

		final String jsonToUse = json.trim();
		if (jsonToUse.isEmpty())
		{
			return null;
		}

		final String[] prefixAndId = jsonToUse.split(PARTS_SEPARATOR);
		Check.assume(prefixAndId.length == 2, "The given json needs to consist of a prefix and the actual ID, separated by {}; json={}", PARTS_SEPARATOR, json);

		final String prefix = prefixAndId[0];
		final int idInt = Integer.parseInt(prefixAndId[1]);

		return DetailId.fromPrefixAndId(prefix, idInt);
	}

	@Nullable
	public static String toJson(@Nullable final DetailId detailId)
	{
		return detailId == null ? null : (detailId.idPrefix + PARTS_SEPARATOR + detailId.idInt);
	}

	public static Set<String> toJson(@Nullable final Collection<DetailId> detailIds)
	{
		if (detailIds == null || detailIds.isEmpty())
		{
			return ImmutableSet.of();
		}

		return detailIds.stream().map(detailId -> detailId.toJson()).collect(GuavaCollectors.toImmutableSet());
	}

	@Getter
	private final String idPrefix;

	@Getter
	private final int idInt;

	private transient String _tableAlias = null; // lazy

	private DetailId(@NonNull final String idPrefix, final int idInt)
	{
		assumeNotEmpty(idPrefix, "idPrefix");
		Check.assume(!idPrefix.contains(PARTS_SEPARATOR), "The given prefix may not contain the the parts-separator={}; prefix={}", PARTS_SEPARATOR, idPrefix);

		this.idPrefix = idPrefix;
		this.idInt = idInt;
	}

	@Override
	public String toString()
	{
		return toJson(this);
	}

	@JsonValue
	public String toJson()
	{
		return toJson(this);
	}

	public String getTableAlias()
	{
		if (_tableAlias == null)
		{
			_tableAlias = "d" + idInt;
		}
		return _tableAlias;
	}

	@Nullable
	public AdTabId toAdTabId()
	{
		if (PREFIX_AD_TAB_ID.equals(idPrefix))
		{
			return AdTabId.ofRepoId(idInt);
		}
		return null;
	}

	@Override
	public int compareTo(@Nullable final DetailId o)
	{
		if (o == null)
		{
			return 1;
		}

		return Objects.compare(toJson(), o.toJson(), Comparator.naturalOrder());
	}

	public static boolean equals(@Nullable final DetailId o1, @Nullable final DetailId o2)
	{
		return Objects.equals(o1, o2);
	}
}
