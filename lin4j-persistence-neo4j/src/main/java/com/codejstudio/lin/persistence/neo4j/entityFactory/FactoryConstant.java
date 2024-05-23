package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Map;

import org.neo4j.graphdb.RelationshipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lin.persistence.neo4j.entityFactory.BaseRelationFactory.RelationType;
import com.codejstudio.lin.persistence.neo4j.entityFactory.i.INodeFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public final class FactoryConstant {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(FactoryConstant.class);


	/* variables: arrays, collections, maps, groups */

	private static final Map<String, Class<? extends INodeFactory>> ELEMENT_FACTORY_MAP;
	private static final Map<String, Class<? extends INodeFactory>> RELATION_FACTORY_MAP;


	/* initializers */

	static {
		try {
			ELEMENT_FACTORY_MAP = CollectionUtil.generateNewMap();
			RELATION_FACTORY_MAP = CollectionUtil.generateNewMap();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	static final void registerFactoryForInit(final Class<? extends BaseElement> eClazz, 
			final Class<? extends INodeFactory> fClazz) {
		ELEMENT_FACTORY_MAP.put(eClazz.getSimpleName(), fClazz);
	}

	static final void registerRelationFactoryForInit(final RelationType type, 
			final Class<? extends INodeFactory> fClazz) {
		RELATION_FACTORY_MAP.put(type.name(), fClazz);
	}


	static final Class<? extends INodeFactory> getFactory(final String name) {
		return ELEMENT_FACTORY_MAP.get(name);
	}

	static final Class<? extends INodeFactory> getRelationFactory(final RelationshipType type) {
		return RELATION_FACTORY_MAP.get(type.name());
	}
}
