package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CDRWithParametersSymbolCombination extends ConditionDoRepeatedlySymbolCombination {

	/* constants */

	private static final long serialVersionUID = 7418357066578317305L;

	private static final CDRWithParametersSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new CDRWithParametersSymbolCombination();


	/* constructors */

	private CDRWithParametersSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_CDR_WITH_PARAMETERS_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static CDRWithParametersSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
