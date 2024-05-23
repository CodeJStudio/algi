package com.codejstudio.lin.persistence.neo4j.entityFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.relation.RelationGroup;
import com.codejstudio.lin.persistence.neo4j.entityFactory.i.IGroupFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class RelationGroupFactory extends BaseRelationFactory implements IGroupFactory {

	/* variables */

	private static RelationGroupFactory FACTORY;


	/* constructors */

	protected RelationGroupFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(RelationGroup.class), 
					RelationGroupFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static RelationGroupFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new RelationGroupFactory();
		}
		return FACTORY;
	}

}
