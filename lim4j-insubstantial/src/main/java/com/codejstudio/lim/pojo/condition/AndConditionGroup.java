package com.codejstudio.lim.pojo.condition;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AndConditionGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class AndConditionGroup extends ConditionGroup {

	/* constants */

	private static final long serialVersionUID = -2590399793251528400L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AndConditionGroup() {
		super();
	}

	public AndConditionGroup(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
		setAndOrType(AndOrType.AND);
	}

	public AndConditionGroup(boolean initIdFlag, Condition... conditions) throws LIMException {
		super(initIdFlag, conditions);
		setAndOrType(AndOrType.AND);
	}


	public AndConditionGroup(Condition... conditions) throws LIMException {
		super(conditions);
		setAndOrType(AndOrType.AND);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(AndConditionGroup.class);
		Condition.registerSubPojoClassForInit(AndConditionGroup.class);
	}

}
