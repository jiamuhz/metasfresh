package org.adempiere.warehouse.api.impl;

import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.warehouse.WarehouseType;
import org.adempiere.warehouse.WarehouseTypeId;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import lombok.NonNull;

/** */

class WarehouseTypesIndex
{
	public static WarehouseTypesIndex of(final List<WarehouseType> warehouseTypes)
	{
		return new WarehouseTypesIndex(warehouseTypes);
	}

	private final ImmutableMap<WarehouseTypeId, WarehouseType> warehouseTypesById;

	private WarehouseTypesIndex(final List<WarehouseType> warehouseTypes)
	{
		warehouseTypesById = Maps.uniqueIndex(warehouseTypes, WarehouseType::getId);
	}

	public WarehouseType getById(@NonNull final WarehouseTypeId id)
	{
		final WarehouseType warehouseType = warehouseTypesById.get(id);
		if (warehouseType == null)
		{
			throw new AdempiereException("@NotFound@ @M_Warehouse_Type_ID@: " + id);
		}
		return warehouseType;
	}
}
