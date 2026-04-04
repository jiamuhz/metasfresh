 

package de.metas.ui.web.window.model.sql;

import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class SqlValueConvertersTest
{
	@Nested
	class convertToPOValue
	{
		@Nested
		class toInteger
		{
			@Test
			void emptyString()
			{
				Assertions.assertThat(
						SqlValueConverters.convertToPOValue("", "NotRelevant", DocumentFieldWidgetType.Lookup, Integer.class)
				).isNull();
			}

			/**
			 * needed for some User Query legacy BLs
			 */
			@Test
			void floatString()
			{
				Assertions.assertThat(
						SqlValueConverters.convertToPOValue("4030153.000000000000", "NotRelevant", DocumentFieldWidgetType.Integer, Integer.class)
				).isEqualTo(4030153);
			}
		}

		@Nested
		class toBigDecimal
		{
			@Test
			void emptyString()
			{
				Assertions.assertThat(
						SqlValueConverters.convertToPOValue("", "NotRelevant", DocumentFieldWidgetType.Amount, BigDecimal.class)
				).isNull();
			}
		}

	}
}


