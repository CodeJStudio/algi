package com.codejstudio.lim.common.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.collection.MultiValueMap;
import com.codejstudio.lim.common.exception.LIMException;

/**
 * <code>CollectionUtil</code> is written to generate new objects of Collection, List or Map interface.
 * <br>The implemented class types can be configured in "common.properties".<br>
 * The default types are "<code>java.util.HashSet</code>", "<code>java.util.LinkedList</code>", 
 * "<code>java.util.HashMap</code>", "<code>java.util.concurrent.ConcurrentHashMap</code>" 
 * and "<code>com.codejstudio.lim.common.collection.SetMultiValueMap</code>".
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public final class CollectionUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(CollectionUtil.class);

	private static final String DEFAULT_NEW_SET = "java.util.HashSet";
	private static final String DEFAULT_NEW_LIST = "java.util.LinkedList";
	private static final String DEFAULT_NEW_MAP = "java.util.HashMap";
	private static final String DEFAULT_NEW_CONCURRENTMAP = "java.util.concurrent.ConcurrentHashMap";
	private static final String DEFAULT_NEW_MULTIVALUEMAP 
			= "com.codejstudio.lim.common.collection.SetMultiValueMap";

	private static final String DEFAULT_NEW_COLLECTION = DEFAULT_NEW_SET;

	private static final String NEW_COLLECTION_TYPE_KEY = "newCollectionType";
	private static final String NEW_SET_TYPE_KEY = "newSetType";
	private static final String NEW_LIST_TYPE_KEY = "newListType";
	private static final String NEW_MAP_TYPE_KEY = "newMapType";
	private static final String NEW_CONCURRENTMAP_TYPE_KEY = "newConcurrentMapType";
	private static final String NEW_MULTIVALUEMAP_TYPE_KEY = "newMultiValueMapType";


	/* static methods */

	@SuppressWarnings("unchecked")
	public static final <T> T[] generateNewArray(final Class<T> tClazz, final int length) {
		return (T[]) Array.newInstance(tClazz, length);
	}

	@SuppressWarnings("unchecked")
	public static final <T> T[] generateNewArray(final Class<T> tClazz, final int... dimensions) {
		return (T[]) Array.newInstance(tClazz, dimensions);
	}

	@SuppressWarnings("unchecked")
	public static final <T> T[] generateNewArray(final Class<T> tClazz, final Object... components) {
		if (tClazz == null || checkNullOrEmpty(components)) {
			return null;
		}

		T[] ta = (T[]) Array.newInstance(tClazz, components.length);
		int count = 0;
		for (int i = 0; i < components.length; i++) {
			if (tClazz.isInstance(components[i])) {
				ta[count++] = (T) components[i];
			}
		}
		return (count >= ta.length) ? ta : Arrays.copyOf(ta, count);
	}


	@SuppressWarnings("unchecked")
	public static final <E> Collection<E> generateNewCollection() throws LIMException {
		try {
			PropertiesClass pcl = new PropertiesClass(
					PropertiesLoader.PROPERTIES_FILENAME_COMMON, NEW_COLLECTION_TYPE_KEY, true);
			if (pcl.getClassName() != null) {
				return (Collection<E>) pcl.getTypeOfClass().newInstance();
			} else {
				return generateDefaultNewCollection();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	static final <E> Collection<E> generateDefaultNewCollection() 
			throws LIMException {
		try {
			return (Collection<E>) Class.forName(DEFAULT_NEW_COLLECTION).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <E> Set<E> generateNewSet() throws LIMException {
		try {
			PropertiesClass pcl = new PropertiesClass(
					PropertiesLoader.PROPERTIES_FILENAME_COMMON, NEW_SET_TYPE_KEY, true);
			if (pcl.getClassName() != null) {
				return (Set<E>) pcl.getTypeOfClass().newInstance();
			} else {
				return generateDefaultNewSet();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	static final <E> Set<E> generateDefaultNewSet() throws LIMException {
		try {
			return (Set<E>) Class.forName(DEFAULT_NEW_SET).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <E> List<E> generateNewList() throws LIMException {
		try {
			PropertiesClass pcl = new PropertiesClass(
					PropertiesLoader.PROPERTIES_FILENAME_COMMON, NEW_LIST_TYPE_KEY, true);
			if (pcl.getClassName() != null) {
				return (List<E>) pcl.getTypeOfClass().newInstance();
			} else {
				return generateDefaultNewList();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	static final <E> List<E> generateDefaultNewList() throws LIMException {
		try {
			return (List<E>) Class.forName(DEFAULT_NEW_LIST).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <K, V> Map<K, V> generateNewMap() throws LIMException {
		try {
			PropertiesClass pcl = new PropertiesClass(
					PropertiesLoader.PROPERTIES_FILENAME_COMMON, NEW_MAP_TYPE_KEY, true);
			if (pcl.getClassName() != null) {
				return (Map<K, V>) pcl.getTypeOfClass().newInstance();
			} else {
				return generateDefaultNewMap();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	static final <K, V> Map<K, V> generateDefaultNewMap() throws LIMException {
		try {
			return (Map<K, V>) Class.forName(DEFAULT_NEW_MAP).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <K, V> ConcurrentMap<K, V> generateNewConcurrentMap() throws LIMException {
		try {
			PropertiesClass pcl = new PropertiesClass(
					PropertiesLoader.PROPERTIES_FILENAME_COMMON, NEW_CONCURRENTMAP_TYPE_KEY, true);
			if (pcl.getClassName() != null) {
				return (ConcurrentMap<K, V>) pcl.getTypeOfClass().newInstance();
			} else {
				return generateDefaultNewConcurrentMap();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	static final <K, V> ConcurrentMap<K, V> generateDefaultNewConcurrentMap() throws LIMException {
		try {
			return (ConcurrentMap<K, V>) Class.forName(DEFAULT_NEW_CONCURRENTMAP).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <K, V> MultiValueMap<K, V> generateNewMultiValueMap() throws LIMException {
		try {
			PropertiesClass pcl = new PropertiesClass(
					PropertiesLoader.PROPERTIES_FILENAME_COMMON, NEW_MULTIVALUEMAP_TYPE_KEY, true);
			if (pcl.getClassName() != null) {
				return (MultiValueMap<K, V>) pcl.getTypeOfClass().newInstance();
			} else {
				return generateDefaultNewMultiValueMap();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}

	@SuppressWarnings("unchecked")
	static final <K, V> MultiValueMap<K, V> generateDefaultNewMultiValueMap() throws LIMException {
		try {
			return (MultiValueMap<K, V>) Class.forName(DEFAULT_NEW_MULTIVALUEMAP).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw LIMException.getLIMException(e);
		}
	}



	@SuppressWarnings("unchecked")
	public static final <T> T[] convertToArray(final Class<T> tClazz, final Object objectArray) 
			throws LIMException {
		Class<?> objcl;
		if (tClazz == null || objectArray == null || !(objcl = objectArray.getClass()).isArray()) {
			return null;
		}
		if (tClazz.equals(objcl.getComponentType())) {
			return (T[]) objectArray;
		}

		int l = Array.getLength(objectArray);
		T[] ta = generateNewArray(tClazz, l);
		int count = 0;
		for (int i = 0; i < l; i++) {
			Object obj = Array.get(objectArray, i);
			if (tClazz.isInstance(obj)) {
				ta[count++] = (T) obj;
			}
		}
		return (count >= ta.length) ? ta : Arrays.copyOf(ta, count);
	}



	@SuppressWarnings("unchecked")
	public static final <T, E> Collection<T> convertToCollection(final Class<T> tClazz, 
			final E... eObjects) throws LIMException {
		if (tClazz == null || checkNullOrEmpty(eObjects)) {
			return null;
		}
		if (tClazz.isAssignableFrom(eObjects.getClass().getComponentType())) {
			return (Collection<T>) convertToCollectionOfSuperClass(eObjects);
		}

		Collection<T> tc = generateNewCollection();
		addAllWithNoCheck(tClazz, tc, eObjects);
		return checkNullOrEmpty(tc) ? null : tc;
	}

	@SuppressWarnings("unchecked")
	public static final <T, E> Collection<T> convertToCollection(final Class<T> tClazz, 
			final Class<E> eClazz, final Collection<E> eCollection) throws LIMException {
		return (tClazz != null && tClazz.isAssignableFrom(eClazz)) 
				? (Collection<T>) convertToCollectionOfSuperClass(eCollection) 
				: convertToCollection(tClazz, eCollection);
	}

	@SuppressWarnings("unchecked")
	public static final <T, E> Collection<T> convertToCollection(final Class<T> tClazz, 
			final Collection<E> eCollection) throws LIMException {
		if (tClazz == null || checkNullOrEmpty(eCollection)) {
			return null;
		}

		Collection<T> tc = null;
		try {
			tc = (Collection<T>) eCollection.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.warn("Fail to generate new instance of the Class(" + eCollection.getClass() + ") "
					+ "of the collection, replacing it with the default Collection type.");
			tc = generateNewCollection();
		}
		addAllWithNoCheck(tClazz, tc, eCollection);
		return checkNullOrEmpty(tc) ? null : tc;
	}

	@SuppressWarnings("unchecked")
	public static final <F, S extends F> Collection<F> convertToCollectionOfSuperClass(final S... sObjects) 
			throws LIMException {
		if (checkNullOrEmpty(sObjects)) {
			return null;
		}

		Collection<F> fc = generateNewCollection();
		Collections.addAll(fc, sObjects);
		return fc;
	}

	@SuppressWarnings("unchecked")
	public static final <F, S extends F> Collection<F> convertToCollectionOfSuperClass(
			final Collection<S> sCollection) throws LIMException {
		if (checkNullOrEmpty(sCollection)) {
			return null;
		}

		Collection<F> fc = null;
		try {
			fc = (Collection<F>) sCollection.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.warn("Fail to generate new instance of the Class(" + sCollection.getClass() + ") "
					+ "of the collection, replacing it with the default Collection type.");
			fc = generateNewCollection();
		}
		fc.addAll(sCollection);
		return fc;
	}

	public static final <E> Collection<E> copyCollection(final Collection<E> eCollection) 
			throws LIMException {
		return convertToCollectionOfSuperClass(eCollection);
	}


	@SuppressWarnings("unchecked")
	public static final <T, E> Set<T> convertToSet(final Class<T> tClazz, final E... eObjects) 
			throws LIMException {
		if (tClazz == null || checkNullOrEmpty(eObjects)) {
			return null;
		}
		if (tClazz.isAssignableFrom(eObjects.getClass().getComponentType())) {
			return (Set<T>) convertToSetOfSuperClass(eObjects);
		}

		Set<T> ts = generateNewSet();
		addAllWithNoCheck(tClazz, ts, eObjects);
		return checkNullOrEmpty(ts) ? null : ts;
	}

	@SuppressWarnings("unchecked")
	public static final <T, E> Set<T> convertToSet(final Class<T> tClazz, final Class<E> eClazz, 
			final Set<E> eSet) throws LIMException {
		return (tClazz != null && tClazz.isAssignableFrom(eClazz)) 
				? (Set<T>) convertToSetOfSuperClass(eSet) : convertToSet(tClazz, eSet);
	}

	@SuppressWarnings("unchecked")
	public static final <T, E> Set<T> convertToSet(final Class<T> tClazz, final Set<E> eSet) 
			throws LIMException {
		if (tClazz == null || checkNullOrEmpty(eSet)) {
			return null;
		}

		Set<T> ts = null;
		try {
			ts = (Set<T>) eSet.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.warn("Fail to generate new instance of the Class(" + eSet.getClass() + ") of the set, "
					+ "replacing it with the default Set type.");
			ts = generateNewSet();
		}
		addAllWithNoCheck(tClazz, ts, eSet);
		return checkNullOrEmpty(ts) ? null : ts;
	}

	@SuppressWarnings("unchecked")
	public static final <F, S extends F> Set<F> convertToSetOfSuperClass(final S... sObjects) 
			throws LIMException {
		if (checkNullOrEmpty(sObjects)) {
			return null;
		}

		Set<F> fs = generateNewSet();
		Collections.addAll(fs, sObjects);
		return fs;
	}

	@SuppressWarnings("unchecked")
	public static final <F, S extends F> Set<F> convertToSetOfSuperClass(final Set<S> sSet) 
			throws LIMException {
		if (checkNullOrEmpty(sSet)) {
			return null;
		}

		Set<F> fs = null;
		try {
			fs = (Set<F>) sSet.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.warn("Fail to generate new instance of the Class(" + sSet.getClass() + ") of the set, "
					+ "replacing it with the default Set type.");
			fs = generateNewSet();
		}
		fs.addAll(sSet);
		return fs;
	}

	public static final <E> Set<E> copySet(final Set<E> eSet) throws LIMException {
		return convertToSetOfSuperClass(eSet);
	}


	@SuppressWarnings("unchecked")
	public static final <T, E> List<T> convertToList(final Class<T> tClazz, final E... eObjects) 
			throws LIMException {
		if (tClazz == null || checkNullOrEmpty(eObjects)) {
			return null;
		}
		if (tClazz.isAssignableFrom(eObjects.getClass().getComponentType())) {
			return (List<T>) convertToListOfSuperClass(eObjects);
		}

		List<T> tl = generateNewList();
		addAllWithNoCheck(tClazz, tl, eObjects);
		return checkNullOrEmpty(tl) ? null : tl;
	}

	@SuppressWarnings("unchecked")
	public static final <T, E> List<T> convertToList(final Class<T> tClazz, final Class<E> eClazz, 
			final List<E> eList) throws LIMException {
		return (tClazz != null && tClazz.isAssignableFrom(eClazz)) 
				? (List<T>) convertToListOfSuperClass(eList) : convertToList(tClazz, eList);
	}

	public static final <T, E> List<T> convertToList(final Class<T> tClazz, 
			final Collection<E> eCollection) throws LIMException {
		if (tClazz == null || checkNullOrEmpty(eCollection)) {
			return null;
		}

		List<T> tl = generateNewList();
		addAllWithNoCheck(tClazz, tl, eCollection);
		return checkNullOrEmpty(tl) ? null : tl;
	}

	@SuppressWarnings("unchecked")
	public static final <T, E> List<T> convertToList(final Class<T> tClazz, final List<E> eList) 
			throws LIMException {
		if (tClazz == null || checkNullOrEmpty(eList)) {
			return null;
		}

		List<T> tl = null;
		try {
			tl = (List<T>) eList.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.warn("Fail to generate new instance of the Class(" + eList.getClass() + ") of the list, "
					+ "replacing it with the default List type.");
			tl = generateNewList();
		}
		addAllWithNoCheck(tClazz, tl, eList);
		return checkNullOrEmpty(tl) ? null : tl;
	}

	@SuppressWarnings("unchecked")
	public static final <F, S extends F> List<F> convertToListOfSuperClass(final S... sObjects) 
			throws LIMException {
		if (checkNullOrEmpty(sObjects)) {
			return null;
		}

		List<F> fl = generateNewList();
		Collections.addAll(fl, sObjects);
		return fl;
	}

	public static final <F, S extends F> List<F> convertToListOfSuperClass(final Collection<S> sCollection) 
			throws LIMException {
		if (checkNullOrEmpty(sCollection)) {
			return null;
		}

		List<F> fl = generateNewList();
		fl.addAll(sCollection);
		return fl;
	}

	@SuppressWarnings("unchecked")
	public static final <F, S extends F> List<F> convertToListOfSuperClass(final List<S> sList) 
			throws LIMException {
		if (checkNullOrEmpty(sList)) {
			return null;
		}

		List<F> fl = null;
		try {
			fl = (List<F>) sList.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.warn("Fail to generate new instance of the Class(" + sList.getClass() + ") of the list, "
					+ "replacing it with the default List type.");
			fl = generateNewList();
		}
		fl.addAll(sList);
		return fl;
	}

	public static final <E> List<E> copyList(final List<E> eList) throws LIMException {
		return convertToListOfSuperClass(eList);
	}



	@SuppressWarnings("unchecked")
	public static final <E> Collection<E> getIntersection(final Collection<E> eCollection, 
			final E... targetObjects) throws LIMException {
		return getIntersection(eCollection, ((targetObjects == null) ? null : Arrays.asList(targetObjects)));
	}

	@SuppressWarnings("unchecked")
	public static final <E> Collection<E> getIntersection(final Collection<E> eCollection, 
			final Collection<E> targetCollection) throws LIMException {
		if (checkNullOrEmpty(eCollection) || checkNullOrEmpty(targetCollection)) {
			return null;
		}

		Collection<E> ec = null;
		try {
			ec = (Collection<E>) eCollection.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			ec = generateNewCollection();
			log.warn("Fail to generate new instance of the Class(" + eCollection.getClass() + ") "
					+ "of the collection, replacing it with the default Collection type.");
		}
		for (E e : targetCollection) {
			if (eCollection.contains(e)) {
				ec.add(e);
			}
		}
		return ec;
	}


	@SuppressWarnings("unchecked")
	public static final <E> Collection<E> getUnion(final Collection<E> eCollection, 
			final E... targetObjects) throws LIMException {
		return getUnion(eCollection, ((targetObjects == null) ? null : Arrays.asList(targetObjects)));
	}

	@SuppressWarnings("unchecked")
	public static final <E> Collection<E> getUnion(final Collection<E> eCollection, 
			final Collection<E> targetCollection) throws LIMException {
		if (checkNullOrEmpty(eCollection) || checkNullOrEmpty(targetCollection)) {
			return eCollection;
		}

		Collection<E> ec = null;
		try {
			ec = (Collection<E>) eCollection.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			ec = generateNewCollection();
			log.warn("Fail to generate new instance of the Class(" + eCollection.getClass() + ") "
					+ "of the collection, replacing it with the default Collection type.");
		}
		ec.addAll(eCollection);
		for (E e : targetCollection) {
			if (!eCollection.contains(e)) {
				ec.add(e);
			}
		}
		return ec;
	}


	@SuppressWarnings("unchecked")
	public static final <E> Collection<E> getRelativeComplement(final Collection<E> eCollection, 
			final E... targetObjects) throws LIMException {
		return getRelativeComplement(eCollection, 
				((targetObjects == null) ? null : Arrays.asList(targetObjects)));
	}

	@SuppressWarnings("unchecked")
	public static final <E> Collection<E> getRelativeComplement(final Collection<E> eCollection, 
			final Collection<E> targetCollection) throws LIMException {
		if (checkNullOrEmpty(eCollection) || checkNullOrEmpty(targetCollection)) {
			return eCollection;
		}

		Collection<E> ec = null;
		try {
			ec = (Collection<E>) eCollection.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			ec = generateNewCollection();
			log.warn("Fail to generate new instance of the Class(" + eCollection.getClass() + ") "
					+ "of the collection, replacing it with the default Collection type.");
		}
		ec.addAll(eCollection);
		ec.removeAll(targetCollection);
		return ec;
	}

	@SuppressWarnings("unchecked")
	public static final <E> List<E> getRelativeComplement(final List<E> eList, final E... targetObjects) 
			throws LIMException {
		return getRelativeComplement(eList, ((targetObjects == null) ? null : Arrays.asList(targetObjects)));
	}

	@SuppressWarnings("unchecked")
	public static final <E> List<E> getRelativeComplement(final List<E> eList, 
			final List<E> targetList) throws LIMException {
		if (checkNullOrEmpty(eList) || checkNullOrEmpty(targetList)) {
			return eList;
		}

		List<E> el = null;
		try {
			el = (List<E>) eList.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			el = generateNewList();
			log.warn("Fail to generate new instance of the Class(" + eList.getClass() + ") of the list, "
					+ "replacing it with the default List type.");
		}
		el.addAll(eList);
		el.removeAll(targetList);
		return el;
	}



	@SuppressWarnings("unchecked")
	public static final <E> boolean checkNullOrEmpty(final E... eObjects) {
		if (eObjects == null || eObjects.length == 0) {
			return true;
		}
		for (E e : eObjects) {
			if (e != null) {
				return false;
			}
		}
		return true;
	}

	public static final <E> boolean checkNullOrEmpty(final Collection<E> eCollection) {
		if (eCollection == null || eCollection.size() == 0) {
			return true;
		}
		for (E e : eCollection) {
			if (e != null) {
				return false;
			}
		}
		return true;
	}

	public static final <K, V> boolean checkNullOrEmpty(final Map<K, V> kvMap) {
		if (kvMap == null || kvMap.size() == 0) {
			return true;
		}
		Set<K> ks = kvMap.keySet();
		for (K k : ks) {
			if (k != null && kvMap.get(k) != null) {
				return false;
			}
		}
		return true;
	}

	public static final <K, V> boolean checkNullOrEmpty(final MultiValueMap<K, V> kvMVMap) 
			throws LIMException {
		if (kvMVMap == null || kvMVMap.size() == 0) {
			return true;
		}
		Set<K> ks = kvMVMap.keySet();
		for (K k : ks) {
			if (k != null && kvMVMap.get(k) != null) {
				return false;
			}
		}
		return true;
	}



	public static final <E> boolean contains(final E[] eArray, final E targetObject) {
		return indexOf(eArray, targetObject) >= 0;
	}

	public static final <E> boolean contains(final Collection<E> eCollection, E targetObject) {
		return !checkNullOrEmpty(eCollection) && eCollection.contains(targetObject);
	}

	@SuppressWarnings("unchecked")
	public static final <E> boolean allContains(final E[] eArray, final E... targetObjects) {
		return allContains(((eArray == null) ? null : Arrays.asList(eArray)), 
				((targetObjects == null) ? null : Arrays.asList(targetObjects)));
	}

	public static final <E> boolean allContains(final Collection<E> eCollection, 
			final Collection<E> targetCollection) {
		if (checkNullOrEmpty(eCollection)) {
			return false;
		}
		if (checkNullOrEmpty(targetCollection)) {
			return true;
		}

		for (E e : targetCollection) {
			if (!eCollection.contains(e)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static final <E> boolean orderlyContains(final E[] eArray, final E... targetObjects) {
		if (checkNullOrEmpty(eArray)) {
			return false;
		}
		if (checkNullOrEmpty(targetObjects)) {
			return true;
		}

		int i = 0;
		for (E e : eArray) {
			if (i == targetObjects.length) {
				return true;
			} else if (e.equals(targetObjects[i])) {
				i++;
			}
		}
		return i >= targetObjects.length;
	}

	@SuppressWarnings("unchecked")
	public static final <E> boolean onlyContains(final Collection<? extends E> eCollection, 
			final E... targetObjects) {
		return onlyContains(eCollection, ((targetObjects == null) ? null : Arrays.asList(targetObjects)));
	}

	public static final <E> boolean onlyContains(final Collection<? extends E> eCollection, 
			final Collection<? extends E> targetCollection) {
		if (checkNullOrEmpty(eCollection) || checkNullOrEmpty(targetCollection) 
				|| eCollection.size() != targetCollection.size()) {
			return false;
		}
		for (E e : targetCollection) {
			if (!eCollection.contains(e)) {
				return false;
			}
		}
		return true;
	}


	public static final <E> boolean sizeEquals(final E[] eArray1, final E[] eArray2) {
		return (checkNullOrEmpty(eArray1) && checkNullOrEmpty(eArray2)) 
				|| (!checkNullOrEmpty(eArray1) && !checkNullOrEmpty(eArray2) 
						&& eArray1.length == eArray2.length);
	}


	public static final <E> boolean arrayEquals(final E[] eArray1, final E[] eArray2) {
		if (!sizeEquals(eArray1, eArray2)) {
			return false;
		}

		for (int i = 0; (eArray1 != null && i < eArray1.length); i++) {
			if (!Objects.equals(eArray1[i], eArray2[i])) {
				return false;
			}
		}
		return true;
	}

	public static final <E> boolean arrayEquals(final E[] eArray1, final E[] eArray2, final int length) {
		return arrayEquals(eArray1, 0, eArray2, 0, length);
	}

	public static final <E> boolean arrayEquals(final E[] eArray1, final int start1, final E[] eArray2, 
			final int start2, final int length) {
		if (checkNullOrEmpty(eArray1) || checkNullOrEmpty(eArray2) 
				|| start1 < 0 || (start1 + length >= eArray1.length) 
				|| start2 < 0 || (start2 + length >= eArray2.length)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (!Objects.equals(eArray1[start1 + i], eArray2[start2 + i])) {
				return false;
			}
		}
		return true;
	}



	public static final <E> int indexOf(final E[] eArray, final E targetObject) {
		return indexOf(eArray, targetObject, 0);
	}

	public static final <E> int indexOf(final E[] eArray, final E targetObject, int startIndex) {
		if (checkNullOrEmpty(eArray)) {
			return -1;
		}
		if (startIndex < 0) {
			startIndex = 0;
		}
		for (int i = startIndex; i < eArray.length; i++) {
			if ((targetObject == null && eArray[i] == null) 
					|| (targetObject != null && targetObject.equals(eArray[i]))) {
				return i;
			}
		}
		return -1;
	}

	public static final <E> int lastIndexOf(final E[] eArray, final E targetObject) {
		return lastIndexOf(eArray, targetObject, Integer.MAX_VALUE);
	}

	public static final <E> int lastIndexOf(final E[] eArray, final E targetObject, int startIndex) {
		if (checkNullOrEmpty(eArray)) {
			return -1;
		}
		if (startIndex < 0) {
			return -1;
		} else if (startIndex >= eArray.length) {
			startIndex = eArray.length - 1;
		}
		for (int i = startIndex; i >= 0; i--) {
			if ((targetObject == null && eArray[i] == null) 
					|| (targetObject != null && targetObject.equals(eArray[i]))) {
				return i;
			}
		}
		return -1;
	}


	@SuppressWarnings("unchecked")
	public static final <E> int[] indexOf(final E[] eArray, final E... targetObjects) {
		if (checkNullOrEmpty(eArray) || checkNullOrEmpty(targetObjects)) {
			return null;
		}

		int[] ia = new int[targetObjects.length];
		for (int i = 0; i < ia.length; i++) {
			ia[i] = -1;
		}

		int i = 0;
		for (int j = 0; (j < eArray.length && i < targetObjects.length); j++) {
			if (eArray[j] != null && eArray[j].equals(targetObjects[i])) {
				ia[i] = j;
				i++;
			}
		}

		for (int j = 0; j < ia.length; j++) {
			if (ia[j] == -1) {
				return null;
			}
		}
		return ia;
	}



	@SuppressWarnings("unchecked")
	public static final <F, S extends F> boolean addAllOfSubClass(Collection<F> fCollection, 
			final S... targetObjects) {
		return addAllOfSubClass(fCollection, 
				((targetObjects == null) ? null : Arrays.asList(targetObjects)));
	}

	public static final <F, S extends F> boolean addAllOfSubClass(Collection<F> fCollection, 
			final Collection<S> targetCollection) {
		if (fCollection == null || checkNullOrEmpty(targetCollection) || fCollection == targetCollection) {
			return false;
		}
		return fCollection.addAll(targetCollection);
	}

	@SuppressWarnings("unchecked")
	public static final <T, E> boolean addAll(final Class<T> tClazz, Collection<T> tCollection, 
			final E... targetObjects) {
		if (targetObjects == null) {
			return false;
		}
		return addAll(tClazz, tCollection, (Class<E>) targetObjects.getClass().getComponentType(), 
				Arrays.asList(targetObjects));
	}

	@SuppressWarnings("unchecked")
	public static final <T, E> boolean addAll(final Class<T> tClazz, Collection<T> tCollection, 
			final Class<E> eClazz, final Collection<E> targetCollection) {
		if (tClazz != null && tClazz.isAssignableFrom(eClazz)) {
			return addAllOfSubClass(tCollection, (Collection<T>) targetCollection);
		}
		if (tClazz == null || tCollection == null || checkNullOrEmpty(targetCollection) 
				|| tCollection == targetCollection) {
			return false;
		}

		return addAllWithNoCheck(tClazz, tCollection, targetCollection);
	}

	@SuppressWarnings("unchecked")
	private static final <T, E> boolean addAllWithNoCheck(final Class<T> tClazz, Collection<T> tCollection, 
			final E... targetObjects) {
		boolean flag = true;
		for (E e : targetObjects) {
			if (tClazz.isInstance(e)) {
				flag &= tCollection.add((T) e);
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	private static final <T, E> boolean addAllWithNoCheck(final Class<T> tClazz, Collection<T> tCollection, 
			final Collection<E> targetCollection) {
		boolean flag = true;
		for (E e : targetCollection) {
			if (tClazz.isInstance(e)) {
				flag &= tCollection.add((T) e);
			}
		}
		return flag;
	}




	@SuppressWarnings("unchecked")
	public static final <E> boolean removeAll(Collection<E> eCollection, final E... targetObjects) {
		return removeAll(eCollection, ((targetObjects == null) ? null : Arrays.asList(targetObjects)));
	}

	public static final <E> boolean removeAll(Collection<E> eCollection, 
			final Collection<E> targetCollection) {
		if (eCollection == null || checkNullOrEmpty(targetCollection)) {
			return false;
		}
		return eCollection.removeAll(targetCollection);
	}


	@SuppressWarnings("unchecked")
	public static final <K, V> boolean removeAll(Map<K, V> kvMap, final K... targetKeys) {
		return removeAll(kvMap, ((targetKeys == null) ? null : Arrays.asList(targetKeys)));
	}

	public static final <K, V> boolean removeAll(Map<K, V> kvMap, final Collection<K> targetKeyCollection) {
		if (kvMap == null || checkNullOrEmpty(targetKeyCollection)) {
			return false;
		}

		for (K k : targetKeyCollection) {
			kvMap.remove(k);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static final <K, V> boolean removeAll(Map<K, V> kvMap, final Entry<K, V>... targetEntries) 
			throws LIMException {
		return removeAll(kvMap, convertToSetOfSuperClass(targetEntries));
	}

	public static final <K, V> boolean removeAll(Map<K, V> kvMap, final Set<Entry<K, V>> targetEntrySet) {
		if (kvMap == null || checkNullOrEmpty(targetEntrySet)) {
			return false;
		}
		return kvMap.entrySet().removeAll(targetEntrySet);
	}


	@SuppressWarnings("unchecked")
	public static final <E> List<E> removeDuplicate(List<E> eList) throws LIMException {
		if (checkNullOrEmpty(eList)) {
			return eList;
		}

		List<E> el = null;
		try {
			el = eList.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			el = generateNewList();
			log.warn("Fail to generate new instance of the Class(" + eList.getClass() + ") of the list, "
					+ "replacing it with the default List type.");
		}
		for (E e : eList) {
			if (!el.contains(e)) {
				el.add(e);
			}
		}
		return el;
	}


	public static final <E> Collection<E> removeNull(Collection<E> eCollection) throws LIMException {
		Collection<E> ec = copyCollection(eCollection);
		while (ec.remove(null));
		return ec;
	}

	@SuppressWarnings("unchecked")
	public static final <E> List<E> removeNull(E... eObjects) throws LIMException {
		List<E> el = convertToListOfSuperClass(eObjects);
		while (el.remove(null));
		return el;
	}

	public static final <E> List<E> removeNull(List<E> eList) throws LIMException {
		List<E> el = copyList(eList);
		while (el.remove(null));
		return el;
	}

}
