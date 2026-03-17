package de.metas.location;

import de.metas.util.Services;
import lombok.NonNull;
import org.compiere.model.I_C_Country;
import org.compiere.model.I_C_Location;
import org.springframework.stereotype.Repository;

import static org.adempiere.model.InterfaceWrapperHelper.load;

/** */

@Repository
public class LocationRepository
{
	private Location toLocation(@NonNull final I_C_Location locationRecord)
	{
		final String address = Services.get(ILocationBL.class).mkAddress(locationRecord);

		final I_C_Country countryRecord = Services.get(ICountryDAO.class).getById(CountryId.ofRepoId(locationRecord.getC_Country_ID()));
		return Location.builder()
				.id(LocationId.ofRepoId(locationRecord.getC_Location_ID()))
				.address(address)
				.city(locationRecord.getCity())
				.postal(locationRecord.getPostal())
				.streetAddress(locationRecord.getAddress1())
				.countryCode(countryRecord.getCountryCode())
				.build();
	}

	public Location getByLocationId(@NonNull final LocationId locationId)
	{
		return toLocation(load(locationId.getRepoId(), I_C_Location.class));
	}
}
