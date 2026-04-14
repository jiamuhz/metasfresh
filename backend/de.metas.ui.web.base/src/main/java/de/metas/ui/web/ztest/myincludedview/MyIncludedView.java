package de.metas.ui.web.ztest.myincludedview;

import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.inoutcandidate.model.I_M_Packageable_V;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.window.datatypes.DocumentId;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.List;


/**
 *
 */
public class MyIncludedView extends AbstractCustomView<MyIncludedViewRow>
{
	public static MyIncludedView cast(final IView view)
	{
		return (MyIncludedView)view;
	}

	private final List<RelatedProcessDescriptor> additionalRelatedProcessDescriptors;

	@Builder
	private MyIncludedView(
		@NonNull final ViewId viewId,
		@Nullable final ITranslatableString description,
		@NonNull final MyIncludedViewRowsData rowsData,
		@Nullable final List<RelatedProcessDescriptor> additionalRelatedProcessDescriptors
	)
	{
		super(viewId, description, rowsData, NullDocumentFilterDescriptorsProvider.instance);
    this.additionalRelatedProcessDescriptors = additionalRelatedProcessDescriptors;
  }

	@Override
	protected MyIncludedViewRowsData getRowsData()
	{
		return MyIncludedViewRowsData.cast(super.getRowsData());
	}

	/**
	 * @return {@link I_M_Packageable_V#Table_Name}.
	 */
	@Override
	public String getTableNameOrNull(@Nullable final DocumentId ignored)
	{
		return I_M_Packageable_V.Table_Name;
	}

	@Override
	public void close(final ViewCloseAction action)
	{
		if (action.isDone())
		{
			//closePickingCandidatesFromRackSystemPickingSlots();
		}
	}

	@Override
	public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors()
	{
		return additionalRelatedProcessDescriptors;
	}
}
