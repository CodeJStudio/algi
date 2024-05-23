package com.codejstudio.lim.common.util;

import java.io.InputStream;
import java.util.Map;

import com.codejstudio.lim.common.collection.PropertiesWrapper;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;

/**
 * <code>PropertiesLoader</code> is written to load local property files.
 * <br>
 * eg. "common.properties"
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PropertiesLoader {

	/* constants */

	protected static final String PROPERTIES_PATH_PREFIX = "properties/";
	public static final String PROPERTIES_FILE_SUFFIX = ".properties";

	public static final String PROPERTIES_FILENAME_COMMON = "lim4j-common";
	public static final String PROPERTIES_FILENAME_LOG4J = "log4j";

	public static final String SEPARATOR_MULTI_VALUES = "" + WordSeparator.COMMA.getCharacter();


	/* variables: arrays, collections, maps, groups */

	private static Map<String, PropertiesWrapper> propertiesMap = null;


	/* initializers */

	private static void init(final String filename) throws LIMException {
		InputStream is = null;
		try {
			if (propertiesMap == null) {
				propertiesMap = CollectionUtil.generateDefaultNewMap();
			}

			PropertiesWrapper pw = null;
			if (filename == null || propertiesMap.get(filename) != null) {
				return;
			}

			String filePath = (!filename.startsWith(PROPERTIES_PATH_PREFIX) ? PROPERTIES_PATH_PREFIX : "") 
					+ filename 
					+ (!filename.endsWith(PROPERTIES_FILE_SUFFIX) ? PROPERTIES_FILE_SUFFIX : "");
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

			pw = new PropertiesWrapper();
			pw.load(is);
			propertiesMap.put(filename, pw);
		} catch (Exception e) {
			throw LIMException.getLIMException(e);
		} finally {
			CloseUtil.quietlyClose(is);
		}
	}


	/* CRUD for arrays, collections, maps, groups: properties */

	public static PropertiesWrapper getProperties(final String filename) throws LIMException {
		String fn;
		if (StringUtil.isEmpty(filename) 
				|| StringUtil.isEmpty(fn = filename.endsWith(PROPERTIES_FILE_SUFFIX) 
				? filename.substring(0, filename.length() - PROPERTIES_FILE_SUFFIX.length()) : filename)) {
			return null;
		}

		if (propertiesMap == null || propertiesMap.get(fn) == null) {
			init(fn);
		}
		return propertiesMap.get(fn);
	}

	public static String getProperty(final String filename, final String key) throws LIMException {
		return getProperties(filename).getProperty(key);
	}

	public static Object setProperty(final String filename, final String key, final String value) 
			throws LIMException {
		return getProperties(filename).setProperty(key, value);
	}

	public static String removeProperty(final String filename, final String key) throws LIMException {
		return getProperties(filename).remove(key);
	}


	/* static methods */

	public static boolean isValid(final String value) {
		return !StringUtil.isEmpty(value) && !(value.startsWith("${") && value.endsWith("}"));
	}

}
