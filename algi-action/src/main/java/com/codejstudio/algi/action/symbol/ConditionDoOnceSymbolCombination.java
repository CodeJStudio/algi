package com.codejstudio.algi.action.symbol;

import java.util.Arrays;
import java.util.List;

import com.codejstudio.algi.action.symbolElement.ConditionDoOnceSymbolElement;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class ConditionDoOnceSymbolCombination extends AbstractComplexSymbolCombination {

	/* constants */

	private static final long serialVersionUID = -2373865466390631807L;

	public static final Class<? extends ISymbolElement> SYMBOL_ELEMENT_CLAZZ 
			= ConditionDoOnceSymbolElement.class;

	protected static final String[][] ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION = new String[][] {
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(5), // po{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(1), // }cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(2), // }do(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
		new String[] {
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(0), // cd{
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(2), // }do(
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), // ){
				AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(8), // }
		}, 
	};

	protected static final int INDEX_OF_CDO_WITH_PARAMETERS_ONCE_SYMBOL_COMBINATION = 0;
	protected static final int INDEX_OF_CDO_WITHOUT_PARAMETERS_SYMBOL_COMBINATION = 1;

	protected static final String[] EXTRA_SURROUNDING_SYMBOL_ARRAY = new String[] {
			AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(2), //}do(
			AbstractComplexSurroundingSymbol.COMPLEX_SYMBOL_LIST.get(7), //){
	};

	private static final ConditionDoOnceSymbolCombination[] SYMBOL_COMBINATION_INSTANCE_ARRAY 
			= new ConditionDoOnceSymbolCombination[] {
					CDOWithParametersOnceSymbolCombination.getInstance(), 
					CDOWithoutParametersSymbolCombination.getInstance(), 
	};


	/* constructors */

	protected ConditionDoOnceSymbolCombination(String[] symbolArrayForCombination) {
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


	/* overridden methods */

	@Override
	public int extraLength() {
		return EXTRA_SURROUNDING_SYMBOL_ARRAY.length;
	}


	@Override
	public Object[] strip(final String surroundedContent) throws LIMException {
		int[] indexArray;
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(surroundedContent);
		if (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) 
				|| (indexArray = CollectionUtil.indexOf(SymbolUtil.verifyAndStripSingleOuterSymbol(
						arrayOfFragmentArrays[0]), this.innerSymbolArray)) == null) {
			return null;
		}

		// {[ParametersOnce], Condition, LogicValue[], ExecutingBody[]} for CDO
		Object[] returnArray = new Object[4];
		switch (indexArray.length) {
			case 4:{
				returnArray[1] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[0] + 1, indexArray[1]));
				assembleMultiValues(arrayOfFragmentArrays[0], indexArray, 1, 2, returnArray);
				break;
			}
			case 5:{
				returnArray[0] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[0] + 1, indexArray[1]));
				returnArray[1] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[1] + 1, indexArray[2]));
				assembleMultiValues(arrayOfFragmentArrays[0], indexArray, 2, 3, returnArray);
				break;
			}
			default:
				return null;
		}

		return returnArray;
	}

	private void assembleMultiValues(final String[] fragmentArray, final int[] indexArray, 
			final int fromIndex, final int toIndex, Object[] returnArray) throws LIMException {
		List<String> logicValueList = CollectionUtil.generateNewList();
		List<String> executingBodyList = CollectionUtil.generateNewList();
		logicValueList.add(String.join("", 
				Arrays.copyOfRange(fragmentArray, indexArray[fromIndex] + 1, indexArray[toIndex])));
		String[] eba = Arrays.copyOfRange(
				fragmentArray, indexArray[fromIndex + 1] + 1, indexArray[toIndex + 1]);
		int[] ia;
		while ((ia = CollectionUtil.indexOf(SymbolUtil.verifyAndStripSingleOuterSymbol(eba), 
				EXTRA_SURROUNDING_SYMBOL_ARRAY)) != null) {
			executingBodyList.add(String.join("", Arrays.copyOfRange(eba, 0, ia[0])));
			logicValueList.add(String.join("", Arrays.copyOfRange(eba, ia[0] + 1, ia[1])));
			eba = Arrays.copyOfRange(eba, ia[1] + 1, eba.length);
		}
		executingBodyList.add(String.join("", eba));

		returnArray[2] = logicValueList.toArray(new String[0]);
		returnArray[3] = executingBodyList.toArray(new String[0]);
	}


	@Override
	public boolean lastSingleSymbol(final String surroundedSymbol) {
		return verifyInnerSymbol(SymbolUtil.verifyAndStripSingleOuterSymbol(surroundedSymbol), 
				(this.innerSymbolArray.length - 1), false);
	}

	@Override
	protected boolean verifyInnerSymbol(final String innerSymbol, final int index) {
		return verifyInnerSymbol(innerSymbol, index, true);
	}

	private boolean verifyInnerSymbol(final String innerSymbol, final int index, final boolean extraFlag) {
		if (StringUtil.isEmpty(innerSymbol) || index < 0 
				|| CollectionUtil.checkNullOrEmpty(this.innerSymbolArray) 
				|| (extraFlag && CollectionUtil.checkNullOrEmpty(EXTRA_SURROUNDING_SYMBOL_ARRAY))) {
			return false;
		}

		if (index < this.innerSymbolArray.length - 1) {
			return innerSymbol.equalsIgnoreCase(this.innerSymbolArray[index]);
		}

		int remainder = (index - this.innerSymbolArray.length + 1) 
				% EXTRA_SURROUNDING_SYMBOL_ARRAY.length;
		return (remainder == 0 
						&& innerSymbol.equalsIgnoreCase(
								this.innerSymbolArray[this.innerSymbolArray.length - 1])) 
				|| (extraFlag && innerSymbol.equalsIgnoreCase(EXTRA_SURROUNDING_SYMBOL_ARRAY[remainder]));
	}


	/* static methods */

	public static ConditionDoOnceSymbolCombination getInstance(final int type) {
		return (ConditionDoOnceSymbolCombination) getInstance(type, SYMBOL_COMBINATION_INSTANCE_ARRAY);
	}


	public static boolean verifySymbolArray(final String[] symbolArray) {
		return verifySymbolArray(symbolArray, ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION);
	}

	public static int verifySymbolArrayAndGetCombinationType(final String[] symbolArray) {
		return verifySymbolArrayAndGetCombinationType(symbolArray, ARRAY_OF_SYMBOL_ARRAYS_FOR_COMBINATION);
	}

}
