package com.codejstudio.lim.pojo.argument;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.GenericElementGroup;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.attribute.AttributeGroup;
import com.codejstudio.lim.pojo.attribute.DefaultAttribute;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IAttributable;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IValuable;
import com.codejstudio.lim.pojo.relation.AffiliationRelation;
import com.codejstudio.lim.pojo.relation.CausalityRelation;
import com.codejstudio.lim.pojo.relation.DummyRelation;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.JudgedStatementGroup;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Argument.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Argument.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseValidity",
	"baseCausalityRelation",
	"baseSubArgumentGroup",
	"baseJudgedStatementGroup",
	"baseConditionGroup",
	"baseAttributeGroup",
})
public class Argument extends InformationUnit implements IAttributable, IConditionable, IValuable {

	/* constants */

	private static final long serialVersionUID = -5717576359919138443L;

	public static final String TYPE_NAME = "argument";

	public static final String VALIDITY = "validity";
	public static final String EVIDENCE = "evidence";
	public static final String CONCLUSION = "conclusion";

	public static final char SYNBOL_EVIDENCE = '∵';
	public static final char SYNBOL_CONCLUSION = '∴';

	public static final char SEPARATOR_INSIDE = WordSeparator.COMMA.getCharacter();
	public static final char SEPARATOR_BETWEEN = WordSeparator.SEMICOLON.getCharacter();


	/* variables */

	@XmlElement(name = "validity")
	protected BaseElement baseValidity;

	@XmlElement(name = "causality-relation")
	protected BaseElement baseCausalityRelation;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "sub-argument-group")
	private BaseElement baseSubArgumentGroup;

	private ArgumentGroup subArgumentGroup;

	@XmlElement(name = "judged-statement-group")
	private BaseElement baseJudgedStatementGroup;

	private JudgedStatementGroup judgedStatementGroup;

	@XmlElement(name = "condition-group")
	private BaseElement baseConditionGroup;

	private ConditionGroup conditionGroup;

	@XmlElement(name = "attribute-group")
	private BaseElement baseAttributeGroup;

	private AttributeGroup attributeGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Argument() {
		super();
	}

	public Argument(Argument argument) throws LIMException {
		super(argument);
		load(argument);
	}

	public Argument(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Argument(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}

	public Argument(boolean initIdFlag, boolean initTypeFlag, JudgedStatement conclusion, 
			JudgedStatement... evidences) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConclusionAndEvidence(conclusion, evidences);
	}

	public Argument(boolean initIdFlag, boolean initTypeFlag, JudgedStatement[] conclusionArray, 
			JudgedStatement... evidences) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConclusionAndEvidence(conclusionArray, evidences);
	}

	public Argument(boolean initIdFlag, boolean initTypeFlag, List<JudgedStatement> conclusionList, 
			JudgedStatement... evidences) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConclusionAndEvidence(conclusionList, evidences);
	}


	public Argument(String description) throws LIMException {
		this(true, false, description);
	}

	public Argument(JudgedStatement conclusion, JudgedStatement... evidences) throws LIMException {
		this(true, false, conclusion, evidences);
	}

	public Argument(JudgedStatement[] conclusionArray, JudgedStatement... evidences) 
			throws LIMException {
		this(true, false, conclusionArray, evidences);
	}

	public Argument(List<JudgedStatement> conclusionList, JudgedStatement... evidences) 
			throws LIMException {
		this(true, false, conclusionList, evidences);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(Argument.class);
			JAXBBoundClassConstant.registerBoundClassForInit(Argument.class);
			Argument.registerSubPojoClassForInit(Argument.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initSubArgumentGroup() throws LIMException {
		if (this.subArgumentGroup == null) {
			this.subArgumentGroup = new ArgumentGroup(true);
			super.addInnerElementDelegate(this.subArgumentGroup);
			this.baseSubArgumentGroup = new BaseElement(subArgumentGroup);
		}
	}

	private void initJudgedStatementGroup() throws LIMException {
		if (this.judgedStatementGroup == null) {
			this.judgedStatementGroup = new JudgedStatementGroup(true);
			super.addInnerElementDelegate(this.judgedStatementGroup);
			this.baseJudgedStatementGroup = new BaseElement(judgedStatementGroup);
		}
	}

	private void initConditionGroup() throws LIMException {
		if (this.conditionGroup == null) {
			this.conditionGroup = new ConditionGroup(true);
			super.addInnerElementDelegate(this.conditionGroup);
			this.baseConditionGroup = new BaseElement(conditionGroup);
		}
	}

	private void initAttributeGroup() throws LIMException {
		if (this.attributeGroup == null) {
			this.attributeGroup = new AttributeGroup(true);
			super.addInnerElementDelegate(this.attributeGroup);
			this.baseAttributeGroup = new BaseElement(attributeGroup);
		}
	}


	/* destroyers */

	private void destroySubArgumentGroup() {
		if (this.subArgumentGroup != null && this.subArgumentGroup.size() == 0) {
			this.baseSubArgumentGroup = null;
			super.removeInnerElementDelegate(this.subArgumentGroup.getId());
			this.subArgumentGroup = null;
		}
	}

	private void destroyJudgedStatementGroup() {
		if (this.judgedStatementGroup != null && this.judgedStatementGroup.size() == 0) {
			this.baseJudgedStatementGroup = null;
			super.removeInnerElementDelegate(this.judgedStatementGroup.getId());
			this.judgedStatementGroup = null;
		}
	}

	private void destroyConditionGroup() {
		if (this.conditionGroup != null && this.conditionGroup.size() == 0) {
			this.baseConditionGroup = null;
			super.removeInnerElementDelegate(this.conditionGroup.getId());
			this.conditionGroup = null;
		}
	}

	private void destroyAttributeGroup() {
		if (this.attributeGroup != null && this.attributeGroup.size() == 0) {
			this.baseAttributeGroup = null;
			super.removeInnerElementDelegate(this.attributeGroup.getId());
			this.attributeGroup = null;
		}
	}


	/* getters & setters */

	@Override
	public LogicValue getLogicValue() {
		return getValidity();
	}

	@XmlTransient
	public BaseElement getBaseValidity() {
		return baseValidity;
	}

	public void setBaseValidity(BaseElement baseValidity) {
		this.baseValidity = baseValidity;
	}

	@XmlTransient
	public Validity getValidity() {
		Attribute attribute = getAttributeByKey(VALIDITY);
		return (attribute == null) ? null : (Validity) attribute.getValue();
	}

	public boolean setValidity(final Validity validity) throws LIMException {
		Validity v = getValidity();
		if (Objects.equals(v, validity)) {
			return true;
		}

		boolean flag = true;
		if (v != null) {
			this.baseValidity = null;
			flag &= super.removeInnerElementDelegate(v.getId()) 
					& removeAttributeByKey(VALIDITY);
		}
		if (validity != null) {
			flag &= addIncompatibleAttribute(VALIDITY, validity) 
					& super.addInnerElementDelegate(validity);
			this.baseValidity = new BaseElement(validity);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setValidity(" + validity + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setValidity(" + validity + ")");
		}
		return flag;
	}


	@XmlTransient
	public BaseElement getBaseCausalityRelation() {
		return baseCausalityRelation;
	}

	public void setBaseCausalityRelation(BaseElement baseCausalityRelation) {
		this.baseCausalityRelation = baseCausalityRelation;
	}

	@XmlTransient
	public CausalityRelation getCausalityRelation() {
		return (this.baseCausalityRelation == null) 
				? null : (CausalityRelation) super.getInnerElementDelegate(this.baseCausalityRelation);
	}

	private boolean setCausalityRelation(final CausalityRelation causalityRelation) throws LIMException {
		boolean flag = true;
		if (this.baseCausalityRelation != null && causalityRelation != null) {
			if (!this.baseCausalityRelation.baseEquals(causalityRelation)) {
				flag &= super.replaceInnerElementDelegate(this.baseCausalityRelation, causalityRelation);
				this.baseCausalityRelation = new BaseElement(causalityRelation);
			}
		} else if (this.baseCausalityRelation != null) {
			flag &= super.removeInnerElementDelegate(this.baseCausalityRelation.getId());
			this.baseCausalityRelation = null;
		} else if (causalityRelation != null) {
			this.baseCausalityRelation = new BaseElement(causalityRelation);
			flag &= super.addInnerElementDelegate(this.baseCausalityRelation, causalityRelation);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setCausalityRelation(" 
					+ ((causalityRelation == null) ? null : causalityRelation.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setCausalityRelation(" 
					+ ((causalityRelation == null) ? null : causalityRelation.toBaseString()) + ")");
		}
		return flag;
	}

	private void updateCausalityRelation() throws LIMException {
		if (!containAttributeByKey(EVIDENCE) || !containAttributeByKey(CONCLUSION)) {
			setCausalityRelation(null);
			return;
		}

		JudgedStatementGroup evdg = getEvidenceGroupDelegate(true);
		JudgedStatementGroup ccsg = getConclusionGroupDelegate(true);
		if (GroupableUtil.checkNullOrEmpty(evdg) 
				|| GroupableUtil.checkNullOrEmpty(ccsg)) {
			setCausalityRelation(null);
			return;
		}

		CausalityRelation cr = getCausalityRelation();
		if (cr == null) {
			cr = new CausalityRelation(true);
		}
		cr.setCause(evdg);
		cr.setEffect(ccsg);
		setCausalityRelation(cr);
	}

	private JudgedStatementGroup getEvidenceGroupDelegate(final boolean initFlag) throws LIMException {
		JudgedStatementGroup evdg = getEvidenceGroup();
		if (evdg != null) {
			return evdg;
		}

		Attribute at = getAttributeByKey(EVIDENCE);
		if (at != null) {
			GenericElementGroup vlg = at.getValueGroup();
			if (vlg != null) {
				JudgedStatementGroup jsg = (JudgedStatementGroup)GroupableUtil
						.convertToGroupOfSubClass(JudgedStatement.class, vlg, initFlag);
				return jsg;
			}
		}
		return null;
	}

	private JudgedStatementGroup getConclusionGroupDelegate(final boolean initFlag) throws LIMException {
		JudgedStatementGroup ccsg = getConclusionGroup();
		if (ccsg != null) {
			return ccsg;
		}

		Attribute at = getAttributeByKey(CONCLUSION);
		if (at != null) {
			GenericElementGroup vlg = at.getValueGroup();
			if (vlg != null) {
				JudgedStatementGroup jsg = (JudgedStatementGroup)GroupableUtil
						.convertToGroupOfSubClass(JudgedStatement.class, vlg, initFlag);
				return jsg;
			}
		}
		return null;
	}


	@XmlTransient
	public BaseElement getBaseSubArgumentGroup() {
		return baseSubArgumentGroup;
	}

	public void setBaseSubArgumentGroup(BaseElement baseSubArgumentGroup) {
		this.baseSubArgumentGroup = baseSubArgumentGroup;
	}

	public ArgumentGroup getSubArgumentGroup() {
		return subArgumentGroup;
	}


	@XmlTransient
	public BaseElement getBaseJudgedStatementGroup() {
		return baseJudgedStatementGroup;
	}

	public void setBaseJudgedStatementGroup(BaseElement baseJudgedStatementGroup) {
		this.baseJudgedStatementGroup = baseJudgedStatementGroup;
	}

	public JudgedStatementGroup getJudgedStatementGroup() {
		return judgedStatementGroup;
	}


	@XmlTransient
	public BaseElement getBaseConditionGroup() {
		return baseConditionGroup;
	}

	public void setBaseConditionGroup(BaseElement baseConditionGroup) {
		this.baseConditionGroup = baseConditionGroup;
	}

	@Override
	public ConditionGroup getConditionGroup() {
		return conditionGroup;
	}


	@XmlTransient
	public BaseElement getBaseAttributeGroup() {
		return baseAttributeGroup;
	}

	public void setBaseAttributeGroup(BaseElement baseAttributeGroup) {
		this.baseAttributeGroup = baseAttributeGroup;
	}

	@Override
	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}

	@Override
	public String getDescription() throws LIMException {
		String dsc = super.getDescription();
		return (dsc != null) ? dsc : assembleArgumentDescription(getEvidenceGroup(), getConclusionGroup());
	}

	private String assembleArgumentDescription(final JudgedStatementGroup evidenceGroup, 
			final JudgedStatementGroup conclusionGroup) throws LIMException {
		if (GroupableUtil.checkNullOrEmpty(evidenceGroup) 
				|| GroupableUtil.checkNullOrEmpty(conclusionGroup)) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(SYNBOL_EVIDENCE + " ");
		List<JudgedStatement> evdgigl = evidenceGroup.getInnerGroupList();
		for (JudgedStatement evd : evdgigl) {
			if (evd != null && evd.getDescription() != null) {
				sb.append(evd.getDescription());
				sb.append(SEPARATOR_INSIDE + " ");
			}
		}
		sb.delete(sb.length() - (SEPARATOR_INSIDE + " ").length(), sb.length());
		sb.append(SEPARATOR_BETWEEN + " ");
		sb.append(SYNBOL_CONCLUSION + " ");
		List<JudgedStatement> ccsgigl = conclusionGroup.getInnerGroupList();
		for (JudgedStatement ccs : ccsgigl) {
			if (ccs != null && ccs.getDescription() != null) {
				sb.append(ccs.getDescription());
				sb.append(SEPARATOR_INSIDE + " ");
			}
		}
		sb.delete(sb.length() - (SEPARATOR_INSIDE + " ").length(), sb.length());
		return sb.toString();
	}

	private void updateDynamic() throws LIMException {
		boolean dynamic = false;
		JudgedStatementGroup evdg = getEvidenceGroupDelegate(false);
		if (!GroupableUtil.checkNullOrEmpty(evdg)) {
			List<JudgedStatement> evdgigl = evdg.getInnerGroupList();
			for (JudgedStatement evd : evdgigl) {
				Boolean dn;
				if (evd != null && (dn = evd.getDynamic()) != null && dn) {
					dynamic = true;
				}
			}
		}
		JudgedStatementGroup ccsg = getConclusionGroupDelegate(false);
		if (!GroupableUtil.checkNullOrEmpty(ccsg)) {
			List<JudgedStatement> ccsgigl = ccsg.getInnerGroupList();
			for (JudgedStatement ccs : ccsgigl) {
				Boolean dn;
				if (ccs != null && (dn = ccs.getDynamic()) != null && dn) {
					dynamic = true;
				}
			}
		}
		setDynamic(dynamic);
	}


	/* CRUD for arrays, collections, maps, groups: evidences */

	@XmlTransient
	public JudgedStatementGroup getEvidenceGroup() {
		CausalityRelation cr = getCausalityRelation();
		AbstractRelationableInformationElement cause;
		return (cr == null || (cause = cr.getCause()) == null || !(cause instanceof JudgedStatementGroup)) 
				? null : (JudgedStatementGroup) cause;
	}


	public boolean addEvidence(final JudgedStatement... evidences) throws LIMException {
		return addEvidence((evidences == null) ? null : Arrays.asList(evidences));
	}

	public boolean addEvidence(final List<JudgedStatement> evidenceList) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(evidenceList)) {
			return false;
		}

		try {
			Attribute at = getAttributeByKey(EVIDENCE);
			boolean flag = super.addInnerElementDelegate(evidenceList) 
					& addJudgedStatement(evidenceList) 
					& ((at == null) ? addAttribute(true, new Attribute(EVIDENCE, evidenceList))
							: at.addValue(evidenceList));
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addEvidence(" 
						+ BaseElement.toBaseString(evidenceList) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addEvidence(" 
						+ BaseElement.toBaseString(evidenceList) + ")");
			}
			return flag;
		} finally {
			updateCausalityRelation();
			updateDynamic();
		}
	}

	public boolean removeEvidence(final String id) throws LIMException {
		Attribute at;
		if (id == null || (at = getAttributeByKey(EVIDENCE)) == null 
				|| !at.containValue(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeEvidence(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& removeJudgedStatement(id) 
					& at.removeValue(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeEvidence(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeEvidence(" + id + ")");
			}
			return flag;
		} finally {
			updateCausalityRelation();
			updateDynamic();
		}
	}


	/* CRUD for arrays, collections, maps, groups: conclusions */

	@XmlTransient
	public JudgedStatementGroup getConclusionGroup() {
		CausalityRelation cr = getCausalityRelation();
		AbstractRelationableInformationElement effect;
		return (cr == null || (effect = cr.getEffect()) == null || !(effect instanceof JudgedStatementGroup)) 
				? null : (JudgedStatementGroup) effect;
	}

	public boolean addConclusion(final JudgedStatement... conclusions) throws LIMException {
		return addConclusion((conclusions == null) ? null : Arrays.asList(conclusions));
	}

	public boolean addConclusion(final List<JudgedStatement> conclusionList) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(conclusionList)) {
			return false;
		}

		try {
			Attribute at = getAttributeByKey(CONCLUSION);
			boolean flag = super.addInnerElementDelegate(conclusionList) 
					& addJudgedStatement(conclusionList) 
					& ((at == null) ? addAttribute(true, new Attribute(CONCLUSION, conclusionList))
							: at.addValue(conclusionList));
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addConclusion(" 
						+ BaseElement.toBaseString(conclusionList) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addConclusion(" 
						+ BaseElement.toBaseString(conclusionList) + ")");
			}
			return flag;
		} finally {
			updateCausalityRelation();
			updateDynamic();
		}
	}

	public boolean removeConclusion(final String id) throws LIMException {
		Attribute at;
		if (id == null || (at = getAttributeByKey(CONCLUSION)) == null 
				|| !at.containValue(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeConclusion(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& removeJudgedStatement(id) 
					& at.removeValue(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeConclusion(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeConclusion(" + id + ")");
			}
			return flag;
		} finally {
			updateCausalityRelation();
			updateDynamic();
		}
	}


	/* CRUD for arrays, collections, maps, groups: conclusions & evidences */

	public boolean addConclusionAndEvidence(final JudgedStatement conclusion, 
			final JudgedStatement... evidences) throws LIMException {
		return addEvidence(evidences) & addConclusion(conclusion);
	}

	public boolean addConclusionAndEvidence(final JudgedStatement[] conclusionArray, 
			final JudgedStatement... evidences) throws LIMException {
		return addEvidence(evidences) & addConclusion(conclusionArray);
	}

	public boolean addConclusionAndEvidence(final List<JudgedStatement> conclusionList, 
			final JudgedStatement... evidences) throws LIMException {
		return addEvidence(evidences) & addConclusion(conclusionList);
	}


	/* CRUD for arrays, collections, maps, groups: sub-arguments */

	public boolean containSubArgument(final Argument argument) {
		return (this.subArgumentGroup == null) ? false 
				: this.subArgumentGroup.containGroupElement(argument);
	}

	public boolean containSubArgument(final String id) {
		return (this.subArgumentGroup == null) ? false : this.subArgumentGroup.containGroupElement(id);
	}


	public boolean addSubArgumentWithPosition(final Argument... arguments) throws LIMException {
		return addSubArgument(null, arguments);
	}

	public boolean addSubArgumentWithPosition(final List<Argument> argumentList) throws LIMException {
		return addSubArgument(null, argumentList);
	}

	public boolean addSubArgument(final Integer[] indexArray, final Argument... arguments) 
			throws LIMException {
		return addSubArgument(indexArray, ((arguments == null) ? null : Arrays.asList(arguments)));
	}

	public boolean addSubArgument(final Integer[] indexArray, final List<Argument> argumentList) 
			throws LIMException {
		return addSubArgument(argumentList) 
				& super.putSubElementPositionDelegate(indexArray, 
						(List<Argument>) CollectionUtil.getRelativeComplement(argumentList, this));
	}

	public boolean addSubArgument(final Argument... arguments) throws LIMException {
		return addSubArgument((arguments == null) ? null : Arrays.asList(arguments));
	}

	public boolean addSubArgument(final List<Argument> argumentList) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(argumentList) 
				|| CollectionUtil.onlyContains(argumentList, this)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addSubArgument(" 
					+ BaseElement.toBaseString(argumentList) + ")");
			return false;
		}

		try {
			Collection<Argument> ac = CollectionUtil.getRelativeComplement(argumentList, this);
			initSubArgumentGroup();
			boolean flag = super.addInnerElementDelegate(ac) 
					& this.subArgumentGroup.addGroupElement(ac);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addSubArgument(" 
						+ BaseElement.toBaseString(argumentList) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addSubArgument(" 
						+ BaseElement.toBaseString(argumentList) + ")");
			}
			return flag;
		} finally {
			destroySubArgumentGroup();
		}
	}


	public boolean removeSubArgument(final String id) {
		if (id == null || !containSubArgument(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeSubArgument(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.subArgumentGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeSubArgument(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeSubArgument(" + id + ")");
			}
			return flag;
		} finally {
			destroySubArgumentGroup();
		}
	}


	/* CRUD for arrays, collections, maps, groups: judged-statements */

	public boolean containJudgedStatement(final JudgedStatement statement) {
		return (this.judgedStatementGroup == null) ? false 
				: this.judgedStatementGroup.containGroupElement(statement);
	}

	public boolean containJudgedStatement(final String id) {
		return (this.judgedStatementGroup == null) ? false 
				: this.judgedStatementGroup.containGroupElement(id);
	}


	public boolean addJudgedStatementWithPosition(final JudgedStatement... statements) throws LIMException {
		return addJudgedStatement(null, statements);
	}

	public boolean addJudgedStatementWithPosition(final List<JudgedStatement> statementList) 
			throws LIMException {
		return addJudgedStatement(null, statementList);
	}

	public boolean addJudgedStatement(final Integer[] indexArray, final JudgedStatement... statements) 
			throws LIMException {
		return addJudgedStatement(indexArray, (statements == null) ? null : Arrays.asList(statements));
	}

	public boolean addJudgedStatement(final Integer[] indexArray, 
			final List<JudgedStatement> statementList) throws LIMException {
		return addJudgedStatement(statementList) 
				& super.putSubElementPositionDelegate(indexArray, statementList);
	}

	public boolean addJudgedStatement(final JudgedStatement... statements) throws LIMException {
		return addJudgedStatement((statements == null) ? null : Arrays.asList(statements));
	}

	public boolean addJudgedStatement(final List<JudgedStatement> statementList) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(statementList)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addJudgedStatement(" 
					+ BaseElement.toBaseString(statementList) + ")");
			return false;
		}

		try {
			initJudgedStatementGroup();
			boolean flag = super.addInnerElementDelegate(statementList) 
					& this.judgedStatementGroup.addGroupElement(statementList);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addJudgedStatement(" 
						+ BaseElement.toBaseString(statementList) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addJudgedStatement(" 
						+ BaseElement.toBaseString(statementList) + ")");
			}
			return flag;
		} finally {
			destroyJudgedStatementGroup();
		}
	}


	public boolean removeJudgedStatement(final String id) {
		if (id == null || !containJudgedStatement(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeJudgedStatement(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.judgedStatementGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeJudgedStatement(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeJudgedStatement(" + id + ")");
			}
			return flag;
		} finally {
			destroyJudgedStatementGroup();
		}
	}


	/* CRUD for arrays, collections, maps, groups: conditions */

	@Override
	public boolean containCondition(final Condition condition) {
		return (this.conditionGroup == null) ? false : this.conditionGroup.containGroupElement(condition);
	}

	@Override
	public boolean containCondition(final String id) {
		return (this.conditionGroup == null) ? false : this.conditionGroup.containGroupElement(id);
	}

	@Override
	public boolean addCondition(final Condition... conditions) throws LIMException {
		return addCondition((conditions == null) ? null : Arrays.asList(conditions));
	}

	@Override
	public boolean addCondition(final Collection<Condition> conditionCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(conditionCollection)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addCondition(" 
					+ BaseElement.toBaseString(conditionCollection) + ")");
			return false;
		}

		try {
			initConditionGroup();
			boolean flag = super.addInnerElementDelegate(conditionCollection) 
					& this.conditionGroup.addGroupElement(conditionCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addCondition(" 
						+ BaseElement.toBaseString(conditionCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addCondition(" 
						+ BaseElement.toBaseString(conditionCollection) + ")");
			}
			return flag;
		} finally {
			destroyConditionGroup();
		}
	}

	@Override
	public boolean removeCondition(final String id) {
		if (id == null || !containCondition(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeCondition(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.conditionGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeCondition(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeCondition(" + id + ")");
			}
			return flag;
		} finally {
			destroyConditionGroup();
		}
	}


	/* CRUD for arrays, collections, maps, groups: attributes */

	@Override
	public boolean containAttributeByKey(final String key) {
		return (this.attributeGroup == null) ? false : this.attributeGroup.containAttributeByKey(key);
	}

	@Override
	public boolean containAttribute(final Attribute attribute) {
		return (this.attributeGroup == null) ? false : this.attributeGroup.containAttribute(attribute);
	}

	@Override
	public boolean containAttribute(final String id) {
		return (this.attributeGroup == null) ? false : this.attributeGroup.containAttribute(id);
	}


	@Override
	public BaseElement getBaseAttributeByKey(final String key) {
		return (this.attributeGroup == null) ? null : this.attributeGroup.getBaseAttributeByKey(key);
	}

	@Override
	public BaseElement getBaseAttribute(final Attribute attribute) {
		return (this.attributeGroup == null) ? null : this.attributeGroup.getBaseAttribute(attribute);
	}

	@Override
	public BaseElement getBaseAttribute(final String id) {
		return (this.attributeGroup == null) ? null : this.attributeGroup.getBaseAttribute(id);
	}


	@Override
	public Attribute getAttributeByKey(final String key) {
		return (this.attributeGroup == null) ? null : this.attributeGroup.getAttributeByKey(key);
	}

	@Override
	public Attribute getAttribute(final String key, final GenericElement value) {
		Attribute at = getAttributeByKey(key);
		return (at == null || !at.containValue(value)) ? null : at;
	}

	@Override
	public Attribute getAttribute(final Attribute attribute) {
		return (this.attributeGroup == null) ? null : this.attributeGroup.getAttribute(attribute);
	}

	@Override
	public Attribute getAttribute(final String id) {
		return (this.attributeGroup == null) ? null : this.attributeGroup.getAttribute(id);
	}


	@Override
	public boolean addIncompatibleAttribute(final String key, final GenericElement value) 
			throws LIMException {
		return addAttribute(false, new Attribute(key, value));
	}

	@Override
	public boolean addCompatibleAttribute(final String key, final GenericElement... values) 
			throws LIMException {
		Attribute at;
		return (key == null) ? false 
				: (((at = getAttributeByKey(key)) != null) 
						? at.addValue(values) : addAttribute(true, new Attribute(key, values)));
	}

	@Override
	public boolean addDefaultAttribute(final GenericElement... values) throws LIMException {
		return addDefaultAttribute(new DefaultAttribute(values));
	}

	@Override
	public boolean addDefaultAttribute(final DefaultAttribute attribute) throws LIMException {
		return addAttribute(true, attribute);
	}

	@Override
	public boolean addAttribute(final boolean compatibleFlag, final Attribute... attributes) 
			throws LIMException {
		return addAttribute(compatibleFlag, ((attributes == null) ? null : Arrays.asList(attributes)));
	}

	@Override
	public boolean addAttribute(final boolean compatibleFlag, 
			final Collection<Attribute> attributeCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(attributeCollection)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addAttribute(" 
					+ BaseElement.toBaseString(attributeCollection) + ")");
			return false;
		}

		try {
			initAttributeGroup();
			boolean flag = true;
			for (Attribute at : attributeCollection) {
				Attribute containedAt = this.attributeGroup.getAttribute(at);
				if (containedAt != null) {
					if (containedAt.equals(at)) {
						continue;
					}
					if (compatibleFlag || at instanceof DefaultAttribute) {
						flag &= containedAt.addValue(at);
					} else {
						flag &= super.replaceInnerElementDelegate(containedAt, at) 
								& this.attributeGroup.putAttribute(at) 
								& super.replaceInnerElementDelegate(
										new DummyRelation(AffiliationRelation.class, this, containedAt), 
										new AffiliationRelation(this, at));
					}
				} else if (at != null) {
					flag &= super.addInnerElementDelegate(at) 
							& this.attributeGroup.putAttribute(at) 
							& super.addInnerElementDelegate(new AffiliationRelation(this, at));
				}
			}
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addAttribute(" 
						+ BaseElement.toBaseString(attributeCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addAttribute(" 
						+ BaseElement.toBaseString(attributeCollection) + ")");
			}
			return flag;
		} finally {
			destroyAttributeGroup();
		}
	}


	@Override
	public boolean removeAttributeByKey(final String key) throws LIMException {
		return removeAttribute(getBaseAttributeByKey(key));
	}

	@Override
	public boolean removeAttribute(final BaseElement baseAttribute) throws LIMException {
		return (baseAttribute == null || (!(baseAttribute instanceof Attribute) 
						&& !baseAttribute.getClass().equals(BaseElement.class))) 
				? false : removeAttribute(baseAttribute.getId()); 
	}

	@Override
	public boolean removeAttribute(final String key, final GenericElement value) throws LIMException {
		Attribute at = getAttribute(key, value);
		return (at == null) ? false 
				: ((at.getCompatible() == null || !at.getCompatible()) 
						? removeAttribute(at.getId()) : at.removeValue(value.getId()));
	}

	@Override
	public boolean removeAttribute(final String id) throws LIMException {
		if (id == null || !containAttribute(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeAttribute(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.attributeGroup.removeAttribute(id) 
					& super.removeInnerElementDelegate(
							new DummyRelation(AffiliationRelation.class, this, id, Attribute.class));
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeAttribute(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeAttribute(" + id + ")");
			}
			return flag;
		} finally {
			destroyAttributeGroup();
		}
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(Argument.class) ? this : new Argument(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(Argument.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof Argument) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((Argument) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final Argument element) {
		if (element != null) {
			this.baseValidity = element.baseValidity;
			this.baseCausalityRelation = element.baseCausalityRelation;
			this.baseSubArgumentGroup = element.baseSubArgumentGroup;
			this.subArgumentGroup = element.subArgumentGroup;
			this.baseJudgedStatementGroup = element.baseJudgedStatementGroup;
			this.judgedStatementGroup = element.judgedStatementGroup;
			this.baseConditionGroup = element.baseConditionGroup;
			this.conditionGroup = element.conditionGroup;
			this.baseAttributeGroup = element.baseAttributeGroup;
			this.attributeGroup = element.attributeGroup;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseCausalityRelation != null && this.baseCausalityRelation.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseCausalityRelation.getId());
			super.addInnerElementDelegate(this.baseCausalityRelation, ge);
		}
		if (this.baseSubArgumentGroup != null && this.baseSubArgumentGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseSubArgumentGroup.getId());
			this.subArgumentGroup = (ge instanceof ArgumentGroup) 
					? (ArgumentGroup) ge : this.subArgumentGroup;
			super.addInnerElementDelegate(this.subArgumentGroup);
		}
		if (this.baseJudgedStatementGroup != null && this.baseJudgedStatementGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseJudgedStatementGroup.getId());
			this.judgedStatementGroup = (ge instanceof JudgedStatementGroup) 
					? (JudgedStatementGroup) ge : this.judgedStatementGroup;
			super.addInnerElementDelegate(this.judgedStatementGroup);
		}
		if (this.baseConditionGroup != null && this.baseConditionGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseConditionGroup.getId());
			this.conditionGroup = (ge instanceof ConditionGroup) 
					? (ConditionGroup) ge : this.conditionGroup;
			super.addInnerElementDelegate(this.conditionGroup);
		}
		if (this.baseAttributeGroup != null && this.baseAttributeGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseAttributeGroup.getId());
			this.attributeGroup = (ge instanceof AttributeGroup) 
					? (AttributeGroup) ge : this.attributeGroup;
			super.addInnerElementDelegate(this.attributeGroup);
		}
	}


	@Override
	public Argument cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(Argument.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (Argument) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Argument clonedElement = (Argument) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Argument cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Argument) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Argument)) 
				? null : cloneToElement((Argument) ce, null);
	}

	private Argument cloneToElement(final Argument clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.baseValidity = (this.baseValidity != null) 
				? (BaseElement) this.baseValidity.cloneElement(clonedElementMap) 
				: clonedElement.baseValidity;

		clonedElement.baseCausalityRelation = (this.baseCausalityRelation != null) 
				? (BaseElement) this.baseCausalityRelation.cloneElement(clonedElementMap) 
				: clonedElement.baseCausalityRelation;

		clonedElement.baseSubArgumentGroup = (this.baseSubArgumentGroup != null) 
				? (BaseElement) this.baseSubArgumentGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseSubArgumentGroup;
		clonedElement.subArgumentGroup = (this.subArgumentGroup != null) 
				? (ArgumentGroup) this.subArgumentGroup.cloneElement(clonedElementMap) 
				: clonedElement.subArgumentGroup;

		clonedElement.baseJudgedStatementGroup = (this.baseJudgedStatementGroup != null) 
				? (BaseElement) this.baseJudgedStatementGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseJudgedStatementGroup;
		clonedElement.judgedStatementGroup = (this.judgedStatementGroup != null) 
				? (JudgedStatementGroup) this.judgedStatementGroup.cloneElement(clonedElementMap) 
				: clonedElement.judgedStatementGroup;

		clonedElement.baseConditionGroup = (this.baseConditionGroup != null) 
				? (BaseElement) this.baseConditionGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseConditionGroup;
		clonedElement.conditionGroup = (this.conditionGroup != null) 
				? (ConditionGroup) this.conditionGroup.cloneElement(clonedElementMap) 
				: clonedElement.conditionGroup;

		clonedElement.baseAttributeGroup = (this.baseAttributeGroup != null) 
				? (BaseElement) this.baseAttributeGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseAttributeGroup;
		clonedElement.attributeGroup = (this.attributeGroup != null) 
				? (AttributeGroup) this.attributeGroup.cloneElement(clonedElementMap) 
				: clonedElement.attributeGroup;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (Argument.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
