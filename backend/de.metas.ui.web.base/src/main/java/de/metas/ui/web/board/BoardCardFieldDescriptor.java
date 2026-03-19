package de.metas.ui.web.board;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.common.collect.ImmutableSet;

import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlOrderByValue;
import de.metas.ui.web.window.descriptor.sql.SqlSelectDisplayValue;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;



@Builder
@Value
public class BoardCardFieldDescriptor
{
	@NonNull
	private final ITranslatableString caption;

	@NonNull
	private final String fieldName;
	@NonNull
	private final DocumentFieldWidgetType widgetType;
	/** Set of "select value" SQLs required to load the value */
	@NonNull
	private final ImmutableSet<String> sqlSelectValues;

	private final boolean usingDisplayColumn;
	private final SqlSelectDisplayValue sqlSelectDisplayValue;

	@NonNull
	private final SqlOrderByValue sqlOrderBy;

	/** Retrieves a particular field from given {@link ResultSet}. */
	@FunctionalInterface
	public interface BoardFieldLoader
	{
		Object retrieveValueAsJson(ResultSet rs, String adLanguage) throws SQLException;
	}

	@NonNull
	private final BoardFieldLoader fieldLoader;
}
