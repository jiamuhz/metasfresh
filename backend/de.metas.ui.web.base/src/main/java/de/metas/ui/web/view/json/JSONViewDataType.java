package de.metas.ui.web.view.json;

import de.metas.ui.web.window.descriptor.DocumentFieldDescriptor.Characteristic;

  
public enum JSONViewDataType
{
	/** Side list data */
	list(Characteristic.SideListField),

	/** Grid layout data */
	grid(Characteristic.GridViewField),

	/** Included view layout */
	includedView(null);

	private final Characteristic requiredFieldCharacteristic;

	private JSONViewDataType(final Characteristic requiredFieldCharacteristic)
	{
		this.requiredFieldCharacteristic = requiredFieldCharacteristic;
	}

	public Characteristic getRequiredFieldCharacteristic()
	{
		return requiredFieldCharacteristic;
	}
}
