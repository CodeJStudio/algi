package com.codejstudio.lim.pojo.statement;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Narration.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Narration extends JudgedStatement {

	/* constants */

	private static final long serialVersionUID = 2043237593216194511L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Narration() {
		super();
	}

	public Narration(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Narration(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public Narration(String description) throws LIMException {
		super(true, true, description);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Narration.class);
		JudgedStatement.registerSubPojoClassForInit(Narration.class);
	}

}
