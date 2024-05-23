package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DRCWithParametersRepeatedSymbolCombination extends DoRepeatedlyConditionSymbolCombination {

	/* constants */

	private static final long serialVersionUID = 5777027277954196375L;

	private static final DRCWithParametersRepeatedSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new DRCWithParametersRepeatedSymbolCombination();


	/* constructors */

	private DRCWithParametersRepeatedSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_DRC_WITH_PARAMETERS_REPEATED_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static DRCWithParametersRepeatedSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
