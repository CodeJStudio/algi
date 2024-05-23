package com.codejstudio.algi.action.symbolAction;

import java.util.Collection;

import com.codejstudio.algi.action.symbol.InformationiverseSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.util.AbstractDynamicSymbol;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class InformationiverseAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = -5567119186739236441L;


	/* constructors */

	public InformationiverseAction() {
		super();
		this.symbol = InformationiverseSymbol.getInstance();
	}

	public InformationiverseAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public InformationiverseAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String dsc = new Description((String) this.multiContentArray[0], false).getDecodedText();
		LogicValue newableFlag = (this.multiContentArray[1] == null) 
				? null : (LogicValue) this.multiContentArray[1];

		Collection<InformationElement> iec = (dsc.indexOf(AbstractDynamicSymbol.DYNAMIC_PLACEHOLDER) >= 0) 
				? Informationiverse.seekInformationsByDescription(dsc, AbstractDynamicSymbol.DYNAMIC_PLACEHOLDER) 
				: Informationiverse.seekInformationsByDescription(dsc);
		return !CollectionUtil.checkNullOrEmpty(iec) ? iec.toArray() 
				: (!LogicValue.TRUE.valueEquals(newableFlag) 
						? null : new Object[] {new InformationElement(dsc)});
	}

}
