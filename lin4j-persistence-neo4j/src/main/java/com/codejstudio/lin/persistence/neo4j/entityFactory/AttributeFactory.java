package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.attribute.Attribute;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class AttributeFactory extends InformationUnitFactory {

	/* constants */

	public static final String PROPERTY_KEY_COMPATIBLE = "compatible";
	public static final String PROPERTY_KEY_KEY = "key";
	public static final String PROPERTY_KEY_VALUE = "value";
	public static final String PROPERTY_KEY_VALUE_GROUP = "valueGroup";

	public static final String DEFAULT_LABEL = Attribute.class.getSimpleName();


	/* variables */

	private static AttributeFactory FACTORY;


	/* constructors */

	protected AttributeFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(Attribute.class), 
					AttributeFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static AttributeFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new AttributeFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (Attribute) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final Attribute element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		Boolean cm = element.getCompatible();
		if (cm != null) {
			propertyContainer.setProperty(PROPERTY_KEY_COMPATIBLE, cm.toString());
		}

		String k = element.getKey();
		if (k != null) {
			propertyContainer.setProperty(PROPERTY_KEY_KEY, k);
		}

		BaseElement bv = element.getBaseValue();
		if (bv != null) {
			propertyContainer.setProperty(PROPERTY_KEY_VALUE, BaseElementFactory.convertToPlainText(bv));
		}

		BaseElement bvg = element.getBaseValueGroup();
		if (bvg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_VALUE_GROUP, BaseElementFactory.convertToPlainText(bvg));
		}
	}


	@Override
	public Attribute loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public Attribute loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		Attribute element = (Attribute) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, Attribute element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_COMPATIBLE)) {
			Object cm = propertyContainer.getProperty(PROPERTY_KEY_COMPATIBLE);
			if (cm instanceof String) {
				element.setCompatible(new Boolean((String) cm));
			}
		}

		if (apks.contains(PROPERTY_KEY_KEY)) {
			Object k = propertyContainer.getProperty(PROPERTY_KEY_KEY);
			if (k instanceof String) {
				element.setKey((String) k);
			}
		}

		if (apks.contains(PROPERTY_KEY_VALUE)) {
			Object v = propertyContainer.getProperty(PROPERTY_KEY_VALUE);
			if (v instanceof String) {
				element.setBaseValue(BaseElementFactory.convertToElement((String) v));
			}
		}

		if (apks.contains(PROPERTY_KEY_VALUE_GROUP)) {
			Object vg = propertyContainer.getProperty(PROPERTY_KEY_VALUE_GROUP);
			if (vg instanceof String) {
				element.setBaseValueGroup(BaseElementFactory.convertToElement((String) vg));
			}
		}
	}

}
