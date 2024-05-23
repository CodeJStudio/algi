package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.CompareSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class CompareAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 5679496334709680391L;


	/* constructors */

	public CompareAction() {
		super();
		this.symbol = CompareSymbol.getInstance();
	}

	public CompareAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public CompareAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	@SuppressWarnings("unchecked")
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		Number comparer = (Number) this.multiContentArray[0];
		Number comparee = (Number) this.multiContentArray[1];
		return !(comparer instanceof Comparable) ? null 
				: ((comparer.getClass() == comparee.getClass()) 
						? new Object[] {((Comparable<Number>) comparer).compareTo(comparee)} 
						: new Object[] {Double.valueOf(comparer.toString())
								.compareTo(Double.valueOf(comparee.toString()))});
	}

}
