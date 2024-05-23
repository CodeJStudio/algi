package com.codejstudio.algi.action.symbol;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.AbstractSymbolCombination;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractSurroundingSymbolCombination extends AbstractSymbolCombination 
		implements ISurroundingSymbolCombination {

	/* constants */

	private static final long serialVersionUID = -7350942173527139214L;


	/* variables */

	protected Class<? extends ISymbolElement> symbolElementClazz;


	/* constructors */

	protected AbstractSurroundingSymbolCombination(String[] symbolArrayForCombination, 
			Class<? extends ISymbolElement> symbolElementClazz) {
		super(symbolArrayForCombination);
		setSymbolElementClass(symbolElementClazz);
	}


	/* getters & setters */

	public Class<? extends ISymbolElement> getSymbolElementClass() {
		return symbolElementClazz;
	}

	protected void setSymbolElementClass(Class<? extends ISymbolElement> symbolElementClazz) {
		this.symbolElementClazz = symbolElementClazz;
	}


	/* overridden methods */

	@Override
	public ISymbolElement generateSymbolElement(final String source) {
		return ISymbolElement.newInstance(this.symbolElementClazz, source);
	}

	@Override
	public boolean verifySurroundingSymbol(final String surroundedContent) throws LIMException {
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(surroundedContent);
		if (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) || arrayOfFragmentArrays.length != 3) {
			return false;
		}
		return CollectionUtil.orderlyContains(
				SymbolUtil.verifyAndStripSingleOuterSymbol(arrayOfFragmentArrays[2]), this.innerSymbolArray);
	}


	@Override
	protected boolean verifyInnerSymbol(final String innerSymbol) {
		if (StringUtil.isEmpty(innerSymbol) || CollectionUtil.checkNullOrEmpty(this.innerSymbolArray)) {
			return false;
		}

		for (String symbol : this.innerSymbolArray) {
			if (innerSymbol.equalsIgnoreCase(symbol)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean verifyInnerSymbol(final String innerSymbol, final int index) {
		if (StringUtil.isEmpty(innerSymbol) || CollectionUtil.checkNullOrEmpty(this.innerSymbolArray) 
				|| index < 0 || index >= this.innerSymbolArray.length) {
			return false;
		}
		return innerSymbol.equalsIgnoreCase(this.innerSymbolArray[index]);
	}

}
