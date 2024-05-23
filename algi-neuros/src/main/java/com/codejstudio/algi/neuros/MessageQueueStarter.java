package com.codejstudio.algi.neuros;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.codejstudio.algi.StartingListener;
import com.codejstudio.algi.common.web.ApplicationContextUtil;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;
import com.codejstudio.lin.persistence.AbstractLINPersistence;
import com.codejstudio.lin.persistence.ILINPersistence;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
@Component
public class MessageQueueStarter implements StartingListener {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(MessageQueueStarter.class);


	/* variables */

	@Value("${init.autoInits}")
	private String autoInits;

	@Value("${persistence.switchOn}")
	private boolean switchOnFlag;

	@Value("${persistence.acceptSave}")
	private boolean acceptSaveFlag;

	@Value("${queueNames}")
	private String queueNames;


	/* overridden methods */

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		log.debug(LogUtil.wrap("MessageQueueStarter.onApplicationEvent()"));

		ApplicationContextUtil.setApplicationContext(event.getApplicationContext());

		ILINPersistence persistence = null;
		try {
			InitializationUtil.autoInit(autoInits);
			if (this.switchOnFlag && (persistence = AbstractLINPersistence.getPersistence()) != null) {
				persistence.setAcceptSave(this.acceptSaveFlag);
				persistence.doLoadAll();
				log.debug(LogUtil.wrap("IPersistence.doLoadAll()"));
			}
		} catch (LIMException e) {
			log.error(e.toString(), e);
		}

		String[] qna = this.queueNames.split(PropertiesLoader.SEPARATOR_MULTI_VALUES);
		for (String qn : qna) {
			try {
				MessageReceiverDispatcher mrd = (MessageReceiverDispatcher) event
						.getApplicationContext().getBean(MessageReceiverDispatcher.COMPONENT_NAME);
				mrd.setPersistence(persistence);
				mrd.connect(qn.trim());
				new Thread(mrd).start();
			} catch (Exception e) {
				log.error(LogUtil.wrap("Start the queue service failure:"), e);
			}
		}
	}

}