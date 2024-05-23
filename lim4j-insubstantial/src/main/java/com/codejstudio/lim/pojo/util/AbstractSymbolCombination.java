package com.codejstudio.lim.pojo.util;

import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.ISymbolCombination;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public abstract class AbstractSymbolCombination implements ISymbolCombination {

	/* constants */

	private static final long serialVersionUID = -2750937136208968800L;


	/* variables: arrays, collections, maps, groups */

	protected String[] innerSymbolArray;


	/* constructors */

	protected AbstractSymbolCombination(String[] symbolArrayForCombination) {
		super();
		setInnerSymbolArray(symbolArrayForCombination);
	}


	/* getters & setters */

	@Override
	public String[] getInnerSymbolArray() {
		return innerSymbolArray;
	}

	protected void setInnerSymbolArray(String[] innerSymbolArray) {
		this.innerSymbolArray = innerSymbolArray;
	}


	/* overridden methods */

	@Override
	public int length() {
		return (this.innerSymbolArray == null) ? 0 : this.innerSymbolArray.length;
	}

	@Override
	public int extraLength() {
		return 0;
	}

	@Override
	public String getSingleInnerSymbol(final int index) {
		return (CollectionUtil.checkNullOrEmpty(this.innerSymbolArray) 
						|| index < 0 || index >= this.innerSymbolArray.length) 
				? null : this.innerSymbolArray[index];
	}


	@Override
	public boolean firstSingleSymbol(final String surroundedSymbol) {
		return verifyInnerSymbol(SymbolUtil.verifyAndStripSingleOuterSymbol(surroundedSymbol), 0);
	}

	@Override
	public boolean lastSingleSymbol(final String surroundedSymbol) {
		return verifyInnerSymbol(SymbolUtil.verifyAndStripSingleOuterSymbol(surroundedSymbol), 
				this.innerSymbolArray.length - 1);
	}


	@Override
	public boolean verifySingleSymbol(final String surroundedSymbol) {
		return verifyInnerSymbol(SymbolUtil.verifyAndStripSingleOuterSymbol(surroundedSymbol));
	}

	protected abstract boolean verifyInnerSymbol(String innerSymbol);


	@Override
	public boolean verifySingleSymbol(final String surroundedSymbol, final int index) {
		return verifyInnerSymbol(SymbolUtil.verifyAndStripSingleOuterSymbol(surroundedSymbol), index);
	}

	protected abstract boolean verifyInnerSymbol(String innerSymbol, int index);

}
