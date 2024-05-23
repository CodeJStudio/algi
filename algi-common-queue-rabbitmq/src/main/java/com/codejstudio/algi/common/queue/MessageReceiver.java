package com.codejstudio.algi.common.queue;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.util.LogUtil;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class MessageReceiver extends BaseConnector implements Consumer, Runnable {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);


	/* constructors */

	public MessageReceiver() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		try {
			channel.basicConsume(queueName, true, this);
		} catch (IOException e) {
			log.error(LogUtil.wrap("Specify the consumer queue to fail: "), e);
		}
	}


	@Override
	public abstract void handleConsumeOk(String consumerTag);

	@Override
	public abstract void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, 
			byte[] body) throws IOException;


	@Override
	public void handleCancelOk(String consumerTag) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
		throw new UnsupportedOperationException();
	}

}
