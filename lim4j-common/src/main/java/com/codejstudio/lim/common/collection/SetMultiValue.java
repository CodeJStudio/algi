package com.codejstudio.lim.common.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class SetMultiValue<V> implements MultiValue<V>, Serializable {

	/* constants */

	private static final long serialVersionUID = 3560596889518358675L;


	/* variables */

	private transient Set<V> vSet;


	/* constructors */

	public SetMultiValue() throws LIMException {
		super();
		this.vSet = CollectionUtil.generateNewSet();
	}


	/* overridden methods */

	@Override
	public int size() {
		return this.vSet.size();
	}

	@Override
	public boolean isEmpty() {
		return this.vSet.isEmpty();
	}

	@Override
	public boolean contains(final Object value) {
		return this.vSet.contains(value);
	}

	@Override
	public boolean add(final V value) {
		return this.vSet.add(value);
	}

	@Override
	public boolean addAll(final Collection<? extends V> valueCollection) {
		return this.vSet.addAll(valueCollection);
	}

	@Override
	public boolean addAll(final MultiValue<? extends V> values) {
		if (values == null) {
			return false;
		}
		return addAll(values.toCollection());
	}

	@Override
	public boolean remove(final Object value) {
		return this.vSet.remove(value);
	}

	@Override
	public Collection<V> toCollection() {
		return this.vSet;
	}

	@Override
	public Collection<V> createCollectionForValues() throws LIMException {
		return CollectionUtil.generateNewSet();
	}

	@Override
	public String toString() {
		Iterator<V> vit = this.vSet.iterator();
		if (!vit.hasNext()) {
			return "[]";
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		while (true) {
			V v = vit.next();
			sb.append((v == this) ? "(this Set)" : v);
			if (!vit.hasNext()) {
				return sb.append(']').toString();
			}
			sb.append(',').append(' ');
		}
	}

}
