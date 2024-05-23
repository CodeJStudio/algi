package com.codejstudio.lim.pojo.statement;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Proposition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Proposition extends JudgedStatement {

	/* constants */

	private static final long serialVersionUID = -8701506461447823532L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Proposition() {
		super();
	}

	public Proposition(Proposition proposition) throws LIMException {
		super(proposition);
	}

	public Proposition(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Proposition(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public Proposition(String description) throws LIMException {
		this(true, true, description);
	}


	public Proposition(Statement statement) throws LIMException {
		this(statement.getDescription());
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Proposition.class);
		JudgedStatement.registerSubPojoClassForInit(Proposition.class);
	}

}
