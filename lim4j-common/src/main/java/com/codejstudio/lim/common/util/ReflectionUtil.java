package com.codejstudio.lim.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class ReflectionUtil {

	/* static methods */

	public static final Executable findExecutable(final Executable[] executableArray, 
			final Class<?>... parameterTypes) {
		if (CollectionUtil.checkNullOrEmpty(executableArray)) {
			return null;
		}

		for (Executable exe : executableArray) {
			Class<?>[] pta = exe.getParameterTypes();
			if (CollectionUtil.arrayEquals(parameterTypes, pta)) {
				return exe;
			}
		}
		for (Executable exe : executableArray) {
			Class<?>[] pta = exe.getParameterTypes();
			if (CollectionUtil.sizeEquals(parameterTypes, pta)) {
				boolean flag = true;
				for (int i = 0; i < parameterTypes.length; i++) {
					if (!pta[i].isAssignableFrom(parameterTypes[i])) {
						flag = false;
						break;
					}
				}
				if (flag) {
					return exe;
				}
			}
		}
		for (Executable exe : executableArray) {
			Class<?>[] pta = exe.getParameterTypes();
			if (!CollectionUtil.checkNullOrEmpty(pta) 
					&& pta[pta.length - 1] != null && pta[pta.length - 1].isArray() 
					&& CollectionUtil.arrayEquals(parameterTypes, pta, pta.length - 1)) {
				boolean flag = true;
				Class<?> ct = pta[pta.length - 1].getComponentType();
				for (int i = pta.length - 1; i < parameterTypes.length; i++) {
					if (!ct.isAssignableFrom(parameterTypes[i])) {
						flag = false;
						break;
					}
				}
				if (flag) {
					return exe;
				}
			}
		}
		for (Executable exe : executableArray) {
			Class<?>[] pta = exe.getParameterTypes();
			if (!CollectionUtil.checkNullOrEmpty(pta) 
					&& pta[pta.length - 1] != null && pta[pta.length - 1].isArray()) {
				boolean flag = true;
				for (int i = 0; i < pta.length - 1; i++) {
					if (!pta[i].isAssignableFrom(parameterTypes[i])) {
						flag = false;
						break;
					}
				}
				if (!flag) {
					continue;
				}
				Class<?> ct = pta[pta.length - 1].getComponentType();
				for (int i = pta.length - 1; i < parameterTypes.length; i++) {
					if (!ct.isAssignableFrom(parameterTypes[i])) {
						flag = false;
						break;
					}
				}
				if (flag) {
					return exe;
				}
			}
		}

		return null;
	}

	public static final Object invokeExecutable(Executable executable, final boolean forceAccessFlag, 
			final Object object, final Object... args) throws LIMException {
		if (executable == null) {
			return null;
		}

		Class<?>[] pta = executable.getParameterTypes();
		Object[] obja = args;
		if (!CollectionUtil.checkNullOrEmpty(pta) 
				&& pta[pta.length - 1] != null && pta[pta.length - 1].isArray()) {
			if (pta.length > 1 && (args == null || args.length < pta.length - 1)) {
				return null;
			}

			List<Object> argList = CollectionUtil.generateNewList();
			argList.addAll(Arrays.asList(Arrays.copyOfRange(args, 0, pta.length - 1)));

			Object[] varargs = Arrays.copyOfRange(args, pta.length - 1, args.length);
			Class<?> ct = pta[pta.length - 1].getComponentType();
			Class<?> vacl;
			if (varargs.length == 1 && varargs[0] != null && (vacl = varargs[0].getClass()).isArray() 
					&& ct.isAssignableFrom(vacl.getComponentType())) {
				argList.add(varargs[0]);
			} else {
				List<Object> varargList = CollectionUtil.generateNewList();
				for (Object va : varargs) {
					if (ct.isInstance(va)) {
						varargList.add(va);
					}
				}
				varargs = varargList.toArray(CollectionUtil.generateNewArray(ct, 0));
				argList.add(varargs);
			}
			obja = argList.toArray();
		}

		boolean accessibleFlag = false;
		try {
			accessibleFlag = executable.isAccessible();
			if (forceAccessFlag && !accessibleFlag) {
				executable.setAccessible(true);
			}
			if (executable instanceof Constructor) {
				return ((Constructor<?>) executable).newInstance(obja);
			} else if (executable instanceof Method) {
				return ((Method) executable).invoke(object, obja);
			}
		} catch (Exception e) {
			throw LIMException.getLIMException(e);
		} finally {
			if (forceAccessFlag && executable != null) {
				executable.setAccessible(accessibleFlag);
			}
		}

		return null;
	}


	public static final Constructor<?> findConstructor(final Class<?> clazz, 
			final Class<?>... parameterTypes) {
		return (clazz == null) ? null 
				: (Constructor<?>) findExecutable(clazz.getDeclaredConstructors(), parameterTypes);
	}

	public static final Object invokeConstructor(final Constructor<?> constructor, 
			final boolean forceAccessFlag, final Object... args) throws LIMException {
		return invokeExecutable(constructor, forceAccessFlag, null, args);
	}


	public static final Method findMethod(final Class<?> clazz, final String methodName, 
			final Class<?>... parameterTypes) throws LIMException {
		return (Method) findExecutable(findMethodsByName(clazz, methodName), parameterTypes);
	}

	public static final Method[] findMethodsByName(final Class<?> clazz, final String methodName) 
			throws LIMException {
		if (clazz == null || StringUtil.isEmpty(methodName)) {
			return null;
		}

		List<Method> ml = CollectionUtil.generateNewList();
		Method[] ma = clazz.getDeclaredMethods();
		for (Method m : ma) {
			if (methodName.equals(m.getName())) {
				ml.add(m);
			}
		}

		if (ml.size() == 0) {
			ma = clazz.getMethods();
			for (Method m : ma) {
				if (methodName.equals(m.getName())) {
					ml.add(m);
				}
			}
		}

		return ml.toArray(new Method[0]);
	}

	public static final Object invokeMethod(final Method method, final boolean forceAccessFlag, 
			final Object object, final Object... args) throws LIMException {
		return invokeExecutable(method, forceAccessFlag, object, args);
	}


	public static final Field findField(final Class<?> clazz, final String fieldName) throws LIMException {
		if (clazz == null || StringUtil.isEmpty(fieldName)) {
			return null;
		}
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			throw LIMException.getLIMException(e);
		}
	}

	public static final Object getFieldObject(Field field, final Object object) throws LIMException {
		if (field == null) {
			return null;
		}

		boolean accessibleFlag = false;
		try {
			accessibleFlag = field.isAccessible();
			if (!accessibleFlag) {
				field.setAccessible(true);
			}
			return field.get(object);
		} catch (Exception e) {
			throw LIMException.getLIMException(e);
		} finally {
			if (field != null) {
				field.setAccessible(accessibleFlag);
			}
		}
	}

}
