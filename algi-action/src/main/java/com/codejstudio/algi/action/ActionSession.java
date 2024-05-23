package com.codejstudio.algi.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.ICloneable;
import com.codejstudio.lim.pojo.i.ISession;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ActionSession implements ISession {

	/* constants */

	private static final long serialVersionUID = 5881427065740392854L;


	/* variables: arrays, collections, maps, groups */

	private Map<String, Object> attributeMap;

	private Map<String, InputParameter> inputParameterMap;


	/* constructors */

	public ActionSession() throws LIMException {
		super();
	}


	/* initializers */

	protected void initAttributeMap() throws LIMException {
		if (this.attributeMap == null) {
			this.attributeMap = CollectionUtil.generateNewMap();
		}
	}

	protected void initInputParameterMap() throws LIMException {
		if (this.inputParameterMap == null) {
			this.inputParameterMap = CollectionUtil.generateNewMap();
		}
	}


	/* destroyers */

	private void destroyAttributeMap() {
		if (this.attributeMap != null && this.attributeMap.size() == 0) {
			this.attributeMap = null;
		}
	}

	private void destroyInputParameterMap() {
		if (this.inputParameterMap != null && this.inputParameterMap.size() == 0) {
			this.inputParameterMap = null;
		}
	}


	/* CRUD for arrays, collections, maps, groups: action session attributes */

	@Override
	public Object getAttribute(final String name) {
		return (this.attributeMap == null) ? null : this.attributeMap.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return (this.attributeMap == null) ? null : Collections.enumeration(this.attributeMap.keySet());
	}

	@Override
	public void putAttribute(final String name, final Object attribute) throws LIMException {
		initAttributeMap();
		this.attributeMap.put(name, attribute);
	}

	@Override
	public void removeAttribute(final String name) {
		if (this.attributeMap != null) {
			this.attributeMap.remove(name);
		}
	}


	/* CRUD for arrays, collections, maps, groups: action input parameters */

	@Override
	public Object getInputParameter(final String name) {
		InputParameter ip;
		return (this.inputParameterMap == null || (ip = this.inputParameterMap.get(name)) == null) 
				? null : ip.getParameter();
	}

	@Override
	public InputParameter[] getInputParameterArray() {
		Collection<InputParameter> ipc;
		return (this.inputParameterMap == null 
						|| CollectionUtil.checkNullOrEmpty(ipc = this.inputParameterMap.values())) 
				? null : ipc.toArray(new InputParameter[0]);
	}

	@Override
	public Enumeration<String> getInputParameterNameEnumeration() {
		return (this.inputParameterMap == null) 
				? null : Collections.enumeration(this.inputParameterMap.keySet());
	}

	@Override
	public void putInputParameter(final String name, final Object parameter) throws LIMException {
		initInputParameterMap();
		this.inputParameterMap.put(name, new InputParameter(name, parameter));
	}

	@Override
	public void putInputParameter(final InputParameter... inputParameters) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(inputParameters)) {
			return;
		}

		initInputParameterMap();
		for (InputParameter ip : inputParameters) {
			if (ip != null) {
				this.inputParameterMap.put(ip.getName(), ip);
			}
		}
	}

	@Override
	public void removeInputParameter(final String name) {
		if (this.inputParameterMap != null) {
			this.inputParameterMap.remove(name);
		}
	}

	@Override
	public void clearInputParameters() {
		if (this.inputParameterMap != null) {
			this.inputParameterMap.clear();
		}
	}


	/* overridden methods */

	@Override
	public Object getDynamicElement(final String name) {
		Object obj = getInputParameter(name);
		return (obj != null) ? obj : getAttribute(name);
	}


	@Override
	public ActionSession cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		ActionSession clonedElement;
		try {
			clonedElement = (ActionSession) this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}

		if (!CollectionUtil.checkNullOrEmpty(this.attributeMap)) {
			clonedElement.initAttributeMap();
			Set<String> atks = this.attributeMap.keySet();
			for (String k : atks) {
				Object value = this.attributeMap.get(k);
				if (value instanceof ICloneable) {
					clonedElement.attributeMap.put(k, ((ICloneable) value).cloneElement(clonedElementMap));
				} else if (value != null) {
					clonedElement.attributeMap.put(k, value);
				}
			}
			clonedElement.destroyAttributeMap();
		}

		if (!CollectionUtil.checkNullOrEmpty(this.inputParameterMap)) {
			clonedElement.initInputParameterMap();
			Set<String> ipks = this.inputParameterMap.keySet();
			for (String k : ipks) {
				InputParameter value = this.inputParameterMap.get(k);
				if (value != null) {
					clonedElement.inputParameterMap.put(k, value.cloneElement(clonedElementMap));
				}
			}
			clonedElement.destroyInputParameterMap();
		}

		return clonedElement;
	}

}
