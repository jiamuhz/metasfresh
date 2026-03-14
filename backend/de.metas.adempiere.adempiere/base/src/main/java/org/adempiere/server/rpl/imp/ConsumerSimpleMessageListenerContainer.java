package org.adempiere.server.rpl.imp;

/** */

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

/**
 * Message listener container
 * Note: needed because {@link SimpleMessageListenerContainer#doStart()} is protected
 */
public class ConsumerSimpleMessageListenerContainer extends SimpleMessageListenerContainer
{

	public void startConsumers() throws Exception
	{
		super.doStart();
	}

}
