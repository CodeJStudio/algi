package com.codejstudio.algi.common;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractALGIRunnable implements IALGIRunnable {

	/* variables */

	protected DispatchedObject dispatchedObject;


	/* getters & setters */

	@Override
	public DispatchedObject getDispatchedObject() {
		return dispatchedObject;
	}

	@Override
	public void setDispatchedObject(DispatchedObject dispatchedObject) {
		this.dispatchedObject = dispatchedObject;
	}

}
