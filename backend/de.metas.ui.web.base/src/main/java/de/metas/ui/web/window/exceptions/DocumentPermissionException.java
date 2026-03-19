package de.metas.ui.web.window.exceptions;

import de.metas.i18n.AdMessageKey;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import org.adempiere.exceptions.AdempiereException;



/**
 * Exception thrown when a required document permission was not granted.
 *
 * 
 */
public class DocumentPermissionException extends AdempiereException
{
	private static final long serialVersionUID = -6951387618389868436L;

	public enum DocumentPermission
	{
		WindowAccess,
		/**
		 * Document view access
		 */
		View,
		/**
		 * Document update access
		 */
		Update,
	}

	private static final AdMessageKey MSG_NoAccess = AdMessageKey.of("NoAccess");

	public static DocumentPermissionException noAccess(final DocumentPermission permissionRequired)
	{
		return new DocumentPermissionException(permissionRequired, TranslatableStrings.builder().appendADMessage(MSG_NoAccess).build());
	}

	@Deprecated
	public static DocumentPermissionException of(final DocumentPermission permissionRequired, final String errorMessage)
	{
		return new DocumentPermissionException(permissionRequired, TranslatableStrings.parse(errorMessage));
	}

	public static DocumentPermissionException of(final DocumentPermission permissionRequired, final ITranslatableString errorMessage)
	{
		return new DocumentPermissionException(permissionRequired, errorMessage);
	}

	private DocumentPermissionException(final DocumentPermission permissionRequired, final ITranslatableString errorMessage)
	{
		super(errorMessage);
		setParameter("PermissionRequired", permissionRequired);
	}
}
