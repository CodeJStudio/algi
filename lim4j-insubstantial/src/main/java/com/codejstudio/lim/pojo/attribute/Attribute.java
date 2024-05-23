package com.codejstudio.lim.pojo.attribute;

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
import com.codejstudio.lim.pojo.GenericElementGroup;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Attribute.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Attribute.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseValue",
	"baseValueGroup",
})
public class Attribute extends InformationUnit {

	/* constants */

	private static final long serialVersionUID = -4511925556818399516L;

	public static final String TYPE_NAME = "attribute";

	public static final boolean FORCE_COMPATIBLE_FLAG = false;


	/* variables */

	@XmlAttribute
	protected Boolean compatible;

	@XmlAttribute(required = true)
	protected String key;

	@XmlElement(name = "value")
	protected BaseElement baseValue;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "value-group")
	private BaseElement baseValueGroup;

	private GenericElementGroup valueGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Attribute() {
		super();
	}

	public Attribute(Attribute attribute) throws LIMException {
		super(attribute);
		load(attribute);
	}

	public Attribute(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Attribute(boolean initIdFlag, boolean initTypeFlag, String key, GenericElement... values) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		init(key, values);
	}

	public Attribute(boolean initIdFlag, boolean initTypeFlag, String key, 
			Collection<? extends GenericElement> valueCollection) throws LIMException {
		super(initIdFlag, initTypeFlag);
		init(key, valueCollection);
	}

	public Attribute(String key, GenericElement... values) throws LIMException {
		this(true, false, key, values);
	}

	public Attribute(String key, Collection<? extends GenericElement> valueCollection) throws LIMException {
		this(true, false, key, valueCollection);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(Attribute.class);
			JAXBBoundClassConstant.registerBoundClassForInit(Attribute.class);
			Attribute.registerSubPojoClassForInit(Attribute.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	protected void init(final String key, final GenericElement... values) throws LIMException {
		init(key, ((values == null) ? null : Arrays.asList(values)));
	}

	protected void init(final String key, final Collection<? extends GenericElement> valueCollection) 
			throws LIMException {
		if (key == null || CollectionUtil.checkNullOrEmpty(valueCollection)) {
			throw new LIMException(new NullPointerException());
		}

		setKey(key);
		addValue(valueCollection);
	}


	private void initValueGroup() throws LIMException {
		if (this.valueGroup == null) {
			this.valueGroup = new GenericElementGroup(true);
			super.addInnerElementDelegate(this.valueGroup);
			this.baseValueGroup = new BaseElement(valueGroup);
		}
	}


	/* destroyers */

	private void destroyValueGroup() {
		if (this.valueGroup != null && this.valueGroup.size() == 0) {
			this.baseValueGroup = null;
			super.removeInnerElementDelegate(this.valueGroup.getId());
			this.valueGroup = null;
		}
	}


	/* getters & setters */

	@XmlTransient
	public Boolean getCompatible() {
		return compatible;
	}

	public void setCompatible(Boolean compatible) {
		this.compatible = (compatible == null || !compatible) ? null : true;
	}

	@XmlTransient
	public String getKey() {
		return key;
	}

	public void setKey(final String key) throws LIMException {
		if (key == null) {
			throw new LIMException(new NullPointerException());
		}
		this.key = key;
		ElementTrace.log.info(this.toBaseString() + ": setKey(\"" + key + "\")");
	}

	@XmlTransient
	public BaseElement getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(BaseElement baseValue) {
		this.baseValue = baseValue;
	}

	@XmlTransient
	public GenericElement getValue() {
		return (this.baseValue == null) 
				? null : (GenericElement) super.getInnerElementDelegate(this.baseValue);
	}

	private boolean setValue(final GenericElement value) throws LIMException {
		boolean flag = true;
		if (this.baseValue != null && value != null) {
			if (!this.baseValue.baseEquals(value)) {
				flag &= super.replaceInnerElementDelegate(this.baseValue, value);
				this.baseValue = new BaseElement(value);
			}
		} else if (this.baseValue != null) {
			flag &= super.removeInnerElementDelegate(this.baseValue.getId());
			this.baseValue = null;
		} else if (value != null) {
			this.baseValue = new BaseElement(value);
			flag &= super.addInnerElementDelegate(this.baseValue, value);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setValue(" 
					+ ((value == null) ? null : value.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setValue(" 
					+ ((value == null) ? null : value.toBaseString()) + ")");
		}
		return flag;
	}

	@XmlTransient
	public BaseElement getBaseValueGroup() {
		return baseValueGroup;
	}

	public void setBaseValueGroup(BaseElement baseValueGroup) {
		this.baseValueGroup = baseValueGroup;
	}

	public GenericElementGroup getValueGroup() {
		return valueGroup;
	}


	/* CRUD for arrays, collections, maps, groups: compatible values */

	public boolean containValue(final GenericElement value) {
		return (this.compatible == null || !this.compatible) 
				? (value != null && value.baseEquals(this.baseValue)) 
				: ((this.valueGroup == null) ? false : this.valueGroup.containGroupElement(value));
	}

	public boolean containValue(final String id) {
		return (this.compatible == null || !this.compatible) 
				? (id != null && this.baseValue != null && id.equals(this.baseValue.getId())) 
				: ((this.valueGroup == null) ? false : this.valueGroup.containGroupElement(id));
	}

	public boolean addValue(final GenericElement... values) throws LIMException {
		return addValue((values == null) ? null : Arrays.asList(values));
	}

	public boolean addValue(final Collection<? extends GenericElement> valueCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(valueCollection) 
				|| BaseElement.onlyContains(valueCollection, this.baseValue) 
				|| (this.valueGroup != null 
				&& GroupableUtil.onlyContains(this.valueGroup, valueCollection))) {
			return false;
		}

		int size = valueCollection.size();
		size += (this.baseValue == null) ? 0 : 1;

		try {
			initValueGroup();
			boolean flag = true;
			if (size == 1) {
				setCompatible(false);
				flag &= setValue(valueCollection.iterator().next());
			} else if (size > 1) {
				setCompatible(true);
				this.baseValue = null;
				flag &= super.addInnerElementDelegate(valueCollection);
			}
			flag &= this.valueGroup.addGroupElement(valueCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addValue(" 
						+ BaseElement.toBaseString(valueCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addValue(" 
						+ BaseElement.toBaseString(valueCollection) + ")");
			}
			return flag;
		} finally {
			destroyValueGroup();
		}
	}

	public boolean removeValue(final String id) throws LIMException {
		if (id == null || GroupableUtil.checkNullOrEmpty(this.valueGroup)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeValue(" + id + ")");
			return false;
		}

		try {
			boolean flag = true;
			if (this.compatible == null || !this.compatible) {
				if (this.baseValue != null && id.equals(this.baseValue.getId())) {
					flag &= setValue(null);
				}
			} else {
				flag &= super.removeInnerElementDelegate(id);
			}
			flag &= this.valueGroup.removeGroupElement(id);

			if (this.valueGroup.size() == 1 && !FORCE_COMPATIBLE_FLAG) {
				GenericElement ge = this.valueGroup.getInnerGroupList().iterator().next();
				setCompatible(false);
				flag &= setValue(ge);
			}

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeValue(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeValue(" + id + ")");
			}
			return flag;
		} finally {
			destroyValueGroup();
		}
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(Attribute.class) ? this : new Attribute(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(Attribute.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof Attribute) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((Attribute) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final Attribute element) {
		if (element != null) {
			this.compatible = element.compatible;
			this.key = element.key;
			this.baseValue = element.baseValue;
			this.baseValueGroup = element.baseValueGroup;
			this.valueGroup = element.valueGroup;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseValue != null && this.baseValue.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseValue.getId());
			super.addInnerElementDelegate(this.baseValue, ge);
		}
		if (this.baseValueGroup != null && this.baseValueGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseValueGroup.getId());
			this.valueGroup = (ge instanceof GenericElementGroup) 
					? (GenericElementGroup) ge : this.valueGroup;
			super.addInnerElementDelegate(this.valueGroup);
		}
	}


	@Override
	public Attribute cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(Attribute.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (Attribute) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Attribute clonedElement = (Attribute) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Attribute cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Attribute) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Attribute)) 
				? null : cloneToElement((Attribute) ce, null);
	}

	private Attribute cloneToElement(final Attribute clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.compatible = this.compatible;
		clonedElement.key = this.key;

		clonedElement.baseValue = (this.baseValue != null) 
				? (BaseElement) this.baseValue.cloneElement(clonedElementMap) : clonedElement.baseValue;

		clonedElement.baseValueGroup = (this.baseValueGroup != null) 
				? (BaseElement) this.baseValueGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseValueGroup;
		clonedElement.valueGroup = (this.valueGroup != null) 
				? (GenericElementGroup) this.valueGroup.cloneElement(clonedElementMap) 
				: clonedElement.valueGroup;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (Attribute.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
