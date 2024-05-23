package com.codejstudio.algi.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
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
public class ProcessorDispatcher extends AbstractProcessor {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(ProcessorDispatcher.class);


	/* constructors */

	public ProcessorDispatcher() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("ProcessorDispatcher.run()"));

		try {
			String processorType = (String) this.dispatchedObject.getAttribute(
					DispatchedObject.ATTRIBUTE_KEY_PROCESSORTYPE);
			if (processorType == null) {
				return;
			}

			if (processorType.equals(IProcessor.CLASSIC)) {
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_PROCESSOR_NLP, this.dispatchedObject);
			} else if (processorType.equals(IProcessor.AFTERNLP)) {
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_PROCESSOR_AFTERNLP, this.dispatchedObject);
			}
		} catch (ALGIException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("ProcessorDispatcher.run() finished."));
		}
	}

}
