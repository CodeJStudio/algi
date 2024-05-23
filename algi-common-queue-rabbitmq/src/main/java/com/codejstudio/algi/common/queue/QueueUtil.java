package com.codejstudio.algi.common.queue;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.PropertiesConstant;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CloseUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public final class QueueUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(QueueUtil.class);


	/* static methods */

	public static final void sendMessage(final String targetQueuePropertyKey, final Serializable object) 
			throws ALGIException {
		log.debug(LogUtil.wrap("QueueUtil.sendMessage() to: (" + targetQueuePropertyKey + ")"));
		MessageSender sender = null;
		String queueName = null;
		try {
			queueName = PropertiesLoader.getProperty(
					PropertiesConstant.PROPERTIES_FILENAME_QUEUES, targetQueuePropertyKey);
			log.debug(LogUtil.wrap("To send data to the queue (" + targetQueuePropertyKey + "): " 
					+ queueName));

			sender = new MessageSender();
			sender.connect(queueName);
			sender.sendMessage(object);
			log.debug(LogUtil.wrap("Send data to the queue (" + targetQueuePropertyKey + "): " 
					+ queueName + " finished"));
		} catch (IOException | LIMException e) {
			log.error(LogUtil.wrap("Fail to send data to the queue (" + targetQueuePropertyKey + "): " 
					+ queueName), e);
			throw ALGIException.getALGIException(e);
		} finally {
			CloseUtil.quietlyClose(sender);
		}
	}

}
