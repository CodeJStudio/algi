package com.codejstudio.algi.action.symbolAction;

import java.util.Enumeration;

import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.action.symbol.CodeExecuteSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CodeExecuteAction extends AbstractSingleSymbolContentAction {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(CodeExecuteAction.class);

	private static final long serialVersionUID = -6984479534873439290L;


	/* variables */

	private static JexlEngine engine = new Engine();


	/* constructors */

	public CodeExecuteAction() {
		super();
		this.symbol = CodeExecuteSymbol.getInstance();
	}

	public CodeExecuteAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public CodeExecuteAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String expression = (String) this.singleContent;
		Object result = null;
		try {
			expression = new Description(expression, false).getDecodedText();
			JexlExpression je = engine.createExpression(expression);
			JexlContext context = new MapContext();
			if (this.session != null) {
				Enumeration<String> se = this.session.getAttributeNames();
				while (se != null && se.hasMoreElements()) {
					String name = se.nextElement();
					context.set(name, session.getAttribute(name));
				}
			}
			result = je.evaluate(context);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return (result == null) ? null : new Object[] {result};

	}

}
