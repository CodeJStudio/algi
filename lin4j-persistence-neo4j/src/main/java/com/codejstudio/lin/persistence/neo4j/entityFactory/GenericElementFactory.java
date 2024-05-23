package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Map;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class GenericElementFactory extends BaseElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_DYNAMIC = "dynamic";

	public static final String KEY_PREFIX_ITATM = IIntegratable.INTEGRATED_ATTACHMENT_XMLATTRIBUTE_NAME 
			+ WordSeparator.UNDERSCORE.getCharacter();
	public static final String KEY_PREFIX_ITEM = IIntegratable.INTEGRATED_ATTACHMENT_XMLELEMENT_NAME 
			+ WordSeparator.UNDERSCORE.getCharacter();

	public static final String DEFAULT_LABEL = GenericElement.class.getSimpleName();


	/* variables */

	private static GenericElementFactory FACTORY;


	/* constructors */

	protected GenericElementFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(GenericElement.class), 
					GenericElementFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static GenericElementFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new GenericElementFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (GenericElement) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final GenericElement element, 
			final boolean superPropertyFlag) {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		Boolean dn = element.getDynamic();
		if (dn != null) {
			propertyContainer.setProperty(PROPERTY_KEY_DYNAMIC, dn.toString());
		}

		Map<String, String> itatm = element.getIntegratedAttributeMap();
		if (!CollectionUtil.checkNullOrEmpty(itatm)) {
			Set<String> itatks = itatm.keySet();
			for (String k : itatks) {
				propertyContainer.setProperty(KEY_PREFIX_ITATM + k, itatm.get(k));
			}
		}

		Map<String, BaseElement> item = element.getIntegratedElementMap();
		if (!CollectionUtil.checkNullOrEmpty(item)) {
			Set<String> iteks = item.keySet();
			for (String k : iteks) {
				BaseElement value = item.get(k);
				propertyContainer.setProperty(
						KEY_PREFIX_ITEM + k, BaseElementFactory.convertToPlainText(value));
			}
		}
	}


	@Override
	public GenericElement loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public GenericElement loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		GenericElement element = (GenericElement) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, GenericElement element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_DYNAMIC)) {
			Object dn = propertyContainer.getProperty(PROPERTY_KEY_DYNAMIC);
			if (dn instanceof String) {
				element.setDynamic(new Boolean((String) dn));
			}
		}

		if (!apks.isEmpty()) {
			Map<String, String> itatm = CollectionUtil.generateNewMap();
			Map<String, BaseElement> item = CollectionUtil.generateNewMap();

			for (String key : apks) {
				if (key.startsWith(KEY_PREFIX_ITATM)) {
					String k = key.substring(KEY_PREFIX_ITATM.length());
					Object p = propertyContainer.getProperty(key);
					if (p instanceof String) {
						itatm.put(k, (String) p);
					}
				} else if (key.startsWith(KEY_PREFIX_ITEM)) {
					String k = key.substring(KEY_PREFIX_ITEM.length());
					Object p = propertyContainer.getProperty(key);
					if (p instanceof String) {
						item.put(k, BaseElementFactory.convertToElement((String) p));
					}
				}
			}
			if (!CollectionUtil.checkNullOrEmpty(itatm)) {
				element.setIntegratedAttributeMap(itatm);
			}
			if (!CollectionUtil.checkNullOrEmpty(item)) {
				element.setIntegratedElementMap(item);
			}
		}
	}

}
