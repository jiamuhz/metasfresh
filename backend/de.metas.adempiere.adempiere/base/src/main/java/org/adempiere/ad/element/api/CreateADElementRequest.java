package org.adempiere.ad.element.api;

import lombok.Builder;
import lombok.Value;

/** */

@Value
@Builder
public class CreateADElementRequest
{
	String name;
	String printName;
	String description;
	String help;

	String tabCommitWarning;

	String webuiNameBrowse;
	String webuiNameNew;
	String webuiNameNewBreadcrumb;
}
