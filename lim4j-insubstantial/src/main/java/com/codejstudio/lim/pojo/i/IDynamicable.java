package com.codejstudio.lim.pojo.i;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.PojoElementEnumConstant;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface IDynamicable {

	/* enumeration */

	enum DynamicParameterType {
		INPUT,
		OUTPUT,
		;


		/* initializers */

		/**
		 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
		 */
		static void autoInit() {}

		static {
			PojoElementEnumConstant.registerEnumClassForInit(DynamicParameterType.class);
		}
	}


	/* interface methods */

	String substituteStaticInputsForDynamic(DynamicParameterType[] typeArray, Object[] inputArray) 
			throws LIMException;

	String substituteDynamicIdentifier(String[] inputArray) throws LIMException;

}
