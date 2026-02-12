package org.compiere.report.email.service;

import de.metas.process.ProcessInstanceInfo;

public interface IEmailParamsFactory {

	IEmailParameters getInstanceForPI(final ProcessInstanceInfo pi);

}
