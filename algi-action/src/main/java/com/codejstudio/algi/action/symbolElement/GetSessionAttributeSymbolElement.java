package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.GetSessionAttributeSymbol;
import com.codejstudio.algi.action.symbolAction.GetSessionAttributeAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class GetSessionAttributeSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -9016458027296142528L;


	/* constructors */

	public GetSessionAttributeSymbolElement(String source) throws LIMException {
		super();
		init(source, GetSessionAttributeAction.class, GetSessionAttributeSymbol.getInstance());
	}

	public GetSessionAttributeSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
