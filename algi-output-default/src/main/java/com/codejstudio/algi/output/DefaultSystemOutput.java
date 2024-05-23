package com.codejstudio.algi.output;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.lim.common.util.LogUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DefaultSystemOutput extends AbstractOutput {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DefaultSystemOutput.class);


	/* constructors */

	public DefaultSystemOutput() {
		super();
		setOutputStream(System.out);
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("DefaultSystemOutput.run()"));

		String dataString = (String) this.dispatchedObject.getAttribute(
				DispatchedObject.ATTRIBUTE_KEY_DATASTRING);

		OutputStreamWriter osw = new OutputStreamWriter(this.os);
		try {
			osw.write("DefaultSystemOutput (dataString): \n" + dataString + "\n");
			osw.flush();
		} catch (IOException e) {
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("DefaultSystemOutput.run() finished."));
		}
	}

}
