package com.codejstudio.lim.pojo.i;

import java.io.Serializable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface ISymbol extends Serializable {

	/* constants */

	static final char SYMBOL_ESCAPE_CHARACTER = '\\';
	static final char SYMBOL_LEFT = '<';
	static final char SYMBOL_RIGHT = '>';

	static final Character[] ALL_BASIC_SYMBOL_ARRAY = new Character[] {
			SYMBOL_ESCAPE_CHARACTER, 
			SYMBOL_LEFT, 
			SYMBOL_RIGHT, 
	};


	/* interface methods */

	String getInnerSymbol();


	boolean verifySymbol(String symbolContent);

	boolean verifySingleSymbol(String surroundedSymbol);

}
