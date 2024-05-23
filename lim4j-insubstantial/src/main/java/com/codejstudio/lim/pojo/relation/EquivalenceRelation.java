package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * EquivalenceRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class EquivalenceRelation extends MappingRelation {

	/* constants */

	private static final long serialVersionUID = -2713135414885236033L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public EquivalenceRelation() {
		super();
	}

	public EquivalenceRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public EquivalenceRelation(boolean initIdFlag, AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, primaryElement, secondaryElement);
	}


	public EquivalenceRelation(AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(true, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(EquivalenceRelation.class);
		BaseRelation.registerSubPojoClassForInit(EquivalenceRelation.class);
	}

}
