package com.codejstudio.algi.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.algi.common.queue.QueueConstant;
import com.codejstudio.algi.common.queue.QueueUtil;
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
public class DefaultSystemInput extends AbstractInput {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(DefaultSystemInput.class);

	private static final String SYSTEM_INPUT_COMMAND_IMPORT = "import";


	/* constructors */

	public DefaultSystemInput() {
		super();
		setInputStream(System.in);
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("DefaultSystemInput.run()"));

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(this.is));
			log.debug(LogUtil.wrap("Please input your words: "));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String dataString;
				if ((SYSTEM_INPUT_COMMAND_IMPORT + " ").equalsIgnoreCase(
						line.substring(0, SYSTEM_INPUT_COMMAND_IMPORT.length() + 1))) {
					String importFile = line.substring(SYSTEM_INPUT_COMMAND_IMPORT.length()).trim();
					this.dispatchedObject.putAttribute(
							DispatchedObject.ATTRIBUTE_KEY_IMPORTFILE, importFile);
					this.dispatchedObject.putAttribute(
							DispatchedObject.ATTRIBUTE_KEY_PREPROCESSORTYPE, IPreprocessor.IMPORT);
					QueueUtil.sendMessage(QueueConstant.QUEUENAME_PREPROCESSOR, this.dispatchedObject);
				} else {
					log.debug(LogUtil.wrap("What you input is: " + line));
					dataString = line;

					this.dispatchedObject.putAttribute(
							DispatchedObject.ATTRIBUTE_KEY_DATASTRING, dataString);
					this.dispatchedObject.putAttribute(
							DispatchedObject.ATTRIBUTE_KEY_OUTPUTTYPE, IOutput.SYSTEM);
					this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_PREPROCESSORTYPE, 
							IPreprocessor.DIRECT_OUTPUT
//							IPreprocessor.CLASSIC
							);
					QueueUtil.sendMessage(QueueConstant.QUEUENAME_PREPROCESSOR, this.dispatchedObject);
				}
			}
		} catch (IOException | ALGIException e) {
			log.error(e.toString(), e);
		} finally {
			CloseUtil.quietlyClose(br);
			CloseUtil.quietlyClose(this.is);
			log.debug(LogUtil.wrap("DefaultSystemInput.run() finished."));
		}
	}

}
