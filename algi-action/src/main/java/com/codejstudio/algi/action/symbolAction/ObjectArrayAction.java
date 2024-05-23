package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.ObjectArraySymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ObjectArrayAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = -1550278010232149747L;


	/* constructors */

	public ObjectArrayAction() {
		super();
		this.symbol = ObjectArraySymbol.getInstance();
	}

	public ObjectArrayAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public ObjectArrayAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}
		return new Object[] {this.multiContentArray};
	}

}
