package com.codejstudio.algi.action;

import java.util.List;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.ISymbolElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractMultiActionableElement extends AbstractActionableElement {

	/* constants */

	private static final long serialVersionUID = -3967687307749171349L;


	/* variables: arrays, collections, maps, groups */

	protected List<ISymbolElement> subSymbolElementList;


	/* constructors */

	/**
	 * for JAXB usage of unmarshalling
	 */
	public AbstractMultiActionableElement() {
		super();
	}

	public AbstractMultiActionableElement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}


	/* initializers */

	@Override
	protected List<ISymbolElement> initAndGetSubSymbolElementList() throws LIMException {
		initSubSymbolElementList();
		return subSymbolElementList;
	}

	private void initSubSymbolElementList() throws LIMException {
		if (this.subSymbolElementList == null) {
			this.subSymbolElementList = CollectionUtil.generateNewList();
		}
	}


	/* destroyers */

	private void destroySubSymbolElementList(final boolean forceFlag) {
		if (forceFlag || (this.subSymbolElementList != null && this.subSymbolElementList.size() == 0)) {
			this.subSymbolElementList = null;
		}
	}


	/* getters & setters */

	public List<ISymbolElement> getSubSymbolElementList() {
		return subSymbolElementList;
	}


	/* overridden methods */

	@Override
	protected void postSetEmptyDescriptionObject() {
		destroySubSymbolElementList(true);
	}


	@Override
	public AbstractMultiActionableElement cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(AbstractMultiActionableElement.class)) {
					return (AbstractMultiActionableElement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		AbstractMultiActionableElement clonedElement 
				= (AbstractMultiActionableElement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	public AbstractMultiActionableElement cloneToElement(
			final AbstractMultiActionableElement clonedElement) throws LIMException {
		AbstractActionableElement ce;
		if (!(clonedElement instanceof AbstractMultiActionableElement) 
				|| !((ce = super.cloneToElement(clonedElement)) instanceof AbstractMultiActionableElement)) {
			return null;
		}
		return cloneToElement((AbstractMultiActionableElement) ce, null);
	}

	private AbstractMultiActionableElement cloneToElement(
			final AbstractMultiActionableElement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(this.subSymbolElementList)) {
			clonedElement.initSubSymbolElementList();
			for (ISymbolElement se : this.subSymbolElementList) {
				clonedElement.subSymbolElementList.add(se);
			}
			clonedElement.destroySubSymbolElementList(false);
		}

		return clonedElement;
	}

}
