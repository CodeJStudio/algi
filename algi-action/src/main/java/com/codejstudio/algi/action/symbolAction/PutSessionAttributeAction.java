package com.codejstudio.algi.action.symbolAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.action.ActionSession;
import com.codejstudio.algi.action.symbol.PutSessionAttributeSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.util.AbstractDynamicSymbol;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class PutSessionAttributeAction extends AbstractMultiSymbolContentAction {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(PutSessionAttributeAction.class);

	private static final long serialVersionUID = 2654995269265831640L;


	/* constructors */

	public PutSessionAttributeAction() {
		super();
		this.symbol = PutSessionAttributeSymbol.getInstance();
	}

	public PutSessionAttributeAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public PutSessionAttributeAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String name = ((name = AbstractDynamicSymbol.stripSingleDynamicSymbol(
				(String) this.multiContentArray[0])) != null) ? name : (String) this.multiContentArray[0];
		Object attribute = this.multiContentArray[1];
		String type = (this.multiContentArray[2] == null) ? null : (String) this.multiContentArray[2];

		try {
			if (!StringUtil.isEmpty(type)) {
				switch (type.toLowerCase()) {
					case PutSessionAttributeSymbol.TYPE_BYTE:
						attribute = Byte.valueOf(attribute.toString());
						break;
					case PutSessionAttributeSymbol.TYPE_SHORT:
						attribute = Short.valueOf(attribute.toString());
						break;
					case PutSessionAttributeSymbol.TYPE_INT:
					case PutSessionAttributeSymbol.TYPE_INTEGER:
						attribute = Integer.valueOf(attribute.toString());
						break;
					case PutSessionAttributeSymbol.TYPE_LONG:
						attribute = Long.valueOf(attribute.toString());
						break;
					case PutSessionAttributeSymbol.TYPE_FLOAT:
						attribute = Float.valueOf(attribute.toString());
						break;
					case PutSessionAttributeSymbol.TYPE_DOUBLE:
						attribute = Double.valueOf(attribute.toString());
						break;
					case PutSessionAttributeSymbol.TYPE_BOOLEAN:
						attribute = Boolean.valueOf(attribute.toString());
						break;
					case PutSessionAttributeSymbol.TYPE_INFO_STRING:
						attribute = (attribute instanceof InformationElement) 
								? ((InformationElement)attribute).getDescription() : attribute;
						break;
				}
			}
		} catch (LIMException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.toString(), e);
		}

		if (this.session == null) {
			this.session = new ActionSession();
		}
		this.session.putAttribute(name, attribute);

		return new Object[] {attribute};
	}

}
