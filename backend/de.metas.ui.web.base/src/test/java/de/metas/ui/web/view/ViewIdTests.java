package de.metas.ui.web.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.util.Random;

import org.adempiere.exceptions.AdempiereException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import de.metas.JsonObjectMapperHolder;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;

 

public class ViewIdTests
{
	private final Random random = new Random(System.currentTimeMillis());

	private ObjectMapper jsonObjectMapper;

	@BeforeEach
	public void init()
	{
		jsonObjectMapper = JsonObjectMapperHolder.newJsonObjectMapper();
	}

	@Nested
	public class withWindowId
	{
		@Test
		public void standardCase()
		{
			final ViewId viewId = ViewId.ofViewIdString("123-abcde");
			assertViewIdMatching(viewId, "123", "abcde");

			final ViewId viewId2 = viewId.withWindowId(WindowDocumentTypeId.of(456));
			assertViewIdMatching(viewId2, "456", "abcde");
		}

		@Test
		public void sameWindowId()
		{
			final ViewId viewId = ViewId.ofViewIdString("123-abcde");
			final ViewId viewId2 = viewId.withWindowId(WindowDocumentTypeId.of(123));
			assertThat(viewId).isSameAs(viewId2);
		}
	}

	private static final void assertViewIdMatching(final ViewId viewId, final String windowIdStr, final String viewIdPart)
	{
		final String viewIdStr = windowIdStr + "-" + viewIdPart;

		assertThat(viewId.getWindowId()).isEqualTo(WindowDocumentTypeId.fromJson(windowIdStr));
		assertThat(viewId.getViewId()).isEqualTo(viewIdStr);
		assertThat(viewId.toJson()).isEqualTo(viewIdStr);
		assertThat(viewId.getViewIdPart()).isEqualTo(viewIdPart);
		assertThat(viewId.getPart(0)).isEqualTo(windowIdStr);
		assertThat(viewId.getPart(1)).isEqualTo(viewIdPart);
	}

	@Test
	public void test_ofViewIdString_CorrectWindowId()
	{
		final ViewId viewId = randomViewId();

		final WindowDocumentTypeId expectedWindowId = viewId.getWindowId();
		final ViewId viewId2 = ViewId.ofViewIdString(viewId.getViewId(), expectedWindowId);

		assertThat(viewId2).isEqualTo(viewId);
	}

	@Test
	public void test_of_CorrectWindowId()
	{
		final ViewId viewId = randomViewId();

		final String expectedWindowIdStr = viewId.getWindowId().toJson();
		final ViewId viewId2 = ViewId.of(expectedWindowIdStr, viewId.toJson());

		assertThat(viewId2).isEqualTo(viewId);
	}

	@Test
	public void test_ofViewIdString_NullWindowId()
	{
		final ViewId viewId = randomViewId();

		final WindowDocumentTypeId expectedWindowId = null;
		final ViewId viewId2 = ViewId.ofViewIdString(viewId.getViewId(), expectedWindowId);

		assertThat(viewId2).isEqualTo(viewId);
	}

	@Test
	public void test_of_NullWindowId()
	{
		final ViewId viewId = randomViewId();

		final String expectedWindowIdStr = null;
		final ViewId viewId2 = ViewId.of(expectedWindowIdStr, viewId.toJson());

		assertThat(viewId2).isEqualTo(viewId);
	}

	@Test
	public void test_ofViewIdString_WrongWindowId()
	{
		final ViewId viewId = randomViewId();

		final WindowDocumentTypeId expectedWindowId = randomWindowIdButNot(viewId.getWindowId());

		assertThatThrownBy(() -> ViewId.ofViewIdString(viewId.getViewId(), expectedWindowId))
				.as("Exception is expected because windowId are not matching")
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void test_of_WrongWindowId()
	{
		final ViewId viewId = randomViewId();

		final String expectedWindowIdStr = randomWindowIdButNot(viewId.getWindowId()).toJson();

		assertThatThrownBy(() -> ViewId.of(expectedWindowIdStr, viewId.toJson()))
				.as("Exception is expected because windowId are not matching")
				.isInstanceOf(IllegalArgumentException.class);
	}

	private final ViewId randomViewId()
	{
		final WindowDocumentTypeId windowId = randomWindowId();
		return ViewId.random(windowId);
	}

	private final WindowDocumentTypeId randomWindowId()
	{
		final int windowIdInt = random.nextInt(9000000) + 1;
		return WindowDocumentTypeId.of(windowIdInt);
	}

	private final WindowDocumentTypeId randomWindowIdButNot(final WindowDocumentTypeId windowIdToExclude)
	{
		WindowDocumentTypeId windowId = randomWindowId();
		while (windowId.equals(windowIdToExclude))
		{
			windowId = randomWindowId();
		}
		return windowId;
	}

	@Test
	public void test_getOtherParts_NoElementExpected()
	{
		final ViewId viewId = ViewId.ofViewIdString("packingHUs-351a5c70a1ff4d4fb2852468d6b5b01b");
		assertThat(viewId.getOtherParts()).isEqualTo(ImmutableList.of());
	}

	@Test
	public void test_getOtherParts_OneElementExpected()
	{
		final ViewId viewId = ViewId.ofViewIdString("packingHUs-351a5c70a1ff4d4fb2852468d6b5b01b-1000103");
		assertThat(viewId.getOtherParts()).isEqualTo(ImmutableList.of("1000103"));
	}

	@Test
	public void test_getOtherParts_ThreeElementExpected()
	{
		final ViewId viewId = ViewId.ofViewIdString("packingHUs-351a5c70a1ff4d4fb2852468d6b5b01b-1-2-3");
		assertThat(viewId.getOtherParts()).isEqualTo(ImmutableList.of("1", "2", "3"));
	}

	@Test
	public void testSerializeDeserialize()
	{
		testSerializeDeserialize(ViewId.ofParts(WindowDocumentTypeId.fromJson("windowId"), "viewIdPart"));
		testSerializeDeserialize(ViewId.ofParts(WindowDocumentTypeId.fromJson("windowId"), "viewIdPart", "otherPart1", "otherPart2"));
	}

	private void testSerializeDeserialize(final ViewId viewId)
	{
		final ViewId viewIdDeserialized = fromJson(toJson(viewId));
		assertThat(viewIdDeserialized).isEqualTo(viewId);
	}

	private String toJson(final ViewId viewId)
	{
		try
		{
			return jsonObjectMapper.writeValueAsString(viewId);
		}
		catch (final JsonProcessingException e)
		{
			throw new AdempiereException("Failed serializing " + viewId, e);
		}
	}

	private ViewId fromJson(final String json)
	{
		try
		{
			return jsonObjectMapper.readValue(json, ViewId.class);
		}
		catch (final IOException e)
		{
			throw new AdempiereException("Failed deserializing:\n" + json, e);
		}
	}
}
