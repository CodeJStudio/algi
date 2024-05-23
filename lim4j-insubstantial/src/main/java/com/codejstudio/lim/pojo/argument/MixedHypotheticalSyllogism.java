package com.codejstudio.lim.pojo.argument;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * MixedHypotheticalSyllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class MixedHypotheticalSyllogism extends HypotheticalSyllogism {

	/* constants */

	private static final long serialVersionUID = -3392649409328429493L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public MixedHypotheticalSyllogism() {
		super();
	}

	public MixedHypotheticalSyllogism(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public MixedHypotheticalSyllogism(boolean initIdFlag, boolean initTypeFlag, String description) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public MixedHypotheticalSyllogism(String description) throws LIMException {
		super(true, true, description);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(MixedHypotheticalSyllogism.class);
		Argument.registerSubPojoClassForInit(MixedHypotheticalSyllogism.class);
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


	public void setElementsOfSyllogism(HypotheticalProposition majorPremise, JudgedStatement minorPremise, 
			JudgedStatement conclusion) throws LIMException {
		super.setElementsOfSyllogism(majorPremise, minorPremise, conclusion);
	}

}
