package com.codejstudio.lim.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.codejstudio.lim.common.collection.MultiValueMap;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.DynamicableUtil;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lim.pojo.util.Position;
import com.codejstudio.lim.pojo.util.XmlAttributeMapAdapter;

/**
 * InformationElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = InformationElement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"description",
	"baseSubInformationElementGroup",
	"subElementPosition",
})
public class InformationElement extends AbstractRelationableInformationElement {

	/* constants */

	private static final long serialVersionUID = -8355194018170968564L;

	public static final String TYPE_NAME = "information-element";

	public static final String SUB_ELEMENT_POSITION_NAME = "sub-element-positions";


	/* variables */

	private Description descriptionObject;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "sub-information-element-group")
	private BaseElement baseSubInformationElementGroup;

	private InformationElementGroup subInformationElementGroup;

	private Map<String, String> subElementPositionMap;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public InformationElement() {
		super();
	}

	public InformationElement(InformationElement element) throws LIMException {
		super(element);
		load(element);
	}

	public InformationElement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public InformationElement(boolean initIdFlag, boolean initTypeFlag, String description) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setDescription(description);
	}

	public InformationElement(boolean initIdFlag, boolean initTypeFlag, String description, 
			boolean encodeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
		setDescription(description, encodeFlag);
	}

	protected InformationElement(boolean initIdFlag, boolean initTypeFlag, Description descriptionObject) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setDescriptionObject(descriptionObject);
	}


	public InformationElement(String description) throws LIMException {
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

			PojoElementClassConstant.registerElementClassForInit(InformationElement.class);
			JAXBBoundClassConstant.registerBoundClassForInit(InformationElement.class);
			InformationElement.registerSubPojoClassForInit(InformationElement.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initSubInformationElementGroup() throws LIMException {
		if (this.subInformationElementGroup == null) {
			this.subInformationElementGroup = new InformationElementGroup(true);
			super.addInnerElementDelegate(this.subInformationElementGroup);
			this.baseSubInformationElementGroup = new BaseElement(subInformationElementGroup);
		}
	}

	private void initSubElementPositionMap() throws LIMException {
		if (this.subElementPositionMap == null) {
			this.subElementPositionMap = CollectionUtil.generateNewMap();
		}
	}


	/* destroyers */

	private void destroySubInformationElementGroup() {
		if (this.subInformationElementGroup != null && this.subInformationElementGroup.size() == 0) {
			this.baseSubInformationElementGroup = null;
			super.removeInnerElementDelegate(this.subInformationElementGroup.getId());
			this.subInformationElementGroup = null;
		}
	}

	private void destroySubElementPositionMap() {
		if (this.subElementPositionMap != null && this.subElementPositionMap.size() == 0) {
			this.subElementPositionMap = null;
		}
	}


	/* getters & setters */

	@XmlElement(name = "description")
	public String getDescription() throws LIMException {
		return (this.descriptionObject == null) ? null : this.descriptionObject.getDecodedText();
	}

	public void setDescription(final String description) throws LIMException {
		setDescription(description, true);
	}

	public void setDescription(final String description, final boolean encodeFlag) throws LIMException {
		setDescription(description, encodeFlag, true);
	}

	protected void setDescription(final String description, final boolean encodeFlag, 
			final boolean dynamicFlag) throws LIMException {
		this.descriptionObject = StringUtil.isEmpty(description) 
				? null : new Description(description, encodeFlag);
		if (dynamicFlag && this.descriptionObject != null) {
			super.updateDynamicInformationByDescription(this.descriptionObject.getEncodedText());
		}
		ElementTrace.log.info(this.toBaseString() + ": setDescription(\"" + description + "\")");
	}

	@XmlTransient
	public Description getDescriptionObject() {
		return descriptionObject;
	}

	protected void setDescriptionObject(final Description descriptionObject) {
		this.descriptionObject = descriptionObject;
		if (descriptionObject != null) {
			super.updateDynamicInformationByDescription(descriptionObject.getEncodedText());
		}
		ElementTrace.log.info(this.toBaseString() + ": setDescriptionObject(\"" + descriptionObject + "\")");
	}


	@XmlTransient
	public BaseElement getBaseSubInformationElementGroup() {
		return baseSubInformationElementGroup;
	}

	public void setBaseSubInformationElementGroup(BaseElement baseSubInformationElementGroup) {
		this.baseSubInformationElementGroup = baseSubInformationElementGroup;
	}

	public InformationElementGroup getSubInformationElementGroup() {
		return subInformationElementGroup;
	}

	/**
	 * for JAXB usage of unmarshalling
	 */
	@XmlElement(name = SUB_ELEMENT_POSITION_NAME)
	@XmlJavaTypeAdapter(value = XmlAttributeMapAdapter.class)
	public Map<String, String> getSubElementPosition() {
		return subElementPositionMap;
	}

	/**
	 * for JAXB usage of unmarshalling
	 */
	public void setSubElementPosition(Map<String, String> subElementPositionMap) {
		this.subElementPositionMap = subElementPositionMap;
	}


	/* CRUD for arrays, collections, maps, groups: sub-information-elements */

	public boolean containSubInformationElement(final InformationElement element) {
		return (this.subInformationElementGroup == null) 
				? false : this.subInformationElementGroup.containGroupElement(element);
	}

	public boolean containSubInformationElement(final String elementId) {
		return (this.subInformationElementGroup == null) 
				? false : this.subInformationElementGroup.containGroupElement(elementId);
	}


	public boolean addSubInformationElementWithPosition(final InformationElement... elements) 
			throws LIMException {
		return addSubInformationElement(null, elements);
	}

	public boolean addSubInformationElementWithPosition(final List<InformationElement> elementList) 
			throws LIMException {
		return addSubInformationElement(null, elementList);
	}

	public boolean addSubInformationElement(final Integer[] indexArray, 
			final InformationElement... elements) throws LIMException {
		return addSubInformationElement(indexArray, ((elements == null) ? null : Arrays.asList(elements)));
	}

	public boolean addSubInformationElement(final Integer[] indexArray, 
			final List<InformationElement> elementList) throws LIMException {
		return addSubInformationElement(elementList) 
				& putSubElementPositionDelegate(indexArray, elementList);
	}

	public boolean addSubInformationElement(final InformationElement... elements) throws LIMException {
		return addSubInformationElement((elements == null) ? null : Arrays.asList(elements));
	}

	public boolean addSubInformationElement(final List<InformationElement> elementList) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementList)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addSubInformationElement(" 
					+ BaseElement.toBaseString(elementList) + ")");
			return false;
		}

		try {
			initSubInformationElementGroup();
			boolean flag = super.addInnerElementDelegate(elementList) 
					& this.subInformationElementGroup.addGroupElement(elementList);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addSubInformationElement(" 
						+ BaseElement.toBaseString(elementList) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addSubInformationElement(" 
						+ BaseElement.toBaseString(elementList) + ")");
			}
			return flag;
		} finally {
			destroySubInformationElementGroup();
		}
	}


	public boolean removeSubInformationElement(final String elementId) {
		if (elementId == null || !containSubInformationElement(elementId)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeSubInformationElement(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(elementId) 
					& this.subInformationElementGroup.removeGroupElement(elementId);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeSubInformationElement(" 
						+ id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeSubInformationElement(" 
						+ id + ")");
			}
			return flag;
		} finally {
			destroySubInformationElementGroup();
		}
	}


	/* CRUD for arrays, collections, maps, groups: sub-element positions */

	public int subElementPositionSize() {
		return (this.subElementPositionMap == null) ? 0 : this.subElementPositionMap.size();
	}

	protected Collection<Position> getSubElementPositionDelegate(final String elementId) 
			throws LIMException {
		if (elementId == null || CollectionUtil.checkNullOrEmpty(this.subElementPositionMap)) {
			return null;
		}

		Collection<Position> psc = CollectionUtil.generateNewCollection();
		for (Entry<String, String> sse : this.subElementPositionMap.entrySet()) {
			if (elementId.equals(sse.getValue())) {
				psc.add(new Position(sse.getKey()));
			}
		}
		return psc;
	}

	public InformationElement findSubElementByIndex(final int index, final int lengthOfRest) 
			throws LIMException {
		if (this.descriptionObject == null || CollectionUtil.checkNullOrEmpty(this.subElementPositionMap) 
				|| index < 0 || lengthOfRest < 0) {
			return null;
		}

		Position ps = new Position(index, (this.descriptionObject.length() - index - lengthOfRest));
		String elementId = this.subElementPositionMap.get(ps.getPositionInfo());
		GenericElement ge = super.getInnerElementDelegate(elementId);
		return !(ge instanceof InformationElement) ? null : (InformationElement) ge;
	}

	protected boolean putSubElementPositionDelegate(final InformationElement... subElements) 
			throws LIMException {
		return putSubElementPositionDelegate(null, subElements);
	}

	protected boolean putSubElementPositionDelegate(final List<InformationElement> subElementList) 
			throws LIMException {
		return putSubElementPositionDelegate(null, subElementList);
	}

	protected boolean putSubElementPositionDelegate(final Integer[] indexArray, 
			final InformationElement... subElements) throws LIMException {
		return putSubElementPositionDelegate(indexArray, 
				((subElements == null) ? null : Arrays.asList(subElements)));
	}

	public boolean putSubElementPositionDelegate(final Integer[] indexArray, 
			final List<? extends InformationElement> subElementList) throws LIMException {
		if (this.descriptionObject == null || CollectionUtil.checkNullOrEmpty(subElementList)) {
			return false;
		}

		MultiValueMap<InformationElement, Integer> ieimvm = CollectionUtil.generateNewMultiValueMap();
		for (int i = 0; i < subElementList.size(); i++) {
			InformationElement ie = subElementList.get(i);
			String d;
			if (ie!= null && (d = ie.getDescription()) != null) {
				if (indexArray != null && i < indexArray.length 
						&& indexArray[i] != null && indexArray[i] >= 0) {
					ieimvm.put(ie, indexArray[i]);
				} else {
					Integer idx = this.descriptionObject.indexOf(d, 0);
					while (idx >= 0) {
						ieimvm.put(ie, idx);
						idx = this.descriptionObject.indexOf(d, idx + 1);
					}
				}
			}
		}
		return putSubElementPositionDelegate(ieimvm);
	}

	public boolean putSubElementPositionDelegate(
			final MultiValueMap<InformationElement, Integer> subElementIndexMVMap) throws LIMException {
		if (this.descriptionObject == null || CollectionUtil.checkNullOrEmpty(subElementIndexMVMap)) {
			return false;
		}

		boolean flag = true;
		for (Entry<InformationElement, Integer> ieie : subElementIndexMVMap.entries()) {
			if (ieie == null || ieie.getValue() == null 
					|| ieie.getKey() == null || ieie.getKey().descriptionObject == null) {
				continue;
			}
			Position ps = new Position(ieie.getValue(), ieie.getKey().descriptionObject.length());
			flag &= putSubElementPositionDelegate(ieie.getKey().getId(), ps);
		}
		return flag;
	}

	protected boolean putSubElementPositionDelegate(final String subElementId, final Position position) 
			throws LIMException {
		String psi;
		if (subElementId == null || position == null || (psi = position.getPositionInfo()) == null) {
			ElementTrace.log.warn(this.toBaseString() + "fail to putSubElementPositionDelegate(" 
					+ "subElementId: " + subElementId + ", position: " + position + ")");
			return false;
		}

		try {
			initSubElementPositionMap();
			this.subElementPositionMap.put(psi, subElementId);
			ElementTrace.log.info(this.toBaseString() + ": putSubElementPositionDelegate(" 
					+ "subElementId: " + subElementId + ", position: " + position + ")");
			return true;
		} finally {
			destroySubElementPositionMap();
		}
	}

	protected boolean removeSubElementPositionDelegate(final String subElementId) 
			throws LIMException {
		if (subElementId == null || CollectionUtil.checkNullOrEmpty(this.subElementPositionMap)) {
			ElementTrace.log.warn(this.toBaseString() 
					+ "fail to putSubElementPositionDelegate(" + subElementId + ")");
			return false;
		}

		try {
			Set<Entry<String, String>> sses = CollectionUtil.generateNewSet();
			for (Entry<String, String> sse : this.subElementPositionMap.entrySet()) {
				if (subElementId.equals(sse.getValue())) {
					sses.add(sse);
				}
			}
			boolean flag = CollectionUtil.removeAll(this.subElementPositionMap, sses);

			if (flag) {
				ElementTrace.log.info(this.toBaseString() 
						+ ": putSubElementPositionDelegate(" + subElementId + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() 
						+ "fail to putSubElementPositionDelegate(" + subElementId + ")");
			}
			return flag;
		} finally {
			destroySubElementPositionMap();
		}
	}


	/* overridden methods */

	@Override
	public String toString() {
		String sts = super.toString();
		String dsc = (this.descriptionObject == null) ? "" 
				: (TOSTRING_SYMBOL_SEPARATOR + "description=\"" 
						+ this.descriptionObject.getEncodedText() + "\"");
		return (sts.endsWith(TOSTRING_SYMBOL_RIGHT)) 
				? (sts.substring(0, (sts.length() - TOSTRING_SYMBOL_RIGHT.length())) 
						+ dsc + TOSTRING_SYMBOL_RIGHT) 
				: (sts + dsc);
	}


	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(InformationElement.class) 
					? this : new InformationElement(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(InformationElement.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof InformationElement) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((InformationElement) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final InformationElement element) {
		if (element != null) {
			this.descriptionObject = element.descriptionObject;
			this.baseSubInformationElementGroup = element.baseSubInformationElementGroup;
			this.subInformationElementGroup = element.subInformationElementGroup;
			this.subElementPositionMap = element.subElementPositionMap;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseSubInformationElementGroup != null 
				&& this.baseSubInformationElementGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseSubInformationElementGroup.getId());
			this.subInformationElementGroup = (ge instanceof InformationElementGroup) 
					? (InformationElementGroup) ge : this.subInformationElementGroup;
			super.addInnerElementDelegate(this.subInformationElementGroup);
		}
	}


	@Override
	public InformationElement cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(InformationElement.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (InformationElement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		InformationElement clonedElement = (InformationElement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public InformationElement cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof InformationElement) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof InformationElement)) 
				? null : cloneToElement((InformationElement) ce, null);
	}

	private InformationElement cloneToElement(final InformationElement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.descriptionObject = (this.descriptionObject != null) 
				? (Description) this.descriptionObject.cloneElement(clonedElementMap) 
				: clonedElement.descriptionObject;

		clonedElement.baseSubInformationElementGroup = (this.baseSubInformationElementGroup != null) 
				? (BaseElement) this.baseSubInformationElementGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseSubInformationElementGroup;
		clonedElement.subInformationElementGroup = (this.subInformationElementGroup != null) 
				? (InformationElementGroup) this.subInformationElementGroup.cloneElement(clonedElementMap) 
				: clonedElement.subInformationElementGroup;

		if (!CollectionUtil.checkNullOrEmpty(this.subElementPositionMap)) {
			clonedElement.initSubElementPositionMap();
			Set<String> sepks = this.subElementPositionMap.keySet();
			for (String k : sepks) {
				String value = this.subElementPositionMap.get(k);
				clonedElement.subElementPositionMap.put(k, value);
			}
			clonedElement.destroySubElementPositionMap();
		}

		return clonedElement;
	}


	/* class methods */

	public boolean descriptionEquals(final InformationElement element) {
		return (element == null) ? false : descriptionEquals(element.descriptionObject);
	}

	public boolean descriptionEquals(final Description descriptionObject) {
		return (descriptionObject == null && this.descriptionObject == null) 
				|| (descriptionObject != null && descriptionObject.equals(this.descriptionObject));
	}


	public InformationElement substituteDynamicSymbolByParameterType(final DynamicParameterType[] typeArray) 
			throws LIMException {
		return DynamicableUtil.substituteDynamicSymbolByParameterType(this, typeArray);
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (InformationElement.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
