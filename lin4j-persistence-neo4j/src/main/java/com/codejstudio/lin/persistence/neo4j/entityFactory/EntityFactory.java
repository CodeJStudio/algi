package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.entity.Entity;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class EntityFactory extends GenericElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_NAME = "name";
	public static final String PROPERTY_KEY_PROPOSED_ELEMENT_GROUP = "proposedElementGroup";
	public static final String PROPERTY_KEY_OBSERVED_ELEMENT_GROUP = "observedElementGroup";

	public static final String DEFAULT_LABEL = Entity.class.getSimpleName();


	/* variables */

	private static EntityFactory FACTORY;


	/* constructors */

	protected EntityFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(Entity.class), 
					EntityFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static EntityFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new EntityFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (Entity) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final Entity element, 
			final boolean superPropertyFlag) {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		String n = element.getName();
		if (n != null) {
			propertyContainer.setProperty(PROPERTY_KEY_NAME, n);
		}

		BaseElement bpeg = element.getBaseProposedElementGroup();
		if (bpeg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_PROPOSED_ELEMENT_GROUP, BaseElementFactory.convertToPlainText(bpeg));
		}

		BaseElement boeg = element.getBaseObservedElementGroup();
		if (boeg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_OBSERVED_ELEMENT_GROUP, BaseElementFactory.convertToPlainText(boeg));
		}
	}


	@Override
	public Entity loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public Entity loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		Entity element = (Entity) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, Entity element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_NAME)) {
			Object n = propertyContainer.getProperty(PROPERTY_KEY_NAME);
			if (n instanceof String) {
				element.setName((String) n);
			}
		}

		if (apks.contains(PROPERTY_KEY_PROPOSED_ELEMENT_GROUP)) {
			Object peg = propertyContainer.getProperty(PROPERTY_KEY_PROPOSED_ELEMENT_GROUP);
			if (peg instanceof String) {
				element.setBaseProposedElementGroup(BaseElementFactory.convertToElement((String) peg));
			}
		}

		if (apks.contains(PROPERTY_KEY_OBSERVED_ELEMENT_GROUP)) {
			Object oeg = propertyContainer.getProperty(PROPERTY_KEY_OBSERVED_ELEMENT_GROUP);
			if (oeg instanceof String) {
				element.setBaseObservedElementGroup(BaseElementFactory.convertToElement((String) oeg));
			}
		}
	}

}
