package com.codejstudio.algi.action.symbol;

import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractComplexSymbolCombination extends AbstractSurroundingSymbolCombination {

	/* constants */

	private static final long serialVersionUID = -3881336409509913503L;


	/* constructors */

	protected AbstractComplexSymbolCombination(String[] symbolArrayForCombination, 
			Class<? extends ISymbolElement> symbolElementClazz) {
		super(symbolArrayForCombination, symbolElementClazz);
	}


	/* static methods */

	protected static void init(final AbstractComplexSymbolCombination[] complexSymbolCombinationArray) {
		for (int i = 0; i < complexSymbolCombinationArray.length; i++) {
			SymbolUtil.registerSymbolCombinationForInit(complexSymbolCombinationArray[i]);
		}
	}

	protected static AbstractComplexSymbolCombination getInstance(final int type, 
			final AbstractComplexSymbolCombination[] complexSymbolCombinationArray) {
		if (type >= 0 && type < complexSymbolCombinationArray.length) {
			return complexSymbolCombinationArray[type];
		} else {
			return null;
		}
	}


	protected static boolean verifySymbolArray(final String[] symbolArray, 
			final String[][] arrayOfSymbolArraysForCombination) {
		return verifySymbolArrayAndGetCombinationType(symbolArray, arrayOfSymbolArraysForCombination) >= 0;
	}

	protected static int verifySymbolArrayAndGetCombinationType(final String[] symbolArray, 
			final String[][] arrayOfSymbolArraysForCombination) {
		String[] stra = SymbolUtil.verifyAndStripSingleOuterSymbol(symbolArray);
		for (int i = 0; i < arrayOfSymbolArraysForCombination.length; i++) {
			if (CollectionUtil.orderlyContains(stra, arrayOfSymbolArraysForCombination[i])) {
				return i;
			}
		}
		return -1;
	}

}
