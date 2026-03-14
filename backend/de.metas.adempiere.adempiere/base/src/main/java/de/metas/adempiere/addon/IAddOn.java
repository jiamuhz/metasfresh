package de.metas.adempiere.addon;

/** */

/**
 * Implementations of this interface can be started in an early stage.<br>
 * To register an implementation in <code>addons.properties</code>.
 *
 * @author metas-dev <dev@metasfresh.com>
 *
 */
public interface IAddOn
{
	void beforeConnection();
}
