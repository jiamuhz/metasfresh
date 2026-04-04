 

package de.metas.ui.web.payment_allocation;

import de.metas.currency.Amount;
import de.metas.currency.CurrencyCode;
import de.metas.ui.web.payment_allocation.InvoiceRow.InvoiceRowBuilder;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceRowReducers
{
	public static InvoiceRow reduce(
			@NonNull final InvoiceRow row,
			@NonNull final List<JSONDocumentChangedEvent> fieldChangeRequests)
	{
		final InvoiceRowBuilder rowBuilder = row.toBuilder();

		for (final JSONDocumentChangedEvent fieldChangeRequest : fieldChangeRequests)
		{
			fieldChangeRequest.assertReplaceOperation();
			final String fieldName = fieldChangeRequest.getPath();
			if (InvoiceRow.FIELD_DiscountAmt.contentEquals(fieldName))
			{
				final BigDecimal discountAmtBD = fieldChangeRequest.getValueAsBigDecimal(BigDecimal.ZERO);

				final CurrencyCode currencyCode = row.getDiscountAmt().getCurrencyCode();
				final Amount discountAmt = Amount.of(discountAmtBD, currencyCode);
				rowBuilder.discountAmt(discountAmt);
			}
			else if (InvoiceRow.FIELD_ServiceFeeAmt.contentEquals(fieldName))
			{
				final BigDecimal serviceFeeAmtBD = fieldChangeRequest.getValueAsBigDecimal(BigDecimal.ZERO);

				final CurrencyCode currencyCode = row.getCurrencyCode();
				final Amount serviceFeeAmt = Amount.of(serviceFeeAmtBD, currencyCode);
				rowBuilder.serviceFeeAmt(serviceFeeAmt);
			}
			else if (InvoiceRow.FIELD_BankFeeAmt.contentEquals(fieldName))
			{
				final BigDecimal bankFeeAmtBD = fieldChangeRequest.getValueAsBigDecimal(BigDecimal.ZERO);

				final CurrencyCode currencyCode = row.getCurrencyCode();
				final Amount bankFeeAmt = Amount.of(bankFeeAmtBD, currencyCode);
				rowBuilder.bankFeeAmt(bankFeeAmt);
			}
			else
			{
				throw new AdempiereException("Changing " + fieldName + " is not allowed");
			}
		}

		return rowBuilder.build();
	}
}
