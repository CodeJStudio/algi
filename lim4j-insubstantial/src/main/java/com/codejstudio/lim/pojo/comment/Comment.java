package com.codejstudio.lim.pojo.comment;

import java.util.Collection;
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
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Comment.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Comment.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseElement",
})
public class Comment extends GenericElement {

	/* constants */

	private static final long serialVersionUID = 891364320907405092L;

	public static final String TYPE_NAME = "comment";


	/* variables */

	@XmlElement(name = "element", required = true)
	protected BaseElement baseElement;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Comment() {
		super();
	}

	public Comment(Comment comment) throws LIMException {
		super(comment);
		load(comment);
	}

	public Comment(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Comment(boolean initIdFlag, boolean initTypeFlag, OwnableInformationElement element) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setElement(element);
	}


	public Comment(OwnableInformationElement element) throws LIMException {
		this(true, false, element);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(Comment.class);
			JAXBBoundClassConstant.registerBoundClassForInit(Comment.class);
			Comment.registerSubPojoClassForInit(Comment.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* getters & setters */

	@XmlTransient
	public BaseElement getBaseElement() {
		return baseElement;
	}

	public void setBaseElement(BaseElement baseElement) {
		this.baseElement = baseElement;
	}

	@XmlTransient
	public OwnableInformationElement getElement() {
		return (this.baseElement == null) 
				? null : (OwnableInformationElement) super.getInnerElementDelegate(this.baseElement);
	}

	public boolean setElement(final OwnableInformationElement element) throws LIMException {
		if (element == null) {
			throw new LIMException(new NullPointerException());
		}

		boolean flag = true;
		if (this.baseElement != null) {
			if (!this.baseElement.baseEquals(element)) {
				flag &= super.replaceInnerElementDelegate(this.baseElement, element);
				this.baseElement = new BaseElement(element);
			}
		} else {
			this.baseElement = new BaseElement(element);
			flag &= super.addInnerElementDelegate(this.baseElement, element);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setElement(" 
					+ ((element == null) ? null : element.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setElement(" 
					+ ((element == null) ? null : element.toBaseString()) + ")");
		}
		return flag;
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(Comment.class) ? this : new Comment(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(Comment.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof Comment) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((Comment) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final Comment element) {
		if (element != null) {
			this.baseElement = element.baseElement;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseElement != null && this.baseElement.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseElement.getId());
			super.addInnerElementDelegate(this.baseElement, ge);
		}
	}


	@Override
	public Comment cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(Comment.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (Comment) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Comment clonedElement = (Comment) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Comment cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Comment) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Comment)) 
				? null : cloneToElement((Comment) ce, null);
	}

	private Comment cloneToElement(final Comment clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.baseElement = (this.baseElement != null) 
				? (BaseElement) this.baseElement.cloneElement(clonedElementMap) 
				: clonedElement.baseElement;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (Comment.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
