package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DoOnceSymbol extends AbstractComplexSurroundingSymbol {

	/* constants */

	private static final long serialVersionUID = -6667618389865037590L;

	public static final String SYMBOL = "do";//Do Once

	private static final DoOnceSymbol SYMBOL_INSTANCE = new DoOnceSymbol();


	/* constructors */

	private DoOnceSymbol() {
		super();
	}


	/* getters & setters */

	public static DoOnceSymbol getInstance() {
		return SYMBOL_INSTANCE;
	}

	@Override
	public String getInnerSymbol() {
		return SYMBOL;
	}

}
