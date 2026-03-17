package de.metas.acct.interceptor;

/** */

import com.google.common.annotations.VisibleForTesting;
import de.metas.acct.api.AccountDimension;
import de.metas.acct.api.AcctSchema;
import de.metas.acct.api.ChartOfAccountsId;
import de.metas.acct.api.IAccountDAO;
import de.metas.acct.api.IAcctSchemaDAO;
import de.metas.elementvalue.ElementValue;
import de.metas.elementvalue.ElementValueRepository;
import de.metas.organization.OrgId;
import de.metas.treenode.TreeNodeService;
import lombok.NonNull;
import org.adempiere.ad.modelvalidator.annotations.Interceptor;
import org.adempiere.ad.modelvalidator.annotations.ModelChange;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.model.I_C_ElementValue;
import org.compiere.model.ModelValidator;

import java.util.concurrent.atomic.AtomicBoolean;

@Interceptor(I_C_ElementValue.class)
public class C_ElementValue
{
	private final IAcctSchemaDAO acctSchemasRepo;
	private final IAccountDAO accountDAO;
	private final TreeNodeService treeNodeService;

	private static final AtomicBoolean updateTreeNodeDisabled = new AtomicBoolean(false);

	public C_ElementValue(
			@NonNull final IAcctSchemaDAO acctSchemasRepo,
			@NonNull final IAccountDAO accountDAO,
			@NonNull final TreeNodeService treeNodeService)
	{
		this.acctSchemasRepo = acctSchemasRepo;
		this.accountDAO = accountDAO;
		this.treeNodeService = treeNodeService;
	}

	@ModelChange(timings = { ModelValidator.TYPE_BEFORE_NEW, ModelValidator.TYPE_BEFORE_CHANGE })
	public void beforeSave(final I_C_ElementValue elementValue)
	{
		validate(elementValue);
	}

	private void validate(final I_C_ElementValue elementValue)
	{
		if (elementValue.isAutoTaxAccount() && elementValue.getC_Tax_ID() <= 0)
		{
			throw new FillMandatoryException(I_C_ElementValue.COLUMNNAME_C_Tax_ID);
		}
	}

	@ModelChange(timings = { ModelValidator.TYPE_AFTER_NEW, ModelValidator.TYPE_AFTER_CHANGE })
	public void afterSave(final I_C_ElementValue elementValue)
	{
		createValidCombinationIfNeeded(elementValue);
	}

	@VisibleForTesting
	protected void createValidCombinationIfNeeded(final I_C_ElementValue elementValue)
	{
		if (elementValue.isSummary())
		{
			return;
		}

		final AccountDimension.Builder accountDimensionTemplate = AccountDimension.builder()
				//.setAcctSchemaId(acctSchema.getId())
				.setC_ElementValue_ID(elementValue.getC_ElementValue_ID())
				.setAD_Client_ID(elementValue.getAD_Client_ID())
				.setAD_Org_ID(OrgId.ANY.getRepoId());

		final ChartOfAccountsId chartOfAccountsId = ChartOfAccountsId.ofRepoId(elementValue.getC_Element_ID());
		for (final AcctSchema acctSchema : acctSchemasRepo.getByChartOfAccountsId(chartOfAccountsId))
		{
			accountDAO.getOrCreate(accountDimensionTemplate.setAcctSchemaId(acctSchema.getId()).build());
		}
	}

	public static IAutoCloseable temporaryDisableUpdateTreeNode()
	{
		final boolean wasDisabled = updateTreeNodeDisabled.getAndSet(true);
		return () -> updateTreeNodeDisabled.set(wasDisabled);
	}

	@ModelChange(timings = { ModelValidator.TYPE_AFTER_CHANGE }, ifColumnsChanged = { I_C_ElementValue.COLUMNNAME_Parent_ID, I_C_ElementValue.COLUMNNAME_SeqNo })
	public void updateTreeNode(final I_C_ElementValue record)
	{
		if (updateTreeNodeDisabled.get())
		{
			return;
		}

		final ElementValue elementValue = ElementValueRepository.fromRecord(record);
		treeNodeService.updateTreeNode(elementValue);
	}
}
