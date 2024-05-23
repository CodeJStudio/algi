package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AnalogyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AnalogyRelation extends MappingRelation {

	/* constants */

	private static final long serialVersionUID = 3943997387358204676L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AnalogyRelation() {
		super();
	}

	public AnalogyRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public AnalogyRelation(boolean initIdFlag, AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, primaryElement, secondaryElement);
	}


	public AnalogyRelation(AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		this(true, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(AnalogyRelation.class);
		BaseRelation.registerSubPojoClassForInit(AnalogyRelation.class);
	}

}
