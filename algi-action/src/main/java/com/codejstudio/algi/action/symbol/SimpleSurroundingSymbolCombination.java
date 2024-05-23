package com.codejstudio.algi.action.symbol;

import com.codejstudio.lim.pojo.i.ISymbolElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SimpleSurroundingSymbolCombination extends AbstractSurroundingSymbolCombination {

	/* constants */

	private static final long serialVersionUID = -4562047637171198589L;


	/* variables */

	protected ISurroundingSymbol symbol;


	/* constructors */

	protected SimpleSurroundingSymbolCombination(ISurroundingSymbol symbol, 
			String[] symbolArrayForCombination, Class<? extends ISymbolElement> symbolElementClazz) {
		super(symbolArrayForCombination, symbolElementClazz);
		this.symbol = symbol;
	}


	/* getters & setters */

	public ISurroundingSymbol getSymbol() {
		return symbol;
	}


	/* overridden methods */

	@Override
	public boolean verifySurroundingSymbol(final String surroundedContent) {
		return symbol.verifySurroundingSymbol(surroundedContent);
	}

	@Override
	public Object[] strip(final String surroundedContent) {
		return new String[] {symbol.strip(surroundedContent)};
	}

}
