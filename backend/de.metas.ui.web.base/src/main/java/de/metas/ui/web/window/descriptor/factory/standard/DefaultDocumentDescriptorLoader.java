package de.metas.ui.web.window.descriptor.factory.standard;

import com.google.common.base.Stopwatch;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.dataentry.window.descriptor.factory.DataEntrySubTabBindingDescriptorBuilder;
import de.metas.ui.web.dataentry.window.descriptor.factory.DataEntryTabLoader;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.ui.web.window.descriptor.DocumentDescriptor;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutDetailDescriptor;
import de.metas.ui.web.window.exceptions.DocumentLayoutBuildException;
import de.metas.util.Check;
import lombok.NonNull;
import org.adempiere.ad.element.api.AdWindowId;
import org.compiere.model.GridTabVO;
import org.compiere.model.GridWindowVO;
import org.slf4j.Logger;

import java.util.List;

  
/* package */ class DefaultDocumentDescriptorLoader
{
	private static final Logger logger = LogManager.getLogger(DefaultDocumentDescriptorLoader.class);

	//
	// Parameters
	private final AdWindowId adWindowId;

	private final DataEntrySubTabBindingDescriptorBuilder dataEntrySubTabBindingDescriptorBuilder;

	//
	// Status
	private boolean _executed = false;

	/* package */ DefaultDocumentDescriptorLoader(
			final AdWindowId adWindowId,
			@NonNull final DataEntrySubTabBindingDescriptorBuilder dataEntrySubTabBindingDescriptorBuilder)
	{
		this.adWindowId = adWindowId;
		this.dataEntrySubTabBindingDescriptorBuilder = dataEntrySubTabBindingDescriptorBuilder;
	}

	public DocumentDescriptor load()
	{
		// Mark as executed
		if (_executed)
		{
			throw new IllegalStateException("Already executed");
		}
		_executed = true;

		if (adWindowId == null)
		{
			throw new DocumentLayoutBuildException("No window found for AD_Window_ID=" + adWindowId);
		}

		final Stopwatch stopwatch = Stopwatch.createStarted();

		final GridWindowVO gridWindowVO = DocumentLoaderUtil.createGridWindoVO(adWindowId);
		Check.assumeNotNull(gridWindowVO, "Parameter gridWindowVO is not null"); // shall never happen

		final DocumentDescriptor.Builder documentBuilder = DocumentDescriptor.builder();

		final DocumentLayoutDescriptor.Builder layoutBuilder = DocumentLayoutDescriptor.builder()
				.setWindowDocumentTypeId(WindowDocumentTypeId.of(gridWindowVO.getAdWindowId()))
				.setStopwatch(stopwatch)
				.putDebugProperty("generator-name", toString());

		//
		// Layout: Create UI sections from main tab
		final GridTabVO mainTabVO = gridWindowVO.getTab(GridTabVO.MAIN_TabNo);
		final LayoutFactory rootLayoutFactory = LayoutFactory.ofMainTab(gridWindowVO, mainTabVO);
		{
			final ITranslatableString windowCaption = TranslatableStrings.ofMap(gridWindowVO.getNameTrls(), gridWindowVO.getName());
			layoutBuilder.setCaption(windowCaption);

			layoutBuilder.setSingleRowLayout(rootLayoutFactory.layoutSingleRow());
			layoutBuilder.setGridView(rootLayoutFactory.layoutGridView());
			layoutBuilder.setSideListView(rootLayoutFactory.layoutSideListView());

			// Set special field names
			// IMPORTANT: do this after you created all layouts
			layoutBuilder
					.setDocumentSummaryElement(rootLayoutFactory.createSpecialElement_DocumentSummary())
					.setDocActionElement(rootLayoutFactory.createSpecialElement_DocStatusAndDocAction());
		}

		ADTabLoader.builder()
				.adWindowId(adWindowId)
				.rootLayoutFactory(rootLayoutFactory)
				.layoutBuilder(layoutBuilder)
				.build()
				.load();

		final DataEntryTabLoader dataEntryTabLoader = DataEntryTabLoader
				.builder()
				.adWindowId(adWindowId)
				.windowId(rootLayoutFactory.documentEntity().getWindowId())
				.dataEntrySubTabBindingDescriptorBuilder(dataEntrySubTabBindingDescriptorBuilder)
				.build();
		final List<DocumentLayoutDetailDescriptor> layoutDescriptors = dataEntryTabLoader.loadDocumentLayout();
		for (final DocumentLayoutDetailDescriptor descriptor : layoutDescriptors)
		{
			layoutBuilder.addDetail(descriptor);
		}

		final List<DocumentEntityDescriptor> entityDescriptors = dataEntryTabLoader.loadDocumentEntity();
		for (final DocumentEntityDescriptor descriptor : entityDescriptors)
		{
			rootLayoutFactory.documentEntity().addIncludedEntity(descriptor);
		}

		//
		// Build & return the final descriptor
		final DocumentDescriptor descriptor = documentBuilder
				.setLayout(layoutBuilder.build())
				.setEntityDescriptor(rootLayoutFactory.documentEntity().build())
				.build();
		logger.debug("Descriptor loaded in {}: {}", stopwatch, descriptor);
		return descriptor;
	}

}
