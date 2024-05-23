package com.codejstudio.lim.common.exception;

/**
 * This is the root exception class for all LIM exceptions.
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class LIMException extends Exception {

	/* constants */

	private static final long serialVersionUID = -4933171103303242363L;


	/* constructors */

	public LIMException(String message) {
		super(message);
	}

	public LIMException(Throwable cause) {
		super(cause);
	}

	public LIMException(String message, Throwable cause) {
		super(message, cause);
	}


	/* static methods */

	public static final LIMException getLIMException(final Throwable cause) {
		return (cause instanceof LIMException) ? (LIMException) cause : new LIMException(cause);
	}

	public static final LIMException getLIMException(final String message, final Throwable cause) {
		return (cause instanceof LIMException) ? (LIMException) cause : new LIMException(message, cause);
	}

}
