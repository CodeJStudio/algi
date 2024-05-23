package com.codejstudio.algi.action.symbol;

import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.algi.action.symbolElement.AbstractSymbolElement;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.ISymbol;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.AbstractSymbol;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractSurroundingSymbol extends AbstractSymbol implements ISurroundingSymbol {

	/* constants */

	private static final long serialVersionUID = -1549887022936504853L;


	/* overridden methods */

	@Override
	public boolean verifySymbol(final String symbolContent) {
		return verifySurroundingSymbol(symbolContent);
	}

	@Override
	protected boolean verifyInnerSymbol(final String innerSymbol) {
		return verifyRightInnerSymbol(innerSymbol) 
				|| verifySurroundingInnerSymbol(verifyAndStripLeftInnerSymbol(innerSymbol));
	}

	protected boolean verifySurroundingInnerSymbol(final String surroundingInnerSymbol) {
		return !StringUtil.isEmpty(surroundingInnerSymbol) 
				&& surroundingInnerSymbol.equalsIgnoreCase(getInnerSymbol());
	}

	protected String verifyAndStripLeftInnerSymbol(final String innerSymbol) {
		return verifyLeftInnerSymbol(innerSymbol) 
				? innerSymbol.substring(0, 
						(innerSymbol.length() - ("" + ISurroundingSymbol.SYMBOL_LEFT).length())) 
				: null;
	}

	protected boolean verifyLeftInnerSymbol(final String innerSymbol) {
		return !StringUtil.isEmpty(innerSymbol) 
				&& innerSymbol.equalsIgnoreCase(getInnerSymbol() + ISurroundingSymbol.SYMBOL_LEFT);
	}

	protected boolean verifyRightInnerSymbol(final String innerSymbol) {
		return !StringUtil.isEmpty(innerSymbol) 
				&& innerSymbol.equals("" + ISurroundingSymbol.SYMBOL_RIGHT);
	}


	@Override
	public boolean verifySurroundingSymbol(final String surroundedContent) {
		return verifyLeftSurroundingSymbol(surroundedContent) 
				&& verifyRightSurroundingSymbol(surroundedContent);
	}

	@Override
	public boolean verifyLeftSurroundingSymbol(final String surroundedContent) {
		return !StringUtil.isEmpty(surroundedContent) 
				&& surroundedContent.toLowerCase().startsWith(surroundLeft());
	}

	@Override
	public boolean verifyRightSurroundingSymbol(final String surroundedContent) {
		return !StringUtil.isEmpty(surroundedContent) && surroundedContent.endsWith(surroundRight());
	}


	@Override
	public String strip(final String surroundedContent) {
		String str = surroundedContent;
		if (StringUtil.isEmpty(str) || (str = verifyAndStripLeftSurroundingSymbol(str)) == null
				|| (str = verifyAndStripRightSurroundingSymbol(str)) == null) {
			return null;
		}
		return str;
	}

	protected String verifyAndStripLeftSurroundingSymbol(final String surroundedContent) {
		String str = "" + ISurroundingSymbol.SYMBOL_LEFT + ISymbol.SYMBOL_RIGHT;
		return !verifyLeftSurroundingSymbol(surroundedContent) 
				? null : surroundedContent.substring(surroundedContent.indexOf(str) + (str).length());
	}

	protected String verifyAndStripRightSurroundingSymbol(final String surroundedContent) {
		return verifyRightSurroundingSymbol(surroundedContent) 
				? surroundedContent.substring(0, (surroundedContent.length() - surroundRight().length())) 
				: null;
	}


	@Override
	public String surround(final String strippedContent) {
		return StringUtil.isEmpty(strippedContent) 
				? null : surroundLeft() + strippedContent + surroundRight();
	}


	protected String surroundLeft() {
		return SymbolUtil.packageSingleSymbol(getInnerSymbol() + ISurroundingSymbol.SYMBOL_LEFT);
	}

	protected String surroundRight() {
		return SymbolUtil.packageSingleSymbol("" + ISurroundingSymbol.SYMBOL_RIGHT);
	}


	@Override
	public ISymbolElement generateSymbolElement(final String source, final SymbolAction symbolAction) {
		return AbstractSymbolElement.newInstance(getSymbolElementClass(), source, symbolAction);
	}


	/* static methods */

	protected static String[] getSurroundingSymbolCombination(final String symbol) {
		return new String[] {
				symbol + ISurroundingSymbol.SYMBOL_LEFT, 
				"" + ISurroundingSymbol.SYMBOL_RIGHT, 
		};
	}

}
