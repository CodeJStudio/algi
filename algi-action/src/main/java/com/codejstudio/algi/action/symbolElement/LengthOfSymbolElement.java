package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.LengthOfSymbol;
import com.codejstudio.algi.action.symbolAction.LengthOfAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class LengthOfSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 8584352522060996221L;


	/* constructors */

	public LengthOfSymbolElement(String source) throws LIMException {
		super();
		init(source, LengthOfAction.class, LengthOfSymbol.getInstance());
	}

	public LengthOfSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
