package com.codejstudio.lim.common.util;

import java.lang.reflect.Method;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class InitializationUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(InitializationUtil.class);

	public static final String PROPERTY_KEY_AUTO_INITS = "autoInits";
	public static final String METHOD_NAME_AUTO_INIT = "autoInit";


	/* static methods */

	public static final void initSampleMode() throws LIMException {
		initSampleMode(null);
	}

	public static final void initSampleMode(String autoInits) throws LIMException {
		PropertiesLoader.removeProperty(PropertiesLoader.PROPERTIES_FILENAME_COMMON, 
				IDUtil.ID_GENERATION_KEY);
		PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource(
				PropertiesLoader.PROPERTIES_PATH_PREFIX + PropertiesLoader.PROPERTIES_FILENAME_LOG4J 
				+ PropertiesLoader.PROPERTIES_FILE_SUFFIX));
		autoInit(autoInits);
	}

	public static final void autoInit(String autoInits) throws LIMException {
		String autoInitsFilenames = !StringUtil.isEmpty(autoInits) 
				? autoInits : LIMAutoInitConstant.AUTO_INITS;
		String[] aifa = autoInitsFilenames.split(PropertiesLoader.SEPARATOR_MULTI_VALUES);
		for (String aif : aifa) {
			PropertiesClasses pcls = null;
			try {
				pcls = new PropertiesClasses(aif, PROPERTY_KEY_AUTO_INITS);
			} catch (LIMException e) {
				log.error("fail to init below properties file: \"" + aif + "\"");
				log.error(e.toString());
			}
			if (pcls == null) {
				continue;
			}

			for (String cln : pcls.clazzNameArray) {
				try {
					Method m = ReflectionUtil.findMethod(Class.forName(cln), METHOD_NAME_AUTO_INIT);
					ReflectionUtil.invokeMethod(m, true, null);
				} catch (Exception e) {
					log.error(e.toString(), e);
				}
			}
		}
	}

}
