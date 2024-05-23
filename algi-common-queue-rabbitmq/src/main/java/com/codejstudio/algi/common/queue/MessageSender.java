package com.codejstudio.algi.common.queue;

import java.io.IOException;
import java.io.Serializable;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.SerializationUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class MessageSender extends BaseConnector {

	/* class methods */

	public void sendMessage(final Serializable object) throws IOException, LIMException {
		channel.basicPublish("", queueName, null, SerializationUtil.serialize(object));
	}

}
