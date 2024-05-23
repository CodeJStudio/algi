package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbol.ConditionDoRepeatedlySymbolCombination;
import com.codejstudio.algi.action.symbolAction.ConditionDoRepeatedlyAction;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ConditionDoRepeatedlySymbolElement extends AbstractComplexSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 5295044910653709922L;


	/* constructors */

	public ConditionDoRepeatedlySymbolElement(String source, ConditionDoRepeatedlyAction cdrAction) 
			throws LIMException {
		super();
		init(source, ConditionDoRepeatedlyAction.class); 
		initAction(cdrAction);
	}

	public ConditionDoRepeatedlySymbolElement(String source) throws LIMException {
		this(source, null);
	}


	/* initializers */

	@Override
	protected void initAction(final SymbolAction complexAction) throws LIMException {
		this.symbolCombination = (this.symbolCombination != null) ? this.symbolCombination 
				: ConditionDoRepeatedlySymbolCombination.getInstance(ConditionDoRepeatedlySymbolCombination
						.verifySymbolArrayAndGetCombinationType(this.sourceSymbolFragmentArray));
		super.initAction(complexAction);
	}

}
