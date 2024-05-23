package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DRCWithoutParametersSymbolCombination extends DoRepeatedlyConditionSymbolCombination {

	/* constants */

	private static final long serialVersionUID = -4443461534257521302L;

	private static final DRCWithoutParametersSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new DRCWithoutParametersSymbolCombination();


	/* constructors */

	private DRCWithoutParametersSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_DRC_WITHOUT_PARAMETERS_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static DRCWithoutParametersSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
