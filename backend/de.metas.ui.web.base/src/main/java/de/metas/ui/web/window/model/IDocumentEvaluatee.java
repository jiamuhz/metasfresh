package de.metas.ui.web.window.model;

import org.compiere.util.Evaluatee;



public interface IDocumentEvaluatee extends Evaluatee
{

	/**
	 * Creates a new evaluatee which has given field in scope.
	 * 
	 * The field in scope is the field for whom we actually do the evaluation.
	 * Documents will not be asked for a field value it the field is in scope.
	 * 
	 * @param fieldNameInScope
	 * @return new evaluatee instance which has the given field in scope
	 */
	IDocumentEvaluatee fieldInScope(String fieldNameInScope);

	/**
	 * Creates a new evaluatee which will exclude given field names.
	 * 
	 * @param fieldNamesToExclude
	 */
	IDocumentEvaluatee excludingFields(String... fieldNamesToExclude);
}
