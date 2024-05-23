package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * LessThanRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class LessThanRelation extends ComparisonRelation {

	/* constants */

	private static final long serialVersionUID = 1839955916974616393L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public LessThanRelation() {
		super();
	}

	public LessThanRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public LessThanRelation(boolean initIdFlag, AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(LessThanRelation.class);
		BaseRelation.registerSubPojoClassForInit(LessThanRelation.class);
	}

}
