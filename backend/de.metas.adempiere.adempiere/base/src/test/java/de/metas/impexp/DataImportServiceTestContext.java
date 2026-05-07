package de.metas.impexp;

import static org.adempiere.model.InterfaceWrapperHelper.newInstance;
import static org.adempiere.model.InterfaceWrapperHelper.saveRecord;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import org.adempiere.ad.table.api.AdTableId;
import org.adempiere.ad.table.api.IADTableDAO;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_AD_Table;
import org.compiere.model.I_AD_Window;
import org.compiere.model.I_C_DataImport;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mockito;

import de.metas.impexp.config.DataImportConfigId;
import de.metas.impexp.config.DataImportConfigRepository;
import de.metas.impexp.format.ImpFormatId;
import de.metas.impexp.format.ImpFormatRepository;
import de.metas.impexp.format.ImportTableDescriptor;
import de.metas.impexp.format.ImportTableDescriptorRepository;
import de.metas.impexp.processing.IImportProcess;
import de.metas.impexp.processing.IImportProcessFactory;
import de.metas.util.Services;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Disabled
class DataImportServiceTestContext<ImportRecordType, ImportProcessType extends IImportProcess<ImportRecordType>>
{
	// Parameters
	private final Class<ImportRecordType> importModelClass;
	private final String importTableName;
	private final ImpFormatId impFormatId;
	private final RecordingSupplier<ImportProcessType> importProcessFactory;

	private final IADTableDAO tableDAO;
	private final ImpFormatRepository importFormatsRepo;
	private final ImportTableDescriptorRepository importTableDescriptorRepo;
	private final IImportProcessFactory importProcessFactoryService;
	@Getter
	private final MockedInsertIntoImportTableService importTableAppenderService;
	@Getter
	private final DataImportService dataImportService;

	//
	// Master data
	//
	@Getter
	private final DataImportConfigId dataImportConfigId;

	@Builder
	private DataImportServiceTestContext(
			@NonNull final Class<ImportRecordType> importModelClass,
			@NonNull final ImpFormatId impFormatId,
			@NonNull final Supplier<ImportProcessType> importProcessFactory,
			@Nullable final ImportRecordsAsyncExecutor importRecordsAsyncExecutor)
	{
		this.importModelClass = importModelClass;
		this.importTableName = InterfaceWrapperHelper.getTableName(importModelClass);
		this.impFormatId = impFormatId;
		this.importProcessFactory = RecordingSupplier.wrap(importProcessFactory);

		tableDAO = Services.get(IADTableDAO.class);
		importTableDescriptorRepo = Mockito.spy(new ImportTableDescriptorRepository());
		importFormatsRepo = new ImpFormatRepository(importTableDescriptorRepo);
		importProcessFactoryService = Services.get(IImportProcessFactory.class);
		importTableAppenderService = new MockedInsertIntoImportTableService();
		dataImportService = new DataImportService(
				new DataImportConfigRepository(),
				importFormatsRepo,
				new DataImportRunsService(),
				importTableAppenderService,
				Optional.ofNullable(importRecordsAsyncExecutor));

		mockImportTableDescriptor(importTableName);

		dataImportConfigId = createDataImportConfig();
	}

	private void mockImportTableDescriptor(final String importTableName)
	{
		final AdTableId importTableId = AdTableId.ofRepoId(tableDAO.retrieveTableId(importTableName));
		final ImportTableDescriptor importTableDescriptor = ImportTableDescriptor.builder()
				.tableName(importTableName)
				.keyColumnName(importTableName + "_ID")
				.build();

		Mockito.doReturn(importTableDescriptor)
				.when(importTableDescriptorRepo)
				.getByTableId(importTableId);
	}

	private DataImportConfigId createDataImportConfig()
	{
		final I_C_DataImport dataImportConfigRecord = newInstance(I_C_DataImport.class);
		dataImportConfigRecord.setInternalName(importTableName);
		dataImportConfigRecord.setAD_ImpFormat_ID(impFormatId.getRepoId());
		saveRecord(dataImportConfigRecord);

		StaticDelegatingImportProcess.setDelegateFactory(importProcessFactory);

		importProcessFactoryService.registerImportProcess(
				importModelClass,
				StaticDelegatingImportProcess.castTo(importModelClass));

		return DataImportConfigId.ofRepoId(dataImportConfigRecord.getC_DataImport_ID());
	}

	public void createWindowForTable(final String tableName)
	{
		final I_AD_Window window = InterfaceWrapperHelper.newInstance(I_AD_Window.class);
		window.setName("Window of " + tableName);
		saveRecord(window);

		final AdTableId adTableId = AdTableId.ofRepoId(tableDAO.retrieveTableId(tableName));
		final I_AD_Table table = InterfaceWrapperHelper.newInstance(I_AD_Table.class);
		table.setAD_Table_ID(adTableId.getRepoId());
		table.setTableName(tableName);
		table.setAD_Window_ID(window.getAD_Window_ID());
		saveRecord(table);
	}

	public List<ImportProcessType> getProcessRuns()
	{
		return importProcessFactory.getPreviousValues();
	}
}
