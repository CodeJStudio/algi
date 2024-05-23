package com.codejstudio.algi.action;

import java.util.List;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.ISession;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractAction implements Action {

	/* variables */

	protected ISession session;


	/* constructors */

	public AbstractAction() {
		super();
	}


	/* getters & setters */

	@Override
	public ISession getSession() {
		return session;
	}

	@Override
	public void setSession(ISession session) {
		this.session = session;
	}


	/* overridden methods */

	@Override
	public Object[] execute(final ISession session, final InputParameter... inputParameters) 
			throws LIMException {
		setSession(session);
		return execute(inputParameters);
	}

	@Override
	public Object[] execute(final InputParameter... inputParameters) throws LIMException {
		putInputParameter(inputParameters);
		preExecute(inputParameters);
		return doExecute();
	}

	private void putInputParameter(final InputParameter... inputParameters) throws LIMException {
		this.session = (this.session == null) ? new ActionSession() : this.session;
		this.session.putInputParameter(inputParameters);
	}

	protected void preExecute(InputParameter... inputParameters) throws LIMException {}

	protected abstract Object[] doExecute() throws LIMException;


	/* static methods */

	protected static Object[] makeupInputArray(final Object... inputs) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(inputs)) {
			return null;
		}

		boolean flag = false;
		for (Object obj : inputs) {
			if (obj != null && obj.getClass().isArray()) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			return inputs;
		}

		List<Object> objl = CollectionUtil.generateNewList();
		for (Object obj : inputs) {
			if (obj == null) {
				continue;
			}
			if (obj.getClass().isArray()) {
				Object[] obja = (Object[]) obj;
				for (Object o : obja) {
					if (o != null) {
						objl.add(o);
					}
				}
			} else {
				objl.add(obj);
			}
		}
		return CollectionUtil.checkNullOrEmpty(objl) ? null : objl.toArray();
	}

}
