package com.codejstudio.algi.action.symbolAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractComplexSurroundingSymbolAction extends AbstractSurroundingSymbolAction 
		implements ComplexSymbolAction {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(AbstractComplexSurroundingSymbolAction.class);

	private static final long serialVersionUID = 3058461234965089527L;


	/* variables */

	protected String parametersOnce;

	protected String condition;


	/* initializers */

	@Override
	protected void init() throws LIMException {
		if (this.symbolElement == null) {
			initSymbolElement();
		}
		this.symbolFlag = (this.symbolCombination != null) 
				? this.symbolCombination.verifySurroundingSymbol(this.sourceDescription) : this.symbolFlag;
	}

	protected abstract void initSymbolElement() throws LIMException;


	/* overridden methods */

	@Override
	protected boolean makeupContent() throws LIMException {
		if (this.symbolCombination == null && !StringUtil.isEmpty(this.sourceDescription)) {
			initSymbolElement();
		}
		if (this.symbolCombination == null || StringUtil.isEmpty(this.sourceDescription)) {
			return false;
		}

		Object[] obja = this.symbolCombination.strip(this.sourceDescription);
		return makeupComplexContent(obja);
	}

	protected abstract boolean makeupComplexContent(Object[] sourceArray);


	@Override
	protected boolean dynamicInformationFlag() {
		return false;
	}


	/* class methods */

	protected boolean getConditionFlag(final String condition, final String logicValue, 
			final InputParameter... inputParameters) throws LIMException {
		Object cdobj = executeCondition(condition, inputParameters);
		LogicValue cdlv;
		LogicValue lv = LogicValue.getLogicValue(logicValue);
		return (cdobj instanceof Condition && ((Condition) cdobj).logicValueEquals(lv)) 
				|| ((cdlv = LogicValue.getLogicValue(cdobj)) != null && cdlv.valueEquals(lv)) 
				|| (cdlv == null && executeConditionAndLogicValue(cdobj, logicValue));
	}

	private Object executeCondition(final String condition, final InputParameter... inputParameters) 
			throws LIMException {
		Object[] cdobja = new ActionFlow(new ActionDefinition(condition, false))
				.execute(this.session, inputParameters);
		if (!CollectionUtil.checkNullOrEmpty(cdobja)) {
			return cdobja[0];
		} else {
			return null;
		}
	}

	private boolean executeConditionAndLogicValue(final Object conditionObject, final String logicValue, 
			final InputParameter... inputParameters) {
		try {
			this.session.putAttribute(SESSION_ATTRIBUTE_NAME_CONDITION, conditionObject);
			Object[] cxaOutputArray = new CodeExecuteAction(logicValue)
					.execute(this.session, inputParameters);
			return (CollectionUtil.checkNullOrEmpty(cxaOutputArray) 
							|| !(cxaOutputArray[0] instanceof Boolean)) 
					? false : (Boolean) cxaOutputArray[0];
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return false;
	}

}
