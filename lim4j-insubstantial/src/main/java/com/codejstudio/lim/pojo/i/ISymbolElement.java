package com.codejstudio.lim.pojo.i;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface ISymbolElement extends Serializable {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(ISymbolElement.class);


	/* interface methods */

	ISymbol getSymbol();

	String getSource();

	Object[] executeAction(ISession session, InputParameter... inputParameters) throws LIMException;


	/* static methods */

	static ISymbolElement newInstance(final Class<? extends ISymbolElement> symbolElementClazz, 
			final String source) {
		if (symbolElementClazz == null) {
			return null;
		}

		try {
			return symbolElementClazz.getDeclaredConstructor(String.class).newInstance(source);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return null;
	}

}
