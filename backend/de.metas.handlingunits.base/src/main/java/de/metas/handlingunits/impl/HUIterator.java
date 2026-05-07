package de.metas.handlingunits.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.TrxRunnable;

import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.IHUIterator;
import de.metas.handlingunits.IMutableHUContext;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Item;
import de.metas.handlingunits.storage.IHUItemStorage;
import de.metas.util.Check;
import de.metas.util.Services;
import lombok.NonNull;

public class HUIterator extends AbstractHUIterator
{
	public HUIterator()
	{
		registerNodeIterator(I_M_HU.class, new HUNodeIterator());
		registerNodeIterator(I_M_HU_Item.class, new HUItemNodeIterator());

		setEnableStorageIteration(true); // backward compatibility
	}

	/**
	 * If true (the default!), you also need to provide a date via {@link #setDate(java.util.Date)}.
	 *
	 * @param enabled
	 * @return
	 */
	public final HUIterator setEnableStorageIteration(final boolean enabled)
	{
		if (enabled)
		{
			registerNodeIterator(IHUItemStorage.class, new HUItemStorageNodeIterator());
		}
		else
		{
			unregisterNodeIterator(IHUItemStorage.class);
		}

		return this;
	}

	@Override
	public final IHUIterator iterate(@NonNull final I_M_HU hu)
	{
		final List<I_M_HU> hus = Collections.singletonList(hu);
		return iterate(hus);
	}

	@Override
	public final IHUIterator iterate(final Collection<I_M_HU> hus)
	{
		Check.assumeNotNull(hus, "hus not null");

		final AbstractNodeIterator<I_M_HU> huNodeIterator = getNodeIterator(I_M_HU.class);

		setStatus(HUIteratorStatus.Running);

		for (final I_M_HU hu : hus)
		{
			huNodeIterator.iterate(hu);
		}

		//
		// If after running everything the status is still running, switch it to finished
		if (getStatus() == HUIteratorStatus.Running)
		{
			setStatus(HUIteratorStatus.Finished);
		}

		return this;
	}

	/**
	 * Iterate given HUs in a database transaction.
	 *
	 * Use this when you want to change those HUs or their dependent data
	 *
	 * @param hus
	 * @return this
	 */
	public final IHUIterator iterateInTrx(final Collection<I_M_HU> hus)
	{
		Check.assumeNotNull(hus, "hus not null");

		final AbstractNodeIterator<I_M_HU> huNodeIterator = getNodeIterator(I_M_HU.class);

		setStatus(HUIteratorStatus.Running);

		//
		// Execute in transaction
		final ITrxManager trxManager = Services.get(ITrxManager.class);
		trxManager.run(getHUContext().getTrxName(), new TrxRunnable()
		{

			@Override
			public void run(final String localTrxName) throws Exception
			{
				//
				// Setup in-transaction context
				final IHUContext huContext = getHUContext();
				final IMutableHUContext huContextLocal = huContext.copyAsMutable();
				huContextLocal.setTrxName(localTrxName);
				setHUContext(huContextLocal);

				try
				{
					//
					// Iterate hus
					for (final I_M_HU hu : hus)
					{
						//
						// Make sure our HU has NULL transaction or same transaction as the one we are running in now
						final String huTrxName = InterfaceWrapperHelper.getTrxName(hu);
						if (!trxManager.isNull(huTrxName) && !trxManager.isSameTrxName(huTrxName, localTrxName))
						{
							throw new AdempiereException("" + hu + " shall have null transaction or local transaction(" + localTrxName + ") but not " + huTrxName);
						}

						//
						// Set HU's transaction to our local transaction an iterate it
						InterfaceWrapperHelper.setTrxName(hu, localTrxName);
						try
						{
							huNodeIterator.iterate(hu);
						}
						finally
						{
							// Restore HU's initial transaction
							InterfaceWrapperHelper.setTrxName(hu, huTrxName);
						}
					}
				}
				finally
				{
					// restore initial context
					setHUContext(huContext);
				}
			}
		});

		//
		// If after running everything the status is still running, switch it to finished
		if (getStatus() == HUIteratorStatus.Running)
		{
			setStatus(HUIteratorStatus.Finished);
		}

		return this;
	}
}
