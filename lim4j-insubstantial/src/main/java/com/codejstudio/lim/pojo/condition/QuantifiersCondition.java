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
import com.codejstudio.lim.pojo.util.PojoElementEnumConstant;

/**
 * QuantifiersCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class QuantifiersCondition extends ScopeCondition {

	/* enumeration */

	public enum QuantifiersType {
		UNIVERSAL,
		PARTICULAR,
		SINGULAR,
		;
	}


	/* constants */

	private static final long serialVersionUID = 639441727519338927L;

	public static final String QUANTIFIERS_TYPE = "quantifiers-type";


	/* variables */

	private QuantifiersType quantifiersType;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public QuantifiersCondition() {
		super();
	}

	public QuantifiersCondition(boolean initIdFlag, boolean initTypeFlag, 
			InformationElement descriptionElement, QuantifiersType quantifiersType) throws LIMException {
		super(initIdFlag, initTypeFlag, descriptionElement);
		setQuantifiersType(quantifiersType);
	}


	public QuantifiersCondition(InformationElement descriptionElement, QuantifiersType quantifiersType) 
			throws LIMException {
		this(true, true, descriptionElement, quantifiersType);
	}

	public QuantifiersCondition(InformationElement descriptionElement, QuantifiersType quantifiersType, 
			Boolean implicit) throws LIMException {
		this(true, true, descriptionElement, quantifiersType);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(QuantifiersCondition.class);
		PojoElementEnumConstant.registerEnumClassForInit(QuantifiersType.class);
		Condition.registerSubPojoClassForInit(QuantifiersCondition.class);
	}


	/* getters & setters */

	public QuantifiersType getQuantifiersType() {
		return quantifiersType;
	}

	public boolean setQuantifiersType(final QuantifiersType quantifiersType) throws LIMException {
		if (Objects.equals(this.quantifiersType, quantifiersType)) {
			return true;
		}

		boolean flag = true;
		if (this.quantifiersType != null) {
			flag &= super.removeIntegratedAttributeDelegate(QUANTIFIERS_TYPE);
			this.quantifiersType = null;
		}
		if (quantifiersType != null) {
			this.quantifiersType = quantifiersType;
			flag &= super.putIntegratedAttributeDelegate(QUANTIFIERS_TYPE, quantifiersType.name());
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setQuantifiersType(" + quantifiersType + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setQuantifiersType(" + quantifiersType + ")");
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
		String typeName = super.getIntegratedAttributeDelegate(QUANTIFIERS_TYPE);
		this.quantifiersType = QuantifiersType.valueOf(typeName);
	}


	@Override
	public QuantifiersCondition cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(QuantifiersCondition.class)) {
					return (QuantifiersCondition) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		QuantifiersCondition clonedElement = (QuantifiersCondition) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public QuantifiersCondition cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof QuantifiersCondition) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof QuantifiersCondition)) 
				? null : cloneToElement((QuantifiersCondition) ce, null);
	}

	private QuantifiersCondition cloneToElement(final QuantifiersCondition clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.quantifiersType = this.quantifiersType;
		return clonedElement;
	}

}
