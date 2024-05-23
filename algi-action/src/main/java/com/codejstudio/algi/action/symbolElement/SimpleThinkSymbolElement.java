package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.SimpleThinkSymbol;
import com.codejstudio.algi.action.symbolAction.SimpleThinkAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SimpleThinkSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 6478137385791835266L;


	/* constructors */

	public SimpleThinkSymbolElement(String source) throws LIMException {
		super();
		init(source, SimpleThinkAction.class, SimpleThinkSymbol.getInstance());
	}

	public SimpleThinkSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
