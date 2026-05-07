package com.mchange.v2.c3p0.impl;

import com.mchange.v2.resourcepool.ResourcePool;

import lombok.NonNull;
public final class C3P0PooledConnectionPool_MetasfreshObserver
{
	public static ResourcePool getResourcePool(@NonNull final C3P0PooledConnectionPool pool)
	{
		return pool.rp;
	}
}
