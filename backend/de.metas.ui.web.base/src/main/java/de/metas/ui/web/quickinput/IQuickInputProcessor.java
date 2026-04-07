package de.metas.ui.web.quickinput;

import java.util.Set;

import de.metas.ui.web.window.datatypes.DocumentId;

 

public interface IQuickInputProcessor
{
	/**
	 * Processes the quick input and creates the included document line.
	 * 
	 * @param quickInput
	 * @return {@link DocumentId}s of the included document that was created.
	 */
	Set<DocumentId> process(final QuickInput quickInput);
}
