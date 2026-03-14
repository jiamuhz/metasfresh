package org.adempiere.appdict.validation.spi.impl;

/** */

import de.metas.process.JavaProcess;
import de.metas.util.Check;
import lombok.NonNull;
import org.adempiere.appdict.validation.api.IADValidatorViolation;
import org.adempiere.appdict.validation.spi.AbstractADValidator;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_AD_Process;
import org.compiere.util.Util;

public class ADProcessADValidator extends AbstractADValidator<I_AD_Process>
{
	@Override
	public void validate(@NonNull final I_AD_Process process)
	{
		String classname = process.getClassname();
		if (Check.isBlank(classname))
		{
			return;
		}

		classname = classname.trim();

		// Skip @script references
		if (classname.startsWith("@script"))
		{
			return;
		}

		Util.validateJavaClassname(process.getClassname(), JavaProcess.class);
	}

	@Override
	public String getLogMessage(final IADValidatorViolation violation)
	{
		final StringBuilder message = new StringBuilder();
		try
		{
			final I_AD_Process process = InterfaceWrapperHelper.create(violation.getItem(), I_AD_Process.class);

			message.append("Error on ").append(process).append(" (IsActive=").append(process.isActive()).append("): ");
		}
		catch (final Exception e)
		{
			message.append("Error (InterfaceWrapperHelper exception: ").append(e.getLocalizedMessage()).append(") on ").append(violation.getItem()).append(": ");
		}

		message.append(violation.getError().getLocalizedMessage());

		return message.toString();
	}

	@Override
	public Class<I_AD_Process> getType()
	{
		return I_AD_Process.class;
	}
}
