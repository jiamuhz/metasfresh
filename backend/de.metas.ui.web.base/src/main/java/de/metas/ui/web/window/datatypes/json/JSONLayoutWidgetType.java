  
package de.metas.ui.web.window.datatypes.json;

import com.google.common.collect.ImmutableBiMap;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.WidgetTypeStandardNumberPrecision;
import io.swagger.v3.oas.annotations.media.Schema;
import org.adempiere.exceptions.AdempiereException;

import javax.annotation.Nullable;
import java.util.OptionalInt;

@Schema(description = "widget-type")
public enum JSONLayoutWidgetType
{
	Text, LongText, Link, Password,

	Date, Time, DateTime, ZonedDateTime, DateRange, Timestamp,

	Integer, Number, Amount, Quantity, CostPrice,

	List, MultiValuesList, Lookup, Labels, Address, ProductAttributes,

	YesNo, Switch,

	Image, Color, BinaryData,

	Button, ActionButton, ProcessButton, ZoomIntoButton,

	InlineTab;

	@Nullable
	public static JSONLayoutWidgetType fromNullable(@Nullable final DocumentFieldWidgetType widgetType)
	{
		if (widgetType == null)
		{
			return null;
		}

		final JSONLayoutWidgetType jsonWidgetType = widgetType2json.get(widgetType);
		if (jsonWidgetType == null)
		{
			throw new AdempiereException("Cannot convert " + widgetType + " to " + JSONLayoutWidgetType.class);
		}

		return jsonWidgetType;
	}

	private static final ImmutableBiMap<DocumentFieldWidgetType, JSONLayoutWidgetType> widgetType2json = ImmutableBiMap.<DocumentFieldWidgetType, JSONLayoutWidgetType>builder()
			.put(DocumentFieldWidgetType.Text, JSONLayoutWidgetType.Text)
			.put(DocumentFieldWidgetType.LongText, JSONLayoutWidgetType.LongText)
			.put(DocumentFieldWidgetType.URL, JSONLayoutWidgetType.Link)
			.put(DocumentFieldWidgetType.Password, JSONLayoutWidgetType.Password)
			.put(DocumentFieldWidgetType.LocalDate, JSONLayoutWidgetType.Date)
			.put(DocumentFieldWidgetType.LocalTime, JSONLayoutWidgetType.Time)
			.put(DocumentFieldWidgetType.ZonedDateTime, JSONLayoutWidgetType.ZonedDateTime)
			.put(DocumentFieldWidgetType.Timestamp, JSONLayoutWidgetType.Timestamp)
			.put(DocumentFieldWidgetType.DateRange, JSONLayoutWidgetType.DateRange)
			.put(DocumentFieldWidgetType.Integer, JSONLayoutWidgetType.Integer)
			.put(DocumentFieldWidgetType.Number, JSONLayoutWidgetType.Number)
			.put(DocumentFieldWidgetType.Amount, JSONLayoutWidgetType.Amount)
			.put(DocumentFieldWidgetType.Quantity, JSONLayoutWidgetType.Quantity)
			.put(DocumentFieldWidgetType.CostPrice, JSONLayoutWidgetType.CostPrice)
			.put(DocumentFieldWidgetType.List, JSONLayoutWidgetType.List)
			.put(DocumentFieldWidgetType.MultiValuesList, JSONLayoutWidgetType.MultiValuesList)
			.put(DocumentFieldWidgetType.Lookup, JSONLayoutWidgetType.Lookup)
			.put(DocumentFieldWidgetType.Labels, JSONLayoutWidgetType.Labels)
			.put(DocumentFieldWidgetType.Address, JSONLayoutWidgetType.Address)
			.put(DocumentFieldWidgetType.ProductAttributes, JSONLayoutWidgetType.ProductAttributes)
			.put(DocumentFieldWidgetType.YesNo, JSONLayoutWidgetType.YesNo)
			.put(DocumentFieldWidgetType.Switch, JSONLayoutWidgetType.Switch)
			.put(DocumentFieldWidgetType.Image, JSONLayoutWidgetType.Image)
			.put(DocumentFieldWidgetType.Color, JSONLayoutWidgetType.Color)
			.put(DocumentFieldWidgetType.BinaryData, JSONLayoutWidgetType.BinaryData)
			.put(DocumentFieldWidgetType.Button, JSONLayoutWidgetType.Button)
			.put(DocumentFieldWidgetType.ActionButton, JSONLayoutWidgetType.ActionButton)
			.put(DocumentFieldWidgetType.ProcessButton, JSONLayoutWidgetType.ProcessButton)
			.put(DocumentFieldWidgetType.ZoomIntoButton, JSONLayoutWidgetType.ZoomIntoButton)
			.put(DocumentFieldWidgetType.InlineTab, JSONLayoutWidgetType.InlineTab)
			.build();

	public OptionalInt getStandardNumberPrecision()
	{
		final ImmutableBiMap<JSONLayoutWidgetType, DocumentFieldWidgetType> json2widgetType = widgetType2json.inverse();
		final DocumentFieldWidgetType widgetType = json2widgetType.get(this);
		if (widgetType == null)
		{
			return OptionalInt.empty();
		}

		return WidgetTypeStandardNumberPrecision.DEFAULT.getMinPrecision(widgetType);
	}
}
