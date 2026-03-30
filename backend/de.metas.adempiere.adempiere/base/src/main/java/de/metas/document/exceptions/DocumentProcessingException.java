package de.metas.document.exceptions;

/** */

import de.metas.document.engine.IDocument;
import de.metas.document.engine.IDocumentBL;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStringBuilder;
import de.metas.i18n.TranslatableStrings;
import de.metas.util.Check;
import de.metas.util.Services;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;

/**
 * Miscellaneous document processing failure
 *
 * @author tsa
 */
@SuppressWarnings("serial")
public class DocumentProcessingException extends AdempiereException
{
	public DocumentProcessingException(final IDocument document, final String docAction)
	{
		super(buildMsg(null, document, docAction));
	}

	public DocumentProcessingException(final String message, final Object documentObj, final String docAction)
	{
		super(buildMsg(message, documentObj, docAction));
	}

	private static ITranslatableString buildMsg(
			@Nullable final String message,
			@Nullable final Object documentObj,
			@Nullable final String docAction)
	{
		final TranslatableStringBuilder msg = TranslatableStrings.builder();
		if (message == null || Check.isBlank(message))
		{
			msg.append("错误:"); // "Error Processing Document"
		}
		else
		{
			msg.append(message.trim());
		}

		final String documentInfo;
		final String processMsg;
		if (documentObj == null)
		{
			// shall not happen
			documentInfo = "no document";
			processMsg = null;
		}
		else
		{
			final IDocument document = Services.get(IDocumentBL.class).getDocumentOrNull(documentObj);
			if (document != null)
			{
				documentInfo = document.getDocumentInfo();
				processMsg = document.getProcessMsg();
			}
			else
			{
				documentInfo = documentObj.toString();
				processMsg = null;
			}
		}

		//msg.append("\n").appendADElement("Document").append(": ").append(documentInfo); // 移除了 .appendADElement("Document").append(": ")
		msg.append("\n").append(documentInfo);

		msg.append("\n").appendADElement("DocAction").append(": ").append(docAction);
		if (!Check.isEmpty(processMsg, true))
		{
			//msg.append("\n").appendADMessage("ProcessMsg").append(": ").append(processMsg); // 移除了 .appendADMessage("ProcessMsg").append(": ")
			msg.append("\n").append(processMsg);
		}

		return msg.build();
	}
}
