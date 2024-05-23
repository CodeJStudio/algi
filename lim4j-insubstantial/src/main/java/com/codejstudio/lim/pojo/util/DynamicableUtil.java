package com.codejstudio.lim.pojo.util;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.condition.DynamicCondition;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.i.ISession;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class DynamicableUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DynamicableUtil.class);


	public static final int START_DYNAMIC_SERIAL = 1;

	public static final String NOT_SAME = "NOT_SAME";
	public static final String MAYBE_SAME = "MAYBE_SAME";

	public static final DynamicCondition CONDITION_NOT_SAME;
	public static final DynamicCondition CONDITION_MAYBE_SAME;

	public static final InputDynamicSymbol INPUT_DYNAMIC_SYMBOL = InputDynamicSymbol.getSymbolInstance();
	public static final OutputDynamicSymbol OUTPUT_DYNAMIC_SYMBOL = OutputDynamicSymbol.getSymbolInstance();


	/* initializers */

	static {
		try {
			CONDITION_NOT_SAME = new DynamicCondition(NOT_SAME);
			CONDITION_MAYBE_SAME = new DynamicCondition(MAYBE_SAME);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public synchronized static final String generateDynamicSerial() {
		return "" + START_DYNAMIC_SERIAL;
	}

	public synchronized static final String generateDynamicSerial(final String lastSerial) {
		Integer i = StringUtil.integerValue(lastSerial);
		return (i == null) ? null : ("" + (i + 1));
	}

	public synchronized static final boolean verifyDynamicSerial(final String serial) {
		return ObjectUtil.isNumeric(serial);
	}



	public static final DynamicParameterType[] generateDynamicParameterTypeFromContent(
			final String content) throws LIMException {
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(content, 
				INPUT_DYNAMIC_SYMBOL, OUTPUT_DYNAMIC_SYMBOL);
		return (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) 
						|| arrayOfFragmentArrays.length != 3) 
				? null : generateDynamicParameterTypeFromContent(arrayOfFragmentArrays[2]);
	}

	public static final DynamicParameterType[] generateDynamicParameterTypeFromContent(
			final String[] dynamicFragmentArray) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(dynamicFragmentArray)) {
			return null;
		}

		List<DynamicParameterType> dptl = CollectionUtil.generateNewList();
		for (String dynamicFragment : dynamicFragmentArray) {
			if (INPUT_DYNAMIC_SYMBOL.verifySymbol(dynamicFragment)) {
				dptl.add(DynamicParameterType.INPUT);
			} else if (OUTPUT_DYNAMIC_SYMBOL.verifySymbol(dynamicFragment)) {
				dptl.add(DynamicParameterType.OUTPUT);
			}
		}
		return CollectionUtil.checkNullOrEmpty(dptl) ? null : dptl.toArray(new DynamicParameterType[0]);
	}

	public static final InformationElement substituteDynamicSymbolByParameterType(
			final InformationElement dynamicElement, final DynamicParameterType[] typeArray) 
					throws LIMException {
		Boolean dn;
		DynamicInformation di;
		if (dynamicElement == null || (dn = dynamicElement.getDynamic()) == null || !dn 
				|| (di = dynamicElement.getDynamicInformation()) == null) {
			return null;
		}

		DynamicParameterType[] dpta;
		if (CollectionUtil.arrayEquals((dpta = di.getDynamicFragmentParameterTypeArray()), typeArray)) {
			return dynamicElement;
		}

		String[] afa = di.getAllFragmentArray();
		int[] dfia = di.getDynamicFragmentIndexArray();
		int cursor = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dfia.length; i++) {
			if (dfia[i] > afa.length) {
				break;
			}
			if (dfia[i] > cursor) {
				sb.append(String.join("", Arrays.copyOfRange(afa, cursor, dfia[i])));
			}
			if (dpta[i].equals(typeArray[i])) {
				sb.append(afa[dfia[i]]);
			} else if (DynamicParameterType.INPUT.equals(dpta[i]) 
					&& INPUT_DYNAMIC_SYMBOL.verifySymbol(afa[dfia[i]])) {
				String str = INPUT_DYNAMIC_SYMBOL.stripDynamicSymbol(afa[dfia[i]]);
				str = OUTPUT_DYNAMIC_SYMBOL.packageDynamicSymbol(str);
				sb.append(str);
			} else if (DynamicParameterType.OUTPUT.equals(dpta[i]) 
					&& OUTPUT_DYNAMIC_SYMBOL.verifySymbol(afa[dfia[i]])) {
				String str = OUTPUT_DYNAMIC_SYMBOL.stripDynamicSymbol(afa[dfia[i]]);
				str = INPUT_DYNAMIC_SYMBOL.packageDynamicSymbol(str);
				sb.append(str);
			}
			cursor = dfia[i] + 1;
		}
		if (cursor < afa.length) {
			sb.append(String.join("", Arrays.copyOfRange(afa, cursor, afa.length)));
		}

		InformationElement ie = (InformationElement) BaseElement.newInstance(
				dynamicElement.getClass(), true);
		ie = dynamicElement.cloneToElement(ie);
		ie.setDescription(sb.toString());
		return ie;
	}


	public static final String substituteInputForOutput(final String content) throws LIMException {
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(content, 
				INPUT_DYNAMIC_SYMBOL, OUTPUT_DYNAMIC_SYMBOL);
		if (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) || arrayOfFragmentArrays.length < 1 
				|| arrayOfFragmentArrays[0].length < 1) {
			return null;
		}
		if (arrayOfFragmentArrays[0].length > 1) {
			return substituteInputForOutput(arrayOfFragmentArrays[0]);
		}
		return OUTPUT_DYNAMIC_SYMBOL.verifySymbol(arrayOfFragmentArrays[0][0]) 
				? SymbolUtil.packageSingleSymbol(INPUT_DYNAMIC_SYMBOL.getInnerSymbol() 
						+ SymbolUtil.verifyAndStripSingleOuterSymbol(arrayOfFragmentArrays[0][0])
						.substring(OUTPUT_DYNAMIC_SYMBOL.getInnerSymbol().length())) 
				: arrayOfFragmentArrays[0][0];
	}

	public static final String substituteInputForOutput(final String[] contentArray) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(contentArray)) {
			return null;
		}
		if (contentArray.length == 1) {
			return substituteInputForOutput(contentArray[0]);
		}

		StringBuilder sb = new StringBuilder();
		for (String contentFragment : contentArray) {
			sb.append(OUTPUT_DYNAMIC_SYMBOL.verifySymbol(contentFragment) 
					? substituteInputForOutput(contentFragment) : contentFragment);
		}
		return sb.toString();
	}


	public static final String[] substitutePlaceholderForDynamicContent(final String[] contentArray) {
		if (CollectionUtil.checkNullOrEmpty(contentArray)) {
			return null;
		}

		String[] stra = new String[contentArray.length];
		for (int i = 0; i < contentArray.length; i++) {
			stra[i] = (INPUT_DYNAMIC_SYMBOL.verifySingleSymbol(contentArray[i]) 
							|| OUTPUT_DYNAMIC_SYMBOL.verifySingleSymbol(contentArray[i])) 
					? AbstractDynamicSymbol.DYNAMIC_PLACEHOLDER : contentArray[i];
		}
		return stra;
	}

	public static final String substitutePlaceholderForDynamicContent(final String content) 
			throws LIMException {
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(content, 
				INPUT_DYNAMIC_SYMBOL, OUTPUT_DYNAMIC_SYMBOL);
		return (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) 
						|| arrayOfFragmentArrays.length != 3) 
				? null : String.join("", substitutePlaceholderForDynamicContent(arrayOfFragmentArrays[0]));
	}


	public static final String substituteStaticContentForDynamic(
			final DynamicInformation dynamicInformation, final ISession session) {
		String[] afa;
		if (session == null || dynamicInformation == null || !dynamicInformation.isDynamic() 
				|| CollectionUtil.checkNullOrEmpty(afa = dynamicInformation.getAllFragmentArray())) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		Object obj;
		for (int i = 0; i < afa.length; i++) {
			String str = afa[i];
			AbstractDynamicSymbol adsb = INPUT_DYNAMIC_SYMBOL.verifySingleSymbol(str) ? INPUT_DYNAMIC_SYMBOL 
					: (OUTPUT_DYNAMIC_SYMBOL.verifySingleSymbol(str) ? OUTPUT_DYNAMIC_SYMBOL : null);
			if (adsb != null) {
				String strippedContent = adsb.stripDynamicSymbol(str);
				DynamicInformation di;
				String s;
				if (!StringUtil.isEmpty(strippedContent) 
						&& (di = DynamicInformation.newInstance(strippedContent)) != null && di.isDynamic() 
						&& (s = substituteStaticContentForDynamic(di, session)) != null) {
					str = adsb.packageDynamicSymbol(s);
				}
			}
			sb.append((INPUT_DYNAMIC_SYMBOL.verifySingleSymbol(str) 
					&& StringUtil.isStringable(obj = session.getDynamicElement(
							INPUT_DYNAMIC_SYMBOL.stripDynamicSymbol(str)))) ? obj : str);
		}
		return sb.toString();
	}


	public static final String[][] analyzeStaticContentBasedOnDynamicContent(final String staticContent, 
			final DynamicInformation dynamicInformation) throws LIMException {
		if (dynamicInformation == null || !dynamicInformation.verifyStaticFragments(staticContent) 
				|| CollectionUtil.checkNullOrEmpty(dynamicInformation.getDynamicFragmentArray())) {
			return null;
		}

		int index = 0;
		int lastIndex = 0;
		String[] sfa = dynamicInformation.getStaticFragmentArray();
		List<String> allFragmentList = CollectionUtil.generateNewList();
		List<String> nonSymbolFragmentList = CollectionUtil.generateNewList();
		List<String> symbolFragmentList = CollectionUtil.generateNewList();
		for (int i = 0; i < sfa.length; i++) {
			index = staticContent.indexOf(sfa[i], index);
			if (index < 0) {
				return null;
			}
			if (index > lastIndex) {
				String symbolFragment = staticContent.substring(lastIndex, index);
				allFragmentList.add(symbolFragment);
				symbolFragmentList.add(symbolFragment);
			}
			String nonSymbolFragment = sfa[i];
			allFragmentList.add(nonSymbolFragment);
			nonSymbolFragmentList.add(nonSymbolFragment);

			lastIndex = index += sfa[i].length();
		}

		if (lastIndex < staticContent.length()) {
			String symbolFragment = staticContent.substring(lastIndex);
			allFragmentList.add(symbolFragment);
			symbolFragmentList.add(symbolFragment);
		}

		String[][] str2da = new String[3][];
		str2da[0] = allFragmentList.toArray(new String[0]);
		str2da[1] = nonSymbolFragmentList.toArray(new String[0]);
		str2da[2] = symbolFragmentList.toArray(new String[0]);
		return str2da;
	}

}
