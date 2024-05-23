package com.codejstudio.lin.persistence.neo4j.entityFactory;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationUnit;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class InformationUnitFactory extends InformationSectionFactory {

	/* constants */

	public static final String DEFAULT_LABEL = InformationUnit.class.getSimpleName();


	/* variables */

	private static InformationUnitFactory FACTORY;


	/* constructors */

	protected InformationUnitFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(InformationUnit.class), 
					InformationUnitFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static InformationUnitFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new InformationUnitFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public InformationUnit loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public InformationUnit loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		InformationUnit element = (InformationUnit) super.loadNode(node, label, initTypeFlag);
		this.loadProperty(node, element, initTypeFlag, false);
		return element;
	}

	public void loadProperty(final PropertyContainer propertyContainer, InformationUnit element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}
	}

}
