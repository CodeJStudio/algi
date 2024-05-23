package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.doubt.Explanation;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class ExplanationFactory extends InformationElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_TARGET_DOUBT = "targetDoubt";
	public static final String PROPERTY_KEY_RESPONSE = "response";

	public static final String DEFAULT_LABEL = Explanation.class.getSimpleName();


	/* variables */

	private static ExplanationFactory FACTORY;


	/* constructors */

	protected ExplanationFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(Explanation.class), 
					ExplanationFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static ExplanationFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new ExplanationFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (Explanation) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final Explanation element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement btd = element.getBaseTargetDoubt();
		if (btd != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_TARGET_DOUBT, BaseElementFactory.convertToPlainText(btd));
		}

		BaseElement br = element.getBaseResponse();
		if (br != null) {
			propertyContainer.setProperty(PROPERTY_KEY_RESPONSE, BaseElementFactory.convertToPlainText(br));
		}
	}


	@Override
	public Explanation loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public Explanation loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		Explanation element = (Explanation) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, Explanation element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_TARGET_DOUBT)) {
			Object td = propertyContainer.getProperty(PROPERTY_KEY_TARGET_DOUBT);
			if (td instanceof String) {
				element.setBaseTargetDoubt(BaseElementFactory.convertToElement((String) td));
			}
		}

		if (apks.contains(PROPERTY_KEY_RESPONSE)) {
			Object r = propertyContainer.getProperty(PROPERTY_KEY_RESPONSE);
			if (r instanceof String) {
				element.setBaseResponse(BaseElementFactory.convertToElement((String) r));
			}
		}
	}

}
