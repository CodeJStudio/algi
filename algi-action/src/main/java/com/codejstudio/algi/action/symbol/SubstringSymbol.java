package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolElement.SubstringSymbolElement;
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
public class SubstringSymbol extends AbstractMultiContentSymbol {

	/* constants */

	private static final long serialVersionUID = 668977326049246793L;

	public static final String SYMBOL = "ss";//Substring

	public static final Class<? extends ISymbolElement> SYMBOL_ELEMENT_CLAZZ = SubstringSymbolElement.class;
	private static final SubstringSymbol SYMBOL_INSTANCE = new SubstringSymbol();
	private static final SimpleSurroundingSymbolCombination SYMBOL_COMBINATION_INSTANCE;

	private static final int SIZE = 3;
	private static final boolean[] NECESSITY_ARRAY = {true, true, false};
	private static final Class<?>[] TYPE_ARRAY = {String.class, Integer.class, Integer.class};


	/* constructors */

	private SubstringSymbol() {
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
		SymbolConstant.registerSymbolClassForInit(SubstringSymbol.class);
	}


	/* getters & setters */

	public static SubstringSymbol getInstance() {
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
