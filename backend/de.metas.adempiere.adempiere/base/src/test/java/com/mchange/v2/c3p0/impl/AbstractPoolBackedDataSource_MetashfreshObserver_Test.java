package com.mchange.v2.c3p0.impl;

import org.junit.jupiter.api.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class AbstractPoolBackedDataSource_MetashfreshObserver_Test
{
	@Test
	public void check_getPoolManager_doesNotFail()
	{
		final boolean autoregister = false;
		final ComboPooledDataSource poolBackedDataSource = new ComboPooledDataSource(autoregister);

		AbstractPoolBackedDataSource_MetashfreshObserver.getPoolManager(poolBackedDataSource);
	}
}
