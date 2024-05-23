package com.codejstudio.lin.common;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.LIMAutoInitConstant;

public class LINAutoInitConstant extends LIMAutoInitConstant {

	/* constants */

	public static final String PROPERTIES_FILENAME_COMMON = "lin4j-common";

	public static final String AUTO_INITS;


	/* constructors */

	private LINAutoInitConstant() throws LIMException {
		super(PROPERTIES_FILENAME_COMMON);
	}


	/* initializers */

	static {
		try {
			AUTO_INITS = new LINAutoInitConstant().autoInits;
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}

}
