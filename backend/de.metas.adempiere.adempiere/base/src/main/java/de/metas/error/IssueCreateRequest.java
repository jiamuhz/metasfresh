package de.metas.error;

import javax.annotation.Nullable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class IssueCreateRequest
{
	@Nullable
	String summary;

	@Nullable
	String sourceClassname;

	@Nullable
	String sourceMethodName;

	@Nullable
	String loggerName;

	@Nullable
	Throwable throwable;

	@Nullable
	String stackTrace;
}
