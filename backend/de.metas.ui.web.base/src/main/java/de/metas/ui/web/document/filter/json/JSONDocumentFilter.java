package de.metas.ui.web.document.filter.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterList;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.provider.DocumentFilterDescriptorsProvider;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

  
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDocumentFilter
{
	public static DocumentFilterList unwrapList(final List<JSONDocumentFilter> jsonFilters, final DocumentFilterDescriptorsProvider filterDescriptorProvider)
	{
		if (jsonFilters == null || jsonFilters.isEmpty())
		{
			return DocumentFilterList.EMPTY;
		}
		return jsonFilters
				.stream()
				.map(filterDescriptorProvider::unwrap)
				.filter(Objects::nonNull)
				.collect(DocumentFilterList.toDocumentFilterList());
	}

	public static DocumentFilter unwrapAsGenericFilter(final JSONDocumentFilter jsonFilter)
	{
		return DocumentFilter.builder()
				.filterId(jsonFilter.getFilterId())
				.parameters(jsonFilter.getParameters()
						.stream()
						.map(jsonParam -> DocumentFilterParam.builder()
								.setFieldName(jsonParam.getParameterName())
								.setValue(jsonParam.getValue())
								.setValueTo(jsonParam.getValueTo())
								.setOperator()
								.build())
						.collect(GuavaCollectors.toImmutableList()))
				.build();
	}

	public static List<JSONDocumentFilter> ofList(final DocumentFilterList filters, final JSONOptions jsonOpts)
	{
		if (filters == null || filters.isEmpty())
		{
			return ImmutableList.of();
		}

		return filters.stream()
				.map(filter -> of(filter, jsonOpts))
				.collect(GuavaCollectors.toImmutableList());
	}

	public static JSONDocumentFilter of(final DocumentFilter filter, final JSONOptions jsonOpts)
	{
		final String filterId = filter.getFilterId();
		final List<JSONDocumentFilterParam> jsonParameters = filter.getParameters()
				.stream()
				.filter(filterParam -> !filter.isInternalParameter(filterParam.getFieldName()))
				.map(filterParam -> JSONDocumentFilterParam.of(filterParam, jsonOpts))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(GuavaCollectors.toImmutableList());

		return new JSONDocumentFilter(filterId, filter.getCaption(jsonOpts.getAdLanguage()), jsonParameters);
	}

	@JsonProperty("filterId")
	String filterId;

	@JsonProperty("caption")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	String caption;

	@JsonProperty("parameters")
	List<JSONDocumentFilterParam> parameters;

	@JsonCreator
	private JSONDocumentFilter(
			@JsonProperty("filterId") @NonNull final String filterId,
			@JsonProperty("caption") @Nullable final String caption,
			@JsonProperty("parameters") @Nullable final List<JSONDocumentFilterParam> parameters)
	{
		Check.assumeNotEmpty(filterId, "filterId is not empty");

		this.filterId = filterId;
		this.caption = caption;
		this.parameters = parameters == null ? ImmutableList.of() : ImmutableList.copyOf(parameters);
	}
}
