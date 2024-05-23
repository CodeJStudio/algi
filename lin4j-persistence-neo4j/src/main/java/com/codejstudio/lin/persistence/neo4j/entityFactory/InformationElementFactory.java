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
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class InformationElementFactory extends AbstractRelationableInformationElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_DESCRIPTION = "description";
	public static final String PROPERTY_KEY_SUB_INFORMATION_ELEMENT_GROUP = "subInformationElementGroup";

	public static final String KEY_PREFIX_SEPM = InformationElement.SUB_ELEMENT_POSITION_NAME 
			+ WordSeparator.UNDERSCORE.getCharacter();

	public static final String DEFAULT_LABEL = InformationElement.class.getSimpleName();


	/* variables */

	private static InformationElementFactory FACTORY;


	/* constructors */

	protected InformationElementFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(InformationElement.class), 
					InformationElementFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static InformationElementFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new InformationElementFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (InformationElement) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final InformationElement element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		String dsc = element.getDescription();
		if (dsc != null) {
			propertyContainer.setProperty(PROPERTY_KEY_DESCRIPTION, dsc);
		}

		BaseElement bsieg = element.getBaseSubInformationElementGroup();
		if (bsieg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_SUB_INFORMATION_ELEMENT_GROUP, BaseElementFactory.convertToPlainText(bsieg));
		}

		Map<String, String> sepm = element.getSubElementPosition();
		if (!CollectionUtil.checkNullOrEmpty(sepm)) {
			Set<String> sepks = sepm.keySet();
			for (String k : sepks) {
				propertyContainer.setProperty(KEY_PREFIX_SEPM + k, sepm.get(k));
			}
		}
	}


	@Override
	public InformationElement loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public InformationElement loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		InformationElement element = (InformationElement) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, InformationElement element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_DESCRIPTION)) {
			Object dsc = propertyContainer.getProperty(PROPERTY_KEY_DESCRIPTION);
			if (dsc instanceof String) {
				element.setDescription((String) dsc);
			}
		}

		if (apks.contains(PROPERTY_KEY_SUB_INFORMATION_ELEMENT_GROUP)) {
			Object sieg = propertyContainer.getProperty(PROPERTY_KEY_SUB_INFORMATION_ELEMENT_GROUP);
			if (sieg instanceof String) {
				element.setBaseSubInformationElementGroup(
						BaseElementFactory.convertToElement((String) sieg));
			}
		}

		if (!apks.isEmpty()) {
			Map<String, String> sepm = CollectionUtil.generateNewMap();
			for (String key : apks) {
				if (key.startsWith(KEY_PREFIX_SEPM)) {
					String k = key.substring(KEY_PREFIX_SEPM.length());
					Object p = propertyContainer.getProperty(key);
					if (p instanceof String) {
						sepm.put(k, (String) p);
					}
				}
			}
			if (!CollectionUtil.checkNullOrEmpty(sepm)) {
				element.setSubElementPosition(sepm);
			}
		}
	}

}
