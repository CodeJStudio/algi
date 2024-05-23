package com.codejstudio.lim;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.codejstudio.lim.common.collection.MultiValueMap;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.util.GroupableUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class Informationiverse {

	/* constants */

	private static final String DEFAULT_MASTERID = "-1";


	/* variables: arrays, collections, maps, groups */

	private static final Map<String, GenericElement> ELEMENT_MAP;

	private static final Map<String, List<String>> ELEMENT_RELATION_MAP;

	private static final Map<String, GenericActionableElement> ACTIONABLE_ELEMENT_MAP;


	/* initializers */

	static {
		try {
			ELEMENT_MAP = CollectionUtil.generateNewConcurrentMap();
			ELEMENT_RELATION_MAP = CollectionUtil.generateNewConcurrentMap();
			ACTIONABLE_ELEMENT_MAP = CollectionUtil.generateNewConcurrentMap();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* CRUD for arrays, collections, maps, groups: universe elements */

	public static Collection<GenericElement> getAllElementCollection() {
		return ELEMENT_MAP.values();
	}

	public static GenericElement getElement(final String id) {
		return ELEMENT_MAP.get(id);
	}

	public static <T extends GenericElement> T getRandomElement(final Class<T> tClazz) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(ELEMENT_MAP.values())) {
			return null;
		}

		List<T> tl = CollectionUtil.convertToList(tClazz, ELEMENT_MAP.values());
		return CollectionUtil.checkNullOrEmpty(tl) ? null : tl.get((int) (Math.random() * tl.size()));
	}


	public synchronized static boolean addElement(final GenericElement element) throws LIMException {
		return addElement(element, DEFAULT_MASTERID);
	}

	public synchronized static boolean addElement(final GenericElement element, 
			final BaseElement masterElement) throws LIMException {
		return addElement(element, masterElement.getId());
	}

	protected synchronized static boolean addElement(final GenericElement element, final String masterId) 
			throws LIMException {
		if (element == null) {
			return false;
		}

		List<String> relatedIdList = ELEMENT_RELATION_MAP.get(element.getId());
		if (relatedIdList != null && relatedIdList.contains(masterId)) {
			return false;
		}

		ELEMENT_MAP.put(element.getId(), element);
		relatedIdList = (relatedIdList == null) ? CollectionUtil.generateNewList() : relatedIdList;
		relatedIdList.add(masterId);
		ELEMENT_RELATION_MAP.put(element.getId(), relatedIdList);
		return true;
	}


	public synchronized static boolean removeElement(final String id) {
		return removeElement(null, id, DEFAULT_MASTERID);
	}

	public synchronized static boolean removeElement(final GenericElement element) {
		return removeElement(element, null, DEFAULT_MASTERID);
	}

	public synchronized static boolean removeElement(final String id, final BaseElement masterElement) {
		return removeElement(null, id, masterElement.getId());
	}

	public synchronized static boolean removeElement(final GenericElement element, 
			final BaseElement masterElement) {
		return removeElement(element, null, masterElement.getId());
	}

	protected synchronized static boolean removeElement(final GenericElement element, 
			final String elementId, final String masterId) {
		if (element == null && elementId == null) {
			return false;
		}

		List<String> relatedIdList = (element != null) ? ELEMENT_RELATION_MAP.get(element.getId()) 
				: ELEMENT_RELATION_MAP.get(elementId);
		if (relatedIdList == null || !relatedIdList.contains(masterId)) {
			return false;
		}

		if (element != null) {
			ELEMENT_MAP.remove(element.getId(), element);
		} else {
			ELEMENT_MAP.remove(elementId);
		}

		relatedIdList.remove(masterId);
		if (relatedIdList.size() == 0) {
			ELEMENT_RELATION_MAP.remove(element.getId(), relatedIdList);
		}
		return true;
	}


	public synchronized static GenericElement replaceElement(final BaseElement replacee, 
			final GenericElement replacer) throws LIMException {
		return replaceElement(replacee, replacer, DEFAULT_MASTERID);
	}

	public synchronized static GenericElement replaceElement(final BaseElement replacee, 
			final GenericElement replacer, final BaseElement masterElement) throws LIMException {
		return replaceElement(replacee, replacer, masterElement.getId());
	}

	protected synchronized static GenericElement replaceElement(final BaseElement replacee, 
			final GenericElement replacer, final String masterId) throws LIMException {
		if (Objects.equals(replacee, replacer)) {
			return replacer;
		}

		GenericElement ge = null;
		if (replacee != null) {
			List<String> relatedIdList = ELEMENT_RELATION_MAP.get(replacee.getId());
			if (relatedIdList != null && relatedIdList.contains(masterId)) {
				if (replacee instanceof GenericElement) {
					ELEMENT_MAP.remove(replacee.getId(), replacee);
					ge = (GenericElement) replacee;
				} else {
					ge = ELEMENT_MAP.remove(replacee.getId());
				}

				relatedIdList.remove(masterId);
				if (relatedIdList.size() == 0) {
					ELEMENT_RELATION_MAP.remove(replacee.getId(), relatedIdList);
				}
			}
		}

		addElement(replacer, masterId);
		return ge;
	}


	/* CRUD for arrays, collections, maps, groups: universe actionable elements */

	public static Collection<GenericActionableElement> getAllActionableElementCollection() {
		return ACTIONABLE_ELEMENT_MAP.values();
	}

	public static GenericActionableElement getActionableElement(final String id) {
		return ACTIONABLE_ELEMENT_MAP.get(id);
	}

	public synchronized static boolean addActionableElement(
			final GenericActionableElement actionableElement) {
		if (actionableElement == null) {
			return false;
		}

		ACTIONABLE_ELEMENT_MAP.put(actionableElement.getId(), actionableElement);
		return true;
	}


	public synchronized static boolean removeActionableElement(final String id) {
		if (id == null) {
			return false;
		}
		ACTIONABLE_ELEMENT_MAP.remove(id);
		return true;
	}

	public synchronized static boolean removeActionableElement(
			final GenericActionableElement actionableElement) {
		if (actionableElement == null) {
			return false;
		}
		return ACTIONABLE_ELEMENT_MAP.remove(actionableElement.getId(), actionableElement);
	}


	public synchronized static GenericActionableElement replaceActionableElement(
			final BaseElement replacee, final GenericActionableElement replacer) {
		if (Objects.equals(replacee, replacer)) {
			return replacer;
		}

		GenericActionableElement gae = null;
		if (replacee != null) {
			if (replacee instanceof GenericActionableElement) {
				ACTIONABLE_ELEMENT_MAP.remove(replacee.getId(), replacee);
				gae = (GenericActionableElement) replacee;
			} else {
				gae = ACTIONABLE_ELEMENT_MAP.remove(replacee.getId());
			}
		}

		addActionableElement(replacer);
		return gae;
	}


	/* static methods */

	public static Collection<BaseElement> getAll() throws LIMException {
		Collection<BaseElement> bec = CollectionUtil.generateNewCollection();
		bec.addAll(ELEMENT_MAP.values());
		bec.addAll(ACTIONABLE_ELEMENT_MAP.values());
		return CollectionUtil.checkNullOrEmpty(bec) ? null : bec;
	}


	public static Concept getRandomConcept() throws LIMException {
		Concept c;
		while ((c = getRandomElement(Concept.class)) instanceof IGroupable);
		return c;
	}

	public static Statement getRandomStatement() throws LIMException {
		Statement s;
		while ((s = getRandomElement(Statement.class)) instanceof IGroupable);
		return s;
	}

	public static HypotheticalProposition getRandomHypotheticalProposition() throws LIMException {
		HypotheticalProposition hpp;
		while ((hpp = getRandomElement(HypotheticalProposition.class)) instanceof IGroupable);
		return hpp;
	}



	public static InformationElement seekInformationByDescription(final String description) 
			throws LIMException {
		if (StringUtil.isEmpty(description)) {
			return null;
		}

		for (GenericElement ge : ELEMENT_MAP.values()) {
			if (ge instanceof InformationElement 
					&& description.equals(((InformationElement) ge).getDescription())) {
				return (InformationElement) ge;
			}
		}
		return null;
	}

	public static Collection<InformationElement> seekInformationsByDescription(final String description) 
			throws LIMException {
		if (StringUtil.isEmpty(description)) {
			return null;
		}

		Collection<InformationElement> iec = CollectionUtil.generateNewCollection();
		for (GenericElement ge : ELEMENT_MAP.values()) {
			if (ge instanceof InformationElement 
					&& description.equals(((InformationElement) ge).getDescription())) {
				iec.add((InformationElement) ge);
			}
		}
		return CollectionUtil.checkNullOrEmpty(iec) ? null : iec;
	}

	public static Collection<InformationElement> seekInformationsByDescription(final String description, 
			final String separator) throws LIMException {
		if (StringUtil.isEmpty(description)) {
			return null;
		}
		if (StringUtil.isEmpty(separator)) {
			return seekInformationsByDescription(description);
		}

		Collection<InformationElement> iec = CollectionUtil.generateNewCollection();
		String[] stra = StringUtil.splitIncludingSeparator(description, separator);
		for (GenericElement ge : ELEMENT_MAP.values()) {
			if (ge instanceof InformationElement) {
				InformationElement ie = (InformationElement) ge;
				if (StringUtil.getReplacedFragment(stra, ie.getDescription(), separator) != null) {
					iec.add(ie);
				}
			}
		}
		return CollectionUtil.checkNullOrEmpty(iec) ? null : iec;
	}


	public static MultiValueMap<InformationElement, Integer> seekSubElementsByDescription(
			final String description, final Integer... indexesOfSubElement) throws LIMException {
		if (StringUtil.isEmpty(description)) {
			return null;
		}

		List<Integer> idxl = null;
		if (!CollectionUtil.checkNullOrEmpty(indexesOfSubElement)) {
			idxl = Arrays.asList(indexesOfSubElement);
		}

		MultiValueMap<InformationElement, Integer> ieimvm = CollectionUtil.generateNewMultiValueMap();
		for (GenericElement ge : ELEMENT_MAP.values()) {
			if (ge instanceof InformationElement) {
				InformationElement ie = (InformationElement) ge;
				String d;
				if (ie != null && (d = ie.getDescription()) != null && !description.equals(d) 
						&& description.contains(d)) {
					Integer idx = description.indexOf(d, 0);
					while (idx >= 0) {
						if (idxl == null || idxl.contains(idx)) {
							ieimvm.put(ie, idx);
						}
						idx = description.indexOf(d, idx + 1);
					}
				}
			}
		}
		return CollectionUtil.checkNullOrEmpty(ieimvm) ? null : ieimvm;
	}


	public static Collection<InformationElement> seekByConditions(final ConditionGroup conditionGroup) 
			throws LIMException {
		if (GroupableUtil.checkNullOrEmpty(conditionGroup)) {
			return null;
		}

		Collection<InformationElement> iec = CollectionUtil.generateNewCollection();
		for (GenericElement ge : ELEMENT_MAP.values()) {
			if ((ge instanceof InformationElement) 
					&& IConditionable.verifyCondition((InformationElement) ge, conditionGroup, false)) {
				iec.add((InformationElement) ge);
			}
		}
		return CollectionUtil.checkNullOrEmpty(iec) ? null : iec;
	}


	public static Collection<BaseRelation> seekRelations(final InformationElement primaryElement, 
			final InformationElement secondaryElement, final boolean switchableFlag) throws LIMException {
		if (primaryElement == null || secondaryElement == null) {
			return null;
		}

		Collection<BaseRelation> brc = CollectionUtil.generateNewCollection();
		for (GenericElement ge : ELEMENT_MAP.values()) {
			if (ge instanceof BaseRelation) {
				BaseRelation br = (BaseRelation) ge;
				if ((primaryElement.baseEquals(br.getPrimaryElement()) 
						&& secondaryElement.baseEquals(br.getSecondaryElement())) 
						|| (switchableFlag && primaryElement.baseEquals(br.getSecondaryElement()) 
								&& secondaryElement.baseEquals(br.getPrimaryElement()))) {
					brc.add(br);
				}
			}
		}
		return CollectionUtil.checkNullOrEmpty(brc) ? null : brc;
	}

}
