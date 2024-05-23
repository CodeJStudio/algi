package com.codejstudio.algi.action.dynamicAction;

import java.util.Set;

import com.codejstudio.algi.action.AbstractAction;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.collection.MultiValueMap;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.InformationElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SeekAndLoadSubElementsAction extends AbstractAction {

	/* constants */

	public static final String PARAMETER_INDEXES = SeekAndLoadSubElementsAction.class.getSimpleName() 
			+ SEPARATOR_PARAMETER_NAME + "indexes";


	/* variables */

	protected final InformationElement superElement;


	/* constructors */

	public SeekAndLoadSubElementsAction(InformationElement superElement) throws LIMException {
		super();
		if (superElement == null || superElement.getDescription() == null) {
			throw new ALGIException(new IllegalArgumentException());
		}
		this.superElement = superElement;
	}


	/* getters & setters */

	public InformationElement getSuperElement() {
		return superElement;
	}


	/* overridden methods */

	@Override
	protected Object[] doExecute() throws LIMException {
		Object obj = this.session.getInputParameter(PARAMETER_INDEXES);
		MultiValueMap<InformationElement, Integer> ieimvm = null;
		Integer index;
		if (obj instanceof Integer[]) {
			ieimvm = Informationiverse.seekSubElementsByDescription(this.superElement.getDescription(), 
					(Integer[]) obj);
		} else if (obj instanceof Integer) {
			ieimvm = Informationiverse.seekSubElementsByDescription(this.superElement.getDescription(), 
					(Integer) obj);
		} else if ((index = ObjectUtil.integerValue(obj)) != null) {
			ieimvm = Informationiverse.seekSubElementsByDescription(this.superElement.getDescription(), 
					index);
		}
		if (CollectionUtil.checkNullOrEmpty(ieimvm)) {
			return null;
		}

		Set<InformationElement> ieiks = ieimvm.keySet();
		this.superElement.addSubInformationElement(ieiks.toArray(new InformationElement[0]));
		this.superElement.putSubElementPositionDelegate(ieimvm);

		return null;
	}

}
