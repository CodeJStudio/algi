package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * CustomizedRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class CustomizedRelation extends BaseRelation {

	/* constants */

	private static final long serialVersionUID = 284087783767397446L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public CustomizedRelation() {
		super();
	}

	public CustomizedRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public CustomizedRelation(boolean initIdFlag, AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, true, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(CustomizedRelation.class);
		BaseRelation.registerSubPojoClassForInit(CustomizedRelation.class);
	}

}
