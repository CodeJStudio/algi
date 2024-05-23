package com.codejstudio.lim.common.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface MultiValueMap<K, V> {

	/* interface methods */

	int size() throws LIMException;

	boolean isEmpty();

	boolean containsKey(Object key);

	boolean containsValue(Object value) throws LIMException;

	boolean contains(Object key, Object value);

	MultiValue<V> get(Object key);

	boolean put(K key, V value) throws LIMException;

	@SuppressWarnings("unchecked")
	boolean put(K key, V... values) throws LIMException;

	boolean put(K key, Collection<? extends V> valueCollection);

	boolean putAll(Map<? extends K, ? extends V> map) throws LIMException;

	boolean putAll(MultiValueMap<? extends K, ? extends V> map);

	MultiValue<V> remove(Object key);

	boolean remove(Object key, Object value);

	void clear();

	Set<K> keySet();

	Collection<V> values() throws LIMException;

	Collection<Entry<K, V>> entries() throws LIMException;

}
