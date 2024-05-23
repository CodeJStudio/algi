package com.codejstudio.lim.pojo.i;

import java.io.Serializable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface ISymbolCombination extends Serializable {

	/* interface methods */

	String[] getInnerSymbolArray();

	int length();

	int extraLength();

	String getSingleInnerSymbol(int index);


	boolean firstSingleSymbol(String surroundedSymbol);

	boolean lastSingleSymbol(String surroundedSymbol);


	boolean verifySingleSymbol(String surroundedSymbol);

	boolean verifySingleSymbol(String surroundedSymbol, int index);

}
