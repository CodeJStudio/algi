package com.codejstudio.lim.pojo.util;

import com.codejstudio.lim.pojo.i.ISymbol;
import com.codejstudio.lim.pojo.i.ISymbolCombination;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class OutputDynamicSymbol extends AbstractDynamicSymbol {

	/* constants */

	private static final long serialVersionUID = -5312911556031949805L;

	public static final String SYMBOL = "#";

	public static final String DYNAMIC_RESULT = "" + ISymbol.SYMBOL_LEFT 
												+ SYMBOL + "R" 
												+ ISymbol.SYMBOL_RIGHT;

	private static final OutputDynamicSymbol SYMBOL_INSTANCE = new OutputDynamicSymbol();

	private static final NonSurroundingSymbolCombination SYMBOL_COMBINATION_INSTANCE;


	/* constructors */

	private OutputDynamicSymbol() {
		super();
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		SYMBOL_COMBINATION_INSTANCE = new NonSurroundingSymbolCombination(
				SYMBOL_INSTANCE, new String[] {SYMBOL});
		SymbolUtil.registerSymbolCombinationForInit(SYMBOL_COMBINATION_INSTANCE);
		SymbolConstant.registerSymbolClassForInit(OutputDynamicSymbol.class);
	}


	/* getters & setters */

	public static OutputDynamicSymbol getSymbolInstance() {
		return SYMBOL_INSTANCE;
	}

	public static ISymbolCombination getSymbolCombinationInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}


	@Override
	public String getInnerSymbol() {
		return SYMBOL;
	}

}
