package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class StatementFactory extends InformationUnitFactory {

	/* constants */

	public static final String PROPERTY_KEY_CONCEPT_GROUP = "conceptGroup";
	public static final String PROPERTY_KEY_CONDITION_GROUP = "conditionGroup";
	public static final String PROPERTY_KEY_ATTRIBUTE_GROUP = "attributeGroup";

	public static final String DEFAULT_LABEL = Statement.class.getSimpleName();


	/* variables */

	private static StatementFactory FACTORY;


	/* constructors */

	protected StatementFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(Statement.class), 
					StatementFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static StatementFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new StatementFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (Statement) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final Statement element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement bcg = element.getBaseConceptGroup();
		if (bcg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_CONCEPT_GROUP, BaseElementFactory.convertToPlainText(bcg));
		}

		BaseElement bcdg = element.getBaseConditionGroup();
		if (bcdg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_CONDITION_GROUP, BaseElementFactory.convertToPlainText(bcdg));
		}

		BaseElement batg = element.getBaseAttributeGroup();
		if (batg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_ATTRIBUTE_GROUP, BaseElementFactory.convertToPlainText(batg));
		}
	}


	@Override
	public Statement loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public Statement loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		Statement element = (Statement) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, Statement element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_CONCEPT_GROUP)) {
			Object cg = propertyContainer.getProperty(PROPERTY_KEY_CONCEPT_GROUP);
			if (cg instanceof String) {
				element.setBaseConceptGroup(BaseElementFactory.convertToElement((String) cg));
			}
		}

		if (apks.contains(PROPERTY_KEY_CONDITION_GROUP)) {
			Object cdg = propertyContainer.getProperty(PROPERTY_KEY_CONDITION_GROUP);
			if (cdg instanceof String) {
				element.setBaseConditionGroup(BaseElementFactory.convertToElement((String) cdg));
			}
		}

		if (apks.contains(PROPERTY_KEY_ATTRIBUTE_GROUP)) {
			Object atg = propertyContainer.getProperty(PROPERTY_KEY_ATTRIBUTE_GROUP);
			if (atg instanceof String) {
				element.setBaseAttributeGroup(BaseElementFactory.convertToElement((String) atg));
			}
		}
	}

}
