package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.SplitSymbol;
import com.codejstudio.algi.action.symbolAction.SplitAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SplitSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -7684446026528473653L;


	/* constructors */

	public SplitSymbolElement(String source) throws LIMException {
		super();
		init(source, SplitAction.class, SplitSymbol.getInstance());
	}

	public SplitSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
