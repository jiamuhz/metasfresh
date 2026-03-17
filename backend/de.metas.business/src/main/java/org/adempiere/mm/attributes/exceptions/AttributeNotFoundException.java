package org.adempiere.mm.attributes.exceptions;

/** */

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.mm.attributes.AttributeId;
import org.adempiere.mm.attributes.api.IAttributeSet;
import org.compiere.model.I_M_Attribute;

import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStringBuilder;
import de.metas.i18n.TranslatableStrings;

public class AttributeNotFoundException extends AdempiereException
{
	/**
	 *
	 */
	private static final long serialVersionUID = -7800379395702483714L;

	private final I_M_Attribute attribute;
	private final IAttributeSet attributeSet;

	public AttributeNotFoundException(final I_M_Attribute attribute, final Object attributeSetObj)
	{
		super(buildMsg(attribute, attributeSetObj));

		this.attribute = attribute;
		if (attributeSetObj instanceof IAttributeSet)
		{
			attributeSet = (IAttributeSet)attributeSetObj;
		}
		else
		{
			attributeSet = null;
		}
	}

	public AttributeNotFoundException(final String attributeValueKey, final Object attributeSetObj)
	{
		super(buildMsg(attributeValueKey, attributeSetObj));
		attribute = null;
		if (attributeSetObj instanceof IAttributeSet)
		{
			attributeSet = (IAttributeSet)attributeSetObj;
		}
		else
		{
			attributeSet = null;
		}
	}

	public AttributeNotFoundException(final AttributeId attributeId, final Object attributeSetObj)
	{
		this(attributeId.toString(), attributeSetObj);
	}

	private static final String toString(final I_M_Attribute attribute)
	{
		if (attribute == null)
		{
			return "<NULL>";
		}
		else
		{
			return attribute.getName();
		}

	}

	private static final ITranslatableString buildMsg(final I_M_Attribute attribute, final Object attributeSetObj)
	{
		final String attributeStr = toString(attribute);
		return buildMsg(attributeStr, attributeSetObj);
	}

	private static final ITranslatableString buildMsg(final String attributeStr, final Object attributeSetObj)
	{
		final TranslatableStringBuilder builder = TranslatableStrings.builder();
		builder.append("Attribute ");

		if (attributeStr == null)
		{
			builder.append("<NULL>");
		}
		else
		{
			builder.append("'").append(attributeStr).append("'");

		}

		builder.append(" was not found");

		if (attributeSetObj != null)
		{
			builder.append(" for ").append(attributeSetObj.toString());
		}

		return builder.build();
	}

	public I_M_Attribute getM_Attribute()
	{
		return attribute;
	}

	public IAttributeSet getAttributeSet()
	{
		return attributeSet;
	}
}
