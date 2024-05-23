package com.codejstudio.lim.pojo.util;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.ISymbol;
import com.codejstudio.lim.pojo.i.ISymbolCombination;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class SymbolUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(SymbolUtil.class);


	/* variables: arrays, collections, maps, groups */

	private static final Collection<ISymbolCombination> ALL_SYMBOL_COMBINATION_COLLECTION;


	/* initializers */

	static {
		try {
			ALL_SYMBOL_COMBINATION_COLLECTION = CollectionUtil.generateNewCollection();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public static final void registerSymbolCombinationForInit(final ISymbolCombination symbolCombination) {
		if (!ALL_SYMBOL_COMBINATION_COLLECTION.contains(symbolCombination)) {
			ALL_SYMBOL_COMBINATION_COLLECTION.add(symbolCombination);
		}
	}


	public static final String packageSingleSymbol(final String innerSymbol) {
		return "" + ISymbol.SYMBOL_LEFT + innerSymbol + ISymbol.SYMBOL_RIGHT;
	}


	public static final String[] verifyAndStripSingleOuterSymbol(final String[] symbolContentArray) {
		if (CollectionUtil.checkNullOrEmpty(symbolContentArray)) {
			return null;
		}

		String[] stra = new String[symbolContentArray.length];
		for (int i = 0; i < symbolContentArray.length; i++) {
			stra[i] = verifyAndStripSingleOuterSymbol(symbolContentArray[i]);
		}
		return stra;
	}

	public static final String verifyAndStripSingleOuterSymbol(final String symbolContent) {
		return verifyAndStripSingleOuterSymbol(symbolContent, ISymbol.SYMBOL_LEFT, ISymbol.SYMBOL_RIGHT);
	}

	public static final String verifyAndStripSingleOuterSymbol(final String symbolContent, 
			final char leftSymbol, final char rightSymbol) {
		return (symbolContent == null || !symbolContent.startsWith("" + leftSymbol) 
						|| !symbolContent.endsWith("" + rightSymbol)) 
				? null : symbolContent.substring(("" + leftSymbol).length(), 
						(symbolContent.length() - ("" + rightSymbol).length()));
	}


	public static final boolean verifyAnySymbolCombinations(final String surroundedSymbol) 
			throws LIMException {
		return verifyAndGetSymbolCombinations(surroundedSymbol) != null;
	}

	public static final boolean verifyAnySymbolCombinations(final String surroundedSymbol, final int index) 
			throws LIMException {
		return verifyAndGetSymbolCombinations(surroundedSymbol, index) != null;
	}


	public static final ISymbolCombination[] verifyAndGetSymbolCombinations(final String surroundedSymbol) 
			throws LIMException {
		return verifyAndGetSymbolCombinations(surroundedSymbol, -1);
	}

	public static final ISymbolCombination[] verifyAndGetSymbolCombinations(final String surroundedSymbol, 
			final int index) throws LIMException {
		if (surroundedSymbol == null || CollectionUtil.checkNullOrEmpty(ALL_SYMBOL_COMBINATION_COLLECTION)) {
			return null;
		}

		Collection<ISymbolCombination> scc = CollectionUtil.generateNewCollection();
		for (ISymbolCombination sc : ALL_SYMBOL_COMBINATION_COLLECTION) {
			if ((index < 0) ? sc.verifySingleSymbol(surroundedSymbol) 
					: sc.verifySingleSymbol(surroundedSymbol, index)) {
				scc.add(sc);
			}
		}
		return CollectionUtil.checkNullOrEmpty(scc) ? null : scc.toArray(new ISymbolCombination[0]);
	}



	public static final boolean verifySingleSymbol(final String content, final ISymbol... symbols) {
		if (StringUtil.isEmpty(content) || CollectionUtil.checkNullOrEmpty(symbols)) {
			return false;
		}

		for (ISymbol sb : symbols) {
			if (sb != null && sb.verifySingleSymbol(content)) {
				return true;
			}
		}
		return false;
	}


	public static final String[][] analyzeSymbolContent(final String content, final ISymbol... symbols) 
			throws LIMException {
		if (StringUtil.isEmpty(content)) {
			return null;
		}

		List<String> allFragmentList = CollectionUtil.generateNewList();
		List<String> nonSymbolFragmentList = CollectionUtil.generateNewList();
		List<String> symbolFragmentList = CollectionUtil.generateNewList();
		int symbolLevel = 0;
		int[] ia = new int[content.length()];
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			int j = - 1;
			if (i > 0 && (c == ISymbol.SYMBOL_LEFT || c == ISymbol.SYMBOL_RIGHT)) {
				j = i - 1;
				while (j >= 0 && content.charAt(j) == ISymbol.SYMBOL_ESCAPE_CHARACTER) {
					j--;
				}
				ia[i] = ((i - j) % 2 == 1) 
						? ((c == ISymbol.SYMBOL_LEFT) ? ++symbolLevel 
								: ((c == ISymbol.SYMBOL_RIGHT) ? --symbolLevel : ia[i])) 
						: symbolLevel;
			} else {
				ia[i] = (c == ISymbol.SYMBOL_LEFT) ? ++symbolLevel 
						: ((c == ISymbol.SYMBOL_RIGHT) ? --symbolLevel : symbolLevel);
			}

			if (j < 0 || ((i - j) % 2 == 1)) {
				if (c == ISymbol.SYMBOL_LEFT) {
					symbolLevel++;
				} else if (c == ISymbol.SYMBOL_RIGHT) {
					symbolLevel--;
				}
			}
		}

		int first = 0;
		boolean levelOneFlag = false;
		StringBuilder nsfsb = new StringBuilder();
		StringBuilder sfsb = new StringBuilder();
		for (int i = 0; i < ia.length; i++) {
			if (ia[i] == 1) {
				if (levelOneFlag) {
					sfsb.append(content.substring(first, i + 1));
					String symbolFragment = sfsb.toString();
					if (!CollectionUtil.checkNullOrEmpty(symbols) 
							? verifySingleSymbol(sfsb.toString(), symbols) 
							: verifyAnySymbolCombinations(sfsb.toString())) {
						if (nsfsb.length() > 0) {
							String nonSymbolFragment = nsfsb.toString();
							allFragmentList.add(nonSymbolFragment);
							nonSymbolFragmentList.add(nonSymbolFragment);
							nsfsb.delete(0, nsfsb.length());
						}
						allFragmentList.add(symbolFragment);
						symbolFragmentList.add(symbolFragment);
						sfsb.delete(0, sfsb.length());
					} else {
						nsfsb.append(sfsb);
						sfsb.delete(0, sfsb.length());
					}
				} else {
					first = i;
				}
				levelOneFlag = !levelOneFlag;
			} else if (!levelOneFlag) {
				nsfsb.append(content.charAt(i));
			}
		}

		if (nsfsb.length() > 0) {
			String nonSymbolFragment = nsfsb.toString();
			allFragmentList.add(nonSymbolFragment);
			nonSymbolFragmentList.add(nonSymbolFragment);
		}

		String[][] str2da = new String[3][];
		str2da[0] = allFragmentList.toArray(new String[0]);
		str2da[1] = nonSymbolFragmentList.toArray(new String[0]);
		str2da[2] = symbolFragmentList.toArray(new String[0]);
		return str2da;
	}

}
