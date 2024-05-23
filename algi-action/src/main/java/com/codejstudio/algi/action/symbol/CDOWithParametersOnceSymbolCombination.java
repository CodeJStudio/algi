package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CDOWithParametersOnceSymbolCombination extends ConditionDoOnceSymbolCombination {

	/* constants */

	private static final long serialVersionUID = 7031858198865553906L;

	private static final CDOWithParametersOnceSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new CDOWithParametersOnceSymbolCombination();


	/* constructors */

	private CDOWithParametersOnceSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_CDO_WITH_PARAMETERS_ONCE_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static CDOWithParametersOnceSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
