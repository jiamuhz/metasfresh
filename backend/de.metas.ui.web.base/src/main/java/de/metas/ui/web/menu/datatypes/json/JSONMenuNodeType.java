package de.metas.ui.web.menu.datatypes.json;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import de.metas.ui.web.menu.MenuNode.MenuNodeType;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;

public enum JSONMenuNodeType
{
	group, //
	window, newRecord, //
	process, report, //
	board, //
	calendar, //
	;

	@Nullable
	public static JSONMenuNodeType ofNullable(@Nullable final MenuNodeType type)
	{
		if (type == null)
		{
			return null;
		}

		final JSONMenuNodeType jsonType = type2json.get(type);
		if (jsonType == null)
		{
			throw new AdempiereException("Cannot convert " + type + " to " + JSONMenuNodeType.class);
		}
		return jsonType;
	}

	private static final BiMap<MenuNodeType, JSONMenuNodeType> type2json = ImmutableBiMap.<MenuNodeType, JSONMenuNodeType> builder()
			.put(MenuNodeType.Group, group)
			.put(MenuNodeType.Window, window)
			.put(MenuNodeType.NewRecord, newRecord)
			.put(MenuNodeType.Process, process)
			.put(MenuNodeType.Report, report)
			.put(MenuNodeType.Board, board)
			.put(MenuNodeType.Calendar, calendar)
			.build();

	public final MenuNodeType toMenuNodeType()
	{
		final MenuNodeType type = type2json.inverse().get(this);
		if (type == null)
		{
			throw new AdempiereException("Cannot convert " + type + " to " + MenuNodeType.class);
		}
		return type;
	}
}
