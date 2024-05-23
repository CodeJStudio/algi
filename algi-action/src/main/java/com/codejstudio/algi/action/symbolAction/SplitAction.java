package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.SplitSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SplitAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = -6493403387493275071L;


	/* constructors */

	public SplitAction() {
		super();
		this.symbol = SplitSymbol.getInstance();
	}

	public SplitAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public SplitAction(Description sourceDescriptionObject) throws LIMException {
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
		String regex = (this.multiContentArray[1] == null) ? null : (String) this.multiContentArray[1];

		return new Object[] {target.split((regex == null) ? "" : regex)};
	}

}
