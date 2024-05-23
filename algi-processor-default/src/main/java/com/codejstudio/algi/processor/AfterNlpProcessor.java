package com.codejstudio.algi.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.symbolAction.SimpleSentenceThinkAction;
import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.queue.QueueConstant;
import com.codejstudio.algi.common.queue.QueueUtil;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.pojo.InformationElement;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class AfterNlpProcessor extends AbstractProcessor {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(AfterNlpProcessor.class);


	/* constructors */

	public AfterNlpProcessor() {
		super();
	}


	/* overridden methods */

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		log.debug(LogUtil.wrap("AfterNlpProcessor.run()"));

		try {
			List<String> segmentList = (List<String>) this.dispatchedObject
					.getAttribute(DispatchedObject.ATTRIBUTE_KEY_NLP_SEG);
			if (CollectionUtil.checkNullOrEmpty(segmentList)) {
				return;
			}

			String str = String.join(",", segmentList.toArray(new String[0]));
			Action ssta = new SimpleSentenceThinkAction(str);
			Object[] sstaOutputArray = ssta.execute();

			if (CollectionUtil.checkNullOrEmpty(sstaOutputArray)) {
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_DATASTRING, null);
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_OUTPUT, this.dispatchedObject);
				return;
			}

			for (Object obj : sstaOutputArray) {
				if (obj instanceof InformationElement) {
					this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_PERSISTENCE_OBJECT, 
							(InformationElement) obj);
					this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_DATASTRING, 
							((InformationElement) obj).getDescription());
					QueueUtil.sendMessage(QueueConstant.QUEUENAME_PERSISTENCE, this.dispatchedObject);
				} else {
					this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_DATASTRING, 
							obj.toString());
					QueueUtil.sendMessage(QueueConstant.QUEUENAME_OUTPUT, this.dispatchedObject);
				}
			}
		} catch (LIMException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("AfterNlpProcessor.run() finished."));
		}
	}

}
