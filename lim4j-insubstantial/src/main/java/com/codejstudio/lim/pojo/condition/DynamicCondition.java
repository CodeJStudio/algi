package com.codejstudio.lim.pojo.condition;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * DynamicCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class DynamicCondition extends Condition {

	/* constants */

	private static final long serialVersionUID = -2172943533063983836L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public DynamicCondition() {
		super();
	}

	public DynamicCondition(String description) throws LIMException {
		super(true, true);
		setDescription(description);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(DynamicCondition.class);
		Condition.registerSubPojoClassForInit(DynamicCondition.class);
	}

}
