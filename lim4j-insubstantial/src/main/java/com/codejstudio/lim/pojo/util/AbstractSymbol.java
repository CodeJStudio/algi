package com.codejstudio.lim.pojo.util;

import com.codejstudio.lim.pojo.i.ISymbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public abstract class AbstractSymbol implements ISymbol {

	/* constants */

	private static final long serialVersionUID = 5212643012797074037L;


	/* overridden methods */

	@Override
	public boolean verifySingleSymbol(final String surroundedSymbol) {
		return verifyInnerSymbol(SymbolUtil.verifyAndStripSingleOuterSymbol(surroundedSymbol));
	}

	protected abstract boolean verifyInnerSymbol(String innerSymbol);

}
