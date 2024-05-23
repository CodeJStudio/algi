package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbolElement.ConditionDoRepeatedlySymbolElement;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ConditionDoRepeatedlyAction extends AbstractDoRepeatedlyAction {

	/* constants */

	private static final long serialVersionUID = 2794478830306217202L;


	/* constructors */

	public ConditionDoRepeatedlyAction() {
		super();
	}

	public ConditionDoRepeatedlyAction(String sourceDescription) throws LIMException {
		super();
		setSourceDescription(sourceDescription);
	}

	public ConditionDoRepeatedlyAction(Description sourceDescriptionObject) throws LIMException {
		super();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* initializers */

	@Override
	protected void initSymbolElement() throws LIMException {
		this.symbolElement = new ConditionDoRepeatedlySymbolElement(this.sourceDescription, this);
	}


	/* overridden methods */

	@Override
	protected boolean setupParameters(final String[] sourceArray) {
		// {[ParametersOnce], Condition, LogicValue, ExecutingBody, [ParametersRepeated]} for CDR
		this.parametersOnce = sourceArray[0];
		this.condition = sourceArray[1];
		this.logicValue = sourceArray[2];
		this.executingBody = sourceArray[3];
		this.parametersRepeated = sourceArray[4];
		return true;
	}

	@Override
	protected boolean firstRoundExecutingWithoutConditionFlag() {
		return false;
	}

}
