package com.codejstudio.lim.pojo.relation;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * PredicateMappingRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PredicateMappingRelation extends MappingRelation {

	/* constants */

	private static final long serialVersionUID = 3959145585835510961L;

	public static final String PREDICATE = "predicate";


	/* variables */

	protected Concept predicate;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public PredicateMappingRelation() {
		super();
	}

	public PredicateMappingRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag);
	}

	public PredicateMappingRelation(boolean initIdFlag, 
			AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement, final Concept predicate) 
					throws LIMException {
		super(initIdFlag, primaryElement, secondaryElement);
		if (predicate != null) {
			super.addInnerElementDelegate(predicate);
			super.putIntegratedElementDelegate(PREDICATE, new BaseElement(predicate));
		}
	}


	public PredicateMappingRelation(AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement, Concept predicate) throws LIMException {
		this(true, primaryElement, secondaryElement, predicate);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(PredicateMappingRelation.class);
		BaseRelation.registerSubPojoClassForInit(PredicateMappingRelation.class);
	}


	/* overridden methods */

	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		Map<String, BaseElement> item;
		if (CollectionUtil.checkNullOrEmpty(rootElementMap) 
				|| CollectionUtil.checkNullOrEmpty(item = super.getIntegratedElementMap())) {
			return;
		}

		BaseElement predicate = item.get(PREDICATE);
		if (predicate != null && predicate.getId() != null) {
			GenericElement ge = rootElementMap.get(predicate.getId());
			this.predicate = (ge instanceof Concept) ? (Concept) ge : this.predicate;
			super.addInnerElementDelegate(this.predicate);
		}
	}


	@Override
	public PredicateMappingRelation cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(PredicateMappingRelation.class)) {
					return (PredicateMappingRelation) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		PredicateMappingRelation clonedElement 
				= (PredicateMappingRelation) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public PredicateMappingRelation cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof PredicateMappingRelation) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof PredicateMappingRelation)) 
				? null : cloneToElement((PredicateMappingRelation) ce, null);
	}

	private PredicateMappingRelation cloneToElement(final PredicateMappingRelation clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.predicate = (this.predicate != null) 
				? (Concept) this.predicate.cloneElement(clonedElementMap) : clonedElement.predicate;

		return clonedElement;
	}

}
