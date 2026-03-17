package de.metas.marketing.base.bpartner;

import de.metas.util.GuavaCollectors;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;

import java.util.Map;
import java.util.stream.Stream;

/** */

public enum DefaultAddressType
{
	BillToDefault("B"), //
	ShipToDefault("S");

	@Getter
	private final String code;

	private DefaultAddressType(final String code)
	{
		this.code = code;
	}

	@NonNull
	public static DefaultAddressType forCode(@NonNull final String code)
	{
		final DefaultAddressType type = code2type.get(code);
		if (type == null)
		{
			throw new AdempiereException("No " + DefaultAddressType.class + " found for code: " + code);
		}
		return type;
	}

	private static final Map<String, DefaultAddressType> code2type = Stream.of(values())
			.collect(GuavaCollectors.toImmutableMapByKey(DefaultAddressType::getCode));

}
