 

package de.metas.ui.web.payment_allocation;

import de.metas.i18n.IMsgBL;
import de.metas.ui.web.view.CreateViewRequest;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.IViewFactory;
import de.metas.ui.web.view.IViewsStorage4GivenWindow;
import de.metas.ui.web.view.IViewsRepository;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewFactory;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.service.ISysConfigBL;

import javax.annotation.Nullable;
import java.util.stream.Stream;

@ViewFactory(windowId = InvoicesViewFactory.WINDOW_ID_String)
public class InvoicesViewFactory implements IViewFactory, IViewsStorage4GivenWindow
{
	static final String WINDOW_ID_String = "invoicesToAllocate"; // FIXME: HARDCODED
	public static final WindowDocumentTypeId WINDOW_ID = WindowDocumentTypeId.fromJson(WINDOW_ID_String);

	public static final String SYSCONFIG_EnablePreparedForAllocationFlag = "de.metas.ui.web.payment_allocation.EnablePreparedForAllocationFlag";

	private final IMsgBL msgBL = Services.get(IMsgBL.class);
	private final PaymentsViewFactory paymentsViewFactory;

	public InvoicesViewFactory(@NonNull final PaymentsViewFactory paymentsViewFactory)
	{
		this.paymentsViewFactory = paymentsViewFactory;
	}

	public static boolean isEnablePreparedForAllocationFlag()
	{
		final ISysConfigBL sysConfigBL = Services.get(ISysConfigBL.class);
		return sysConfigBL.getBooleanValue(SYSCONFIG_EnablePreparedForAllocationFlag, false);
	}

	@Override
	public void setViewsRepository(@NonNull final IViewsRepository viewsRepository)
	{
		// nothing
	}

	@Override
	public ViewLayout getViewLayout(final WindowDocumentTypeId windowId, final JSONViewDataType viewDataType, final ViewProfileId profileId)
	{
		final ViewLayout.Builder layoutBuilder = ViewLayout.builder()
				.setWindowId(WINDOW_ID)
				.setCaption(msgBL.translatable("InvoicesToAllocate"))
				.setAllowOpeningRowDetails(false)
				.addElementsFromViewRowClass(InvoiceRow.class, viewDataType);

		if (!isEnablePreparedForAllocationFlag())
		{
			layoutBuilder.removeElementByFieldName(InvoiceRow.FIELD_IsPreparedForAllocation);
		}

		return layoutBuilder.build();
	}

	@Override
	public InvoicesView createView(final @NonNull CreateViewRequest request)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public WindowDocumentTypeId getWindowId()
	{
		return WINDOW_ID;
	}

	@Override
	public void put(final IView view)
	{
		throw new UnsupportedOperationException();
	}

	@Nullable
	@Override
	public InvoicesView getByIdOrNull(final ViewId invoicesViewId)
	{
		final ViewId paymentsViewId = toPaymentsViewId(invoicesViewId);
		final PaymentsView paymentsView = paymentsViewFactory.getByIdOrNull(paymentsViewId);
		return paymentsView != null
				? paymentsView.getInvoicesView()
				: null;
	}

	private static ViewId toPaymentsViewId(final ViewId invoicesViewId)
	{
		return invoicesViewId.withWindowId(PaymentsViewFactory.WINDOW_ID);
	}

	@Override
	public void closeById(final ViewId viewId, final ViewCloseAction closeAction)
	{
	}

	@Override
	public Stream<IView> streamAllViews()
	{
		return paymentsViewFactory.streamAllViews()
				.map(PaymentsView::cast)
				.map(PaymentsView::getInvoicesView);
	}

	@Override
	public void invalidateView(final ViewId invoicesViewId)
	{
		final InvoicesView invoicesView = getByIdOrNull(invoicesViewId);
		if (invoicesView != null)
		{
			invoicesView.invalidateAll();
		}
	}
}
