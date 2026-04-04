package de.metas.ui.web.picking;

import static org.assertj.core.api.Assertions.assertThat;

import de.metas.ui.web.window.model.lookup.LookupDataSourceFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.metas.handlingunits.picking.PickingCandidateService;
import de.metas.ui.web.picking.packageable.PackageableRowsRepository;
import de.metas.ui.web.picking.packageable.PackageableViewFactory;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.json.JSONViewDataType;

 

public class PickingViewFactoryTests
{
	/**
	 * Verifies that {@link PackageableViewFactory#createView(de.metas.ui.web.view.CreateViewRequest)} still works,<br>
	 * because when adding certain stuff one might break the builder.
	 */
	@Test
	public void testCreateView()
	{
		final PackageableRowsRepository pickingViewRepo = new PackageableRowsRepository(LookupDataSourceFactory.sharedInstance());
		final PickingCandidateService pickingCandidateService = Mockito.mock(PickingCandidateService.class);

		final PackageableViewFactory pickingViewFactory = new PackageableViewFactory(pickingViewRepo, pickingCandidateService);
		final IView view = pickingViewFactory.createView(CreateViewRequest.builder(PickingConstants.WINDOWID_PickingView, JSONViewDataType.grid).build());
		assertThat(view).isNotNull();
	}
}
