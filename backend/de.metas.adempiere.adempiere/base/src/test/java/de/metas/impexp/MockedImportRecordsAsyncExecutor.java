package de.metas.impexp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import de.metas.util.collections.CollectionUtils;
import lombok.NonNull;
import lombok.ToString;

@ToString
public class MockedImportRecordsAsyncExecutor implements ImportRecordsAsyncExecutor
{
	private final List<ImportRecordsRequest> scheduledRequests = new ArrayList<>();
	private int nextWorkpackageId = 1;

	@Override
	public AsyncImportRecordsResponse schedule(@NonNull final ImportRecordsRequest request)
	{
		scheduledRequests.add(request);
		
		return AsyncImportRecordsResponse.builder()
				.workpackageId(nextWorkpackageId++)
				.build();
	}

	public void assertNoCalls()
	{
		assertThat(scheduledRequests).isEmpty();
	}

	public void assertOneCall()
	{
		assertThat(scheduledRequests).hasSize(1);
	}

	public ImportRecordsRequest getSingleScheduledRequest()
	{
		return CollectionUtils.singleElement(scheduledRequests);
	}
}
