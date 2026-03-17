package de.metas.util;

/** */

import lombok.NonNull;

public interface IColorRepository extends ISingletonService
{
	MFColor getColorById(@NonNull ColorId adColorId);

	ColorId saveFlatColorAndReturnId(String flatColorHexString);

	ColorId getColorIdByName(String name);
}
