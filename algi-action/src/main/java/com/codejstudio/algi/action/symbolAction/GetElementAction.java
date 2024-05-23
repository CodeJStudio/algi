package com.codejstudio.algi.action.symbolAction;

import java.util.List;

import com.codejstudio.algi.action.symbol.GetElementSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class GetElementAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 6656276768587921444L;


	/* constructors */

	public GetElementAction() {
		super();
		this.symbol = GetElementSymbol.getInstance();
	}

	public GetElementAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public GetElementAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		Object listOrArray;
		if (!verifyContent() 
				|| (!((listOrArray = this.multiContentArray[0]) instanceof List) 
						&& !(listOrArray instanceof Object[]))) {
			return null;
		}

		Integer index = (Integer) this.multiContentArray[1];
		List<?> l = !(listOrArray instanceof List) ? null : (List<?>) listOrArray;
		Object[] a = !(listOrArray instanceof Object[]) ? null : (Object[]) listOrArray;
		if (index < 0 || (l != null && index > l.size()) || (a != null && index > a.length)) {
			return null;
		}

		return new Object[] {(l != null) ? l.get(index) : ((a != null) ? a[index] : null)};
	}

}
