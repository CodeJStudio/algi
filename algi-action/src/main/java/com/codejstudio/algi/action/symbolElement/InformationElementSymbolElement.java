package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.InformationElementSymbol;
import com.codejstudio.algi.action.symbolAction.InformationElementAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class InformationElementSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 6143460906422858755L;


	/* constructors */

	public InformationElementSymbolElement(String source) throws LIMException {
		super();
		init(source, InformationElementAction.class, InformationElementSymbol.getInstance());
	}

	public InformationElementSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
