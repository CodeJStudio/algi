package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.SimpleSentenceThinkSymbol;
import com.codejstudio.algi.action.symbolAction.SimpleSentenceThinkAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SimpleSentenceThinkSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 2247444970778744762L;


	/* constructors */

	public SimpleSentenceThinkSymbolElement(String source) throws LIMException {
		super();
		init(source, SimpleSentenceThinkAction.class, SimpleSentenceThinkSymbol.getInstance());
	}

	public SimpleSentenceThinkSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
