package com.codejstudio.lin.persistence.neo4j.entityFactory;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationSection;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class InformationSectionFactory extends InformationElementFactory {

	/* constants */

	public static final String DEFAULT_LABEL = InformationSection.class.getSimpleName();


	/* variables */

	private static InformationSectionFactory FACTORY;


	/* constructors */

	protected InformationSectionFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(InformationSection.class), 
					InformationSectionFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static InformationSectionFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new InformationSectionFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public InformationSection loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public InformationSection loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		InformationSection element = (InformationSection) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, InformationSection element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}
	}

}
