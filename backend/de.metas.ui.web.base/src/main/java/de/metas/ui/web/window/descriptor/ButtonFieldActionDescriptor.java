package de.metas.ui.web.window.descriptor;

import de.metas.ui.web.process.ProcessId;
import lombok.NonNull;
import lombok.ToString;



@ToString
public final class ButtonFieldActionDescriptor
{
	public static final ButtonFieldActionDescriptor processCall(@NonNull final ProcessId processId)
	{
		final String zoomIntoTableIdFieldName = null; // N/A
		return new ButtonFieldActionDescriptor(ButtonFieldActionType.processCall, processId, zoomIntoTableIdFieldName);
	}

	public static final ButtonFieldActionDescriptor genericZoomInto(@NonNull final String zoomIntoTableIdFieldName)
	{
		final ProcessId processId = null; // N/A
		return new ButtonFieldActionDescriptor(ButtonFieldActionType.genericZoomInto, processId, zoomIntoTableIdFieldName);
	}

	public static enum ButtonFieldActionType
	{
		processCall, genericZoomInto,
	}

	private @NonNull final ButtonFieldActionType actionType;
	private final ProcessId processId;
	private final String zoomIntoTableIdFieldName;

	private ButtonFieldActionDescriptor(final ButtonFieldActionType actionType, final ProcessId processId, final String zoomIntoTableIdFieldName)
	{
		super();
		this.actionType = actionType;
		this.processId = processId;
		this.zoomIntoTableIdFieldName = zoomIntoTableIdFieldName;
	}
	
	public ButtonFieldActionType getActionType()
	{
		return actionType;
	}
	
	public ProcessId getProcessId()
	{
		return processId;
	}
	
	public String getZoomIntoTableIdFieldName()
	{
		return zoomIntoTableIdFieldName;
	}
}
