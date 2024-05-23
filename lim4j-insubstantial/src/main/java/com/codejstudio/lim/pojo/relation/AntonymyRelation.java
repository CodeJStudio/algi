package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AntonymyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AntonymyRelation extends SynonymyRelation {

	/* constants */

	private static final long serialVersionUID = 6262398360141097845L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AntonymyRelation() {
		super();
	}

	public AntonymyRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public AntonymyRelation(boolean initIdFlag, Concept primaryDefiniendum, Concept secondaryDefiniendum) 
			throws LIMException {
		super(initIdFlag, primaryDefiniendum, secondaryDefiniendum);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(AntonymyRelation.class);
		BaseRelation.registerSubPojoClassForInit(AntonymyRelation.class);
	}

}
