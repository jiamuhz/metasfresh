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
public final class WindowDocumentTypeId
{
	private final String value;
	private transient OptionalInt valueInt = null; // lazy

	@JsonCreator
	public static WindowDocumentTypeId fromJson(@NonNull final String json)
	{
		return new WindowDocumentTypeId(json);
	}

	@Nullable
	public static WindowDocumentTypeId fromNullableJson(@Nullable final String json)
	{
		return json != null ? fromJson(json) : null;
	}

	public static WindowDocumentTypeId of(final int windowIdInt)
	{
		return new WindowDocumentTypeId(windowIdInt);
	}

	public static WindowDocumentTypeId of(@NonNull final AdWindowId adWindowId)
	{
		return new WindowDocumentTypeId(adWindowId.getRepoId());
	}

	public static WindowDocumentTypeId of(final DocumentId documentTypeId)
	{
		if (documentTypeId.isInt())
		{
			return new WindowDocumentTypeId(documentTypeId.toInt());
		}
		else
		{
			return new WindowDocumentTypeId(documentTypeId.toJson());
		}
	}

	@Nullable
	public static WindowDocumentTypeId ofNullable(@Nullable final AdWindowId adWindowId)
	{
		return adWindowId != null ? new WindowDocumentTypeId(adWindowId.getRepoId()) : null;
	}

	private WindowDocumentTypeId(final String value)
	{
		Check.assumeNotEmpty(value, "value is not empty");
		this.value = value;
	}

	private WindowDocumentTypeId(final int valueInt)
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

	public static boolean equals(@Nullable final WindowDocumentTypeId id1, @Nullable final WindowDocumentTypeId id2)
	{
		return Objects.equals(id1, id2);
	}
}
