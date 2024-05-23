package com.codejstudio.lim.common.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class DynamicJarLoader {

	/* constants */

	static final String METHOD_NAME_ADD_URL = "addURL";


	/* static methods */

	public static final void loadJarFile(final String jarPath) throws LIMException {
		loadJarFile(jarPath, null);
	}

	public static final void loadJarFile(final String jarPath, final ClassLoader clazzLoader) 
			throws LIMException {
		File jarFile = new File(jarPath);
		if (!jarFile.exists()) {
			return;
		}

		try {
			Method m = ReflectionUtil.findMethod(URLClassLoader.class, METHOD_NAME_ADD_URL, URL.class);
			ReflectionUtil.invokeMethod(m, true, 
					((clazzLoader != null) ? clazzLoader : Thread.currentThread().getContextClassLoader()), 
							jarFile.toURI().toURL());
		} catch (Exception e) {
			throw LIMException.getLIMException(e);
		}
	}

}