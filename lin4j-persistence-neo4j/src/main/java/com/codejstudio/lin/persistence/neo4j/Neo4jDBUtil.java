package com.codejstudio.lin.persistence.neo4j;

import java.io.File;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.graphdb.Entity;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;
import com.codejstudio.lin.persistence.DatabaseConstant;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public final class Neo4jDBUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(Neo4jDBUtil.class);


	/* variables: arrays, collections, maps, groups */

	private static Map<String, GraphDatabaseService> graphDbMap;


	/* static methods */

	public static final GraphDatabaseService getDatabase(final String dbName) throws LIMException {
		if (graphDbMap == null) {
			graphDbMap = CollectionUtil.generateNewMap();
		}
		GraphDatabaseService graphDb = graphDbMap.get(dbName);
		if (graphDb == null) {
			String dbPath = PropertiesLoader.getProperty(
					DatabaseConstant.PROPERTIES_FILENAME_DB_CONFIG, dbName);
			graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File(dbPath));
			graphDbMap.put(dbName, graphDb);
		}
		return graphDb;
	}


	public static final void registerShutdownHook(final GraphDatabaseService graphDatabase) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				log.debug(LogUtil.wrap("Database is shutting down."));
				graphDatabase.shutdown();
			}
		});
	}


	public static final Collection<Entity> loadAllEntityCollection(final GraphDatabaseService graphDatabase) 
			throws LIMException {
		Collection<Entity> enc = CollectionUtil.generateNewCollection();
		Iterator<Node> anit = graphDatabase.getAllNodes().iterator();
		while (anit.hasNext()) {
			enc.add(anit.next());
		}
		Iterator<Relationship> arit = graphDatabase.getAllRelationships().iterator();
		while (arit.hasNext()) {
			enc.add(arit.next());
		}
		return enc;
	}


	public static final void batchDeleteNodesByLabel(final GraphDatabaseService graphDatabase, 
			final Label label) {
		Iterator<Node> nit = graphDatabase.findNodes(label);
		while (nit.hasNext()) {
			deleteSingleNode(nit.next());
		}
	}

	public static final void deleteSingleNode(final Node node) {
		Iterator<Relationship> rit = node.getRelationships().iterator();
		while (rit.hasNext()) {
			Relationship r = rit.next();
			r.delete();
		}
		node.delete();
	}


	public static final void prettyPrintAll(final GraphDatabaseService graphDatabase, 
			PrintStream printStream) {
		printStream.println("Start query: ");
		Iterator<Label> alit = graphDatabase.getAllLabels().iterator();
		while (alit.hasNext()) {
			prettyPrintByLabel(graphDatabase, alit.next(), printStream);
		}
		printStream.println("End query.");
		printStream.flush();
	}

	public static final void prettyPrintByLabel(final GraphDatabaseService graphDatabase, final Label label, 
			PrintStream printStream) {
		printStream.println("Label: " + label.name() + "; ");
		Iterator<Node> nit = graphDatabase.findNodes(label);
		while (nit.hasNext()) {
			prettyPrintNode(nit.next(), printStream);
		}
		printStream.println();
	}

	public static final void prettyPrintNode(final Node node, PrintStream printStream) {
		printStream.print("Node_id: " + node.getId() + "; ");

		Iterator<Label> lit = node.getLabels().iterator();
		printStream.print("Node_labels: ");
		while (lit.hasNext()) {
			Label l = lit.next();
			printStream.print(l.name() + ", ");
		}
		printStream.print("; ");

		prettyPrintProperties(node, printStream);

		Iterator<Relationship> rit = node.getRelationships().iterator();
		printStream.print("Node_relationships: ");
		while (rit.hasNext()) {
			prettyPrintRelationship(rit.next(), printStream);
		}
		printStream.println("; ");
	}

	public static final void prettyPrintRelationship(final Relationship relationship, 
			PrintStream printStream) {
		printStream.print("(" + relationship.getStartNodeId() + ")-" + "[" + relationship.getId() 
				+ ":" + relationship.getType() + "; ");
		prettyPrintProperties(relationship, printStream);
		printStream.print("]->" + "(" + relationship.getEndNodeId() + "), ");
	}

	public static final void prettyPrintProperties(final PropertyContainer propertyContainer, 
			PrintStream printStream) {
		Iterator<String> pkit = propertyContainer.getPropertyKeys().iterator();
		String clazzName = propertyContainer.getClass().getSimpleName();
		clazzName = clazzName.endsWith("Proxy") ? clazzName.substring(0, clazzName.length()-5) : clazzName;
		printStream.print(clazzName + "_properties:{ ");
		while (pkit.hasNext()) {
			String pk = pkit.next();
			printStream.print(pk + ": " + propertyContainer.getProperty(pk) + ", ");
		}
		printStream.print("}; ");
	}

}
