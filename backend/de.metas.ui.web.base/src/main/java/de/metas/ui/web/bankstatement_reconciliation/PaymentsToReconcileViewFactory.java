

package de.metas.ui.web.bankstatement_reconciliation;

import java.util.stream.Stream;

import de.metas.i18n.TranslatableStrings;
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
import de.metas.util.Check;
import lombok.NonNull;

import javax.annotation.Nullable;

@ViewFactory(windowId = PaymentsToReconcileViewFactory.WINDOW_ID_String)
public class PaymentsToReconcileViewFactory implements IViewFactory, IViewsStorage4GivenWindow
{
	static final String WINDOW_ID_String = "paymentsToReconcile";
	public static final WindowDocumentTypeId WINDOW_ID = WindowDocumentTypeId.fromJson(WINDOW_ID_String);

	private final BankStatementReconciliationViewFactory banksStatementReconciliationViewFactory;

	public PaymentsToReconcileViewFactory(
			@NonNull final BankStatementReconciliationViewFactory banksStatementReconciliationViewFactory)
	{
		this.banksStatementReconciliationViewFactory = banksStatementReconciliationViewFactory;
	}

	@Override
	public void setViewsRepository(@NonNull final IViewsRepository viewsRepository)
	{
		// nothing
	}

	@Override
	public IView createView(final @NonNull CreateViewRequest request)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ViewLayout getViewLayout(
			final WindowDocumentTypeId windowId,
			final JSONViewDataType viewDataType,
			final ViewProfileId profileId)
	{
		Check.assumeEquals(windowId, WINDOW_ID, "windowId");

		return ViewLayout.builder()
				.setWindowId(WINDOW_ID)
				.setCaption(TranslatableStrings.empty())
				.setAllowOpeningRowDetails(false)
				.addElementsFromViewRowClass(PaymentToReconcileRow.class, viewDataType)
				.build();
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
	public PaymentsToReconcileView getByIdOrNull(@NonNull final ViewId paymentsToReconcileViewId)
	{
		final ViewId bankStatementReconciliationViewId = toBankStatementReconciliationViewId(paymentsToReconcileViewId);
		final BankStatementReconciliationView bankStatementReconciliationView = banksStatementReconciliationViewFactory.getByIdOrNull(bankStatementReconciliationViewId);
		return bankStatementReconciliationView != null
				? bankStatementReconciliationView.getPaymentsToReconcileView()
				: null;
	}

	private static ViewId toBankStatementReconciliationViewId(@NonNull final ViewId paymentsToReconcileViewId)
	{
		return paymentsToReconcileViewId.withWindowId(BankStatementReconciliationViewFactory.WINDOW_ID);
	}

	@Override
	public void closeById(final ViewId viewId, final ViewCloseAction closeAction)
	{
		// nothing
	}

	@Override
	public Stream<IView> streamAllViews()
	{
		return banksStatementReconciliationViewFactory.streamAllViews()
				.map(BankStatementReconciliationView::cast)
				.map(BankStatementReconciliationView::getPaymentsToReconcileView);
	}

	@Override
	public void invalidateView(final ViewId paymentsToReconcileViewId)
	{
		final PaymentsToReconcileView paymentsToReconcileView = getByIdOrNull(paymentsToReconcileViewId);
		if (paymentsToReconcileView != null)
		{
			paymentsToReconcileView.invalidateAll();
		}
	}
}
