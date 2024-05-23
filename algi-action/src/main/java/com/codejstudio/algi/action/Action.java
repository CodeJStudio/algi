package com.codejstudio.algi.action;

import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.pojo.i.IActionable;
import com.codejstudio.lim.pojo.i.ISession;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public interface Action extends IActionable {

	/* constants */

	static final String SEPARATOR_PARAMETER_NAME = "" + WordSeparator.UNDERSCORE.getCharacter();


	/* interface methods */

	ISession getSession();

	void setSession(ISession session);

}
