package com.codejstudio.lim.pojo.util;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class PartDynamicalizedStaticInformation {

	/* constants */

	public static final String INITIALISM = "PDSI";


	/* variables */

	private final InformationElement dynamicElement;
	private final InformationElement staticElement;

	private final String partDynamicalizedDescription;


	/* variables: arrays, collections, maps, groups */

	private final DynamicParameterType[] partDynamicParameterTypeArray;

	private String[] dynamicalizedFragmentArray;

	private int[] dynamicalizedFragmentIndexArray;

	private Map<String, String> dynamicalizedStaticFragmentMapping;


	/* constructors */

	private PartDynamicalizedStaticInformation(final InformationElement dynamicElement, 
			final InformationElement staticElement, final String partDynamicalizedDescription, 
			final DynamicParameterType[] partDynamicParameterTypeArray) throws LIMException {
		String sdsc;
		DynamicInformation di;
		if (dynamicElement == null || (di = dynamicElement.getDynamicInformation()) == null 
				|| staticElement == null || (sdsc = staticElement.getDescription()) == null) {
			throw new LIMException(new IllegalArgumentException());
		}
		this.dynamicElement = dynamicElement;
		this.staticElement = staticElement;
		this.partDynamicalizedDescription = partDynamicalizedDescription;
		this.partDynamicParameterTypeArray = partDynamicParameterTypeArray;

		init(di, sdsc);
	}


	/* initializers */

	private void init(final DynamicInformation dynamicInformation, final String staticDescription) 
			throws LIMException {
		String[] diafa = dynamicInformation.getAllFragmentArray();
		String[] disfa = dynamicInformation.getStaticFragmentArray();
		String[] dnfa = new String[diafa.length];
		int[] dnfia = new int[diafa.length];
		Map<String, String> dnsfm = CollectionUtil.generateNewMap();
		String sdsc = staticDescription;
		int j = 0;
		for (int i = 0; i < diafa.length; i++) {
			if (j >= disfa.length) {
				if (i >= 1 && dnfa[i - 1] == null) {
					throw new LIMException(new IllegalArgumentException());
				}
				dnfia[i] = dnfia[i - 1] + diafa[i - 1].length();
				dnfa[i] = sdsc;
				dnsfm.put(diafa[i], dnfa[i]);
			} else 
			if (disfa[j].equals(diafa[i])) {
				int index = sdsc.indexOf(diafa[i]);
				if (index < 0) {
					throw new LIMException(new IllegalArgumentException());
				} else {
					if (index > 0 && i > 0) {
						if (i >= 2 && dnfa[i - 2] == null) {
							throw new LIMException(new IllegalArgumentException());
						}
						dnfia[i - 1] = (i == 1) ? 0 : dnfia[i - 2] + diafa[i - 2].length();
						dnfa[i - 1] = sdsc.substring(0, index);
						dnsfm.put(diafa[i - 1], dnfa[i - 1]);
					}
					if (i >= 1 && dnfa[i - 1] == null) {
						throw new LIMException(new IllegalArgumentException());
					}
					dnfia[i] = (j == 0) ? index : (index + dnfia[i - 1]);
					dnfa[i] = sdsc.substring(index, index + diafa[i].length());
					sdsc = sdsc.substring(index + diafa[i].length());
				}
				j++;
			}
		}

		this.dynamicalizedFragmentArray = dnfa;
		this.dynamicalizedFragmentIndexArray = dnfia;
		this.dynamicalizedStaticFragmentMapping = dnsfm;
	}


	public static final PartDynamicalizedStaticInformation newInstance(
			final InformationElement dynamicElement, final InformationElement staticElement, 
			final String partDynamicalizedDescription, 
			final DynamicParameterType[] partDynamicParameterTypeArray) throws LIMException {
		return new PartDynamicalizedStaticInformation(dynamicElement, staticElement, 
				partDynamicalizedDescription, partDynamicParameterTypeArray);
	}


	/* getters & setters */

	public InformationElement getDynamicElement() {
		return dynamicElement;
	}

	public InformationElement getStaticElement() {
		return staticElement;
	}

	public String getPartDynamicalizedDescription() {
		return partDynamicalizedDescription;
	}

	public DynamicParameterType[] getPartDynamicParameterTypeArray() {
		return partDynamicParameterTypeArray;
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


	/* overridden methods */

	@Override
	public final String toString() {
		return INITIALISM + "(" + partDynamicalizedDescription + ")" 
				+ ": " + staticElement 
				;
	}

}
