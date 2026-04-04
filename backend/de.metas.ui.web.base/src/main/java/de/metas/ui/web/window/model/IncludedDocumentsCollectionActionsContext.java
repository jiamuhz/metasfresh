package de.metas.ui.web.window.model;

import java.util.Collection;

import org.adempiere.ad.expression.api.LogicExpressionResult;
import org.compiere.util.Evaluatee;

import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DetailId;

 

public interface IncludedDocumentsCollectionActionsContext
{
	boolean isParentDocumentProcessed();

	boolean isParentDocumentActive();

	boolean isParentDocumentNew();
	
	boolean isParentDocumentInvalid();

	Evaluatee toEvaluatee();

	Collection<Document> getIncludedDocuments();

	void collectAllowNew(DocumentPath parentDocumentPath, DetailId detailId, LogicExpressionResult allowNew);

	void collectAllowDelete(DocumentPath parentDocumentPath, DetailId detailId, LogicExpressionResult allowDelete);
	
	
}
