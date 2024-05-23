package com.codejstudio.lim.common.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
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
public abstract class AbstractMultiValueMap<K, V> implements MultiValueMap<K, V> {

	/* variables: arrays, collections, maps, groups */

	private transient Map<K, MultiValue<V>> map;

	private transient Collection<V> valuesCollection;

	private transient Collection<Entry<K, V>> entryCollection;


	/* getters & setters */

	protected Map<K, MultiValue<V>> getMap() {
		return map;
	}

	protected void setMap(Map<K, MultiValue<V>> map) {
		this.map = map;
	}


	/* overridden methods */

	@Override
	public int size() throws LIMException {
		return values().size();
	}

	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	@Override
	public boolean containsKey(final Object key) {
		return this.map.containsKey(key);
	}

	@Override
	public boolean containsValue(final Object value) throws LIMException {
		return values().contains(value);
	}

	@Override
	public boolean contains(final Object key, final Object value) {
		MultiValue<V> vmv = get(key);
		return vmv != null && vmv.contains(value);
	}

	@Override
	public MultiValue<V> get(final Object key) {
		return this.map.get(key);
	}

	@Override
	public boolean put(final K key, final V value) throws LIMException {
		MultiValue<V> vmv = get(key);
		if (vmv == null) {
			vmv = createMultiValue();
			if (vmv.add(value)) {
				map.put(key, vmv);
				return true;
			} else {
				return false;
			}
		} else {
			return vmv.add(value);
		}
	}

	protected abstract MultiValue<V> createMultiValue() throws LIMException;


	@Override
	@SuppressWarnings("unchecked")
	public boolean put(final K key, final V... values) throws LIMException {
		return put(key, ((values == null) ? null : Arrays.asList(values)));
	}

	@Override
	public boolean put(final K key, final Collection<? extends V> valueCollection) {
		if (CollectionUtil.checkNullOrEmpty(valueCollection)) {
			return false;
		}
		return get(key).addAll(valueCollection);
	}

	@SuppressWarnings("unchecked")
	protected boolean put(final K key, final MultiValue<? extends V> values) {
		if (values == null) {
			return false;
		}

		if (!containsKey(key)) {
			this.map.put(key, (MultiValue<V>) values);
			return true;
		} else {
			MultiValue<V> vmv = get(key);
			return vmv.addAll(values);
		}
	}

	@Override
	public boolean putAll(final Map<? extends K, ? extends V> map) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(map)) {
			return false;
		}

		boolean flag = false;
		for (Map.Entry<? extends K, ? extends V> kve : map.entrySet()) {
			flag |= put(kve.getKey(), kve.getValue());
		}
		return flag;
	}

	@Override
	public boolean putAll(final MultiValueMap<? extends K, ? extends V> map) {
		if (map == null) {
			return false;
		}

		boolean flag = false;
		Set<? extends K> ks = map.keySet();
		for (K k : ks) {
			MultiValue<? extends V> vmv = map.get(k);
			flag |= put(k, vmv);
		}
		return flag;
	}

	@Override
	public MultiValue<V> remove(final Object key) {
		return this.map.remove(key);
	}

	@Override
	public boolean remove(final Object key, final Object value) {
		MultiValue<V> vmv = get(key);
		if (vmv == null) {
			return false;
		}
		boolean flag = vmv.remove(value);
		if (vmv.isEmpty()) {
			this.map.remove(key);
		}
		return flag;
	}

	@Override
	public void clear() {
		this.map.clear();
	}

	@Override
	public Set<K> keySet() {
		return this.map.keySet();
	}

	@Override
	public Collection<V> values() throws LIMException {
		Collection<V> vc = this.valuesCollection;
		if (vc == null) {
			vc = createCollectionForValues();
			for (MultiValue<V> vmv : this.map.values()) {
				vc.addAll(vmv.toCollection());
			}
			this.valuesCollection = vc;
		}
		return vc;
	}

	protected abstract Collection<V> createCollectionForValues() throws LIMException;


	@Override
	public Collection<Entry<K, V>> entries() throws LIMException {
		Collection<Entry<K, V>> kvec = this.entryCollection;
		if (kvec == null) {
			kvec = createEntryCollection();
			for (Entry<K, MultiValue<V>> kvmve : this.map.entrySet()) {
				for (V v : kvmve.getValue().toCollection()) {
					kvec.add(new MultiValueEntry<K, V>(kvmve.getKey(), v));
				}
			}
			this.entryCollection = kvec;
		}
		return kvec;
	}

	protected abstract Collection<Entry<K, V>> createEntryCollection() throws LIMException;


	@Override
	public String toString() {
		Iterator<K> kit = keySet().iterator();
		if (!kit.hasNext()) {
			return "{}";
		}

		StringBuilder sb = new StringBuilder();
		sb.append('{');
		while (true) {
			K k = kit.next();
			MultiValue<V> vmv = get(k);
			sb.append((k == this) ? "(this Map)" : k);
			sb.append('=');
			sb.append((vmv == this) ? "(this Map)" : vmv);
			if (!kit.hasNext())
				return sb.append('}').toString();
			sb.append(',').append(' ');
		}
	}



	/* inner classes */

	static class MultiValueEntry<K, V> implements Entry<K, V> {

		/* variables */

		final K key;
		V value;


		/* constructors */

		MultiValueEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}


		/* overridden methods */

		@Override
		public final K getKey() {
			return key;
		}

		@Override
		public final V setValue(final V newValue) {
			V oldValue = this.value;
			this.value = newValue;
			return oldValue;
		}

		@Override
		public final V getValue() {
			return value;
		}

		@Override
		public final String toString() {
			return this.key + "=" + this.value;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.key, this.value);
		}

		@Override
		public boolean equals(final Object object) {
			if (this == object) {
				return true;
			}
			if (object == null) {
				return false;
			}
			if (getClass() != object.getClass()) {
				return false;
			}
			MultiValueEntry<?, ?> mve = (MultiValueEntry<?, ?>) object;
			return Objects.equals(this.key, mve.key) && Objects.equals(this.value, mve.value);
		}

	}

}
