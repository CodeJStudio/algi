package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.CodeExecuteSymbol;
import com.codejstudio.algi.action.symbolAction.CodeExecuteAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CodeExecuteSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -5813012451596717593L;


	/* constructors */

	public CodeExecuteSymbolElement(String source) throws LIMException {
		super();
		init(source, CodeExecuteAction.class, CodeExecuteSymbol.getInstance());
	}

	public CodeExecuteSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
