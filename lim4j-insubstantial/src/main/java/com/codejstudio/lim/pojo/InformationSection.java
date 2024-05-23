package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * InformationSection.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = InformationSection.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class InformationSection extends InformationElement {

	/* constants */

	private static final long serialVersionUID = -6890668143951132339L;

	public static final String TYPE_NAME = "information-section";


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public InformationSection() {
		super();
	}

	public InformationSection(InformationSection element) throws LIMException {
		super(element);
	}

	public InformationSection(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public InformationSection(boolean initIdFlag, boolean initTypeFlag, String description) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public InformationSection(String description) throws LIMException {
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

			PojoElementClassConstant.registerElementClassForInit(InformationSection.class);
			JAXBBoundClassConstant.registerBoundClassForInit(InformationSection.class);
			InformationSection.registerSubPojoClassForInit(InformationSection.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(InformationSection.class) 
					? this : new InformationSection(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(InformationSection.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (InformationSection.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
