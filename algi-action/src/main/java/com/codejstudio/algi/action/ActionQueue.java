package com.codejstudio.algi.action;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.ActionableElementClassConstant;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ActionQueue extends AbstractMultiActionableElement {

	/* constants */

	private static final long serialVersionUID = -7755597614332216721L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ActionQueue() {
		super();
	}

	protected ActionQueue(Description descriptionObject) throws LIMException {
		super(true, true);
		setDescriptionObject(descriptionObject);
		ElementTrace.log.info(this.toString());
	}

	public ActionQueue(ActionDefinition... actionDefinitions) throws LIMException {
		this(ActionDefinition.descriptionMerge(actionDefinitions));
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		ActionableElementClassConstant.registerActionableClassForInit(ActionQueue.class);
		GenericActionableElement.registerSubPojoClassForInit(ActionQueue.class);
	}


	/* overridden methods */

	@Override
	public Object[][] execute(final InputParameter... inputParameters) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.subSymbolElementList)) {
			return null;
		}

		Object[][] arrayOfOutputArrays = new Object[this.subSymbolElementList.size()][];
		for (int i = 0; i < this.subSymbolElementList.size(); i++) {
			ISymbolElement se = this.subSymbolElementList.get(i);
			if (se != null) {
				arrayOfOutputArrays[i] = se.executeAction(
						((this.session == null) ? (this.session = new ActionSession()) : this.session), 
						inputParameters);
			}
		}
		return arrayOfOutputArrays;
	}

}
