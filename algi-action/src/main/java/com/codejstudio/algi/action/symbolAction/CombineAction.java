package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.CombineSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CombineAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = -4965239745171082146L;


	/* constructors */

	public CombineAction() {
		super();
		this.symbol = CombineSymbol.getInstance();
	}

	public CombineAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public CombineAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (Object obj : this.multiContentArray) {
			sb.append(obj);
		}

		return StringUtil.isEmpty(sb) ? null : new Object[] {new Description(sb.toString())};
	}

}
