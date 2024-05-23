package com.codejstudio.lim.pojo.argument;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * HypotheticalSyllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class HypotheticalSyllogism extends Syllogism {

	/* constants */

	private static final long serialVersionUID = -8170652894620039386L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public HypotheticalSyllogism() {
		super();
	}

	public HypotheticalSyllogism(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public HypotheticalSyllogism(boolean initIdFlag, boolean initTypeFlag, String description) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public HypotheticalSyllogism(String description) throws LIMException {
		super(true, true, description);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(HypotheticalSyllogism.class);
		Argument.registerSubPojoClassForInit(HypotheticalSyllogism.class);
	}

}
