package com.codejstudio.algi.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.DynamicJarLoader;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lin.persistence.ILINPersistence;
import com.codejstudio.lin.persistence.ILINPersistenceContainer;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public final class PropertiesClassUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(PropertiesClassUtil.class);


	/* static methods */

	public static final Map<String, IALGIRunnable> quietlyStartPropertiesClasses(
			final String propertiesFilename, final String clazzPropertyKeys, 
			final ILINPersistence persistence, final boolean initDispatchedObjectFlag) throws LIMException {
		String[] cpka;
		if (StringUtil.isEmpty(clazzPropertyKeys) 
				|| CollectionUtil.checkNullOrEmpty(
						cpka = clazzPropertyKeys.split(PropertiesLoader.SEPARATOR_MULTI_VALUES))) {
			return null;
		}

		Map<String, IALGIRunnable> sarm = CollectionUtil.generateNewMap();
		for (int i = 0; i < cpka.length; i++) {
			try {
				IALGIRunnable iar = startPropertiesClass(propertiesFilename, cpka[i], 
						persistence, (!initDispatchedObjectFlag ? null : new DispatchedObject()));
				if (iar != null) {
					sarm.put(StringUtil.trim(cpka[i]), iar);
				}
			} catch (LIMException e) {
				log.error(e.toString(), e);
			}
		}

		return CollectionUtil.checkNullOrEmpty(sarm) ? null : sarm;
	}

	@SuppressWarnings("unchecked")
	public static final IALGIRunnable startPropertiesClass(final String propertiesFilename, 
			final String clazzPropertyKey, final ILINPersistence persistence, 
			final DispatchedObject dispatchedObject) throws ALGIException {
		try {
			String cpk = StringUtil.trim(clazzPropertyKey);
			log.debug(LogUtil.wrap("clazzPropertyKey: " + cpk));
			if (StringUtil.isEmpty(cpk)) {
				return null;
			}

			ALGIPropertiesClass apcl = new ALGIPropertiesClass(propertiesFilename, cpk);
			log.debug(LogUtil.wrap("className: " + apcl.getClassName()));
			log.debug(LogUtil.wrap("interfaceName: " + apcl.getInterfaceName()));
			if (!apcl.isPropertyValueClassName() || StringUtil.isEmpty(StringUtil.trim(apcl.getClassName()))) {
				return null;
			}

			String jp = apcl.getJarPath();
			log.debug(LogUtil.wrap("jarPath: " + jp));
			if (!StringUtil.isEmpty(StringUtil.trim(jp))) {
				DynamicJarLoader.loadJarFile(jp);
			}

			Class<?> cl = apcl.getTypeOfClass();
			log.debug(LogUtil.wrap("algiPropertiesClass.getTypeOfClass().getName(): " + cl.getName()));
			Class<? extends IALGIRunnable> arcl = (Class<? extends IALGIRunnable>) apcl.getTypeOfInterface();
			log.debug(LogUtil.wrap("algiPropertiesClass.getTypeOfInterface().getName(): " + arcl.getName()));

			return startARClass(cl, arcl, persistence, dispatchedObject);
		} catch (Exception e) {
			throw ALGIException.getALGIException(e);
		}
	}



	public static final IALGIRunnable startARClass(final String clazzName, 
			final Class<? extends IALGIRunnable> algiRunnableClazz, final ILINPersistence persistence, 
			final DispatchedObject dispatchedObject) throws ALGIException {
		try {
			return startARClass(Class.forName(clazzName), algiRunnableClazz, persistence, dispatchedObject);
		} catch (Exception e) {
			throw ALGIException.getALGIException(e);
		}
	}

	public static final IALGIRunnable startARClass(final Class<?> clazz, 
			final Class<? extends IALGIRunnable> algiRunnableClazz, final ILINPersistence persistence, 
			final DispatchedObject dispatchedObject) throws ALGIException {
		try {
			Object obj = clazz.newInstance();
			log.debug(LogUtil.wrap("clazz.getName(): " + clazz.getName()));
			log.debug(LogUtil.wrap("algiRunnableClazz.isInstance(obj): " + algiRunnableClazz.isInstance(obj)));
			if (algiRunnableClazz.isInstance(obj)) {
				IALGIRunnable iar = (IALGIRunnable)obj;
				iar.setDispatchedObject(dispatchedObject);
				if (iar instanceof ILINPersistenceContainer) {
					((ILINPersistenceContainer) iar).setPersistence(persistence);
				}
				log.debug(LogUtil.wrap(obj.getClass().getName() + " IAlgiRunnableThread.start()"));
				new Thread(iar).start();
				return iar;
			}
		} catch (Exception e) {
			throw ALGIException.getALGIException(e);
		}
		return null;
	}

}
