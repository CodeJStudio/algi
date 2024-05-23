package com.codejstudio.lim.pojo.statement;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Definition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Definition extends JudgedStatement {

	/* constants */

	private static final long serialVersionUID = 3886392364048849854L;

	public static final String DEFINIENDUM = "definiendum";
	public static final String DEFINIENS = "definiens";
	public static final String CONNECTIVE_OF_DEFINITION = "connective-of-definition";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Definition() {
		super();
	}

	public Definition(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Definition(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public Definition(String description) throws LIMException {
		super(true, true, description);
	}

	public Definition(Concept definiendum, Concept definiens, Concept connectiveOfDefinition) 
			throws LIMException {
		super(true, true);
		setDefiniendumAndDefiniens(definiendum, definiens, connectiveOfDefinition);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Definition.class);
		JudgedStatement.registerSubPojoClassForInit(Definition.class);
	}


	/* getters & setters */

	public Concept getDefiniendum() {
		return (Concept) super.getInnerElementDelegate(super.getIntegratedElementDelegate(DEFINIENDUM));
	}

	public boolean setDefiniendum(final Concept definiendum) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(DEFINIENDUM);
		boolean flag = true;
		if (be != null && definiendum != null) {
			if (!be.baseEquals(definiendum)) {
				flag &= super.replaceInnerElementDelegate(be, definiendum) 
						& super.putIntegratedElementDelegate(DEFINIENDUM, new BaseElement(definiendum));
			}
		} else if (be != null) {
			flag &= super.removeIntegratedElementDelegate(DEFINIENDUM) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (definiendum != null) {
			be = new BaseElement(definiendum);
			flag &= super.addInnerElementDelegate(be, definiendum) 
					& super.putIntegratedElementDelegate(DEFINIENDUM, be);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setDefiniendum(" 
					+ ((definiendum == null) ? null : definiendum.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setDefiniendum(" 
					+ ((definiendum == null) ? null : definiendum.toBaseString()) + ")");
		}
		return flag;
	}


	public Concept getDefiniens() {
		return (Concept) super.getInnerElementDelegate(super.getIntegratedElementDelegate(DEFINIENS));
	}

	public boolean setDefiniens(final Concept definiens) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(DEFINIENS);
		boolean flag = true;
		if (be != null && definiens != null) {
			if (!be.baseEquals(definiens)) {
				flag &= super.replaceInnerElementDelegate(be, definiens) 
						& super.putIntegratedElementDelegate(DEFINIENS, new BaseElement(definiens));
			}
		} else if (be != null) {
			flag &= super.removeIntegratedElementDelegate(DEFINIENS) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (definiens != null) {
			be = new BaseElement(definiens);
			flag &= super.addInnerElementDelegate(be, definiens) 
					& super.putIntegratedElementDelegate(DEFINIENS, be);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setDefiniens(" 
					+ ((definiens == null) ? null : definiens.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setDefiniens(" 
					+ ((definiens == null) ? null : definiens.toBaseString()) + ")");
		}
		return flag;
	}


	public Concept getConnectiveOfDefinition() {
		return (Concept) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION));
	}

	public boolean setConnectiveOfDefinition(final Concept connectiveOfDefinition) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION);
		boolean flag = true;
		if (be != null && connectiveOfDefinition != null) {
			if (!be.baseEquals(connectiveOfDefinition)) {
				flag &= super.replaceInnerElementDelegate(be, connectiveOfDefinition) 
						& super.putIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION, 
								new BaseElement(connectiveOfDefinition));
			}
		} else if (be != null) {
			flag &= super.removeIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (connectiveOfDefinition != null) {
			be = new BaseElement(connectiveOfDefinition);
			flag &= super.addInnerElementDelegate(be, connectiveOfDefinition) 
					& super.putIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION, be);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setConnectiveOfDefinition(" 
					+ ((connectiveOfDefinition == null) ? null : connectiveOfDefinition.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setConnectiveOfDefinition(" 
					+ ((connectiveOfDefinition == null) ? null : connectiveOfDefinition.toBaseString()) + ")");
		}
		return flag;
	}


	public void setDefiniendumAndDefiniens(final Concept definiendum, final Concept definiens) 
			throws LIMException {
		setDefiniendum(definiendum);
		setDefiniens(definiens);
	}

	public void setDefiniendumAndDefiniens(final Concept definiendum, final Concept definiens, 
			final Concept connectiveOfDefinition) throws LIMException {
		setDefiniendumAndDefiniens(definiendum, definiens);
		setConnectiveOfDefinition(connectiveOfDefinition);
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

		BaseElement definiendum = item.get(DEFINIENDUM);
		if (definiendum != null && definiendum.getId() != null) {
			GenericElement ge = rootElementMap.get(definiendum.getId());
			super.addInnerElementDelegate(definiendum, ge);
		}
		BaseElement definiens = item.get(DEFINIENS);
		if (definiens != null && definiens.getId() != null) {
			GenericElement ge = rootElementMap.get(definiens.getId());
			super.addInnerElementDelegate(definiens, ge);
		}
		BaseElement connectiveOfDefinition = item.get(CONNECTIVE_OF_DEFINITION);
		if (connectiveOfDefinition != null && connectiveOfDefinition.getId() != null) {
			GenericElement ge = rootElementMap.get(connectiveOfDefinition.getId());
			super.addInnerElementDelegate(connectiveOfDefinition, ge);
		}
	}

}
