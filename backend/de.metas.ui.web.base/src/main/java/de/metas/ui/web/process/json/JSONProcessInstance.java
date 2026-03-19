package de.metas.ui.web.process.json;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.window.datatypes.json.JSONDocumentField;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.GuavaCollectors;

  
@SuppressWarnings("serial")
public class JSONProcessInstance implements Serializable
{
	public static JSONProcessInstance of(final IProcessInstanceController pinstance, final JSONOptions jsonOpts)
	{
		return new JSONProcessInstance(pinstance, jsonOpts);
	}

	@JsonProperty("pinstanceId")
	private final String pinstanceId;

	@JsonProperty("fieldsByName")
	private final Map<String, JSONDocumentField> parametersByName;

	@JsonProperty("startProcessDirectly")
	private final boolean startProcessDirectly;

	private JSONProcessInstance(final IProcessInstanceController pinstance, final JSONOptions jsonOpts)
	{
		pinstanceId = pinstance.getInstanceId().toJson();

		parametersByName = pinstance.getParameters()
				.stream()
				.map(param -> JSONDocumentField.ofProcessParameter(param, jsonOpts))
				.collect(GuavaCollectors.toImmutableMapByKey(JSONDocumentField::getField));

		startProcessDirectly = pinstance.isStartProcessDirectly();
	}
}
