package com.codejstudio.lim.common.util;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class CharacterUtil {

	/* static methods */

	public static final boolean containChinese(final String source) {
		if (StringUtil.trim(source) == null) {
			return false;
		}
		for (char c : StringUtil.trim(source).toCharArray()) {
			if (checkIsChineseCharacterByBlock(c) || checkIsChinesePunctuationByBlock(c)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean isChinese(final String source) {
		if (StringUtil.trim(source) == null) {
			return false;
		}
		for (char c : StringUtil.trim(source).toCharArray()) {
			if (!checkIsChineseCharacterByBlock(c) && !checkIsChinesePunctuationByBlock(c)) {
				return false;
			}
		}
		return true;
	}

	public static final boolean checkContainChineseCharacterByBlock(final String source) {
		if (StringUtil.trim(source) == null) {
			return false;
		}
		for (char c : StringUtil.trim(source).toCharArray()) {
			if (checkIsChineseCharacterByBlock(c)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean checkIsChineseCharacterByBlock(final String source) {
		if (StringUtil.trim(source) == null) {
			return false;
		}
		for (char c : StringUtil.trim(source).toCharArray()) {
			if (!checkIsChineseCharacterByBlock(c)) {
				return false;
			}
		}
		return true;
	}

	public static final boolean checkContainChinesePunctuationByBlock(final String source) {
		if (StringUtil.trim(source) == null) {
			return false;
		}
		for (char c : StringUtil.trim(source).toCharArray()) {
			if (checkIsChinesePunctuationByBlock(c)) {
				return true;
			}
		}
		return false;
	}

	public static final boolean checkIsChinesePunctuationByBlock(final String source) {
		if (StringUtil.trim(source) == null) {
			return false;
		}
		for (char c : StringUtil.trim(source).toCharArray()) {
			if (!checkIsChinesePunctuationByBlock(c)) {
				return false;
			}
		}
		return true;
	}


	public static final boolean checkIsChineseCharacterByBlock(final char character) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(character);
		return ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS 
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS 
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B 
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D;
	}

	public static final boolean checkIsChinesePunctuationByBlock(final char character) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(character);
		return ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS 
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION 
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS 
				|| ub == Character.UnicodeBlock.VERTICAL_FORMS;
	}

}