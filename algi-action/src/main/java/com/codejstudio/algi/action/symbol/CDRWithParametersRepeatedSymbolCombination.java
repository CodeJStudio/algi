package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CDRWithParametersRepeatedSymbolCombination extends ConditionDoRepeatedlySymbolCombination {

	/* constants */

	private static final long serialVersionUID = -214167214703349763L;

	private static final CDRWithParametersRepeatedSymbolCombination SYMBOL_COMBINATION_INSTANCE 
			= new CDRWithParametersRepeatedSymbolCombination();


	/* constructors */

	private CDRWithParametersRepeatedSymbolCombination() {
		super(ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION[INDEX_OF_CDR_WITH_PARAMETERS_REPEATED_SYMBOL_COMBINATION]);
	}


	/* getters & setters */

	public static CDRWithParametersRepeatedSymbolCombination getInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}

}
