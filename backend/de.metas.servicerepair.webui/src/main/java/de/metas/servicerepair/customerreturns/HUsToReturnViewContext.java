 

package de.metas.servicerepair.customerreturns;

import de.metas.inout.InOutId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class HUsToReturnViewContext
{
	@NonNull InOutId customerReturnsId;
}
