package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.role.BaseRole;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class BaseRoleFactory extends GenericElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_ENTITY = "entity";
	public static final String PROPERTY_KEY_COMMENT_GROUP = "commentGroup";

	public static final String DEFAULT_LABEL = BaseRole.class.getSimpleName();


	/* variables */

	private static BaseRoleFactory FACTORY;


	/* constructors */

	protected BaseRoleFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(BaseRole.class), 
					BaseRoleFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static BaseRoleFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new BaseRoleFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (BaseRole) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final BaseRole element, 
			final boolean superPropertyFlag) {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement ben = element.getBaseEntity();
		if (ben != null) {
			propertyContainer.setProperty(PROPERTY_KEY_ENTITY, BaseElementFactory.convertToPlainText(ben));
		}

		BaseElement bcmg = element.getBaseCommentGroup();
		if (bcmg != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_COMMENT_GROUP, BaseElementFactory.convertToPlainText(bcmg));
		}
	}


	@Override
	public BaseRole loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public BaseRole loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		BaseRole element = (BaseRole) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, BaseRole element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_ENTITY)) {
			Object en = propertyContainer.getProperty(PROPERTY_KEY_ENTITY);
			if (en instanceof String) {
				element.setBaseEntity(BaseElementFactory.convertToElement((String) en));
			}
		}

		if (apks.contains(PROPERTY_KEY_COMMENT_GROUP)) {
			Object cmg = propertyContainer.getProperty(PROPERTY_KEY_COMMENT_GROUP);
			if (cmg instanceof String) {
				element.setBaseCommentGroup(BaseElementFactory.convertToElement((String) cmg));
			}
		}
	}

}
