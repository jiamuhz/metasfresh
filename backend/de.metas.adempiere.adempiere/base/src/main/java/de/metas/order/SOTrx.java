package de.metas.order;

import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.Optional;

/** */

public enum SOTrx
{
	SALES, PURCHASE;

	/**
	 * For backward compatibility we are accepting null parameter, so we are calling {@link #ofNullableBoolean(Boolean)}.
	 * <p>
	 * But in the future, instead of calling this method,
	 * pls call {@link #ofBooleanNotNull(Boolean)} if you know that the parameter is not null,
	 * or pls call {@link #ofNullableBoolean(Boolean)} if you know the parameter might be null (so the return value).
	 */
	@Nullable
	public static SOTrx ofBoolean(@Nullable final Boolean isSOTrx)
	{
		return ofNullableBoolean(isSOTrx);
	}

	@Nullable
	public static SOTrx ofNullableBoolean(@Nullable final Boolean isSOTrx)
	{
		return isSOTrx != null ? ofBooleanNotNull(isSOTrx) : null;
	}

	@NonNull
	public static SOTrx ofBooleanNotNull(@NonNull final Boolean isSOTrx)
	{
		return isSOTrx ? SALES : PURCHASE;
	}

	public static Optional<SOTrx> optionalOfBoolean(@Nullable final Boolean isSOTrx)
	{
		return isSOTrx != null
				? Optional.of(ofBooleanNotNull(isSOTrx))
				: Optional.empty();
	}

	public boolean toBoolean()
	{
		return isSales();
	}

	public static boolean toBoolean(final SOTrx soTrx)
	{
		if (soTrx == null)
		{
			return false;
		}
		return soTrx.toBoolean();
	}

	public boolean isSales()
	{
		return this == SALES;
	}

	public boolean isPurchase()
	{
		return this == PURCHASE;
	}

	public SOTrx invert()
	{
		return isSales() ? PURCHASE : SALES;
	}

	@NonNull
	public static SOTrx ofNameNotNull(@NonNull final String soTrx)
	{
		try
		{
			return SOTrx.valueOf(soTrx);
		}
		catch (final Exception exception)
		{
			throw new AdempiereException("Invalid SOTrx!")
					.appendParametersToMessage()
					.setParameter("SOTrx", soTrx)
					.setParameter("Known values", values());
		}
	}
}
