package com.codejstudio.algi.action.dynamicAction;

import com.codejstudio.algi.action.Action;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.util.AbstractDynamicSymbol;
import com.codejstudio.lim.pojo.util.PartDynamicalizedStaticInformation;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class VerifySubElementAction extends AbstractDynamicAction {

	/* variables */

	protected PartDynamicalizedStaticInformation partDynamicalizedStaticInformation;


	/* constructors */

	public VerifySubElementAction(PartDynamicalizedStaticInformation partDynamicalizedStaticInformation) 
			throws LIMException {
		if (partDynamicalizedStaticInformation == null) {
			throw new LIMException(new IllegalArgumentException());
		}
		this.partDynamicalizedStaticInformation = partDynamicalizedStaticInformation;
	}


	/* getters & setters */

	public PartDynamicalizedStaticInformation getPartDynamicalizedStaticInformation() {
		return partDynamicalizedStaticInformation;
	}


	/* overridden methods */

	@Override
	protected Object[] doExecute() throws LIMException {
		int indexOfSubElement = this.partDynamicalizedStaticInformation.getPartDynamicalizedDescription()
				.indexOf(AbstractDynamicSymbol.DYNAMIC_PLACEHOLDER);
		int lengthOfRest = this.partDynamicalizedStaticInformation.getPartDynamicalizedDescription()
				.length() - indexOfSubElement - AbstractDynamicSymbol.DYNAMIC_PLACEHOLDER.length();

		InformationElement staticElement = this.partDynamicalizedStaticInformation.getStaticElement();
		verifySubElements(staticElement, indexOfSubElement);

		InformationElement subElement = staticElement.findSubElementByIndex(indexOfSubElement, lengthOfRest);
		if (subElement != null && verifyCondition(subElement)) {
			return new Object[] {subElement};
		}

		return null;
	}

	private void verifySubElements(final InformationElement staticElement, final int indexOfSubElement) 
			throws LIMException {
		if (staticElement.subElementPositionSize() == 0) {
			Action salsea = new SeekAndLoadSubElementsAction(staticElement);
			this.session.putInputParameter(SeekAndLoadSubElementsAction.PARAMETER_INDEXES, indexOfSubElement);
			salsea.setSession(this.session);
			salsea.execute();
		}
	}

	private boolean verifyCondition(final InformationElement subElement) throws LIMException {
		ConditionGroup[] dfcda = this.partDynamicalizedStaticInformation.getDynamicElement()
				.getDynamicInformation().getDynamicFragmentConditionArray();
		if (CollectionUtil.checkNullOrEmpty(dfcda)) {
			return true;
		}

		DynamicParameterType[] dpta 
				= this.partDynamicalizedStaticInformation.getPartDynamicParameterTypeArray();
		for (int i = 0; (dpta != null && i < dpta.length); i++) {
			if (dpta[i].equals(DynamicParameterType.OUTPUT) 
					&& !IConditionable.verifyCondition(subElement, dfcda[i])) {
				return false;
			}
		}
		return true;
	}

}
