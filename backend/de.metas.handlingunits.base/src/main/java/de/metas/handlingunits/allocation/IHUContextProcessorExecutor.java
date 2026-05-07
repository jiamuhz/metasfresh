package de.metas.handlingunits.allocation;

import java.util.function.Consumer;
import java.util.function.Function;

import org.adempiere.util.lang.Mutable;
import org.compiere.util.TrxRunnable;

import de.metas.handlingunits.IHUContext;
import de.metas.handlingunits.allocation.impl.IMutableAllocationResult;
import de.metas.handlingunits.attribute.IHUTransactionAttributeBuilder;
import de.metas.handlingunits.hutransaction.IHUTransactionAttribute;
import de.metas.handlingunits.hutransaction.IHUTrxBL;
import lombok.NonNull;

/**
 * Executor responsible for running {@link IHUContextProcessor}. Use one of the methods in {@link IHUTrxBL} to get an instance.
 *
 * When you are about to process something related to HUs it is HIGHLY recommended to run your code snippet using this executor.
 *
 * This executor will do
 * <ul>
 * <li>manage database transaction</li>
 * <li>will collect and automatically process {@link IHUTransactionAttribute}s</li>
 * <li>create packing materials/empties movements if needed (see {@link IHUContext#getHUPackingMaterialsCollector()})</li>
 * </ul>
 *
 * @author User
 *
 */
public interface IHUContextProcessorExecutor
{
	/**
	 * Execute the processor and take care of everything (see interface documentation). Run the processor within a {@link TrxRunnable}, but <b>do not</b> commit on successful execution.
	 *
	 * @param processor
	 * @return result or {@link IHUContextProcessor#NULL_RESULT} if the result is not relevant for that processing.
	 */
	IMutableAllocationResult run(IHUContextProcessor processor);

	/**
	 * Execute the processor and take care of everything (see interface documentation). Run the processor within a {@link TrxRunnable}, but <b>do not</b> commit on successful execution.
	 *
	 * @param processor
	 * @see #run(IHUContextProcessor)
	 */
	default void run(@NonNull final Consumer<IHUContext> processor)
	{
		run((IHUContextProcessor)huContext -> {
			processor.accept(huContext);
			return IHUContextProcessor.NULL_RESULT;
		});
	}

	default <T> T call(@NonNull final Function<IHUContext, T> callable)
	{
		final Mutable<T> resultHolder = new Mutable<>();
		run(huContext -> {
			T result = callable.apply(huContext);
			resultHolder.setValue(result);
		});
		return resultHolder.getValue();
	}

	/**
	 * Gets current {@link IHUTransactionAttributeBuilder}.
	 *
	 * NOTE: the {@link IHUTransactionAttributeBuilder} is available only while {@link #run(IHUContextProcessor)} is running.
	 * Not before and not after that.
	 * It shall be accessed only from {@link IHUContextProcessor#process(IHUContext)}.
	 * If you are accessing it outside of that scope, an exception will be thrown.
	 *
	 * @return current {@link IHUTransactionAttributeBuilder}; never return null.
	 */
	IHUTransactionAttributeBuilder getTrxAttributesBuilder();
}
