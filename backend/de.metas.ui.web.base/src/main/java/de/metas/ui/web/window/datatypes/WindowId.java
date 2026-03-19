package de.metas.ui.web.window.datatypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.adempiere.ad.element.api.AdWindowId;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.OptionalInt;



@EqualsAndHashCode
public final class WindowId
{
	@JsonCreator
	public static WindowId fromJson(@NonNull final String json)
	{
		return new WindowId(json);
	}

	@Nullable
	public static WindowId fromNullableJson(@Nullable final String json)
	{
		return json != null ? fromJson(json) : null;
	}

	public static WindowId of(final int windowIdInt)
	{
		return new WindowId(windowIdInt);
	}

	public static WindowId of(@NonNull final AdWindowId adWindowId)
	{
		return new WindowId(adWindowId.getRepoId());
	}

	public static WindowId of(final DocumentId documentTypeId)
	{
		if (documentTypeId.isInt())
		{
			return new WindowId(documentTypeId.toInt());
		}
		else
		{
			return new WindowId(documentTypeId.toJson());
		}
	}

	@Nullable
	public static WindowId ofNullable(@Nullable final AdWindowId adWindowId)
	{
		return adWindowId != null ? new WindowId(adWindowId.getRepoId()) : null;
	}

	private final String value;
	private transient OptionalInt valueInt = null; // lazy

	private WindowId(final String value)
	{
		Check.assumeNotEmpty(value, "value is not empty");
		this.value = value;
	}

	private WindowId(final int valueInt)
	{
		Check.assumeGreaterThanZero(valueInt, "valueInt");
		this.valueInt = OptionalInt.of(valueInt);
		value = String.valueOf(valueInt);
	}

	@Override
	@Deprecated
	public String toString()
	{
		return toJson();
	}

	@JsonValue
	public String toJson()
	{
		return value;
	}

	public int toInt()
	{
		return toOptionalInt()
				.orElseThrow(() -> new AdempiereException("WindowId cannot be converted to int: " + this));
	}

	public int toIntOr(final int fallbackValue)
	{
		return toOptionalInt()
				.orElse(fallbackValue);
	}

	private OptionalInt toOptionalInt()
	{
		OptionalInt valueInt = this.valueInt;
		if (valueInt == null)
		{
			valueInt = this.valueInt = parseOptionalInt();
		}
		return valueInt;
	}

	private OptionalInt parseOptionalInt()
	{
		try
		{
			return OptionalInt.of(Integer.parseInt(value));
		}
		catch (final Exception ex)
		{
			return OptionalInt.empty();
		}
	}

	@Nullable
	public AdWindowId toAdWindowIdOrNull()
	{
		return AdWindowId.ofRepoIdOrNull(toIntOr(-1));
	}

	public AdWindowId toAdWindowId()
	{
		return AdWindowId.ofRepoId(toInt());
	}

	public boolean isInt()
	{
		try
		{
			toInt();
			return true;
		}
		catch (final Exception ex)
		{
			return false;
		}
	}

	public DocumentId toDocumentId()
	{
		return DocumentId.of(value);
	}

	public static boolean equals(@Nullable final WindowId id1, @Nullable final WindowId id2)
	{
		return Objects.equals(id1, id2);
	}
}
