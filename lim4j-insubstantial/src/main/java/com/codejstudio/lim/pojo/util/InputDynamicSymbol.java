package com.codejstudio.lim.pojo.util;

import com.codejstudio.lim.pojo.i.ISymbolCombination;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class InputDynamicSymbol extends AbstractDynamicSymbol {

	/* constants */

	private static final long serialVersionUID = -6258830414638369323L;

	public static final String SYMBOL = "$";

	private static final InputDynamicSymbol SYMBOL_INSTANCE = new InputDynamicSymbol();

	private static final NonSurroundingSymbolCombination SYMBOL_COMBINATION_INSTANCE;


	/* constructors */

	private InputDynamicSymbol() {
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
		SymbolConstant.registerSymbolClassForInit(InputDynamicSymbol.class);
	}


	/* getters & setters */

	public static InputDynamicSymbol getSymbolInstance() {
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
