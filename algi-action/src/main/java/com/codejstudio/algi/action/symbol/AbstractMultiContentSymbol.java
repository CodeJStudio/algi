package com.codejstudio.algi.action.symbol;

import com.codejstudio.lim.common.util.StringUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractMultiContentSymbol extends AbstractSurroundingSymbol 
		implements IMultiContentSymbol {

	/* constants */

	private static final long serialVersionUID = 8555326463770713515L;


	/* overridden methods */

	@Override
	public boolean isFinite() {
		return getContentSize() > 0;
	}

	@Override
	public String[] splitStrippedContent(final String strippedContent) {
		return StringUtil.isEmpty(strippedContent) ? null : strippedContent.split(SEPARATOR);
	}

}
