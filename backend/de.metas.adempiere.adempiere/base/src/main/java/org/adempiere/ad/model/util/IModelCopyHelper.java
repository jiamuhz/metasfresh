package org.adempiere.ad.model.util;

/** */

import org.adempiere.model.InterfaceWrapperHelper;

/**
 * Helper class which assists you to FULLY copy values from a model to another model.
 *
 * If you want to create a new instance of this helper, use {@link InterfaceWrapperHelper#copy()}.
 *
 * 实现者 内含 源Model 和 目标Model, 实现两者之间的 Copy
 *
 */
public interface IModelCopyHelper
{
	/**
	 * Execute copy.
	 *
	 * NOTE: model will not be saved.
	 */
	void copy();

	/**
	 * Execute copy to a new model (not saved)
	 *
	 * @param modelClass
	 */
	<T> T copyToNew(Class<T> modelClass);

	/**
	 * Sets from which model are we copying
	 *
	 * @param fromModel
	 */
	IModelCopyHelper setFrom(final Object fromModel);

	/**
	 * Sets to which model are we copying
	 *
	 * @param toModel
	 */
	IModelCopyHelper setTo(final Object toModel);

	/**
	 * Sets if we shall NOT copy columns which are flagged with IsCalculated.
	 *
	 * @param skipCalculatedColumns
	 */
	IModelCopyHelper setSkipCalculatedColumns(boolean skipCalculatedColumns);

	IModelCopyHelper addTargetColumnNameToSkip(String columnName);

}
