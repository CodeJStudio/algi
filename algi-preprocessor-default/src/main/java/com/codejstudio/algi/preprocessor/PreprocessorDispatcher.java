package com.codejstudio.algi.preprocessor;

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
public class PreprocessorDispatcher extends AbstractPreprocessor {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(PreprocessorDispatcher.class);


	/* constructors */

	public PreprocessorDispatcher() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("PreprocessorDispatcher.run()"));

		try {
			String preprocessorType = (String) this.dispatchedObject.getAttribute(
					DispatchedObject.ATTRIBUTE_KEY_PREPROCESSORTYPE);
			if (preprocessorType == null) {
				return;
			}

			if (preprocessorType.equals(IPreprocessor.DIRECT_OUTPUT)) {
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_PREPROCESSOR_DIRECT_OUTPUT, 
						this.dispatchedObject);
			} else if (preprocessorType.equals(IPreprocessor.CLASSIC)) {
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_PREPROCESSOR_CLASSIC, this.dispatchedObject);
			} else if (preprocessorType.equals(IPreprocessor.IMPORT)) {
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_PREPROCESSOR_IMPORT, this.dispatchedObject);
			}
		} catch (ALGIException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("PreprocessorDispatcher.run() finished."));
		}
	}

}
