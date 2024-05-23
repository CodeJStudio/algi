package com.codejstudio.algi.output;

import java.io.OutputStream;

import com.codejstudio.algi.common.AbstractALGIRunnable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractOutput extends AbstractALGIRunnable implements IOutput {

	/* variables */

	protected OutputStream os;


	/* getters & setters */

	protected void setOutputStream(OutputStream os) {
		this.os = os;
	}

}
