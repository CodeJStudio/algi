package com.codejstudio.lim.pojo.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.IStringable;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.ICloneable;
import com.codejstudio.lim.pojo.i.ISymbol;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class Description implements IStringable, ICloneable, Serializable {

	/* constants */

	private static final long serialVersionUID = 2407797844611260273L;


	/* variables */

	private String encodedText;
	private String decodedText;


	/* variables: arrays, collections, maps, groups */

	private String[] textFragmentArray;
	private Integer[] textIndexArray;
	private Integer[] textFragmentIndexArray;


	/* constructors */

	public Description() {}

	public Description(String text, boolean encodeFlag) throws LIMException {
		setText(text, encodeFlag);
	}

	public Description(String text) throws LIMException {
		setText(text, true);
	}


	/* getters & setters */

	public String getEncodedText() {
		return encodedText;
	}

	public String getDecodedText() {
		return decodedText;
	}


	private void setText(final String text, final boolean encodeFlag) throws LIMException {
		this.encodedText = this.decodedText = text;
		if (encodeFlag) {
			encodeSymbolText(text);
		} else {
			decodedSymbolText(text);
		}
	}

	private void decodedSymbolText(final String text) throws LIMException {
		if (StringUtil.isEmpty(text)) {
			return;
		}

		char[] ca = text.toCharArray();
		StringBuilder sb = new StringBuilder();
		boolean escapeFlag = true;
		for (int i = 0; i < ca.length; i++) {
			if (escapeFlag && ca[i] == ISymbol.SYMBOL_ESCAPE_CHARACTER) {
				if (i == ca.length - 1) {
					return;
				}
				escapeFlag = false;
			} else {
				sb.append(ca[i]);
				escapeFlag = true;
			}
		}

		String str = sb.toString();
		ca = str.toCharArray();
		sb = new StringBuilder();
		boolean leftFlag = false;
		List<String> tfl = CollectionUtil.generateNewList();
		List<Integer> til = CollectionUtil.generateNewList();
		List<Integer> tfil = CollectionUtil.generateNewList();
		til.add(0);
		tfil.add(0);
		for (int i = 0; i < ca.length; i++) {
			if (ca[i] == ISymbol.SYMBOL_LEFT) {
				int index = str.indexOf(ISymbol.SYMBOL_RIGHT, i);
				String s;
				if (index > 0 && SymbolUtil.verifyAnySymbolCombinations(s = str.substring(i, index + 1))) {
					sb.append(s);
					tfl.add(s);
					til.add(til.get(til.size() - 1) + s.length());
					tfil.add(tfil.get(tfil.size() - 1) + 1);
					i += s.length() - 1;
					leftFlag = true;
				}
			}
			if (!leftFlag) {
				sb.append(ca[i]);
				tfl.add("" + ca[i]);
				til.add(til.get(til.size() - 1) + 1);
				tfil.add(tfil.get(tfil.size() - 1) + 1);
			}
			leftFlag = false;
		}
		til.remove(til.size() - 1);
		tfil.remove(tfil.size() - 1);

		this.decodedText = sb.toString();
		this.textFragmentArray = tfl.toArray(new String[0]);
		this.textIndexArray = til.toArray(new Integer[0]);
		this.textFragmentIndexArray = tfil.toArray(new Integer[0]);
	}

	private void encodeSymbolText(final String text) throws LIMException {
		if (StringUtil.isEmpty(text)) {
			return;
		}

		int difference = 0;
		List<int[]> ial = CollectionUtil.generateNewList();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c == ISymbol.SYMBOL_LEFT) {
				++difference;
				ial.add(new int[] {1, difference, i, 0});
			} else if (c == ISymbol.SYMBOL_RIGHT) {
				ial.add(new int[] {-1, difference, i, 0});
				difference--;
			}
		}
		for (int i = 0; i < ial.size(); i++) {
			int[] iali = ial.get(i);
			if (iali != null && iali[0] < 0) {
				for (int j = i - 1; j >= 0; j--) {
					int[] ialj = ial.get(j);
					if (ialj[0] > 0 && ialj[1] <= iali[1] && ialj[3] != -1) {
						if (SymbolUtil.verifyAnySymbolCombinations(text.substring(ialj[2], iali[2] + 1))) {
							iali[3] = -1;
							ialj[3] = -1;
							break;
						}
					} else if (ialj[1] < iali[1] && ialj[3] == -1) {
						break;
					}

					if (j == 0) {
						iali[3] = 1;
						ialj[3] = (ialj[3] != -1) ? 1 : ialj[3];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		List<String> tfl = CollectionUtil.generateNewList();
		List<Integer> til = CollectionUtil.generateNewList();
		List<Integer> tfil = CollectionUtil.generateNewList();
		til.add(0);
		tfil.add(0);
		int start = 0;
		for (int i = 0; i < ial.size(); i++) {
			int[] ia = ial.get(i);
			if (ia[3] != -1) {
				for (int j = start; j < ia[2]; j++) {
					char c = text.charAt(j);
					if (c == ISymbol.SYMBOL_ESCAPE_CHARACTER) {
						appendCharWithEscapeCharacter(sb, c, tfl, til, tfil);
					} else {
						appendCharWithoutEscapeCharacter(sb, c, tfl, til, tfil);
					}
				}
				char c = text.charAt(ia[2]);
				appendCharWithEscapeCharacter(sb, c, tfl, til, tfil);
				start = ia[2] + 1;
			}
		}
		for (; start < text.length(); start++) {
			appendCharWithoutEscapeCharacter(sb, text.charAt(start), tfl, til, tfil);
		}
		til.remove(til.size() - 1);
		tfil.remove(tfil.size() - 1);

		this.encodedText = sb.toString();
		this.textFragmentArray = tfl.toArray(new String[0]);
		this.textIndexArray = til.toArray(new Integer[0]);
		this.textFragmentIndexArray = tfil.toArray(new Integer[0]);
	}

	private void appendCharWithEscapeCharacter(StringBuilder stringBuilder, final char character, 
			List<String> textFragmentList, List<Integer> textIndexList, List<Integer> textFragmentIndexList) {
		stringBuilder.append("" + ISymbol.SYMBOL_ESCAPE_CHARACTER + character);
		textFragmentList.add("" + ISymbol.SYMBOL_ESCAPE_CHARACTER + character);
		textIndexList.add(textIndexList.get(textIndexList.size() - 1) + 2);
		textFragmentIndexList.add(textFragmentIndexList.get(textFragmentIndexList.size() - 1) + 1);
	}

	private void appendCharWithoutEscapeCharacter(StringBuilder stringBuilder, final char character, 
			List<String> textFragmentList, List<Integer> textIndexList, List<Integer> textFragmentIndexList) {
		stringBuilder.append(character);
		textFragmentList.add("" + character);
		textIndexList.add(textIndexList.get(textIndexList.size() - 1) + 1);
		textFragmentIndexList.add(textFragmentIndexList.get(textFragmentIndexList.size() - 1) + 1);
	}


	/* class methods */

	public int length() {
		return this.textFragmentArray.length;
	}

	public int indexOf(final String text, final int fromIndex) {
		int index = this.encodedText.indexOf(text, fromIndex);
		for (int i = ((fromIndex < 0) ? 0 : fromIndex); i < textIndexArray.length; i++) {
			if (((i == textIndexArray.length - 1) ? (index == textIndexArray[i]) 
					: (index >= textIndexArray[i] && index < textIndexArray[i + 1]))) {
				return textFragmentIndexArray[i];
			}
		}
		return -1;
	}


	/* overridden methods */

	@Override
	public String toString() {
		return getEncodedText();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.encodedText);
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Description dsco = (Description) object;
		return Objects.equals(this.encodedText, dsco.encodedText);
	}


	@Override
	public Description cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		Description clonedElement;
		try {
			clonedElement = (Description) this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}

		clonedElement.encodedText = this.encodedText;
		clonedElement.decodedText = this.decodedText;

		if (this.textFragmentArray != null) {
			clonedElement.textFragmentArray = new String[this.textFragmentArray.length];
			for (int i = 0; i < this.textFragmentArray.length; i++) {
				clonedElement.textFragmentArray[i] = this.textFragmentArray[i];
			}
		}
		if (this.textIndexArray != null) {
			clonedElement.textIndexArray = new Integer[this.textIndexArray.length];
			for (int i = 0; i < this.textIndexArray.length; i++) {
				clonedElement.textIndexArray[i] = this.textIndexArray[i];
			}
		}
		if (this.textFragmentIndexArray != null) {
			clonedElement.textFragmentIndexArray = new Integer[this.textFragmentIndexArray.length];
			for (int i = 0; i < this.textFragmentIndexArray.length; i++) {
				clonedElement.textFragmentIndexArray[i] = this.textFragmentIndexArray[i];
			}
		}

		return clonedElement;
	}


	/* static methods */

	public static Description merge(final Description... descriptionObjects) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(descriptionObjects)) {
			return null;
		}

		List<Description> dscol = CollectionUtil.removeNull(descriptionObjects);
		if (dscol.size() == 1) {
			return dscol.get(0);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dscol.size(); i++) {
			sb.append(dscol.get(i).decodedText);
		}
		return new Description(sb.toString());
	}

}
