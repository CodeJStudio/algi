package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * ComparisonRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class ComparisonRelation extends MappingRelation {

	/* constants */

	private static final long serialVersionUID = -3118072788550958816L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ComparisonRelation() {
		super();
	}

	public ComparisonRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public ComparisonRelation(boolean initIdFlag, AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(ComparisonRelation.class);
		BaseRelation.registerSubPojoClassForInit(ComparisonRelation.class);
	}

}
