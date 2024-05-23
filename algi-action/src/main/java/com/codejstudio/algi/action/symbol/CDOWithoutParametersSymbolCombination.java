package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CDOWithoutParametersSymbolCombination extends ConditionDoOnceSymbolCombination {

	/* constants */

	private static final long serialVersionUID = -2651736561404013153L;

	private static final CDOWithoutParametersSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new CDOWithoutParametersSymbolCombination();


	/* constructors */

	private CDOWithoutParametersSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_CDO_WITHOUT_PARAMETERS_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static CDOWithoutParametersSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
