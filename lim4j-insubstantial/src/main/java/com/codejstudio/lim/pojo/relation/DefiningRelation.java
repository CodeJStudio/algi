package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * DefiningRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class DefiningRelation extends BaseRelation {

	/* constants */

	private static final long serialVersionUID = 2496361825943156869L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public DefiningRelation() {
		super();
	}

	public DefiningRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public DefiningRelation(boolean initIdFlag, Concept definiendum, Concept definiens) throws LIMException {
		super(initIdFlag, true, definiendum, definiens);
		definiendum.addDefaultAttribute(definiens);
		super.addInnerElementDelegate(new MappingRelation(initIdFlag, definiendum, definiens));
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(DefiningRelation.class);
		BaseRelation.registerSubPojoClassForInit(DefiningRelation.class);
	}

}
