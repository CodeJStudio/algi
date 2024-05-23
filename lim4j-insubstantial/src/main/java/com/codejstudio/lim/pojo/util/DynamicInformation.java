package com.codejstudio.lim.pojo.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.ICloneable;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class DynamicInformation implements ICloneable, Serializable {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DynamicInformation.class);

	private static final long serialVersionUID = 395572639118477029L;


	/* variables */

	protected String description;

	protected int inputCount = 0;
	protected int outputCount = 0;

	protected int netInputCount = 0;
	protected int netOutputCount = 0;


	/* variables: arrays, collections, maps, groups */

	private String[] allFragmentArray;
	private int[] allFragmentIndexArray;

	private String[] staticFragmentArray;
	private int[] staticFragmentIndexArray;

	protected String[] dynamicFragmentArray;
	private int[] dynamicFragmentIndexArray;

	private String[] netDynamicFragmentArray;
	private String[] netInputDynamicFragmentArray;

	private String[] placeholderArray;

	private DynamicParameterType[] dynamicFragmentParameterTypeArray;
	private DynamicParameterType[] netDynamicFragmentParameterTypeArray;

	private ConditionGroup wholeDynamicCondition;
	protected ConditionGroup[] dynamicFragmentConditionArray;
	protected ConditionGroup[] netDynamicFragmentConditionArray;


	/* constructors */

	protected DynamicInformation() {}

	protected DynamicInformation(String description) throws LIMException {
		this.description = description;
		if (description == null) {
			throw new LIMException(new IllegalArgumentException());
		}

		init();
	}


	/* initializers */

	public static DynamicInformation newInstance() {
		return new DynamicInformation();
	}

	public static DynamicInformation newInstance(String description) {
		try {
			return new DynamicInformation(description);
		} catch (LIMException e) {
			log.error(e.toString(), e);
		}
		return null;
	}

	private void init() throws LIMException {
		if (generateFragmentArrays()) {
			generateFragmentIndexArrays();
			generateDynamicFragmentParameterTypeArrayAndCount();
			generateNetDynamicFragmentParameterTypeArrayAndCount();
		}
	}

	private boolean generateFragmentArrays() throws LIMException {
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(this.description, 
				DynamicableUtil.INPUT_DYNAMIC_SYMBOL, DynamicableUtil.OUTPUT_DYNAMIC_SYMBOL);
		if (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) || arrayOfFragmentArrays.length != 3) {
			return false;
		}

		this.allFragmentArray = arrayOfFragmentArrays[0];
		this.staticFragmentArray = arrayOfFragmentArrays[1];
		this.dynamicFragmentArray = arrayOfFragmentArrays[2];
		generateNetDynamicFragmentArray();
		return true;
	}

	protected void generateNetDynamicFragmentArray() throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.dynamicFragmentArray)) {
			return;
		}

		List<String> ndfl = CollectionUtil.generateNewList();
		List<String> nidfl = CollectionUtil.generateNewList();
		for (String df : this.dynamicFragmentArray) {
			if (!ndfl.contains(df)) {
				ndfl.add(df);
				if (DynamicableUtil.INPUT_DYNAMIC_SYMBOL.verifySymbol(df)) {
					nidfl.add(df);
				}
			}
		}
		this.netDynamicFragmentArray = !CollectionUtil.checkNullOrEmpty(ndfl) 
				? ndfl.toArray(new String[0]) : this.netDynamicFragmentArray;
		this.netInputDynamicFragmentArray = !CollectionUtil.checkNullOrEmpty(nidfl) 
				? nidfl.toArray(new String[0]) : this.netInputDynamicFragmentArray;
	}

	private void generateFragmentIndexArrays() {
		this.allFragmentIndexArray = analyzeAllFragmentIndex();
		this.staticFragmentIndexArray = analyzeFragmentIndex(this.staticFragmentArray);
		this.dynamicFragmentIndexArray = analyzeFragmentIndex(this.dynamicFragmentArray);
	}

	private int[] analyzeAllFragmentIndex() {
		if (CollectionUtil.checkNullOrEmpty(this.allFragmentArray)) {
			return null;
		}

		int[] indexArray = new int[this.allFragmentArray.length];
		for (int i = 0; i < this.allFragmentArray.length; i++) {
			indexArray[i] = (i <= 0) ? 0 : (indexArray[i - 1] + this.allFragmentArray[i - 1].length());
		}
		return indexArray;
	}

	private int[] analyzeFragmentIndex(final String[] fragmentArray) {
		if (CollectionUtil.checkNullOrEmpty(this.allFragmentArray) 
				|| CollectionUtil.checkNullOrEmpty(fragmentArray)) {
			return null;
		}

		int indexOfAll = 0;
		int[] indexArray = new int[fragmentArray.length];
		for (int i = 0; i < fragmentArray.length; i++) {
			for (int j = indexOfAll; j < this.allFragmentArray.length; j++) {
				if (fragmentArray[i].equals(this.allFragmentArray[j])) {
					indexArray[i] = j;
					indexOfAll = j;
					break;
				}
			}
		}
		return indexArray;
	}

	protected void generateDynamicFragmentParameterTypeArrayAndCount() throws LIMException {
		this.dynamicFragmentParameterTypeArray = DynamicableUtil
				.generateDynamicParameterTypeFromContent(this.dynamicFragmentArray);
		if (CollectionUtil.checkNullOrEmpty(this.dynamicFragmentParameterTypeArray)) {
			return;
		}

		int ic = 0;
		int oc = 0;
		for (DynamicParameterType actionParameterType : this.dynamicFragmentParameterTypeArray) {
			if (actionParameterType.equals(DynamicParameterType.INPUT)) {
				ic++;
			} else if (actionParameterType.equals(DynamicParameterType.OUTPUT)) {
				oc++;
			}
		}
		this.inputCount = ic;
		this.outputCount = oc;
	}

	protected void generateNetDynamicFragmentParameterTypeArrayAndCount() throws LIMException {
		this.netDynamicFragmentParameterTypeArray = DynamicableUtil
				.generateDynamicParameterTypeFromContent(this.netDynamicFragmentArray);
		if (CollectionUtil.checkNullOrEmpty(this.netDynamicFragmentParameterTypeArray)) {
			return;
		}

		int nic = 0;
		int noc = 0;
		for (DynamicParameterType actionParameterType : this.netDynamicFragmentParameterTypeArray) {
			if (actionParameterType.equals(DynamicParameterType.INPUT)) {
				nic++;
			} else if (actionParameterType.equals(DynamicParameterType.OUTPUT)) {
				noc++;
			}
		}
		this.netInputCount = nic;
		this.netOutputCount = noc;
	}


	private void updateDynamicFragmentConditionArrayByNetDynamicConditions() {
		if (CollectionUtil.checkNullOrEmpty(this.netDynamicFragmentConditionArray) 
				|| !CollectionUtil.sizeEquals(this.netDynamicFragmentArray, 
						this.netDynamicFragmentConditionArray)) {
			this.dynamicFragmentConditionArray = null;
		} else {
			this.dynamicFragmentConditionArray = new ConditionGroup[this.dynamicFragmentArray.length];
			for (int i = 0; i < this.dynamicFragmentArray.length; i++) {
				for (int j = 0; j < this.netDynamicFragmentArray.length; j++) {
					if (Objects.equals(this.dynamicFragmentArray[i], this.netDynamicFragmentArray[j])) {
						this.dynamicFragmentConditionArray[i] = this.netDynamicFragmentConditionArray[j];
						break;
					}
				}
			}
		}
	}


	/* getters & setters */

	public String getDescription() {
		return description;
	}

	public int getInputCount() {
		return inputCount;
	}

	public int getOutputCount() {
		return outputCount;
	}

	public int getNetInputCount() {
		return netInputCount;
	}

	public int getNetOutputCount() {
		return netOutputCount;
	}

	/**
	 * 动态信息计数
	 */
	public int dynamicCount() {
		return this.inputCount + this.outputCount;
	}

	public boolean isDynamic() {
		return dynamicCount() > 0;
	}


	public String[] getAllFragmentArray() {
		return allFragmentArray;
	}

	public int[] getAllFragmentIndexArray() {
		return allFragmentIndexArray;
	}

	public String[] getStaticFragmentArray() {
		return staticFragmentArray;
	}

	public int[] getStaticFragmentIndexArray() {
		return staticFragmentIndexArray;
	}

	public String[] getDynamicFragmentArray() {
		return dynamicFragmentArray;
	}

	public int[] getDynamicFragmentIndexArray() {
		return dynamicFragmentIndexArray;
	}

	public String[] getNetDynamicFragmentArray() {
		return netDynamicFragmentArray;
	}

	public String[] getNetInputDynamicFragmentArray() {
		return netInputDynamicFragmentArray;
	}

	public String[] getPlaceholderArray() {
		if (this.placeholderArray == null && this.allFragmentArray != null) {
			this.placeholderArray 
					= DynamicableUtil.substitutePlaceholderForDynamicContent(this.allFragmentArray);
		}
		return placeholderArray;
	}


	public DynamicParameterType[] getDynamicFragmentParameterTypeArray() {
		return dynamicFragmentParameterTypeArray;
	}

	public DynamicParameterType[] getNetDynamicFragmentParameterTypeArray() {
		return netDynamicFragmentParameterTypeArray;
	}

	public void setDynamicFragmentParameterTypes(DynamicParameterType... types) {
		this.dynamicFragmentParameterTypeArray = types;
	}


	public ConditionGroup getWholeDynamicCondition() {
		return wholeDynamicCondition;
	}

	public void setWholeDynamicCondition(ConditionGroup wholeDynamicCondition) {
		this.wholeDynamicCondition = wholeDynamicCondition;
	}

	public ConditionGroup[] getDynamicFragmentConditionArray() {
		return dynamicFragmentConditionArray;
	}

	public ConditionGroup[] getNetDynamicFragmentConditionArray() {
		return netDynamicFragmentConditionArray;
	}

	public void setNetDynamicConditions(ConditionGroup... conditions) {
		this.netDynamicFragmentConditionArray = conditions;
		updateDynamicFragmentConditionArrayByNetDynamicConditions();
	}


	/* overridden methods */


	@Override
	public DynamicInformation cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		DynamicInformation clonedElement;
		try {
			clonedElement = (DynamicInformation) this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}

		clonedElement.description = this.description;
		clonedElement.inputCount = this.inputCount;
		clonedElement.outputCount = this.outputCount;
		clonedElement.netInputCount = this.netInputCount;
		clonedElement.netOutputCount = this.netOutputCount;

		if (this.allFragmentArray != null) {
			clonedElement.allFragmentArray = new String[this.allFragmentArray.length];
			for (int i = 0; i < this.allFragmentArray.length; i++) {
				clonedElement.allFragmentArray[i] = this.allFragmentArray[i];
			}
		}
		if (this.allFragmentIndexArray != null) {
			clonedElement.allFragmentIndexArray = new int[this.allFragmentIndexArray.length];
			for (int i = 0; i < this.allFragmentIndexArray.length; i++) {
				clonedElement.allFragmentIndexArray[i] = this.allFragmentIndexArray[i];
			}
		}
		if (this.staticFragmentArray != null) {
			clonedElement.staticFragmentArray = new String[this.staticFragmentArray.length];
			for (int i = 0; i < this.staticFragmentArray.length; i++) {
				clonedElement.staticFragmentArray[i] = this.staticFragmentArray[i];
			}
		}
		if (this.staticFragmentIndexArray != null) {
			clonedElement.staticFragmentIndexArray = new int[this.staticFragmentIndexArray.length];
			for (int i = 0; i < this.staticFragmentIndexArray.length; i++) {
				clonedElement.staticFragmentIndexArray[i] = this.staticFragmentIndexArray[i];
			}
		}
		if (this.dynamicFragmentArray != null) {
			clonedElement.dynamicFragmentArray = new String[this.dynamicFragmentArray.length];
			for (int i = 0; i < this.dynamicFragmentArray.length; i++) {
				clonedElement.dynamicFragmentArray[i] = this.dynamicFragmentArray[i];
			}
		}
		if (this.dynamicFragmentIndexArray != null) {
			clonedElement.dynamicFragmentIndexArray = new int[this.dynamicFragmentIndexArray.length];
			for (int i = 0; i < this.dynamicFragmentIndexArray.length; i++) {
				clonedElement.dynamicFragmentIndexArray[i] = this.dynamicFragmentIndexArray[i];
			}
		}
		if (this.netDynamicFragmentArray != null) {
			clonedElement.netDynamicFragmentArray = new String[this.netDynamicFragmentArray.length];
			for (int i = 0; i < this.netDynamicFragmentArray.length; i++) {
				clonedElement.netDynamicFragmentArray[i] = this.netDynamicFragmentArray[i];
			}
		}
		if (this.netInputDynamicFragmentArray != null) {
			clonedElement.netInputDynamicFragmentArray 
					= new String[this.netInputDynamicFragmentArray.length];
			for (int i = 0; i < this.netInputDynamicFragmentArray.length; i++) {
				clonedElement.netInputDynamicFragmentArray[i] = this.netInputDynamicFragmentArray[i];
			}
		}
		if (this.placeholderArray != null) {
			clonedElement.placeholderArray = new String[this.placeholderArray.length];
			for (int i = 0; i < this.placeholderArray.length; i++) {
				clonedElement.placeholderArray[i] = this.placeholderArray[i];
			}
		}
		if (this.dynamicFragmentParameterTypeArray != null) {
			clonedElement.dynamicFragmentParameterTypeArray 
					= new DynamicParameterType[this.dynamicFragmentParameterTypeArray.length];
			for (int i = 0; i < this.dynamicFragmentParameterTypeArray.length; i++) {
				clonedElement.dynamicFragmentParameterTypeArray[i] 
						= this.dynamicFragmentParameterTypeArray[i];
			}
		}
		if (this.netDynamicFragmentParameterTypeArray != null) {
			clonedElement.netDynamicFragmentParameterTypeArray 
					= new DynamicParameterType[this.netDynamicFragmentParameterTypeArray.length];
			for (int i = 0; i < this.netDynamicFragmentParameterTypeArray.length; i++) {
				clonedElement.netDynamicFragmentParameterTypeArray[i] 
						= this.netDynamicFragmentParameterTypeArray[i];
			}
		}

		clonedElement.wholeDynamicCondition = (this.wholeDynamicCondition != null) 
				? this.wholeDynamicCondition.cloneElement(clonedElementMap) 
				: clonedElement.wholeDynamicCondition;

		if (this.dynamicFragmentConditionArray != null) {
			clonedElement.dynamicFragmentConditionArray 
					= new ConditionGroup[this.dynamicFragmentConditionArray.length];
			for (int i = 0; i < this.dynamicFragmentConditionArray.length; i++) {
				clonedElement.dynamicFragmentConditionArray[i] 
						= (this.dynamicFragmentConditionArray[i] != null) 
								? this.dynamicFragmentConditionArray[i].cloneElement(clonedElementMap) 
								: clonedElement.dynamicFragmentConditionArray[i];
			}
		}
		if (this.netDynamicFragmentConditionArray != null) {
			clonedElement.netDynamicFragmentConditionArray 
					= new ConditionGroup[this.netDynamicFragmentConditionArray.length];
			for (int i = 0; i < this.netDynamicFragmentConditionArray.length; i++) {
				clonedElement.netDynamicFragmentConditionArray[i] 
						= (this.netDynamicFragmentConditionArray[i] != null) 
								? this.netDynamicFragmentConditionArray[i].cloneElement(clonedElementMap) 
								: clonedElement.netDynamicFragmentConditionArray[i];
			}
		}

		return clonedElement;
	}


	/* static methods */

	public static boolean equivalencyCheck(DynamicInformation dynamicInformation) {
		try {
			ConditionGroup wdcd;
			List<Condition> wdcdigl;
			if (dynamicInformation == null) {
				return false;
			} else if ((wdcd = dynamicInformation.getWholeDynamicCondition()) == null 
					|| CollectionUtil.checkNullOrEmpty(wdcdigl = wdcd.getInnerGroupList())) {
				return true;
			}

			for (Condition cd : wdcdigl) {
				if (cd.equals(DynamicableUtil.CONDITION_NOT_SAME)) {
					return false;
				}
			}
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return true;
	}


	/* class methods */

	public String generateStaticDescription(Map<String, String> dynamicalizedStaticFragmentMapping, 
			Collection<Concept> alternativeCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(dynamicalizedStaticFragmentMapping)) {
			return null;
		}

		Collection<Concept> cc = null;
		if (!CollectionUtil.checkNullOrEmpty(alternativeCollection)) {
			cc = CollectionUtil.generateNewCollection();
			cc.addAll(alternativeCollection);
		}

		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (String frag : this.allFragmentArray) {
			if (i < this.staticFragmentArray.length 
					&& frag.equals(this.staticFragmentArray[i])) {
				sb.append(frag);
			} else {
				String mappingFrag = dynamicalizedStaticFragmentMapping.get(frag);
				if (mappingFrag == null && cc != null) {
					for (Concept c : cc) {
						if (c != null && (mappingFrag = c.getDescription()) != null) {
							dynamicalizedStaticFragmentMapping.put(frag, mappingFrag);
							cc.remove(c);
							break;
						}
					}
				}
				sb.append((mappingFrag != null) ? mappingFrag : frag);
			}
		}

		return sb.toString();
	}

	public boolean verifyStaticFragments(final InformationElement element) throws LIMException {
		return (element == null) ? false : verifyStaticFragments(element.getDescription());
	}

	public boolean verifyStaticFragments(final String description) {
		if (StringUtil.isEmpty(description) || CollectionUtil.checkNullOrEmpty(this.staticFragmentArray)) {
			return false;
		}

		int index = 0;
		for (String sf : this.staticFragmentArray) {
			if ((index = description.indexOf(sf, index)) < 0) {
				return false;
			}
		}
		return true;
	}


	public boolean verifyDynamicFragmentCondition(final InformationElement element) throws LIMException {
		return (element == null) ? false : verifyDynamicFragmentCondition(element.getDescription());
	}

	public boolean verifyDynamicFragmentCondition(final String description) throws LIMException {
		String[][] arrayOfFragmentArrays = DynamicableUtil
				.analyzeStaticContentBasedOnDynamicContent(description, this);
		if (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) || arrayOfFragmentArrays.length != 3 
				|| arrayOfFragmentArrays[2].length != this.dynamicFragmentConditionArray.length) {
			return false;
		}

		for (int i = 0; i < arrayOfFragmentArrays[2].length; i++) {
			InformationElement ie = Informationiverse.seekInformationByDescription(arrayOfFragmentArrays[2][i]);
			if (!IConditionable.verifyCondition(ie, this.dynamicFragmentConditionArray[i])) {
				return false;
			}
		}
		return true;
	}

}
