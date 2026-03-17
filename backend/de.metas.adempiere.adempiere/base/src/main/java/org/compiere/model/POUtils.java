package org.compiere.model;

import java.math.BigDecimal;

import javax.annotation.Nullable;

import org.adempiere.exceptions.AdempiereException;
import org.slf4j.Logger;

import de.metas.error.AdIssueId;
import de.metas.error.IErrorManager;
import de.metas.logging.LogManager;
import de.metas.util.NumberUtils;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import lombok.NonNull;

/** */

public class POUtils
{

	private static final Logger logger = LogManager.getLogger(POUtils.class);

	/**
	 * Removes trailing zero (after the decimal digit) if the given {@code value} is a {@link BigDecimal} with a space bigger than 15.
	 * <p>
	 * Note: does never truncate or round that number, i.e. the result will in every case be equal according to {@link BigDecimal#compareTo(BigDecimal)}.
	 *
	 * @task https://github.com/metasfresh/metasfresh/issues/3914 Avoid numeric values with too many trailing zeros
	 */
	public static Object stripZerosAndLogIssueIfBigDecimalScaleTooBig(
			@Nullable final Object value,
			@NonNull PO po)
	{
		final boolean valueIsNotBigDecimal = !(value instanceof BigDecimal);
		if (valueIsNotBigDecimal)
		{
			return value; // nothing to do
		}

		final int maxAllowedScale = 15;
		final BigDecimal bdValue = (BigDecimal)value;
		if (bdValue.scale() <= maxAllowedScale)
		{
			return bdValue; // nothing to do
		}

		final BigDecimal bpWithoutTrailingZeroes = NumberUtils.stripTrailingDecimalZeros(bdValue);

		final String firstMessagePart = StringUtils.formatMessage(
				"The given value has scale={}; going to proceed with a stripped down value with scale={};",
				bdValue.scale(), bpWithoutTrailingZeroes.scale());
		final String lastMessagePart = StringUtils.formatMessage(" value={}; po={}", bdValue, po);

		final AdIssueId issueId = Services.get(IErrorManager.class).createIssue(new AdempiereException(firstMessagePart + lastMessagePart));
		logger.warn(firstMessagePart + " created AD_Issue_ID={}" + lastMessagePart, issueId);

		return bpWithoutTrailingZeroes;
	}
}
