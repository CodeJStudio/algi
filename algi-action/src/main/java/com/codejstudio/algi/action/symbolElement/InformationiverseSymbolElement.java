package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.InformationiverseSymbol;
import com.codejstudio.algi.action.symbolAction.InformationiverseAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class InformationiverseSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 3912379271393957566L;


	/* constructors */

	public InformationiverseSymbolElement(String source) throws LIMException {
		super();
		init(source, InformationiverseAction.class, InformationiverseSymbol.getInstance());
	}

	public InformationiverseSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
