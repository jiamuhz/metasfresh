package org.adempiere.util.trxConstraints.api.impl;

/** */


import org.adempiere.test.AdempiereTestHelper;
import org.adempiere.util.trxConstraints.api.ITrxConstraints;
import org.adempiere.util.trxConstraints.api.ITrxConstraintsBL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.metas.util.Services;
import de.metas.util.collections.CollectionUtils;

public class TrxConstraintsBLTest
{
	/** service under test */
	private TrxConstraintsBL trxConstraintsBL;

	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();

		Services.registerService(ITrxConstraintsBL.class, new TrxConstraintsBL()
		{
			@Override
			protected boolean isDisabled()
			{
				return false;
			}
		});
		this.trxConstraintsBL = (TrxConstraintsBL)Services.get(ITrxConstraintsBL.class);
	}

	@Test
	public void test_saveRestore()
	{
		final ITrxConstraints constraints1 = trxConstraintsBL.getConstraints();
		constraints1.addAllowedTrxNamePrefix("Trx1");

		//
		// Copy to constraints2 and test
		trxConstraintsBL.saveConstraints();
		final ITrxConstraints constraints2 = trxConstraintsBL.getConstraints();
		Assert.assertNotSame(constraints1, constraints2);
		Assert.assertEquals("Invalid AllowedTrxNamePrefixes for: " + constraints2,
				CollectionUtils.asSet("Trx1"), // expected
				constraints2.getAllowedTrxNamePrefixes() // actual
		);
		//
		constraints2.addAllowedTrxNamePrefix("Trx2");

		//
		// Copy to constraints3 and test
		trxConstraintsBL.saveConstraints();
		final ITrxConstraints constraints3 = trxConstraintsBL.getConstraints();
		Assert.assertNotSame(constraints2, constraints3);
		Assert.assertEquals("Invalid AllowedTrxNamePrefixes for: " + constraints3,
				CollectionUtils.asSet("Trx1", "Trx2"), // expected
				constraints3.getAllowedTrxNamePrefixes() // actual
		);
		//
		constraints3.addAllowedTrxNamePrefix("Trx3");

		//
		// Restore to constraints2 and test
		{
			trxConstraintsBL.restoreConstraints();
			final ITrxConstraints constraints2Restored = trxConstraintsBL.getConstraints();
			Assert.assertSame(constraints2, constraints2Restored);
			Assert.assertEquals("Invalid AllowedTrxNamePrefixes for: " + constraints2Restored,
					CollectionUtils.asSet("Trx1", "Trx2"), // expected
					constraints2Restored.getAllowedTrxNamePrefixes() // actual
			);
		}

		//
		// Restore to constraints1 and test
		{
			trxConstraintsBL.restoreConstraints();
			final ITrxConstraints constraints1Restored = trxConstraintsBL.getConstraints();
			Assert.assertSame(constraints1, constraints1Restored);
			Assert.assertEquals("Invalid AllowedTrxNamePrefixes for: " + constraints1Restored,
					CollectionUtils.asSet("Trx1"), // expected
					constraints1Restored.getAllowedTrxNamePrefixes() // actual
			);
		}

		//
		// Restore to constraints1 and test (AGAIN)
		{
			trxConstraintsBL.restoreConstraints();
			final ITrxConstraints constraints1Restored = trxConstraintsBL.getConstraints();
			Assert.assertSame(constraints1, constraints1Restored);
			Assert.assertEquals("Invalid AllowedTrxNamePrefixes for: " + constraints1Restored,
					CollectionUtils.asSet("Trx1"), // expected
					constraints1Restored.getAllowedTrxNamePrefixes() // actual
			);
		}

	}
}
