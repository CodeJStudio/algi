package com.codejstudio.lim.pojo.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.GenericActionableElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class ActionableElementClassConstant {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(ActionableElementClassConstant.class);


	/* variables: arrays, collections, maps, groups */

	private static final Map<String, Class<? extends GenericActionableElement>> ACTIONABLE_CLAZZ_MAP;


	/* initializers */

	static {
		try {
			ACTIONABLE_CLAZZ_MAP = CollectionUtil.generateNewMap();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public static final void registerActionableClassForInit(
			final Class<? extends GenericActionableElement> actionableClazz) {
		ACTIONABLE_CLAZZ_MAP.put(actionableClazz.getSimpleName(), actionableClazz);
	}


	public static final Set<String> getActionableClassSimpleNameSet() throws LIMException {
		return CollectionUtil.copySet(ACTIONABLE_CLAZZ_MAP.keySet());
	}

	public static final Collection<Class<? extends GenericActionableElement>> getActionableClassCollection() 
			throws LIMException {
		return CollectionUtil.copyCollection(ACTIONABLE_CLAZZ_MAP.values());
	}

	public static final Class<? extends GenericActionableElement> getActionableClass(
			final String clazzSimpleName) {
		return ACTIONABLE_CLAZZ_MAP.get(clazzSimpleName);
	}

}
