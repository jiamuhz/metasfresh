package de.metas.ui.web.window.model;

import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.LookupValuesPage;
import de.metas.ui.web.window.model.Document.CopyMode;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import org.adempiere.ad.callout.api.ICalloutField;
import org.adempiere.ad.expression.api.LogicExpressionResult;

import java.util.Optional;


/* package */interface IDocumentField extends IDocumentFieldView
{
	enum FieldInitializationMode
	{
		NewDocument, Refresh, Load,
	}

	Document getDocument();

	@Override
	default DocumentPath getDocumentPath()
	{
		return getDocument().getDocumentPath();
	}

	/**
	 * Sets initial value / last saved value.
	 * 
	 * This method is also:
	 * <ul>
	 * <li>updating the field value (see {@link #setValue(Object, IDocumentChangesCollector)}
	 * <li>updating the validStatus
	 * </ul>
	 */
	void setInitialValue(Object initialValue, IDocumentChangesCollector changesCollector);

	/**
	 * Set field's current value.
	 */
	void setValue(Object value, IDocumentChangesCollector changesCollector);

	void setMandatory(LogicExpressionResult mandatory, final IDocumentChangesCollector changesCollector);

	void setReadonly(LogicExpressionResult readonly);

	void setDisplayed(LogicExpressionResult displayed);

	/**
	 * Notify this instance that it's lookup values are staled. So next time they are needed, they need to be reloaded.
	 */
	boolean setLookupValuesStaled(String triggeringFieldName);

	LookupValuesList getLookupValues();

	LookupValuesPage getLookupValuesForQuery(String query);

	Optional<LookupValue> getLookupValueById(@NonNull RepoIdAware id);

	ICalloutField asCalloutField();

	/** @return field's valid state; never return null */
	@Override
	DocumentValidStatus getValidStatus();

	/**
	 * Note: it's not clear why it is enough to only evaluate the while the <i>initial</i> status is invalid..<br>
	 * Yet I keep it that way for now, because it works as far as we see. And changing it might result in a performance degradation.
	 */
	DocumentValidStatus updateStatusIfInitialInvalidAndGet(IDocumentChangesCollector changesCollector);

	IDocumentField copy(Document document, CopyMode copyMode);
}
