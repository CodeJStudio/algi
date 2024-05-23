package com.codejstudio.lim.common.collection;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public interface MultiValue<V> {

	/* interface methods */

	int size();

	boolean isEmpty();

	boolean contains(Object value);

	boolean add(V value);

	boolean addAll(Collection<? extends V> valueCollection);

	boolean addAll(MultiValue<? extends V> values);

	boolean remove(Object value);

	Collection<V> toCollection();

	Collection<V> createCollectionForValues() throws LIMException;

}
