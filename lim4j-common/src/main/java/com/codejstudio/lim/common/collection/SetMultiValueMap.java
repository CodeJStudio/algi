package com.codejstudio.lim.common.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map.Entry;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class SetMultiValueMap<K, V> extends AbstractMultiValueMap<K, V> implements Serializable {

	/* constants */

	private static final long serialVersionUID = -5474571159746890422L;


	/* constructors */

	public SetMultiValueMap() throws LIMException {
		super();
		setMap(CollectionUtil.generateNewMap());
	}


	/* overridden methods */

	@Override
	protected MultiValue<V> createMultiValue() throws LIMException {
		return new SetMultiValue<V>();
	}

	@Override
	protected Collection<V> createCollectionForValues() throws LIMException {
		return createMultiValue().createCollectionForValues();
	}

	@Override
	protected Collection<Entry<K, V>> createEntryCollection() throws LIMException {
		return CollectionUtil.generateNewCollection();
	}

}
