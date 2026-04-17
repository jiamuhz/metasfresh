package de.metas.ui.web.document.references;

import javax.annotation.Nullable;

import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import de.metas.util.Check;
import de.metas.util.StringUtils;
import lombok.NonNull;
import lombok.Value;


@Value
public class WebuiDocumentReferenceTargetWindow
{
	public static WebuiDocumentReferenceTargetWindow ofWindowId(@NonNull final WindowDocumentTypeId windowId)
	{
		final String category = null;
		return new WebuiDocumentReferenceTargetWindow(windowId, category);
	}

	public static WebuiDocumentReferenceTargetWindow ofWindowIdAndCategory(
			@NonNull final WindowDocumentTypeId windowId,
			@NonNull final String category)
	{
		Check.assumeNotEmpty(category, "category is not empty");
		return new WebuiDocumentReferenceTargetWindow(windowId, category);
	}

	WindowDocumentTypeId windowId;
	String category;

	private WebuiDocumentReferenceTargetWindow(
			@NonNull final WindowDocumentTypeId windowId,
			@Nullable final String category)
	{
		this.windowId = windowId;
		this.category = StringUtils.trimBlankToNull(category);
	}
}
