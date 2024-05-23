package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DRCWithParametersOnceSymbolCombination extends DoRepeatedlyConditionSymbolCombination {

	/* constants */

	private static final long serialVersionUID = -3811582300399318594L;

	private static final DRCWithParametersOnceSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new DRCWithParametersOnceSymbolCombination();


	/* constructors */

	private DRCWithParametersOnceSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_DRC_WITH_PARAMETERS_ONCE_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static DRCWithParametersOnceSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
