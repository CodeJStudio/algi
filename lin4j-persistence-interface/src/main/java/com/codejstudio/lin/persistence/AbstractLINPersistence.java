package com.codejstudio.lin.persistence;

import java.util.Collection;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.PropertiesClass;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lin.common.exception.LINException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public abstract class AbstractLINPersistence implements ILINPersistence {

	/* variables */

	private static ILINPersistence persistence;

	private boolean acceptSave;


	/* variables: arrays, collections, maps, groups */

	protected Map<String, Collection<BaseElement>> elementMap;
	protected Map<String, String> idsMap;
	protected Collection<String> uniqueNeo4jEntityIdCollection;


	/* initializers */

	public static ILINPersistence getPersistence() throws LINException {
		try {
			if (persistence == null) {
				PropertiesClass pcl = new PropertiesClass(
						PROPERTIES_FILENAME_PERSISTENCE, PROPERTY_KEY_PERSISTENCE_DEFAULT, true);
				persistence = (ILINPersistence) pcl.getTypeOfClass().newInstance();
				((AbstractLINPersistence)persistence).init();
			}
		} catch (Exception e) {
			throw LINException.getLINException(e);
		}
		return persistence;
	}

	protected void init() throws LIMException {
		this.elementMap = CollectionUtil.generateNewMap();
		this.idsMap = CollectionUtil.generateNewMap();
		this.uniqueNeo4jEntityIdCollection = CollectionUtil.generateNewCollection();
	}


	/* getters & setters */

	@Override
	public boolean isAcceptSave() {
		return acceptSave;
	}

	@Override
	public void setAcceptSave(boolean acceptSave) {
		this.acceptSave = acceptSave;
	}


	/* CRUD for arrays, collections, maps, groups: elements */

	@Override
	public Map<String, Collection<BaseElement>> getAllElementMap() {
		return this.elementMap;
	}

	@Override
	public Collection<BaseElement> getElementCollection(final String dbName) {
		return this.elementMap.get(dbName);
	}

	@Override
	public boolean containElement(final String dbName, final BaseElement element) {
		Collection<BaseElement> bec = this.elementMap.get(dbName);
		return !CollectionUtil.checkNullOrEmpty(bec) && bec.contains(element);
	}

	@Override
	public void addElements(final String dbName, final Collection<BaseElement> elementCollection) 
			throws LIMException {
		Collection<BaseElement> bec = this.elementMap.get(dbName);
		if (!CollectionUtil.checkNullOrEmpty(bec)) {
			CollectionUtil.addAllOfSubClass(bec, elementCollection);
		} else {
			this.elementMap.put(dbName, CollectionUtil.copyCollection(elementCollection));
		}
	}

	@Override
	public void removeElements(final String dbName) {
		this.elementMap.remove(dbName);
	}

	@Override
	public void removeElements(final String dbName, final Collection<BaseElement> elementCollection) {
		Collection<BaseElement> bec = this.elementMap.get(dbName);
		CollectionUtil.removeAll(bec, elementCollection);
	}

}
