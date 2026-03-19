package de.metas.ui.web.material.cockpit;

import com.google.common.collect.ImmutableList;
import de.metas.i18n.ITranslatableString;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import de.metas.material.cockpit.model.I_MD_Stock;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.material.cockpit.process.MD_Cockpit_DocumentDetail_Display;
import de.metas.ui.web.process.view.ViewActionDescriptorsFactory;
import de.metas.ui.web.process.view.ViewActionDescriptorsList;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.template.AbstractCustomView;
import de.metas.ui.web.view.template.IRowsData;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.model.DocumentQueryOrderBy;
import de.metas.ui.web.window.model.DocumentQueryOrderByList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import org.adempiere.util.lang.impl.TableRecordReference;

import java.util.List;



public class MaterialCockpitView extends AbstractCustomView<MaterialCockpitRow>
{
	public static MaterialCockpitView cast(final IView view)
	{
		return (MaterialCockpitView)view;
	}

	private final DocumentFilterList filters;

	private final List<RelatedProcessDescriptor> relatedProcessDescriptors;

	@Builder
	private MaterialCockpitView(
			@NonNull final ViewId viewId,
			@NonNull final ITranslatableString description,
			@NonNull final IRowsData<MaterialCockpitRow> rowsData,
			@NonNull final DocumentFilterList filters,
			@NonNull final DocumentFilterDescriptorsProvider filterDescriptors,
			@Singular final List<RelatedProcessDescriptor> relatedProcessDescriptors)
	{
		super(viewId,
			  description,
			  rowsData,
			  filterDescriptors);

		this.filters = filters;
		this.relatedProcessDescriptors = ImmutableList.copyOf(relatedProcessDescriptors);
	}

	/**
	 * @return {@code null}, because each record of this view is based on > 1 tables.
	 */
	@Override
	public String getTableNameOrNull(final DocumentId documentId)
	{
		return null;
	}

	@Override
	public DocumentFilterList getFilters()
	{
		return filters;
	}

	@Override
	public DocumentQueryOrderByList getDefaultOrderBys()
	{
		return DocumentQueryOrderByList.ofList(
				ImmutableList.of(
						DocumentQueryOrderBy.byFieldName(I_MD_Cockpit.COLUMNNAME_QtyStockEstimateSeqNo_AtDate),
						DocumentQueryOrderBy.byFieldName(I_MD_Cockpit.COLUMNNAME_ProductValue))
		);
	}

	@Override
	protected boolean isEligibleInvalidateEvent(final TableRecordReference recordRef)
	{
		final String tableName = recordRef.getTableName();
		return I_MD_Cockpit.Table_Name.equals(tableName)
				|| I_MD_Stock.Table_Name.equals(tableName);
	}

	@Override
	public List<RelatedProcessDescriptor> getAdditionalRelatedProcessDescriptors()
	{
		return relatedProcessDescriptors;
	}

	@Override
	public ViewActionDescriptorsList getActions()
	{
		return ViewActionDescriptorsFactory.instance
				.getFromClass(MD_Cockpit_DocumentDetail_Display.class);
	}

}
