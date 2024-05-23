package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericElement;

/**
 * DummyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class DummyRelation {

	/* variables */

	private Class<? extends BaseRelation> relationClazz;

	private AbstractRelationableInformationElement primaryElement;

	private BaseElement secondaryElement;


	/* constructors */

	public DummyRelation(Class<? extends BaseRelation> relationClazz, 
			AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) {
		this.relationClazz = relationClazz;
		this.primaryElement = primaryElement;
		this.secondaryElement = secondaryElement;
	}

	public DummyRelation(Class<? extends BaseRelation> relationClazz, 
			AbstractRelationableInformationElement primaryElement, String secondaryElementId, 
			Class<? extends AbstractRelationableInformationElement> secondaryElementClazz) {
		this.relationClazz = relationClazz;
		this.primaryElement = primaryElement;
		this.secondaryElement = new BaseElement(secondaryElementId, secondaryElementClazz);
	}


	/* getters & setters */

	public Class<? extends BaseRelation> getRelationClass() {
		return relationClazz;
	}

	public AbstractRelationableInformationElement getPrimaryElement() {
		return primaryElement;
	}

	public BaseElement getSecondaryElement() {
		return secondaryElement;
	}


	/* class methods */

	public boolean checkNull() {
		return this.relationClazz == null || this.primaryElement == null || this.secondaryElement == null;
	}

	public boolean equals(final GenericElement element) {
		if (element == null) {
			return false;
		}

		if (this.relationClazz.equals(element.getClass()) && element instanceof BaseRelation 
				&& this.primaryElement.equals(((BaseRelation)element).getPrimaryElement())) { 
			AbstractRelationableInformationElement se = ((BaseRelation)element).getSecondaryElement();
			if (this.secondaryElement instanceof AbstractRelationableInformationElement) {
				if (this.secondaryElement.equals(se)) {
					return true;
				}
			} else {
				if (this.secondaryElement.getId().equals(se.getId()) 
						&& this.secondaryElement.getType().equals(se.getType())) {
					return true;
				}
			}
		}
		return false;
	}

}
