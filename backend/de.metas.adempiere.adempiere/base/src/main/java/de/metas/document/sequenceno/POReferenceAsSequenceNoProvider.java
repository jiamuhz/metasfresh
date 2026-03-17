package de.metas.document.sequenceno;

import de.metas.document.DocumentSequenceInfo;
import de.metas.logging.LogManager;
import de.metas.util.Check;
import lombok.NonNull;
import org.compiere.util.Evaluatee;
import org.slf4j.Logger;

import java.util.function.Supplier;

/** */

public class POReferenceAsSequenceNoProvider implements CustomSequenceNoProvider
{
	private static final Logger logger = LogManager.getLogger(POReferenceAsSequenceNoProvider.class);

	private static final String PARAM_POReference = "POReference";

	@Override
	public @NonNull String provideSeqNo(
			@NonNull final Supplier<String> incrementalSeqNoSupplier,
			@NonNull final Evaluatee evaluatee,
			@NonNull final DocumentSequenceInfo documentSequenceInfo)
	{
		return provideSequenceNo(evaluatee) + "-" + incrementalSeqNoSupplier.get();
	}

	/** @return {@code true} if the given {@code context} has a non-null {@code POReference} value. */
	@Override
	public boolean isApplicable(@NonNull final Evaluatee context, @NonNull final DocumentSequenceInfo docSeqInfo)
	{
		final String poReference = getPOReferenceOrNull(context);
		final boolean result = Check.isNotBlank(poReference);
		logger.debug("isApplicable - Given evaluatee-context contains {}={}; -> returning {}; context={}", PARAM_POReference, poReference, result, context);

		return result;
	}

	private static String getPOReferenceOrNull(@NonNull final Evaluatee context)
	{
		String poReference = context.get_ValueAsString(PARAM_POReference);
		if (poReference == null)
		{
			return null;
		}

		poReference = poReference.trim();
		return !poReference.isEmpty() ? poReference : null;
	}

	/** @return the given {@code context}'s {@code POReference} value. */
	private static String provideSequenceNo(@NonNull final Evaluatee context)
	{
		final String poReference = getPOReferenceOrNull(context);
		Check.assumeNotNull(poReference, "The given context needs to have a non-empty POreference value; context={}", context);

		logger.debug("provideSequenceNo - returning {};", poReference);
		return poReference;
	}
}
