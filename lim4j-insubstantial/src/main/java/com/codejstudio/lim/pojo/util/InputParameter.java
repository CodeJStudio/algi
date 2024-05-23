package com.codejstudio.lim.pojo.util;

import java.util.List;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.i.ICloneable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class InputParameter implements ICloneable {

	/* variables */

	protected String name;

	protected Object parameter;


	/* constructors */

	public InputParameter(String name, Object parameter) {
		super();
		setName(name);
		setParameter(parameter);
	}


	/* getters & setters */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}


	/* overridden methods */

	@Override
	public InputParameter cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		InputParameter clonedElement;
		try {
			clonedElement = (InputParameter) this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}

		clonedElement.name = this.name;
		clonedElement.parameter = (this.parameter instanceof ICloneable) 
				? ((ICloneable) this.parameter).cloneElement(clonedElementMap) : this.parameter;

		return clonedElement;
	}


	/* static methods */

	public static final InputParameter[] generateInstances(final Object... inputs) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(inputs) || inputs.length % 2 != 0) {
			return null;
		}

		List<InputParameter> ipl = CollectionUtil.generateNewList();
		for (int i = 0; (i * 2 + 1) < inputs.length; i++) {
			Object name = inputs[i * 2];
			Object parameter = inputs[i * 2 + 1];
			if (!(name instanceof String)) {
				continue;
			}
			ipl.add(new InputParameter((String) name, parameter));
		}

		return CollectionUtil.checkNullOrEmpty(ipl) ? null : ipl.toArray(new InputParameter[0]);
	}

	public static final InputParameter[] informationInstances(final Object... inputs) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(inputs) || inputs.length % 2 != 0) {
			return null;
		}

		List<InputParameter> ipl = CollectionUtil.generateNewList();
		for (int i = 0; (i * 2 + 1) < inputs.length; i++) {
			Object name = inputs[i * 2];
			Object parameter = inputs[i * 2 + 1];
			if (!(name instanceof String)) {
				continue;
			}
			ipl.add(new InputParameter((String) name, 
					((parameter instanceof InformationElement) 
							? ((InformationElement) parameter).getDescriptionObject() : parameter)));
		}

		return CollectionUtil.checkNullOrEmpty(ipl) ? null : ipl.toArray(new InputParameter[0]);
	}

}
