package com.codejstudio.lin.persistence;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.neo4j.graphdb.Entity;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.util.PojoElementUtil;
import com.codejstudio.lin.persistence.neo4j.Neo4jDBUtil;
import com.codejstudio.lin.persistence.neo4j.entityFactory.AbstractFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class Neo4jPersistence extends AbstractLINPersistence {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(Neo4jPersistence.class);


	/* constructors */

	protected Neo4jPersistence() {
		super();
	}


	/* overridden methods */

	@Override
	public void doCreateOrUpdate(final String dbName, final BaseElement... elements) throws LIMException {
		doCreateOrUpdate(dbName, false, ((elements == null) ? null : Arrays.asList(elements)));
	}

	@Override
	public void doCreateOrUpdate(final String dbName, final Collection<BaseElement> elementCollection) 
			throws LIMException {
		doCreateOrUpdate(dbName, false, elementCollection);
	}

	@Override
	public void doCreateOrUpdate(final String dbName, final boolean subElementsFlag, 
			final BaseElement... elements) throws LIMException {
		doCreateOrUpdate(dbName, subElementsFlag, ((elements == null) ? null : Arrays.asList(elements)));
	}

	@Override
	public void doCreateOrUpdate(final String dbName, final boolean subElementsFlag, 
			final Collection<BaseElement> elementCollection) throws LIMException {
		GraphDatabaseService graphDb = Neo4jDBUtil.getDatabase(dbName);
		try (Transaction tx = graphDb.beginTx()) {
			AbstractFactory.create(graphDb, subElementsFlag, 
					this.idsMap, this.uniqueNeo4jEntityIdCollection, elementCollection);
			addElements(dbName, elementCollection);
			tx.success();
		}
	}


	@Override
	public void doCreate(final String dbName, final BaseElement... elements) throws LIMException {
		doCreate(dbName, false, ((elements == null) ? null : Arrays.asList(elements)));
	}

	@Override
	public void doCreate(final String dbName, final Collection<BaseElement> elementCollection) 
			throws LIMException {
		doCreate(dbName, false, elementCollection);
	}

	@Override
	public void doCreate(final String dbName, final boolean subElementsFlag, final BaseElement... elements) 
			throws LIMException {
		doCreate(dbName, subElementsFlag, ((elements == null) ? null : Arrays.asList(elements)));
	}

	@Override
	public void doCreate(final String dbName, final boolean subElementsFlag, 
			final Collection<BaseElement> elementCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return;
		}

		Collection<BaseElement> bec = subElementsFlag 
				? PojoElementUtil.hierarchyToPlain(elementCollection) : elementCollection;
		Collection<BaseElement> notContainCollection = CollectionUtil.generateNewCollection();
		for (BaseElement be : bec) {
			if (!super.containElement(dbName, be)) {
				notContainCollection.add(be);
			}
		}

		GraphDatabaseService graphDb = Neo4jDBUtil.getDatabase(dbName);
		try (Transaction tx = graphDb.beginTx()) {
			AbstractFactory.create(graphDb, false, this.idsMap, this.uniqueNeo4jEntityIdCollection, 
					notContainCollection);
			addElements(dbName, notContainCollection);
			tx.success();
		}
	}


	@Override
	public void doLoadAll() throws LIMException {
		for (String dbName : DatabaseConstant.ALL_DB_NAME_ARRAY) {
			try {
				doLoadAll(dbName);
			} catch (RuntimeException e) {
				log.error(e.toString(), e);
			}
		}
	}

	@Override
	public void doLoadAll(final String dbName) throws LIMException {
		log.debug(LogUtil.wrap("doLoadAll(\"" + dbName + "\")"));
		GraphDatabaseService graphDb = Neo4jDBUtil.getDatabase(dbName);
		try (Transaction tx = graphDb.beginTx()) {
			Collection<Entity> aenc = Neo4jDBUtil.loadAllEntityCollection(graphDb);
			List<BaseElement> elementList = AbstractFactory.load(
					aenc, this.idsMap, this.uniqueNeo4jEntityIdCollection);

			List<GenericActionableElement> gael 
					= CollectionUtil.convertToList(GenericActionableElement.class, elementList);
			List<GenericElement> gel = CollectionUtil.convertToList(GenericElement.class, elementList);
			Root root = new Root(gael, gel, true);
			root.redecorate();
			addElements(dbName, CollectionUtil.convertToListOfSuperClass(root.getPojoElementList()));
		}
	}


	@Override
	public void doDeleteAll() throws LIMException {
		for (String dbName : DatabaseConstant.ALL_DB_NAME_ARRAY) {
			try {
				doDeleteAll(dbName);
			} catch (RuntimeException e) {
				log.error(e.toString(), e);
			}
		}
	}

	@Override
	public void doDeleteAll(final String dbName) throws LIMException {
		GraphDatabaseService graphDb = Neo4jDBUtil.getDatabase(dbName);
		try (Transaction tx = graphDb.beginTx()) {
			ResourceIterator<Label> aliuit = graphDb.getAllLabelsInUse().iterator();
			while (aliuit.hasNext()) {
				Label label = aliuit.next();
				Neo4jDBUtil.batchDeleteNodesByLabel(graphDb, label);
			}
			tx.success();
		}
	}

	@Override
	public void doDelete(final String dbName, final BaseElement... elements) throws LIMException {
		doDelete(dbName, ((elements == null) ? null : Arrays.asList(elements)));
	}

	@Override
	public void doDelete(final String dbName, final Collection<BaseElement> elementCollection) 
			throws LIMException {
		GraphDatabaseService graphDb = Neo4jDBUtil.getDatabase(dbName);
		try (Transaction tx = graphDb.beginTx()) {
			for (BaseElement be : elementCollection) {
				Neo4jDBUtil.deleteSingleNode(
						graphDb.getNodeById(AbstractFactory.getNodeId(this.idsMap.get(be.getId()))));
			}
			tx.success();
		}
	}

}
