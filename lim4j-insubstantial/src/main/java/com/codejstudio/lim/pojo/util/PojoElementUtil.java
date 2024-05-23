package com.codejstudio.lim.pojo.util;

import java.util.Arrays;
import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.BaseElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class PojoElementUtil {

	/* static methods */

	public static final Collection<BaseElement> hierarchyToPlain(final BaseElement... elements) 
			throws LIMException {
		return hierarchyToPlain((elements == null) ? null : Arrays.asList(elements));
	}

	public static final Collection<BaseElement> hierarchyToPlain(
			final Collection<BaseElement> elementCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return null;
		}

		Collection<BaseElement> bec = CollectionUtil.generateNewCollection();
		for (BaseElement element : elementCollection) {
			hierarchyToPlain(element, bec);
		}
		return bec;
	}

	private static final void hierarchyToPlain(final BaseElement element, 
			Collection<BaseElement> returnCollection) throws LIMException {
		if (element == null || returnCollection.contains(element)) {
			return;
		}
		returnCollection.add(element);
		if (element instanceof GenericElement) {
			Collection<GenericElement> geiec = ((GenericElement) element).getInnerElementCollection();
			for (GenericElement ge : geiec) {
				hierarchyToPlain(ge, returnCollection);
			}
		}
	}

}
