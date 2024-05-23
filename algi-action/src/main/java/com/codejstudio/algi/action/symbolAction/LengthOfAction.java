package com.codejstudio.algi.action.symbolAction;

import java.util.List;

import com.codejstudio.algi.action.symbol.LengthOfSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class LengthOfAction extends AbstractSingleSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 6162570313308554694L;


	/* constructors */

	public LengthOfAction() {
		super();
		this.symbol = LengthOfSymbol.getInstance();
	}

	public LengthOfAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public LengthOfAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		Object sc;
		if (!verifyContent() 
				|| (!((sc = this.singleContent) instanceof String) 
						&& !(sc instanceof List) && !(sc instanceof Object[]))) {
			return null;
		}

		String str = !(sc instanceof String) ? null : (String) sc;
		List<?> l = !(sc instanceof List) ? null : (List<?>) sc;
		Object[] a = !(sc instanceof Object[]) ? null : (Object[]) sc;
		return new Object[] {(str != null) ? str.length() 
				: ((l != null) ? l.size() : ((a != null) ? a.length : null))};
	}

}
