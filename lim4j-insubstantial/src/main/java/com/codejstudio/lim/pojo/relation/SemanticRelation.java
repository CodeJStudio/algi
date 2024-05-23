package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * SemanticRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class SemanticRelation extends BaseRelation {

	/* constants */

	private static final long serialVersionUID = 4027154386608333082L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public SemanticRelation() {
		super();
	}

	public SemanticRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public SemanticRelation(boolean initIdFlag, AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, true, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(SemanticRelation.class);
		BaseRelation.registerSubPojoClassForInit(SemanticRelation.class);
	}

}
