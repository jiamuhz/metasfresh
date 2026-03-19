package de.metas.ui.web.handlingunits;

import org.springframework.stereotype.Component;



/**
 * {@link HUEditorView} customizer.
 *
 * Implementations of this interface which are annotated with {@link Component} will be automatically discovered and registered.
 *
 * 
 *
 */
public interface HUEditorViewCustomizer
{
	/** @return referencing tableName to be matched */
	String getReferencingTableNameToMatch();

	default HUEditorRowIsProcessedPredicate getHUEditorRowIsProcessedPredicate()
	{
		return null;
	}

	default Boolean isAttributesAlwaysReadonly()
	{
		return null;
	}

	/**
	 * Called before the {@link HUEditorView} is created.
	 *
	 * The method is called only if the view is matching our criteria (i.e. {@link #getReferencingTableNameToMatch()}).
	 *
	 * @param viewBuilder
	 */
	void beforeCreate(HUEditorViewBuilder viewBuilder);

}
