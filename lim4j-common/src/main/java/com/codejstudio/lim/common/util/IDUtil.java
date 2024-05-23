package com.codejstudio.lim.common.util;

import java.util.UUID;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * <code>IDUtil</code> is written to generate ID in String type.<br>
 * Which type of generation can be configured in "common.properties".<br>
 * eg. "increment" or "uuid".<br>
 * The default type is "increment".
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public final class IDUtil {

	/* enumeration */

	public enum IdGenerationType{
		UUID,
		INCREMENT,
	}


	/* constants */

	protected static final String ID_GENERATION_KEY = "idGeneration";


	/* static methods */

	/**
	 * The entrance of ID generation.
	 */
	public synchronized static final String generateID() throws LIMException {
		String idGenerationType = PropertiesLoader
				.getProperty(PropertiesLoader.PROPERTIES_FILENAME_COMMON, ID_GENERATION_KEY);
		if (idGenerationType == null) {
			// default type:
			return generateIncrementID();
		}

		switch (IdGenerationType.valueOf(idGenerationType.toUpperCase())) {
			case UUID:
				return generateUUID();
			case INCREMENT:
			default:
				return generateIncrementID();
		}
	}



	public synchronized static final String generateUUID() {
		return UUID.randomUUID().toString();
	}


	private static long incrementId = 0;

	public synchronized static final String generateIncrementID() {
		return "" + incrementId++;
	}

}
