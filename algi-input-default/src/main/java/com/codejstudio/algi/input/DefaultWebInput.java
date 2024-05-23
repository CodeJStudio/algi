package com.codejstudio.algi.input;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.algi.common.queue.QueueConstant;
import com.codejstudio.algi.common.queue.QueueUtil;
import com.codejstudio.algi.common.web.JSONConstant;
import com.codejstudio.algi.output.IOutput;
import com.codejstudio.algi.preprocessor.IPreprocessor;
import com.codejstudio.lim.common.util.CloseUtil;
import com.codejstudio.lim.common.util.LogUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DefaultWebInput extends AbstractInput {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DefaultWebInput.class);


	/* variables */

	protected ObjectOutputStream oos;


	/* constructors */

	public DefaultWebInput() {
		super();
	}


	/* class methods */

	public synchronized ObjectOutputStream getObjectOutputStream() throws IOException {
		PipedInputStream pis = new PipedInputStream();
		PipedOutputStream pos = new PipedOutputStream(pis);
		this.oos = new ObjectOutputStream(pos);
		this.is = new ObjectInputStream(pis);
		return this.oos;
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("DefaultWebInput.run()"));

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error(e.toString(), e);
				break;
			}

			if (this.is == null) {
				continue;
			}

			try {
				ObjectInputStream ois = (ObjectInputStream) this.is;
				Object obj = ois.readObject();
				log.debug(LogUtil.wrap("Object obj = ois.readObject();"));
				if (!(obj instanceof JSONObject)) {
					continue;
				}

				log.debug(LogUtil.wrap("obj instanceof JSONObject"));
				JSONObject json = (JSONObject) obj;
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_JSON, json);
				String text = (String) json.get(JSONConstant.JSON_TEXT);
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_DATASTRING, text);
				String lang = (String) json.get(JSONConstant.JSON_LANGUAGE);
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_LANGUAGE, lang);
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_OUTPUTTYPE, IOutput.WEB);
				this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_PREPROCESSORTYPE, 
//						IPreprocessor.DIRECT_OUTPUT
						IPreprocessor.CLASSIC
						);
				QueueUtil.sendMessage(QueueConstant.QUEUENAME_PREPROCESSOR, this.dispatchedObject);
			} catch (IOException | ALGIException | ClassNotFoundException e) {
				log.error(e.toString(), e);
			} finally {
				CloseUtil.quietlyClose(this.is);
				this.is = null;
				log.debug(LogUtil.wrap("DefaultWebInput.run() finished."));
			}
		}

	}

}
