package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.LogicValue;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class LogicValueFactory extends InformationUnitFactory {

	/* constants */

	public static final String PROPERTY_KEY_VALUE = "value";

	public static final String DEFAULT_LABEL = LogicValue.class.getSimpleName();


	/* variables */

	private static LogicValueFactory FACTORY;


	/* constructors */

	protected LogicValueFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(LogicValue.class), 
					LogicValueFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static LogicValueFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new LogicValueFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (LogicValue) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final LogicValue element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		double v = element.getValue();
		propertyContainer.setProperty(PROPERTY_KEY_VALUE, v);
	}


	@Override
	public LogicValue loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public LogicValue loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		LogicValue element = (LogicValue) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, LogicValue element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_VALUE)) {
			Object v = propertyContainer.getProperty(PROPERTY_KEY_VALUE);
			if (v instanceof Double) {
				element.setValue((Double) v);
			}
		}
	}

}
