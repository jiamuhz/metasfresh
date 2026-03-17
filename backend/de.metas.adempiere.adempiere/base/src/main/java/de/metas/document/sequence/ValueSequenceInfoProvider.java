package de.metas.document.sequence;

import java.util.Optional;

import javax.annotation.Nullable;

import de.metas.document.DocumentSequenceInfo;
import lombok.NonNull;
import lombok.Value;

/** */

public interface ValueSequenceInfoProvider
{
	@Value
	public static class ProviderResult
	{
		public static final ProviderResult EMPTY = new ProviderResult(null);

		public static ProviderResult of(@NonNull final DocumentSequenceInfo documentSequenceInfo)
		{
			return new ProviderResult(documentSequenceInfo);
		}

		Optional<DocumentSequenceInfo> documentSequenceInfo;

		public boolean hasInfo()
		{
			return documentSequenceInfo.isPresent();
		}

		public DocumentSequenceInfo getInfoOrNull()
		{
			return documentSequenceInfo.orElse(null);
		}

		private ProviderResult(@Nullable final DocumentSequenceInfo documentSequenceInfo)
		{
			this.documentSequenceInfo = Optional.ofNullable(documentSequenceInfo);
		}
	}

	ProviderResult computeValueInfo(Object modelrecord);
}
