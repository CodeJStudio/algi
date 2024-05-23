package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.SubstituteDynamicIdentifierSymbol;
import com.codejstudio.algi.action.symbolAction.SubstituteDynamicIdentifierAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SubstituteDynamicIdentifierSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -7844142189277540927L;


	/* constructors */

	public SubstituteDynamicIdentifierSymbolElement(String source) throws LIMException {
		super();
		init(source, SubstituteDynamicIdentifierAction.class, SubstituteDynamicIdentifierSymbol.getInstance());
	}

	public SubstituteDynamicIdentifierSymbolElement(String source, SymbolAction symbolAction) 
			throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
