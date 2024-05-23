package com.codejstudio.lim.pojo.condition;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * ScopeCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class ScopeCondition extends Condition {

	/* constants */

	private static final long serialVersionUID = 2418911958190348304L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ScopeCondition() {
		super();
	}

	public ScopeCondition(boolean initIdFlag, boolean initTypeFlag, InformationElement descriptionElement) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, descriptionElement);
	}


	public ScopeCondition(InformationElement descriptionElement) throws LIMException {
		this(true, true, descriptionElement);
	}

	public ScopeCondition(InformationElement descriptionElement, Boolean implicit) throws LIMException {
		this(true, true, descriptionElement);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(ScopeCondition.class);
		Condition.registerSubPojoClassForInit(ScopeCondition.class);
	}

}
