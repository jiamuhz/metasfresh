/**
 *
 */
package org.adempiere.util;

/** */


import java.awt.Color;

/**
 * Tools to convert colors from different formats
 * @author tsa
 *
 */
public class Colors
{
	/**
	 * Get {@link Color} from an html hex color
	 *
	 * @param htmlColor html color (e.g. #aa00cc, aa00cc, aa)
	 * @return null if the given string is empty or can'T be parsed into a color.
	 */
	public static Color toColor(String htmlColor)
	{
		if (htmlColor == null || htmlColor.length() == 0)
			return null;

		String hex = htmlColor;
		if (hex.startsWith("#"))
			hex = hex.substring(1);
		if (hex.length() < 6)
		{
			StringBuffer sb = new StringBuffer(hex);
			while(sb.length() < 6)
				sb.append("0");
			hex = sb.toString();
		}

		final int rgb;
		try
		{
			rgb = Integer.parseInt(hex, 16);
		}
		catch (NumberFormatException e)
		{
			return null;
		}

		return new Color(rgb);
	}

	public static String toHtmlColor(Color color)
	{
		return "#" + Integer.toHexString(color.getRGB() | 0xFF000000).substring(2);
	}
}
