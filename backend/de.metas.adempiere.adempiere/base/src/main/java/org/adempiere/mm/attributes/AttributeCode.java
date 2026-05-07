package org.adempiere.mm.attributes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import de.metas.util.Check;
import de.metas.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * aka M_Attribute.Value
 */
@SuppressWarnings("UnstableApiUsage")
@EqualsAndHashCode
public final class AttributeCode implements Comparable<AttributeCode>
{
	@Nullable
	public static AttributeCode ofNullableString(@Nullable final String code)
	{
		return StringUtils.trimBlankToOptional(code).map(AttributeCode::ofString).orElse(null);
	}

	@NonNull
	@JsonCreator
	public static AttributeCode ofString(@NonNull final String code)
	{
		return interner.intern(new AttributeCode(code));
	}

	private static final Interner<AttributeCode> interner = Interners.newStrongInterner();

	@Getter
	private final String code;

	private AttributeCode(@NonNull final String code)
	{
		Check.assumeNotEmpty(code, "code is not empty");
		this.code = code;
	}

	/**
	 * @deprecated please use {@link #getCode()}
	 */
	@JsonValue
	@Override
	@Deprecated
	public String toString()
	{
		return getCode();
	}

	public static boolean equals(@Nullable final AttributeCode a1, @Nullable final AttributeCode a2)
	{
		return Objects.equals(a1, a2);
	}

	@Override
	public int compareTo(final AttributeCode other)
	{
		return this.code.compareTo(other.code);
	}
}
