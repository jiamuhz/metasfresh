package de.metas.process;

import javax.annotation.Nullable;

import org.adempiere.util.lang.IAutoCloseable;
import org.slf4j.MDC;
import org.slf4j.MDC.MDCCloseable;

import com.google.common.collect.ImmutableList;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProcessMDC
{
	public static final String NAME_AD_Process_ID = "AD_Process_ID";
	public static final String NAME_AD_PInstance_ID = "AD_PInstance_ID";

	public static IAutoCloseable putProcessAndInstanceId(
			@Nullable final AdProcessId adProcessId,
			@Nullable final PInstanceId pInstanceId)
	{
		final ImmutableList<MDCCloseable> closeables = ImmutableList.of(
				putAdProcessId(adProcessId),
				putPInstanceId(pInstanceId));

		return () -> closeables.forEach(MDCCloseable::close);
	}

	public static MDCCloseable putAdProcessId(@Nullable final AdProcessId adProcessId)
	{
		return MDC.putCloseable(
				NAME_AD_Process_ID,
				adProcessId != null ? String.valueOf(adProcessId.getRepoId()) : null);
	}

	public static MDCCloseable putPInstanceId(@Nullable final PInstanceId pinstanceId)
	{
		return MDC.putCloseable(
				NAME_AD_PInstance_ID,
				pinstanceId != null ? String.valueOf(pinstanceId.getRepoId()) : null);
	}
}
