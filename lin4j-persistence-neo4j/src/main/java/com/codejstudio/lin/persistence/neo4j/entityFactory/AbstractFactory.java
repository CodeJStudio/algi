package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Entity;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lin.persistence.neo4j.entityFactory.i.IGroupFactory;
import com.codejstudio.lin.persistence.neo4j.entityFactory.i.INodeFactory;
import com.codejstudio.lin.persistence.neo4j.entityFactory.i.IRelationshipFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public abstract class AbstractFactory {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(AbstractFactory.class);

	public static final String NODE_KEY = "N";
	public static final String RELATIONSHIP_KEY = "R";


	/* static methods */

	protected static final void registerFactoryForInit(
			final Collection<Class<? extends BaseElement>> subPojoClazzCollection, 
			final Class<? extends INodeFactory> fClazz) {
		if (CollectionUtil.checkNullOrEmpty(subPojoClazzCollection)) {
			return;
		}
		for (Class<? extends BaseElement> subPojoClazz : subPojoClazzCollection) {
			FactoryConstant.registerFactoryForInit(subPojoClazz, fClazz);
		}
	}


	protected static final AbstractFactory getFactory(final Class<? extends BaseElement> elementClazz) {
		Class<? extends INodeFactory> nfcl = (elementClazz == null) 
				? null : FactoryConstant.getFactory(elementClazz.getSimpleName());
		log.debug(LogUtil.wrap("elementClazz.getSimpleName(): " 
				+ ((elementClazz == null) ? null : elementClazz.getSimpleName()) 
				+ "; factoryName: " + ((nfcl == null) ? null : nfcl.getSimpleName())));
		return getFactoryInstance(nfcl);
	}

	protected static final AbstractFactory getFactory(final Label label) {
		return getFactoryInstance((label == null) ? null : FactoryConstant.getFactory(label.name()));
	}

	protected static final AbstractFactory getFactory(final RelationshipType type) {
		return getFactoryInstance(FactoryConstant.getRelationFactory(type));
	}

	static final AbstractFactory getFactoryInstance(final Class<? extends INodeFactory> factoryClazz) {
		try {
			return (factoryClazz == null) ? null : (AbstractFactory) factoryClazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.error(e.toString(), e);
//			throw LINException.getLINException(e);
		}
		return null;
	}


	public static final void create(final GraphDatabaseService graphDatabase, final BaseElement... elements) 
			throws LIMException {
		create(graphDatabase, true, null, null, ((elements == null) ? null : Arrays.asList(elements)));
	}

	public static final void create(final GraphDatabaseService graphDatabase, 
			final Collection<BaseElement> elementCollection) throws LIMException {
		create(graphDatabase, true, null, null, elementCollection);
	}

	public static final void create(final GraphDatabaseService graphDatabase, final boolean subElementsFlag, 
			final BaseElement... elements) throws LIMException {
		create(graphDatabase, subElementsFlag, null, null, 
				((elements == null) ? null : Arrays.asList(elements)));
	}

	public static final void create(final GraphDatabaseService graphDatabase, final boolean subElementsFlag, 
			final Collection<BaseElement> elementCollection) throws LIMException {
		create(graphDatabase, subElementsFlag, null, null, elementCollection);
	}

	public static final void create(final GraphDatabaseService graphDatabase, final boolean subElementsFlag, 
			final Map<String, String> idsMap, final Collection<String> uniqueNeo4jEntityIdCollection, 
			final BaseElement... elements) throws LIMException {
		create(graphDatabase, subElementsFlag, idsMap, uniqueNeo4jEntityIdCollection, 
				((elements == null) ? null : Arrays.asList(elements)));
	}

	public static final void create(final GraphDatabaseService graphDatabase, final boolean subElementsFlag, 
			Map<String, String> idsMap, Collection<String> uniqueNeo4jEntityIdCollection, 
			final Collection<BaseElement> elementCollection) throws LIMException {
		Map<String, Node> nodeMap = CollectionUtil.generateNewMap();
		Collection<GenericElement> relationElementCollection = CollectionUtil.generateNewCollection();

		createSubNodes(graphDatabase, subElementsFlag, idsMap, uniqueNeo4jEntityIdCollection, 
				elementCollection, nodeMap, relationElementCollection);

		for (GenericElement ge : relationElementCollection) {
			if (idsMap != null && idsMap.containsKey(ge.getId())) {
				continue;
			}
			AbstractFactory factory = getFactory(ge.getClass());
			if (factory instanceof IRelationshipFactory) {
				Relationship r = ((IRelationshipFactory)factory).createRelationship(nodeMap, ge);
				if (r != null) {
					String uenid = getUniqueEntityId(r);
					if (uenid == null 
							|| (uniqueNeo4jEntityIdCollection != null 
									&& uniqueNeo4jEntityIdCollection.contains(uenid))) {
						continue;
					}
					if (uniqueNeo4jEntityIdCollection != null && idsMap != null) {
						uniqueNeo4jEntityIdCollection.add(uenid);
						idsMap.put(ge.getId(), uenid);
					}
				}
			}
		}
	}

	private static void createSubNodes(final GraphDatabaseService graphDatabase, 
			final boolean subElementsFlag, 
			final Map<String, String> idsMap, final Collection<String> uniqueNeo4jEntityIdCollection, 
			final Collection<? extends BaseElement> elementCollection, final Map<String, Node> nodeMap, 
			final Collection<GenericElement> relationElementCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return;
		}
		for (BaseElement element : elementCollection) {
			createSubNodes(graphDatabase, subElementsFlag, idsMap, uniqueNeo4jEntityIdCollection, 
					element, nodeMap, relationElementCollection);
		}
	}

	private static void createSubNodes(final GraphDatabaseService graphDatabase, 
			final boolean subElementsFlag, Map<String, String> idsMap, 
			Collection<String> uniqueNeo4jEntityIdCollection, final BaseElement element, 
			Map<String, Node> nodeMap, Collection<GenericElement> relationElementCollection) 
					throws LIMException {
		if (element == null || nodeMap.containsKey(element.getId()) 
				|| relationElementCollection.contains(element) 
				|| (idsMap != null && idsMap.containsKey(element.getId()))) {
			return;
		}

		Node n = null;
		AbstractFactory f = getFactory(element.getClass());
		if (f instanceof IGroupFactory && f instanceof INodeFactory) {
			n = ((INodeFactory) f).createNode(graphDatabase, element);
		} else if (f instanceof IRelationshipFactory && element instanceof GenericElement) {
			relationElementCollection.add((GenericElement) element);
		} else if (f instanceof INodeFactory) {
			n = ((INodeFactory) f).createNode(graphDatabase, element);
		}

		if (n != null) {
			String uenid = getUniqueEntityId(n);
			if (uenid == null 
					|| (uniqueNeo4jEntityIdCollection != null 
							&& uniqueNeo4jEntityIdCollection.contains(uenid))) {
				return;
			}
			if (uniqueNeo4jEntityIdCollection != null && idsMap != null) {
				uniqueNeo4jEntityIdCollection.add(uenid);
				idsMap.put(element.getId(), uenid);
			}
			nodeMap.put(element.getId(), n);
		}

		if (subElementsFlag && element instanceof GenericElement) {
			createSubNodes(graphDatabase, subElementsFlag, idsMap, uniqueNeo4jEntityIdCollection, 
					((GenericElement)element).getInnerElementCollection(), nodeMap, 
					relationElementCollection);
		}
	}



	public static final List<BaseElement> load(final Collection<Entity> entityCollection) 
			throws LIMException {
		return load(entityCollection, null, null);
	}

	public static final List<BaseElement> load(final Collection<Entity> entityCollection, 
			Map<String, String> idsMap, Collection<String> uniqueNeo4jEntityIdCollection) 
					throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(entityCollection)) {
			return null;
		}

		List<BaseElement> bel = CollectionUtil.generateNewList();
		for (Entity entity : entityCollection) {
			String uenid = getUniqueEntityId(entity);
			if (uenid == null 
					|| (uniqueNeo4jEntityIdCollection != null 
							&& uniqueNeo4jEntityIdCollection.contains(uenid))) {
				continue;
			}

			BaseElement element = null;
			AbstractFactory f;
			if (entity instanceof Node) {
				Node n = (Node) entity;
				Iterator<Label> lit = n.getLabels().iterator();
				Label l = !lit.hasNext() ? null : lit.next();
				f = (l == null) ? null : getFactory(l);
				if (f instanceof INodeFactory) {
					element = ((INodeFactory) f).loadNode(n, l);
				}
			} else if (entity instanceof Relationship) {
				Relationship r = (Relationship) entity;
				RelationshipType rt = r.getType();
				f = (rt == null) ? null : getFactory(rt);
				if (f instanceof IRelationshipFactory) {
					element = ((IRelationshipFactory) f).loadRelationship(r);
				}
			}

			if (element == null) {
				continue;
			}
			String eid = element.getId();
			if (idsMap != null && idsMap.containsKey(eid)) {
				continue;
			}
			if (uniqueNeo4jEntityIdCollection != null && idsMap != null) {
				uniqueNeo4jEntityIdCollection.add(uenid);
				idsMap.put(eid, uenid);
			}
			bel.add(element);
		}
		return bel;
	}


	public static final String getUniqueEntityId(final Entity entity) {
		String type = (entity instanceof Node) ? NODE_KEY 
				: ((entity instanceof Relationship) ? RELATIONSHIP_KEY : null);
		return (type == null) ? null : type + WordSeparator.UNDERSCORE.getCharacter() + entity.getId();
	}

	public static final long getNodeId(final String uniqueEntityId) throws LIMException {
		try {
			if (uniqueEntityId != null 
					&& uniqueEntityId.startsWith(NODE_KEY + WordSeparator.UNDERSCORE.getCharacter())) {
				String id = uniqueEntityId
						.substring((NODE_KEY + WordSeparator.UNDERSCORE.getCharacter()).length());
				return Long.valueOf(id);
			}
		} catch (RuntimeException e) {
			throw LIMException.getLIMException(e);
		}
		return -1;
	}

}
