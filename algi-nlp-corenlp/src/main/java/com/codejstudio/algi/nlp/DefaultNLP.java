package com.codejstudio.algi.nlp;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.queue.QueueConstant;
import com.codejstudio.algi.common.queue.QueueUtil;
import com.codejstudio.algi.processor.IProcessor;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.LogUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DefaultNLP extends AbstractNLP {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DefaultNLP.class);


	/* constructors */

	public DefaultNLP() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("DefaultNLP.run()"));

		try {
			String dataString = (String) this.dispatchedObject.getAttribute(
					DispatchedObject.ATTRIBUTE_KEY_DATASTRING);
			String language = (String) this.dispatchedObject.getAttribute(
					DispatchedObject.ATTRIBUTE_KEY_LANGUAGE);

			List<String> segList = NlpUtil.getSegmentation(dataString, language);
			if (segList instanceof Serializable) {
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_NLP_SEG, 
						(Serializable) segList);
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_PROCESSORTYPE, 
						IProcessor.AFTERNLP);
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_PROCESSOR, this.dispatchedObject);
			} else {
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_DATASTRING, 
						((segList == null) ? null : ("segments: \n" + segList)));
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_OUTPUT, this.dispatchedObject);

			}
		} catch (LIMException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("DefaultNLP.run() finished."));
		} 
	}

}
