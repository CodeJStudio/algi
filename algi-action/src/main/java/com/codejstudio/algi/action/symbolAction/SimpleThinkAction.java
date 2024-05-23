package com.codejstudio.algi.action.symbolAction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.dynamicAction.DynamicHypotheticalPropositionAction;
import com.codejstudio.algi.action.symbol.DynamicStatementSymbol;
import com.codejstudio.algi.action.symbol.SimpleThinkSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.collection.MultiValueMap;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.i.IRelationable;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.RelationGroup;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.JudgedStatementGroup;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.HPPDynamicInformation;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SimpleThinkAction extends AbstractSingleSymbolContentAction {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(SimpleThinkAction.class);

	private static final long serialVersionUID = 7993304507295166815L;


	/* variables */

	protected Statement dynamicStatement;


	/* variables: arrays, collections, maps, groups */

	private MultiValueMap<HypotheticalProposition, SimpleThinkObject> simpleThinkObjectMVMap;

	private Set<BaseRelation> relationSet;


	/* constructors */

	public SimpleThinkAction() {
		super();
		this.symbol = SimpleThinkSymbol.getInstance();
	}

	public SimpleThinkAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public SimpleThinkAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		String id;
		GenericElement ge;
		if (!verifyContent() 
				|| (ge = Informationiverse.getElement(id = (String) this.singleContent)) == null) {
			return null;
		}

		Map<String, GenericElement> sgem = CollectionUtil.generateNewMap();
		sgem.put(id, ge);
		GenericElement thoughtElement = doThink(ge, sgem);

		Boolean dn;
		return (thoughtElement == null || ((dn = thoughtElement.getDynamic()) != null && dn)) 
				? null : new Object[] {thoughtElement};
	}

	private GenericElement doThink(final GenericElement element, 
			Map<String, GenericElement> elementMap) throws LIMException {
		if (element == null) {
			return null;
		}

		Boolean dn;
		if ((dn = element.getDynamic()) == null || !dn) {
			return !(element instanceof IRelationable) 
					? null : doThinkRelationable((IRelationable) element, elementMap);
		}

		if (element instanceof ActionDefinition) {
			ActionFlow afl = new ActionFlow((ActionDefinition) element);
			Object[] aflOutputArray = afl.execute(this.session);
			return (!CollectionUtil.checkNullOrEmpty(aflOutputArray) 
							&& aflOutputArray[0] instanceof GenericElement) 
					? (GenericElement) aflOutputArray[0] 
					: doThinkRandomDynamicHypotheticalProposition(elementMap);
		} else if (element instanceof HypotheticalProposition) {
			return doThinkDynamicHypotheticalProposition(
					(HypotheticalProposition) element, elementMap);
		} else if (element instanceof Statement) {
			this.dynamicStatement = (Statement) element;
			GenericElement ge = doThinkDynamicStatement();
			if (ge != null) {
				return ge;
			} else {
				return doThinkRandomDynamicHypotheticalProposition(elementMap);
			}
		} else if (element instanceof IRelationable) {
			return doThinkRelationable((IRelationable) element, elementMap);
		}

		return null;
	}

	private GenericElement doThinkDynamicHypotheticalProposition(
			final HypotheticalProposition hypotheticalProposition, 
			Map<String, GenericElement> elementMap) throws LIMException {
		ConditionGroup atdg = hypotheticalProposition.getAntecedentGroup();
		List<Condition> atdigl;
		JudgedStatementGroup csqg = hypotheticalProposition.getConsequentGroup();
		List<JudgedStatement> csqigl;
		if (atdg == null || CollectionUtil.checkNullOrEmpty(atdigl = atdg.getInnerGroupList()) 
				|| csqg == null || CollectionUtil.checkNullOrEmpty(csqigl = csqg.getInnerGroupList())
				) {
			return null;
		}

		// Toggle below commented lines, to unlock the thinking process tracking
		log.debug(LogUtil.wrap("hppID: " + hypotheticalProposition.getId() + "; csqg: " + csqigl));
		StringBuilder sb = new StringBuilder("atdg: ");
		for (Condition cd : atdigl) {
			sb.append(((HypotheticalCondition) cd).getAntecedent() + "; ");
		}
		log.debug(LogUtil.wrap(sb.toString()));

		int atdSize = atdigl.size();
		Statement[] sa = (atdSize <= 0) ? null : new Statement[atdSize];
		HPPDynamicInformation hdi 
				= (HPPDynamicInformation) hypotheticalProposition.getDynamicInformation();
		String[] udcfa = hdi.getUniqueDynamicConsequentFragmentArray();
		int udcfSize = CollectionUtil.checkNullOrEmpty(udcfa) ? 0 : udcfa.length;
		Concept[] ca = (udcfSize <= 0) ? null : new Concept[udcfSize];
		if (this.simpleThinkObjectMVMap == null) {
			this.simpleThinkObjectMVMap = CollectionUtil.generateNewMultiValueMap();
		}

		int count = 0;
		do {
			int atdCount = 0;
			do {
				for (int i = 0; (atdSize > 0 && i < atdSize); i++) {
					Boolean dn;
					while ((sa[i] = getNextStatement()).getDescriptionObject() == null 
							|| ((dn = sa[i].getDynamic()) != null && dn) 
							|| !hypotheticalProposition.verifyConditionsOfAntecedent(sa[i], i));
				}
				atdCount++;

				// Toggle below commented lines, to unlock the thinking process tracking
				if (atdCount == 150) {
					log.debug(LogUtil.wrap("antecedentCount == 150"));
				} else
				if (atdCount == 100) {
					log.debug(LogUtil.wrap("antecedentCount == 100"));
				}

			} while (!hypotheticalProposition.verifyDynamicAntecedentFragment(sa) && atdCount <= 150);

			for (int i = 0; (udcfSize > 0 && i < udcfSize); i++) {
				Boolean dn;
				while ((ca[i] = getNextConcept()).getDescriptionObject() == null 
						|| ((dn = ca[i].getDynamic()) != null && dn) 
						|| !hdi.verifyUniqueDynamicConsequentFragmentCondition(ca[i], i));
			}
			count++;
		} while (this.simpleThinkObjectMVMap.contains(hypotheticalProposition, 
				new SimpleThinkObject(hypotheticalProposition, sa, ca)) && count <= 30);


		// Toggle below commented lines, to unlock the thinking process tracking
		log.debug(LogUtil.wrap("sa: " + ((sa == null) ? null : Arrays.asList((Object[]) sa))));
		log.debug(LogUtil.wrap("ca: " + ((ca == null) ? null : Arrays.asList((Object[]) ca))));

		Action dhppa = new DynamicHypotheticalPropositionAction(hypotheticalProposition);
		InputParameter[] ipa = InputParameter.generateInstances(
				DynamicHypotheticalPropositionAction.PARAMETER_ELEMENT, new Object[] {sa, ca});
		Object[] dhppaOutputArray = dhppa.execute(ipa);
		this.simpleThinkObjectMVMap.put(hypotheticalProposition, 
				new SimpleThinkObject(hypotheticalProposition, sa, ca));

		// Toggle below commented lines, to unlock the thinking process tracking
		log.debug(LogUtil.wrap("DynamicHypotheticalPropositionAction.execute: " 
				+ ((dhppaOutputArray == null) ? null : Arrays.asList((Object[]) dhppaOutputArray))));
		log.debug("");

		return (!CollectionUtil.checkNullOrEmpty(dhppaOutputArray) 
						&& dhppaOutputArray[0] instanceof Statement) 
				? doThink(this.dynamicStatement, elementMap) 
				: doThinkRandomDynamicHypotheticalProposition(elementMap);
	}

	private GenericElement doThinkRandomDynamicHypotheticalProposition(
			Map<String, GenericElement> elementMap) throws LIMException {
		HypotheticalProposition hpp = getNextHypotheticalProposition();
		return doThink(hpp, elementMap);
	}

	private GenericElement doThinkDynamicStatement() throws LIMException {
		Boolean dn;
		if (this.dynamicStatement == null || (dn = this.dynamicStatement.getDynamic()) == null || !dn) {
			return null;
		}

		Action dssa = new DynamicStatementSymbolAction(
				"<ds(>" + this.dynamicStatement.getId() + "," + DynamicStatementSymbol.TYPE_INFO + "<)>");
		Object[] dssaOutputArray = dssa.execute(this.session);
		return (CollectionUtil.checkNullOrEmpty(dssaOutputArray) 
						|| !(dssaOutputArray[0] instanceof GenericElement)) 
				? null : (GenericElement) dssaOutputArray[0];
	}

	private GenericElement doThinkRelationable(final IRelationable relationableElement, 
			Map<String, GenericElement> elementMap) throws LIMException {
		RelationGroup rg;
		List<BaseRelation> rgigl;
		if (relationableElement == null 
				|| GroupableUtil.checkNullOrEmpty(rg = relationableElement.getRelationGroup()) 
				|| CollectionUtil.checkNullOrEmpty(rgigl = rg.getInnerGroupList())) {
			return null;
		}

		Set<IRelationable> rbs = CollectionUtil.generateNewSet();
		if (this.relationSet == null) {
			this.relationSet = CollectionUtil.generateNewSet();
		}
		for (BaseRelation br : rgigl) {
			if (br == null || this.relationSet.contains(br)) {
				continue;
			}
			this.relationSet.add(br);
			IRelationable irb = br.getAnotherElement(relationableElement);
			GenericElement ge = doThink((GenericElement) irb, elementMap);
			if (ge != null) {
				return ge;
			} else {
				rbs.add(irb);
			}
		}
		for (IRelationable irb : rbs) {
			GenericElement ge = doThinkRelationable(irb, elementMap);
			if (ge != null) {
				return ge;
			}
		}
		return null;
	}


	private Concept getNextConcept() throws LIMException {
		return Informationiverse.getRandomConcept();
	}

	private Statement getNextStatement() throws LIMException {
		return Informationiverse.getRandomStatement();
	}

	private HypotheticalProposition getNextHypotheticalProposition() throws LIMException {
		return Informationiverse.getRandomHypotheticalProposition();
	}



	/* inner classes */

	static class SimpleThinkObject {

		/* constants */

		static final Logger log = LoggerFactory.getLogger(SimpleThinkObject.class);


		/* variables */

		private HypotheticalProposition hypotheticalProposition;


		/* variables: arrays, collections, maps, groups */

		private Statement[] antecedentArray;
		private Concept[] uniqueConsequentFragmentArray;


		/* constructors */

		SimpleThinkObject(HypotheticalProposition hypotheticalProposition, Statement[] antecedentArray, 
				Concept[] uniqueConsequentFragmentArray) {
			this.hypotheticalProposition = hypotheticalProposition;
			this.antecedentArray = antecedentArray;
			this.uniqueConsequentFragmentArray = uniqueConsequentFragmentArray;
		}


		/* overridden methods */

		@Override
		public int hashCode() {
			try {
				return Objects.hash(makeupInputArray(this.hypotheticalProposition, this.antecedentArray, 
						this.uniqueConsequentFragmentArray));
			} catch (LIMException e) {
				log.error(e.toString(), e);
			}
			return super.hashCode();
		}

		@Override
		public boolean equals(final Object object) {
			if (this == object) {
				return true;
			}
			if (object == null) {
				return false;
			}
			if (getClass() != object.getClass()) {
				return false;
			}
			SimpleThinkObject stobj = (SimpleThinkObject) object;
			return Objects.equals(this.hypotheticalProposition, stobj.hypotheticalProposition) 
					&& CollectionUtil.arrayEquals(this.antecedentArray, stobj.antecedentArray)
					&& CollectionUtil.arrayEquals(
							this.uniqueConsequentFragmentArray, stobj.uniqueConsequentFragmentArray);
		}

	}

}
