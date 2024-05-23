package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class AbstractRelationableInformationElementFactory extends OwnableInformationElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_RELATION_GROUP = "relationGroup";

	public static final String DEFAULT_LABEL = AbstractRelationableInformationElement.class.getSimpleName();


	/* variables */

	private static AbstractRelationableInformationElementFactory FACTORY;


	/* constructors */

	protected AbstractRelationableInformationElementFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(
					BaseElement.getSubPojoClassCollection(AbstractRelationableInformationElement.class), 
					AbstractRelationableInformationElementFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static AbstractRelationableInformationElementFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new AbstractRelationableInformationElementFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (AbstractRelationableInformationElement) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, 
			final AbstractRelationableInformationElement element, final boolean superPropertyFlag) {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement brg = element.getBaseRelationGroup();
		if (brg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_RELATION_GROUP, BaseElementFactory.convertToPlainText(brg));
		}
	}


	@Override
	public AbstractRelationableInformationElement loadNode(final Node node, final Label label) 
			throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public AbstractRelationableInformationElement loadNode(final Node node, final Label label, 
			final boolean initTypeFlag) throws LIMException {
		AbstractRelationableInformationElement element = 
				(AbstractRelationableInformationElement) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, 
			AbstractRelationableInformationElement element, final boolean initTypeFlag, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_RELATION_GROUP)) {
			Object rg = propertyContainer.getProperty(PROPERTY_KEY_RELATION_GROUP);
			if (rg instanceof String) {
				element.setBaseRelationGroup(BaseElementFactory.convertToElement((String) rg));
			}
		}
	}

}
