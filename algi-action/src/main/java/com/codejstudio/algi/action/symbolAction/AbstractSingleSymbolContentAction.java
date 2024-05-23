package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.ISingleContentSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractSingleSymbolContentAction extends AbstractSimpleSurroundingSymbolAction {

	/* constants */

	private static final long serialVersionUID = 3106598063499003247L;


	/* variables */

	protected Object singleContent;


	/* getters & setters */

	public Object getSingleContent() {
		return singleContent;
	}


	/* overridden methods */

	@Override
	protected boolean makeupContent() throws LIMException {
		if (StringUtil.isEmpty(this.sourceDescription) || !(this.symbol instanceof ISingleContentSymbol)) {
			return false;
		}

		String strippedContent = this.symbolFlag 
				? this.symbol.strip(this.sourceDescription) : this.sourceDescription;
		this.singleContent = castContentByType(strippedContent, 
				((ISingleContentSymbol) this.symbol).getContentType());
		return true;
	}


	@Override
	protected boolean verifyContent() throws LIMException {
		return ((ISingleContentSymbol) this.symbol).getContentType().isInstance(this.singleContent);
	}

}
