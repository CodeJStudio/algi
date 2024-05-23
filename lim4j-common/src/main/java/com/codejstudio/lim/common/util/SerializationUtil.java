package com.codejstudio.lim.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class SerializationUtil {

	/* static methods */

	public static final byte[] serialize(final Object object) throws LIMException {
		if (object == null) {
			return null;
		}

		ObjectOutputStream oos = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			return baos.toByteArray();
		} catch (IOException e) {
			throw LIMException.getLIMException("Failed to serialize object of type: " + object.getClass(), e);
		} finally {
			CloseUtil.quietlyClose(oos);
		}
	}

	public static final Object deserialize(final byte[] byteArray) throws LIMException {
		if (byteArray == null) {
			return null;
		}

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(byteArray));
			return ois.readObject();
		} catch (IOException e) {
			throw LIMException.getLIMException("Failed to deserialize object", e);
		} catch (ClassNotFoundException e) {
			throw LIMException.getLIMException("Failed to deserialize object type", e);
		} finally {
			CloseUtil.quietlyClose(ois);
		}
	}

}
