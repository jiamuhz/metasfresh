package de.metas.ui.web.window.descriptor.factory.standard;

import org.adempiere.ad.element.api.AdWindowId;
import org.compiere.model.GridTabVO;
import org.compiere.model.GridWindowVO;

import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.WindowDocumentLayoutDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutDetailDescriptor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;


@Value
@Builder
public class ADTabLoader
{
	AdWindowId adWindowId;

	@NonNull
	LayoutFactory rootLayoutFactory;

	@NonNull
	WindowDocumentLayoutDescriptor.Builder layoutBuilder;

	public void load()
	{
		final GridWindowVO gridWindowVO = DocumentLoaderUtil.createGridWindoVO(adWindowId);
		//
		// Layout: Create UI details from child tabs
		for (final GridTabVO detailTabVO : gridWindowVO.getChildTabs(GridTabVO.MAIN_TabNo))
		{
			// Skip sort tabs because they are not supported
			if (detailTabVO.IsSortTab)
			{
				continue;
			}

			// Skip tabs which were already used/embedded in root layout
			if (rootLayoutFactory.isSkipAD_Tab_ID(detailTabVO.getAdTabId()))
			{
				continue;
			}

			final GridTabVO mainTabVO = gridWindowVO.getTab(GridTabVO.MAIN_TabNo);

			final LayoutFactory detailLayoutFactory = LayoutFactory.ofIncludedTab(gridWindowVO, mainTabVO, detailTabVO);

			detailLayoutFactory
					.layoutDetail() // might be empty
					.map(DocumentLayoutDetailDescriptor.Builder::build)
					.ifPresent(layoutBuilder::addDetail);

			final DocumentEntityDescriptor.Builder detailEntityBuilder = detailLayoutFactory.documentEntity();
			rootLayoutFactory.documentEntity().addIncludedEntity(detailEntityBuilder.build());
		}
	}
}
