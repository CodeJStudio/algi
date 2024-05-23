package com.codejstudio.lim.pojo.i;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.RelationGroup;

/**
 * IRelationable.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public interface IRelationable {

	/* getters & setters */

	/**
	 * The implemented classes should also contain:<br/>
	 * private RelationGroup relationGroup;<br/>
	 * private void initRelationGroup();<br/>
	 * private void destroyRelationGroup();
	 */
	RelationGroup getRelationGroup();


	/* CRUD for arrays, collections, maps, groups: relations */

	boolean containRelation(BaseRelation relation);

	boolean containRelation(String id);

	boolean addRelation(BaseRelation... relations) throws LIMException;

	boolean addRelation(Collection<BaseRelation> relationCollection) throws LIMException;

	boolean removeRelation(String id);


	/* interface methods */

	boolean equivalentTo(IRelationable relationable) throws LIMException;

}
