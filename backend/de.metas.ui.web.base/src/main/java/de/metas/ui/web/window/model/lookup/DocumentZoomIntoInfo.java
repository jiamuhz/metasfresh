package de.metas.ui.web.window.model.lookup;

import de.metas.document.references.zoom_into.CustomizedWindowInfo;
import de.metas.document.references.zoom_into.CustomizedWindowInfoMap;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.ad.element.api.AdWindowId;

import javax.annotation.Nullable;
import java.util.Optional;

 

@Value
@Builder(toBuilder = true)
public class DocumentZoomIntoInfo
{
	public static DocumentZoomIntoInfo of(final String tableName, final int id)
	{
		final Integer idObj = id >= 0 ? id : null;
		return builder().tableName(tableName).recordId(idObj).build();
	}

	@NonNull String tableName;
	Integer recordId;
	WindowDocumentTypeId windowId;

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	public DocumentZoomIntoInfo overrideWindowIdIfPossible(@Nullable final Optional<WindowDocumentTypeId> windowId)
	{
		if (windowId == null || !windowId.isPresent())
		{
			return this;
		}
		return toBuilder().windowId(windowId.get()).build();
	}

	public DocumentZoomIntoInfo overrideWindowIdIfPossible(@NonNull final CustomizedWindowInfoMap customizedWindowInfoMap)
	{
		if (this.windowId == null)
		{
			return this;
		}

		final AdWindowId adWindowId = this.windowId.toAdWindowIdOrNull();
		if (adWindowId == null)
		{
			return this;
		}

		final WindowDocumentTypeId customizedWindowId = customizedWindowInfoMap
				.getCustomizedWindowInfo(adWindowId)
				.map(CustomizedWindowInfo::getCustomizationWindowId)
				.map(WindowDocumentTypeId::of)
				.orElse(null);
		if (customizedWindowId == null)
		{
			return this;
		}

		return !WindowDocumentTypeId.equals(this.windowId, customizedWindowId)
				? toBuilder().windowId(customizedWindowId).build()
				: this;
	}

	public boolean isRecordIdPresent()
	{
		return recordId != null;
	}
}
