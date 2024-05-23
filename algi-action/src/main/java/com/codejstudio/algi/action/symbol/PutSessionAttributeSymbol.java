package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolElement.PutSessionAttributeSymbolElement;
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
public class PutSessionAttributeSymbol extends AbstractMultiContentSymbol {

	/* constants */

	private static final long serialVersionUID = -1712963527609494291L;

	public static final String SYMBOL = "ps";//Put Attribute to Session

	public static final Class<? extends ISymbolElement> SYMBOL_ELEMENT_CLAZZ 
			= PutSessionAttributeSymbolElement.class;
	private static final PutSessionAttributeSymbol SYMBOL_INSTANCE = new PutSessionAttributeSymbol();
	private static final SimpleSurroundingSymbolCombination SYMBOL_COMBINATION_INSTANCE;

	private static final int SIZE = 3;
	private static final boolean[] NECESSITY_ARRAY = {true, false, false};
	private static final Class<?>[] TYPE_ARRAY = {String.class, Object.class, String.class};


	public static final String TYPE_BYTE = "byte";
	public static final String TYPE_SHORT = "short";
	public static final String TYPE_INT = "int";
	public static final String TYPE_INTEGER = "integer";
	public static final String TYPE_LONG = "long";
	public static final String TYPE_FLOAT = "float";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_BOOLEAN = "boolean";
	public static final String TYPE_INFO_STRING = "info_string";


	/* constructors */

	private PutSessionAttributeSymbol() {
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
		SymbolConstant.registerSymbolClassForInit(PutSessionAttributeSymbol.class);
	}


	/* getters & setters */

	public static PutSessionAttributeSymbol getInstance() {
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
