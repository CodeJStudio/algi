package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.InformationElementSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class InformationElementAction extends AbstractSingleSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 6868204045673241816L;


	/* constructors */

	public InformationElementAction() {
		super();
		this.symbol = InformationElementSymbol.getInstance();
	}

	public InformationElementAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public InformationElementAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String elementId = (String) this.singleContent;
		GenericElement ge = Informationiverse.getElement(elementId);
		return !(ge instanceof InformationElement) ? null : new Object[] {(InformationElement) ge};
	}

}
