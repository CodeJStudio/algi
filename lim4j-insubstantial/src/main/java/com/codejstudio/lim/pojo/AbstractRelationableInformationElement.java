package com.codejstudio.lim.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IRelationable;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.relation.RelationGroup;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AbstractRelationableInformationElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = AbstractRelationableInformationElement.TYPE_NAME, propOrder = {
	"baseRelationGroup",
})
public abstract class AbstractRelationableInformationElement extends OwnableInformationElement 
		implements IRelationable {

	/* constants */

	private static final long serialVersionUID = -3980911963073860705L;

	public static final String TYPE_NAME = "relationable-information-element";


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "relation-group")
	private BaseElement baseRelationGroup;

	private RelationGroup relationGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AbstractRelationableInformationElement() {
		super();
	}

	public AbstractRelationableInformationElement(AbstractRelationableInformationElement element) 
			throws LIMException {
		super(element);
		this.load(element);
	}

	public AbstractRelationableInformationElement(boolean initIdFlag, boolean initTypeFlag) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(
					AbstractRelationableInformationElement.class);
			AbstractRelationableInformationElement.registerSubPojoClassForInit(
					AbstractRelationableInformationElement.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initRelationGroup() throws LIMException {
		if (this.relationGroup == null) {
			this.relationGroup = new RelationGroup(true);
			super.addInnerElementDelegate(this.relationGroup);
			this.baseRelationGroup = new BaseElement(relationGroup);
		}
	}


	/* destroyers */

	private void destroyRelationGroup() {
		if (this.relationGroup != null && this.relationGroup.size() == 0) {
			this.baseRelationGroup = null;
			super.removeInnerElementDelegate(this.relationGroup.getId());
			this.relationGroup = null;
		}
	}


	/* getters & setters */

	@XmlTransient
	public BaseElement getBaseRelationGroup() {
		return baseRelationGroup;
	}

	public void setBaseRelationGroup(BaseElement baseRelationGroup) {
		this.baseRelationGroup = baseRelationGroup;
	}

	@Override
	public RelationGroup getRelationGroup() {
		return relationGroup;
	}


	/* CRUD for arrays, collections, maps, groups: relations */

	@Override
	public boolean containRelation(final BaseRelation relation) {
		return (this.relationGroup == null) ? false : this.relationGroup.containGroupElement(relation);
	}

	@Override
	public boolean containRelation(final String id) {
		return (this.relationGroup == null) ? false : this.relationGroup.containGroupElement(id);
	}

	@Override
	public boolean addRelation(final BaseRelation... relations) throws LIMException {
		return addRelation((relations == null) ? null : Arrays.asList(relations));
	}

	@Override
	public boolean addRelation(final Collection<BaseRelation> relationCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(relationCollection)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addRelation(" 
					+ BaseElement.toBaseString(relationCollection) + ")");
			return false;
		}

		try {
			initRelationGroup();
			boolean flag = super.addInnerElementDelegate(relationCollection) 
					& this.relationGroup.addGroupElement(relationCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addRelation(" 
						+ BaseElement.toBaseString(relationCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addRelation(" 
						+ BaseElement.toBaseString(relationCollection) + ")");
			}
			return flag;
		} finally {
			destroyRelationGroup();
		}
	}

	@Override
	public boolean removeRelation(final String id) {
		if (id == null || !containRelation(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeRelation(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.relationGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeRelation(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeRelation(" + id + ")");
			}
			return flag;
		} finally {
			destroyRelationGroup();
		}
	}


	/* overridden methods */

	@Override
	public boolean equivalentTo(final IRelationable relationable) throws LIMException {
		List<BaseRelation> rgigl;
		if (relationable == null || this.relationGroup == null 
				|| (rgigl = this.relationGroup.getInnerGroupList()) == null) {
			return false;
		}

		for (BaseRelation br : rgigl) {
			IRelationable ae;
			if (br instanceof EquivalenceRelation && (ae = br.getAnotherElement(this)) != null 
					&& ae.equals(relationable)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof AbstractRelationableInformationElement) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((AbstractRelationableInformationElement) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final AbstractRelationableInformationElement element) {
		if (element != null) {
			this.baseRelationGroup = element.baseRelationGroup;
			this.relationGroup = element.relationGroup;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseRelationGroup != null && this.baseRelationGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseRelationGroup.getId());
			this.relationGroup = (ge instanceof RelationGroup) 
					? (RelationGroup) ge : this.relationGroup;
			super.addInnerElementDelegate(this.relationGroup);
		}
	}


	@Override
	public AbstractRelationableInformationElement cloneElement(
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(AbstractRelationableInformationElement.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (AbstractRelationableInformationElement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		AbstractRelationableInformationElement clonedElement 
			= (AbstractRelationableInformationElement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public AbstractRelationableInformationElement cloneToElement(final GenericElement clonedElement) 
			throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof AbstractRelationableInformationElement) 
				|| !((ce = super.cloneToElement(clonedElement)) instanceof AbstractRelationableInformationElement)) 
			? null : cloneToElement((AbstractRelationableInformationElement) ce, null);
	}

	private AbstractRelationableInformationElement cloneToElement(
			final AbstractRelationableInformationElement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.baseRelationGroup = (this.baseRelationGroup != null) 
				? (BaseElement) this.baseRelationGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseRelationGroup;
		clonedElement.relationGroup = (this.relationGroup != null) 
				? (RelationGroup) this.relationGroup.cloneElement(clonedElementMap) 
				: clonedElement.relationGroup;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (AbstractRelationableInformationElement.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
