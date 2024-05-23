package com.codejstudio.algi.output;

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
public class OutputDispatcher extends AbstractOutput {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(OutputDispatcher.class);


	/* constructors */

	public OutputDispatcher() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("OutputDispatcher.run()"));

		try {
			String outputType = (String) this.dispatchedObject.getAttribute(
					DispatchedObject.ATTRIBUTE_KEY_OUTPUTTYPE);
			if (outputType == null) {
				return;
			}

			if (outputType.equals(IOutput.WEB)) {
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_OUTPUT_WEB, this.dispatchedObject);
			} else if (outputType.equals(IOutput.SYSTEM)) {
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_OUTPUT_SYSTEM, this.dispatchedObject);
			}
		} catch (ALGIException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("OutputDispatcher.run() finished."));
		}
	}

}
