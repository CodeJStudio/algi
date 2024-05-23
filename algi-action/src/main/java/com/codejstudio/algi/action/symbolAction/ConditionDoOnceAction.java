package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.ActionQueue;
import com.codejstudio.algi.action.symbolElement.ConditionDoOnceSymbolElement;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ConditionDoOnceAction extends AbstractComplexSurroundingSymbolAction {

	/* constants */

	private static final long serialVersionUID = 7208358476407171519L;


	/* variables: arrays, collections, maps, groups */

	protected String[] logicValueArray;

	protected String[] executingBodyArray;


	/* constructors */

	public ConditionDoOnceAction() {
		super();
	}

	public ConditionDoOnceAction(String sourceDescription) throws LIMException {
		super();
		setSourceDescription(sourceDescription);
	}

	public ConditionDoOnceAction(Description sourceDescriptionObject) throws LIMException {
		super();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* initializers */

	@Override
	protected void initSymbolElement() throws LIMException {
		this.symbolElement = new ConditionDoOnceSymbolElement(this.sourceDescription, this);
	}


	/* overridden methods */

	@Override
	protected boolean makeupComplexContent(final Object[] sourceArray) {
		if (sourceArray == null || sourceArray.length < 4) {
			return false;
		}
		return setupParameters(sourceArray);
	}

	private boolean setupParameters(final Object[] sourceArray) {
		// {[ParametersOnce], Condition, LogicValue[], ExecutingBody[]} for CDO
		if (!(sourceArray[0] == null || sourceArray[0] instanceof String) 
				|| !(sourceArray[1] instanceof String) 
				|| !(sourceArray[2] instanceof String[]) || !(sourceArray[3] instanceof String[])) {
			return false;
		}

		this.parametersOnce = (String) sourceArray[0];
		this.condition = (String) sourceArray[1];
		this.logicValueArray = (String[]) sourceArray[2];
		this.executingBodyArray = (String[]) sourceArray[3];
		if (logicValueArray.length != this.executingBodyArray.length) {
			return false;
		}

		return true;
	}


	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!StringUtil.isEmpty(this.parametersOnce)) {
			new ActionQueue(new ActionDefinition(this.parametersOnce, false)).execute(this.session);
		}

		for (int i = 0; i < this.logicValueArray.length; i++) {
			if (getConditionFlag(this.condition, this.logicValueArray[i])) {
				return new ActionFlow(new ActionDefinition(this.executingBodyArray[i], false))
						.execute(this.session);
			}
		}
		return null;
	}

}
