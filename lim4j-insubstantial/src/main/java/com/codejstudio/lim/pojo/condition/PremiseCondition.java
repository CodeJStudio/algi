package com.codejstudio.lim.pojo.condition;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * PremiseCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PremiseCondition extends Condition {

	/* constants */

	private static final long serialVersionUID = -4523049574775238913L;

	public static final String PREMISE = "premise";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public PremiseCondition() {
		super();
	}

	public PremiseCondition(boolean initIdFlag, boolean initTypeFlag, Statement premise) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setPremise(premise);
	}


	public PremiseCondition(Statement premise) throws LIMException {
		this(true, true, premise);
	}

	public PremiseCondition(Statement premise, Boolean implicit) throws LIMException {
		this(true, true, premise);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(PremiseCondition.class);
		Condition.registerSubPojoClassForInit(PremiseCondition.class);
	}


	/* getters & setters */

	public Statement getPremise() {
		return (Statement) super.getInnerElementDelegate(super.getIntegratedElementDelegate(PREMISE));
	}

	public boolean setPremise(final Statement premise) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(PREMISE);
		boolean flag = true;
		if (be != null && premise != null) {
			if (!be.baseEquals(premise)) {
				flag &= super.replaceInnerElementDelegate(be, premise) 
						& super.putIntegratedElementDelegate(PREMISE, new BaseElement(premise));
			}
		} else if (be != null) {
			flag &= super.removeIntegratedElementDelegate(PREMISE) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (premise != null) {
			be = new BaseElement(premise);
			flag &= super.addInnerElementDelegate(be, premise) 
					& super.putIntegratedElementDelegate(PREMISE, be);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setPremise(" 
					+ ((premise == null) ? null : premise.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setPremise(" 
					+ ((premise == null) ? null : premise.toBaseString()) + ")");
		}

		flag &= super.setDescriptionElement(premise);
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

		BaseElement premise = item.get(PREMISE);
		if (premise != null && premise.getId() != null) {
			GenericElement ge = rootElementMap.get(premise.getId());
			super.addInnerElementDelegate(premise, ge);
		}
	}

}
