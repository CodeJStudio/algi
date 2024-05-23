package com.codejstudio.lim.common.util;

import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class LogUtil {

	/* constants */

	public static final char HYPHEN = WordSeparator.HYPHEN.getCharacter();
	public static final char UNDERSCORE = WordSeparator.UNDERSCORE.getCharacter();
	public static final char EQUAL_MARK = WordSeparator.EQUAL_MARK.getCharacter();

	public static final char DEFAULT_MARK = EQUAL_MARK;
	public static final byte DEFAULT_PRE_MARK_COUNT = 10;
	public static final byte DEFAULT_SUF_MARK_COUNT = 10;


	/* static methods */

	public static final String wrap(final String logMessage) {
		return wrap(logMessage, DEFAULT_MARK, DEFAULT_PRE_MARK_COUNT, DEFAULT_SUF_MARK_COUNT);
	}

	public static final String wrap(final String logMessage, final Character markType, 
			final byte preMarkCount, final byte sufMarkCount) {
		if (StringUtil.isEmpty(logMessage)) {
			return logMessage;
		}

		char mark = (markType != null) ? markType : DEFAULT_MARK;

		StringBuilder preMark = null;
		if (preMarkCount > 0) {
			preMark = new StringBuilder();
			for (int i = 0; i < preMarkCount; i++) {
				preMark.append(mark);
			}
		}

		StringBuilder sufMark = null;
		if (sufMarkCount > 0) {
			sufMark = new StringBuilder();
			for (int i = 0; i < sufMarkCount; i++) {
				sufMark.append(mark);
			}
		}

		return ((preMark != null) ? (preMark.toString() + " ") : "")
			+ logMessage
			+ ((sufMark != null) ? (" " + sufMark.toString()) : "");
	}

}
