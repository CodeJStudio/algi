package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.SubstringSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SubstringAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 6404242226028158433L;


	/* constructors */

	public SubstringAction() {
		super();
		this.symbol = SubstringSymbol.getInstance();
	}

	public SubstringAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public SubstringAction(Description sourceDescriptionObject) throws LIMException {
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
		Integer begin = (Integer) this.multiContentArray[1];
		Integer end = (this.multiContentArray[2] == null) ? null : (Integer) this.multiContentArray[2];
		if (begin >= target.length() || begin < 0 
				|| (end != null && (end > target.length() || end <= begin))) {
			return null;
		}

		String ss = (end == null) ? target.substring(begin) : target.substring(begin, end);
		return new Object[] {new Description(ss)};
	}

}
