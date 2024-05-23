package com.codejstudio.algi.action.symbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DoRepeatedlySymbol extends AbstractComplexSurroundingSymbol {

	/* constants */

	private static final long serialVersionUID = -3912118966434386191L;

	public static final String SYMBOL = "dr";//Do Repeatedly

	private static final DoRepeatedlySymbol SYMBOL_INSTANCE = new DoRepeatedlySymbol();


	/* constructors */

	private DoRepeatedlySymbol() {
		super();
	}


	/* getters & setters */

	public static DoRepeatedlySymbol getInstance() {
		return SYMBOL_INSTANCE;
	}

	@Override
	public String getInnerSymbol() {
		return SYMBOL;
	}

}
