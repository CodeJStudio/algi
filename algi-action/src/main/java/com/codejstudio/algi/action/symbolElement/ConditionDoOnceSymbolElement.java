package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.ConditionDoOnceSymbolCombination;
import com.codejstudio.algi.action.symbolAction.ConditionDoOnceAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ConditionDoOnceSymbolElement extends AbstractComplexSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 8277491016486278399L;


	/* constructors */

	public ConditionDoOnceSymbolElement(String source, ConditionDoOnceAction cdoAction) throws LIMException {
		super();
		init(source, ConditionDoOnceAction.class); 
		initAction(cdoAction);
	}

	public ConditionDoOnceSymbolElement(String source) throws LIMException {
		this(source, null);
	}


	/* initializers */

	@Override
	protected void initAction(final SymbolAction complexAction) throws LIMException {
		this.symbolCombination = (this.symbolCombination != null) ? this.symbolCombination 
				: ConditionDoOnceSymbolCombination.getInstance(ConditionDoOnceSymbolCombination
						.verifySymbolArrayAndGetCombinationType(this.sourceSymbolFragmentArray));
		super.initAction(complexAction);
	}

}
