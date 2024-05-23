package com.codejstudio.lim.pojo.util;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class NonSurroundingSymbolCombination extends AbstractSymbolCombination {

	/* constants */

	private static final long serialVersionUID = 3844670734985930551L;


	/* variables */

	protected AbstractNonSurroundingSymbol symbol;


	/* constructors */

	protected NonSurroundingSymbolCombination(AbstractNonSurroundingSymbol symbol, 
			String[] symbolArrayForCombination) {
		super(symbolArrayForCombination);
		this.symbol = symbol;
	}


	/* getters & setters */

	public AbstractNonSurroundingSymbol getSymbol() {
		return symbol;
	}


	/* overridden methods */

	@Override
	protected boolean verifyInnerSymbol(final String innerSymbol) {
		return this.symbol.verifyInnerSymbol(innerSymbol);
	}

	@Override
	protected boolean verifyInnerSymbol(final String innerSymbol, final int index) {
		return (index != 0) ? false : this.symbol.verifyInnerSymbol(innerSymbol);
	}

}
