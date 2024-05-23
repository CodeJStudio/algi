package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbolElement.DoRepeatedlyConditionSymbolElement;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DoRepeatedlyConditionAction extends AbstractDoRepeatedlyAction {

	/* constants */

	private static final long serialVersionUID = -8691934214842990297L;


	/* constructors */

	public DoRepeatedlyConditionAction() {
		super();
	}

	public DoRepeatedlyConditionAction(String sourceDescription) throws LIMException {
		super();
		setSourceDescription(sourceDescription);
	}

	public DoRepeatedlyConditionAction(Description sourceDescriptionObject) throws LIMException {
		super();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* initializers */

	@Override
	protected void initSymbolElement() throws LIMException {
		this.symbolElement = new DoRepeatedlyConditionSymbolElement(this.sourceDescription, this);
	}


	/* overridden methods */

	@Override
	protected boolean setupParameters(final String[] sourceArray) {
		// {[ParametersOnce], LogicValue, ExecutingBody, Condition, [ParametersRepeated]} for DRC
		this.parametersOnce = sourceArray[0];
		this.logicValue = sourceArray[1];
		this.executingBody = sourceArray[2];
		this.condition = sourceArray[3];
		this.parametersRepeated = sourceArray[4];
		return true;
	}

	@Override
	protected boolean firstRoundExecutingWithoutConditionFlag() {
		return true;
	}

}
