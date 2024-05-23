package com.codejstudio.lim.pojo.concept;

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
import com.codejstudio.lim.pojo.GenericElementGroup;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.attribute.AttributeGroup;
import com.codejstudio.lim.pojo.attribute.DefaultAttribute;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IAttributable;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.relation.AffiliationRelation;
import com.codejstudio.lim.pojo.relation.DummyRelation;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Concept.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Concept.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseSubConceptGroup",
	"baseConditionGroup",
	"baseAttributeGroup",
})
public class Concept extends InformationUnit implements IAttributable, IConditionable {

	/* constants */

	private static final long serialVersionUID = 2517650639428746608L;

	public static final String TYPE_NAME = "concept";


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "sub-concept-group")
	private BaseElement baseSubConceptGroup;

	private ConceptGroup subConceptGroup;

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
	public Concept() {
		super();
	}

	public Concept(Concept concept) throws LIMException {
		super(concept);
		load(concept);
	}

	public Concept(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Concept(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public Concept(String description) throws LIMException {
		super(true, false, description);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(Concept.class);
			JAXBBoundClassConstant.registerBoundClassForInit(Concept.class);
			Concept.registerSubPojoClassForInit(Concept.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initSubConceptGroup() throws LIMException {
		if (this.subConceptGroup == null) {
			this.subConceptGroup = new ConceptGroup(true);
			super.addInnerElementDelegate(this.subConceptGroup);
			this.baseSubConceptGroup = new BaseElement(subConceptGroup);
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

	private void destroySubConceptGroup() {
		if (this.subConceptGroup != null && this.subConceptGroup.size() == 0) {
			this.baseSubConceptGroup = null;
			super.removeInnerElementDelegate(this.subConceptGroup.getId());
			this.subConceptGroup = null;
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
	public BaseElement getBaseSubConceptGroup() {
		return baseSubConceptGroup;
	}

	public void setBaseSubConceptGroup(BaseElement baseSubConceptGroup) {
		this.baseSubConceptGroup = baseSubConceptGroup;
	}

	public ConceptGroup getSubConceptGroup() {
		return subConceptGroup;
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


	/* CRUD for arrays, collections, maps, groups: sub-concepts */

	public boolean containSubConcept(final Concept concept) {
		return (this.subConceptGroup == null) ? false : this.subConceptGroup.containGroupElement(concept);
	}

	public boolean containSubConcept(final String id) {
		return (this.subConceptGroup == null) ? false : this.subConceptGroup.containGroupElement(id);
	}


	public boolean addSubConceptWithPosition(final Concept... concepts) throws LIMException {
		return addSubConcept(null, concepts);
	}

	public boolean addSubConceptWithPosition(final List<Concept> conceptList) throws LIMException {
		return addSubConcept(null, conceptList);
	}

	public boolean addSubConcept(final Integer[] indexArray, final Concept... concepts) throws LIMException {
		return addSubConcept(indexArray, ((concepts == null) ? null : Arrays.asList(concepts)));
	}

	public boolean addSubConcept(final Integer[] indexArray, final List<Concept> conceptList) 
			throws LIMException {
		return addSubConcept(conceptList) 
				& super.putSubElementPositionDelegate(indexArray, 
						(List<Concept>) CollectionUtil.getRelativeComplement(conceptList, this));
	}

	public boolean addSubConcept(final Concept... concepts) throws LIMException {
		return addSubConcept((concepts == null) ? null : Arrays.asList(concepts));
	}

	public boolean addSubConcept(final List<Concept> conceptList) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(conceptList) 
				|| CollectionUtil.onlyContains(conceptList, this)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addSubConcept(" 
					+ BaseElement.toBaseString(conceptList) + ")");
			return false;
		}

		try {
			Collection<Concept> cc = CollectionUtil.getRelativeComplement(conceptList, this);
			initSubConceptGroup();
			boolean flag = super.addInnerElementDelegate(cc) 
					& this.subConceptGroup.addGroupElement(cc);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addSubConcept(" 
						+ BaseElement.toBaseString(conceptList) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addSubConcept(" 
						+ BaseElement.toBaseString(conceptList) + ")");
			}
			return flag;
		} finally {
			destroySubConceptGroup();
		}
	}


	public boolean removeSubConcept(final String id) {
		if (id == null || !containSubConcept(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeSubConcept(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.subConceptGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeSubConcept(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeSubConcept(" + id + ")");
			}
			return flag;
		} finally {
			destroySubConceptGroup();
		}
	}


	/* CRUD for arrays, collections, maps, groups: conditions */

	@Override
	public boolean containCondition(final Condition condition) throws LIMException {
		return (this.conditionGroup == null) ? false 
				: (this.conditionGroup.containGroupElement(condition) ? true 
						: (!(condition instanceof AttributeCondition) ? false 
								: containAttributeCondition((AttributeCondition) condition)));
	}

	@Override
	public boolean containCondition(final String id) {
		return (this.conditionGroup == null) ? false : this.conditionGroup.containGroupElement(id);
	}

	public boolean containAttributeCondition(final AttributeCondition condition) throws LIMException {
		return (this.conditionGroup == null) ? false 
				: this.conditionGroup.containAttributeCondition(condition);
	}

	public boolean addCondition(final DefaultAttribute attribute) throws LIMException {
		GenericElementGroup vlg;
		if (attribute == null || GroupableUtil.checkNullOrEmpty(vlg = attribute.getValueGroup())) {
			return false;
		}

		boolean flag = true;
		List<GenericElement> vlgigl = vlg.getInnerGroupList();
		for (GenericElement ge : vlgigl) {
			flag &= addCondition(new AttributeCondition(ge));
		}
		return flag;
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
		return addAttribute(true, attribute) & addCondition(attribute);
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
		return removeAttribute(getBaseAttributeByKey(key).getId());
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
			this.xmlElement = this.getClass().equals(Concept.class) ? this : new Concept(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(Concept.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof Concept) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((Concept) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final Concept element) {
		if (element != null) {
			this.baseSubConceptGroup = element.baseSubConceptGroup;
			this.subConceptGroup = element.subConceptGroup;
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

		if (this.baseSubConceptGroup != null && this.baseSubConceptGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseSubConceptGroup.getId());
			this.subConceptGroup = (ge instanceof ConceptGroup) 
					? (ConceptGroup) ge : this.subConceptGroup;
			super.addInnerElementDelegate(this.subConceptGroup);
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
	public Concept cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(Concept.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (Concept) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Concept clonedElement = (Concept) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Concept cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Concept) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Concept)) 
				? null : cloneToElement((Concept) ce, null);
	}

	private Concept cloneToElement(final Concept clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.baseSubConceptGroup = (this.baseSubConceptGroup != null) 
				? (BaseElement) this.baseSubConceptGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseSubConceptGroup;
		clonedElement.subConceptGroup = (this.subConceptGroup != null) 
				? (ConceptGroup) this.subConceptGroup.cloneElement(clonedElementMap) 
				: clonedElement.subConceptGroup;

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
		if (Concept.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
