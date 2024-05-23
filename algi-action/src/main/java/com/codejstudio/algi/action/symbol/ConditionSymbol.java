package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ConditionSymbol extends AbstractComplexSurroundingSymbol {

	/* constants */

	private static final long serialVersionUID = 1144469194984004079L;

	public static final String SYMBOL = "cd";//Condition

	private static final ConditionSymbol SYMBOL_INSTANCE = new ConditionSymbol();


	/* constructors */

	private ConditionSymbol() {
		super();
	}


	/* getters & setters */

	public static ConditionSymbol getInstance() {
		return SYMBOL_INSTANCE;
	}

	@Override
	public String getInnerSymbol() {
		return SYMBOL;
	}

}
