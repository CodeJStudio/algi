package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DRCWithParametersSymbolCombination extends DoRepeatedlyConditionSymbolCombination {

	/* constants */

	private static final long serialVersionUID = 359797173225532031L;

	private static final DRCWithParametersSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new DRCWithParametersSymbolCombination();


	/* constructors */

	private DRCWithParametersSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_DRC_WITH_PARAMETERS_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static DRCWithParametersSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
