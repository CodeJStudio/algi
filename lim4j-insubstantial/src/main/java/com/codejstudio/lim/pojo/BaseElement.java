package com.codejstudio.lim.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.IDUtil;
import com.codejstudio.lim.common.util.ReflectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.ICloneable;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * BaseElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = BaseElement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BaseElement implements ICloneable, Cloneable, Comparable<BaseElement>, Serializable {

	/* constants */

	private static final long serialVersionUID = -5469704654101714381L;

	public static final String TYPE_NAME = "base-element";

	public static final String FIELD_NAME_SUB_POJO_CLAZZ_COLLECTION = "SUB_POJO_CLAZZ_COLLECTION";

	public static final String TOSTRING_SYMBOL_LEFT = "<";
	public static final String TOSTRING_SYMBOL_RIGHT = ">";
	public static final String TOSTRING_SYMBOL_SEPARATOR = WordSeparator.COMMA.getCharacter() +" ";


	/* variables */

	@XmlAttribute
	protected String id;

	@XmlAttribute
	protected String type;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;


	/* constructors */

	/**
	 * only for JAXB usage of unmarshalling
	 */
	public BaseElement() {}

	public BaseElement(BaseElement element, boolean verifyTypeFlag) {
		load(element);
		if (verifyTypeFlag && this.type == null) {
			initType(element);
		}
	}

	public BaseElement(BaseElement element) {
		this(element, true);
	}

	public BaseElement(String id, String type) {
		this.id = id;
		this.type = type;
	}

	public BaseElement(String id, Class<? extends BaseElement> elementClazz) {
		this.id = id;
		initType(elementClazz);
	}

	public BaseElement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		init(initIdFlag, initTypeFlag);
		ElementTrace.log.info("New Element: " + this.toString());
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(BaseElement.class);
			JAXBBoundClassConstant.registerBoundClassForInit(BaseElement.class);
			BaseElement.registerSubPojoClassForInit(BaseElement.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	protected void init(final boolean initIdFlag, final boolean initTypeFlag) throws LIMException {
		if (initIdFlag) {
			initId();
		}
		if (initTypeFlag) {
			initType(this);
		}
	}

	private void initId() throws LIMException {
		this.id = IDUtil.generateID();
	}

	private void initType(final BaseElement element) {
		if (element != null) {
			initType(element.getClass());
		}
	}

	private void initType(final Class<? extends BaseElement> elementClazz) {
		if (elementClazz != null) {
			this.type = generateTypeFromClass(elementClazz);
		}
	}


	/* getters & setters */

	@XmlTransient
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@XmlTransient
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	/* class methods */

	protected IConvertible reload(final IConvertible element) {
		if (this instanceof IConvertible && element != null) {
			load((BaseElement) element);
			return (IConvertible) this;
		} else {
			return null;
		}
	}

	private void load(final BaseElement element) {
		if (element != null) {
			this.id = element.id;
			this.type = element.type;
		}
	}


	public boolean baseEquals(final BaseElement element) {
		if (element == null || this.id == null) {
			return false;
		}

		String thisType = this.getClass().equals(BaseElement.class) 
				? this.type : generateTypeFromClass(this.getClass());
		String elementType = element.getClass().equals(BaseElement.class) 
				? element.type : generateTypeFromClass(element.getClass());
		return (this.id.equals(element.id) && thisType != null && thisType.equals(elementType)) 
				? true : super.equals(element);
	}


	/* overridden methods */

	@Override
	public BaseElement cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		try {
			BaseElement clonedElement = (BaseElement) this.getClass().newInstance();
			clonedElement.load(this);
			if (clonedElementMap != null && !clonedElement.getClass().equals(BaseElement.class)) {
				clonedElementMap.put(clonedElement.id, clonedElement);
			}
			return clonedElement;
		} catch (InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@Override
	public int compareTo(final BaseElement element) {
		return (StringUtil.isNumeric(this.id) && StringUtil.isNumeric(element.id)) 
				? Long.valueOf(this.id).compareTo(Long.valueOf(element.id)) : this.id.compareTo(element.id);
	}

	@Override
	public String toString() {
		return toBaseString();
	}

	public String toBaseString() {
		return TOSTRING_SYMBOL_LEFT 
				+ "id=" + this.id + TOSTRING_SYMBOL_SEPARATOR
				+ "type=" + this.type + TOSTRING_SYMBOL_SEPARATOR
				+ "class=" + this.getClass().getSimpleName() + TOSTRING_SYMBOL_RIGHT;
	}


	/* static methods */

	public static String toBaseString(final BaseElement... elements) {
		return toBaseString((elements == null) ? null : Arrays.asList(elements));
	}

	public static String toBaseString(final Collection<? extends BaseElement> elementCollection) {
		if (elementCollection == null) {
			return null;
		}
		Iterator<? extends BaseElement> beit = elementCollection.iterator();
		if (!beit.hasNext()) {
			return "[]";
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		while (true) {
			BaseElement be = beit.next();
			sb.append((be == null) ? null : be.toBaseString());
			if (!beit.hasNext()) {
				return sb.append(']').toString();
			}
			sb.append(',').append(' ');
		}
	}


	public static final BaseElement newInstance(final Class<? extends BaseElement> elementClazz, 
			final boolean initFlag) throws LIMException {
		try {
			BaseElement be = elementClazz.newInstance();
			if (initFlag) {
				be.initId();
				be.initType(be.getClass());
				be.addThisAsInnerElement();
			}
			return be;
		} catch (InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}
	}

	protected void addThisAsInnerElement() throws LIMException {}


	@SuppressWarnings("unchecked")
	public static final Collection<Class<? extends BaseElement>> getSubPojoClassCollection(
			final Class<? extends BaseElement> elementClazz) throws LIMException {
		Object obj = ReflectionUtil.getFieldObject(
				ReflectionUtil.findField(elementClazz, FIELD_NAME_SUB_POJO_CLAZZ_COLLECTION), null);
		return !(obj instanceof Collection<?>) ? null 
				: (Collection<Class<? extends BaseElement>>) CollectionUtil
						.copyCollection((Collection<?>) obj);
	}

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (LogicValue.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}


	public static final String generateTypeFromClass(final Class<? extends BaseElement> elementClazz) {
		return (elementClazz == null) ? null 
				: CaseFormatUtil.camelToSeparated(elementClazz.getSimpleName(), 
						WordSeparator.HYPHEN, false);
	}

	public static final String generateConstantFromClass(final Class<? extends BaseElement> elementClazz) {
		return (elementClazz == null) ? null 
				: CaseFormatUtil.camelToSeparated(elementClazz.getSimpleName(), 
						WordSeparator.UNDERSCORE, true);
	}

	protected static final String generateClassSimpleNameFromType(final String type) {
		return (type == null) ? null : CaseFormatUtil.separatedToCamel(type, WordSeparator.HYPHEN, true);
	}


	@SuppressWarnings("unchecked")
	protected static final <T extends BaseElement> boolean removeBaseElements(
			final Collection<BaseElement> elementCollection, T... elements) throws LIMException {
		return removeBaseElements(elementCollection, ((elements == null) ? null : Arrays.asList(elements)));
	}

	protected static final <T extends BaseElement> boolean removeBaseElements(
			final Collection<BaseElement> elementCollection, Collection<T> tCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection) 
				|| CollectionUtil.checkNullOrEmpty(tCollection)) {
			return false;
		}

		Collection<BaseElement> bec = CollectionUtil.generateNewCollection();
		for (BaseElement element : tCollection) {
			for (BaseElement be : elementCollection) {
				if (be != null && be.baseEquals(element)) {
					bec.add(be);
					break;
				}
			}
		}
		return elementCollection.removeAll(bec);
	}


	public static final <T extends BaseElement> boolean onlyContains(final Collection<T> tCollection, 
			final BaseElement... elements) throws LIMException {
		return onlyContains(tCollection, ((elements == null) ? null : Arrays.asList(elements)));
	}

	protected static final <T extends BaseElement> boolean onlyContains(final Collection<T> tCollection, 
			final Collection<BaseElement> elementCollection) {
		if (CollectionUtil.checkNullOrEmpty(tCollection) 
				|| CollectionUtil.checkNullOrEmpty(elementCollection) 
				|| tCollection.size() != elementCollection.size()) {
			return false;
		}

		for (BaseElement be : elementCollection) {
			boolean flag = false;
			if (be != null) {
				for (T t : tCollection) {
					if (be.baseEquals(t)) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				return false;
			}
		}
		return true;
	}

}
