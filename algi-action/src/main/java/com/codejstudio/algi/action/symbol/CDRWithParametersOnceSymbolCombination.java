package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CDRWithParametersOnceSymbolCombination extends ConditionDoRepeatedlySymbolCombination {

	/* constants */

	private static final long serialVersionUID = -359438988933552973L;

	private static final CDRWithParametersOnceSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new CDRWithParametersOnceSymbolCombination();


	/* constructors */

	private CDRWithParametersOnceSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_CDR_WITH_PARAMETERS_ONCE_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static CDRWithParametersOnceSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
