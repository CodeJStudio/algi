package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ParameterOnceSymbol extends AbstractComplexSurroundingSymbol {

	/* constants */

	private static final long serialVersionUID = -1621555220743762058L;

	public static final String SYMBOL = "po";//Parameter Once

	private static final ParameterOnceSymbol SYMBOL_INSTANCE = new ParameterOnceSymbol();


	/* constructors */

	private ParameterOnceSymbol() {
		super();
	}


	/* getters & setters */

	public static ParameterOnceSymbol getInstance() {
		return SYMBOL_INSTANCE;
	}

	@Override
	public String getInnerSymbol() {
		return SYMBOL;
	}

}
