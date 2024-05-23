package com.codejstudio.algi.action;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.codejstudio.algi.action.symbolElement.AbstractSurroundingSymbolElement;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.ISession;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.InputParameter;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractActionableElement extends GenericActionableElement implements Action {

	/* constants */

	private static final long serialVersionUID = -5774191774851070271L;


	/* variables */

	protected ISession session;


	/* variables: arrays, collections, maps, groups */

	private String[] actionDescriptionFragmentArray;


	/* constructors */

	/**
	 * for JAXB usage of unmarshalling
	 */
	public AbstractActionableElement() {
		super();
	}

	protected AbstractActionableElement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addThisAsInnerElement();
	}


	/* initializers */

	protected void init(final String actionDescription) throws LIMException {
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(actionDescription);
		if (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays)) {
			throw new ALGIException(new IllegalArgumentException());
		}
		this.actionDescriptionFragmentArray = arrayOfFragmentArrays[0];

		String[] outsideOfSymbolArray = new String[2];
		List<ISymbolElement> ssel = initAndGetSubSymbolElementList();
		if (!AbstractSurroundingSymbolElement.generateSubSymbolElements(
				this.actionDescriptionFragmentArray, outsideOfSymbolArray, ssel, null)) {
			throw new ALGIException(new IllegalArgumentException());
		}
		postInit(ssel);
	}

	protected List<ISymbolElement> initAndGetSubSymbolElementList() throws LIMException {
		return CollectionUtil.generateNewList();
	}

	protected void postInit(List<ISymbolElement> subSymbolElementList) {}


	/* destroyers */

	private void destroyActionDescriptionFragmentArray(final boolean forceFlag) {
		if (forceFlag || (this.actionDescriptionFragmentArray != null 
				&& CollectionUtil.checkNullOrEmpty(this.actionDescriptionFragmentArray))) {
			this.actionDescriptionFragmentArray = null;
		}
	}


	/* getters & setters */

	@Override
	@XmlTransient
	public ISession getSession() {
		return session;
	}

	@Override
	public void setSession(ISession session) {
		this.session = session;
	}


	public String[] getActionDescriptionFragmentArray() {
		return actionDescriptionFragmentArray;
	}


	/* overridden methods */

	@Override
	protected void updateDescription() throws LIMException {
		String dsc;
		if (this.descriptionObject != null 
				&& !StringUtil.isEmpty(dsc = this.descriptionObject.getEncodedText())) {
			init(dsc);
		} else {
			destroyActionDescriptionFragmentArray(true);
			postSetEmptyDescriptionObject();
		}
	}

	protected void postSetEmptyDescriptionObject() {}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		return (IConvertible) this;
	}


	@Override
	public AbstractActionableElement cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(AbstractActionableElement.class)) {
					return (AbstractActionableElement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		AbstractActionableElement clonedElement 
				= (AbstractActionableElement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	public AbstractActionableElement cloneToElement(final AbstractActionableElement clonedElement) 
			throws LIMException {
		return cloneToElement(clonedElement, null);
	}

	private AbstractActionableElement cloneToElement(final AbstractActionableElement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (clonedElement == null) {
			return null;
		}

		clonedElement.session = (this.session != null) 
				? (ISession) this.session.cloneElement(clonedElementMap) : clonedElement.session;

		if (this.actionDescriptionFragmentArray != null) {
			clonedElement.actionDescriptionFragmentArray 
					= new String[this.actionDescriptionFragmentArray.length];
			for (int i = 0; i < this.actionDescriptionFragmentArray.length; i++) {
				clonedElement.actionDescriptionFragmentArray[i] = this.actionDescriptionFragmentArray[i];
			}
		}

		return clonedElement;
	}


	@Override
	public String toString() {
		String sts = super.toString();
		String dsc = (this.descriptionObject != null) 
				? (TOSTRING_SYMBOL_SEPARATOR + "description=\"" 
						+ this.descriptionObject.getEncodedText() + "\"") : "";
		return (sts.endsWith(TOSTRING_SYMBOL_RIGHT)) 
				? (sts.substring(0, (sts.length() - TOSTRING_SYMBOL_RIGHT.length())) 
						+ dsc + TOSTRING_SYMBOL_RIGHT) 
				: (sts + dsc);
	}

	@Override
	public Object[] execute(final ISession session, final InputParameter... inputParameters) 
			throws LIMException {
		setSession(session);
		return execute(inputParameters);
	}

	@Override
	public Object[] execute(final InputParameter... inputParameters) throws LIMException {
		return null;
	}

}
