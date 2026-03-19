package de.metas.ui.web.menu;

 

@FunctionalInterface
public interface MenuNodeFavoriteProvider
{
	boolean isFavorite(MenuNode menuNode);
}
