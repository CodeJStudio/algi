package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.statement.JudgedStatement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class JudgedStatementFactory extends StatementFactory {

	/* constants */

	public static final String PROPERTY_KEY_TRUTH = "truth";

	public static final String DEFAULT_LABEL = JudgedStatement.class.getSimpleName();


	/* variables */

	private static JudgedStatementFactory FACTORY;


	/* constructors */

	protected JudgedStatementFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(JudgedStatement.class), 
					JudgedStatementFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static JudgedStatementFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new JudgedStatementFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (JudgedStatement) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final JudgedStatement element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement bt = element.getBaseTruth();
		if (bt != null) {
			propertyContainer.setProperty(PROPERTY_KEY_TRUTH, BaseElementFactory.convertToPlainText(bt));
		}
	}


	@Override
	public JudgedStatement loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public JudgedStatement loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		JudgedStatement element = (JudgedStatement) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, JudgedStatement element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_TRUTH)) {
			Object t = propertyContainer.getProperty(PROPERTY_KEY_TRUTH);
			if (t instanceof String) {
				element.setBaseTruth(BaseElementFactory.convertToElement((String) t));
			}
		}
	}

}
