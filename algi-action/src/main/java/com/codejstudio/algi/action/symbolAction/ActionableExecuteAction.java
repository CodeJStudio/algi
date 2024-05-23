package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.ActionableExecuteSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.i.IActionable;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ActionableExecuteAction extends AbstractSingleSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 8811133523534695291L;


	/* constructors */

	public ActionableExecuteAction() {
		super();
		this.symbol = ActionableExecuteSymbol.getInstance();
	}

	public ActionableExecuteAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public ActionableExecuteAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String actionableId = (String) this.singleContent;
		GenericActionableElement gae = Informationiverse.getActionableElement(actionableId);
		return !(gae instanceof IActionable) ? null : ((IActionable) gae).execute(this.session);
	}

}
