package com.codejstudio.algi.action.symbolAction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.ActionQueue;
import com.codejstudio.algi.common.PropertiesConstant;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;
import com.codejstudio.lim.common.util.StringUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractDoRepeatedlyAction extends AbstractComplexSurroundingSymbolAction {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(AbstractDoRepeatedlyAction.class);

	private static final long serialVersionUID = -5748266396734586259L;

	static final String PROPERTY_KEY_REPEAT_LIMIT = "repeatLimit";

	private static final Integer REPEAT_LIMIT;


	/* variables */

	protected String logicValue;

	protected String executingBody;

	protected String parametersRepeated;


	/* initializers */

	static {
		try {
			REPEAT_LIMIT = Integer.parseInt(PropertiesLoader.getProperty(
					PropertiesConstant.PROPERTIES_FILENAME_ACTION, PROPERTY_KEY_REPEAT_LIMIT));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/* overridden methods */

	@Override
	protected boolean makeupComplexContent(final Object[] sourceArray) {
		if (sourceArray == null || !(sourceArray instanceof String[]) || sourceArray.length < 5) {
			return false;
		}
		return setupParameters((String[]) sourceArray);
	}

	protected abstract boolean setupParameters(String[] sourceArray);


	@Override
	protected Object[] doExecuteWithContent() throws LIMException {
		List<Object> resultList = CollectionUtil.generateNewList();

		{
			int count = 0;
			if (!StringUtil.isEmpty(this.parametersOnce)) {
				new ActionQueue(new ActionDefinition(this.parametersOnce, false)).execute(this.session);
			}

			boolean firstRoundFlag = true;
			boolean repeatedFlag = true;
			while (repeatedFlag 
					&& (REPEAT_LIMIT == null || REPEAT_LIMIT < 0 || count < REPEAT_LIMIT)
					) {
				if ((firstRoundFlag && firstRoundExecutingWithoutConditionFlag())
						|| (repeatedFlag = getConditionFlag(this.condition, this.logicValue))) {
					Object[] resultArray = new ActionFlow(new ActionDefinition(this.executingBody, false))
							.execute(this.session);
					CollectionUtil.addAllOfSubClass(resultList, ((resultArray == null) ? null : resultArray));
				}

				if (!StringUtil.isEmpty(this.parametersRepeated)) {
					new ActionQueue(new ActionDefinition(this.parametersRepeated, false))
							.execute(this.session);
				}

				count++;
				firstRoundFlag = false;
			}
		}

		return CollectionUtil.checkNullOrEmpty(resultList) ? null : resultList.toArray();
	}

	protected abstract boolean firstRoundExecutingWithoutConditionFlag();

}
