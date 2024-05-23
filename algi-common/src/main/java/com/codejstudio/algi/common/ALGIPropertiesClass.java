package com.codejstudio.algi.common;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.PropertiesClass;
import com.codejstudio.lim.common.util.PropertiesLoader;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ALGIPropertiesClass extends PropertiesClass {

	/* constants */

	public static final String JAR_PATH_SUFFIX = ".jarPath";
	public static final String INTERFACE_PATH_SUFFIX = ".interface";


	/* variables */

	protected String jarPath;
	protected String interfacePropertyValue;
	protected String interfaceName;


	/* constructors */

	public ALGIPropertiesClass(String propertiesFilename, String clazzPropertyKey) throws LIMException {
		super(propertiesFilename, clazzPropertyKey, true);
		setJarPath(PropertiesLoader.getProperty(propertiesFilename, getJarPathProperty(this.clazzPropertyKey)));
		setInterfacePropertyValue(this.clazzPropertyKey);
	}


	/* getters & setters */

	public String getJarPath() {
		return jarPath;
	}

	protected void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}

	public String getInterfacePropertyValue() {
		return interfacePropertyValue;
	}

	protected void setInterfacePropertyValue(final String clazzPropertyKey) throws LIMException {
		String interfacePropertyValue = PropertiesLoader.getProperty(propertiesFilename, 
				getInterfacePathProperty(clazzPropertyKey));
		interfacePropertyValue = (interfacePropertyValue != null) ? interfacePropertyValue 
				: PropertiesLoader.getProperty(propertiesFilename, 
						(getDefaultPropertyKey() + INTERFACE_PATH_SUFFIX));
		this.interfacePropertyValue = interfacePropertyValue;
		this.interfaceName = convertToPropertiesClassName(interfacePropertyValue);
	}


	public String getInterfaceName() {
		return interfaceName;
	}

	public Class<?> getTypeOfInterface() throws ClassNotFoundException {
		return Class.forName(interfaceName);
	}


	private static String getJarPathProperty(final String propertyKey) {
		return (propertyKey == null) ? null : (propertyKey + JAR_PATH_SUFFIX);
	}

	private static String getInterfacePathProperty(final String propertyKey) {
		return (propertyKey == null) ? null : (propertyKey + INTERFACE_PATH_SUFFIX);
	}

}
