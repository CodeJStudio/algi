package com.codejstudio.algi.action.symbol;

import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public interface IMultiContentSymbol {

	/* constants */

	static final String SEPARATOR = "" + WordSeparator.COMMA.getCharacter();


	/* interface methods */

	boolean isFinite();

	int getContentSize();

	boolean[] getContentNecessityArray();

	Class<?>[] getContentTypeArray();


	String[] splitStrippedContent(String strippedContent);

}
