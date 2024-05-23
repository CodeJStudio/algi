package com.codejstudio.algi.action.symbolAction;

import java.io.Serializable;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.symbol.ISurroundingSymbolCombination;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.i.ISession;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public interface SymbolAction extends Action, Serializable {

	/* interface methods */

	void setSymbolCombination(ISurroundingSymbolCombination symbolCombination);

	void setSymbolElement(ISymbolElement symbolElement);

	void setSourceDescription(String sourceDescription) throws LIMException;

	void setSourceDescriptionObject(Description sourceDescriptionObject) throws LIMException;


	Object[] execute(String sourceDescription, ISession session, InputParameter... inputParameters) 
			throws LIMException;

}
