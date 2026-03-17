package org.compiere.util;

/** */

import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.model.PO;

/**
 * Used in conjunction with sql <code>RETURNING</code> dml statements.
 * And implementor can be passed to {@link DB#executeUpdate(ExecuteUpdateRequest)}.<br>
 * The implementors job is to process the returned values.
 * <p>
 * Hint: {@link PO} contains an inner class that implements this interface.
 *
 *
 *
 */

@FunctionalInterface
public interface ISqlUpdateReturnProcessor
{
	void process(ResultSet rs) throws SQLException;
}
