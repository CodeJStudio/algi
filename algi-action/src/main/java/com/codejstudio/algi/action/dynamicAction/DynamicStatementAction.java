package com.codejstudio.algi.action.dynamicAction;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.util.AbstractDynamicSymbol;
import com.codejstudio.lim.pojo.util.DynamicInformation;
import com.codejstudio.lim.pojo.util.DynamicableUtil;
import com.codejstudio.lim.pojo.util.PartDynamicalizedStaticInformation;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DynamicStatementAction extends AbstractDynamicAction {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DynamicStatementAction.class);

	public static final String PARAMETER_ELEMENT = DynamicStatementAction.class.getSimpleName() 
			+ SEPARATOR_PARAMETER_NAME + "element";


	/* variables */

	protected final Statement dynamicStatement;

	protected final DynamicInformation dynamicInformation;

	protected final int inputCount;
	protected final int outputCount;

	protected String substitutedStaticDescription;


	/* variables: arrays, collections, maps, groups */

	protected final DynamicParameterType[] typeArray;


	/* constructors */

	public DynamicStatementAction(Statement dynamicStatement) throws ALGIException {
		super();
		Boolean dn;
		if (dynamicStatement == null 
				|| (dn = dynamicStatement.getDynamic()) == null || !dn) {
			throw new ALGIException(new IllegalArgumentException());
		}
		this.dynamicStatement = dynamicStatement;
		this.dynamicInformation = dynamicStatement.getDynamicInformation();
		this.typeArray = this.dynamicInformation.getDynamicFragmentParameterTypeArray();
		this.inputCount = this.dynamicInformation.getInputCount();
		this.outputCount = this.dynamicInformation.getOutputCount();
	}


	/* getters & setters */

	public Statement getDynamicElement() {
		return dynamicStatement;
	}

	public String getSubstitutedStaticDescription() {
		return substitutedStaticDescription;
	}

	public DynamicParameterType[] getTypeArray() {
		return typeArray;
	}


	/* overridden methods */

	@Override
	protected Object[] doExecute() throws LIMException {
		Object obj = this.session.getInputParameter(PARAMETER_ELEMENT);
		Object[] inputArray;
		DynamicInformation di;
		String substitution;
		if (obj instanceof Object[] 
				&& (inputArray = makeupInputArray((Object[]) obj)).length == this.inputCount) {
			this.substitutedStaticDescription = this.dynamicStatement
					.substituteStaticInputsForDynamic(this.typeArray, inputArray);
		} else if ((di = this.dynamicStatement.getDynamicInformation()) != null 
				&& (substitution = DynamicableUtil.substituteStaticContentForDynamic(
						di, this.session)) != null 
				&& !DynamicableUtil.INPUT_DYNAMIC_SYMBOL.containSymbol(substitution)) {
			this.substitutedStaticDescription 
					= DynamicableUtil.substitutePlaceholderForDynamicContent(substitution);
		} else {
			return null;
		}


		Collection<InformationElement> iec = (this.outputCount == 0) 
				? Informationiverse.seekInformationsByDescription(substitutedStaticDescription) 
				: Informationiverse.seekInformationsByDescription(substitutedStaticDescription, 
								AbstractDynamicSymbol.DYNAMIC_PLACEHOLDER);
		if (CollectionUtil.checkNullOrEmpty(iec)) {
			return new Object[] {new Statement(substitutedStaticDescription)};
		}

		Collection<PartDynamicalizedStaticInformation> pdsic = CollectionUtil.generateNewCollection();
		for (InformationElement ie : iec) {

			// Toggle below commented lines, to unlock the thinking process tracking
			log.debug(LogUtil.wrap("this.dynamicStatement: " + this.dynamicStatement + "; ie: " + ie 
//					+ "; this.substitutedStaticDescription: " + this.substitutedStaticDescription
					));
			log.debug("");

			PartDynamicalizedStaticInformation pdsi = PartDynamicalizedStaticInformation.newInstance(
					this.dynamicStatement, ie, this.substitutedStaticDescription, this.typeArray);
			if (pdsi != null) {
				pdsic.add(pdsi);
			}
		}
		return !CollectionUtil.checkNullOrEmpty(pdsic) ? pdsic.toArray() 
				: new Object[] {new Statement(substitutedStaticDescription)};
	}

}
