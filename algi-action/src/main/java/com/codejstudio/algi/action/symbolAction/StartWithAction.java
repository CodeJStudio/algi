package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.StartWithSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class StartWithAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = -7990504022835919406L;


	/* constructors */

	public StartWithAction() {
		super();
		this.symbol = StartWithSymbol.getInstance();
	}

	public StartWithAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public StartWithAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String target = (String) this.multiContentArray[0];
		String prefix = (String) this.multiContentArray[1];

		return new Object[] {target.startsWith(prefix)};
	}

}
