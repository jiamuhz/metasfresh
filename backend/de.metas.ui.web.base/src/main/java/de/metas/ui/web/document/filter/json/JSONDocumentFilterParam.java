package de.metas.ui.web.document.filter.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import lombok.Value;

import java.util.Optional;

  
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONDocumentFilterParam
{
	/**
	 * Creates {@link JSONDocumentFilterParam} from {@link DocumentFilterParam} if the given filter is not internal.
	 * 
	 * @return JSON document filter parameter
	 */
	/* package */static Optional<JSONDocumentFilterParam> of(final DocumentFilterParam filterParam, final JSONOptions jsonOpts)
	{
		// Don't convert internal filters
		if (filterParam.isSqlFilter())
		{
			// throw new IllegalArgumentException("Sql filters are not allowed to be converted to JSON filters: " + filterParam);
			return Optional.empty();
		}

		final String fieldName = filterParam.getFieldName();
		final Object jsonValue = Values.valueToJsonObject(filterParam.getValue(), jsonOpts);
		final Object jsonValueTo = Values.valueToJsonObject(filterParam.getValueTo(), jsonOpts);
		final JSONDocumentFilterParam jsonFilterParam = new JSONDocumentFilterParam(fieldName, jsonValue, jsonValueTo);
		return Optional.of(jsonFilterParam);
	}

	@JsonProperty("parameterName")
	String parameterName;

	@JsonProperty("value")
	Object value;

	@JsonProperty("valueTo")
	Object valueTo;

	@JsonCreator
	private JSONDocumentFilterParam(
			@JsonProperty("parameterName") final String parameterName,
			@JsonProperty("value") final Object value,
			@JsonProperty("valueTo") final Object valueTo)
	{
		this.parameterName = parameterName;
		this.value = value;
		this.valueTo = valueTo;
	}
}
