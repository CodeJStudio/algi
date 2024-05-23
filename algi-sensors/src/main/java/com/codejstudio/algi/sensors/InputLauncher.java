package com.codejstudio.algi.sensors;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.codejstudio.algi.StartingListener;
import com.codejstudio.algi.common.IALGIRunnable;
import com.codejstudio.algi.common.PropertiesClassUtil;
import com.codejstudio.algi.common.PropertiesConstant;
import com.codejstudio.algi.common.web.ApplicationContextUtil;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.common.util.LogUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
@Component
public class InputLauncher implements StartingListener {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(InputLauncher.class);


	/* variables */

	@Value("${init.autoInits}")
	private String autoInits;

	@Value("${init.inputs}")
	private String inputs;


	/* variables: arrays, collections, maps, groups */

	private static Map<String, IALGIRunnable> inputARMap;


	/* getters & setters */

	public static Map<String, IALGIRunnable> getInputARMap() {
		return inputARMap;
	}


	/* overridden methods */

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		log.debug(LogUtil.wrap("Launcher.onApplicationEvent()"));

		ApplicationContextUtil.setApplicationContext(event.getApplicationContext());

		try {
			InitializationUtil.autoInit(autoInits);
			Map<String, IALGIRunnable> sarm = PropertiesClassUtil.quietlyStartPropertiesClasses(
					PropertiesConstant.PROPERTIES_FILENAME_INPUT, inputs, null, true);
			InputLauncher.inputARMap = (sarm == null) ? null : Collections.unmodifiableMap(sarm);
		} catch (LIMException e) {
			log.error(e.toString(), e);
		}
	}

}