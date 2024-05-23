package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.DynamicHypotheticalPropositionSymbol;
import com.codejstudio.algi.action.symbolAction.DynamicHypotheticalPropositionSymbolAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DynamicHypotheticalPropositionSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -7237158746778524947L;


	/* constructors */

	public DynamicHypotheticalPropositionSymbolElement(String source) throws LIMException {
		super();
		init(source, DynamicHypotheticalPropositionSymbolAction.class, 
				DynamicHypotheticalPropositionSymbol.getInstance());
	}

	public DynamicHypotheticalPropositionSymbolElement(String source, SymbolAction symbolAction) 
			throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
