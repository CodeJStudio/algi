package com.codejstudio.lim.pojo.statement;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.attribute.AttributeGroup;
import com.codejstudio.lim.pojo.attribute.DefaultAttribute;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.concept.ConceptGroup;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IAttributable;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IDynamicable;
import com.codejstudio.lim.pojo.relation.AffiliationRelation;
import com.codejstudio.lim.pojo.relation.DummyRelation;
import com.codejstudio.lim.pojo.util.AbstractDynamicSymbol;
import com.codejstudio.lim.pojo.util.DynamicInformation;
import com.codejstudio.lim.pojo.util.DynamicableUtil;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Statement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Statement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseConceptGroup",
	"baseConditionGroup",
	"baseAttributeGroup",
})
public class Statement extends InformationUnit implements IAttributable, IDynamicable, IConditionable {

	/* constants */

	private static final long serialVersionUID = -3694031720304732356L;

	public static final String TYPE_NAME = "statement";


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "concept-group")
	private BaseElement baseConceptGroup;

	private ConceptGroup conceptGroup;

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
	public Statement() {
		super();
	}

	public Statement(Statement statement) throws LIMException {
		super(statement);
		load(statement);
	}

	public Statement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Statement(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public Statement(String description) throws LIMException {
		this(true, false, description);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(Statement.class);
			JAXBBoundClassConstant.registerBoundClassForInit(Statement.class);
			Statement.registerSubPojoClassForInit(Statement.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initConceptGroup() throws LIMException {
		if (this.conceptGroup == null) {
			this.conceptGroup = new ConceptGroup(true);
			super.addInnerElementDelegate(this.conceptGroup);
			this.baseConceptGroup = new BaseElement(conceptGroup);
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

	private void destroyConceptGroup() {
		if (this.conceptGroup != null && this.conceptGroup.size() == 0) {
			this.baseConceptGroup = null;
			super.removeInnerElementDelegate(this.conceptGroup.getId());
			this.conceptGroup = null;
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

	@XmlTransient
	public BaseElement getBaseConceptGroup() {
		return baseConceptGroup;
	}

	public void setBaseConceptGroup(BaseElement baseConceptGroup) {
		this.baseConceptGroup = baseConceptGroup;
	}

	public ConceptGroup getConceptGroup() {
		return conceptGroup;
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


	/* CRUD for arrays, collections, maps, groups: concepts */

	public boolean containConcept(final Concept concept) {
		return (this.conceptGroup == null) ? false : this.conceptGroup.containGroupElement(concept);
	}

	public boolean containConcept(final String id) {
		return (this.conceptGroup == null) ? false : this.conceptGroup.containGroupElement(id);
	}


	public boolean addConceptWithPosition(final Concept... concepts) throws LIMException {
		return addConcept(null, concepts);
	}

	public boolean addConceptWithPosition(final List<Concept> conceptList) throws LIMException {
		return addConcept(null, conceptList);
	}

	public boolean addConcept(final Integer[] indexArray, final Concept... concepts) throws LIMException {
		return addConcept(indexArray, ((concepts == null) ? null : Arrays.asList(concepts)));
	}

	public boolean addConcept(final Integer[] indexArray, final List<Concept> conceptList) 
			throws LIMException {
		return addConcept(conceptList) 
				& super.putSubElementPositionDelegate(indexArray, conceptList);
	}

	public boolean addConcept(final Concept... concepts) throws LIMException {
		return addConcept((concepts == null) ? null : Arrays.asList(concepts));
	}

	public boolean addConcept(final List<Concept> conceptList) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(conceptList)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addConcept(" 
					+ BaseElement.toBaseString(conceptList) + ")");
			return false;
		}

		try {
			initConceptGroup();
			boolean flag = super.addInnerElementDelegate(conceptList) 
					& this.conceptGroup.addGroupElement(conceptList);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addConcept(" 
						+ BaseElement.toBaseString(conceptList) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addConcept(" 
						+ BaseElement.toBaseString(conceptList) + ")");
			}
			return flag;
		} finally {
			destroyConceptGroup();
		}
	}


	public boolean removeConcept(final String id) {
		if (id == null || !containConcept(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeConcept(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.conceptGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeConcept(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeConcept(" + id + ")");
			}
			return flag;
		} finally {
			destroyConceptGroup();
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
	public String substituteStaticInputsForDynamic(final DynamicParameterType[] typeArray, 
			final Object[] inputArray) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(typeArray) || CollectionUtil.checkNullOrEmpty(inputArray) 
				) {
			return null;
		}

		Boolean dn = super.getDynamic();
		DynamicInformation di;
		String[] pha;
		if (dn == null || !dn || (di = super.getDynamicInformation()) == null 
				|| (pha = di.getPlaceholderArray()) == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0, j = 0; i < pha.length; i++) {
			if (!pha[i].equals(AbstractDynamicSymbol.DYNAMIC_PLACEHOLDER)) {
				sb.append(pha[i]);
			} else if (j < inputArray.length && DynamicParameterType.INPUT.equals(typeArray[j])) {
				String append;
				sb.append((inputArray[j] instanceof Concept 
								&& (append = ((Concept) inputArray[j]).getDescription()) != null) 
						? append : inputArray[j]);
				j++;
			} else {
				sb.append(AbstractDynamicSymbol.DYNAMIC_PLACEHOLDER);
			}
		}
		return sb.toString();
	}

	@Override
	public String substituteDynamicIdentifier(final String[] inputArray) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(inputArray)) {
			return null;
		}

		Boolean dn = super.getDynamic();
		DynamicInformation di;
		String[] afa;
		if (dn == null || !dn || (di = super.getDynamicInformation()) == null 
				|| (afa = di.getAllFragmentArray()) == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0, j = 0; i < afa.length; i++) {
			if (j < inputArray.length && AbstractDynamicSymbol.verifySingleDynamicSymbol(afa[i])) {
				if (DynamicableUtil.INPUT_DYNAMIC_SYMBOL.verifySingleSymbol(afa[i])) {
					sb.append(DynamicableUtil.INPUT_DYNAMIC_SYMBOL.packageDynamicSymbol(inputArray[j]));
				} else if (DynamicableUtil.OUTPUT_DYNAMIC_SYMBOL.verifySingleSymbol(afa[i])) {
					sb.append(DynamicableUtil.OUTPUT_DYNAMIC_SYMBOL.packageDynamicSymbol(inputArray[j]));
				}
				j++;
			} else {
				sb.append(afa[i]);
			}
		}
		return sb.toString();
	}


	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(Statement.class) ? this : new Statement(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(Statement.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof Statement) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((Statement) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final Statement element) {
		if (element != null) {
			this.baseConceptGroup = element.baseConceptGroup;
			this.conceptGroup = element.conceptGroup;
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

		if (this.baseConceptGroup != null && this.baseConceptGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseConceptGroup.getId());
			this.conceptGroup = (ge instanceof ConceptGroup) 
					? (ConceptGroup) ge : this.conceptGroup;
			super.addInnerElementDelegate(this.conceptGroup);
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
	public Statement cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(Statement.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (Statement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Statement clonedElement = (Statement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Statement cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Statement) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Statement)) 
				? null : cloneToElement((Statement) ce, null);
	}

	private Statement cloneToElement(final Statement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.baseConceptGroup = (this.baseConceptGroup != null) 
				? (BaseElement) this.baseConceptGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseConceptGroup;
		clonedElement.conceptGroup = (this.conceptGroup != null) 
				? (ConceptGroup) this.conceptGroup.cloneElement(clonedElementMap) 
				: clonedElement.conceptGroup;

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
		if (Statement.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
