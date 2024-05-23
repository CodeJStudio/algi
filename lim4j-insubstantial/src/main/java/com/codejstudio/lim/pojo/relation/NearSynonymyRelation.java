package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * NearSynonymyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class NearSynonymyRelation extends SynonymyRelation {

	/* constants */

	private static final long serialVersionUID = -8426729824737982728L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public NearSynonymyRelation() {
		super();
	}

	public NearSynonymyRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public NearSynonymyRelation(boolean initIdFlag, Concept primaryDefiniendum, 
			Concept secondaryDefiniendum) throws LIMException {
		super(initIdFlag, primaryDefiniendum, secondaryDefiniendum);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(NearSynonymyRelation.class);
		BaseRelation.registerSubPojoClassForInit(NearSynonymyRelation.class);
	}

}
