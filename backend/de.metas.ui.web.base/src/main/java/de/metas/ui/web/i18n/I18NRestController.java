package de.metas.ui.web.i18n;

import de.metas.i18n.AdMessagesTree;
import de.metas.i18n.IMsgBL;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.session.UserSession;
import de.metas.util.Services;
import de.metas.util.StringUtils;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

 

@RestController
@RequestMapping(I18NRestController.ENDPOINT)
public class I18NRestController
{
	public static final String ENDPOINT = WebConfig.ENDPOINT_ROOT + "/i18n";
	private final IMsgBL msgBL = Services.get(IMsgBL.class);
	private final UserSession userSession;

	private static final String AD_MESSAGE_PREFIX = "webui.";
	public I18NRestController(
			@NonNull final UserSession userSession)
	{
		this.userSession = userSession;
	}

	@GetMapping("/messages")
	public Map<String, Object> getMessages(@RequestParam(name = "filter", required = false) final String filterString,
										   @RequestParam(name = "lang", required = false) final String adLanguageParam)
	{
		final String adLanguage = StringUtils.trimBlankToOptional(adLanguageParam).orElseGet(userSession::getAD_Language);

		final AdMessagesTree messagesTree = msgBL.messagesTree()
				.adMessagePrefix(AD_MESSAGE_PREFIX)
				.filterAdMessageStartingWithIgnoringCase(filterString)
				.removePrefix(true)
				.load(adLanguage);

		return toJson(messagesTree);
	}

	private static Map<String, Object> toJson(@NonNull final AdMessagesTree messagesTree)
	{
		final LinkedHashMap<String, Object> json = new LinkedHashMap<>();
		json.put("_language", messagesTree.getAdLanguage());
		json.putAll(messagesTree.getMap());
		return json;
	}
}
