package com.codejstudio.lim.common.util;

import com.codejstudio.lim.common.exception.LIMException;

public class LIMAutoInitConstant {

	/* constants */

	public static final String PROPERTIES_FILENAME_COMMON = PropertiesLoader.PROPERTIES_FILENAME_COMMON;
	public static final String PROPERTY_KEY_AUTO_INITS = "autoInits";

	public static final String AUTO_INITS;


	/* variables */

	protected String autoInits;


	/* constructors */

	protected LIMAutoInitConstant(String propertiesFilename) throws LIMException {
		this.autoInits = PropertiesLoader.getProperty(propertiesFilename, PROPERTY_KEY_AUTO_INITS);
	}

	private LIMAutoInitConstant() throws LIMException {
		this(PROPERTIES_FILENAME_COMMON);
	}


	/* initializers */

	static {
		try {
			AUTO_INITS = new LIMAutoInitConstant().autoInits;
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}

}
