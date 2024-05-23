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
 * HypotheticalCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class HypotheticalCondition extends Condition {

	/* constants */

	private static final long serialVersionUID = 8396476462218015666L;

	public static final String ANTECEDENT = "antecedent";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public HypotheticalCondition() {
		super();
	}

	public HypotheticalCondition(boolean initIdFlag, boolean initTypeFlag, Statement antecedent) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setAntecedent(antecedent);
	}


	public HypotheticalCondition(Statement antecedent) throws LIMException {
		this(true, true, antecedent);
	}

	public HypotheticalCondition(Statement antecedent, Boolean implicit) throws LIMException {
		this(true, true, antecedent);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(HypotheticalCondition.class);
		Condition.registerSubPojoClassForInit(HypotheticalCondition.class);
	}


	/* getters & setters */

	public Statement getAntecedent() {
		return (Statement) super.getInnerElementDelegate(super.getIntegratedElementDelegate(ANTECEDENT));
	}

	public boolean setAntecedent(final Statement antecedent) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(ANTECEDENT);
		boolean flag = true;
		if (be != null && antecedent != null) {
			if (!be.baseEquals(antecedent)) {
				flag &= super.replaceInnerElementDelegate(be, antecedent) 
						& super.putIntegratedElementDelegate(ANTECEDENT, new BaseElement(antecedent));
			}
		} else if (be != null) {
			flag &= super.removeIntegratedElementDelegate(ANTECEDENT) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (antecedent != null) {
			be = new BaseElement(antecedent);
			flag &= super.addInnerElementDelegate(be, antecedent) 
					& super.putIntegratedElementDelegate(ANTECEDENT, be);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setAntecedent(" 
					+ ((antecedent == null) ? null : antecedent.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setAntecedent(" 
					+ ((antecedent == null) ? null : antecedent.toBaseString()) + ")");
		}

		flag &= super.setDescriptionElement(antecedent);
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

		BaseElement antecedent = item.get(ANTECEDENT);
		if (antecedent != null && antecedent.getId() != null) {
			GenericElement ge = rootElementMap.get(antecedent.getId());
			super.addInnerElementDelegate(antecedent, ge);
		}
	}

}
