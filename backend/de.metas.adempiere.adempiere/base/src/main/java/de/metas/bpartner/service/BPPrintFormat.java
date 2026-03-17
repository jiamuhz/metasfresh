/**
 *
 */
package de.metas.bpartner.service;

import de.metas.bpartner.BPartnerId;
import de.metas.util.Check;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

/** */

/**
 *
 *
 */

@Value
@Builder
public class BPPrintFormat
{
	@Getter
	private final BPartnerId bpartnerId;

	@Getter
	private final int docTypeId;

	@Getter
	private final int adTableId;

	@Getter
	private final int printFormatId;

	@Getter
	private final int bpPrintFormatId;

	@Builder(toBuilder = true)
	private BPPrintFormat(@NonNull final BPartnerId bpartnerId,
			final int docTypeId,
			final int adTableId,
			final int printFormatId,
			final int bpPrintFormatId)
	{
		Check.assume(docTypeId > 0, "docTypeId shall be > 0");
		Check.assume(adTableId > 0, "adTableId shall be > 0");
		Check.assume(printFormatId > 0, "printFormatId shall be > 0");

		this.bpartnerId = bpartnerId;
		this.docTypeId = docTypeId;
		this.adTableId = adTableId;
		this.printFormatId = printFormatId;
		this.bpPrintFormatId = bpPrintFormatId;
	}
}
