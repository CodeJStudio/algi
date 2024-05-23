package com.codejstudio.lim.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ObjectUtil.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public final class ObjectUtil {

	/* static methods */

	public static final boolean isNumeric(final Object object) {
		return (object instanceof Number) ? true 
				: (!(object instanceof CharSequence) ? false : StringUtil.isNumeric((CharSequence) object));
	}

	public static final Integer integerValue(final Object object) {
		if (!isNumeric(object)) {
			return null;
		}
		return (object instanceof Number) ? ((Number) object).intValue() 
				: (!(object instanceof CharSequence) ? null : StringUtil.integerValue((CharSequence) object));
	}

	public static final Long longValue(final Object object) {
		if (!isNumeric(object)) {
			return null;
		}
		return (object instanceof Number) ? ((Number) object).longValue() 
				: (!(object instanceof CharSequence) ? null : StringUtil.longValue((CharSequence) object));
	}

	public static final Double doubleValue(final Object object) {
		if (!isNumeric(object)) {
			return null;
		}
		return (object instanceof Number) ? ((Number) object).doubleValue() 
				: (!(object instanceof CharSequence) ? null : StringUtil.doubleValue((CharSequence) object));
	}


	public static final List<Object[]> permutate(final List<Object> objectList, 
			final boolean duplicateFlag) {
		return permutate(objectList, 0, duplicateFlag);
	}

	public static final List<Object[]> permutate(final List<Object> objectList, final int length, 
			final boolean duplicateFlag) {
		if (CollectionUtil.checkNullOrEmpty(objectList)) {
			return null;
		}

		int l = length;
		if (length <= 0 || length > objectList.size()) {
			l = objectList.size();
		}

		List<Object[]> objal = objectList.stream().map(obj -> new Object[]{obj}).collect(Collectors.toList());
		Stream<Object[]> stream = objal.stream();
		for (int i = 1; i < l; i++) {
			stream = stream.flatMap(oa -> 
					(duplicateFlag ? objectList.stream() 
							: objectList.stream().filter(temp -> !Arrays.asList(oa).contains(temp)))
					.map(obj -> {
						Object[] obja = Arrays.copyOf(oa, oa.length + 1); 
						obja[obja.length - 1] = obj; 
						return obja;
					}));
		}
		return stream.collect(Collectors.toList());
	}

}
