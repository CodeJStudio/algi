package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.DoRepeatedlyConditionSymbolCombination;
import com.codejstudio.algi.action.symbolAction.DoRepeatedlyConditionAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DoRepeatedlyConditionSymbolElement extends AbstractComplexSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 144408562258512153L;


	/* constructors */

	public DoRepeatedlyConditionSymbolElement(String source, DoRepeatedlyConditionAction drcAction) 
			throws LIMException {
		super();
		init(source, DoRepeatedlyConditionAction.class); 
		initAction(drcAction);
	}

	public DoRepeatedlyConditionSymbolElement(String source) throws LIMException {
		this(source, null);
	}


	/* initializers */

	@Override
	protected void initAction(final SymbolAction complexAction) throws LIMException {
		this.symbolCombination = (this.symbolCombination != null) ? this.symbolCombination 
				: DoRepeatedlyConditionSymbolCombination.getInstance(DoRepeatedlyConditionSymbolCombination
						.verifySymbolArrayAndGetCombinationType(this.sourceSymbolFragmentArray));
		super.initAction(complexAction);
	}

}
