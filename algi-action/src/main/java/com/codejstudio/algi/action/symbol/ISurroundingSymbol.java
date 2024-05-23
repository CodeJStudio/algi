package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.pojo.i.ISymbol;
import com.codejstudio.lim.pojo.i.ISymbolElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public interface ISurroundingSymbol extends IBaseSurroundingSymbol {

	/* constants */

	static final char SYMBOL_LEFT = '(';
	static final char SYMBOL_RIGHT = ')';

	static final Character[] ALL_BASIC_SYMBOL_ARRAY = new Character[] {
			ISymbol.SYMBOL_ESCAPE_CHARACTER, 
			SYMBOL_LEFT, 
			SYMBOL_RIGHT, 
	};


	/* interface methods */

	String strip(String surroundedContent);

	String surround(String strippedContent);


	Class<? extends ISymbolElement> getSymbolElementClass();

	ISymbolElement generateSymbolElement(String source, SymbolAction symbolAction);

}
