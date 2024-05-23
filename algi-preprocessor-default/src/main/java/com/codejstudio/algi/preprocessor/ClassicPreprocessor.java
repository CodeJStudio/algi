package com.codejstudio.algi.preprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.algi.common.queue.QueueConstant;
import com.codejstudio.algi.common.queue.QueueUtil;
import com.codejstudio.algi.processor.IProcessor;
import com.codejstudio.lim.common.util.LogUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ClassicPreprocessor extends AbstractPreprocessor {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(ClassicPreprocessor.class);


	/* constructors */

	public ClassicPreprocessor() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("ClassicPreprocessor.run()"));

		try {
			this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_PROCESSORTYPE, 
					IProcessor.CLASSIC);
			QueueUtil.sendMessage(QueueConstant.QUEUENAME_PROCESSOR, this.dispatchedObject);
		} catch (ALGIException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("ClassicPreprocessor.run() finished."));
		}
	}

}
