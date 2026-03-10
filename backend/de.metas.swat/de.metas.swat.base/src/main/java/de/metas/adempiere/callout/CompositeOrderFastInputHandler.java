package de.metas.adempiere.callout;

import java.util.concurrent.CopyOnWriteArrayList;

import org.compiere.apps.search.IGridTabRowBuilder;
import org.compiere.apps.search.impl.CompositeGridTabRowBuilder;

import lombok.NonNull;
import lombok.ToString;

@ToString
public class CompositeOrderFastInputHandler implements IOrderFastInputHandler
{
	private final CopyOnWriteArrayList<IOrderFastInputHandler> handlers = new CopyOnWriteArrayList<>();

	/**
	 * This is the default handler. This composite handler give precedence to all other handlers that are explicitly added.
	 */
	private final IOrderFastInputHandler defaultHandler;

	/**
	 * @param defaultHandler this handler will be invoked last
	 */
	public CompositeOrderFastInputHandler(@NonNull final IOrderFastInputHandler defaultHandler)
	{
		this.defaultHandler = defaultHandler;
	}

	public void addHandler(@NonNull final IOrderFastInputHandler handler)
	{
		if (handler.equals(defaultHandler))
		{
			return;
		}

		handlers.addIfAbsent(handler);
	}


	@Override
	public IGridTabRowBuilder createLineBuilderFromHeader(final Object model)
	{
		final CompositeGridTabRowBuilder builders = new CompositeGridTabRowBuilder();

		for (final IOrderFastInputHandler handler : handlers)
		{
			final IGridTabRowBuilder builder = handler.createLineBuilderFromHeader(model);
			builders.addGridTabRowBuilder(builder);
		}
		builders.addGridTabRowBuilder(defaultHandler.createLineBuilderFromHeader(model));
		return builders;
	}
}
