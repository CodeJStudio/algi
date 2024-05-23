package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.GetElementSymbol;
import com.codejstudio.algi.action.symbolAction.GetElementAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class GetElementSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 4700201471288675230L;


	/* constructors */

	public GetElementSymbolElement(String source) throws LIMException {
		super();
		init(source, GetElementAction.class, GetElementSymbol.getInstance());
	}

	public GetElementSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
