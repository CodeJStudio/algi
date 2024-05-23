package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.VerifyConditionSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class VerifyConditionAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 1529958328198615214L;


	/* constructors */

	public VerifyConditionAction() {
		super();
		this.symbol = VerifyConditionSymbol.getInstance();
	}

	public VerifyConditionAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public VerifyConditionAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		Condition cd = null;
		if (this.multiContentArray[0] instanceof Condition) {
			cd = (Condition) this.multiContentArray[0];
		} else if (this.multiContentArray[0] instanceof String) {
			String conditionId = (String) this.multiContentArray[0];
			GenericElement ge = Informationiverse.getElement(conditionId);
			cd = !(ge instanceof Condition) ? null : (Condition) ge;
		}

		InformationElement ie = null;
		if (this.multiContentArray[1] instanceof InformationElement) {
			ie = (InformationElement) this.multiContentArray[1];
		} else if (this.multiContentArray[1] instanceof String) {
			String dsc = (String) this.multiContentArray[1];
			ie = Informationiverse.seekInformationByDescription(dsc);
		}

		LogicValue flag = (this.multiContentArray[2] == null) ? null : (LogicValue) this.multiContentArray[2];
		Boolean noneConditionFlag = LogicValue.getBooleanValue(flag);

		return (noneConditionFlag != null) 
				? new Object[] {IConditionable.verifyCondition(ie, cd, noneConditionFlag)} 
				: (!(cd instanceof ConditionGroup) 
						? null : new Object[] {IConditionable.verifyCondition(ie, (ConditionGroup) cd)});
	}

}
