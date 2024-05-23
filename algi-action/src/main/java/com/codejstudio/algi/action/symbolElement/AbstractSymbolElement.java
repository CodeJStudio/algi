package com.codejstudio.algi.action.symbolElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.lim.pojo.i.ISymbol;
import com.codejstudio.lim.pojo.i.ISymbolElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractSymbolElement implements ISymbolElement {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(AbstractSymbolElement.class);

	private static final long serialVersionUID = -485862772216295018L;


	/* variables */

	protected ISymbol symbol;

	protected String source;


	/* getters & setters */

	@Override
	public ISymbol getSymbol() {
		return symbol;
	}

	@Override
	public String getSource() {
		return source;
	}


	/* static methods */

	public static ISymbolElement newInstance(final Class<? extends ISymbolElement> symbolElementClazz, 
			final String source, final SymbolAction symbolAction) {
		if (symbolElementClazz == null) {
			return null;
		}

		try {
			return symbolElementClazz.getDeclaredConstructor(String.class, SymbolAction.class)
					.newInstance(source, symbolAction);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return null;
	}

}
