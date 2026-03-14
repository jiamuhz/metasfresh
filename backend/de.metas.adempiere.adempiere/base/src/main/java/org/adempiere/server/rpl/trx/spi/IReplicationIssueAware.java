package org.adempiere.server.rpl.trx.spi;

/** */


public interface IReplicationIssueAware
{
	// @formatter:off
	String COLUMNNAME_IsImportedWithIssues = "IsImportedWithIssues";
	boolean isImportedWithIssues();
	void setIsImportedWithIssues(boolean IsImportedWithIssues);
	// @formatter:on

	// @formatter:off
	String COLUMNNAME_EXP_ReplicationTrx_ID = "EXP_ReplicationTrx_ID";
	void setEXP_ReplicationTrx_ID(int EXP_ReplicationTrx_ID);
	int getEXP_ReplicationTrx_ID();
	// @formatter:on
}
