package com.codejstudio.algi.persistence;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.algi.common.queue.QueueConstant;
import com.codejstudio.algi.common.queue.QueueUtil;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lin.persistence.DatabaseConstant;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DefaultPersistence extends AbstractPersistence {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DefaultPersistence.class);


	/* constructors */

	public DefaultPersistence() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("DefaultPersistence.run()"));

		try {
			Object pstobj;
			if (this.persistence == null || !this.persistence.isAcceptSave() 
					|| (pstobj = this.dispatchedObject.getAttribute(
							DispatchedObject.ATTRIBUTE_KEY_PERSISTENCE_OBJECT)) == null) {
				return;
			}

			if (pstobj instanceof BaseElement) {
				this.persistence.doCreateOrUpdate(DatabaseConstant.DB_LIN, true, 
						(BaseElement) pstobj);
			} else if (pstobj instanceof Collection<?>) {
				Collection<BaseElement> bec = CollectionUtil.convertToCollection(BaseElement.class, 
						(Collection<?>) pstobj);
				if (!CollectionUtil.checkNullOrEmpty(bec)) {
					this.persistence.doCreateOrUpdate(DatabaseConstant.DB_LIN, true, bec);
				}
			} else if (pstobj.getClass().isArray()) {
				BaseElement[] bea = CollectionUtil.convertToArray(BaseElement.class, pstobj);
				if (!CollectionUtil.checkNullOrEmpty(bea)) {
					this.persistence.doCreateOrUpdate(DatabaseConstant.DB_LIN, true, bea);
				}
			}
		} catch (LIMException e) {
			log.error(e.toString(), e);
		} finally {
			try {
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_OUTPUT, this.dispatchedObject);
			} catch (ALGIException e) {
				log.error(e.toString(), e);
			}
			log.debug(LogUtil.wrap("DefaultPersistence.run() finished."));
		}
	}

}
