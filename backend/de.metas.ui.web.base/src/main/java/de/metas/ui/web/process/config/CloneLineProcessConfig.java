

package de.metas.ui.web.process.config;

import de.metas.process.IADProcessDAO;
import de.metas.process.RelatedProcessDescriptor;
import de.metas.ui.web.process.WEBUI_CloneLine;
import de.metas.util.Services;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CloneLineProcessConfig
{
	private final IADProcessDAO adProcessesRepo = Services.get(IADProcessDAO.class);

	@PostConstruct
	public void registerProcess()
	{
		adProcessesRepo.registerTableProcess(RelatedProcessDescriptor.builder()
				.processId(adProcessesRepo.retrieveProcessIdByClass(WEBUI_CloneLine.class))
				.anyTable()
				.anyWindow()
				.displayPlace(RelatedProcessDescriptor.DisplayPlace.SingleDocumentActionsMenu)
				.build());
	}
}
