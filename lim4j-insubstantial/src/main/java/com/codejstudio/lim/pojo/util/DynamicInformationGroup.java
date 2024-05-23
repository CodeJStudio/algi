package com.codejstudio.lim.pojo.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.GenericElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class DynamicInformationGroup extends DynamicInformation {

	/* constants */

	private static final long serialVersionUID = -7279077679995551977L;


	/* variables: arrays, collections, maps, groups */

	private Collection<DynamicInformation> dynamicInformationCollection;

	private Set<String> allDynamicFragmentSet;


	/* constructors */

	public DynamicInformationGroup() {}

	public DynamicInformationGroup(DynamicInformation... dynamicInformations) throws LIMException {
		addDynamicInformation(dynamicInformations);
	}


	/* initializers */

	private void initDynamicInformationCollection() throws LIMException {
		if (this.dynamicInformationCollection == null) {
			this.dynamicInformationCollection = CollectionUtil.generateNewCollection();
		}
	}

	private void initOrClearAllDynamicInformationSet() throws LIMException {
		if (this.allDynamicFragmentSet == null) {
			this.allDynamicFragmentSet = CollectionUtil.generateNewSet();
		} else {
			this.allDynamicFragmentSet.clear();
		}
	}


	/* destroyers */

	private void destroyDynamicInformationCollection() {
		if (this.dynamicInformationCollection != null && this.dynamicInformationCollection.size() == 0) {
			this.dynamicInformationCollection = null;
		}
	}


	/* getters & setters */

	public Collection<DynamicInformation> getDynamicInformationCollection() {
		return dynamicInformationCollection;
	}

	public Set<String> getAllDynamicFragmentSet() {
		return allDynamicFragmentSet;
	}


	/* class methods */

	private boolean updateAllDynamicFragmentSet() throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.dynamicInformationCollection)) {
			return false;
		}

		initOrClearAllDynamicInformationSet();
		boolean flag = true;
		for (DynamicInformation di : dynamicInformationCollection) {
			if (di == null) {
				continue;
			}
			flag &= CollectionUtil.addAllOfSubClass(this.allDynamicFragmentSet, di.getDynamicFragmentArray());
		}
		return flag;
	}


	/* CRUD for arrays, collections, maps, groups: dynamic informations */

	public boolean addDynamicInformation(final DynamicInformation... dynamicInformations) 
			throws LIMException {
		return addDynamicInformation(
				(dynamicInformations == null) ? null : Arrays.asList(dynamicInformations));
	}

	public boolean addDynamicInformation(final Collection<DynamicInformation> dynamicInformationCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(dynamicInformationCollection)) {
			return false;
		}

		try {
			initDynamicInformationCollection();
			return this.dynamicInformationCollection.addAll(dynamicInformationCollection) 
					& updateAllDynamicFragmentSet();
		} finally {
			destroyDynamicInformationCollection();
		}
	}

	public boolean addDynamicInformationFromElements(final GenericElement... elements) 
			throws LIMException {
		return addDynamicInformationFromElements((elements == null) ? null : Arrays.asList(elements));
	}

	public boolean addDynamicInformationFromElements(final Collection<GenericElement> elementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return false;
		}

		boolean flag = true;
		for (GenericElement ge : elementCollection) {
			if (ge != null) {
				flag &= addDynamicInformation(ge.getDynamicInformation());
			}
		}
		return flag;
	}

	public boolean removeDynamicInformation(final DynamicInformation... dynamicInformations) 
			throws LIMException {
		return removeDynamicInformation(
				(dynamicInformations == null) ? null : Arrays.asList(dynamicInformations));
	}

	public boolean removeDynamicInformation(
			final Collection<DynamicInformation> dynamicInformationCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(dynamicInformationCollection) 
				|| CollectionUtil.checkNullOrEmpty(this.dynamicInformationCollection)) {
			return false;
		}

		try {
			return this.dynamicInformationCollection.removeAll(dynamicInformationCollection) 
					& updateAllDynamicFragmentSet();
		} finally {
			destroyDynamicInformationCollection();
		}
	}

}
