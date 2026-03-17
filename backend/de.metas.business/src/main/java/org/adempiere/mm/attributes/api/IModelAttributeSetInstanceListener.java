package org.adempiere.mm.attributes.api;

/** */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.adempiere.ad.modelvalidator.ModelChangeType;
import org.adempiere.ad.persistence.ModelDynAttributeAccessor;

/**
 * Listens on model (which is also {@link IAttributeSetInstanceAware}).<br>
 * Use {@link IModelAttributeSetInstanceListenerService#registerListener(IModelAttributeSetInstanceListener)} to register your own listener.
 *
 * @author tsa
 *
 */
public interface IModelAttributeSetInstanceListener
{
	/**
	 * Flag used to specify that we don't want automatic ASI update when a model is changed.
	 *
	 * Example use case: you want to create an order line on which you precisely set the ASI and don't want to be changed.
	 */
	ModelDynAttributeAccessor<Object, Boolean> DYNATTR_DisableASIUpdateOnModelChange = new ModelDynAttributeAccessor<>(
			IModelAttributeSetInstanceListener.class.getName() + "#DisableASIUpdateOnModelChange",
			Boolean.class);

	/**
	 * {@link #getSourceColumnNames()} can return this constant to indicate that it does not matter which particular column was changed.
	 */
	List<String> ANY_SOURCE_COLUMN = Collections.unmodifiableList(new ArrayList<String>());

	/**
	 * @return Listened model's Table_Name
	 */
	String getSourceTableName();

	/**
	 * @return listened table's column names; also see {@link #modelChanged(Object)}.
	 */
	List<String> getSourceColumnNames();

	/**
	 * Called by API if the listener model (which is also an {@link IAttributeSetInstanceAware}) was changed and the the model validation engine was fired with
	 * <ul>
	 * <li>{@link ModelChangeType#BEFORE_NEW}
	 * <li>{@link ModelChangeType#BEFORE_CHANGE} and at least one of the changed columns is one of those returned by {@link #getSourceColumnNames()}.
	 * </ul>
	 *
	 * @param model
	 */
	void modelChanged(Object model);
}
