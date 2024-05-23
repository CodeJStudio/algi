package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * SynonymyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class SynonymyRelation extends DefiningRelation {

	/* constants */

	private static final long serialVersionUID = -1487733322695195247L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public SynonymyRelation() {
		super();
	}

	public SynonymyRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public SynonymyRelation(boolean initIdFlag, Concept primaryDefiniendum, Concept secondaryDefiniendum) 
			throws LIMException {
		super(initIdFlag, primaryDefiniendum, secondaryDefiniendum);
		secondaryDefiniendum.addDefaultAttribute(primaryDefiniendum);
		super.addInnerElementDelegate(
				new EquivalenceRelation(initIdFlag, primaryDefiniendum, secondaryDefiniendum));
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(SynonymyRelation.class);
		BaseRelation.registerSubPojoClassForInit(SynonymyRelation.class);
	}

}
