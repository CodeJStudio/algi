package com.codejstudio.algi.action.symbol;

import java.util.Arrays;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractDoRepeatedlySymbolCombination extends AbstractComplexSymbolCombination {

	/* constants */

	private static final long serialVersionUID = 9034923629880818750L;


	/* constructors */

	protected AbstractDoRepeatedlySymbolCombination(String[] symbolArrayForCombination, 
			Class<? extends ISymbolElement> symbolElementClazz) {
		super(symbolArrayForCombination, symbolElementClazz);
	}


	/* overridden methods */

	@Override
	public Object[] strip(final String surroundedContent) throws LIMException {
		int[] indexArray;
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(surroundedContent);
		if (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) 
				|| (indexArray = CollectionUtil.indexOf(SymbolUtil.verifyAndStripSingleOuterSymbol(
						arrayOfFragmentArrays[0]), this.innerSymbolArray)) == null) {
			return null;
		}

		// {[ParametersOnce], Condition, LogicValue, ExecutingBody, [ParametersRepeated]} for CDR
		// {[ParametersOnce], LogicValue, ExecutingBody, Condition, [ParametersRepeated]} for DRC
		String[] returnArray = new String[5];
		switch (indexArray.length) {
			case 4:{
				returnArray[1] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[0] + 1, indexArray[1]));
				returnArray[2] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[1] + 1, indexArray[2]));
				returnArray[3] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[2] + 1, indexArray[3]));
				break;
			}
			case 5:{
				int i = (this instanceof CDRWithParametersOnceSymbolCombination 
						|| this instanceof DRCWithParametersOnceSymbolCombination) 
						? 0 : 1;
				returnArray[i++] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[0] + 1, indexArray[1]));
				returnArray[i++] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[1] + 1, indexArray[2]));
				returnArray[i++] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[2] + 1, indexArray[3]));
				returnArray[i] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[3] + 1, indexArray[4]));
				break;
			}
			case 6:{
				returnArray[0] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[0] + 1, indexArray[1]));
				returnArray[1] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[1] + 1, indexArray[2]));
				returnArray[2] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[2] + 1, indexArray[3]));
				returnArray[3] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[3] + 1, indexArray[4]));
				returnArray[4] = String.join("", 
						Arrays.copyOfRange(arrayOfFragmentArrays[0], indexArray[4] + 1, indexArray[5]));
				break;
			}
			default:
				return null;
		}

		return returnArray;
	}

}
