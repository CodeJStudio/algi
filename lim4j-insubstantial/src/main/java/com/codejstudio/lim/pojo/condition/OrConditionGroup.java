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
public class OrConditionGroup extends ConditionGroup {

	/* constants */

	private static final long serialVersionUID = 638286000475777373L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public OrConditionGroup() {
		super();
	}

	public OrConditionGroup(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
		setAndOrType(AndOrType.OR);
	}

	public OrConditionGroup(boolean initIdFlag, Condition... conditions) throws LIMException {
		super(initIdFlag, conditions);
		setAndOrType(AndOrType.OR);
	}


	public OrConditionGroup(Condition... conditions) throws LIMException {
		super(conditions);
		setAndOrType(AndOrType.OR);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(OrConditionGroup.class);
		Condition.registerSubPojoClassForInit(OrConditionGroup.class);
	}

}
