package com.codejstudio.algi.common.queue;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.PropertiesConstant;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class BaseConnector implements Closeable {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(BaseConnector.class);

	private static final String PROPERTY_KEY_HOST = "queue.host";
	private static final String PROPERTY_KEY_PORT = "queue.port";
	private static final String PROPERTY_KEY_USERNAME = "queue.username";
	private static final String PROPERTY_KEY_PASSWORD = "queue.password";


	/* variables */

	protected Channel channel;
	protected Connection connection;
	protected String queueName;


	/* getters & setters */

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}


	/* overridden methods */

	@Override
	public void close() throws IOException {
		if (channel != null) {
			channel.close();
		}
		if (connection != null) {
			connection.close();
		}
	}


	/* class methods */

	public void connect(final String queueName) throws IOException, LIMException {
		this.queueName = queueName;

		String host = PropertiesLoader.getProperty(
				PropertiesConstant.PROPERTIES_FILENAME_QUEUE_CONFIG, PROPERTY_KEY_HOST);
		String port = PropertiesLoader.getProperty(
				PropertiesConstant.PROPERTIES_FILENAME_QUEUE_CONFIG, PROPERTY_KEY_PORT);
		String username = PropertiesLoader.getProperty(
				PropertiesConstant.PROPERTIES_FILENAME_QUEUE_CONFIG, PROPERTY_KEY_USERNAME);
		String password = PropertiesLoader.getProperty(
				PropertiesConstant.PROPERTIES_FILENAME_QUEUE_CONFIG, PROPERTY_KEY_PASSWORD);
		log.debug(LogUtil.wrap("host: " + host + ", port: " + port 
				+ ", username: " + username + ", password: " + password));

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(Integer.parseInt(port));
		factory.setUsername(username);
		factory.setPassword(password);

		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(queueName, false, false, false, null);
	}

}
