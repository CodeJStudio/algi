package com.codejstudio.algi.common;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.LIMAutoInitConstant;

public class ALGIAutoInitConstant extends LIMAutoInitConstant {

	/* constants */

	public static final String PROPERTIES_FILENAME_COMMON = "algi-common";

	public static final String AUTO_INITS;


	/* constructors */

	private ALGIAutoInitConstant() throws LIMException {
		super(PROPERTIES_FILENAME_COMMON);
	}


	/* initializers */

	static {
		try {
			AUTO_INITS = new ALGIAutoInitConstant().autoInits;
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}

}
