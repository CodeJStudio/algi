package com.codejstudio.lim.pojo.attribute;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * DefaultAttribute.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class DefaultAttribute extends Attribute {

	/* constants */

	private static final long serialVersionUID = 236151923550214272L;

	public static final String DEFAULT_KEY = "default";

	public static final boolean FORCE_COMPATIBLE_FLAG = true;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public DefaultAttribute() {
		super();
	}

	public DefaultAttribute(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}


	public DefaultAttribute(boolean initIdFlag, boolean initTypeFlag, GenericElement... values) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, DEFAULT_KEY, values);
	}

	public DefaultAttribute(GenericElement... values) throws LIMException {
		this(true, true, values);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(DefaultAttribute.class);
		Attribute.registerSubPojoClassForInit(DefaultAttribute.class);
	}

}
