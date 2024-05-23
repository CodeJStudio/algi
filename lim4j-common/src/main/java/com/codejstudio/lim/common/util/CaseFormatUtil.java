package com.codejstudio.lim.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CaseFormatUtil.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public final class CaseFormatUtil {

	/* enumeration */

	public enum WordSeparator{
		HYPHEN('-'),
		UNDERSCORE('_'),
		EQUAL_MARK('='),
		COMMA(','),
		SEMICOLON(';'),
		;


		/* variables */

		private char character;


		/* constructors */

		WordSeparator(char character) {
			this.character = character;
		}


		/* getters & setters */

		public char getCharacter() {
			return character;
		}

	}


	/* static methods */

	/**
	* "lowerCamel", "UpperCamel" -> "lower_underscore", "lower-hyphen", "UPPER_UNDERSCORE", "UPPER-HYPHEN"
	*/
	public static final String camelToSeparated(final String source, final WordSeparator separator, 
			final boolean upperOutputFlag) {
		Pattern p = Pattern.compile("[A-Z0-9]");
		Matcher m = p.matcher("" + Character.toLowerCase(source.charAt(0)) + source.substring(1));
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, separator.character + m.group(0));
		}
		m.appendTail(sb);
		return upperOutputFlag ? sb.toString().toUpperCase() : sb.toString().toLowerCase();
	}


	/**
	* "lower_underscore", "lower-hyphen", "UPPER_UNDERSCORE", "UPPER-HYPHEN" -> "lowerCamel", "UpperCamel"
	*/
	public static final String separatedToCamel(final String source, final WordSeparator separator, 
			final boolean upperOutputFlag) {
		Pattern p = Pattern.compile("" + separator.character + "(\\w)");
		Matcher m = p.matcher(source.toLowerCase());
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		m.appendTail(sb);
		return upperOutputFlag ? ("" + Character.toUpperCase(sb.charAt(0)) + sb.substring(1)) : sb.toString();
	}

}
