package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.doubt.Doubt;
import com.codejstudio.lim.pojo.doubt.Doubt.DoubtType;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class DoubtFactory extends InformationElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_DOUBT_TYPE = "doubtType";
	public static final String PROPERTY_KEY_TARGET = "target";
	public static final String PROPERTY_KEY_EXPLANATION_GROUP = "explanationGroup";

	public static final String DEFAULT_LABEL = Doubt.class.getSimpleName();


	/* variables */

	private static DoubtFactory FACTORY;


	/* constructors */

	protected DoubtFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(Doubt.class), DoubtFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static DoubtFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new DoubtFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (Doubt) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final Doubt element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		DoubtType dt = element.getDoubtType();
		if (dt != null) {
			propertyContainer.setProperty(PROPERTY_KEY_DOUBT_TYPE, dt.name());
		}

		BaseElement bt = element.getBaseTarget();
		if (bt != null) {
			propertyContainer.setProperty(PROPERTY_KEY_TARGET, BaseElementFactory.convertToPlainText(bt));
		}

		BaseElement bexg = element.getBaseExplanationGroup();
		if (bexg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_EXPLANATION_GROUP, BaseElementFactory.convertToPlainText(bexg));
		}
	}


	@Override
	public Doubt loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public Doubt loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		Doubt element = (Doubt) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, Doubt element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_DOUBT_TYPE)) {
			Object dt = propertyContainer.getProperty(PROPERTY_KEY_DOUBT_TYPE);
			if (dt instanceof String) {
				element.setDoubtType(DoubtType.valueOf((String) dt));
			}
		}

		if (apks.contains(PROPERTY_KEY_TARGET)) {
			Object t = propertyContainer.getProperty(PROPERTY_KEY_TARGET);
			if (t instanceof String) {
				element.setBaseTarget(BaseElementFactory.convertToElement((String) t));
			}
		}

		if (apks.contains(PROPERTY_KEY_EXPLANATION_GROUP)) {
			Object exg = propertyContainer.getProperty(PROPERTY_KEY_EXPLANATION_GROUP);
			if (exg instanceof String) {
				element.setBaseExplanationGroup(BaseElementFactory.convertToElement((String) exg));
			}
		}
	}

}
