package de.metas.impexp;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AsyncImportRecordsResponse
{
	final int workpackageId;
}
