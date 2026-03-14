package org.adempiere.pos;

/** */


import java.util.List;

import org.compiere.model.I_C_POSKey;
import org.compiere.model.I_C_POSKeyLayout;

import de.metas.util.ISingletonService;

public interface IPOSKeyKayoutDAO extends ISingletonService
{

	List<I_C_POSKey> retrievePOSKeys(I_C_POSKeyLayout keyLayout);

}
