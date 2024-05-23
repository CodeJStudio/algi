package com.codejstudio.algi.preprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.algi.common.queue.QueueConstant;
import com.codejstudio.algi.common.queue.QueueUtil;
import com.codejstudio.lim.common.util.LogUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DirectOutputPreprocessor extends AbstractPreprocessor {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DirectOutputPreprocessor.class);


	/* constructors */

	public DirectOutputPreprocessor() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("DirectOutputPreprocessor.run()"));

		try {
			QueueUtil.sendMessage(QueueConstant.QUEUENAME_OUTPUT, this.dispatchedObject);
		} catch (ALGIException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("DirectOutputPreprocessor.run() finished."));
		}
	}

}
