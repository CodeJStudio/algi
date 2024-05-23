package com.codejstudio.lim.pojo.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.ISymbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class SymbolConstant {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(SymbolConstant.class);


	/* variables: arrays, collections, maps, groups */

	private static final Map<String, Class<? extends ISymbol>> SYMBOL_CLAZZ_MAP;


	/* initializers */

	static {
		try {
			SYMBOL_CLAZZ_MAP = CollectionUtil.generateNewMap();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public static final void registerSymbolClassForInit(final Class<? extends ISymbol> symbolClazz) {
		SYMBOL_CLAZZ_MAP.put(symbolClazz.getSimpleName(), symbolClazz);
	}

	public static final Class<? extends ISymbol> getSymbolClass(final String clazzSimpleName) {
		return SYMBOL_CLAZZ_MAP.get(clazzSimpleName);
	}

}
