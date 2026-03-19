package de.metas.ui.web.session;

import de.metas.i18n.Language;
import de.metas.organization.ClientAndOrgId;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.session.json.JSONUserSession;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.model.DocumentCollection;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.service.ISysConfigBL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

  
@RestController
@RequestMapping(UserSessionRestController.ENDPOINT)
public class UserSessionRestController
{
	public static final String ENDPOINT = WebConfig.ENDPOINT_ROOT + "/userSession";

	private static final String SYSCONFIG_SETTINGS_PREFIX = "webui.frontend.";

	private final ISysConfigBL sysConfigBL = Services.get(ISysConfigBL.class);
	private final UserSession userSession;
	private final UserSessionRepository userSessionRepo;
	private final DocumentCollection documentCollection;

	public UserSessionRestController(
			@NonNull final UserSession userSession,
			@NonNull final UserSessionRepository userSessionRepo,
			@NonNull final DocumentCollection documentCollection)
	{
		this.userSession = userSession;
		this.userSessionRepo = userSessionRepo;
		this.documentCollection = documentCollection;
	}

	@GetMapping
	public JSONUserSession getAll()
	{
		final Map<String, String> settings = sysConfigBL.getValuesForPrefix(
				SYSCONFIG_SETTINGS_PREFIX,
				true,
				ClientAndOrgId.ofClientAndOrg(userSession.getClientId(), userSession.getOrgId()));

		return new JSONUserSession(userSession, settings);
	}

	@PutMapping("/language")
	public JSONLookupValue setLanguage(@RequestBody final JSONLookupValue value)
	{
		final String adLanguage = value.getKey();
		userSessionRepo.setAD_Language(userSession.getLoggedUserId(), adLanguage);
		documentCollection.cacheReset(false); // don't evict unsaved documents from the cache, because they would be lost entirely

		return getLanguage();
	}

	@GetMapping("/language")
	public JSONLookupValue getLanguage()
	{
		final Language language = userSession.getLanguage();
		return JSONLookupValue.of(language.getAD_Language(), language.getName());
	}
}
