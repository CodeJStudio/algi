package com.codejstudio.algi.common;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public interface IALGIRunnable extends Runnable {

	/* interface methods */

	DispatchedObject getDispatchedObject();

	void setDispatchedObject(DispatchedObject dispatchedObject);

}
