package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ParameterRepeatedSymbol extends AbstractComplexSurroundingSymbol {

	/* constants */

	private static final long serialVersionUID = -3142891188958870421L;

	public static final String SYMBOL = "pr";//Parameter Repeated

	private static final ParameterRepeatedSymbol SYMBOL_INSTANCE = new ParameterRepeatedSymbol();


	/* constructors */

	private ParameterRepeatedSymbol() {
		super();
	}


	/* getters & setters */

	public static ParameterRepeatedSymbol getInstance() {
		return SYMBOL_INSTANCE;
	}

	@Override
	public String getInnerSymbol() {
		return SYMBOL;
	}

}
