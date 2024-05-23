package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.VerifyConditionSymbol;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.algi.action.symbolAction.VerifyConditionAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class VerifyConditionSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = -6625252339849920918L;


	/* constructors */

	public VerifyConditionSymbolElement(String source) throws LIMException {
		super();
		init(source, VerifyConditionAction.class, VerifyConditionSymbol.getInstance());
	}

	public VerifyConditionSymbolElement(String source, SymbolAction symbolAction) throws LIMException {
		this(source);
		initAction(symbolAction);
	}

}
