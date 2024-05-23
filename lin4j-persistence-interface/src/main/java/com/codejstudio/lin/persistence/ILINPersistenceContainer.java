package com.codejstudio.lin.persistence;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public interface ILINPersistenceContainer {

	/* interface methods */

	ILINPersistence getPersistence();

	void setPersistence(ILINPersistence persistence);

}
