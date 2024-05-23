package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class GenericActionableElementFactory extends BaseElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_ACTION_DESCRIPTION = "actionDescription";

	public static final String DEFAULT_LABEL = GenericActionableElement.class.getSimpleName();


	/* variables */

	private static GenericActionableElementFactory FACTORY;


	/* constructors */

	protected GenericActionableElementFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(GenericActionableElement.class), 
					GenericActionableElementFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static GenericActionableElementFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new GenericActionableElementFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (GenericActionableElement) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final GenericActionableElement element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		String acdsc = element.getActionDescription();
		if (acdsc != null) {
			propertyContainer.setProperty(PROPERTY_KEY_ACTION_DESCRIPTION, acdsc);
		}
	}


	@Override
	public GenericActionableElement loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public GenericActionableElement loadNode(final Node node, final Label label, 
			final boolean initTypeFlag) throws LIMException {
		GenericActionableElement element 
				= (GenericActionableElement) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, GenericActionableElement element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_ACTION_DESCRIPTION)) {
			Object acdsc = propertyContainer.getProperty(PROPERTY_KEY_ACTION_DESCRIPTION);
			if (acdsc instanceof String) {
				element.setActionDescription((String) acdsc);
			}
		}
	}

}
