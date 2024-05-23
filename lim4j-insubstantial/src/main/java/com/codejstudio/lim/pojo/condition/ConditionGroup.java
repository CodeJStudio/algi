package com.codejstudio.lim.pojo.condition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementEnumConstant;

/**
 * ConditionGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class ConditionGroup extends Condition implements IGroupable<Condition> {

	/* enumeration */

	public enum AndOrType {
		AND,
		OR,
		;
	}


	/* constants */

	private static final long serialVersionUID = -4369848724260398772L;

	public static final String AND_OR_TYPE = "and-or-type";


	/* variables */

	private AndOrType andOrType;


	/* variables: arrays, collections, maps, groups */

	private List<BaseElement> innerGroupList;

	private Map<String, BaseElement> innerGroupMap;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ConditionGroup() {
		super();
	}

	public ConditionGroup(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public ConditionGroup(boolean initIdFlag, Condition... conditions) throws LIMException {
		super(initIdFlag, true);
		addGroupElement(conditions);
	}


	public ConditionGroup(Condition... conditions) throws LIMException {
		this(true, conditions);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(ConditionGroup.class);
		GroupableUtil.registerGroupableClassForInit(Condition.class, ConditionGroup.class);
		PojoElementEnumConstant.registerEnumClassForInit(AndOrType.class);
		Condition.registerSubPojoClassForInit(ConditionGroup.class);
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


	/* getters & setters */

	public AndOrType getAndOrType() {
		return andOrType;
	}

	public boolean setAndOrType(final AndOrType andOrType) throws LIMException {
		if (Objects.equals(this.andOrType, andOrType)) {
			return true;
		}

		boolean flag = true;
		if (this.andOrType != null) {
			flag &= super.removeIntegratedAttributeDelegate(AND_OR_TYPE);
			this.andOrType = null;
		}
		if (andOrType != null) {
			this.andOrType = andOrType;
			flag &= super.putIntegratedAttributeDelegate(AND_OR_TYPE, andOrType.name());
		}
		return flag;
	}


	@Override
	public List<Condition> getInnerGroupList() throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			return null;
		}

		List<Condition> l = CollectionUtil.generateNewList();
		for (BaseElement be : this.innerGroupList) {
			l.add((Condition) super.getInnerElementDelegate(be));
		}
		return CollectionUtil.checkNullOrEmpty(l) ? null : l;
	}


	/* CRUD for arrays, collections, maps, groups: group elements */

	@Override
	public int size() {
		return (this.innerGroupList == null) ? 0 : this.innerGroupList.size();
	}

	@Override
	public boolean containGroupElement(final Condition groupElement) {
		return (groupElement == null) ? false : containGroupElement(groupElement.getId());
	}

	@Override
	public boolean containGroupElement(final String id) {
		return (id == null || this.innerGroupMap == null) ? false : this.innerGroupMap.containsKey(id);
	}

	public boolean containAttributeCondition(final AttributeCondition condition) throws LIMException {
		if (condition == null) {
			return false;
		}

		GenericElement attributeElement = condition.getAttributeElement();
		List<Condition> cdl = getInnerGroupList();
		for (Condition cd : cdl) {
			GenericElement ge;
			if (cd instanceof AttributeCondition 
					&& (ge = ((AttributeCondition) cd).getAttributeElement()) != null 
					&& ge.baseEquals(attributeElement)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addGroupElement(final Condition... groupElements) throws LIMException {
		return addGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean addGroupElement(final Collection<? extends Condition> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection)) {
			return false;
		}

		try {
			initInnerGroupList();
			initInnerGroupMap();
			boolean flag = true;
			for (Condition e : groupElementCollection) {
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
	public boolean replaceGroupElement(final BaseElement replacee, final Condition replacer) 
			throws LIMException {
		return (Objects.equals(replacee, replacer) || (replacee != null && !(replacee instanceof Condition) 
						&& !replacee.getClass().equals(BaseElement.class))) ? false 
				: (removeGroupElement((replacee == null) ? null : replacee.getId()) 
						& addGroupElement((replacer == null) ? null : Arrays.asList(replacer)));
	}


	/* overridden methods */

	@Override
	protected boolean verifyDescriptionWithoutVerifier(final String description, 
			final boolean noneConditionFlag) throws LIMException {
		if (GroupableUtil.checkNullOrEmpty(this)) {
			return noneConditionFlag;
		}

		boolean orFlag = (this.getAndOrType() == AndOrType.OR) && (this.size() > 1);
		List<Condition> cdigl = this.getInnerGroupList();
		for (Condition cd : cdigl) {
			boolean verifyFlag = cd.verifyDescription(description, noneConditionFlag);
			if (orFlag && verifyFlag) {
				return true;
			} else if (!orFlag && !verifyFlag) {
				return false;
			}
		}
		return !orFlag;
	}


	@Override
	public boolean verifyInformation(final InformationElement element) throws LIMException {
		return verifyInformation(element, true);
	}

	public boolean verifyInformation(final InformationElement element, final boolean noneConditionFlag) 
			throws LIMException {
		if (GroupableUtil.checkNullOrEmpty(this)) {
			return noneConditionFlag;
		}

		boolean orFlag = (this.getAndOrType() == AndOrType.OR) && (this.size() > 1);
		List<Condition> cdigl = this.getInnerGroupList();
		for (Condition cd : cdigl) {
			boolean verifyFlag = cd.verifyInformation(element);
			if (orFlag && verifyFlag) {
				return true;
			} else if (!orFlag && !verifyFlag) {
				return false;
			}
		}
		return !orFlag;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load();
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load() {
		String typeName = super.getIntegratedAttributeDelegate(AND_OR_TYPE);
		this.andOrType = !StringUtil.isEmpty(typeName) ? AndOrType.valueOf(typeName) : this.andOrType;
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
		for (BaseElement be : vc) {
			GenericElement ge;
			if (be == null || be.getId() == null 
					|| !((ge = rootElementMap.get(be.getId())) instanceof Condition)) {
				continue;
			}
			super.addInnerElementDelegate(be, ge);
			this.innerGroupList.add(be);
			this.innerGroupMap.put(be.getId(), be);
		}
		destroyInnerGroupList();
		destroyInnerGroupMap();
	}


	@Override
	public ConditionGroup cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(ConditionGroup.class)) {
					return (ConditionGroup) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		ConditionGroup clonedElement = (ConditionGroup) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public ConditionGroup cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof ConditionGroup) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof ConditionGroup)) 
				? null : cloneToElement((ConditionGroup) ce, null);
	}

	private ConditionGroup cloneToElement(final ConditionGroup clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.andOrType = this.andOrType;

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

		return clonedElement;
	}

}
