package com.codejstudio.algi.action.dynamicAction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.collection.MultiValue;
import com.codejstudio.lim.common.collection.MultiValueMap;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.JudgedStatementGroup;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.util.DynamicInformation;
import com.codejstudio.lim.pojo.util.DynamicalizedStaticInformation;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DynamicHypotheticalPropositionAction extends AbstractDynamicAction {

	/* constants */

	public static final String PARAMETER_ELEMENT = "DHP" + SEPARATOR_PARAMETER_NAME + "element";

	public static final String PARAMETER_NAME_PREFIX = PARAMETER_ELEMENT + SEPARATOR_PARAMETER_NAME;


	/* variables */

	protected HypotheticalProposition dynamicHypotheticalProposition;

	protected String parameterName;


	/* constructors */

	public DynamicHypotheticalPropositionAction(HypotheticalProposition dynamicHypotheticalProposition, 
			String parameterName) throws ALGIException {
		super();
		Boolean dn;
		if (dynamicHypotheticalProposition == null 
				|| (dn = dynamicHypotheticalProposition.getDynamic()) == null || !dn) {
			throw new ALGIException(new IllegalArgumentException());
		}
		this.dynamicHypotheticalProposition = dynamicHypotheticalProposition;

		if (StringUtil.isEmpty(parameterName)) {
			this.parameterName = PARAMETER_ELEMENT;
		} else if (parameterName.startsWith(PARAMETER_ELEMENT)) {
			this.parameterName = parameterName;
		} else {
			this.parameterName = PARAMETER_NAME_PREFIX + parameterName;
		}
	}

	public DynamicHypotheticalPropositionAction(HypotheticalProposition dynamicHypotheticalProposition) 
			throws ALGIException {
		this(dynamicHypotheticalProposition, null);
	}


	/* getters & setters */

	public HypotheticalProposition getDynamicHypotheticalProposition() {
		return dynamicHypotheticalProposition;
	}


	/* overridden methods */

	@Override
	protected Object[] doExecute() throws LIMException {
		ConditionGroup atdg = this.dynamicHypotheticalProposition.getAntecedentGroup();
		JudgedStatementGroup csqg = this.dynamicHypotheticalProposition.getConsequentGroup();
		List<Condition> atdigl;
		List<JudgedStatement> csqigl;
		if (atdg == null || CollectionUtil.checkNullOrEmpty(atdigl = atdg.getInnerGroupList()) 
				|| csqg == null || CollectionUtil.checkNullOrEmpty(csqigl = csqg.getInnerGroupList())) {
			return null;
		}

		Object obj = this.session.getDynamicElement(parameterName);
		Object[] obja;
		if (obj instanceof Object[]) {
			obja = makeupInputArray((Object[]) obj);
		} else {
			obja = new Object[] {obj};
		}

		Collection<Concept> cc = separateConceptsFromInputs(obja);
		Map<String, String> dsfm = generateDynamicalizedStaticFragmentMapping(obja, atdigl);

		List<JudgedStatement> jsl = CollectionUtil.generateNewList();
		for (JudgedStatement js : csqigl) {
			Boolean dn;
			if (js != null && (dn = js.getDynamic()) != null && dn) {
				DynamicInformation di = js.getDynamicInformation();
				String dsc = di.generateStaticDescription(dsfm, cc);
				boolean flag = false;
				Collection<InformationElement> iec;
				if (dsc != null 
						&& !CollectionUtil.checkNullOrEmpty(
								iec = Informationiverse.seekInformationsByDescription(dsc))) {
					for (InformationElement ie : iec) {
						if (ie instanceof JudgedStatement) {
							jsl.add((JudgedStatement) ie);
							flag = true;
						}
					}
				}
				if (!flag && dsc != null) {
					jsl.add(new JudgedStatement(dsc));
				}
			} else {
				jsl.add(js);
			}
		}

		return CollectionUtil.checkNullOrEmpty(jsl) ? null : jsl.toArray();
	}

	private Collection<Concept> separateConceptsFromInputs(final Object[] madeupInputArray) 
			throws LIMException {
		Collection<Concept> cc = null;
		for (int i = 0; i < madeupInputArray.length; i++) {
			if (madeupInputArray[i] instanceof Concept) {
				if (cc == null) {
					cc = CollectionUtil.generateNewCollection();
				}
				cc.add((Concept) madeupInputArray[i]);
			}
		}
		return cc;
	}

	private Map<String, String> generateDynamicalizedStaticFragmentMapping(final Object[] madeupInputArray, 
			final List<Condition> antecedentList) throws LIMException {
		MultiValueMap<DynamicInformation, DynamicalizedStaticInformation> ddmvm 
				= CollectionUtil.generateNewMultiValueMap();
		final int validStaticLength 
				= generateDynamicMappingAndStaticLength(madeupInputArray, antecedentList, ddmvm);
		final int dynamicLength = ddmvm.keySet().size();
		if (validStaticLength == 0 || dynamicLength == 0) {
			return null;
		}

		DynamicalizedStaticInformation[][] dsi2da 
				= new DynamicalizedStaticInformation[dynamicLength][validStaticLength];
		convertDynamicMappingTo2DArray(ddmvm, dsi2da);

		List<DynamicalizedStaticInformation[]> dsial = regroupDynamicMappingArray(dynamicLength, dsi2da);
		List<DynamicalizedStaticInformation[]> verifiedDsial = verifyDynamicMappingArray(dsial);
		if (verifiedDsial.size() == 0) {
			return null;
		}

		return generateVerifiedDynamicalizedStaticFragmentMapping(verifiedDsial);
	}

	private int generateDynamicMappingAndStaticLength(final Object[] madeupInputArray, 
			final List<Condition> antecedentList, 
			MultiValueMap<DynamicInformation, DynamicalizedStaticInformation> ddMVMap) throws LIMException {
		int validStaticLength = 0;
		for (int i = 0; i < madeupInputArray.length; i++) {
			if (madeupInputArray[i] instanceof Concept) {
				continue;
			}
			boolean vslFlag = false;
			for (Condition cd : antecedentList) {
				Statement s;
				Boolean dn;
				if (!(cd instanceof HypotheticalCondition) 
						|| (s = ((HypotheticalCondition) cd).getAntecedent()) == null 
						|| (dn = s.getDynamic()) == null || !dn) {
					continue;
				}

				DynamicInformation di = s.getDynamicInformation();
				DynamicalizedStaticInformation dsi = (madeupInputArray[i] instanceof InformationElement) 
						? DynamicalizedStaticInformation.newInstance((InformationElement) madeupInputArray[i], di) 
						: DynamicalizedStaticInformation.newInstance(madeupInputArray[i].toString(), di);
				if (dsi != null) {
					ddMVMap.put(di, dsi);
					vslFlag = true;
				}
			}
			if (vslFlag) {
				validStaticLength++;
			}
		}
		return validStaticLength;
	}

	private void convertDynamicMappingTo2DArray(
			final MultiValueMap<DynamicInformation, DynamicalizedStaticInformation> ddMVMap, 
			DynamicalizedStaticInformation[][] arrayOfDynamicalizedStaticInformationArrays) {
		int i = 0;
		Set<DynamicInformation> ddks = ddMVMap.keySet();
		for (DynamicInformation k : ddks) {
			MultiValue<DynamicalizedStaticInformation> dsimv = ddMVMap.get(k);
			int j = 0;
			for (DynamicalizedStaticInformation dsi : dsimv.toCollection()) {
				arrayOfDynamicalizedStaticInformationArrays[i][j] = dsi;
				j++;
			}
			i++;
		}
	}

	private List<DynamicalizedStaticInformation[]> regroupDynamicMappingArray(final int dynamicLength, 
			final DynamicalizedStaticInformation[][] arrayOfDynamicalizedStaticInformationArrays) 
					throws LIMException {
		List<DynamicalizedStaticInformation[]> dsial = CollectionUtil.generateNewList();
		int[] tmpSizeArray = new int[dynamicLength];
		regroupDynamicMappingArray(tmpSizeArray, arrayOfDynamicalizedStaticInformationArrays, dsial);
		return dsial;
	}

	private void regroupDynamicMappingArray(int[] tmpSizeArray, 
			final DynamicalizedStaticInformation[][] arrayOfDynamicalizedStaticInformationArrays, 
			List<DynamicalizedStaticInformation[]> dynamicalizedStaticInformationArrayList) 
					throws LIMException {
		List<DynamicalizedStaticInformation> dsil = CollectionUtil.generateNewList();
		for (int i = 0; i < arrayOfDynamicalizedStaticInformationArrays.length; i++) {
			dsil.add(arrayOfDynamicalizedStaticInformationArrays[i][tmpSizeArray[i]]);
		}
		dynamicalizedStaticInformationArrayList.add(dsil.toArray(new DynamicalizedStaticInformation[0]));

		tmpSizeArray[0]++;
		for (int i = 0; i < tmpSizeArray.length; i++) {
			if (tmpSizeArray[i] >= arrayOfDynamicalizedStaticInformationArrays[i].length) {
				if (i < tmpSizeArray.length - 1) {
					tmpSizeArray[i + 1]++;
				} else {
					return;
				}
				tmpSizeArray[i] = 0;
			}
		}
		regroupDynamicMappingArray(tmpSizeArray, arrayOfDynamicalizedStaticInformationArrays, 
				dynamicalizedStaticInformationArrayList);
	}


	private List<DynamicalizedStaticInformation[]> verifyDynamicMappingArray(
			final List<DynamicalizedStaticInformation[]> dynamicalizedStaticInformationArrayList) 
					throws LIMException {
		List<DynamicalizedStaticInformation[]> dsial = CollectionUtil.generateNewList();
		for (DynamicalizedStaticInformation[] dsia : dynamicalizedStaticInformationArrayList) {
			if (verifyDynamicMappingArray(dsia)) {
				dsial.add(dsia);
			}
		}
		return dsial;
	}

	private boolean verifyDynamicMappingArray(
			final DynamicalizedStaticInformation[] dynamicalizedStaticInformationArray) throws LIMException {
		return consistencyCheck(dynamicalizedStaticInformationArray) 
				&& equivalencyCheck(dynamicalizedStaticInformationArray);
	}

	private boolean consistencyCheck(
			final DynamicalizedStaticInformation[] dynamicalizedStaticInformationArray) throws LIMException {
		Map<String, String> ssm = CollectionUtil.generateNewMap();
		for (DynamicalizedStaticInformation dsi : dynamicalizedStaticInformationArray) {
			Map<String, String> dsfm = dsi.getDynamicalizedStaticFragmentMapping();
			Set<String> dsfks = dsfm.keySet();
			for (String k : dsfks) {
				if (!ssm.containsKey(k)) {
					ssm.put(k, dsfm.get(k));
				} else if (!ssm.get(k).equals(dsfm.get(k))) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean equivalencyCheck(
			final DynamicalizedStaticInformation[] dynamicalizedStaticInformationArray) throws LIMException {
		if (!DynamicInformation.equivalencyCheck(
				this.dynamicHypotheticalProposition.getDynamicInformation())) {
			return true;
		}

		Map<String, String> ssm = CollectionUtil.generateNewMap();
		for (DynamicalizedStaticInformation dsi : dynamicalizedStaticInformationArray) {
			Map<String, String> dsfm = dsi.getDynamicalizedStaticFragmentMapping();
			Set<String> dsfks = dsfm.keySet();
			for (String k : dsfks) {
				String value = dsfm.get(k);
				if (!ssm.containsKey(value)) {
					ssm.put(value, k);
				} else if (!ssm.get(value).equals(k)) {
					return false;
				}
			}
		}
		return true;
	}


	private Map<String, String> generateVerifiedDynamicalizedStaticFragmentMapping(
			final List<DynamicalizedStaticInformation[]> verifiedDynamicalizedStaticInformationArrayList) 
					throws LIMException {
		DynamicalizedStaticInformation[] vdsia = verifiedDynamicalizedStaticInformationArrayList.get(0);

		Map<String, String> ssm = CollectionUtil.generateNewMap();
		for (DynamicalizedStaticInformation dsi : vdsia) {
			ssm.putAll(dsi.getDynamicalizedStaticFragmentMapping());
		}
		return ssm;
	}

}
