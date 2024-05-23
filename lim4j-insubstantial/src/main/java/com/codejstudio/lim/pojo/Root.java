package com.codejstudio.lim.pojo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.xml.sax.InputSource;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;

/**
 * Root.class

 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.BaseElement
 * @see     com.codejstudio.lim.pojo.i.IConvertible#getXmlElement()
 * @see     com.codejstudio.lim.pojo.i.IConvertible#getPojoElement()
 * @see     com.codejstudio.lim.pojo.i.ICloneable#cloneElement()
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = "lim-elements")
public final class Root implements Serializable {

	/* constants */

	private static final long serialVersionUID = 1411585829487140077L;

	private static final String PROPERTIES_FILENAME_JAXB = "jaxb-insubstantial";

	@XmlAttribute(name = "version")
	public static final String VERSION = "2.0";


	/* variables */

	private static JAXBContext context;


	/* variables: arrays, collections, maps, groups */

	/**
	 * only for JAXB usage
	 */
	private List<BaseElement> rootList;

	private List<GenericElement> xmlElementList;
	private List<GenericElement> pojoElementList;
	private Map<String, GenericElement> pojoElementMap;

	private List<GenericActionableElement> xmlActionableElementList;
	private List<GenericActionableElement> actionableElementList;
	private Map<String, GenericActionableElement> actionableElementMap;


	/* constructors */

	/**
	 * for JAXB auto unmarshalling usage
	 */
	public Root() {}

	public Root(GenericElement... elements) throws LIMException {
		this((elements == null) ? null : Arrays.asList(elements));
	}

	public Root(Collection<GenericElement> elementCollection) throws LIMException {
		this(elementCollection, false);
	}

	public Root(Collection<GenericElement> elementCollection, boolean xmlFlag) throws LIMException {
		this(null, elementCollection, xmlFlag);
	}

	public Root(Collection<GenericActionableElement> actionableElementCollection, 
			Collection<GenericElement> elementCollection) throws LIMException {
		this(actionableElementCollection, elementCollection, false);
	}

	public Root(Collection<GenericActionableElement> actionableElementCollection, 
			Collection<GenericElement> elementCollection, boolean xmlFlag) throws LIMException {
		if (xmlFlag) {
			setXmlActionableElementList((actionableElementCollection instanceof List) 
					? (List<GenericActionableElement>) actionableElementCollection 
					: CollectionUtil.convertToList(GenericActionableElement.class, elementCollection));
			setXmlElementList((elementCollection instanceof List) 
					? (List<GenericElement>) elementCollection 
					: CollectionUtil.convertToList(GenericElement.class, elementCollection));
		} else {
			addActionableElement(actionableElementCollection);
			addElement(elementCollection);
		}
	}


	/* initializers */

	private void initRootList() throws LIMException {
		if (this.rootList == null) {
			this.rootList = CollectionUtil.generateNewList();
		}
	}

	private void initXmlElementList() throws LIMException {
		if (this.xmlElementList == null) {
			this.xmlElementList = CollectionUtil.generateNewList();
		}
	}

	private void initPojoElementList() throws LIMException {
		if (this.pojoElementList == null) {
			this.pojoElementList = CollectionUtil.generateNewList();
		}
	}

	private boolean initPojoElementMap() throws LIMException {
		if (this.pojoElementMap == null) {
			this.pojoElementMap = CollectionUtil.generateNewMap();
			return true;
		}
		return false;
	}

	private void initXmlActionableElementList() throws LIMException {
		if (this.xmlActionableElementList == null) {
			this.xmlActionableElementList = CollectionUtil.generateNewList();
		}
	}

	private void initActionableElementList() throws LIMException {
		if (this.actionableElementList == null) {
			this.actionableElementList = CollectionUtil.generateNewList();
		}
	}

	private boolean initActionableElementMap() throws LIMException {
		if (this.actionableElementMap == null) {
			this.actionableElementMap = CollectionUtil.generateNewMap();
			return true;
		}
		return false;
	}


	/* destroyers */

	private void destroyRootList(final boolean forceFlag) {
		if (forceFlag || (this.rootList != null && this.rootList.size() == 0)) {
			this.rootList = null;
		}
	}

	private void destroyXmlElementList(final boolean forceFlag) {
		if (forceFlag || (this.xmlElementList != null && this.xmlElementList.size() == 0)) {
			this.xmlElementList = null;
		}
	}

	private void destroyPojoElementList(final boolean forceFlag) {
		if (forceFlag || (this.pojoElementList != null && this.pojoElementList.size() == 0)) {
			this.pojoElementList = null;
		}
	}

	private void destroyPojoElementMap(final boolean forceFlag) {
		if (forceFlag || (this.pojoElementMap != null && this.pojoElementMap.size() == 0)) {
			this.pojoElementMap = null;
		}
	}

	private void destroyXmlActionableElementList(final boolean forceFlag) {
		if (forceFlag || (this.xmlActionableElementList != null && this.xmlActionableElementList.size() == 0)) {
			this.xmlActionableElementList = null;
		}
	}

	private void destroyActionableElementList(final boolean forceFlag) {
		if (forceFlag || (this.actionableElementList != null && this.actionableElementList.size() == 0)) {
			this.actionableElementList = null;
		}
	}

	private void destroyActionableElementMap(final boolean forceFlag) {
		if (forceFlag || (this.actionableElementMap != null && this.actionableElementMap.size() == 0)) {
			this.actionableElementMap = null;
		}
	}


	/* getters & setters */

	private static JAXBContext getContext() throws LIMException, JAXBException {
		if (context == null) {
			System.clearProperty("javax.xml.bind.context.factory");

			Collection<Class<?>> clc = CollectionUtil.generateNewCollection();
			clc.add(Root.class);
			clc.addAll(JAXBBoundClassConstant.getBoundClassCollection());
			context = JAXBContext.newInstance(clc.toArray(new Class[0]));
		}
		return context;
	}


	/**
	 * only for JAXB usage of unmarshalling
	 */
	@XmlMixed
	@XmlElementRefs(value = {@XmlElementRef(name = BaseElement.TYPE_NAME, type = BaseElement.class), })
	public List<BaseElement> getRootList() throws LIMException {
		initRootList();
		return rootList;
	}

	public void setRootList(final List<BaseElement> rootList) throws LIMException {
		this.rootList = rootList;
		if (rootList == null) {
			destroyXmlElementList(true);
			destroyXmlElementList(true);
		} else {
			initXmlElementList();
			this.xmlElementList.clear();
			initXmlActionableElementList();
			this.xmlActionableElementList.clear();
			for (Object obj : rootList) {
				if (obj instanceof GenericElement) {
					this.xmlElementList.add((GenericElement) obj);
				} else if (obj instanceof GenericActionableElement) {
					this.xmlActionableElementList.add((GenericActionableElement) obj);
				}
			}
		}
	}

	private void updateRootList() throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.xmlActionableElementList) 
				&& CollectionUtil.checkNullOrEmpty(this.xmlElementList)) {
			destroyRootList(true);
		}

		initRootList();
		this.rootList.clear();
		CollectionUtil.addAllOfSubClass(this.rootList, this.xmlElementList);
		CollectionUtil.addAllOfSubClass(this.rootList, this.xmlActionableElementList);
		destroyRootList(false);
	}


	@XmlTransient
	public List<GenericElement> getXmlElementList() throws LIMException {
		return xmlElementList;
	}

	public void setXmlElementList(final List<GenericElement> xmlElementList) throws LIMException {
		this.xmlElementList = xmlElementList;
		updateRootList();
	}

	@XmlTransient
	public List<GenericElement> getPojoElementList() {
		return pojoElementList;
	}

	@XmlTransient
	private Map<String, GenericElement> getPojoElementMap() throws LIMException {
		generatePojoElementMap();
		return this.pojoElementMap;
	}

	private void generatePojoElementMap() throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.pojoElementList)) {
			return;
		}

		if (initPojoElementMap()) {
			for (Object obj : this.pojoElementList) {
				if (obj instanceof GenericElement) {
					GenericElement ge = (GenericElement) obj;
					this.pojoElementMap.put(ge.id, ge);
				}
			}
		}
	}

	private void regeneratePojoElementMap() throws LIMException {
		destroyPojoElementMap(true);
		generatePojoElementMap();
	}


	@XmlTransient
	public List<GenericActionableElement> getXmlActionableElementList() throws LIMException {
		return xmlActionableElementList;
	}

	public void setXmlActionableElementList(final List<GenericActionableElement> xmlActionableElementList) 
			throws LIMException {
		this.xmlActionableElementList = xmlActionableElementList;
		updateRootList();
	}

	@XmlTransient
	public List<GenericActionableElement> getActionableElementList() {
		return actionableElementList;
	}

	@XmlTransient
	private Map<String, GenericActionableElement> getActionableElementMap() throws LIMException {
		generateActionableElementMap();
		return actionableElementMap;
	}

	private void generateActionableElementMap() throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.actionableElementList)) {
			return;
		}

		if (initActionableElementMap()) {
			for (Object obj : this.actionableElementList) {
				if (obj instanceof GenericActionableElement) {
					GenericActionableElement gae = (GenericActionableElement) obj;
					this.actionableElementMap.put(gae.id, gae);
				}
			}
		}
	}

	private void regenerateActionableElementMap() throws LIMException {
		destroyActionableElementMap(true);
		generateActionableElementMap();
	}


	/* CRUD for arrays, collections, maps, groups: elements */

	public GenericElement getElement(final String elementId) throws LIMException {
		return (elementId == null 
						|| (CollectionUtil.checkNullOrEmpty(this.pojoElementList) 
								&& CollectionUtil.checkNullOrEmpty(this.pojoElementMap))) 
				? null : this.getPojoElementMap().get(elementId);
	}

	public boolean containElement(final GenericElement element) {
		return (this.pojoElementList == null) ? false : this.pojoElementList.contains(element);
	}


	public boolean addElement(final GenericElement... elements) throws LIMException {
		return addElement(true, elements);
	}

	public boolean addElement(final Collection<GenericElement> elementCollection) throws LIMException {
		return addElement(true, elementCollection);
	}

	public boolean addElement(final boolean subElementsFlag, final GenericElement... elements) 
			throws LIMException {
		return addElement(subElementsFlag, (elements == null) ? null : Arrays.asList(elements));
	}

	public boolean addElement(final boolean subElementsFlag, 
			final Collection<GenericElement> elementCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return false;
		}

		initRootList();
		initPojoElementList();
		initXmlElementList();
		initPojoElementMap();
		initActionableElementList();
		initXmlActionableElementList();
		initActionableElementMap();

		boolean flag = true;
		for (GenericElement ge : elementCollection) {
			flag &= addElement(subElementsFlag, ge);
		}

		destroyRootList(false);
		destroyPojoElementList(false);
		destroyXmlElementList(false);
		destroyPojoElementMap(false);
		destroyActionableElementList(false);
		destroyXmlActionableElementList(false);
		destroyActionableElementMap(false);

		return flag;
	}

	private boolean addElement(final boolean subElementsFlag, final GenericElement element) 
			throws LIMException {
		if (element == null || this.pojoElementList.contains(element)) {
			return false;
		}

		boolean flag = true;
		IConvertible icvb;
		if (element != null && (icvb = element.getXmlElement()) instanceof GenericElement) {
			flag &= this.pojoElementList.add(element) & this.xmlElementList.add((GenericElement) icvb) 
					& this.rootList.add((BaseElement) icvb);
			this.pojoElementMap.put(element.id, element);
		}

		Collection<GenericActionableElement> gaec;
		if (subElementsFlag 
				&& !CollectionUtil.checkNullOrEmpty((gaec = element.getInnerActionableElementCollection())) 
				&& gaec.size() > 1) {
			for (GenericActionableElement gae : gaec) {
				IConvertible xcvb;
				if (gae == null || this.actionableElementList.contains(gae) 
						|| !((xcvb = gae.getXmlElement()) instanceof GenericActionableElement)) {
					continue;
				}
				flag &= this.actionableElementList.add((GenericActionableElement) xcvb) 
						& this.xmlActionableElementList.add((GenericActionableElement) xcvb) 
						& this.rootList.add((BaseElement) xcvb);
				this.actionableElementMap.put(gae.id, gae);
			}
		}

		Collection<GenericElement> iec;
		if (subElementsFlag 
				&& !CollectionUtil.checkNullOrEmpty((iec = element.getInnerElementCollection())) 
				&& iec.size() > 1) {
			for (GenericElement subElement : iec) {
				flag &= addElement(subElementsFlag, subElement);
			}
		}

		return flag;
	}


	public boolean removeElement(final GenericElement... elements) throws LIMException {
		throw new LIMException(new UnsupportedOperationException());
	}


	/* CRUD for arrays, collections, maps, groups: actionable elements */

	public GenericActionableElement getActionableElement(final String elementId) throws LIMException {
		return (elementId == null 
						|| (CollectionUtil.checkNullOrEmpty(this.actionableElementList) 
								&& CollectionUtil.checkNullOrEmpty(this.actionableElementMap))) 
				? null : this.getActionableElementMap().get(elementId);
	}

	public boolean containActionableElement(final GenericActionableElement element) {
		return (this.actionableElementList == null) ? false : this.actionableElementList.contains(element);
	}


	public boolean addActionableElement(final GenericActionableElement... actionableElements) 
			throws LIMException {
		return addActionableElement((actionableElements == null) ? null : Arrays.asList(actionableElements));
	}

	public boolean addActionableElement(
			final Collection<GenericActionableElement> actionableElementCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(actionableElementCollection)) {
			return false;
		}

		initRootList();
		initActionableElementList();
		initXmlActionableElementList();
		initActionableElementMap();

		boolean flag = true;
		for (GenericActionableElement gae : actionableElementCollection) {
			IConvertible icvb;
			if (gae == null || this.actionableElementList.contains(gae) 
					|| !((icvb = gae.getXmlElement()) instanceof GenericActionableElement)) {
				continue;
			}
			flag &= this.actionableElementList.add((GenericActionableElement) icvb) 
					& this.xmlActionableElementList.add((GenericActionableElement) icvb) 
					& this.rootList.add((BaseElement) icvb);
			this.actionableElementMap.put(gae.id, gae);
		}

		destroyRootList(false);
		destroyActionableElementList(false);
		destroyXmlActionableElementList(false);
		destroyActionableElementMap(false);

		return flag;
	}


	/* redecorate methods, for unmarshalling process */

	public void redecorate() throws LIMException {
		setRootList(this.rootList);

		cloneElementListFromXmlToPojo();
		cloneActionableElementListFromXmlToPojo();

		generatePojoElementMap();
		generateActionableElementMap();

		doRedecoratePojoElements(false);
		doRedecorateActionableElements(false);

		copyListFromPojoToXml();
	}

	private void cloneElementListFromXmlToPojo() throws LIMException {
		destroyPojoElementList(true);
		if (CollectionUtil.checkNullOrEmpty(this.xmlElementList)) {
			return;
		}

		initPojoElementList();
		Map<String, BaseElement> sbem = CollectionUtil.generateNewMap();
		for (GenericElement ge : this.xmlElementList) {
			this.pojoElementList.add(ge.cloneElement(sbem));
		}
	}

	private void cloneActionableElementListFromXmlToPojo() throws LIMException {
		destroyActionableElementList(true);
		if (CollectionUtil.checkNullOrEmpty(this.xmlActionableElementList)) {
			return;
		}

		initActionableElementList();
		Map<String, BaseElement> sbem = CollectionUtil.generateNewMap();
		for (GenericActionableElement gae : this.xmlActionableElementList) {
			this.actionableElementList.add(gae.cloneElement(sbem));
		}
	}

	private void doRedecoratePojoElements(boolean finalRoundFlag) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.pojoElementList)) {
			return;
		}

		List<GenericElement> gel = CollectionUtil.generateNewList();
		boolean pojoFlag = false;
		for (GenericElement ge : this.pojoElementList) {
			IConvertible icvb;
			if (ge == null 
					|| !((icvb = ge.getPojoElement(this.pojoElementMap, this.actionableElementMap)) 
							instanceof GenericElement)) {
				continue;
			}
			gel.add((GenericElement) icvb);
			pojoFlag |= !ge.equals(icvb);
		}
		this.pojoElementList = gel;
		regeneratePojoElementMap();

		if (pojoFlag || !finalRoundFlag) {
			finalRoundFlag = !pojoFlag;
			doRedecoratePojoElements(finalRoundFlag);
		}
	}

	private void doRedecorateActionableElements(boolean finalRoundFlag) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.actionableElementList)) {
			return;
		}

		List<GenericActionableElement> gael = CollectionUtil.generateNewList();
		boolean pojoFlag = false;
		for (GenericActionableElement gae : this.actionableElementList) {
			IConvertible icvb;
			if (gae == null 
					|| !((icvb = gae.getPojoElement(this.pojoElementMap, this.actionableElementMap)) 
							instanceof GenericActionableElement)) {
				continue;
			}
			gael.add((GenericActionableElement) icvb);
			pojoFlag |= !gae.equals(icvb);
		}
		this.actionableElementList = gael;
		regenerateActionableElementMap();

		if (pojoFlag || !finalRoundFlag) {
			finalRoundFlag = !pojoFlag;
			doRedecorateActionableElements(finalRoundFlag);
		}
	}

	private void copyListFromPojoToXml() throws LIMException {
		destroyRootList(true);
		destroyXmlElementList(true);
		destroyXmlActionableElementList(true);

		initRootList();

		if (!CollectionUtil.checkNullOrEmpty(this.pojoElementList)) {
			initXmlElementList();
			for (GenericElement ge : this.pojoElementList) {
				IConvertible icvb;
				if (ge == null || !((icvb = ge.getXmlElement()) instanceof GenericElement)) {
					continue;
				}
				this.xmlElementList.add((GenericElement) icvb);
				this.rootList.add((BaseElement) icvb);
			}
		}

		if (!CollectionUtil.checkNullOrEmpty(this.actionableElementList)) {
			initXmlActionableElementList();
			for (GenericActionableElement gae : this.actionableElementList) {
				IConvertible icvb;
				if (gae == null || !((icvb = gae.getXmlElement()) instanceof GenericActionableElement)) {
					continue;
				}
				this.xmlActionableElementList.add((GenericActionableElement) icvb);
				this.rootList.add((BaseElement) icvb);
			}
		}

		destroyRootList(false);
	}


	/* JAXB methods, for marshalling & unmarshalling processes */

	public void marshalToXml(final OutputStream os) throws LIMException {
		marshalToXml(true, os);
	}

	public void marshalToXml(final boolean sortByIdFlag, final OutputStream os) throws LIMException {
		if (sortByIdFlag) {
			xmlSort();
		}

		try {
			Marshaller msl = getContext().createMarshaller();

			String jaxbEncoding = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_ENCODING);
			if (jaxbEncoding != null) {
				msl.setProperty(Marshaller.JAXB_ENCODING, jaxbEncoding);
			}

			String jaxbFormattedOutput = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_FORMATTED_OUTPUT);
			if (jaxbFormattedOutput != null) {
				msl.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.valueOf(jaxbFormattedOutput));
			}

			String jaxbSchemaLocation = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_SCHEMA_LOCATION);
			if (jaxbSchemaLocation != null) {
				msl.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, jaxbSchemaLocation);
			}

			String jaxbNoNamespaceSchemaLocation = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION);
			if (jaxbNoNamespaceSchemaLocation != null) {
				msl.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, jaxbNoNamespaceSchemaLocation);
			}

			String jaxbFragment = PropertiesLoader.getProperty(PROPERTIES_FILENAME_JAXB, 
					Marshaller.JAXB_FRAGMENT);
			if (jaxbFragment != null) {
				msl.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.valueOf(jaxbFragment));
			}

			msl.marshal(this, os);
		} catch (JAXBException e) {
			throw LIMException.getLIMException(e);
		}
	}

	/**
	 * Refer to BaseElement.compareTo(BaseElement element);
	 */
	private void xmlSort() {
		if (!CollectionUtil.checkNullOrEmpty(this.rootList)) {
			Collections.sort(this.rootList);
		}
	}


	public static Root unmarshalFromXml(final File file) throws LIMException {
		try {
			return unmarshalFromXml(new BufferedInputStream(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	public static Root unmarshalFromXml(final InputStream is) throws LIMException {
		return unmarshalFromXml(new InputSource(is));
	}

	public static Root unmarshalFromXml(final Reader reader) throws LIMException {
		return unmarshalFromXml(new InputSource(reader));
	}

	public static Root unmarshalFromXml(final URL url) throws LIMException {
		return unmarshalFromXml(new InputSource(url.toExternalForm()));
	}

	public static Root unmarshalFromXml(final InputSource source) throws LIMException {
		try {
			Unmarshaller umsl = getContext().createUnmarshaller();
			return (Root) umsl.unmarshal(source);
		} catch (JAXBException e) {
			throw LIMException.getLIMException(e);
		}
	}

}
