package com.codejstudio.algi.neuros;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.PropertiesConstant;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.common.collection.PropertiesWrapper;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public final class DispatcherConstant {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DispatcherConstant.class);

	private static final String PROPERTIES_FILENAME_DISPATCHER = "dispatcher";
	private static final String PREFIX_DISPATCHER = "dispatcher.";
	private static final String SUFFIX_QUEUE = ".queue";
	private static final String SUFFIX_PROPERTIES = ".properties";
	private static final String SUFFIX_CLAZZ = ".class";


	/* variables: arrays, collections, maps, groups */

	private static Map<String, String[]> dispatcherMap = null;


	/* initializers */

	private static void init() throws ALGIException {
		log.debug(LogUtil.wrap("DispatcherConstant.init()"));
		try {
			PropertiesWrapper pw = PropertiesLoader.getProperties(PROPERTIES_FILENAME_DISPATCHER);
			log.debug(LogUtil.wrap("DISPATCHER properties(" + pw.size() + "): " + pw));
			if (CollectionUtil.checkNullOrEmpty(pw)) {
				return;
			}

			dispatcherMap = CollectionUtil.generateNewMap();
			Set<String> pwks = pw.keySet();
			for (String k : pwks) {
				if (StringUtils.isEmpty(k) || !k.startsWith(PREFIX_DISPATCHER) 
						|| !k.endsWith(SUFFIX_QUEUE)) {
					continue;
				}

				String dispatcherKey = k.substring(0, k.length() - SUFFIX_QUEUE.length());
				String dispatcherQueue = PropertiesLoader.getProperty(
						PropertiesConstant.PROPERTIES_FILENAME_QUEUES, pw.get(k));
				String dispatcherPropertiesFilename = pw.get(dispatcherKey + SUFFIX_PROPERTIES);
				String dispatcherClazz = pw.get(dispatcherKey + SUFFIX_CLAZZ);

				dispatcherMap.put(dispatcherQueue, new String[] {dispatcherQueue, 
						dispatcherPropertiesFilename, dispatcherClazz});
				log.debug(LogUtil.wrap("dispatcherMap.put(" + dispatcherQueue + ", {" + dispatcherQueue 
						+ ", " + dispatcherPropertiesFilename + ", " + dispatcherClazz + "})"));
			}
		} catch (Exception e) {
			throw ALGIException.getALGIException(e);
		}
	}


	/* getters & setters */

	public static final String getDispatcherPropertiesFilename(final String queueName) throws ALGIException {
		if (queueName != null && CollectionUtil.checkNullOrEmpty(dispatcherMap)) {
			init();
		}
		return dispatcherMap.get(queueName)[1];
	}

	public static final String getDispatcherClass(final String queueName) throws ALGIException {
		if (queueName != null && CollectionUtil.checkNullOrEmpty(dispatcherMap)) {
			init();
		}
		return dispatcherMap.get(queueName)[2];
	}

}
