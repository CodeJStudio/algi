package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CDRWithoutParametersSymbolCombination extends ConditionDoRepeatedlySymbolCombination {

	/* constants */

	private static final long serialVersionUID = 4263572638628032420L;

	private static final CDRWithoutParametersSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new CDRWithoutParametersSymbolCombination();


	/* constructors */

	private CDRWithoutParametersSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_CDR_WITHOUT_PARAMETERS_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static CDRWithoutParametersSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
