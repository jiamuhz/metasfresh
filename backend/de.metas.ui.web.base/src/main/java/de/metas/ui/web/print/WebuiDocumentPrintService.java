 

package de.metas.ui.web.print;

import de.metas.document.archive.mailrecipient.DocOutBoundRecipient;
import de.metas.document.archive.mailrecipient.DocOutboundLogMailRecipientRegistry;
import de.metas.document.archive.mailrecipient.DocOutboundLogMailRecipientRequest;
import de.metas.document.engine.IDocumentBL;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.process.AdProcessId;
import de.metas.report.DocumentPrintOptionDescriptor;
import de.metas.report.DocumentPrintOptionValue;
import de.metas.report.DocumentPrintOptionsIncludingDescriptors;
import de.metas.report.DocumentReportFlavor;
import de.metas.report.DocumentReportRequest;
import de.metas.report.DocumentReportResult;
import de.metas.report.DocumentReportService;
import de.metas.report.ReportResultData;
import de.metas.ui.web.print.json.JSONDocumentPrintingOption;
import de.metas.ui.web.print.json.JSONDocumentPrintingOptions;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
import de.metas.ui.web.window.model.Document;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WebuiDocumentPrintService
{
	private static final AdMessageKey MSG_PrintOptions_Caption = AdMessageKey.of("webui.window.Print.caption");
	private static final AdMessageKey MSG_PrintOptions_OKButtonCaption = AdMessageKey.of("Print");

	private static final Logger logger = LogManager.getLogger(WebuiDocumentPrintService.class);
	private final DocumentCollection documentCollection;
	private final DocumentReportService documentReportService;
	private final DocOutboundLogMailRecipientRegistry docOutboundLogMailRecipientRegistry;
	private final IDocumentBL documentBL = Services.get(IDocumentBL.class);

	public WebuiDocumentPrintService(
			@NonNull final DocumentCollection documentCollection,
			@NonNull final DocumentReportService documentReportService,
			@NonNull final DocOutboundLogMailRecipientRegistry docOutboundLogMailRecipientRegistry)
	{
		this.documentCollection = documentCollection;
		this.documentReportService = documentReportService;
		this.docOutboundLogMailRecipientRegistry = docOutboundLogMailRecipientRegistry;
	}

	public ReportResultData createDocumentPrint(@NonNull final WebuiDocumentPrintRequest request)
	{
		final DocumentPath documentPath = request.getDocumentPath();
		final Document document = documentCollection.getDocumentReadonly(documentPath);
		final int windowNo = document.getWindowNo();
		final DocumentEntityDescriptor entityDescriptor = document.getEntityDescriptor();

		final AdProcessId printProcessId = entityDescriptor.getPrintProcessId();
		final TableRecordReference recordRef = documentCollection.getTableRecordReference(documentPath);

		final DocumentReportResult result = documentReportService.createReport(DocumentReportRequest.builder()
																					   .flavor(request.getFlavor())
																					   .reportProcessId(printProcessId)
																					   .documentRef(recordRef)
																					   .clientId(document.getClientId())
																					   .orgId(document.getOrgId())
																					   .userId(request.getUserId())
																					   .roleId(request.getRoleId())
																					   .windowNo(windowNo) // important: required for ProcessInfo.findReportingLanguage
																					   .printPreview(true)
																					   .printOptions(request.getPrintOptions())
																					   //.setJRDesiredOutputType(OutputType.PDF)
																					   .build());

		return result.getReportResultData();
	}

	public JSONDocumentPrintingOptions getPrintingOptions(
			@NonNull final DocumentPath documentPath,
			@NonNull final String adLanguage)
	{
		final DocumentEntityDescriptor entityDescriptor = documentCollection.getDocumentEntityDescriptor(documentPath.getWindowId());
		final AdProcessId printProcessId = entityDescriptor.getPrintProcessId();
		final TableRecordReference recordRef = documentCollection.getTableRecordReference(documentPath);
		final DocumentReportFlavor flavor = getDocOutBoundRecipient(recordRef)
				.filter(DocOutBoundRecipient::isInvoiceAsEmail)
				.map(recipient -> DocumentReportFlavor.EMAIL)
				.orElse(DocumentReportFlavor.PRINT);

		final DocumentPrintOptionsIncludingDescriptors printOptions = documentReportService.getDocumentPrintOptionsIncludingDescriptors(
				printProcessId,
				recordRef,
				flavor);

		return toJSONDocumentPrintingOptions(printOptions, adLanguage);
	}

	private Optional<DocOutBoundRecipient> getDocOutBoundRecipient(@NonNull final TableRecordReference recordRef)
	{
		try
		{
			final Object record = recordRef.getModel(Object.class);

			return docOutboundLogMailRecipientRegistry
					.getRecipient(
							DocOutboundLogMailRecipientRequest.builder()
									.recordRef(recordRef)
									.clientId(InterfaceWrapperHelper.getClientId(record).orElseThrow(() -> new AdempiereException("Cannot get AD_Client_ID from " + record)))
									.orgId(InterfaceWrapperHelper.getOrgId(record).orElseThrow(() -> new AdempiereException("Cannot get AD_Org_ID from " + record)))
									.docTypeId(documentBL.getDocTypeId(record).orElse(null))
									.build());
		}
		catch (Exception ex)
		{
			logger.warn("Failed retrieving DocOutBoundRecipient from {}. Returning empty.", recordRef, ex);
			return Optional.empty();
		}
	}

	private static JSONDocumentPrintingOptions toJSONDocumentPrintingOptions(
			@NonNull final DocumentPrintOptionsIncludingDescriptors printOptions,
			@NonNull final String adLanguage)
	{
		final ArrayList<JSONDocumentPrintingOption> jsonOptionsList = new ArrayList<>();

		for (final DocumentPrintOptionDescriptor option : printOptions.getOptionDescriptors())
		{
			final String caption = option.getName().translate(adLanguage);
			final String description = option.getDescription().translate(adLanguage);
			final String internalName = option.getInternalName();
			final DocumentPrintOptionValue value = printOptions.getOptionValue(internalName);

			jsonOptionsList.add(JSONDocumentPrintingOption.builder()
										.caption(caption)
										.description(description)
										.internalName(internalName)
										.value(value.isTrue())
										.debugSourceName(value.getSourceName())
										.build());
		}

		return JSONDocumentPrintingOptions.builder()
				.caption(TranslatableStrings.adMessage(MSG_PrintOptions_Caption).translate(adLanguage))
				.okButtonCaption(TranslatableStrings.adMessage(MSG_PrintOptions_OKButtonCaption).translate(adLanguage))
				.options(jsonOptionsList)
				.build();
	}
}
