package com.codejstudio.lim.pojo.util;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.IDUtil;
import com.codejstudio.lim.pojo.i.ISymbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public abstract class AbstractDynamicSymbol extends AbstractNonSurroundingSymbol {

	/* constants */

	private static final long serialVersionUID = 604360472851367787L;

	public static final String DYNAMIC_PLACEHOLDER 
			= "" + ISymbol.SYMBOL_LEFT + OutputDynamicSymbol.SYMBOL + ISymbol.SYMBOL_RIGHT;


	/* class methods */

	public String packageDynamicSymbol(final String content) {
		return SymbolUtil.packageSingleSymbol(getInnerSymbol() + content);
	}

	public String stripDynamicSymbol(final String symbolContent) {
		String str = SymbolUtil.verifyAndStripSingleOuterSymbol(symbolContent);
		String innerSymbol;
		return (str == null || !str.startsWith(innerSymbol = getInnerSymbol())) 
				? null : str.substring(innerSymbol.length());
	}


	/* static methods */

	public static boolean verifySingleDynamicSymbol(final String surroundedSymbol) {
		return DynamicableUtil.INPUT_DYNAMIC_SYMBOL.verifySingleSymbol(surroundedSymbol) 
				|| DynamicableUtil.OUTPUT_DYNAMIC_SYMBOL.verifySingleSymbol(surroundedSymbol);
	}

	public static String stripSingleDynamicSymbol(final String symbolContent) {
		return DynamicableUtil.INPUT_DYNAMIC_SYMBOL.verifySingleSymbol(symbolContent) 
				? DynamicableUtil.INPUT_DYNAMIC_SYMBOL.stripDynamicSymbol(symbolContent) 
				: (DynamicableUtil.OUTPUT_DYNAMIC_SYMBOL.verifySingleSymbol(symbolContent) 
						? DynamicableUtil.OUTPUT_DYNAMIC_SYMBOL.stripDynamicSymbol(symbolContent) : null);
	}


	public static String generateInputId(final Object object) throws LIMException {
		return (object == null) ? null : IDUtil.generateID() + object.hashCode();
	}

}
