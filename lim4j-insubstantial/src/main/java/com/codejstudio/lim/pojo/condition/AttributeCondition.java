package com.codejstudio.lim.pojo.condition;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AttributeCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class AttributeCondition extends Condition {

	/* constants */

	private static final long serialVersionUID = -7083578368155230783L;

	public static final String ATTRIBUTE = "attribute";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AttributeCondition() {
		super();
	}

	public AttributeCondition(boolean initIdFlag, boolean initTypeFlag, GenericElement attributeElement) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setAttributeElement(attributeElement);
	}


	public AttributeCondition(GenericElement attributeElement) throws LIMException {
		this(true, true, attributeElement);
	}

	public AttributeCondition(GenericElement attributeElement, Boolean implicit) throws LIMException {
		this(true, true, attributeElement);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(AttributeCondition.class);
		Condition.registerSubPojoClassForInit(AttributeCondition.class);
	}


	/* getters & setters */

	public GenericElement getAttributeElement() {
		return (GenericElement) super.getInnerElementDelegate(super.getIntegratedElementDelegate(ATTRIBUTE));
	}

	public boolean setAttributeElement(final GenericElement attributeElement) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(ATTRIBUTE);
		boolean flag = true;
		if (be != null && attributeElement != null) {
			if (!be.baseEquals(attributeElement)) {
				flag &= super.replaceInnerElementDelegate(be, attributeElement) 
						& super.putIntegratedElementDelegate(ATTRIBUTE, new BaseElement(attributeElement));
			}
		} else if (be != null) {
			flag &= super.removeIntegratedElementDelegate(ATTRIBUTE) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (attributeElement != null) {
			be = new BaseElement(attributeElement);
			flag &= super.addInnerElementDelegate(be, attributeElement) 
					& super.putIntegratedElementDelegate(ATTRIBUTE, be);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setAttributeElement(" 
					+ ((attributeElement == null) ? null : attributeElement.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setAttributeElement(" 
					+ ((attributeElement == null) ? null : attributeElement.toBaseString()) + ")");
		}

		if (attributeElement instanceof InformationElement) {
			flag &= super.setDescriptionElement((InformationElement) attributeElement);
		}
		return flag;
	}


	/* overridden methods */

	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		Map<String, BaseElement> item;
		if (CollectionUtil.checkNullOrEmpty(rootElementMap) 
				|| CollectionUtil.checkNullOrEmpty(item = super.getIntegratedElementMap())) {
			return;
		}

		BaseElement attributeElement = item.get(ATTRIBUTE);
		if (attributeElement != null && attributeElement.getId() != null) {
			GenericElement ge = rootElementMap.get(attributeElement.getId());
			super.addInnerElementDelegate(attributeElement, ge);
		}
	}

}
