package com.codejstudio.lim.common.util;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class PropertiesClass {

	/* constants */

	public static final String PROPERTIES_CLAZZ_PREFIX = "cl:";
	public static final String DEFAULT_PATH_SUFFIX = ".default";


	/* variables */

	protected String propertiesFilename;
	protected String clazzPropertyKey;
	protected String clazzPropertyValue;
	protected String clazzName;


	/* constructors */

	protected PropertiesClass() {}

	public PropertiesClass(String clazzPropertyValue) throws LIMException {
		setClassPropertyValue(clazzPropertyValue);
	}

	public PropertiesClass(String propertiesFilename, String clazzPropertyKey) throws LIMException {
		this(propertiesFilename, clazzPropertyKey, false);
	}

	public PropertiesClass(String propertiesFilename, String clazzPropertyKey, boolean checkDefaultFlag) 
			throws LIMException {
		this.propertiesFilename = propertiesFilename;
		setClassPropertyKeyAndClassPropertyValue(clazzPropertyKey);
		if (checkDefaultFlag && !isPropertyValueClassName()) {
			setClassPropertyKeyAndClassPropertyValue(this.clazzPropertyValue);
		}
	}


	/* getters & setters */

	public String getPropertiesFilename() {
		return propertiesFilename;
	}

	public String getClassPropertyKey() {
		return clazzPropertyKey;
	}

	protected void setClassPropertyKey(String clazzPropertyKey) {
		this.clazzPropertyKey = clazzPropertyKey;
	}

	public String getClassPropertyValue() {
		return clazzPropertyValue;
	}

	protected void setClassPropertyValue(final String clazzPropertyValue) throws LIMException {
		this.clazzPropertyValue = clazzPropertyValue;
		this.clazzName = convertToPropertiesClassName(clazzPropertyValue);
	}

	protected void setClassPropertyKeyAndClassPropertyValue(final String clazzPropertyKey) 
			throws LIMException {
		setClassPropertyKey(clazzPropertyKey);
		setClassPropertyValue(PropertiesLoader.getProperty(this.propertiesFilename, clazzPropertyKey));
	}

	public String getClassName() {
		return clazzName;
	}

	public Class<?> getTypeOfClass() throws ClassNotFoundException {
		return Class.forName(this.clazzName);
	}


	/* class methods */

	public boolean isDefaultProperty(final String clazzPropertyKey) {
		return getDefaultPropertyKey().equals(clazzPropertyKey);
	}

	public String getDefaultPropertyKey() {
		return this.propertiesFilename + DEFAULT_PATH_SUFFIX;
	}

	public boolean isPropertyValueClassName() {
		return isPropertyValueClassName(this.clazzPropertyValue);
	}


	/* static methods */

	protected static boolean isPropertyValueClassName(final String clazzPropertyValue) {
		return !StringUtil.isEmpty(clazzPropertyValue) 
				&& (clazzPropertyValue.length() > PROPERTIES_CLAZZ_PREFIX.length()) 
				&& clazzPropertyValue.startsWith(PROPERTIES_CLAZZ_PREFIX);
	}

	protected static String convertToPropertiesClassName(final String clazzPropertyValue) {
		return isPropertyValueClassName(clazzPropertyValue) 
				? clazzPropertyValue.substring(PROPERTIES_CLAZZ_PREFIX.length()) : clazzPropertyValue;
	}

}
