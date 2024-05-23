package com.codejstudio.lin.common.exception;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * This is the root exception class for all LIN exceptions.
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class LINException extends LIMException {

	/* constants */

	private static final long serialVersionUID = 6092078313286920227L;


	/* constructors */

	public LINException(String message) {
		super(message);
	}

	public LINException(Throwable cause) {
		super(cause);
	}

	public LINException(String message, Throwable cause) {
		super(message, cause);
	}


	/* static methods */

	public static final LINException getLINException(final Throwable cause) {
		return (cause instanceof LINException) ? (LINException) cause : new LINException(cause);
	}

	public static final LINException getLINException(final String message, final Throwable cause) {
		return (cause instanceof LINException) ? (LINException) cause : new LINException(message, cause);
	}

}
