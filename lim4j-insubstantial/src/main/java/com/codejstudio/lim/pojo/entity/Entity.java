package com.codejstudio.lim.pojo.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.OwnableInformationElementGroup;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.role.Proposer;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Entity.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Entity.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseProposedElementGroup",
	"baseObservedElementGroup",
})
public class Entity extends GenericElement {

	/* constants */

	private static final long serialVersionUID = 6991214627634073080L;

	public static final String TYPE_NAME = "entity";


	/* variables */

	@XmlAttribute
	private String name;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "proposed-element-group")
	private BaseElement baseProposedElementGroup;

	private OwnableInformationElementGroup proposedElementGroup;

	@XmlElement(name = "observed-element-group")
	private BaseElement baseObservedElementGroup;

	private OwnableInformationElementGroup observedElementGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Entity() {
		super();
	}

	public Entity(Entity entity) throws LIMException {
		super(entity);
		load(entity);
	}

	public Entity(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Entity(boolean initIdFlag, boolean initTypeFlag, String name) throws LIMException {
		super(initIdFlag, initTypeFlag);
		setName(name);
	}


	public Entity(String name) throws LIMException {
		this(true, false, name);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(Entity.class);
			JAXBBoundClassConstant.registerBoundClassForInit(Entity.class);
			Entity.registerSubPojoClassForInit(Entity.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initProposedElementGroup() throws LIMException {
		if (this.proposedElementGroup == null) {
			this.proposedElementGroup = new OwnableInformationElementGroup(true);
			super.addInnerElementDelegate(this.proposedElementGroup);
			this.baseProposedElementGroup = new BaseElement(proposedElementGroup);
		}
	}

	private void initObservedElementGroup() throws LIMException {
		if (this.observedElementGroup == null) {
			this.observedElementGroup = new OwnableInformationElementGroup(true);
			super.addInnerElementDelegate(this.observedElementGroup);
			this.baseObservedElementGroup = new BaseElement(observedElementGroup);
		}
	}


	/* destroyers */

	private void destroyProposedElementGroup() {
		if (this.proposedElementGroup != null && this.proposedElementGroup.size() == 0) {
			this.baseProposedElementGroup = null;
			super.removeInnerElementDelegate(this.proposedElementGroup.getId());
			this.proposedElementGroup = null;
		}
	}

	private void destroyObservedElementGroup() {
		if (this.observedElementGroup != null && this.observedElementGroup.size() == 0) {
			this.baseObservedElementGroup = null;
			super.removeInnerElementDelegate(this.observedElementGroup.getId());
			this.observedElementGroup = null;
		}
	}


	/* getters & setters */

	@XmlTransient
	public String getName() {
		return name;
	}

	public void setName(final String name) throws LIMException {
		this.name = name;
		super.updateDynamicInformationByDescription(name);
		ElementTrace.log.info(this.toBaseString() + ": setName(\"" + name + "\")");
	}


	@XmlTransient
	public BaseElement getBaseProposedElementGroup() {
		return baseProposedElementGroup;
	}

	public void setBaseProposedElementGroup(BaseElement baseProposedElementGroup) {
		this.baseProposedElementGroup = baseProposedElementGroup;
	}

	public OwnableInformationElementGroup getProposedElementGroup() {
		return proposedElementGroup;
	}


	@XmlTransient
	public BaseElement getBaseObservedElementGroup() {
		return baseObservedElementGroup;
	}

	public void setBaseObservedElementGroup(BaseElement baseObservedElementGroup) {
		this.baseObservedElementGroup = baseObservedElementGroup;
	}

	public OwnableInformationElementGroup getObservedElementGroup() {
		return observedElementGroup;
	}


	/* CRUD for arrays, collections, maps, groups: proposed elements */

	public boolean containProposedElement(final OwnableInformationElement element) {
		return (this.proposedElementGroup == null) 
				? false : this.proposedElementGroup.containGroupElement(element);
	}

	public boolean containProposedElement(final String id) {
		return (this.proposedElementGroup == null) 
				? false : this.proposedElementGroup.containGroupElement(id);
	}

	public boolean addProposedElement(final OwnableInformationElement... elements) throws LIMException {
		return addProposedElement((elements == null) ? null : Arrays.asList(elements));
	}

	public boolean addProposedElement(final Collection<OwnableInformationElement> elementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return false;
		}

		try {
			initProposedElementGroup();
			boolean flag = super.addInnerElementDelegate(elementCollection) 
					& this.proposedElementGroup.addGroupElement(elementCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addProposedElement(" 
						+ BaseElement.toBaseString(elementCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addProposedElement(" 
						+ BaseElement.toBaseString(elementCollection) + ")");
			}
			return flag;
		} finally {
			destroyProposedElementGroup();
		}
	}

	public boolean removeProposedElement(final String id) {
		if (id == null || !containProposedElement(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeProposedElement(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.proposedElementGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeProposedElement(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeProposedElement(" + id + ")");
			}
			return flag;
		} finally {
			destroyProposedElementGroup();
		}
	}


	/* CRUD for arrays, collections, maps, groups: observed elements */

	public boolean containObservedElement(final OwnableInformationElement element) {
		return (this.observedElementGroup == null) 
				? false : this.observedElementGroup.containGroupElement(element);
	}

	public boolean containObservedElement(final String id) {
		return (this.observedElementGroup == null) 
				? false : this.observedElementGroup.containGroupElement(id);
	}

	public boolean addObservedElement(final OwnableInformationElement... elements) throws LIMException {
		return addObservedElement((elements == null) ? null : Arrays.asList(elements));
	}

	public boolean addObservedElement(final Collection<OwnableInformationElement> elementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return false;
		}

		try {
			initObservedElementGroup();
			boolean flag = super.addInnerElementDelegate(elementCollection) 
					& this.observedElementGroup.addGroupElement(elementCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addObservedElement(" 
						+ BaseElement.toBaseString(elementCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addObservedElement(" 
						+ BaseElement.toBaseString(elementCollection) + ")");
			}
			return flag;
		} finally {
			destroyObservedElementGroup();
		}
	}

	public boolean removeObservedElement(final String id) {
		if (id == null || !containObservedElement(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeObservedElement(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.observedElementGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeObservedElement(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeObservedElement(" + id + ")");
			}
			return flag;
		} finally {
			destroyObservedElementGroup();
		}
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(Entity.class) ? this : new Entity(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(Entity.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof Entity) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((Entity) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final Entity element) {
		if (element != null) {
			this.name = element.name;
			this.baseProposedElementGroup = element.baseProposedElementGroup;
			this.proposedElementGroup = element.proposedElementGroup;
			this.baseObservedElementGroup = element.baseObservedElementGroup;
			this.observedElementGroup = element.observedElementGroup;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseProposedElementGroup != null && this.baseProposedElementGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseProposedElementGroup.getId());
			this.proposedElementGroup = (ge instanceof OwnableInformationElementGroup) 
					? (OwnableInformationElementGroup) ge : this.proposedElementGroup;
			super.addInnerElementDelegate(this.proposedElementGroup);
		}
		if (this.baseObservedElementGroup != null && this.baseObservedElementGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseObservedElementGroup.getId());
			this.observedElementGroup = (ge instanceof OwnableInformationElementGroup) 
					? (OwnableInformationElementGroup) ge : this.observedElementGroup;
			super.addInnerElementDelegate(this.observedElementGroup);
		}
	}


	@Override
	public Entity cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(Entity.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (Entity) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Entity clonedElement = (Entity) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Entity cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Entity) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Entity)) 
				? null : cloneToElement((Entity) ce, null);
	}

	private Entity cloneToElement(final Entity clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.name = this.name;

		clonedElement.baseProposedElementGroup = (this.baseProposedElementGroup != null) 
				? (BaseElement) this.baseProposedElementGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseProposedElementGroup;
		clonedElement.proposedElementGroup = (this.proposedElementGroup != null) 
				? (OwnableInformationElementGroup) this.proposedElementGroup.cloneElement(clonedElementMap) 
				: clonedElement.proposedElementGroup;

		clonedElement.baseObservedElementGroup = (this.baseObservedElementGroup != null) 
				? (BaseElement) this.baseObservedElementGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseObservedElementGroup;
		clonedElement.observedElementGroup = (this.observedElementGroup != null) 
				? (OwnableInformationElementGroup) this.observedElementGroup.cloneElement(clonedElementMap) 
				: clonedElement.observedElementGroup;

		return clonedElement;
	}


	/* class methods */

	public boolean propose(final OwnableInformationElement element) throws LIMException {
		if (element == null) {
			return false;
		}

		Proposer p = new Proposer(this);
		element.setProposer(p);
		return true;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (Entity.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
