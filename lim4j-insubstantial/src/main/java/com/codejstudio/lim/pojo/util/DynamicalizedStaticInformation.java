package com.codejstudio.lim.pojo.util;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.InformationElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class DynamicalizedStaticInformation {

	/* variables */

	private DynamicInformation relatedDynamicInformation;


	/* variables: arrays, collections, maps, groups */

	private String[] dynamicalizedFragmentArray;

	private int[] dynamicalizedFragmentIndexArray;

	private Map<String, String> dynamicalizedStaticFragmentMapping;


	/* constructors */

	private DynamicalizedStaticInformation(String description, DynamicInformation dynamicInformation) 
			throws LIMException {
		this.relatedDynamicInformation = dynamicInformation;
		if (description == null || dynamicInformation == null) {
			throw new LIMException(new IllegalArgumentException());
		}

		init(description, dynamicInformation);
	}


	/* initializers */

	private void init(final String description, final DynamicInformation dynamicInformation) 
			throws LIMException {
		String[] diafa = dynamicInformation.getAllFragmentArray();
		String[] disfa = dynamicInformation.getStaticFragmentArray();
		String[] dnfa = new String[diafa.length];
		int[] dnfia = new int[diafa.length];
		Map<String, String> dnsfm = CollectionUtil.generateNewMap();
		String dsc = description;
		int j = 0;
		for (int i = 0; i < diafa.length; i++) {
			if (j >= disfa.length) {
				if (i >= 1 && dnfa[i - 1] == null) {
					throw new LIMException(new IllegalArgumentException());
				}
				dnfia[i] = dnfia[i - 1] + diafa[i - 1].length();
				dnfa[i] = dsc;
				dnsfm.put(diafa[i], dnfa[i]);
			} else 
			if (disfa[j].equals(diafa[i])) {
				int index = dsc.indexOf(diafa[i]);
				if (index < 0) {
					throw new LIMException(new IllegalArgumentException());
				} else {
					if (index > 0 && i > 0) {
						if (i >= 2 && dnfa[i - 2] == null) {
							throw new LIMException(new IllegalArgumentException());
						}
						dnfia[i - 1] = (i == 1) ? 0 : dnfia[i - 2] + diafa[i - 2].length();
						dnfa[i - 1] = dsc.substring(0, index);
						dnsfm.put(diafa[i - 1], dnfa[i - 1]);
					}
					if (i >= 1 && dnfa[i - 1] == null) {
						throw new LIMException(new IllegalArgumentException());
					}
					dnfia[i] = (j == 0) ? index : (index + dnfia[i - 1]);
					dnfa[i] = dsc.substring(index, index + diafa[i].length());
					dsc = dsc.substring(index + diafa[i].length());
				}
				j++;
			}
		}

		this.dynamicalizedFragmentArray = dnfa;
		this.dynamicalizedFragmentIndexArray = dnfia;
		this.dynamicalizedStaticFragmentMapping = dnsfm;
	}

	public static final DynamicalizedStaticInformation newInstance(final InformationElement element, 
			final DynamicInformation dynamicInformation) throws LIMException {
		return (element == null) ? null : newInstance(element.getDescription(), dynamicInformation);
	}

	public static final DynamicalizedStaticInformation newInstance(final String description, 
			final DynamicInformation dynamicInformation) {
		try {
			return new DynamicalizedStaticInformation(description, dynamicInformation);
		} catch (LIMException e) {
			return null;
		}
	}


	/* getters & setters */

	public DynamicInformation getRelatedDynamicInformation() {
		return relatedDynamicInformation;
	}

	public String[] getDynamicalizedFragmentArray() {
		return dynamicalizedFragmentArray;
	}

	public int[] getDynamicalizedFragmentIndexArray() {
		return dynamicalizedFragmentIndexArray;
	}

	public Map<String, String> getDynamicalizedStaticFragmentMapping() {
		return dynamicalizedStaticFragmentMapping;
	}

}
