package com.codejstudio.lim.pojo.attribute;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AttributeGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AttributeGroup extends Attribute implements IGroupable<Attribute> {

	/* constants */

	private static final long serialVersionUID = -6848922756342140212L;


	/* variables: arrays, collections, maps, groups */

	private List<BaseElement> innerGroupList;

	private Map<String, BaseElement> innerGroupMap;

	private Map<String, BaseElement> innerBaseAttributeMap;

	private Map<String, String> innerIdKeyMap;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AttributeGroup() {
		super();
	}

	public AttributeGroup(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public AttributeGroup(boolean initIdFlag, Attribute... attributes) throws LIMException {
		super(initIdFlag, true);
		addGroupElement(attributes);
	}


	public AttributeGroup(Attribute... attributes) throws LIMException {
		this(true, attributes);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(AttributeGroup.class);
		GroupableUtil.registerGroupableClassForInit(Attribute.class, AttributeGroup.class);
		Attribute.registerSubPojoClassForInit(AttributeGroup.class);
	}


	private void initInnerGroupList() throws LIMException {
		if (this.innerGroupList == null) {
			this.innerGroupList = CollectionUtil.generateNewList();
		}
	}

	private void initInnerGroupMap() throws LIMException {
		if (this.innerGroupMap == null) {
			this.innerGroupMap = CollectionUtil.generateNewMap();
		}
	}

	private void initInnerBaseAttributeMap() throws LIMException {
		if (this.innerBaseAttributeMap == null) {
			this.innerBaseAttributeMap = CollectionUtil.generateNewMap();
		}
	}

	private void initInnerIdKeyMap() throws LIMException {
		if (this.innerIdKeyMap == null) {
			this.innerIdKeyMap = CollectionUtil.generateNewMap();
		}
	}


	/* destroyers */

	private void destroyInnerGroupList() {
		if (this.innerGroupList != null && this.innerGroupList.size() == 0) {
			this.innerGroupList = null;
		}
	}

	private void destroyInnerGroupMap() {
		if (this.innerGroupMap != null && this.innerGroupMap.size() == 0) {
			this.innerGroupMap = null;
		}
	}

	private void destroyInnerBaseAttributeMap() {
		if (this.innerBaseAttributeMap != null && this.innerBaseAttributeMap.size() == 0) {
			this.innerBaseAttributeMap = null;
		}
	}

	private void destroyInnerIdKeyMap() {
		if (this.innerIdKeyMap != null && this.innerIdKeyMap.size() == 0) {
			this.innerIdKeyMap = null;
		}
	}


	/* getters & setters */

	@Override
	public List<Attribute> getInnerGroupList() throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			return null;
		}

		List<Attribute> l = CollectionUtil.generateNewList();
		for (BaseElement be : this.innerGroupList) {
			l.add((Attribute) super.getInnerElementDelegate(be));
		}
		return CollectionUtil.checkNullOrEmpty(l) ? null : l;
	}


	/* CRUD for arrays, collections, maps, groups: attributes */

	public boolean containAttributeByKey(final String key) {
		return (this.innerBaseAttributeMap == null) ? false : this.innerBaseAttributeMap.containsKey(key);
	}

	public boolean containAttribute(final Attribute attribute) {
		return (attribute == null) ? false : containAttributeByKey(attribute.key);
	}

	public boolean containAttribute(final String id) {
		return (this.innerIdKeyMap == null) ? false : this.innerIdKeyMap.containsKey(id);
	}


	public BaseElement getBaseAttributeByKey(final String key) {
		return (key == null || this.innerBaseAttributeMap == null) 
				? null : this.innerBaseAttributeMap.get(key);
	}

	public BaseElement getBaseAttribute(final Attribute attribute) {
		return (attribute == null) ? null : getBaseAttributeByKey(attribute.key);
	}

	public BaseElement getBaseAttribute(final String id) {
		return (this.innerIdKeyMap == null || this.innerBaseAttributeMap == null) 
				? null : this.innerBaseAttributeMap.get(this.innerIdKeyMap.get(id));
	}


	public Attribute getAttributeByKey(final String key) {
		return (key == null || this.innerBaseAttributeMap == null 
						|| !this.innerBaseAttributeMap.containsKey(key)) 
				? null : (Attribute) super.getInnerElementDelegate(this.innerBaseAttributeMap.get(key));
	}

	public Attribute getAttribute(final Attribute attribute) {
		return (attribute == null) ? null : getAttributeByKey(attribute.key);
	}

	public Attribute getAttribute(final String id) {
		return (id == null) ? null : (Attribute) super.getInnerElementDelegate(id);
	}


	public boolean putAttribute(final Attribute... attributes) throws LIMException {
		return putAttribute((attributes == null) ? null : Arrays.asList(attributes));
	}

	public boolean putAttribute(final Collection<Attribute> attributeCollection) throws LIMException { 
		if (CollectionUtil.checkNullOrEmpty(attributeCollection)) {
			return false;
		}

		try {
			initInnerBaseAttributeMap();
			initInnerIdKeyMap();
			boolean flag = true;
			for (Attribute at : attributeCollection) {
				if (at == null) {
					continue;
				}

				BaseElement be = getBaseAttribute(at);
				if (be != null) {
					if (be.getId() != null && !be.getId().equals(at.getId())) {
						this.innerBaseAttributeMap.put(at.getKey(), new BaseElement(at));
						this.innerIdKeyMap.put(at.getId(), at.getKey());
						flag &= replaceGroupElement(be, at);
					}
				} else {
					this.innerBaseAttributeMap.put(at.getKey(), new BaseElement(at));
					this.innerIdKeyMap.put(at.getId(), at.getKey());
					flag &= addGroupElement(at);
				}
			}

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": putAttribute(" 
						+ BaseElement.toBaseString(attributeCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to putAttribute(" 
						+ BaseElement.toBaseString(attributeCollection) + ")");
			}
			return flag;
		} finally {
			destroyInnerBaseAttributeMap();
			destroyInnerIdKeyMap();
		}
	}


	public boolean removeAttribute(final String id) {
		if (id == null || !containAttribute(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeAttribute(" + id + ")");
			return false;
		}

		try {
			this.innerBaseAttributeMap.remove(this.innerIdKeyMap.remove(id));
			boolean flag = removeGroupElement(id);

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeAttribute(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeAttribute(" + id + ")");
			}
			return flag;
		} finally {
			destroyInnerBaseAttributeMap();
			destroyInnerIdKeyMap();
		}
	}


	/* CRUD for arrays, collections, maps, groups: group elements */

	@Override
	public int size() {
		return (this.innerGroupList == null) ? 0 : this.innerGroupList.size();
	}

	@Override
	public boolean containGroupElement(final Attribute groupElement) {
		return (groupElement == null) ? false : containGroupElement(groupElement.getId());
	}

	@Override
	public boolean containGroupElement(final String id) {
		return (id == null || this.innerGroupMap == null) ? false : this.innerGroupMap.containsKey(id);
	}

	@Override
	public boolean addGroupElement(final Attribute... groupElements) throws LIMException {
		return addGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean addGroupElement(final Collection<? extends Attribute> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection)) {
			return false;
		}

		try {
			initInnerGroupList();
			initInnerGroupMap();
			boolean flag = true;
			for (Attribute e : groupElementCollection) {
				if (e == null || e.getId() == null) {
					continue;
				}
				if (this.innerGroupMap.containsKey(e.getId())) {
					flag = false;
					continue;
				}

				BaseElement be = new BaseElement(e);
				this.innerGroupMap.put(e.getId(), be);
				flag &= this.innerGroupList.add(be) 
						& super.addInnerElementDelegate(e) 
						& super.putIntegratedElementDelegate(
								IGroupable.GROUP_KEY_PREFIX_IEM + e.getId(), be);
			}

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addGroupElement(" 
						+ BaseElement.toBaseString(groupElementCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addGroupElement(" 
						+ BaseElement.toBaseString(groupElementCollection) + ")");
			}
			return flag;
		} finally {
			destroyInnerGroupList();
			destroyInnerGroupMap();
		}
	}

	@Override
	public boolean removeGroupElement(final String id) {
		if (id == null || !this.innerGroupMap.containsKey(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeGroupElement(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeIntegratedElementDelegate(IGroupable.GROUP_KEY_PREFIX_IEM + id) 
					& super.removeInnerElementDelegate(id) 
					& this.innerGroupList.remove(this.innerGroupMap.remove(id));

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeGroupElement(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeGroupElement(" + id + ")");
			}
			return flag;
		} finally {
			destroyInnerGroupList();
			destroyInnerGroupMap();
		}
	}

	@Override
	public boolean replaceGroupElement(final BaseElement replacee, final Attribute replacer) 
			throws LIMException {
		return (Objects.equals(replacee, replacer) || (replacee != null && !(replacee instanceof Attribute) 
						&& !replacee.getClass().equals(BaseElement.class))) ? false 
				: (removeGroupElement((replacee == null) ? null : replacee.getId()) 
						& addGroupElement((replacer == null) ? null : Arrays.asList(replacer)));
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
		Collection<BaseElement> vc;
		if (CollectionUtil.checkNullOrEmpty(rootElementMap) 
				|| (item = super.getIntegratedElementMap()) == null 
				|| CollectionUtil.checkNullOrEmpty(vc = item.values())) {
			return;
		}

		initInnerGroupList();
		initInnerGroupMap();
		initInnerBaseAttributeMap();
		initInnerIdKeyMap();
		for (BaseElement be : vc) {
			GenericElement ge;
			if (be == null || be.getId() == null 
					|| !((ge = rootElementMap.get(be.getId())) instanceof Attribute)) {
				continue;
			}
			Attribute at = (Attribute) ge;
			super.addInnerElementDelegate(be, ge);
			this.innerGroupList.add(be);
			this.innerGroupMap.put(be.getId(), be);
			this.innerBaseAttributeMap.put(at.getKey(), be);
			this.innerIdKeyMap.put(be.getId(), at.getKey());
		}
		destroyInnerGroupList();
		destroyInnerGroupMap();
		destroyInnerBaseAttributeMap();
		destroyInnerIdKeyMap();
	}


	@Override
	public AttributeGroup cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(AttributeGroup.class)) {
					return (AttributeGroup) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		AttributeGroup clonedElement = (AttributeGroup) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public AttributeGroup cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof AttributeGroup) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof AttributeGroup)) 
				? null : cloneToElement((AttributeGroup) ce, null);
	}

	private AttributeGroup cloneToElement(final AttributeGroup clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(this.innerGroupMap)) {
			clonedElement.initInnerGroupList();
			clonedElement.initInnerGroupMap();
			Set<String> igks = this.innerGroupMap.keySet();
			for (String id : igks) {
				BaseElement be = this.innerGroupMap.get(id);
				if (be == null) {
					continue;
				}
				BaseElement clonedBe = be.cloneElement(clonedElementMap);
				clonedElement.innerGroupList.add(clonedBe);
				clonedElement.innerGroupMap.put(id, clonedBe);
			}
			clonedElement.destroyInnerGroupList();
			clonedElement.destroyInnerGroupMap();
		}
		if (this.innerBaseAttributeMap != null && this.innerBaseAttributeMap.size() > 0) {
			clonedElement.initInnerBaseAttributeMap();
			clonedElement.initInnerIdKeyMap();
			Set<String> ibatks = this.innerBaseAttributeMap.keySet();
			for (String k : ibatks) {
				BaseElement value = this.innerBaseAttributeMap.get(k);
				if (value != null) {
					clonedElement.innerBaseAttributeMap.put(k, value.cloneElement(clonedElementMap));
				}
			}
			clonedElement.destroyInnerBaseAttributeMap();
			clonedElement.destroyInnerIdKeyMap();
		}

		return clonedElement;
	}

}
