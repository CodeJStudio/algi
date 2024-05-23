package com.codejstudio.lim.pojo.relation;

import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * CausalityRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class CausalityRelation extends BaseRelation {

	/* constants */

	private static final long serialVersionUID = 842198428412975651L;

	public static final String CAUSE = "cause";
	public static final String EFFECT = "effect";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public CausalityRelation() {
		super();
	}

	public CausalityRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public CausalityRelation(boolean initIdFlag, AbstractRelationableInformationElement cause, 
			AbstractRelationableInformationElement effect) throws LIMException {
		super(initIdFlag, true);
		setCause(cause);
		setEffect(effect);
	}


	public CausalityRelation(AbstractRelationableInformationElement cause, 
			AbstractRelationableInformationElement effect) throws LIMException {
		this(true, cause, effect);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(CausalityRelation.class);
		BaseRelation.registerSubPojoClassForInit(CausalityRelation.class);
	}


	/* getters & setters */

	@Override
	public BaseElement getBasePrimaryElement() {
		return super.getIntegratedElementDelegate(CAUSE);
	}

	@Override
	@XmlTransient
	public AbstractRelationableInformationElement getPrimaryElement() {
		return getCause();
	}


	public AbstractRelationableInformationElement getCause() {
		return (AbstractRelationableInformationElement) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(CAUSE));
	}

	public boolean setCause(final AbstractRelationableInformationElement cause) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(CAUSE);
		boolean flag = true;
		if (be != null && cause != null) {
			if (!be.baseEquals(cause)) {
				AbstractRelationableInformationElement arie = (AbstractRelationableInformationElement) 
						super.removeAndReplaceInnerElementDelegate(be, cause);
				flag &= arie.removeRelation(this.id) 
						& cause.addRelation(this) 
						& super.putIntegratedElementDelegate(CAUSE, new BaseElement(cause));
			}
		} else if (be != null) {
			AbstractRelationableInformationElement arie = (AbstractRelationableInformationElement) 
					super.getInnerElementDelegate(be);
			flag &= arie.removeRelation(this.id) 
					& super.removeInnerElementDelegate(be.getId()) 
					& super.removeIntegratedElementDelegate(CAUSE);
		} else if (cause != null) {
			be = new BaseElement(cause);
			flag &= super.addInnerElementDelegate(be, cause) 
					& super.putIntegratedElementDelegate(CAUSE, be) 
					& cause.addRelation(this);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setCause(" 
					+ ((cause == null) ? null : cause.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setCause(" 
					+ ((cause == null) ? null : cause.toBaseString()) + ")");
		}
		return flag;
	}


	@Override
	public BaseElement getBaseSecondaryElement() {
		return super.getIntegratedElementDelegate(EFFECT);
	}

	@Override
	@XmlTransient
	public AbstractRelationableInformationElement getSecondaryElement() {
		return getEffect();
	}


	public AbstractRelationableInformationElement getEffect() {
		return (AbstractRelationableInformationElement) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(EFFECT));
	}

	public boolean setEffect(final AbstractRelationableInformationElement effect) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(EFFECT);
		boolean flag = true;
		if (be != null && effect != null) {
			if (!be.baseEquals(effect)) {
				AbstractRelationableInformationElement arie = (AbstractRelationableInformationElement) 
						super.removeAndReplaceInnerElementDelegate(be, effect);
				flag &= arie.removeRelation(this.id) 
						& effect.addRelation(this) 
						& super.putIntegratedElementDelegate(EFFECT, new BaseElement(effect));
			}
		} else if (be != null) {
			AbstractRelationableInformationElement arie = (AbstractRelationableInformationElement) 
					super.getInnerElementDelegate(be);
			flag &= arie.removeRelation(this.id) 
					& super.removeInnerElementDelegate(be.getId()) 
					& super.removeIntegratedElementDelegate(EFFECT);
		} else if (effect != null) {
			be = new BaseElement(effect);
			flag &= super.addInnerElementDelegate(be, effect) 
					& super.putIntegratedElementDelegate(EFFECT, be) 
					& effect.addRelation(this);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setEffect(" 
					+ ((effect == null) ? null : effect.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setEffect(" 
					+ ((effect == null) ? null : effect.toBaseString()) + ")");
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

		BaseElement cause = item.get(CAUSE);
		if (cause != null && cause.getId() != null) {
			GenericElement ge = rootElementMap.get(cause.getId());
			super.addInnerElementDelegate(cause, ge);
		}
		BaseElement effect = item.get(EFFECT);
		if (effect != null && effect.getId() != null) {
			GenericElement ge = rootElementMap.get(effect.getId());
			super.addInnerElementDelegate(effect, ge);
		}
	}

}
