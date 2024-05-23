package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.dynamicAction.DynamicHypotheticalPropositionAction;
import com.codejstudio.algi.action.symbol.DynamicHypotheticalPropositionSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DynamicHypotheticalPropositionSymbolAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 7914229926917452093L;


	/* constructors */

	public DynamicHypotheticalPropositionSymbolAction() {
		super();
		this.symbol = DynamicHypotheticalPropositionSymbol.getInstance();
	}

	public DynamicHypotheticalPropositionSymbolAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public DynamicHypotheticalPropositionSymbolAction(Description sourceDescriptionObject) 
			throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String elementId = (String) this.multiContentArray[0];
		String parameterName = (this.multiContentArray[1] == null) 
				? null : (String) this.multiContentArray[1];

		GenericElement ge = Informationiverse.getElement(elementId);
		HypotheticalProposition dhpp = !(ge instanceof HypotheticalProposition) 
				? null : (HypotheticalProposition) ge;
		if (dhpp == null) {
			return null;
		}

		DynamicHypotheticalPropositionAction dhppa 
				= new DynamicHypotheticalPropositionAction(dhpp, parameterName);
		return dhppa.execute(this.session);
	}


	@Override
	protected boolean dynamicInformationFlag() {
		return false;
	}

}
