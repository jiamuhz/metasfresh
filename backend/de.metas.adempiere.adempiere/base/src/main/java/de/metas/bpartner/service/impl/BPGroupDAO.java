package de.metas.bpartner.service.impl;

import de.metas.bpartner.BPGroupId;
import de.metas.bpartner.BPartnerId;
import de.metas.bpartner.name.strategy.BPartnerNameAndGreetingStrategyId;
import de.metas.bpartner.name.strategy.DoNothingBPartnerNameAndGreetingStrategy;
import de.metas.bpartner.service.IBPGroupDAO;
import de.metas.bpartner.service.IBPartnerDAO;
import de.metas.logging.LogManager;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import lombok.NonNull;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.service.ClientId;
import org.compiere.model.I_C_BP_Group;
import org.compiere.model.I_C_BPartner;
import org.slf4j.Logger;

import javax.annotation.Nullable;

import static org.adempiere.model.InterfaceWrapperHelper.load;
import static org.adempiere.model.InterfaceWrapperHelper.loadOutOfTrx;

/** */

public class BPGroupDAO implements IBPGroupDAO
{
	private static final Logger logger = LogManager.getLogger(BPGroupDAO.class);

	@Override
	public I_C_BP_Group getById(@NonNull final BPGroupId bpGroupId)
	{
		return loadOutOfTrx(bpGroupId, I_C_BP_Group.class);
	}

	@Override
	public I_C_BP_Group getByIdInInheritedTrx(@NonNull final BPGroupId bpGroupId)
	{
		return load(bpGroupId, I_C_BP_Group.class);
	}

	@Override
	public I_C_BP_Group getByBPartnerId(@NonNull final BPartnerId bpartnerId)
	{
		final I_C_BPartner bpartnerRecord = Services.get(IBPartnerDAO.class).getById(bpartnerId);
		final BPGroupId bpGroupId = BPGroupId.ofRepoId(bpartnerRecord.getC_BP_Group_ID());
		return getById(bpGroupId);
	}

	@Override
	public BPGroupId getBPGroupByBPartnerId(@NonNull final BPartnerId bpartnerId)
	{
		return BPGroupId.ofRepoId(getByBPartnerId(bpartnerId).getC_BP_Group_ID());
	}

	@Override
	@Nullable
	public I_C_BP_Group getDefaultByClientId(@NonNull final ClientId clientId)
	{
		final BPGroupId bpGroupId = Services.get(IQueryBL.class)
				.createQueryBuilderOutOfTrx(I_C_BP_Group.class)
				.addOnlyActiveRecordsFilter()
				.addEqualsFilter(I_C_BP_Group.COLUMNNAME_AD_Client_ID, clientId)
				.addEqualsFilter(I_C_BP_Group.COLUMNNAME_IsDefault, true)
				.create()
				.firstIdOnly(BPGroupId::ofRepoIdOrNull);
		if (bpGroupId == null)
		{
			logger.warn("No default BP group found for {}", clientId);
			return null;
		}

		return getById(bpGroupId);
	}

	@Override
	public BPartnerNameAndGreetingStrategyId getBPartnerNameAndGreetingStrategyId(@NonNull final BPGroupId bpGroupId)
	{
		final I_C_BP_Group bpGroup = getById(bpGroupId);

		return StringUtils.trimBlankToOptional(bpGroup.getBPNameAndGreetingStrategy())
				.map(BPartnerNameAndGreetingStrategyId::ofString)
				.orElse(DoNothingBPartnerNameAndGreetingStrategy.ID);
	}
}