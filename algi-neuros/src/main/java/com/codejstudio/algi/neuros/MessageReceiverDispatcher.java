package com.codejstudio.algi.neuros;

import javax.annotation.Resource;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.PropertiesClassUtil;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.algi.common.queue.MessageReceiver;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lin.persistence.ILINPersistence;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component(MessageReceiverDispatcher.COMPONENT_NAME)
public class MessageReceiverDispatcher extends MessageReceiver implements DisposableBean {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(MessageReceiverDispatcher.class);

	static final String COMPONENT_NAME = "messageDispatcher";


	/* variables */

	private ILINPersistence persistence;

	@Resource
	ThreadPoolTaskExecutor threadPoolTaskExecutor;


	/* constructors */

	public MessageReceiverDispatcher() throws LIMException {
		super();
		log.debug(LogUtil.wrap("MessageReceiverDispatcher()"));
	}


	/* getters & setters */

	protected ILINPersistence getPersistence() {
		return persistence;
	}

	protected void setPersistence(ILINPersistence persistence) {
		this.persistence = persistence;
	}


	/* overridden methods */

	@Override
	public void handleConsumeOk(final String consumerTag) {
		log.debug(LogUtil.wrap("Consumer " + consumerTag + " registered"));
	}

	@Override
	public void handleDelivery(final String consumerTag, final Envelope envelope, 
			final BasicProperties properties, final byte[] body) {
		try {
			Object obj = SerializationUtils.deserialize(body);
			DispatchedObject dpobj = (DispatchedObject) obj;

			DispatcherThread thread = new DispatcherThread(this.queueName, this.persistence, dpobj);

			threadPoolTaskExecutor.execute(thread);
			log.debug("threadPoolTaskExecutor.getCorePoolSize():" + threadPoolTaskExecutor.getCorePoolSize() 
				+ ", 线程池中线程数目：" + threadPoolTaskExecutor.getPoolSize() 
				+ "，获取当前线程池活动的线程数：" + threadPoolTaskExecutor.getActiveCount());
		} catch (Exception e) {
			log.error(LogUtil.wrap("synchronous exception:"), e);
		}
	}

	@Override
	public void destroy() throws Exception {
		threadPoolTaskExecutor.destroy();
	}



	/* inner classes */

	static class DispatcherThread implements Runnable {

		/* constants */

		static final Logger log = LoggerFactory.getLogger(DispatcherThread.class);


		/* variables */

		private String queueName;
		private ILINPersistence persistence;
		private DispatchedObject dispatchedObject;


		/* constructors */

		public DispatcherThread(String queueName, ILINPersistence persistence, 
				DispatchedObject dispatchedObject) {
			this.queueName = queueName;
			this.persistence = persistence;
			this.dispatchedObject = dispatchedObject;
		}


		/* overridden methods */

		@Override
		public synchronized void run() {
			try {
				log.debug(LogUtil.wrap("DispatcherThread.run()"));
				log.debug(LogUtil.wrap("queueName: " + queueName));

				String dpf = DispatcherConstant.getDispatcherPropertiesFilename(this.queueName);
				String dcl = DispatcherConstant.getDispatcherClass(this.queueName);
				PropertiesClassUtil.startPropertiesClass(dpf, dcl, this.persistence, this.dispatchedObject);
			} catch (ALGIException e) {
				log.error(e.toString(), e);
			}
		}
	}

}
