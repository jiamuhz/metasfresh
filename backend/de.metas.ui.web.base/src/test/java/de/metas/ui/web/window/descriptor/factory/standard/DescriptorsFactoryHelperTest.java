package de.metas.ui.web.window.descriptor.factory.standard;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.LookupDescriptor;


public class DescriptorsFactoryHelperTest
{
	@Nested
	public class getValueClass
	{
		private LookupDescriptor newLookupDescritor(final Class<?> valueClass)
		{
			final LookupDescriptor lookupDescriptor = Mockito.mock(LookupDescriptor.class);
			Mockito.doReturn(valueClass).when(lookupDescriptor).getValueClass();
			return lookupDescriptor;
		}

		@Test
		public void multiValuesList()
		{
			final LookupDescriptor lookupDescriptor = newLookupDescritor(LookupValue.class);

			assertThat(DescriptorsFactoryHelper.getValueClass(DocumentFieldWidgetType.MultiValuesList, Optional.of(lookupDescriptor)))
					.isEqualTo(LookupValuesList.class);
		}
	}
}
