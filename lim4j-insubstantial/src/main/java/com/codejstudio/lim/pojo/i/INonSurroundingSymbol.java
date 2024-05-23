package com.codejstudio.lim.pojo.i;

import java.io.Serializable;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface INonSurroundingSymbol extends Serializable {

	/* interface methods */

	boolean containSymbol(String content) throws LIMException;

}
