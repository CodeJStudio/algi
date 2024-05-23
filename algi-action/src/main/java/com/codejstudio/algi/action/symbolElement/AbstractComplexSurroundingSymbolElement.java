package com.codejstudio.algi.action.symbolElement;

import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractComplexSurroundingSymbolElement extends AbstractSurroundingSymbolElement {

	/* constants */

	private static final long serialVersionUID = 2681593550092970097L;


	/* initializers */

	protected void init(final String source, final Class<? extends SymbolAction> actionClazz) 
			throws LIMException {
		if (StringUtil.isEmpty(source)) {
			throw new ALGIException(new IllegalArgumentException());
		}
		this.source = source;
		this.actionClazz = actionClazz;
		initSymbolContent(source);
	}

}
