package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AttributeMappingRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AttributeMappingRelation extends MappingRelation {

	/* constants */

	private static final long serialVersionUID = -3276466472372933476L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AttributeMappingRelation() {
		super();
	}

	public AttributeMappingRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public AttributeMappingRelation(boolean initIdFlag, Attribute primaryAttribute, 
			Attribute secondaryAttribute) throws LIMException {
		super(initIdFlag, primaryAttribute, secondaryAttribute);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(AttributeMappingRelation.class);
		BaseRelation.registerSubPojoClassForInit(AttributeMappingRelation.class);
	}

}
