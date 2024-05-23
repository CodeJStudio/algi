package com.codejstudio.algi.common.exception;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * This is the root exception class for all ALGI exceptions.
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ALGIException extends LIMException {

	/* constants */

	private static final long serialVersionUID = 8419349591946927137L;


	/* constructors */

	public ALGIException(String message) {
		super(message);
	}

	public ALGIException(Throwable cause) {
		super(cause);
	}

	public ALGIException(String message, Throwable cause) {
		super(message, cause);
	}


	/* static methods */

	public static final ALGIException getALGIException(final Throwable cause) {
		return (cause instanceof ALGIException) ? (ALGIException) cause : new ALGIException(cause);
	}

	public static final ALGIException getALGIException(final String message, final Throwable cause) {
		return (cause instanceof ALGIException) ? (ALGIException) cause : new ALGIException(message, cause);
	}

}
