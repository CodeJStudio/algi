package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.GetSessionAttributeSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.util.AbstractDynamicSymbol;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class GetSessionAttributeAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 9152840746205984927L;


	/* constructors */

	public GetSessionAttributeAction() {
		super();
		this.symbol = GetSessionAttributeSymbol.getInstance();
	}

	public GetSessionAttributeAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public GetSessionAttributeAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String name = ((name = AbstractDynamicSymbol.stripSingleDynamicSymbol(
				(String) this.multiContentArray[0])) != null) ? name : (String) this.multiContentArray[0];
		String type = (this.multiContentArray[1] == null) ? null : (String) this.multiContentArray[1];

		Object attribute = this.session.getAttribute(name);
		if (!StringUtil.isEmpty(type) 
				&& type.toLowerCase().equals(GetSessionAttributeSymbol.TYPE_INFO_STRING)) {
			attribute = (attribute instanceof InformationElement) 
					? ((InformationElement)attribute).getDescription() : attribute;
		}

		return (this.session == null) ? null : new Object[] {attribute};
	}


	@Override
	protected boolean dynamicInformationFlag() {
		return false;
	}

}
