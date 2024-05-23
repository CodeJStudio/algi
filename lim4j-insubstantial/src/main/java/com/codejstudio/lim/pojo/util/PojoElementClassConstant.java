package com.codejstudio.lim.pojo.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class PojoElementClassConstant {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(PojoElementClassConstant.class);


	/* variables: arrays, collections, maps, groups */

	private static final Map<String, Class<? extends BaseElement>> ELEMENT_CLAZZ_MAP;


	/* initializers */

	static {
		try {
			ELEMENT_CLAZZ_MAP = CollectionUtil.generateNewMap();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public static final void registerElementClassForInit(final Class<? extends BaseElement> clazz) {
		ELEMENT_CLAZZ_MAP.put(clazz.getSimpleName(), clazz);
	}


	public static final Set<String> getElementClassSimpleNameSet() throws LIMException {
		return CollectionUtil.copySet(ELEMENT_CLAZZ_MAP.keySet());
	}

	public static final Collection<Class<? extends BaseElement>> getElementClassCollection() 
			throws LIMException {
		return CollectionUtil.copyCollection(ELEMENT_CLAZZ_MAP.values());
	}

	public static final Class<? extends BaseElement> getElementClass(final String clazzSimpleName) {
		return ELEMENT_CLAZZ_MAP.get(clazzSimpleName);
	}

}
