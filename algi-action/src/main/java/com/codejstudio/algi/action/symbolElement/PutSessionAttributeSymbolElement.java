package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.PutSessionAttributeSymbol;
import com.codejstudio.algi.action.symbolAction.PutSessionAttributeAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class PutSessionAttributeSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -7468117072075339779L;


	/* constructors */

	public PutSessionAttributeSymbolElement(String source) throws LIMException {
		super();
		init(source, PutSessionAttributeAction.class, PutSessionAttributeSymbol.getInstance());
	}

	public PutSessionAttributeSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
