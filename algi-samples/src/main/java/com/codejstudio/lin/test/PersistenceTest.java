package com.codejstudio.lin.test;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.util.ActionableElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lin.persistence.DatabaseConstant;
import com.codejstudio.lin.persistence.neo4j.Neo4jDBUtil;
import com.codejstudio.lin.persistence.neo4j.entityFactory.AbstractFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class PersistenceTest {

	/* constants */

	public static final String DB = DatabaseConstant.DB_LIN;

	public static final GraphDatabaseService GRAPH_DB;


	/* initializers */

	static {
		try {
			String dbPath = PropertiesLoader.getProperty(DatabaseConstant.PROPERTIES_FILENAME_DB_CONFIG, DB);
			GRAPH_DB = new GraphDatabaseFactory().newEmbeddedDatabase(new File(dbPath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		try (Transaction tx = GRAPH_DB.beginTx()) {
			System.out.println();

//			delete(GRAPH_DB);
//			System.out.println();

			prettyPrintAll(GRAPH_DB);
			System.out.println();

			doDbUnmarshal(GRAPH_DB);
			System.out.println();

			tx.success();
		}
		Neo4jDBUtil.registerShutdownHook(GRAPH_DB);
	}

	static void delete(final GraphDatabaseService graphDb) throws LIMException {
		System.out.println("Start delete: ");

		Set<String> ecsns = PojoElementClassConstant.getElementClassSimpleNameSet();
		for (String simpleName : ecsns) {
			Neo4jDBUtil.batchDeleteNodesByLabel(graphDb, Label.label(simpleName));
		}

		Set<String> acsns = ActionableElementClassConstant.getActionableClassSimpleNameSet();
		for (String simpleName : acsns) {
			Neo4jDBUtil.batchDeleteNodesByLabel(graphDb, Label.label(simpleName));
		}

		System.out.println("End delete.");
	}

	static void prettyPrintAll(final GraphDatabaseService graphDb) {
		System.out.println("prettyPrintAll(graphDb(\"" + DB + "\"), System.out);");
		Neo4jDBUtil.prettyPrintAll(graphDb, System.out);
	}

	static void doDbUnmarshal(final GraphDatabaseService graphDb) throws LIMException {
		System.out.println("doDbUnmarshal(graphDb(\"" + DB + "\"));");

		Collection<org.neo4j.graphdb.Entity> entities = Neo4jDBUtil.loadAllEntityCollection(graphDb);
		List<BaseElement> elementList = AbstractFactory.load(entities);

		List<GenericActionableElement> gael 
				= CollectionUtil.convertToList(GenericActionableElement.class, elementList);
		List<GenericElement> gel = CollectionUtil.convertToList(GenericElement.class, elementList);
		Root root = new Root(gael, gel, true);
		root.redecorate();
		System.out.println();
		root.marshalToXml(System.out);
	}

}
