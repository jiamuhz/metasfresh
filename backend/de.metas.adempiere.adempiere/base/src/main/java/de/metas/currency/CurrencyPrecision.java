package de.metas.currency;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.metas.util.Check;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/** */

@EqualsAndHashCode
public final class CurrencyPrecision
{
	@JsonCreator
	public static CurrencyPrecision ofInt(final int precision)
	{
		if (precision >= 0 && precision < CACHED_VALUES.length)
		{
			return CACHED_VALUES[precision];
		}
		else
		{
			return new CurrencyPrecision(precision);
		}
	}

	public static final CurrencyPrecision ZERO;
	public static final CurrencyPrecision TWO;
	public static final CurrencyPrecision FOUR;

	private static final CurrencyPrecision[] CACHED_VALUES = new CurrencyPrecision[] {
			ZERO = new CurrencyPrecision(0),
			new CurrencyPrecision(1),
			TWO = new CurrencyPrecision(2),
			new CurrencyPrecision(3),
			FOUR = new CurrencyPrecision(4),
			new CurrencyPrecision(5),
			new CurrencyPrecision(6),
			new CurrencyPrecision(7),
			new CurrencyPrecision(8),
			new CurrencyPrecision(9),
			new CurrencyPrecision(10),
			new CurrencyPrecision(11),
			new CurrencyPrecision(12),
	};

	public static int toInt(@Nullable final CurrencyPrecision precision, final int defaultValue)
	{
		return precision != null ? precision.toInt() : defaultValue;
	}

	private final int precision;

	private CurrencyPrecision(final int precision)
	{
		Check.assumeGreaterOrEqualToZero(precision, "precision");
		this.precision = precision;
	}

	@Override
	@Deprecated
	public String toString()
	{
		return String.valueOf(precision);
	}

	@JsonValue
	public int toInt()
	{
		return precision;
	}

	public BigDecimal roundIfNeeded(@NonNull final BigDecimal amt)
	{
		if (amt.scale() > precision)
		{
			return amt.setScale(precision, getRoundingMode());
		}
		else
		{
			return amt;
		}
	}

	public BigDecimal round(@NonNull final BigDecimal amt)
	{
		return amt.setScale(precision, getRoundingMode());
	}

	public RoundingMode getRoundingMode()
	{
		return RoundingMode.HALF_UP;
	}

	public CurrencyPrecision min(final CurrencyPrecision other)
	{
		return this.precision <= other.precision ? this : other;
	}
}
