package com.codejstudio.lim.pojo.i;

import java.util.Collection;
import java.util.List;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.GenericElementGroup;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.attribute.AttributeGroup;
import com.codejstudio.lim.pojo.attribute.DefaultAttribute;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.GroupableUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface IAttributable {

	/* getters & setters */

	/**
	 * The implemented classes should also contain:<br/>
	 * private AttributeGroup attributeGroup;<br/>
	 * private void initAttributeGroup();<br/>
	 * private void destroyAttributeGroup();
	 */
	AttributeGroup getAttributeGroup();


	/* CRUD for arrays, collections, maps, groups: attributes */

	boolean containAttributeByKey(String key);

	boolean containAttribute(Attribute attribute);

	boolean containAttribute(String id);


	BaseElement getBaseAttributeByKey(String key);

	BaseElement getBaseAttribute(Attribute attribute);

	BaseElement getBaseAttribute(String id);


	Attribute getAttributeByKey(String key);

	Attribute getAttribute(String key, GenericElement value);

	Attribute getAttribute(Attribute attribute);

	Attribute getAttribute(String id);


	boolean addIncompatibleAttribute(String key, GenericElement value) throws LIMException;

	boolean addCompatibleAttribute(String key, GenericElement... values) throws LIMException;

	boolean addDefaultAttribute(GenericElement... values) throws LIMException;

	boolean addDefaultAttribute(DefaultAttribute attribute) throws LIMException;

	boolean addAttribute(boolean compatibleFlag, Attribute... attributes) throws LIMException;

	boolean addAttribute(boolean compatibleFlag, Collection<Attribute> attributeCollection) 
			throws LIMException;


	boolean removeAttributeByKey(String key) throws LIMException;

	boolean removeAttribute(BaseElement baseAttribute) throws LIMException;

	boolean removeAttribute(String key, GenericElement value) throws LIMException;

	boolean removeAttribute(String id) throws LIMException;


	/* static methods */

	static boolean containAttribute(final IAttributable attributable, final String key, 
			final GenericElement value) {
		Attribute at;
		return attributable != null && !StringUtil.isEmpty(key) && value != null 
				&& (at = attributable.getAttributeByKey(key)) != null && value.equals(at.getValue());
	}

	static boolean containAttributeDescription(final IAttributable attributable, final String key, 
			final Description valueDescriptionObject) throws LIMException {
		Attribute at;
		if (attributable == null || StringUtil.isEmpty(key) 
				|| (at = attributable.getAttributeByKey(key)) == null) {
			return false;
		}

		GenericElementGroup vlg;
		if (at.getCompatible() == null || !at.getCompatible()) {
			return verifyDescription(at.getValue(), valueDescriptionObject);
		} else if (GroupableUtil.checkNullOrEmpty(vlg = at.getValueGroup())) {
			List<GenericElement> vlgigl = vlg.getInnerGroupList();
			for (GenericElement ge : vlgigl) {
				if (verifyDescription(ge, valueDescriptionObject)) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean verifyDescription(final GenericElement element, final Description descriptionObject) {
		return element instanceof InformationElement 
				&& ((InformationElement) element).descriptionEquals(descriptionObject);
	}

}
