package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ActionableElementClassConstant;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
@XmlRootElement(name = GenericActionableElement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"actionDescription",
})
public class GenericActionableElement extends BaseElement implements IConvertible {

	/* constants */

	private static final long serialVersionUID = -4340435443958006448L;

	public static final String TYPE_NAME = "actionable-element";


	/* variables */

	protected Description descriptionObject;

	protected GenericActionableElement xmlElement;
	protected GenericActionableElement pojoElement;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;


	/* constructors */

	/**
	 * for JAXB usage of unmarshalling
	 */
	public GenericActionableElement() {
		super();
	}

	protected GenericActionableElement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
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

			ActionableElementClassConstant.registerActionableClassForInit(GenericActionableElement.class);
			JAXBBoundClassConstant.registerBoundClassForInit(GenericActionableElement.class);
			GenericActionableElement.registerSubPojoClassForInit(GenericActionableElement.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* getters & setters */

	@XmlElement(name = "action-description")
	public String getActionDescription() {
		return (this.descriptionObject == null) ? null : this.descriptionObject.getEncodedText();
	}

	public void setActionDescription(final String actionDescription) throws LIMException {
		if (!StringUtil.isEmpty(actionDescription)) {
			if (descriptionObject == null || !actionDescription.equals(descriptionObject.getEncodedText())) {
				setDescriptionObject(new Description(actionDescription, false));
			}
		} else {
			setDescriptionObject(null);
		}
	}

	@XmlTransient
	public Description getDescriptionObject() {
		return descriptionObject;
	}

	public void setDescriptionObject(final Description descriptionObject) throws LIMException {
		this.descriptionObject = descriptionObject;
		updateDescription();
	}

	protected void updateDescription() throws LIMException {}


	/* class methods */

	@SuppressWarnings("unchecked")
	protected GenericActionableElement generateActionableElementDelegate() throws LIMException {
		try {
			String sn = super.generateClassSimpleNameFromType(this.getType());
			Class<? extends GenericActionableElement> gaecl 
					= ActionableElementClassConstant.getActionableClass(sn);
			if (gaecl == null) {
				gaecl = (Class<? extends GenericActionableElement>) Class.forName(
						this.getClass().getPackage().getName() + "." + sn);
			}
			return gaecl.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}
	}


	/* overridden methods */

	@Override
	protected void addThisAsInnerElement() throws LIMException {
		Informationiverse.addActionableElement(this);
	}


	@Override
	public IConvertible getXmlElement() {
		if (this.xmlElement == null) {
			this.xmlElement = this;
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(GenericActionableElement.class)) 
					? this : generateActionableElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}

	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof GenericActionableElement) || super.reload(convertible) == null) {
			return null;
		}
		load((GenericActionableElement) convertible);
		addThisAsInnerElement();
		return (IConvertible) this;
	}

	private void load(final GenericActionableElement element) throws LIMException {
		if (element != null) {
			this.descriptionObject = element.descriptionObject;
		}
	}


	@Override
	public GenericActionableElement cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(GenericActionableElement.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (GenericActionableElement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		GenericActionableElement clonedElement 
				= (GenericActionableElement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	public GenericActionableElement cloneToElement(final GenericActionableElement clonedElement) 
			throws LIMException {
		return cloneToElement(clonedElement, null);
	}

	private GenericActionableElement cloneToElement(final GenericActionableElement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (clonedElement == null) {
			return null;
		}

		clonedElement.descriptionObject = (this.descriptionObject != null) 
				? (Description) this.descriptionObject.cloneElement(clonedElementMap) 
				: clonedElement.descriptionObject;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (GenericActionableElement.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
