package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.CompareSymbol;
import com.codejstudio.algi.action.symbolAction.CompareAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CompareSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -6157697486870206246L;


	/* constructors */

	public CompareSymbolElement(String source) throws LIMException {
		super();
		init(source, CompareAction.class, CompareSymbol.getInstance());
	}

	public CompareSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
