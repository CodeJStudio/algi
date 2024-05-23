package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.ActionableExecuteSymbol;
import com.codejstudio.algi.action.symbolAction.ActionableExecuteAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ActionableExecuteSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 822685561673400806L;


	/* constructors */

	public ActionableExecuteSymbolElement(String source) throws LIMException {
		super();
		init(source, ActionableExecuteAction.class, ActionableExecuteSymbol.getInstance());
	}

	public ActionableExecuteSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
