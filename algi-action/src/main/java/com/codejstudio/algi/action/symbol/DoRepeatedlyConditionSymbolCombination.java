package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolElement.DoRepeatedlyConditionSymbolElement;
import com.codejstudio.lim.pojo.i.ISymbolElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class DoRepeatedlyConditionSymbolCombination extends AbstractDoRepeatedlySymbolCombination {

	/* constants */

	private static final long serialVersionUID = -365139753368412618L;

	public static final Class<? extends ISymbolElement> SYMBOL_ELEMENT_CLAZZ 
			= DoRepeatedlyConditionSymbolElement.class;

	protected static final String[][] ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION = new String[][] {
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(5), // po{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(4), // }dr(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(1), // }cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(6), // }pr{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(5), // po{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(4), // }dr(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(1), // }cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(3), // dr(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(1), // }cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(6), // }pr{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(3), // dr(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(1), // }cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
	};

	protected static final int INDEX_OF_DRC_WITH_PARAMETERS_SYMBOL_COMBINATION = 0;
	protected static final int INDEX_OF_DRC_WITH_PARAMETERS_ONCE_SYMBOL_COMBINATION = 1;
	protected static final int INDEX_OF_DRC_WITH_PARAMETERS_REPEATED_SYMBOL_COMBINATION = 2;
	protected static final int INDEX_OF_DRC_WITHOUT_PARAMETERS_SYMBOL_COMBINATION = 3;

	private static final DoRepeatedlyConditionSymbolCombination[] SYMBOL_COMBINATION_INSTANCE_ARRAY 
			= new DoRepeatedlyConditionSymbolCombination[] {
					DRCWithParametersSymbolCombination.getInstance(), 
					DRCWithParametersOnceSymbolCombination.getInstance(), 
					DRCWithParametersRepeatedSymbolCombination.getInstance(), 
					DRCWithoutParametersSymbolCombination.getInstance(), 
	};


	/* constructors */

	protected DoRepeatedlyConditionSymbolCombination(String[] symbolArrayForCombination) {
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

	public static DoRepeatedlyConditionSymbolCombination getInstance(final int type) {
		return (DoRepeatedlyConditionSymbolCombination) getInstance(type, SYMBOL_COMBINATION_INSTANCE_ARRAY);
	}


	public static boolean verifySymbolArray(final String[] symbolArray) {
		return verifySymbolArray(symbolArray, ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION);
	}

	public static int verifySymbolArrayAndGetCombinationType(final String[] symbolArray) {
		return verifySymbolArrayAndGetCombinationType(symbolArray, ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION);
	}

}
