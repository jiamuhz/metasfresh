package de.metas.ui.web.pporder;

import de.metas.ad_reference.ADReferenceService;
import de.metas.handlingunits.reservation.HUReservationService;
import de.metas.ui.web.view.ASIViewRowAttributesProvider;
import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.window.datatypes.WindowId;
import lombok.Builder;
import lombok.NonNull;
import org.adempiere.util.lang.ExtendedMemorizingSupplier;
import org.eevolution.api.PPOrderId;

import javax.annotation.Nullable;

 

public class PPOrderLinesViewDataSupplier
{
	private final ASIViewRowAttributesProvider asiAttributesProvider;
	private final ExtendedMemorizingSupplier<PPOrderLinesViewData> dataSupplier;

	@Builder
	private PPOrderLinesViewDataSupplier(
			@NonNull final WindowId viewWindowId,
			@NonNull final PPOrderId ppOrderId,
			@Nullable final ASIViewRowAttributesProvider asiAttributesProvider,
			@NonNull final SqlViewBinding huSQLViewBinding,
			@NonNull final HUReservationService huReservationService,
			@NonNull final ADReferenceService adReferenceService,
			final boolean serialNoFromSequence)
	{
		this.asiAttributesProvider = asiAttributesProvider;
		dataSupplier = ExtendedMemorizingSupplier
				.of(() -> PPOrderLinesViewDataLoader
						.builder(viewWindowId)
						.asiAttributesProvider(asiAttributesProvider)
						.serialNoFromSequence(serialNoFromSequence)
						.huSQLViewBinding(huSQLViewBinding)
						.huReservationService(huReservationService)
						.adReferenceService(adReferenceService)
						.build()
						.retrieveData(ppOrderId));
	}

	public PPOrderLinesViewData getData()
	{
		return dataSupplier.get();
	}

	public void invalidate()
	{
		dataSupplier.forget();
		if (asiAttributesProvider != null)
		{
			asiAttributesProvider.invalidateAll();
		}
	}
}
