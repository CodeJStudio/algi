package com.codejstudio.algi.processor;

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
public class NlpProcessor extends AbstractProcessor {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(NlpProcessor.class);


	/* constructors */

	public NlpProcessor() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("NlpProcessor.run()"));

		try {
			QueueUtil.sendMessage(QueueConstant.QUEUENAME_NLP, this.dispatchedObject);
		} catch (ALGIException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("NlpProcessor.run() finished."));
		}
	}

}
