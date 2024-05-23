package com.codejstudio.algi.action.symbol;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.collection.MultiValueMap;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.util.AbstractSymbol;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractComplexSurroundingSymbol extends AbstractSymbol 
		implements IComplexSurroundingSymbol {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(AbstractComplexSurroundingSymbol.class);

	private static final long serialVersionUID = 3516692238400489965L;

	public static final String CLASSIFICATION_LEFT = "left";
	public static final String CLASSIFICATION_RIGHT = "right";
	public static final String CLASSIFICATION_MID = "mid";

	public static final String[] SINGLE_COMPLEX_SYMBOL_ARRAY = new String[] {
			ConditionSymbol.SYMBOL, //0 cd
			DoOnceSymbol.SYMBOL, //1 do
			DoRepeatedlySymbol.SYMBOL, //2 dr
			ParameterOnceSymbol.SYMBOL, //3 po
			ParameterRepeatedSymbol.SYMBOL, //4 pr
			"" + ISurroundingSymbol.SYMBOL_LEFT, //5 (
			"" + ISurroundingSymbol.SYMBOL_RIGHT, //6 )
			"" + IComplexSurroundingSymbol.SYMBOL_LEFT, //7 {
			"" + IComplexSurroundingSymbol.SYMBOL_RIGHT, //8 }
	};

	public static final byte[][] ARRAY_OF_COMPLEX_SYMBOL_BYTE_ARRAYS = new byte[][] {
			new byte[] {0, 7},    //0 cd{
			new byte[] {8, 0, 7}, //1 }cd{
			new byte[] {8, 1, 5}, //2 }do(
			new byte[] {2, 5},    //3 dr(
			new byte[] {8, 2, 5}, //4 }dr(
			new byte[] {3, 7},    //5 po{
			new byte[] {8, 4, 7}, //6 }pr{
			new byte[] {6, 7},    //7 ){
			new byte[] {8},       //8 }
	};

	public static final List<String> COMPLEX_SYMBOL_LIST;
	private static final MultiValueMap<String, String> COMPLEX_SYMBOL_MVMAP;


	/* initializers */

	static {
		try {
			COMPLEX_SYMBOL_LIST = CollectionUtil.generateNewList();
			COMPLEX_SYMBOL_MVMAP = CollectionUtil.generateNewMultiValueMap();
			for (byte[] bytes : ARRAY_OF_COMPLEX_SYMBOL_BYTE_ARRAYS) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytes.length; i++) {
					sb.append(SINGLE_COMPLEX_SYMBOL_ARRAY[bytes[i]]);
				}
				String str = sb.toString();
				COMPLEX_SYMBOL_LIST.add(str);

				if (str.contains(ConditionSymbol.SYMBOL)) {
					COMPLEX_SYMBOL_MVMAP.put(ConditionSymbol.class.getSimpleName(), str);
				} else if (str.contains(DoOnceSymbol.SYMBOL)) {
					COMPLEX_SYMBOL_MVMAP.put(DoOnceSymbol.class.getSimpleName(), str);
				} else if (str.contains(DoRepeatedlySymbol.SYMBOL)) {
					COMPLEX_SYMBOL_MVMAP.put(DoRepeatedlySymbol.class.getSimpleName(), str);
				} else if (str.contains(ParameterOnceSymbol.SYMBOL)) {
					COMPLEX_SYMBOL_MVMAP.put(ParameterOnceSymbol.class.getSimpleName(), str);
				} else if (str.contains(ParameterRepeatedSymbol.SYMBOL)) {
					COMPLEX_SYMBOL_MVMAP.put(ParameterRepeatedSymbol.class.getSimpleName(), str);
				} else {
					COMPLEX_SYMBOL_MVMAP.put(AbstractComplexSurroundingSymbol.class.getSimpleName(), str);
				}

				if (!str.startsWith("" + ISurroundingSymbol.SYMBOL_RIGHT) 
						&& !str.startsWith("" + IComplexSurroundingSymbol.SYMBOL_RIGHT)) {
					COMPLEX_SYMBOL_MVMAP.put(CLASSIFICATION_LEFT, str);
				} else if (str.endsWith("" + ISurroundingSymbol.SYMBOL_RIGHT) 
						|| str.endsWith("" + IComplexSurroundingSymbol.SYMBOL_RIGHT)) {
					COMPLEX_SYMBOL_MVMAP.put(CLASSIFICATION_RIGHT, str);
				} else {
					COMPLEX_SYMBOL_MVMAP.put(CLASSIFICATION_MID, str);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/* overridden methods */

	@Override
	public boolean verifySymbol(final String symbolContent) {
		return verifySurroundingSymbol(symbolContent);
	}

	@Override
	protected boolean verifyInnerSymbol(final String innerSymbol) {
		return innerSymbol != null 
				&& (COMPLEX_SYMBOL_MVMAP.get(this.getClass().getSimpleName())
						.contains(innerSymbol.toLowerCase()) 
						|| COMPLEX_SYMBOL_MVMAP.get(AbstractComplexSurroundingSymbol.class.getSimpleName())
								.contains(innerSymbol.toLowerCase()));
	}


	@Override
	public boolean verifySurroundingSymbol(final String surroundedContent) {
		throw new UnsupportedOperationException();
	}


	@Override
	public boolean verifyLeftSurroundingSymbol(final String surroundedContent) {
		if (StringUtil.isEmpty(surroundedContent)) {
			return false;
		}

		boolean flag = false;
		for (String symbol : COMPLEX_SYMBOL_MVMAP.get(CLASSIFICATION_LEFT).toCollection()) {
			flag |= surroundedContent.toLowerCase().startsWith(SymbolUtil.packageSingleSymbol(symbol));
		}
		return flag;
	}

	@Override
	public boolean verifyRightSurroundingSymbol(final String surroundedContent) {
		if (StringUtil.isEmpty(surroundedContent)) {
			return false;
		}

		boolean flag = false;
		for (String symbol : COMPLEX_SYMBOL_MVMAP.get(CLASSIFICATION_RIGHT).toCollection()) {
			flag |= surroundedContent.toLowerCase().endsWith(SymbolUtil.packageSingleSymbol(symbol));
		}
		return flag;
	}

	@Override
	public boolean verifyMidSurroundingSymbol(final String surroundedContent) {
		if (StringUtil.isEmpty(surroundedContent)) {
			return false;
		}

		boolean flag = false;
		for (String symbol : COMPLEX_SYMBOL_MVMAP.get(CLASSIFICATION_MID).toCollection()) {
			flag |= surroundedContent.toLowerCase().contains(SymbolUtil.packageSingleSymbol(symbol));
		}
		return flag;
	}


	protected boolean verifyLeftInnerSymbol(final String innerSymbol) {
		return innerSymbol != null 
				&& COMPLEX_SYMBOL_MVMAP.get(CLASSIFICATION_LEFT).contains(innerSymbol.toLowerCase());
	}

	protected boolean verifyRightInnerSymbol(final String innerSymbol) {
		return innerSymbol != null 
				&& COMPLEX_SYMBOL_MVMAP.get(CLASSIFICATION_RIGHT).contains(innerSymbol.toLowerCase());
	}

	protected boolean verifyMidInnerSymbol(final String innerSymbol) {
		return innerSymbol != null 
				&& COMPLEX_SYMBOL_MVMAP.get(CLASSIFICATION_MID).contains(innerSymbol.toLowerCase());
	}

}
