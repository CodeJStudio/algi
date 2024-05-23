package com.codejstudio.lin.persistence.neo4j.entityFactory.i;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public interface INodeFactory {

	/* interface methods */

	Node createNode(GraphDatabaseService graphDatabase, BaseElement element) throws LIMException;

	BaseElement loadNode(Node node, Label label) throws LIMException;

	BaseElement loadNode(Node node, Label label, boolean initTypeFlag) throws LIMException;

}
