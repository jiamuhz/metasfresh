package de.metas.report;

import com.google.common.collect.ImmutableList;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import de.metas.printing.IMassPrintingService;
import de.metas.process.ProcessExecutor;
import de.metas.process.ProcessInstanceInfo;
import de.metas.report.server.OutputType;
import de.metas.report.server.ReportConstants;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.UtilityClass;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/** */

@UtilityClass
public class ExecuteReportStrategyUtil
{
	public Resource executeJasperProcess(
			final int jasperProcessId,
			@NonNull final ProcessInstanceInfo processInfo,
			@NonNull final OutputType outputType)
	{
		final ProcessExecutor processExecutor = ProcessInstanceInfo.builder()
				.setCtx(Env.getCtx())
				.setAD_Process_ID(jasperProcessId)
				.setRecord(processInfo.getTable_ID(), processInfo.getRecord_ID())
				.addParameter(ReportConstants.REPORT_PARAM_BARCODE_URL, DocumentReportService.getBarcodeServlet(processInfo.getClientId(), processInfo.getOrgId()))
				.addParameter(IMassPrintingService.PARAM_PrintCopies, PrintCopies.ONE.toInt())
				.setArchiveReportData(false) // don't archive it! just give us the PDF data
				.setPrintPreview(false)

				// important; event though printPreview(false), we might want JasperPrint, because the result shall be shown in the jasper-viewer
				.setJRDesiredOutputType(outputType)

				.buildAndPrepareExecution()
				.onErrorThrowException(true)
				.executeSync();

		return processExecutor.getResult().getReportDataResource();
	}

	/**
	 * @deprecated Please use {@link #concatenatePDFs(ImmutableList)}.
	 */
	@Deprecated
	public Resource concatenatePDF(
			@NonNull final Resource documentPdfData,
			@NonNull final List<PdfDataProvider> pdfDataToConcatenate)
	{
		if (pdfDataToConcatenate.isEmpty())
		{
			return documentPdfData;
		}

		final PdfDataProvider pdfData = PdfDataProvider.forData(documentPdfData);

		final ImmutableList<PdfDataProvider> allPdfDataToConcatenate = ImmutableList.<PdfDataProvider>builder()
				.add(pdfData)
				.addAll(pdfDataToConcatenate)
				.build();

		return concatenatePDFs(allPdfDataToConcatenate);
	}

	/**
	 * The more sane version of {@link #concatenatePDF(Resource, List)}.
	 */
	@NonNull
	public Resource concatenatePDFs(@NonNull final ImmutableList<PdfDataProvider> pdfDataToConcatenate)
	{
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		concatenatePDFsToOutputStream(out, pdfDataToConcatenate);

		return new ByteArrayResource(out.toByteArray());
	}

	private void concatenatePDFsToOutputStream(
			@NonNull final OutputStream outputStream,
			@NonNull final ImmutableList<PdfDataProvider> pdfDataToConcatenate)
	{
		final Document document = new Document();

		try
		{
			final PdfCopy copyDestination = new PdfCopy(document, outputStream);
			document.open();

			for (final PdfDataProvider pdfData : pdfDataToConcatenate)
			{
				appendPdfPages(copyDestination, pdfData);
			}
			document.close();
		}
		catch (final Exception e)
		{
			throw AdempiereException.wrapIfNeeded(e);
		}
	}

	private static void appendPdfPages(@NonNull final PdfCopy copyDestination, @NonNull final PdfDataProvider pdfData) throws IOException, BadPdfFormatException
	{
		final Resource data = pdfData.getPdfData();

		final PdfReader pdfReader = new PdfReader(data.getInputStream());

		for (int page = 0; page < pdfReader.getNumberOfPages(); )
		{
			copyDestination.addPage(copyDestination.getImportedPage(pdfReader, ++page));
		}
		copyDestination.freeReader(pdfReader);
		pdfReader.close();
	}

	@Value
	public static class PdfDataProvider
	{
		public static PdfDataProvider forData(@NonNull final Resource pdfData)
		{
			return new PdfDataProvider(pdfData);
		}

		Resource pdfData;

		private PdfDataProvider(@NonNull final Resource pdfData)
		{
			this.pdfData = pdfData;
		}
	}
}
