package com.codejstudio.algi.action.symbolAction;

import java.util.Arrays;
import java.util.List;

import com.codejstudio.algi.action.symbol.SubstituteDynamicIdentifierSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.util.Description;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class SubstituteDynamicIdentifierAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = 8938628137336155486L;


	/* constructors */

	public SubstituteDynamicIdentifierAction() {
		super();
		this.symbol = SubstituteDynamicIdentifierSymbol.getInstance();
	}

	public SubstituteDynamicIdentifierAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public SubstituteDynamicIdentifierAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String elementId = (String) this.multiContentArray[0];
		Object[] mca = Arrays.copyOfRange(this.multiContentArray, 1, this.multiContentArray.length);

		GenericElement ge = Informationiverse.getElement(elementId);
		Statement s;
		List<String> strl;
		String[] stra;
		String substitutedDescription;
		if (!(ge instanceof Statement) 
				|| (s = (Statement) ge) == null
				|| CollectionUtil.checkNullOrEmpty(mca) 
				|| CollectionUtil.checkNullOrEmpty(strl = CollectionUtil.convertToList(String.class, mca)) 
				|| CollectionUtil.checkNullOrEmpty(stra = strl.toArray(new String[0])) 
				|| StringUtil.isEmpty(substitutedDescription = s.substituteDynamicIdentifier(stra))) {
			return null;
		}

		return new Object[] {substitutedDescription};
	}

}
