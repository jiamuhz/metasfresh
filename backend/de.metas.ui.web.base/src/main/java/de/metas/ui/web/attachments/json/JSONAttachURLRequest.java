package de.metas.ui.web.attachments.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.printing.esb.base.util.Check;
import lombok.Value;
import org.adempiere.exceptions.AdempiereException;

import java.net.URI;
import java.net.URISyntaxException;

 

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Value
public class JSONAttachURLRequest
{
	@JsonProperty("name")
	String name;
	@JsonProperty("url")
	String url;
	@JsonIgnore
	URI uri;

	@JsonCreator
	private JSONAttachURLRequest(
			@JsonProperty("name") final String name,
			@JsonProperty("url") final String url)
	{
		this.name = name;
		if (Check.isEmpty(name, true))
		{
			throw new AdempiereException("name cannot be empty");
		}
		
		this.url = url;
		try
		{
			this.uri = new URI(url);
		}
		catch (final URISyntaxException ex)
		{
			throw new AdempiereException("Invalid URL: " + url, ex);
		}
	}
}
