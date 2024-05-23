package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.doubt.Doubt;
import com.codejstudio.lim.pojo.doubt.Explanation;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * DoubtNExplanationRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class DoubtNExplanationRelation extends BaseRelation {

	/* constants */

	private static final long serialVersionUID = 5085526189769298639L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public DoubtNExplanationRelation() {
		super();
	}

	public DoubtNExplanationRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public DoubtNExplanationRelation(boolean initIdFlag, Doubt doubt, Explanation explanation) 
			throws LIMException {
		super(initIdFlag, true, doubt, explanation);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(DoubtNExplanationRelation.class);
		BaseRelation.registerSubPojoClassForInit(DoubtNExplanationRelation.class);
	}


}
