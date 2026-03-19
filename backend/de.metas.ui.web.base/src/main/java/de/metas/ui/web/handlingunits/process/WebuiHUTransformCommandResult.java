package de.metas.ui.web.handlingunits.process;

import com.google.common.collect.ImmutableSet;

import de.metas.handlingunits.HuId;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;



/**
 * The result of {@link WebuiHUTransformCommand#execute()}.
 *
 *
 */
@Value
@Builder
public class WebuiHUTransformCommandResult
{
	@Singular("huIdToAddToView")
	private final ImmutableSet<HuId> huIdsToAddToView;

	@Singular("huIdToRemoveFromView")
	private final ImmutableSet<HuId> huIdsToRemoveFromView;

	@Singular("huIdChanged")
	private final ImmutableSet<HuId> huIdsChanged;

	@Singular("huIdCreated")
	private final ImmutableSet<HuId> huIdsCreated;

	private final boolean fullViewInvalidation;
}
