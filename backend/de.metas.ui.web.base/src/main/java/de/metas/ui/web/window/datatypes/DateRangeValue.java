package de.metas.ui.web.window.datatypes;

import java.time.LocalDate;

import lombok.Value;

 

@Value(staticConstructor = "of")
public final class DateRangeValue
{
	private final LocalDate from;
	private final LocalDate to;
}
