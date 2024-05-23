package com.codejstudio.lim.pojo.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class PojoElementEnumConstant {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(PojoElementEnumConstant.class);


	/* variables: arrays, collections, maps, groups */

	private static final Map<String, Class<? extends Enum<?>>> ELEMENT_ENUM_CLAZZ_MAP;


	/* initializers */

	static {
		try {
			ELEMENT_ENUM_CLAZZ_MAP = CollectionUtil.generateNewMap();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public static final void registerEnumClassForInit(final Class<? extends Enum<?>> enumClazz) {
		ELEMENT_ENUM_CLAZZ_MAP.put(enumClazz.getSimpleName(), enumClazz);
	}

	public static final Class<? extends Enum<?>> getEnumClass(final String clazzSimpleName) {
		return ELEMENT_ENUM_CLAZZ_MAP.get(clazzSimpleName);
	}

}
