package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * MappingRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class MappingRelation extends BaseRelation {

	/* constants */

	private static final long serialVersionUID = 646968830796182837L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public MappingRelation() {
		super();
	}

	public MappingRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public MappingRelation(boolean initIdFlag, AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, true, primaryElement, secondaryElement);
	}


	public MappingRelation(AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(true, true, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(MappingRelation.class);
		BaseRelation.registerSubPojoClassForInit(MappingRelation.class);
	}

}
