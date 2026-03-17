package de.metas.acct.api;

import org.compiere.model.I_Fact_Acct;

/** */

/**
 * Listens {@link I_Fact_Acct} events.
 * 
 *
 *
 */
public interface IFactAcctListener
{
	/**
	 * Called when a document is about to be posted, right before saving the {@link I_Fact_Acct} records.
	 * 
	 * @param document
	 */
	void onBeforePost(final Object document);

	/**
	 * Called when a document is about to be posted, right after saving the {@link I_Fact_Acct} records.
	 * 
	 * @param document
	 */
	void onAfterPost(final Object document);

	/**
	 * Called after document's {@link I_Fact_Acct} records were deleted.
	 * 
	 * @param document
	 */
	void onAfterUnpost(final Object document);
}
