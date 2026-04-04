 

package de.metas.ui.web.accounting.filters;

import de.metas.i18n.IMsgBL;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsConstants;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProviderFactory;
import de.metas.ui.web.document.filter.provider.ImmutableDocumentFilterDescriptorsProvider;
import de.metas.ui.web.document.filter.provider.NullDocumentFilterDescriptorsProvider;
import de.metas.ui.web.window.descriptor.CreateFiltersProviderContext;
import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Services;
import lombok.NonNull;
import org.compiere.model.I_Fact_Acct;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Collection;

@Component
public class FactAcctFilterDescriptorsProviderFactory implements DocumentFilterDescriptorsProviderFactory
{
	public static final String FACT_ACCT_TRANSACTIONS_VIEW = "Fact_Acct_Transactions_View";
	private static final String FACT_ACCT_TABLE = I_Fact_Acct.Table_Name;
	private final transient IMsgBL msgBL = Services.get(IMsgBL.class);

	public FactAcctFilterDescriptorsProviderFactory()
	{
	}

	@Override
	@NonNull
	public DocumentFilterDescriptorsProvider createFiltersProvider(
			@NonNull final CreateFiltersProviderContext context,
			final @NonNull Collection<DocumentFieldDescriptor> fields)
	{
		if (!isValidTable(context.getTableName()))
		{
			return NullDocumentFilterDescriptorsProvider.instance;
		}

		return ImmutableDocumentFilterDescriptorsProvider.of(
				DocumentFilterDescriptor.builder()
						.setFilterId(FactAcctFilterConverter.FILTER_ID)
						.setSortNo(DocumentFilterDescriptorsConstants.SORT_NO_FACT_ACCT)
						.setFrequentUsed(true)
						.setDisplayName(msgBL.translatable("AccountNumber"))
						//
						.addParameter(DocumentFilterParamDescriptor.builder()
								.mandatory(true)
								.fieldName(FactAcctFilterConverter.PARAM_ACCOUNT_VALUE_FROM)
								.displayName(msgBL.translatable(FactAcctFilterConverter.PARAM_ACCOUNT_VALUE_FROM))
								.widgetType(DocumentFieldWidgetType.Text)
						)
						.addParameter(DocumentFilterParamDescriptor.builder()
								.mandatory(true)
								.fieldName(FactAcctFilterConverter.PARAM_ACCOUNT_VALUE_TO)
								.displayName(msgBL.translatable(FactAcctFilterConverter.PARAM_ACCOUNT_VALUE_TO))
								.widgetType(DocumentFieldWidgetType.Text)
						)
						//
						.build()
		);
	}

	private boolean isValidTable(@Nullable final String tableName)
	{
		return (FACT_ACCT_TRANSACTIONS_VIEW.equals(tableName) || FACT_ACCT_TABLE.equals(tableName));
	}
}
