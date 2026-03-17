package de.metas.attachments;

import com.google.common.collect.ImmutableList;
import de.metas.report.server.ReportResult;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.MimeType;
import org.compiere.util.Util;
import org.springframework.core.io.Resource;

import javax.activation.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Objects;

/** */

@Value
@Builder(toBuilder = true)
@ToString(exclude = "data")
public class AttachmentEntryCreateRequest
{
	public static AttachmentEntryCreateRequest fromURI(
			@NonNull final String fileName,
			@NonNull final URI uri)
	{
		return AttachmentEntryCreateRequest.builder()
				.type(AttachmentEntryType.URL)
				.filename(fileName)
				.contentType(MimeType.getMimeType(fileName))
				.url(uri)
				.build();
	}

	public static AttachmentEntryCreateRequest fromLocalFileURI(
			@NonNull final String fileName,
			@NonNull final URI uri)
	{
		return AttachmentEntryCreateRequest.builder()
				.type(AttachmentEntryType.LocalFileURL)
				.filename(fileName)
				.contentType(MimeType.getMimeType(fileName))
				.url(uri)
				.build();
	}

	public static AttachmentEntryCreateRequest fromByteArray(
			@NonNull final String fileName,
			final byte[] data)
	{
		return builderFromByteArray(fileName, data).build();
	}

	public static AttachmentEntryCreateRequest.AttachmentEntryCreateRequestBuilder builderFromByteArray(
			@NonNull final String fileName,
			final byte[] data)
	{
		return AttachmentEntryCreateRequest.builder()
				.type(AttachmentEntryType.Data)
				.filename(fileName)
				.contentType(MimeType.getMimeType(fileName))
				.data(data);
	}

	public static AttachmentEntryCreateRequest fromDataSource(final DataSource dataSource)
	{
		final String filename = dataSource.getName();
		final String contentType = dataSource.getContentType();
		final byte[] data;
		try
		{
			data = Util.readBytes(dataSource.getInputStream());
		}
		catch (final IOException e)
		{
			throw new AdempiereException("Failed reading data from " + dataSource, e);
		}

		return builder()
				.type(AttachmentEntryType.Data)
				.filename(filename)
				.contentType(contentType)
				.data(data)
				.build();
	}

	public static Collection<AttachmentEntryCreateRequest> fromResources(@NonNull final Collection<Resource> resources)
	{
		return resources
				.stream()
				.map(AttachmentEntryCreateRequest::fromResource)
				.collect(ImmutableList.toImmutableList());
	}

	public static AttachmentEntryCreateRequest fromResource(@NonNull final Resource resource)
	{
		final String filename = resource.getFilename();
		final String contentType = MimeType.getMimeType(filename);
		final byte[] data;
		try
		{
			data = Util.readBytes(resource.getInputStream());
		}
		catch (final IOException e)
		{
			throw new AdempiereException("Failed reading data from " + resource, e);
		}

		return builder()
				.type(AttachmentEntryType.Data)
				.filename(filename)
				.contentType(contentType)
				.data(data)
				.build();
	}

	public static Collection<AttachmentEntryCreateRequest> fromFiles(@NonNull final Collection<File> files)
	{
		return files
				.stream()
				.map(AttachmentEntryCreateRequest::fromFile)
				.collect(ImmutableList.toImmutableList());
	}

	public static AttachmentEntryCreateRequest fromFile(@NonNull final File file)
	{
		final String filename = file.getName();
		final String contentType = MimeType.getMimeType(filename);
		final byte[] data = Util.readBytes(file);

		return builder()
				.type(AttachmentEntryType.Data)
				.filename(filename)
				.contentType(contentType)
				.data(data)
				.build();
	}

	public static AttachmentEntryCreateRequest fromReport(@NonNull final ReportResult report)
	{
		return builder()
				.type(AttachmentEntryType.Data)
				.filename(report.getReportFilename())
				.contentType(report.getOutputType().getContentType())
				.data(report.getReportContent())
				.build();
	}

	@NonNull
	AttachmentEntryType type;

	String filename;
	String contentType;

	byte[] data;
	URI url;

	AttachmentTags tags;

	public AttachmentEntryCreateRequest withFilename(@NonNull final String filename)
	{
		if (Objects.equals(this.filename, filename))
		{
			return this;
		}

		return toBuilder()
				.filename(filename)
				.contentType(MimeType.getMimeType(filename))
				.build();
	}
}
