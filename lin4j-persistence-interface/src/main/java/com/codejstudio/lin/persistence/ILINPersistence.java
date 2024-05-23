package com.codejstudio.lin.persistence;

import java.util.Collection;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public interface ILINPersistence {

	/* constants */

	static final String PROPERTIES_FILENAME_PERSISTENCE = "lin4j-persistence";
	static final String PROPERTY_KEY_PERSISTENCE_DEFAULT = "lin4j-persistence.default";


	/* getters & setters */

	boolean isAcceptSave();

	void setAcceptSave(boolean acceptSave);


	/* CRUD for arrays, collections, maps, groups: elements */

	Map<String, Collection<BaseElement>> getAllElementMap();

	Collection<BaseElement> getElementCollection(String dbName);

	boolean containElement(String dbName, BaseElement element);

	void addElements(String dbName, Collection<BaseElement> elementCollection) throws LIMException;

	void removeElements(String dbName);

	void removeElements(String dbName, Collection<BaseElement> elementCollection);


	/* interface methods */

	void doCreateOrUpdate(String dbName, BaseElement... elements) throws LIMException;

	void doCreateOrUpdate(String dbName, Collection<BaseElement> elementCollection) throws LIMException;

	void doCreateOrUpdate(String dbName, boolean subElementsFlag, BaseElement... elements) 
			throws LIMException;

	void doCreateOrUpdate(String dbName, boolean subElementsFlag, Collection<BaseElement> elementCollection) 
			throws LIMException;


	void doCreate(String dbName, BaseElement... elements) throws LIMException;

	void doCreate(String dbName, Collection<BaseElement> elementCollection) throws LIMException;

	void doCreate(String dbName, boolean subElementsFlag, BaseElement... elements) throws LIMException;

	void doCreate(String dbName, boolean subElementsFlag, Collection<BaseElement> elementCollection) 
			throws LIMException;


	void doLoadAll() throws LIMException;

	void doLoadAll(String dbName) throws LIMException;


	void doDeleteAll() throws LIMException;

	void doDeleteAll(String dbName) throws LIMException;

	void doDelete(String dbName, BaseElement... elements) throws LIMException;

	void doDelete(String dbName, Collection<BaseElement> elementCollection) throws LIMException;

}
