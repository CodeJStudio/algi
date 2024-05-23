package com.codejstudio.lim.pojo.i;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface ICloneable {

	/* interface methods */

	ICloneable cloneElement(Map<String, BaseElement> clonedElementMap) throws LIMException;

}
