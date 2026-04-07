package de.metas.ui.web.window.datatypes;

import lombok.Getter;
import lombok.NonNull;

 

/**
 * 每种文档 都有对应的 Layout
 */

public enum DocumentType
{
	Window("W"), //
	Process("P"), //
	ProductAttributes("ASI"), //
	ViewRecordAttributes("VRA"), //
	Address("ADDR"), //
	QuickInput("QI") //
	;

	@Getter
	private final String symbol;

	DocumentType(@NonNull final String symbol)
	{
		this.symbol = symbol;
	}
}
