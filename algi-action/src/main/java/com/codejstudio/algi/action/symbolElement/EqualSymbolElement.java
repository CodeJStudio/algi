package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.EqualSymbol;
import com.codejstudio.algi.action.symbolAction.EqualAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class EqualSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 7616744440045809051L;


	/* constructors */

	public EqualSymbolElement(String source) throws LIMException {
		super();
		init(source, EqualAction.class, EqualSymbol.getInstance());
	}

	public EqualSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
