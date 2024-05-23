package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolElement.ConditionDoRepeatedlySymbolElement;
import com.codejstudio.lim.pojo.i.ISymbolElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class ConditionDoRepeatedlySymbolCombination extends AbstractDoRepeatedlySymbolCombination {

	/* constants */

	private static final long serialVersionUID = -3516089673566565554L;

	public static final Class<? extends ISymbolElement> SYMBOL_ELEMENT_CLAZZ 
			= ConditionDoRepeatedlySymbolElement.class;

	protected static final String[][] ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION = new String[][] {
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(5), // po{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(1), // }cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(4), // }dr(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(6), // }pr{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(5), // po{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(1), // }cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(4), // }dr(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(0), // cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(4), // }dr(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(6), // }pr{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(0), // cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(4), // }dr(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
	};

	protected static final int INDEX_OF_CDR_WITH_PARAMETERS_SYMBOL_COMBINATION = 0;
	protected static final int INDEX_OF_CDR_WITH_PARAMETERS_ONCE_SYMBOL_COMBINATION = 1;
	protected static final int INDEX_OF_CDR_WITH_PARAMETERS_REPEATED_SYMBOL_COMBINATION = 2;
	protected static final int INDEX_OF_CDR_WITHOUT_PARAMETERS_SYMBOL_COMBINATION = 3;

	private static final ConditionDoRepeatedlySymbolCombination[] SYMBOL_COMBINATION_INSTANCE_ARRAY 
			= new ConditionDoRepeatedlySymbolCombination[] {
					CDRWithParametersSymbolCombination.getInstance(), 
					CDRWithParametersOnceSymbolCombination.getInstance(), 
					CDRWithParametersRepeatedSymbolCombination.getInstance(), 
					CDRWithoutParametersSymbolCombination.getInstance(), 
	};


	/* constructors */

	protected ConditionDoRepeatedlySymbolCombination(String[] symbolArrayForCombination) {
		super(symbolArrayForCombination, SYMBOL_ELEMENT_CLAZZ);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		init(SYMBOL_COMBINATION_INSTANCE_ARRAY);
	}


	/* static methods */

	public static ConditionDoRepeatedlySymbolCombination getInstance(final int type) {
		return (ConditionDoRepeatedlySymbolCombination) getInstance(type, SYMBOL_COMBINATION_INSTANCE_ARRAY);
	}


	public static boolean verifySymbolArray(final String[] symbolArray) {
		return verifySymbolArray(symbolArray, ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION);
	}

	public static int verifySymbolArrayAndGetCombinationType(final String[] symbolArray) {
		return verifySymbolArrayAndGetCombinationType(symbolArray, ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION);
	}

}
