package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.OwnableInformationElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class OwnableInformationElementFactory extends GenericElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_PROPOSER = "proposer";
	public static final String PROPERTY_KEY_OBSERVER_GROUP = "observerGroup";

	public static final String DEFAULT_LABEL = OwnableInformationElement.class.getSimpleName();


	/* variables */

	private static OwnableInformationElementFactory FACTORY;


	/* constructors */

	protected OwnableInformationElementFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(OwnableInformationElement.class), 
					OwnableInformationElementFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static OwnableInformationElementFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new OwnableInformationElementFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (OwnableInformationElement) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final OwnableInformationElement element, 
			final boolean superPropertyFlag) {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement bp = element.getBaseProposer();
		if (bp != null) {
			propertyContainer.setProperty(PROPERTY_KEY_PROPOSER, BaseElementFactory.convertToPlainText(bp));
		}

		BaseElement bog = element.getBaseObserverGroup();
		if (bog != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_OBSERVER_GROUP, BaseElementFactory.convertToPlainText(bog));
		}
	}


	@Override
	public OwnableInformationElement loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public OwnableInformationElement loadNode(final Node node, final Label label, 
			final boolean initTypeFlag) throws LIMException {
		OwnableInformationElement element 
				= (OwnableInformationElement) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, OwnableInformationElement element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_PROPOSER)) {
			Object p = propertyContainer.getProperty(PROPERTY_KEY_PROPOSER);
			if (p instanceof String) {
				element.setBaseProposer(BaseElementFactory.convertToElement((String) p));
			}
		}

		if (apks.contains(PROPERTY_KEY_OBSERVER_GROUP)) {
			Object og = propertyContainer.getProperty(PROPERTY_KEY_OBSERVER_GROUP);
			if (og instanceof String) {
				element.setBaseObserverGroup(BaseElementFactory.convertToElement((String) og));
			}
		}
	}

}
