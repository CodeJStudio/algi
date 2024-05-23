package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.condition.Condition;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class ConditionFactory extends InformationUnitFactory {

	/* constants */

	public static final String PROPERTY_KEY_IMPLICIT = "implicit";
	public static final String PROPERTY_KEY_DESCRIPTION_ELEMENT = "descriptionElement";
	public static final String PROPERTY_KEY_VERIFIER = "verifier";

	public static final String DEFAULT_LABEL = Condition.class.getSimpleName();


	/* variables */

	private static ConditionFactory FACTORY;


	/* constructors */

	protected ConditionFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(Condition.class), 
					ConditionFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static ConditionFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new ConditionFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (Condition) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final Condition element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		Boolean i = element.getImplicit();
		if (i != null) {
			propertyContainer.setProperty(PROPERTY_KEY_IMPLICIT, i.toString());
		}

		BaseElement bde = element.getBaseDescriptionElement();
		if (bde != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_DESCRIPTION_ELEMENT, BaseElementFactory.convertToPlainText(bde));
		}

		BaseElement bv = element.getBaseVerifier();
		if (bv != null) {
			propertyContainer.setProperty(PROPERTY_KEY_VERIFIER, BaseElementFactory.convertToPlainText(bv));
		}
	}


	@Override
	public Condition loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public Condition loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		Condition element = (Condition) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, Condition element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_IMPLICIT)) {
			Object i = propertyContainer.getProperty(PROPERTY_KEY_IMPLICIT);
			if (i instanceof String) {
				element.setImplicit(new Boolean((String) i));
			}
		}

		if (apks.contains(PROPERTY_KEY_DESCRIPTION_ELEMENT)) {
			Object de = propertyContainer.getProperty(PROPERTY_KEY_DESCRIPTION_ELEMENT);
			if (de instanceof String) {
				element.setBaseDescriptionElement(BaseElementFactory.convertToElement((String) de));
			}
		}

		if (apks.contains(PROPERTY_KEY_VERIFIER)) {
			Object v = propertyContainer.getProperty(PROPERTY_KEY_VERIFIER);
			if (v instanceof String) {
				element.setBaseVerifier(BaseElementFactory.convertToElement((String) v));
			}
		}
	}

}
