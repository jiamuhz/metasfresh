package de.metas.ui.web.document.references.controller;

import com.google.common.collect.ImmutableList;
import de.metas.i18n.AdMessageKey;
import de.metas.i18n.IMsgBL;
import de.metas.ui.web.document.references.WebuiDocumentReference;
import de.metas.ui.web.document.references.json.JSONDocumentReference;
import de.metas.ui.web.document.references.json.JSONDocumentReferencesGroup;
import de.metas.ui.web.document.references.json.JSONDocumentReferencesGroup.JSONDocumentReferencesGroupBuilder;
import de.metas.ui.web.document.references.json.JSONDocumentReferencesGroupList;
import de.metas.ui.web.menu.MenuNode;
import de.metas.ui.web.menu.MenuTree;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.lang.UIDStringUtil;
import lombok.Builder;
import lombok.NonNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

 

/**
 * Aggregates {@link WebuiDocumentReference}s to {@link JSONDocumentReferencesGroupList}s.
 */
final class JSONDocumentReferencesGroupsAggregator
{
	private static final AdMessageKey MSG_MiscGroupCaption = AdMessageKey.of("DocumentReferences.group.Others");

	// Sort by Caption, but keep the "misc group" last
	private static final Comparator<JSONDocumentReferencesGroup> sorting = Comparator.<JSONDocumentReferencesGroup> //
			comparingInt(group -> group.isMiscGroup() ? 1 : 0)
			.thenComparing(JSONDocumentReferencesGroup::getCaption);

	private final MenuTree menuTree;
	private final String othersMenuCaption;
	private final JSONOptions jsonOpts;

	private final String othersGroupId = "_others_" + UIDStringUtil.createRandomUUID();
	private final HashMap<String, JSONDocumentReferencesGroupBuilder> groupsBuilders = new HashMap<>();

	@Builder
	private JSONDocumentReferencesGroupsAggregator(
			@NonNull final MenuTree menuTree,
			@NonNull final IMsgBL msgBL,
			@NonNull final JSONOptions jsonOpts)
	{
		this.menuTree = menuTree;

		othersMenuCaption = msgBL
				.getTranslatableMsgText(MSG_MiscGroupCaption)
				.translate(jsonOpts.getAdLanguage());

		this.jsonOpts = jsonOpts;
	}

	public JSONDocumentReferencesGroupsAggregator addAll(@NonNull final Collection<WebuiDocumentReference> documentReferences)
	{
		documentReferences.forEach(this::add);
		return this;
	}

	public JSONDocumentReferencesGroupsAggregator add(@NonNull final WebuiDocumentReference documentReference)
	{
		final JSONDocumentReference jsonDocumentReference = JSONDocumentReference.of(documentReference, jsonOpts);
		if (jsonDocumentReference == null)
		{
			return this;
		}

		final MenuNode topLevelMenuGroup = menuTree.getTopLevelMenuGroupOrNull(documentReference.getTargetWindow().getWindowId());
		final String topLevelMenuGroupId = topLevelMenuGroup != null ? topLevelMenuGroup.getId() : othersGroupId;

		final JSONDocumentReferencesGroupBuilder groupBuilder = groupsBuilders.computeIfAbsent(topLevelMenuGroupId, k -> {
			final boolean isMiscGroup = topLevelMenuGroup == null;
			final String caption = topLevelMenuGroup != null ? topLevelMenuGroup.getCaption() : othersMenuCaption;
			return JSONDocumentReferencesGroup.builder().caption(caption).isMiscGroup(isMiscGroup);
		});

		groupBuilder.reference(jsonDocumentReference);

		return this;
	}

	private ImmutableList<JSONDocumentReferencesGroup> flushGroups()
	{
		if (groupsBuilders.isEmpty())
		{
			return ImmutableList.of();
		}

		final ImmutableList<JSONDocumentReferencesGroup> groups = groupsBuilders.values()
				.stream()
				.map(JSONDocumentReferencesGroupBuilder::build)
				.filter(group -> !group.isEmpty())
				.sorted(sorting)
				.collect(ImmutableList.toImmutableList());

		groupsBuilders.clear();

		return groups;
	}

	public void addAndPublish(
			@NonNull final List<WebuiDocumentReference> documentReferences,
			@NonNull final JSONDocumentReferencesEventPublisher publisher)
	{
		addAll(documentReferences);
		publish(publisher);
	}

	private void publish(final @NonNull JSONDocumentReferencesEventPublisher publisher)
	{
		final ImmutableList<JSONDocumentReferencesGroup> groups = flushGroups();
		publisher.publishPartialResults(groups);
	}
}
