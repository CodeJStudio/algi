package com.codejstudio.algi.action.symbol;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.i.ISymbolCombination;
import com.codejstudio.lim.pojo.i.ISymbolElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public interface ISurroundingSymbolCombination extends ISymbolCombination {

	/* interface methods */

	ISymbolElement generateSymbolElement(String source);

	boolean verifySurroundingSymbol(String surroundedContent) throws LIMException;

	Object[] strip(String surroundedContent) throws LIMException;

}
