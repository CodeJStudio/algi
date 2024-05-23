package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolElement.LengthOfSymbolElement;
import com.codejstudio.lim.pojo.i.ISymbolCombination;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.SymbolConstant;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class LengthOfSymbol extends AbstractSingleContentSymbol {

	/* constants */

	private static final long serialVersionUID = -1285405383580382057L;

	public static final String SYMBOL = "lo";//Length Of

	public static final Class<? extends ISymbolElement> SYMBOL_ELEMENT_CLAZZ = LengthOfSymbolElement.class;
	private static final LengthOfSymbol SYMBOL_INSTANCE = new LengthOfSymbol();
	private static final SimpleSurroundingSymbolCombination SYMBOL_COMBINATION_INSTANCE;

	private static final Class<?> TYPE = Object.class;


	/* constructors */

	private LengthOfSymbol() {
		super();
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		SYMBOL_COMBINATION_INSTANCE = new SimpleSurroundingSymbolCombination(SYMBOL_INSTANCE, 
				getSurroundingSymbolCombination(SYMBOL), SYMBOL_ELEMENT_CLAZZ);
		SymbolUtil.registerSymbolCombinationForInit(SYMBOL_COMBINATION_INSTANCE);
		SymbolConstant.registerSymbolClassForInit(LengthOfSymbol.class);
	}


	/* getters & setters */

	public static LengthOfSymbol getInstance() {
		return SYMBOL_INSTANCE;
	}

	public static ISymbolCombination getSymbolCombinationInstance() {
		return SYMBOL_COMBINATION_INSTANCE;
	}


	@Override
	public String getInnerSymbol() {
		return SYMBOL;
	}

	@Override
	public Class<? extends ISymbolElement> getSymbolElementClass() {
		return SYMBOL_ELEMENT_CLAZZ;
	}

	@Override
	public Class<?> getContentType() {
		return TYPE;
	}

}
