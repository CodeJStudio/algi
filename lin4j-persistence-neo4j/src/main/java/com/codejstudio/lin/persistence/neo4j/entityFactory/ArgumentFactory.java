package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.argument.Argument;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class ArgumentFactory extends InformationUnitFactory {

	/* constants */

	public static final String PROPERTY_KEY_VALIDITY = "validity";
	public static final String PROPERTY_KEY_CAUSALITY_RELATION = "causalityRelation";
	public static final String PROPERTY_KEY_SUB_ARGUMENT_GROUP = "subArgumentGroup";
	public static final String PROPERTY_KEY_JUDGED_STATEMENT_GROUP = "judgedStatementGroup";
	public static final String PROPERTY_KEY_CONDITION_GROUP = "conditionGroup";
	public static final String PROPERTY_KEY_ATTRIBUTE_GROUP = "attributeGroup";

	public static final String DEFAULT_LABEL = Argument.class.getSimpleName();


	/* variables */

	private static ArgumentFactory FACTORY;


	/* constructors */

	protected ArgumentFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(Argument.class), 
					ArgumentFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static ArgumentFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new ArgumentFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (Argument) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final Argument element, 
			final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement bv = element.getBaseValidity();
		if (bv != null) {
			propertyContainer.setProperty(PROPERTY_KEY_VALIDITY, BaseElementFactory.convertToPlainText(bv));
		}

		BaseElement bcr = element.getBaseCausalityRelation();
		if (bcr != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_CAUSALITY_RELATION, BaseElementFactory.convertToPlainText(bcr));
		}

		BaseElement bsag = element.getBaseSubArgumentGroup();
		if (bsag != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_SUB_ARGUMENT_GROUP, BaseElementFactory.convertToPlainText(bsag));
		}

		BaseElement bjsg = element.getBaseJudgedStatementGroup();
		if (bjsg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_JUDGED_STATEMENT_GROUP, BaseElementFactory.convertToPlainText(bjsg));
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
	public Argument loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public Argument loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		Argument element = (Argument) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, Argument element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_VALIDITY)) {
			Object v = propertyContainer.getProperty(PROPERTY_KEY_VALIDITY);
			if (v instanceof String) {
				element.setBaseValidity(BaseElementFactory.convertToElement((String) v));
			}
		}

		if (apks.contains(PROPERTY_KEY_CAUSALITY_RELATION)) {
			Object cr = propertyContainer.getProperty(PROPERTY_KEY_CAUSALITY_RELATION);
			if (cr instanceof String) {
				element.setBaseCausalityRelation(BaseElementFactory.convertToElement((String) cr));
			}
		}

		if (apks.contains(PROPERTY_KEY_SUB_ARGUMENT_GROUP)) {
			Object sag = propertyContainer.getProperty(PROPERTY_KEY_SUB_ARGUMENT_GROUP);
			if (sag instanceof String) {
				element.setBaseSubArgumentGroup(BaseElementFactory.convertToElement((String) sag));
			}
		}

		if (apks.contains(PROPERTY_KEY_JUDGED_STATEMENT_GROUP)) {
			Object jsg = propertyContainer.getProperty(PROPERTY_KEY_JUDGED_STATEMENT_GROUP);
			if (jsg instanceof String) {
				element.setBaseJudgedStatementGroup(BaseElementFactory.convertToElement((String) jsg));
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
