package com.codejstudio.algi.action.symbol;

import java.io.Serializable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public interface IBaseSurroundingSymbol extends Serializable {

	/* interface methods */

	boolean verifySurroundingSymbol(String surroundedContent);

	boolean verifyLeftSurroundingSymbol(String surroundedContent);

	boolean verifyRightSurroundingSymbol(String surroundedContent);

}
