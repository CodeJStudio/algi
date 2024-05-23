package com.codejstudio.algi.action.symbolAction;

import java.util.List;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.dynamicAction.DynamicStatementAction;
import com.codejstudio.algi.action.dynamicAction.VerifySubElementAction;
import com.codejstudio.algi.action.symbol.DynamicStatementSymbol;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.PartDynamicalizedStaticInformation;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DynamicStatementSymbolAction extends AbstractMultiSymbolContentAction {

	/* constants */

	private static final long serialVersionUID = -5662610215697249052L;


	/* constructors */

	public DynamicStatementSymbolAction() {
		super();
		this.symbol = DynamicStatementSymbol.getInstance();
	}

	public DynamicStatementSymbolAction(String sourceDescription) throws LIMException {
		this();
		setSourceDescription(sourceDescription);
	}

	public DynamicStatementSymbolAction(Description sourceDescriptionObject) throws LIMException {
		this();
		setSourceDescriptionObject(sourceDescriptionObject);
	}


	/* overridden methods */

	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		if (!verifyContent()) {
			return null;
		}

		String source = (String) this.multiContentArray[0];
		String type = (this.multiContentArray[1] == null) ? null : (String) this.multiContentArray[1];

		Statement dynamicStatement = null;
		if (!StringUtil.isEmpty(type) && type.toLowerCase().equals(DynamicStatementSymbol.TYPE_INFO)) {
			GenericElement ge = Informationiverse.getElement(source);
			dynamicStatement = !(ge instanceof Statement) ? null : (Statement) ge;
		} else {
			dynamicStatement = getDynamicStatementByDynamicContent(source);
		}

		Boolean dn;
		if (dynamicStatement == null || (dn = dynamicStatement.getDynamic()) == null || !dn) {
			return null;
		}

		DynamicStatementAction dsa = new DynamicStatementAction(dynamicStatement);
		Object[] dsaOutputArray = dsa.execute(this.session);



		List<Object> vseaOutputList = CollectionUtil.generateNewList();
		for (int i = 0; (dsaOutputArray != null && i < dsaOutputArray.length); i++) {
			if (dsaOutputArray[i] instanceof PartDynamicalizedStaticInformation) {
				Action vsea = new VerifySubElementAction(
						(PartDynamicalizedStaticInformation) dsaOutputArray[i]);
				Object[] vseaOutputArray = vsea.execute();
				CollectionUtil.addAllOfSubClass(vseaOutputList, vseaOutputArray);
			}
		}

		return CollectionUtil.checkNullOrEmpty(vseaOutputList) ? null : vseaOutputList.toArray();
	}

	private Statement getDynamicStatementByDynamicContent(final String dynamicContent) throws LIMException {
		if (StringUtil.isEmpty(dynamicContent) ) {
			return null;
		}

		InformationElement ie = Informationiverse.seekInformationByDescription(dynamicContent);
		return (ie instanceof Statement) ? (Statement) ie : new Statement(dynamicContent);
	}


	@Override
	protected boolean dynamicInformationFlag() {
		return false;
	}

}
