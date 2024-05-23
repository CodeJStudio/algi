package com.codejstudio.lim.pojo.condition;

import java.util.Map;
import java.util.Objects;

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
 * FactorCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class FactorCondition extends Condition {

	/* constants */

	private static final long serialVersionUID = 3617650251345468941L;

	public static final String FACTOR_NAME = "factor-name";

	public static final String TIME = "time";
	public static final String PLACE = "place";


	/* variables */

	private String factorName;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public FactorCondition() {
		super();
	}

	public FactorCondition(boolean initIdFlag, boolean initTypeFlag, InformationElement descriptionElement, 
			String factorName) throws LIMException {
		super(initIdFlag, initTypeFlag, descriptionElement);
		setFactorName(factorName);
	}


	public FactorCondition(InformationElement descriptionElement, String factorName) throws LIMException {
		this(true, true, descriptionElement, factorName);
	}

	public FactorCondition(InformationElement descriptionElement, String factorName, Boolean implicit) 
			throws LIMException {
		this(true, true, descriptionElement, factorName);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(FactorCondition.class);
		Condition.registerSubPojoClassForInit(FactorCondition.class);
	}


	/* getters & setters */

	public String getFactorName() {
		return factorName;
	}

	public boolean setFactorName(final String factorName) throws LIMException {
		if (Objects.equals(this.factorName, factorName)) {
			return true;
		}

		boolean flag = true;
		if (this.factorName != null) {
			flag &= super.removeIntegratedAttributeDelegate(FACTOR_NAME);
			this.factorName = null;
		}
		if (factorName != null) {
			this.factorName = factorName;
			flag &= super.putIntegratedAttributeDelegate(FACTOR_NAME, factorName);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setFactorName(\"" + factorName + "\")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setFactorName(\"" + factorName + "\")");
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
		load();
		return (IConvertible) this;
	}

	private void load() {
		this.factorName = super.getIntegratedAttributeDelegate(FACTOR_NAME);
	}


	@Override
	public FactorCondition cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(FactorCondition.class)) {
					return (FactorCondition) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		FactorCondition clonedElement = (FactorCondition) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public FactorCondition cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof FactorCondition) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof FactorCondition)) 
				? null : cloneToElement((FactorCondition) ce, null);
	}

	private FactorCondition cloneToElement(final FactorCondition clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.factorName = this.factorName;
		return clonedElement;
	}

}
