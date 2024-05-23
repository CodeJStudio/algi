package com.codejstudio.lin.persistence.neo4j.entityFactory;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.util.ActionableElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lin.common.exception.LINException;
import com.codejstudio.lin.persistence.neo4j.entityFactory.i.INodeFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class BaseElementFactory extends AbstractFactory implements INodeFactory {

	/* constants */

	public static final String PROPERTY_KEY_ID = "id";

	public static final String PROPERTY_VALUE_ELEMENT_SYMBOL_LEFT = "<";
	public static final String PROPERTY_VALUE_ELEMENT_SYMBOL_RIGHT = ">";
	public static final String PROPERTY_VALUE_ELEMENT_SYMBOL_SEPARATOR 
			= "" + WordSeparator.COMMA.getCharacter();

	public static final String DEFAULT_LABEL = BaseElement.class.getSimpleName();


	/* variables */

	private static BaseElementFactory FACTORY;


	/* constructors */

	protected BaseElementFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(BaseElement.class), 
					BaseElementFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static BaseElementFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new BaseElementFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Node createNode(final GraphDatabaseService graphDatabase, final BaseElement element) 
			throws LIMException {
		Label l = Label.label(element.getClass().getSimpleName());
		Node n = graphDatabase.createNode(l);
		this.setProperty(n, element, false);
		return n;
	}

	public void setProperty(PropertyContainer propertyContainer, final BaseElement element, 
			final boolean superPropertyFlag) {
		String id = element.getId();
		if (id != null) {
			propertyContainer.setProperty(PROPERTY_KEY_ID, id);
		}
	}


	@Override
	public BaseElement loadNode(final Node node, final Label label) throws LIMException {
		return label.name().equals(DEFAULT_LABEL) 
				? loadNode(node, label, false) : loadNode(node, label, true);
	}

	@Override
	public BaseElement loadNode(final Node node, final Label label, final boolean initTypeFlag) 
			throws LIMException {
		try {
			Class<?> cl;
			if ((cl = PojoElementClassConstant.getElementClass(label.name())) != null) {
			} else if ((cl = ActionableElementClassConstant.getActionableClass(label.name())) != null) {
			}
			Object obj;
			if (cl == null || !((obj = cl.newInstance()) instanceof BaseElement)) {
				return null;
			}

			BaseElement element = (BaseElement) obj;
			this.loadProperty(node, element, initTypeFlag, false);
			return element;
		} catch (InstantiationException | IllegalAccessException e) {
			throw LINException.getLINException(e);
		}
	}

	public void loadProperty(final PropertyContainer propertyContainer, BaseElement element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) {
		element.setId((String) propertyContainer.getProperty(PROPERTY_KEY_ID));
		if (initTypeFlag) {
			element.setType(BaseElement.generateTypeFromClass(element.getClass()));
		}
	}


	/* static methods */

	public static final String convertToPlainText(final BaseElement element) {
		if (element == null || element.getId() == null) {
			return null;
		}

		String type = element.getType();
		return PROPERTY_VALUE_ELEMENT_SYMBOL_LEFT 
				+ element.getId() 
				+ PROPERTY_VALUE_ELEMENT_SYMBOL_SEPARATOR 
				+ ((type != null) ? type : BaseElement.generateTypeFromClass(element.getClass())) 
				+ PROPERTY_VALUE_ELEMENT_SYMBOL_RIGHT;
	}


	public static final BaseElement convertToElement(final String plainText) {
		if (plainText.startsWith(PROPERTY_VALUE_ELEMENT_SYMBOL_LEFT) 
				&& plainText.endsWith(PROPERTY_VALUE_ELEMENT_SYMBOL_RIGHT)) {
			String pt = plainText.substring(PROPERTY_VALUE_ELEMENT_SYMBOL_LEFT.length(), 
					plainText.length() - PROPERTY_VALUE_ELEMENT_SYMBOL_RIGHT.length());
			String[] stra = pt.split(PROPERTY_VALUE_ELEMENT_SYMBOL_SEPARATOR);
			if (stra != null && stra.length == 2) {
				return new BaseElement(stra[0], stra[1]);
			}
		}
		return null;
	}

}
