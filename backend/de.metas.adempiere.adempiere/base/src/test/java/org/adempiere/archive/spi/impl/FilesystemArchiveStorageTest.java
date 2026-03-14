package org.adempiere.archive.spi.impl;

/** */

import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.ClientId;
import org.adempiere.test.AdempiereTestHelper;
import org.assertj.core.api.Assertions;
import org.compiere.model.I_AD_Archive;
import org.compiere.model.I_AD_Client;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import java.util.Random;

public class FilesystemArchiveStorageTest
{
	private FilesystemArchiveStorage storage;

	@BeforeEach
	public void init() throws IOException
	{
		AdempiereTestHelper.get().init();

		final Properties ctx = Env.getCtx();
		final I_AD_Client client = InterfaceWrapperHelper.create(ctx, I_AD_Client.class, ITrx.TRXNAME_None);
		client.setWindowsArchivePath(Files.createTempDirectory(getClass().getSimpleName() + "_").toFile().getAbsolutePath());
		client.setUnixArchivePath(client.getWindowsArchivePath());
		client.setStoreArchiveOnFileSystem(true);
		InterfaceWrapperHelper.save(client);
		final ClientId clientId = ClientId.ofRepoId(client.getAD_Client_ID());

		Env.setClientId(ctx, clientId);

		storage = new FilesystemArchiveStorage();
		storage.init(clientId);
	}

	@Test
	public void test_set_getBinaryData()
	{
		Ini.setClient(false);

		final I_AD_Archive archive = InterfaceWrapperHelper.create(Env.getCtx(), I_AD_Archive.class, ITrx.TRXNAME_None);
		archive.setAD_Org_ID(0);
		archive.setAD_Process_ID(0);
		archive.setAD_Table_ID(0);
		archive.setRecord_ID(0);
		final byte[] data = createTestDataBytes();
		storage.setBinaryData(archive, data);
		InterfaceWrapperHelper.save(archive);

		Assertions.assertThat(archive.isFileSystem()).isTrue();

		final byte[] dataActual = storage.getBinaryData(archive);
		Assertions.assertThat(dataActual).isEqualTo(data);
	}

	private final Random random = new Random();

	private byte[] createTestDataBytes()
	{
		final byte[] data = new byte[4096];
		random.nextBytes(data);
		return data;
	}
}
