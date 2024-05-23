package com.codejstudio.lim.common.util;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class CloseUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(CloseUtil.class);


	/* static methods */

	public static final void quietlyClose(final Closeable closeable) {
		try {
			close(closeable);
		} catch (IOException e) {
			log.error(e.toString(), e);
		}
	}

	public static final void close(final Closeable closeable) throws IOException {
		if (closeable != null) {
			closeable.close();
		}
	}

}
