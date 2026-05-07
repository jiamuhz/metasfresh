package de.metas.security.permissions.record_access;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.adempiere.util.lang.impl.TableRecordReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.metas.JsonObjectMapperHolder;
import de.metas.security.Principal;
import de.metas.security.permissions.Access;
import de.metas.user.UserId;
import lombok.NonNull;

public class RecordAccessTest
{
	private ObjectMapper jsonObjectMapper;

	@BeforeEach
	public void init()
	{
		jsonObjectMapper = JsonObjectMapperHolder.newJsonObjectMapper();
	}

	private void testSerializeDeserialize(@NonNull final Object obj) throws IOException
	{
		System.out.println("object: " + obj);
		final String json = jsonObjectMapper.writeValueAsString(obj);
		System.out.println("json: " + json);

		final Object obj2 = jsonObjectMapper.readValue(json, obj.getClass());
		System.out.println("object deserialized: " + obj2);
		assertThat(obj2).isEqualTo(obj);

		final String json2 = jsonObjectMapper.writeValueAsString(obj2);
		System.out.println("object json again: " + json2);
		assertThat(json2).isEqualTo(json);
	}

	@Test
	public void testSerializeDeserialize() throws Exception
	{
		testSerializeDeserialize(RecordAccess.builder()
				.recordRef(TableRecordReference.of("MyTable", 123))
				.principal(Principal.userId(UserId.METASFRESH))
				.permission(Access.WRITE)
				.issuer(PermissionIssuer.MANUAL)
				.createdBy(UserId.ofRepoId(111))
				.description("some description")
				//
				.id(RecordAccessId.ofRepoId(333))
				.parentId(RecordAccessId.ofRepoId(444))
				.rootId(RecordAccessId.ofRepoId(555))
				//
				.build());
	}

}
