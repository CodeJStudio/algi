package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolElement.InformationElementSymbolElement;
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
public class InformationElementSymbol extends AbstractSingleContentSymbol {

	/* constants */

	private static final long serialVersionUID = -4204730499984197877L;

	public static final String SYMBOL = "ie";//Information Element

	public static final Class<? extends ISymbolElement> SYMBOL_ELEMENT_CLAZZ 
			= InformationElementSymbolElement.class;
	private static final InformationElementSymbol SYMBOL_INSTANCE = new InformationElementSymbol();
	private static final SimpleSurroundingSymbolCombination SYMBOL_COMBINATION_INSTANCE;

	private static final Class<?> TYPE = String.class;


	/* constructors */

	private InformationElementSymbol() {
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
		SymbolConstant.registerSymbolClassForInit(InformationElementSymbol.class);
	}


	/* getters & setters */

	public static InformationElementSymbol getInstance() {
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
