package com.codejstudio.lim.pojo.util;

import java.util.Collection;

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
public final class JAXBBoundClassConstant {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(JAXBBoundClassConstant.class);


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> JAXB_BOUND_CLAZZ_COLLECTION;


	/* initializers */

	static {
		try {
			JAXB_BOUND_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public static final void registerBoundClassForInit(final Class<? extends BaseElement> clazz) {
		JAXB_BOUND_CLAZZ_COLLECTION.add(clazz);
	}

	public static final Collection<Class<? extends BaseElement>> getBoundClassCollection() 
			throws LIMException {
		return CollectionUtil.copyCollection(JAXB_BOUND_CLAZZ_COLLECTION);
	}

}
