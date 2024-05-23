package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.DynamicStatementSymbol;
import com.codejstudio.algi.action.symbolAction.DynamicStatementSymbolAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DynamicStatementSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -5144279340666922585L;


	/* constructors */

	public DynamicStatementSymbolElement(String source) throws LIMException {
		super();
		init(source, DynamicStatementSymbolAction.class, DynamicStatementSymbol.getInstance());
	}

	public DynamicStatementSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
