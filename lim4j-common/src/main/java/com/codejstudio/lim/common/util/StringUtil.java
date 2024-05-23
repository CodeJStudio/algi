package com.codejstudio.lim.common.util;

import java.util.List;
import java.util.Objects;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class StringUtil {

	/* static methods */

	public static final String trim(final String source) {
		return (source == null) ? null : source.trim();
	}

	public static final boolean isEmpty(final CharSequence charSequence) {
		return charSequence == null || charSequence.length() == 0;
	}

	public static final boolean equals(final String source1, final String source2) {
		return Objects.equals(source1, source2);
	}

	public static final boolean equalsIgnoreCase(final String source1, final String source2) {
		return (source1 != null) ? source1.equalsIgnoreCase(source2) : (source2 == null);
	}


	public static final boolean isStringable(final Object object) {
		return object instanceof IStringable || object instanceof CharSequence 
				|| object instanceof Boolean || object instanceof Number;
	}


	public static final boolean isNumeric(final CharSequence charSequence) {
		if (isEmpty(charSequence)) {
			return false;
		}
		final int l = charSequence.length();
		for (int i = 0; i < l; i++) {
			if (!Character.isDigit(charSequence.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static final Number numberValue(final CharSequence charSequence) {
		if (isEmpty(charSequence)) {
			return null;
		}

		short i = 0;
		while (true) {
			try {
				switch (i) {
					case 0 : return Integer.parseInt(charSequence.toString());
					case 1 : return Long.parseLong(charSequence.toString());
					case 2 : return Double.parseDouble(charSequence.toString());
					default: return null;
				}
			} catch (Exception e) {
				i++;
			}
		}
	}

	public static final Integer integerValue(final CharSequence charSequence) {
		if (!isNumeric(charSequence)) {
			return null;
		}
		try {
			return Integer.parseInt(charSequence.toString());
		} catch (Exception e) {
			return null;
		}
	}

	public static final Long longValue(final CharSequence charSequence) {
		if (!isNumeric(charSequence)) {
			return null;
		}
		try {
			return Long.parseLong(charSequence.toString());
		} catch (Exception e) {
			return null;
		}
	}

	public static final Double doubleValue(final CharSequence charSequence) {
		if (!isNumeric(charSequence)) {
			return null;
		}
		try {
			return Double.parseDouble(charSequence.toString());
		} catch (Exception e) {
			return null;
		}
	}



	public static final String[] getReplacedFragment(final String source, final String replacement, 
			final String separator) throws LIMException {
		return getReplacedFragment(splitIncludingSeparator(source, separator), replacement, separator);
	}

	public static final String[] getReplacedFragment(final String[] sourceFragmentArray, 
			final String replacement, final String separator) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(sourceFragmentArray) 
				|| isEmpty(replacement) || isEmpty(separator)) {
			return null;
		}

		String r = replacement;
		List<String> strl = CollectionUtil.generateNewList();
		boolean matchFlag = true;
		boolean skipFlag = false;
		for (int i = 0; i < sourceFragmentArray.length; i++) {
			if (skipFlag) {
				skipFlag = false;
				int index = r.indexOf(sourceFragmentArray[i]);
				if (index >= 0) {
					strl.add(r.substring(0, index));
					r = r.substring(index);
				} else {
					matchFlag = false;
					break;
				}
			}

			if (!sourceFragmentArray[i].equals(separator)) {
				if (r.startsWith(sourceFragmentArray[i])) {
					r = r.substring(sourceFragmentArray[i].length());
				} else {
					matchFlag = false;
					break;
				}
			} else if (i == (sourceFragmentArray.length - 1)) {
				strl.add(r);
			} else {
				skipFlag = true;
				continue;
			}
		}

		return !matchFlag ? null : strl.toArray(new String[0]);
	}

	public static final String[] splitIncludingSeparator(final String source, final String separator) 
			throws LIMException {
		if (isEmpty(source)) {
			return null;
		}
		if (isEmpty(separator)) {
			return new String[] {source};
		}

		List<String> strl = CollectionUtil.generateNewList();
		String s = source;
		while (s.length() > 0) {
			int i = s.indexOf(separator);
			String tmp = (i >= 0) ? s.substring(0, i): s;
			if (!isEmpty(tmp)) {
				strl.add(tmp);
			}
			if (i >= 0) {
				strl.add(separator);
			}
			s = s.substring(tmp.length() + ((i < 0) ? 0 : separator.length()));
		}

		return strl.toArray(new String[0]);
	}


	public static final String[] splitByAnyWhitespace(final String source) {
		if (isEmpty(source)) {
			return null;
		}
		return source.trim().split("\\s+");
	}

}
