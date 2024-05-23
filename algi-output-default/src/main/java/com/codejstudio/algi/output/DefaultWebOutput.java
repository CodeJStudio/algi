package com.codejstudio.algi.output;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.web.HttpClientUtil;
import com.codejstudio.algi.common.web.JSONConstant;
import com.codejstudio.lim.common.util.LogUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DefaultWebOutput extends AbstractOutput {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DefaultWebOutput.class);


	/* constructors */

	public DefaultWebOutput() {
		super();
	}


	/* overridden methods */

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		log.debug(LogUtil.wrap("DefaultWebOutput.run()"));

		try {
			JSONObject json = (JSONObject) this.dispatchedObject.getAttribute(
					DispatchedObject.ATTRIBUTE_KEY_JSON);
			String dataString = (String) this.dispatchedObject.getAttribute(
					DispatchedObject.ATTRIBUTE_KEY_DATASTRING);
			json.put(JSONConstant.JSON_REPLY, dataString);

			String callbackUrl = (String) json.get(JSONConstant.JSON_CALLBACK);
			HttpClientUtil.sendPost(callbackUrl, json);
		} catch (Exception e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("DefaultWebOutput.run() finished."));
		}
	}

}
