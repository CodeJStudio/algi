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
 * NegativesCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class NegativesCondition extends ScopeCondition {

	/* enumeration */

	public enum NegativesType {
		AFFIRMATIVE,
		NEGATIVE,
		;
	}


	/* constants */

	private static final long serialVersionUID = -2801110936356511642L;

	public static final String NEGATIVES_TYPE = "negatives-type";


	/* variables */

	private NegativesType negativesType;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public NegativesCondition() {
		super();
	}

	public NegativesCondition(boolean initIdFlag, boolean initTypeFlag, 
			InformationElement descriptionElement, NegativesType negativesType) throws LIMException {
		super(initIdFlag, initTypeFlag, descriptionElement);
		setNegativesType(negativesType);
	}


	public NegativesCondition(InformationElement descriptionElement, NegativesType negativesType) 
			throws LIMException {
		this(true, true, descriptionElement, negativesType);
	}

	public NegativesCondition(InformationElement descriptionElement, NegativesType negativesType, 
			Boolean implicit) throws LIMException {
		this(true, true, descriptionElement, negativesType);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(NegativesCondition.class);
		PojoElementEnumConstant.registerEnumClassForInit(NegativesType.class);
		Condition.registerSubPojoClassForInit(NegativesCondition.class);
	}


	/* getters & setters */

	public NegativesType getNegativesType() {
		return negativesType;
	}

	public boolean setNegativesType(final NegativesType negativesType) throws LIMException {
		if (Objects.equals(this.negativesType, negativesType)) {
			return true;
		}

		boolean flag = true;
		if (this.negativesType != null) {
			flag &= super.removeIntegratedAttributeDelegate(NEGATIVES_TYPE);
			this.negativesType = null;
		}
		if (negativesType != null) {
			this.negativesType = negativesType;
			flag &= super.putIntegratedAttributeDelegate(NEGATIVES_TYPE, negativesType.name());
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setNegativesType(" + negativesType + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setNegativesType(" + negativesType + ")");
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
		String typeName = super.getIntegratedAttributeDelegate(NEGATIVES_TYPE);
		this.negativesType = NegativesType.valueOf(typeName);
	}


	@Override
	public NegativesCondition cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(NegativesCondition.class)) {
					return (NegativesCondition) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		NegativesCondition clonedElement = (NegativesCondition) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public NegativesCondition cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof NegativesCondition) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof NegativesCondition)) 
				? null : cloneToElement((NegativesCondition) ce, null);
	}

	private NegativesCondition cloneToElement(final NegativesCondition clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.negativesType = this.negativesType;
		return clonedElement;
	}

}
