package com.codejstudio.lim.pojo.i;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface IActionable {

	/* interface methods */

	Object[] execute(ISession session, InputParameter... inputParameters) throws LIMException;

	Object[] execute(InputParameter... inputParameters) throws LIMException;

}
