package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.util.DynamicableUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractSimpleSurroundingSymbolAction extends AbstractSurroundingSymbolAction {

	/* constants */

	private static final long serialVersionUID = -5625108484692850534L;


	/* class methods */

	protected abstract boolean verifyContent() throws LIMException;

	protected Object castContentByType(final String content, final Class<?> contentType) 
			throws LIMException {
		if (content == null) {
			return null;
		}

		if (Integer.class.equals(contentType)) {
			return StringUtil.integerValue(content);
		} else if (Number.class.equals(contentType)) {
			return StringUtil.numberValue(content);
		} else if (LogicValue.class.equals(contentType)) {
			return LogicValue.getLogicValue(content);
		} else if (Object.class.equals(contentType)) {
			if (DynamicableUtil.INPUT_DYNAMIC_SYMBOL.verifySingleSymbol(content)) {
				return this.session.getDynamicElement(
						DynamicableUtil.INPUT_DYNAMIC_SYMBOL.stripDynamicSymbol(content));
			} else {
				return content;
			}
		} else {
			return content;
		}
	}

}
