package de.metas.ui.web.menu.datatypes.json;

import java.util.List;
import java.util.Set;

import org.adempiere.exceptions.AdempiereException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;

import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent;
import de.metas.ui.web.window.datatypes.json.JSONDocumentChangedEvent.JSONOperation;



public class JSONPatchMenuNodeRequest
{
	public static final JSONPatchMenuNodeRequest ofChangeEvents(final List<JSONDocumentChangedEvent> events)
	{

		if (events == null || events.isEmpty())
		{
			throw new AdempiereException("No events");
		}

		Boolean favorite = null;
		if (events != null && !events.isEmpty())
		{
			for (final JSONDocumentChangedEvent event : events)
			{
				if (!event.isReplace())
				{
					throw new AdempiereException("Only " + JSONOperation.replace + " are supported")
							.setParameter("event", event);
				}

				if (PATH_Favorite.equals(event.getPath()))
				{
					favorite = event.getValueAsBoolean(null);
					if (favorite == null)
					{
						throw new AdempiereException("Invalid value for " + PATH_Favorite)
								.setParameter("event", event);
					}
				}
				else
				{
					throw new AdempiereException("Unknown path: " + event.getPath())
							.setParameter("event", event)
							.setParameter("availablePaths", PATHS);
				}
			}
		}

		// Make sure we have at least on actual change
		if (favorite == null)
		{
			throw new AdempiereException("None of the requested changes are supported")
					.setParameter("events", events);
		}

		return new JSONPatchMenuNodeRequest(favorite);
	}

	private static final String PATH_Favorite = "favorite";
	private static final Set<String> PATHS = ImmutableSet.of(PATH_Favorite);

	@JsonProperty("favorite")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final Boolean favorite;

	@JsonCreator
	private JSONPatchMenuNodeRequest(@JsonProperty("favorite") final Boolean favorite)
	{
		this.favorite = favorite;
	}

	public Boolean getFavorite()
	{
		return favorite;
	}
}
