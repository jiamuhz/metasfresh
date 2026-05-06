package de.metas.ui.web.view.descriptor.annotation;

import de.metas.ui.web.view.json.JSONViewDataType;
import de.metas.ui.web.window.datatypes.MediaType;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.ViewEditorRenderMode;
import de.metas.ui.web.window.descriptor.WidgetSize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;



/**
 * Note: take a look at {@link ViewColumnHelper} to see how the annotation is processed.
 * 用于标记 IViewRow 中 列
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewColumn
{
	/**
	 * Field name. If missing or empty, the {@link Field#getName()} of the annotated field will be used.
	 * 翻译来自 AD_Element
	 */
	String fieldName() default "";

	DocumentFieldWidgetType widgetType();

	/**
	 * List AD_Reference_ID; to be used when {@link #widgetType()} is lookup
	 */
	int listReferenceId() default -1;

	/**
	 * Column's caption identified by AD_Message/AD_Element.
	 */
	String captionKey() default "";

	/**
	 * From where to fetch the caption's translation
	 */
	TranslationSource captionTranslationSource() default TranslationSource.DEFAULT;

	/**
	 * true if user is allowed to sort by this column
	 */
	boolean sorting() default true;

	/**
	 * Display sequence number.
	 * Overridden by {@link ViewColumnLayout#seqNo()}.
	 */
	int seqNo() default Integer.MIN_VALUE;

	ViewColumnLayout.Displayed displayed() default ViewColumnLayout.Displayed.TRUE;

	/**
	 * See {@link ViewColumnLayout.Displayed#SYSCONFIG}.
	 * If it evaluates to {@code null} or empty string, then go with {@link #defaultDisplaySysConfig()}.
	 */
	String displayedSysConfigPrefix() default "";

	boolean defaultDisplaySysConfig() default false;

	/**
	 * Column layout profiles.
	 * <p>
	 * If empty, and no defaults like {@link #seqNo()} were defined
	 * then the column won't be displayed in any of {@link JSONViewDataType} profiles.
	 */
	ViewColumnLayout[] layouts() default {};

	ViewEditorRenderMode editor() default ViewEditorRenderMode.NEVER;

	MediaType[] restrictToMediaTypes() default {};

	WidgetSize widgetSize() default WidgetSize.Default;

	enum TranslationSource
	{
		/**
		 * Default (check AD_Message, AD_Element)
		 */
		DEFAULT,
		/**
		 * M_Attribute.Name
		 */
		ATTRIBUTE_NAME,

		// TODO: SYSCONFIG
	}

	@Target({ ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@interface ViewColumnLayout
	{
		enum Displayed
		{
			/**
			 * The column shall be displayed by default.
			 */
			TRUE,

			/**
			 * The column will be displayed only on demand, when it was explicitly (programatically) specified.
			 */
			FALSE,

			/**
			 * The column shall <b>not</b> be displayed,<br>
			 * unless the sys-config with key = "{@link ViewColumnLayout#displayedSysConfigPrefix()}.fieldName" validates to {@code true}.
			 * The sysconfig may be overridden on client or org-level.
			 * If there is no sys-config that can be validated to a boolean, then {@link #defaultDisplaySysConfig()} is assumed.
			 */
			SYSCONFIG
		}

		JSONViewDataType when();

		Displayed displayed() default Displayed.TRUE;

		/**
		 * See {@link Displayed#SYSCONFIG}. If it evaluates to {@code null} or empty string, then go with {@link #defaultDisplaySysConfig()}.
		 */
		String displayedSysConfigPrefix() default "";

		boolean defaultDisplaySysConfig() default false;

		/**
		 * Display sequence number
		 */
		int seqNo();

	}
}
