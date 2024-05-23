package com.codejstudio.lin.persistence.neo4j.entityFactory.i;

import java.util.Map;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.relation.BaseRelation;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public interface IRelationshipFactory {

	/* interface methods */

	Relationship createRelationship(Map<String, Node> nodeMap, BaseElement element);

	BaseRelation loadRelationship(Relationship relationship) throws LIMException;

	BaseRelation loadRelationship(Relationship relationship, boolean initTypeFlag) throws LIMException;

}
