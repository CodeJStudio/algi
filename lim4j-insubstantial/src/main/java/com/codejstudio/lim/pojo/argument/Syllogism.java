package com.codejstudio.lim.pojo.argument;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Syllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Syllogism extends Argument {

	/* constants */

	private static final long serialVersionUID = 7984524102210068435L;

	public static final String MAJOR_PREMISE = "major-premise";
	public static final String MINOR_PREMISE = "minor-premise";
	public static final String CONCLUSION = "conclusion";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Syllogism() {
		super();
	}

	public Syllogism(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Syllogism(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public Syllogism(String description) throws LIMException {
		super(true, true, description);
	}

	public Syllogism(JudgedStatement majorPremise, JudgedStatement minorPremise, 
			JudgedStatement conclusion) throws LIMException {
		super(true, true);
		setElementsOfSyllogism(majorPremise, minorPremise, conclusion);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Syllogism.class);
		Argument.registerSubPojoClassForInit(Syllogism.class);
	}


	/* getters & setters */

	public JudgedStatement getMajorPremise() {
		return (JudgedStatement) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(MAJOR_PREMISE));
	}

	public boolean setMajorPremise(final JudgedStatement majorPremise) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(MAJOR_PREMISE);
		boolean flag = true;
		if (be != null && majorPremise != null) {
			if (!be.baseEquals(majorPremise)) {
				flag &= super.replaceInnerElementDelegate(be, majorPremise) 
						& super.putIntegratedElementDelegate(MAJOR_PREMISE, new BaseElement(majorPremise));
			}
		} else if (be != null) {
			flag &= super.removeJudgedStatement(be.getId()) 
					& super.removeEvidence(be.getId()) 
					& super.removeIntegratedElementDelegate(MAJOR_PREMISE) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (majorPremise != null) {
			be = new BaseElement(majorPremise);
			flag &= super.addInnerElementDelegate(be, majorPremise) 
					& super.putIntegratedElementDelegate(MAJOR_PREMISE, be) 
					& super.addJudgedStatement(majorPremise) 
					& super.addEvidence(majorPremise);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setMajorPremise(" 
					+ ((majorPremise == null) ? null : majorPremise.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setMajorPremise(" 
					+ ((majorPremise == null) ? null : majorPremise.toBaseString()) + ")");
		}
		return flag;
	}

	public JudgedStatement getMinorPremise() {
		return (JudgedStatement) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(MINOR_PREMISE));
	}

	public boolean setMinorPremise(final JudgedStatement minorPremise) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(MINOR_PREMISE);
		boolean flag = true;
		if (be != null && minorPremise != null) {
			if (!be.baseEquals(minorPremise)) {
				flag &= super.replaceInnerElementDelegate(be, minorPremise) 
						& super.putIntegratedElementDelegate(MINOR_PREMISE, new BaseElement(minorPremise));
			}
		} else if (be != null) {
			flag &= super.removeJudgedStatement(be.getId()) 
					& super.removeEvidence(be.getId()) 
					& super.removeIntegratedElementDelegate(MINOR_PREMISE) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (minorPremise != null) {
			be = new BaseElement(minorPremise);
			flag &= super.addInnerElementDelegate(be, minorPremise) 
					& super.putIntegratedElementDelegate(MINOR_PREMISE, be) 
					& super.addJudgedStatement(minorPremise) 
					& super.addEvidence(minorPremise);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setMinorPremise(" 
					+ ((minorPremise == null) ? null : minorPremise.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setMinorPremise(" 
					+ ((minorPremise == null) ? null : minorPremise.toBaseString()) + ")");
		}
		return flag;
	}

	public JudgedStatement getConclusion() {
		return (JudgedStatement) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(CONCLUSION));
	}

	public boolean setConclusion(final JudgedStatement conclusion) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(CONCLUSION);
		boolean flag = true;
		if (be != null && conclusion != null) {
			if (!be.baseEquals(conclusion)) {
				flag &= super.replaceInnerElementDelegate(be, conclusion) 
						& super.putIntegratedElementDelegate(CONCLUSION, new BaseElement(conclusion));
			}
		} else if (be != null) {
			flag &= super.removeJudgedStatement(be.getId()) 
					& super.removeConclusion(be.getId()) 
					& super.removeIntegratedElementDelegate(CONCLUSION) 
					& super.removeInnerElementDelegate(be.getId());
		} else if (conclusion != null) {
			be = new BaseElement(conclusion);
			flag &= super.addInnerElementDelegate(be, conclusion) 
					& super.putIntegratedElementDelegate(CONCLUSION, be) 
					& super.addJudgedStatement(conclusion) 
					& super.addConclusion(conclusion);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setConclusion(" 
					+ ((conclusion == null) ? null : conclusion.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setConclusion(" 
					+ ((conclusion == null) ? null : conclusion.toBaseString()) + ")");
		}
		return flag;
	}


	public void setElementsOfSyllogism(final JudgedStatement majorPremise, 
			final JudgedStatement minorPremise, final JudgedStatement conclusion) throws LIMException {
		setMajorPremise(majorPremise);
		setMinorPremise(minorPremise);
		setConclusion(conclusion);
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

		BaseElement majorPremise = item.get(MAJOR_PREMISE);
		if (majorPremise != null && majorPremise.getId() != null) {
			GenericElement ge = rootElementMap.get(majorPremise.getId());
			super.addInnerElementDelegate(majorPremise, ge);
		}
		BaseElement minorPremise = item.get(MINOR_PREMISE);
		if (minorPremise != null && minorPremise.getId() != null) {
			GenericElement ge = rootElementMap.get(minorPremise.getId());
			super.addInnerElementDelegate(minorPremise, ge);
		}
		BaseElement conclusion = item.get(CONCLUSION);
		if (conclusion != null && conclusion.getId() != null) {
			GenericElement ge = rootElementMap.get(conclusion.getId());
			super.addInnerElementDelegate(conclusion, ge);
		}
	}


	@Override
	public Syllogism cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(Syllogism.class)) {
					return (Syllogism) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Syllogism clonedElement = (Syllogism) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Syllogism cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Syllogism) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Syllogism)) 
				? null : cloneToElement((Syllogism) ce, null);
	}

	private Syllogism cloneToElement(final Syllogism clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {

		return clonedElement;
	}

}
