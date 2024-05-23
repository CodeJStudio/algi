package com.codejstudio.algi.action.symbol;

import com.codejstudio.lim.pojo.i.ISymbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public interface IComplexSurroundingSymbol extends IBaseSurroundingSymbol {

	/* constants */

	static final char SYMBOL_LEFT = '{';
	static final char SYMBOL_RIGHT = '}';

	static final Character[] ALL_BASIC_SYMBOL_ARRAY = new Character[] {
			ISymbol.SYMBOL_ESCAPE_CHARACTER, 
			SYMBOL_LEFT, 
			SYMBOL_RIGHT, 
	};


	/* interface methods */

	boolean verifyMidSurroundingSymbol(String surroundedContent);

}
