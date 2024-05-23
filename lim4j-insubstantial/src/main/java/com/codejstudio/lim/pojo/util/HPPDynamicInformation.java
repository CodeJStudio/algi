package com.codejstudio.lim.pojo.util;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.JudgedStatementGroup;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class HPPDynamicInformation extends DynamicInformation {

	/* constants */

	private static final long serialVersionUID = -5270943058346575670L;


	/* variables: arrays, collections, maps, groups */

	protected String[] dynamicAntecedentFragmentArray;
	protected String[] dynamicConsequentFragmentArray;

	protected String[] uniqueDynamicConsequentFragmentArray;
	protected ConditionGroup[] uniqueDynamicConsequentFragmentConditionArray;


	/* constructors */

	protected HPPDynamicInformation(HypotheticalProposition hypotheticalProposition) throws LIMException {
		if (hypotheticalProposition == null) {
			throw new LIMException(new IllegalArgumentException());
		}

		init(hypotheticalProposition);
	}


	/* initializers */

	public static final HPPDynamicInformation newInstance(
			final HypotheticalProposition hypotheticalProposition) throws LIMException {
		if (hypotheticalProposition == null) {
			return null;
		}
		return new HPPDynamicInformation(hypotheticalProposition);
	}

	private void init(final HypotheticalProposition hypotheticalProposition) throws LIMException {
		ConditionGroup atdg = hypotheticalProposition.getAntecedentGroup();
		Set<String> dafs = generateDynamicAntecedentFragmentArrayAndGetSourceSet(atdg);

		JudgedStatementGroup csqg = hypotheticalProposition.getConsequentGroup();
		Set<String> dcfs = generateDynamicConsequentFragmentArrayAndGetSourceSet(csqg);

		generateDynamicFragmentArray(dafs, dcfs);

		generateDynamicFragmentParameterTypeArrayAndCount();
		generateNetDynamicFragmentArray();
		generateNetDynamicFragmentParameterTypeArrayAndCount();

		generateUniqueDynamicConsequentFragmentArray(dafs, dcfs);
		generateUniqueDynamicConsequentFragmentConditionArray(csqg);
	}

	private Set<String> generateDynamicAntecedentFragmentArrayAndGetSourceSet(
			final ConditionGroup antecedentConditionGroup) throws LIMException {
		if (GroupableUtil.checkNullOrEmpty(antecedentConditionGroup)) {
			return null;
		}

		Set<String> dafs = CollectionUtil.generateNewSet();
		List<Condition> atdigl = antecedentConditionGroup.getInnerGroupList();
		for (Condition cd : atdigl) {
			Statement s;
			DynamicInformation di;
			if (!(cd instanceof HypotheticalCondition) 
					|| (s = ((HypotheticalCondition) cd).getAntecedent()) == null 
					|| (di = s.getDynamicInformation()) == null 
					|| CollectionUtil.checkNullOrEmpty(di.dynamicFragmentArray)) {
				continue;
			}
			CollectionUtil.addAllOfSubClass(dafs, di.dynamicFragmentArray);
		}
		this.dynamicAntecedentFragmentArray = !CollectionUtil.checkNullOrEmpty(dafs) 
				? dafs.toArray(new String[0]) : this.dynamicAntecedentFragmentArray;
		return dafs;
	}

	private Set<String> generateDynamicConsequentFragmentArrayAndGetSourceSet(
			final JudgedStatementGroup consequentConditionGroup) throws LIMException {
		if (GroupableUtil.checkNullOrEmpty(consequentConditionGroup)) {
			return null;
		}

		Set<String> dcfs = CollectionUtil.generateNewSet();
		List<JudgedStatement> csqigl = consequentConditionGroup.getInnerGroupList();
		for (JudgedStatement js : csqigl) {
			DynamicInformation di;
			if (js == null || (di = js.getDynamicInformation()) == null 
					|| CollectionUtil.checkNullOrEmpty(di.dynamicFragmentArray)) {
				continue;
			}
			CollectionUtil.addAllOfSubClass(dcfs, di.dynamicFragmentArray);
		}
		this.dynamicConsequentFragmentArray = !CollectionUtil.checkNullOrEmpty(dcfs) 
				? dcfs.toArray(new String[0]) : this.dynamicConsequentFragmentArray;
		return dcfs;
	}

	private void generateDynamicFragmentArray(final Set<String> dynamicAntecedentFragmentSet, 
			final Set<String> dynamicConsequentFragmentSet) throws LIMException {
		Set<String> dfs = CollectionUtil.generateNewSet();
		CollectionUtil.addAllOfSubClass(dfs, dynamicAntecedentFragmentSet);
		CollectionUtil.addAllOfSubClass(dfs, dynamicConsequentFragmentSet);
		this.dynamicFragmentArray = !CollectionUtil.checkNullOrEmpty(dfs) 
				? dfs.toArray(new String[0]) : this.dynamicFragmentArray;
	}

	private void generateUniqueDynamicConsequentFragmentArray(
			final Set<String> dynamicAntecedentFragmentSet, final Set<String> dynamicConsequentFragmentSet) 
					throws LIMException {
		Collection<String> udcfs = CollectionUtil.getRelativeComplement(
				dynamicConsequentFragmentSet, dynamicAntecedentFragmentSet);
		this.uniqueDynamicConsequentFragmentArray = !CollectionUtil.checkNullOrEmpty(udcfs) 
				? udcfs.toArray(new String[0]) : this.uniqueDynamicConsequentFragmentArray;
	}

	private void generateUniqueDynamicConsequentFragmentConditionArray(
			final JudgedStatementGroup consequentConditionGroup) throws LIMException {
		List<JudgedStatement> csqigl;
		if (CollectionUtil.checkNullOrEmpty(this.uniqueDynamicConsequentFragmentArray) 
				|| GroupableUtil.checkNullOrEmpty(consequentConditionGroup) 
				|| CollectionUtil.checkNullOrEmpty(csqigl = consequentConditionGroup.getInnerGroupList())) {
			return;
		}

		this.uniqueDynamicConsequentFragmentConditionArray 
				= new ConditionGroup[this.uniqueDynamicConsequentFragmentArray.length];
		for (int i = 0; i < this.uniqueDynamicConsequentFragmentArray.length; i++) {
			for (JudgedStatement js : csqigl) {
				DynamicInformation di;
				if (js == null || (di = js.getDynamicInformation()) == null 
						|| CollectionUtil.checkNullOrEmpty(di.dynamicFragmentArray)) {
					continue;
				}
				boolean flag = false;
				for (int j = 0; j < di.dynamicFragmentArray.length; j++) {
					if (Objects.equals(
							this.uniqueDynamicConsequentFragmentArray[i], di.dynamicFragmentArray[j]) 
							&& di.dynamicFragmentConditionArray != null 
							&& di.dynamicFragmentConditionArray.length > j) {
						this.uniqueDynamicConsequentFragmentConditionArray[i] 
								= di.dynamicFragmentConditionArray[j];
						flag = true;
						break;
					}
				}
				if (flag) {
					break;
				}
			}
		}
	}


	/* getters & setters */

	public String[] getDynamicAntecedentFragmentArray() {
		return dynamicAntecedentFragmentArray;
	}

	public String[] getDynamicConsequentFragmentArray() {
		return dynamicConsequentFragmentArray;
	}

	public String[] getUniqueDynamicConsequentFragmentArray() {
		return uniqueDynamicConsequentFragmentArray;
	}

	public ConditionGroup[] getUniqueDynamicConsequentFragmentConditionArray() {
		return uniqueDynamicConsequentFragmentConditionArray;
	}


	/* class methods */

	public boolean verifyUniqueDynamicConsequentFragmentCondition(final InformationElement element, 
			final int index) throws LIMException {
		if (this.uniqueDynamicConsequentFragmentConditionArray == null 
				|| this.uniqueDynamicConsequentFragmentConditionArray.length <= index 
				|| this.uniqueDynamicConsequentFragmentConditionArray[index] == null) {
			return true;
		}
		return IConditionable.verifyCondition(element, 
				this.uniqueDynamicConsequentFragmentConditionArray[index]);
	}

}
