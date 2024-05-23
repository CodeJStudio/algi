package com.codejstudio.lim.pojo.util;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.INonSurroundingSymbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public abstract class AbstractNonSurroundingSymbol extends AbstractSymbol implements INonSurroundingSymbol {

	/* constants */

	private static final long serialVersionUID = 7452026196374930791L;


	/* overridden methods */

	@Override
	public boolean verifySymbol(final String symbolContent) {
		return verifySingleSymbol(symbolContent);
	}

	@Override
	protected boolean verifyInnerSymbol(final String innerSymbol) {
		return !StringUtil.isEmpty(innerSymbol) && innerSymbol.startsWith(getInnerSymbol());
	}


	@Override
	public boolean containSymbol(final String content) throws LIMException {
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(content, this);
		return !CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) && arrayOfFragmentArrays.length == 3 
				&& !CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays[2]);
	}

}
