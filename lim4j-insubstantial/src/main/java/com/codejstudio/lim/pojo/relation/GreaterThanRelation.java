package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * GreaterThanRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class GreaterThanRelation extends ComparisonRelation {

	/* constants */

	private static final long serialVersionUID = 6657494403293348368L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public GreaterThanRelation() {
		super();
	}

	public GreaterThanRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public GreaterThanRelation(boolean initIdFlag, AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(GreaterThanRelation.class);
		BaseRelation.registerSubPojoClassForInit(GreaterThanRelation.class);
	}

}
