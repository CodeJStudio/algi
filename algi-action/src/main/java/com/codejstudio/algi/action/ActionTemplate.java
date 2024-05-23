package com.codejstudio.algi.action;

import java.util.List;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
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
public class ActionTemplate extends AbstractActionableElement {

	/* constants */

	private static final long serialVersionUID = -4112555931838609200L;


	/* variables */

	protected ISymbolElement symbolElement;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ActionTemplate() {
		super();
	}

	protected ActionTemplate(Description descriptionObject) throws LIMException {
		super(true, true);
		setDescriptionObject(descriptionObject);
		ElementTrace.log.info(this.toString());
	}

	public ActionTemplate(ActionDefinition actionDefinition) throws LIMException {
		this((actionDefinition == null) ? null : actionDefinition.getDescriptionObject());
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		ActionableElementClassConstant.registerActionableClassForInit(ActionTemplate.class);
		GenericActionableElement.registerSubPojoClassForInit(ActionTemplate.class);
	}


	@Override
	protected void postInit(final List<ISymbolElement> subSymbolElementList) {
		if (CollectionUtil.checkNullOrEmpty(subSymbolElementList)) {
			return;
		}
		for (int i = 0; i < subSymbolElementList.size(); i++) {
			ISymbolElement se = subSymbolElementList.get(i);
			if (se == null) {
				continue;
			}
			this.symbolElement = se;
			break;
		}
	}


	/* overridden methods */

	@Override
	public Object[] execute(final InputParameter... inputParameters) throws LIMException {
		if (this.symbolElement == null) {
			return null;
		}

		return this.symbolElement.executeAction(
				((this.session == null) ? (this.session = new ActionSession()) : this.session), 
				inputParameters);
	}


	@Override
	public ActionTemplate cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(ActionTemplate.class)) {
					return (ActionTemplate) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		ActionTemplate clonedElement = (ActionTemplate) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public ActionTemplate cloneToElement(final AbstractActionableElement clonedElement) throws LIMException {
		AbstractActionableElement ce;
		return (!(clonedElement instanceof ActionTemplate) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof ActionTemplate)) 
				? null : cloneToElement((ActionTemplate) ce, null);
	}

	private ActionTemplate cloneToElement(final ActionTemplate clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.symbolElement = this.symbolElement;

		return clonedElement;
	}

}
