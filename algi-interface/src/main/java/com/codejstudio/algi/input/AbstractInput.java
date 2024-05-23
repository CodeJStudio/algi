package com.codejstudio.algi.input;

import java.io.InputStream;

import com.codejstudio.algi.common.AbstractALGIRunnable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractInput extends AbstractALGIRunnable implements IInput {

	/* variables */

	protected InputStream is;


	/* getters & setters */

	protected void setInputStream(InputStream is) {
		this.is = is;
	}

}
