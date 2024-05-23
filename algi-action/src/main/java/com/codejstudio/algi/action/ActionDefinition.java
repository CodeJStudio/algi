package com.codejstudio.algi.action;

import java.util.List;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ActionDefinition extends InformationElement {

	/* constants */

	private static final long serialVersionUID = 8846186717955276425L;

	public static final String TYPE_NAME = "action-definition";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ActionDefinition() {
		super();
	}

	public ActionDefinition(String description) throws LIMException {
		super(true, true, description);
	}

	public ActionDefinition(String description, boolean encodeFlag) throws LIMException {
		super(true, true, description, encodeFlag);
	}


	protected ActionDefinition(Description descriptionObject) throws LIMException {
		super(true, true, descriptionObject);
	}


	public ActionDefinition(ActionDefinition... actionDefinitions) throws LIMException {
		this(ActionDefinition.descriptionMerge(actionDefinitions));
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(ActionDefinition.class);
		InformationElement.registerSubPojoClassForInit(ActionDefinition.class);
	}


	/* static methods */

	public static Description descriptionMerge(final ActionDefinition... actionDefinitions) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(actionDefinitions)) {
			return null;
		}

		List<Description> desl = CollectionUtil.generateNewList();
		for (int i = 0; i < actionDefinitions.length; i++) {
			if (actionDefinitions[i] != null) {
				desl.add(actionDefinitions[i].getDescriptionObject());
			}
		}
		return Description.merge(desl.toArray(new Description[0]));
	}

}
