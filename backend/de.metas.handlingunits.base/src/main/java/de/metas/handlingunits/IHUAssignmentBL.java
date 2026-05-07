package de.metas.handlingunits;

import com.google.common.collect.ImmutableSetMultimap;
import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_Assignment;
import de.metas.util.ISingletonService;
import lombok.NonNull;
import org.adempiere.util.lang.IReference;
import org.adempiere.util.lang.impl.TableRecordReference;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public interface IHUAssignmentBL extends ISingletonService
{
	/**
	 * Register an {@link IHUAssignmentListener}.
	 */
	void registerHUAssignmentListener(IHUAssignmentListener listener);

	/**
	 * Gets current {@link IHUAssignmentListener}s.
	 * <p>
	 * NOTE: don't use it directly, it's used by API
	 *
	 * @return composite {@link IHUAssignmentListener}s
	 */
	IHUAssignmentListener getHUAssignmentListeners();

	/**
	 * Assign given HUs <b>as top level HUs</b>. This means, create {@link I_M_HU_Assignment} records and set their {@code M_HU_ID} to the given HUs' IDs.
	 * <p>
	 * NOTE: model's trxName will be used.
	 */
	void assignHUs(Object model, Collection<I_M_HU> huList);

	/**
	 * See {@link #assignHUs(Object, Collection)}.
	 */
	void assignHUs(Object model, Collection<I_M_HU> huList, final String trxName);

	/**
	 * Assign given HU <b>as top level HU</b>.
	 *
	 * @return created/updated {@link I_M_HU_Assignment}.
	 */
	I_M_HU_Assignment assignHU(Object model, I_M_HU hu, final String trxName);

	/**
	 * Assign given HU <b>as top level HU</b>.
	 */
	I_M_HU_Assignment assignHU(Object model, I_M_HU hu, boolean isTransferPackingMaterials, String trxName);

	/**
	 * Create handling unit assignment builder for given loading / trading unit(s) of the top-level HU
	 */
	IHUAssignmentBuilder createTradingUnitDerivedAssignmentBuilder(Properties ctx, Object model, I_M_HU topLevelHU, I_M_HU luHU, I_M_HU tuHU, String trxName);

	/**
	 * Deletes existing links between a specific <code>model</code> and it's existing handling units.
	 */
	void setAssignedHandlingUnits(Object model, Collection<I_M_HU> handlingUnits);

	/**
	 * Unassign all HUs which are currently assigned to given <code>model</code>.
	 */
	void unassignAllHUs(Object model, String trxName);

	/**
	 * Unassign all HUs which are currently assigned to given <code>model</code>.
	 * <p>
	 * Model's transaction will be used.
	 */
	void unassignAllHUs(Object model);

	/**
	 * Unassigns the given <code>hus</code> from the given <code>model</code> by deleting the respective {@link I_M_HU_Assignment} records and then calls
	 * {@link IHUAssignmentListener#onHUUnassigned(IReference, IReference, String)}  for all registered listeners. Note that for HUs with <code>M_HU_ID <= 0</code> no unassignment is attempted, and the
	 * listeners are not notified.
	 */
	void unassignHUs(TableRecordReference modelRef, Collection<HuId> husToUnassign);

	void addAssignedHandlingUnits(Object model, Collection<I_M_HU> handlingUnits);

	/**
	 * Unassign given HUs.
	 */
	void unassignHUs(Object model, Collection<HuId> husToUnassign, String trxName);

	/**
	 * Create an {@link IHUAssignmentBuilder} to easily decorate and create new {@link I_M_HU_Assignment}s.
	 *
	 * @return builder
	 */
	IHUAssignmentBuilder createHUAssignmentBuilder();

	void copyHUAssignments(Object sourceModel, Object targetModel);

	ImmutableSetMultimap<TableRecordReference, HuId> getHUsByRecordRefs(@NonNull Set<TableRecordReference> recordRefs);
}
