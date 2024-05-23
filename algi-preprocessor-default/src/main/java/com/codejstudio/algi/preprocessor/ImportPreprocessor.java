package com.codejstudio.algi.preprocessor;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.common.DispatchedObject;
import com.codejstudio.algi.common.queue.QueueConstant;
import com.codejstudio.algi.common.queue.QueueUtil;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.LogUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.importer.Importer;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class ImportPreprocessor extends AbstractPreprocessor {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(ImportPreprocessor.class);


	/* constructors */

	public ImportPreprocessor() {
		super();
	}


	/* overridden methods */

	@Override
	public void run() {
		log.debug(LogUtil.wrap("ImportPreprocessor.run()"));

		final String importFile = (String) this.dispatchedObject
				.getAttribute(DispatchedObject.ATTRIBUTE_KEY_IMPORTFILE);
		try {
			final String[] stra = StringUtil.splitByAnyWhitespace(importFile);
			if (CollectionUtil.checkNullOrEmpty(stra)) {
				return;
			}

			boolean successFlag = false;
			for (String str : stra) {
				final File file;
				if (StringUtil.isEmpty(str) || !(file = new File(str)).exists() 
						|| !file.isFile() || !file.canWrite()) {
					continue;
				}

				Importer importer = new Importer(file);
				int count = importer.doImport();
				log.info("Importing \"" + str +"\" finished. " 
						+ count + " records have been successfully imported.");

				List<Object> el;
				if ((CollectionUtil.contains(stra, "-save") || CollectionUtil.contains(stra, "-s")) 
						&& !CollectionUtil.checkNullOrEmpty(el = importer.getElementList())) {
					this.dispatchedObject.putAttribute(DispatchedObject.ATTRIBUTE_KEY_PERSISTENCE_OBJECT, 
							el.toArray());
					QueueUtil.sendMessage(QueueConstant.QUEUENAME_PERSISTENCE, this.dispatchedObject);
				}

				successFlag = true;
				break;
			}

			if (!successFlag) {
				log.error("Importing \"" + importFile +"\" failed.");
				log.error("\"" + importFile +"\" is not a valid file path.");
			}
		} catch (LIMException e) {
			log.error("Importing \"" + importFile +"\" failed.");
			log.error(e.toString(), e);
		} finally {
			log.debug(LogUtil.wrap("ImportPreprocessor.run() finished."));
		}
	}

}
