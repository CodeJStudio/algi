package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.comment.Comment;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class CommentFactory extends GenericElementFactory {

	/* constants */

	public static final String PROPERTY_KEY_ELEMENT = "element";

	public static final String DEFAULT_LABEL = Comment.class.getSimpleName();


	/* variables */

	private static CommentFactory FACTORY;


	/* constructors */

	protected CommentFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(Comment.class), 
					CommentFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static CommentFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new CommentFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Node n = super.createNode(graphDatabase, element);
		this.setProperty(n, (Comment) element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final Comment element, 
			final boolean superPropertyFlag) {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement be = element.getBaseElement();
		if (be != null) {
			propertyContainer.setProperty(PROPERTY_KEY_ELEMENT, BaseElementFactory.convertToPlainText(be));
		}
	}


	@Override
	public Comment loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public Comment loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		Comment element = (Comment) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, Comment element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_ELEMENT)) {
			Object e = propertyContainer.getProperty(PROPERTY_KEY_ELEMENT);
			if (e instanceof String) {
				element.setBaseElement(BaseElementFactory.convertToElement((String) e));
			}
		}
	}

}
