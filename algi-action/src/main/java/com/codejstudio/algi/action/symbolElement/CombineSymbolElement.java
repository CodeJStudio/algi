package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.CombineSymbol;
import com.codejstudio.algi.action.symbolAction.CombineAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CombineSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -6840194244326118465L;


	/* constructors */

	public CombineSymbolElement(String source) throws LIMException {
		super();
		init(source, CombineAction.class, CombineSymbol.getInstance());
	}

	public CombineSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
