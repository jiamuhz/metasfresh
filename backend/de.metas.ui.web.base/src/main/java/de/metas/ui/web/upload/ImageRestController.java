package de.metas.ui.web.upload;

import de.metas.i18n.BooleanWithReason;
import de.metas.ui.web.cache.ETagResponseEntityBuilder;
import de.metas.ui.web.config.WebConfig;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.session.UserSession;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import lombok.NonNull;
import org.compiere.model.I_AD_Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@RestController
@RequestMapping(value = ImageRestController.ENDPOINT)
public class ImageRestController
{
	public static final String ENDPOINT = WebConfig.ENDPOINT_ROOT + "/image";

	private final UserSession userSession;
	private final WebuiImageService imageService;

	public ImageRestController(
			@NonNull final UserSession userSession,
			@NonNull final WebuiImageService imageService)
	{
		this.userSession = userSession;
		this.imageService = imageService;
	}

	private JSONOptions newJSONOptions()
	{
		return JSONOptions.of(userSession);
	}

	@PostMapping
	public WebuiImageId uploadImage(@RequestParam("file") final MultipartFile file) throws IOException
	{
		userSession.assertLoggedIn();
		return imageService.uploadImage(file);
	}

	@GetMapping("/{imageId}")
	@ResponseBody
	public ResponseEntity<byte[]> getImage(
			@PathVariable("imageId") final int imageIdInt,
			@RequestParam(name = "maxWidth", required = false, defaultValue = "-1") final int maxWidth,
			@RequestParam(name = "maxHeight", required = false, defaultValue = "-1") final int maxHeight,
			final WebRequest request)
	{
		userSession.assertLoggedIn();

		final WebuiImageId imageId = WebuiImageId.ofRepoIdOrNull(imageIdInt);
		return ETagResponseEntityBuilder.ofETagAware(request, getWebuiImage(imageId, maxWidth, maxHeight))
				.includeLanguageInETag()
				.cacheMaxAge(userSession.getHttpCacheMaxAge())
				.jsonOptions(this::newJSONOptions)
				.toResponseEntity((responseBuilder, webuiImage) -> webuiImage.toResponseEntity(responseBuilder));
	}

	private WebuiImage getWebuiImage(final WebuiImageId imageId, final int maxWidth, final int maxHeight)
	{
		final WebuiImage image = imageService.getWebuiImage(imageId, maxWidth, maxHeight);
		assertUserHasAccess(image);
		return image;
	}

	private void assertUserHasAccess(final WebuiImage image)
	{
		final BooleanWithReason hasAccess = userSession.getUserRolePermissions().checkCanView(image.getAdClientId(), image.getAdOrgId(), I_AD_Image.Table_ID, image.getAdImageId().getRepoId());
		if (hasAccess.isFalse())
		{
			throw new EntityNotFoundException(hasAccess.getReason());
		}
	}
}
