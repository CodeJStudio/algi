package com.codejstudio.lim.pojo.argument;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * PureHypotheticalSyllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PureHypotheticalSyllogism extends HypotheticalSyllogism {

	/* constants */

	private static final long serialVersionUID = -3120648047131465715L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public PureHypotheticalSyllogism() {
		super();
	}

	public PureHypotheticalSyllogism(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public PureHypotheticalSyllogism(boolean initIdFlag, boolean initTypeFlag, String description) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public PureHypotheticalSyllogism(String description) throws LIMException {
		super(true, true, description);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(PureHypotheticalSyllogism.class);
		Argument.registerSubPojoClassForInit(PureHypotheticalSyllogism.class);
	}


	/* getters & setters */

	public HypotheticalProposition getMajorPremise() {
		JudgedStatement majorPremise = super.getMajorPremise();
		return !(majorPremise instanceof HypotheticalProposition) 
				? null : (HypotheticalProposition) majorPremise;
	}

	public void setMajorPremise(HypotheticalProposition majorPremise) throws LIMException {
		super.setMajorPremise(majorPremise);
	}

	public HypotheticalProposition getMinorPremise() {
		JudgedStatement minorPremise = super.getMinorPremise();
		return !(minorPremise instanceof HypotheticalProposition) 
				? null : (HypotheticalProposition) minorPremise;
	}

	public void setMinorPremise(HypotheticalProposition minorPremise) throws LIMException {
		super.setMinorPremise(minorPremise);
	}

	public HypotheticalProposition getConclusion() {
		JudgedStatement conclusion = super.getConclusion();
		return !(conclusion instanceof HypotheticalProposition) 
				? null : (HypotheticalProposition) conclusion;
	}

	public void setConclusion(HypotheticalProposition conclusion) throws LIMException {
		super.setConclusion(conclusion);
	}


	public void setElementsOfSyllogism(HypotheticalProposition majorPremise, 
			HypotheticalProposition minorPremise, HypotheticalProposition conclusion) throws LIMException {
		super.setElementsOfSyllogism(majorPremise, minorPremise, conclusion);
	}

}
