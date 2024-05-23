package com.codejstudio.lim.pojo.i;

import java.io.Serializable;
import java.util.Enumeration;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface ISession extends ICloneable, Serializable {

	/* CRUD for arrays, collections, maps, groups: action session attributes */

	Object getAttribute(String name);

	Enumeration<String> getAttributeNames();

	void putAttribute(String name, Object attribute) throws LIMException;

	void removeAttribute(String name);


	/* CRUD for arrays, collections, maps, groups: action input parameters */

	Object getInputParameter(String name);

	InputParameter[] getInputParameterArray();

	Enumeration<String> getInputParameterNameEnumeration();

	void putInputParameter(String name, Object parameter) throws LIMException;

	void putInputParameter(InputParameter... inputParameters) throws LIMException;

	void removeInputParameter(String name);

	void clearInputParameters();


	/* interface methods */

	Object getDynamicElement(String name);

}
