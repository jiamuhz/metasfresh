package de.metas.ui.web.pickingslotsClearing;

import de.metas.handlingunits.HuId;
import de.metas.ui.web.handlingunits.HUEditorView;
import de.metas.ui.web.pickingslotsClearing.process.HUExtractedFromPickingSlotEvent;
import lombok.NonNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;



/* package */final class PackingHUsViewsCollection
{
	private final ConcurrentHashMap<PackingHUsViewKey, HUEditorView> packingHUsViewsByKey = new ConcurrentHashMap<>();

	public Optional<HUEditorView> getByKeyIfExists(@NonNull final PackingHUsViewKey key)
	{
		return Optional.ofNullable(packingHUsViewsByKey.get(key));
	}

	@FunctionalInterface
	static interface PackingHUsViewSupplier
	{
		HUEditorView createPackingHUsView(PackingHUsViewKey key);
	}

	public HUEditorView computeIfAbsent(@NonNull final PackingHUsViewKey key, @NonNull final PackingHUsViewSupplier packingHUsViewFactory)
	{
		return packingHUsViewsByKey.computeIfAbsent(key, packingHUsViewFactory::createPackingHUsView);
	}

	public void put(@NonNull final PackingHUsViewKey key, @NonNull final HUEditorView packingHUsView)
	{
		packingHUsViewsByKey.put(key, packingHUsView);
	}

	public Optional<HUEditorView> removeIfExists(@NonNull final PackingHUsViewKey key)
	{
		final HUEditorView packingHUsViewRemoved = packingHUsViewsByKey.remove(key);
		return Optional.ofNullable(packingHUsViewRemoved);
	}

	public void handleEvent(@NonNull final HUExtractedFromPickingSlotEvent event)
	{
		packingHUsViewsByKey.entrySet()
				.stream()
				.filter(entry -> isEventMatchingKey(event, entry.getKey()))
				.map(entry -> entry.getValue())
				.forEach(packingHUsView -> packingHUsView.addHUIdAndInvalidate(HuId.ofRepoId(event.getHuId())));
	}

	private static final boolean isEventMatchingKey(final HUExtractedFromPickingSlotEvent event, final PackingHUsViewKey key)
	{
		return key.isBPartnerIdMatching(event.getBpartnerId())
				&& key.isBPartnerLocationIdMatching(event.getBpartnerLocationId());
	}
}
