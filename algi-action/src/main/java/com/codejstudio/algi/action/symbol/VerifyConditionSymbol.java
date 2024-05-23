package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolElement.VerifyConditionSymbolElement;
import com.codejstudio.lim.pojo.LogicValue;
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
public class VerifyConditionSymbol extends AbstractMultiContentSymbol {

	/* constants */

	private static final long serialVersionUID = 5304552600751565853L;

	public static final String SYMBOL = "vc";//Verify Condition

	public static final Class<? extends ISymbolElement> SYMBOL_ELEMENT_CLAZZ 
			= VerifyConditionSymbolElement.class;
	private static final VerifyConditionSymbol SYMBOL_INSTANCE = new VerifyConditionSymbol();
	private static final SimpleSurroundingSymbolCombination SYMBOL_COMBINATION_INSTANCE;

	private static final int SIZE = 3;
	private static final boolean[] NECESSITY_ARRAY = {true, true, false};
	private static final Class<?>[] TYPE_ARRAY = {Object.class, Object.class, LogicValue.class};


	/* constructors */

	private VerifyConditionSymbol() {
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
		SymbolConstant.registerSymbolClassForInit(VerifyConditionSymbol.class);
	}


	/* getters & setters */

	public static VerifyConditionSymbol getInstance() {
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
	public int getContentSize() {
		return SIZE;
	}

	@Override
	public boolean[] getContentNecessityArray() {
		return NECESSITY_ARRAY;
	}

	@Override
	public Class<?>[] getContentTypeArray() {
		return TYPE_ARRAY;
	}

}
