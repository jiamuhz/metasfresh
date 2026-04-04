package de.metas.ui.web.pporder.process;

import java.util.stream.Stream;

import java.util.Objects;

import de.metas.ui.web.pporder.PPOrderLineRow;
import de.metas.ui.web.pporder.PPOrderLinesView;
import de.metas.ui.web.process.adprocess.ViewBasedProcessTemplate;

 

/**
 * Template class for all processes which are based on {@link PPOrderLinesView}.
 * 
 * 
 *
 */
public abstract class WEBUI_PP_Order_Template
		extends ViewBasedProcessTemplate
// implements IProcessPrecondition // let the extending class activate this interface
{
	@SuppressWarnings("MethodDoesntCallSuperMethod")
	@Override
	protected final PPOrderLinesView getView()
	{
		return super.getView(PPOrderLinesView.class);
	}

	@Override
	protected final PPOrderLineRow getSingleSelectedRow()
	{
		return PPOrderLineRow.cast(super.getSingleSelectedRow());
	}
	
	protected final Stream<PPOrderLineRow> streamPPOrderLineRows()
	{
		return streamSelectedRows()
				.map(PPOrderLineRow::cast)
				.filter(Objects::nonNull);
	}
}
