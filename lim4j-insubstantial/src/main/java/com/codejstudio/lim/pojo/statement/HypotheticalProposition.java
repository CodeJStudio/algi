package com.codejstudio.lim.pojo.statement;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.DynamicInformation;
import com.codejstudio.lim.pojo.util.DynamicableUtil;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.HPPDynamicInformation;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * HypotheticalProposition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class HypotheticalProposition extends Proposition {

	/* constants */

	private static final long serialVersionUID = 272463665815021979L;

	public static final String ANTECEDENTS = "antecedents";
	public static final String CONSEQUENTS = "consequents";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public HypotheticalProposition() {
		super();
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag, String description) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag, JudgedStatement consequent, 
			HypotheticalCondition... antecedents) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConsequentAndAntecedent(consequent, antecedents);
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag, 
			JudgedStatement[] consequentArray, HypotheticalCondition... antecedents) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConsequentAndAntecedent(consequentArray, antecedents);
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag, 
			Collection<JudgedStatement> consequentCollection, HypotheticalCondition... antecedents) 
					throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConsequentAndAntecedent(consequentCollection, antecedents);
	}


	public HypotheticalProposition(String description) throws LIMException {
		this(true, true, description);
	}

	public HypotheticalProposition(JudgedStatement consequent, HypotheticalCondition... antecedents) 
			throws LIMException {
		this(true, true, consequent, antecedents);
	}

	public HypotheticalProposition(JudgedStatement[] consequentArray, HypotheticalCondition... antecedents) 
			throws LIMException {
		this(true, true, consequentArray, antecedents);
	}

	public HypotheticalProposition(Collection<JudgedStatement> consequentCollection, 
			HypotheticalCondition... antecedents) throws LIMException {
		this(true, true, consequentCollection, antecedents);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(HypotheticalProposition.class);
		JudgedStatement.registerSubPojoClassForInit(HypotheticalProposition.class);
	}


	private ConditionGroup initAntecedentGroup() throws LIMException {
		ConditionGroup atdg = getAntecedentGroup();
		if (atdg == null) {
			atdg = new ConditionGroup(true);
			super.addInnerElementDelegate(atdg);
			super.putIntegratedElementDelegate(ANTECEDENTS, new BaseElement(atdg));
		}
		return atdg;
	}

	private JudgedStatementGroup initConsequentGroup() throws LIMException {
		JudgedStatementGroup csqg = getConsequentGroup();
		if (csqg == null) {
			csqg = new JudgedStatementGroup(true);
			super.addInnerElementDelegate(csqg);
			super.putIntegratedElementDelegate(CONSEQUENTS, new BaseElement(csqg));
		}
		return csqg;
	}


	/* destroyers */

	private void destroyAntecedentGroup(ConditionGroup antecedentGroup) {
		if (antecedentGroup != null && antecedentGroup.size() == 0) {
			super.removeInnerElementDelegate(antecedentGroup.getId());
			super.removeIntegratedElementDelegate(ANTECEDENTS);
		}
	}

	private void destroyConsequentGroup(JudgedStatementGroup consequentGroup) {
		if (consequentGroup != null && consequentGroup.size() == 0) {
			super.removeInnerElementDelegate(consequentGroup.getId());
			super.removeIntegratedElementDelegate(CONSEQUENTS);
		}
	}


	/* getters & setters */

	public ConditionGroup getAntecedentGroup() {
		return (ConditionGroup) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(ANTECEDENTS));
	}

	public JudgedStatementGroup getConsequentGroup() {
		return (JudgedStatementGroup) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(CONSEQUENTS));
	}


	/* CRUD for arrays, collections, maps, groups: antecedents */

	public boolean addAntecedent(final HypotheticalCondition... antecedents) throws LIMException {
		return addAntecedent((antecedents == null) ? null : Arrays.asList(antecedents));
	}

	public boolean addAntecedent(final Collection<HypotheticalCondition> antecedentCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(antecedentCollection)) {
			return false;
		}

		ConditionGroup atdg = null;
		try {
			atdg = initAntecedentGroup();
			boolean flag = super.addInnerElementDelegate(antecedentCollection) 
					& atdg.addGroupElement(antecedentCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addAntecedent(" 
						+ BaseElement.toBaseString(antecedentCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addAntecedent(" 
						+ BaseElement.toBaseString(antecedentCollection) + ")");
			}
			return flag;
		} finally {
			updateDynamicInformation();
			destroyAntecedentGroup(atdg);
		}
	}

	public boolean removeAntecedent(final String id) throws LIMException {
		ConditionGroup atdg;
		if (id == null || (atdg = getAntecedentGroup()) == null || !atdg.containGroupElement(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeAntecedent(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& atdg.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeAntecedent(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeAntecedent(" + id + ")");
			}
			return flag;
		} finally {
			updateDynamicInformation();
			destroyAntecedentGroup(atdg);
		}
	}


	/* CRUD for arrays, collections, maps, groups: consequents */

	public boolean addConsequent(final JudgedStatement... consequents) throws LIMException {
		return addConsequent((consequents == null) ? null : Arrays.asList(consequents));
	}

	public boolean addConsequent(final Collection<JudgedStatement> consequentCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(consequentCollection)) {
			return false;
		}

		JudgedStatementGroup csqg = null;
		try {
			csqg = initConsequentGroup();
			boolean flag = super.addInnerElementDelegate(consequentCollection) 
					& csqg.addGroupElement(consequentCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addConsequent(" 
						+ BaseElement.toBaseString(consequentCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addConsequent(" 
						+ BaseElement.toBaseString(consequentCollection) + ")");
			}
			return flag;
		} finally {
			updateDynamicInformation();
			destroyConsequentGroup(csqg);
		}
	}

	public boolean removeConsequent(final String id) throws LIMException {
		JudgedStatementGroup csqg;
		if (id == null || (csqg = getConsequentGroup()) == null || !csqg.containGroupElement(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeConsequent(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& csqg.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeConsequent(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeConsequent(" + id + ")");
			}
			return flag;
		} finally {
			updateDynamicInformation();
			destroyConsequentGroup(csqg);
		}
	}


	/* CRUD for arrays, collections, maps, groups: antecedents & consequents */

	public boolean addConsequentAndAntecedent(final JudgedStatement consequent, 
			final HypotheticalCondition... antecedents) throws LIMException {
		return addAntecedent(antecedents) & addConsequent(consequent);
	}

	public boolean addConsequentAndAntecedent(final JudgedStatement[] consequentArray, 
			final HypotheticalCondition... antecedents) throws LIMException {
		return addAntecedent(antecedents) & addConsequent(consequentArray);
	}

	public boolean addConsequentAndAntecedent(final Collection<JudgedStatement> consequentCollection, 
			final HypotheticalCondition... antecedents) throws LIMException {
		return addAntecedent(antecedents) & addConsequent(consequentCollection);
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

		BaseElement antecedentGroup = item.get(ANTECEDENTS);
		if (antecedentGroup != null && antecedentGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(antecedentGroup.getId());
			super.addInnerElementDelegate(antecedentGroup, ge);
		}
		BaseElement consequentGroup = item.get(CONSEQUENTS);
		if (consequentGroup != null && consequentGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(consequentGroup.getId());
			super.addInnerElementDelegate(consequentGroup, ge);
		}
	}


	/* class methods */

	private void updateDynamicInformation() throws LIMException {
		super.setDynamicInformation(HPPDynamicInformation.newInstance(this));
	}


	public boolean verifyConditionsOfAntecedent(final Statement statement, final int index) 
			throws LIMException {
		ConditionGroup atdg;
		List<Condition> atdigl;
		Condition cd;
		Statement s;
		DynamicInformation di;
		return statement != null && !CollectionUtil.checkNullOrEmpty(atdg = getAntecedentGroup()) 
				&& index >= 0 && index < (atdigl = atdg.getInnerGroupList()).size()
				&& (cd = atdigl.get(index)) instanceof HypotheticalCondition 
				&& (s = ((HypotheticalCondition) cd).getAntecedent()) != null 
				&& (di = s.getDynamicInformation()) != null 
				&& di.verifyDynamicFragmentCondition(statement);
	}

	public boolean verifyDynamicAntecedentFragment(final InformationElement[] elementArray) 
			throws LIMException {
		ConditionGroup atdg;
		List<Condition> atdigl;
		if (CollectionUtil.checkNullOrEmpty(elementArray) 
				|| CollectionUtil.checkNullOrEmpty(atdg = getAntecedentGroup()) 
				|| elementArray.length != (atdigl = atdg.getInnerGroupList()).size()) {
			return false;
		}
		if (elementArray.length == 1) {
			return true;
		}

		Map<String, String> dynamicFragmentsMapping = CollectionUtil.generateNewMap();
		Map<String, String> staticFragmentsMapping = CollectionUtil.generateNewMap();
		for (int i = 0; i < elementArray.length; i++) {
			String dsc;
			Condition cd = atdigl.get(i);
			Statement s;
			DynamicInformation di;
			String[][] arrayOfFragmentArrays;
			String[] dfa;
			if (elementArray[i] == null || (dsc = elementArray[i].getDescription()) == null
					|| !(cd instanceof HypotheticalCondition) 
					|| (s = ((HypotheticalCondition) cd).getAntecedent()) == null 
					|| (di = s.getDynamicInformation()) == null 
					|| CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays = DynamicableUtil
							.analyzeStaticContentBasedOnDynamicContent(dsc, di)) 
					|| arrayOfFragmentArrays.length != 3 
					|| (dfa = di.getDynamicFragmentArray()).length != arrayOfFragmentArrays[2].length) {
				return false;
			}

			for (int j = 0; j < arrayOfFragmentArrays[2].length; j++) {
				String sf = dynamicFragmentsMapping.get(dfa[j]);
				String df = staticFragmentsMapping.get(arrayOfFragmentArrays[2][j]);
				if (sf == null && df == null) {
					dynamicFragmentsMapping.put(dfa[j], arrayOfFragmentArrays[2][j]);
					staticFragmentsMapping.put(arrayOfFragmentArrays[2][j], dfa[j]);
				} else if (sf == null || df == null 
						|| !sf.equals(arrayOfFragmentArrays[2][j]) || !df.equals(dfa[j])) {
					return false;
				}
			}
		}
		return true;
	}

}
