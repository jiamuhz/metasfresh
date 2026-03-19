package de.metas.ui.web.process;

import com.google.common.collect.ImmutableList;
import de.metas.process.IProcessPreconditionsContext;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;

import javax.annotation.Nullable;
import java.util.List;

 

public interface WebuiProcessPreconditionsContext extends IProcessPreconditionsContext
{
	@Nullable
	DisplayPlace getDisplayPlace();

	default boolean isConsiderTableRelatedProcessDescriptors() { return true; }

	default List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors()
	{
		return ImmutableList.of();
	}
}
