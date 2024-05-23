package com.codejstudio.algi.persistence;

import com.codejstudio.algi.common.AbstractALGIRunnable;
import com.codejstudio.lin.persistence.ILINPersistence;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractPersistence extends AbstractALGIRunnable implements IPersistence {

	/* variables */

	protected ILINPersistence persistence;


	/* getters & setters */

	@Override
	public ILINPersistence getPersistence() {
		return persistence;
	}

	@Override
	public void setPersistence(ILINPersistence persistence) {
		this.persistence = persistence;
	}

}
