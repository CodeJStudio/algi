package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.SubstringSymbol;
import com.codejstudio.algi.action.symbolAction.SubstringAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SubstringSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 2496297942546139443L;


	/* constructors */

	public SubstringSymbolElement(String source) throws LIMException {
		super();
		init(source, SubstringAction.class, SubstringSymbol.getInstance());
	}

	public SubstringSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
