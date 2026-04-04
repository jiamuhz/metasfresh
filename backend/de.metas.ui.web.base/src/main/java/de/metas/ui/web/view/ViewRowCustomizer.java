package de.metas.ui.web.view;

 

@FunctionalInterface
public interface ViewRowCustomizer
{
	void customizeViewRow(ViewRow.Builder rowBuilder);
}
