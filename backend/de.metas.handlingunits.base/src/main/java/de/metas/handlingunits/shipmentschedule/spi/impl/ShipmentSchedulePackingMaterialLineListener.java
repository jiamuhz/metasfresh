package de.metas.handlingunits.shipmentschedule.spi.impl;

import org.adempiere.model.InterfaceWrapperHelper;
import org.slf4j.Logger;

import ch.qos.logback.classic.Level;
import de.metas.handlingunits.model.I_C_OrderLine;
import de.metas.inoutcandidate.spi.ModelWithoutShipmentScheduleVetoer;
import de.metas.logging.LogManager;
import de.metas.util.Loggables;
import lombok.NonNull;
import lombok.ToString;

/**
 * Makes sure that there are no shipment schedules for picking material lines.
 * <p>
 * Background: we need them in the order document, but not in the shipment schedule. See task 07042.
 *

 * @task http://dewiki908/mediawiki/index.php/07042_Simple_InOut-Creation_from_shipment-schedule_%28109342691288%29
 */
@ToString
public class ShipmentSchedulePackingMaterialLineListener implements ModelWithoutShipmentScheduleVetoer
{

	private static final Logger logger = LogManager.getLogger(ShipmentSchedulePackingMaterialLineListener.class);

	/**
	 * @see #ShipmentSchedulePackingMaterialLineListener()
	 */
	@Override
	public OnMissingCandidate foundModelWithoutInOutCandidate(@NonNull final Object model)
	{
		final I_C_OrderLine ol = InterfaceWrapperHelper.create(model, I_C_OrderLine.class);
		if (ol.isPackagingMaterial())
		{
			Loggables.withLogger(logger, Level.DEBUG)
					.addLog("ShipmentSchedulePackingMaterialLineListener - orderLine.isPackagingMaterial=true; return {}; orderLine={}",
							OnMissingCandidate.I_VETO, ol);
			return OnMissingCandidate.I_VETO; // don't create the shipment schedule
		}
		return OnMissingCandidate.I_DONT_CARE; // let the others decide
	}

}
