package com.codejstudio.lim.pojo.i;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface IConditionable {

	/* getters & setters */

	/**
	 * The implemented classes should also contain:<br/>
	 * private ConditionGroup conditionGroup;<br/>
	 * private void initConditionGroup();<br/>
	 * private void destroyConditionGroup();
	 */
	ConditionGroup getConditionGroup();


	/* CRUD for arrays, collections, maps, groups: conditions */

	boolean containCondition(Condition condition) throws LIMException;

	boolean containCondition(String id);

	boolean addCondition(Condition... conditions) throws LIMException;

	boolean addCondition(Collection<Condition> conditionCollection) throws LIMException;

	boolean removeCondition(String id);


	/* static methods */

	static boolean verifyCondition(final InformationElement element, final ConditionGroup conditionGroup) 
			throws LIMException {
		return verifyCondition(element, conditionGroup, true);
	}

	static boolean verifyCondition(final InformationElement element, final ConditionGroup conditionGroup, 
			final boolean noneConditionFlag) throws LIMException {
		return (noneConditionFlag && conditionGroup == null) 
				|| conditionGroup.verifyInformation(element, noneConditionFlag);
	}

	static boolean verifyCondition(final InformationElement element, final Condition condition, 
			final boolean noneConditionFlag) throws LIMException {
		return (noneConditionFlag && condition == null) || condition.verifyInformation(element);
	}

}
