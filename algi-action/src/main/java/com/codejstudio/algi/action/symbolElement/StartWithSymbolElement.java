package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.StartWithSymbol;
import com.codejstudio.algi.action.symbolAction.StartWithAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class StartWithSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 2831769670418015576L;


	/* constructors */

	public StartWithSymbolElement(String source) throws LIMException {
		super();
		init(source, StartWithAction.class, StartWithSymbol.getInstance());
	}

	public StartWithSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
