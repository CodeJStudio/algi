package com.codejstudio.lim.importer;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ReflectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.importer.ImportFile.Record;
import com.codejstudio.lim.pojo.util.ActionableElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementEnumConstant;
import com.codejstudio.lim.pojo.util.SymbolConstant;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class Importer {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(Importer.class);


	/* variables */

	private ImportFile importFile;


	/* variables: arrays, collections, maps, groups */

	private Map<Long, Object> ridElementMap = CollectionUtil.generateNewMap();
	private Map<String, Object> easElementMap = CollectionUtil.generateNewMap();
	private List<Object> elementList = CollectionUtil.generateNewList();


	/* constructors */

	public Importer(File file) throws LIMException {
		this.importFile = ImportFile.unmarshalFromJson(file);
	}

	public Importer(InputStream is) throws LIMException {
		this.importFile = ImportFile.unmarshalFromJson(is);
	}

	public Importer(Reader reader) throws LIMException {
		this.importFile = ImportFile.unmarshalFromJson(reader);
	}

	public Importer(URL url) throws LIMException {
		this.importFile = ImportFile.unmarshalFromJson(url);
	}

	public Importer(StreamSource source) throws LIMException {
		this.importFile = ImportFile.unmarshalFromJson(source);
	}


	/* getters & setters */

	public Object getElement(long recordId) {
		return ridElementMap.get(recordId);
	}

	public Object getElement(String elementAlias) {
		return easElementMap.get(elementAlias);
	}

	public List<Object> getElementList() {
		return elementList;
	}


	/* class methods */

	public void marshalToJson(final OutputStream os) throws LIMException {
		this.importFile.marshalToJson(os);
	}


	public int doImport() {
		if (this.importFile == null || CollectionUtil.checkNullOrEmpty(this.importFile.recordList)) {
			return -1;
		}

		int count = 0;
		for (Record r : this.importFile.recordList) {
			if (r == null || r.recordId <= 0 || StringUtil.isEmpty(r.actionType)) {
				continue;
			}
			switch (r.actionType.toLowerCase()) {
				case Record.ACTION_TYPE_NEW_GENERATION:
				case Record.ACTION_TYPE_NG:
					count += doActionNewGeneration(r) ? 1 : 0;
					break;
				case Record.ACTION_TYPE_STATIC_CONSTANT_GETTING:
				case Record.ACTION_TYPE_SCG:
					count += doActionStaticConstantGetting(r) ? 1 : 0;
					break;
				case Record.ACTION_TYPE_ELEMENT_OPERATION:
				case Record.ACTION_TYPE_EO:
					count += doActionElementOperation(r) ? 1 : 0;
					break;
				case Record.ACTION_TYPE_ELEMENT_OPERATION_GENERATION:
				case Record.ACTION_TYPE_EOG:
					count += doActionElementOperationGeneration(r) ? 1 : 0;
					break;
				case Record.ACTION_TYPE_STATIC_OPERATION:
				case Record.ACTION_TYPE_SO:
					count += doActionStaticOperation(r) ? 1 : 0;
					break;
				case Record.ACTION_TYPE_STATIC_OPERATION_GENERATION:
				case Record.ACTION_TYPE_SOG:
					count += doActionStaticOperationGeneration(r) ? 1 : 0;
					break;
			}
		}
		return count;
	}

	private boolean doActionNewGeneration(final Record record) {
		if (StringUtil.isEmpty(record.elementAlias) || StringUtil.isEmpty(record.elementType)) {
			return false;
		}

		try {
			Class<?> etcl = getClass(record.elementType);
			Object obj;
			if (CollectionUtil.checkNullOrEmpty(record.actionParameterList)) {
				obj = etcl.isArray() ? CollectionUtil.generateNewArray(etcl, 0) : etcl.newInstance();
			} else {
				Object[] obja = analyzeActionParameterObjects(record.actionParameterList);
				Class<?>[] cla = analyzeActionParameterObjectClasss(obja);
				obj = etcl.isArray() ? CollectionUtil.generateNewArray(etcl.getComponentType(), obja) 
						: ReflectionUtil.invokeConstructor(
								ReflectionUtil.findConstructor(etcl, cla), true, obja);
			}
			return saveImportedObject(obj, etcl, record.recordId, record.elementAlias);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return false;
	}

	private boolean doActionStaticConstantGetting(final Record record) {
		if (StringUtil.isEmpty(record.actionName) || StringUtil.isEmpty(record.actionSource) 
				|| StringUtil.isEmpty(record.elementAlias) || StringUtil.isEmpty(record.elementType)) {
			return false;
		}

		try {
			Class<?> ascl = getClass(record.actionSource);
			Object obj = ReflectionUtil.getFieldObject(
					ReflectionUtil.findField(ascl, record.actionName), null);
			Class<?> etcl = getClass(record.elementType);
			return saveImportedObject(obj, etcl, record.recordId, record.elementAlias);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return false;
	}

	private boolean doActionElementOperation(final Record record) {
		Object obj;
		if (StringUtil.isEmpty(record.actionName) || StringUtil.isEmpty(record.actionSource) 
				|| (obj = this.easElementMap.get(record.actionSource)) == null) {
			return false;
		}
		return doActionOperation(record.actionName, obj.getClass(), obj, record.actionParameterList, 
				null, record.recordId, null);
	}

	private boolean doActionElementOperationGeneration(final Record record) {
		Object obj;
		if (StringUtil.isEmpty(record.actionName) || StringUtil.isEmpty(record.actionSource) 
				|| (obj = this.easElementMap.get(record.actionSource)) == null 
				|| StringUtil.isEmpty(record.elementType) || StringUtil.isEmpty(record.elementAlias)) {
			return false;
		}
		return doActionOperation(record.actionName, obj.getClass(), obj, record.actionParameterList, 
				record.elementType, record.recordId, record.elementAlias);
	}

	private boolean doActionStaticOperation(final Record record) {
		if (StringUtil.isEmpty(record.actionSource) || StringUtil.isEmpty(record.actionName)) {
			return false;
		}

		try {
			Class<?> ascl = getClass(record.actionSource);
			return doActionOperation(record.actionName, ascl, null, record.actionParameterList, 
					null, record.recordId, null);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return false;
	}

	private boolean doActionStaticOperationGeneration(final Record record) {
		if (StringUtil.isEmpty(record.actionSource) || StringUtil.isEmpty(record.actionName) 
				|| StringUtil.isEmpty(record.elementType) || StringUtil.isEmpty(record.elementAlias)) {
			return false;
		}

		try {
			Class<?> ascl = getClass(record.actionSource);
			return doActionOperation(record.actionName, ascl, null, record.actionParameterList, 
					record.elementType, record.recordId, record.elementAlias);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return false;
	}


	private boolean doActionOperation(final String actionName, final Class<?> actionClazz, 
			final Object actionObject, final List<String> actionParameterList, final String elementType, 
			final long recordId, final String elementAlias) {
		try {
			Object[] obja = analyzeActionParameterObjects(actionParameterList);
			Class<?>[] cla = analyzeActionParameterObjectClasss(obja);
			Method m = ReflectionUtil.findMethod(actionClazz, actionName, cla);
			Object obj = ReflectionUtil.invokeMethod(m, true, actionObject, obja);
			if (StringUtil.isEmpty(elementType) || StringUtil.isEmpty(elementAlias) || obj == null) {
				return true;
			}

			Class<?> etcl = getClass(elementType);
			return saveImportedObject(obj, etcl, recordId, elementAlias);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return false;
	}

	private boolean saveImportedObject(final Object object, final Class<?> elementTypeClazz, 
			final long recordId, final String elementAlias) {
		if (elementTypeClazz == null || !elementTypeClazz.isInstance(object)) {
			return false;
		}

		Object previousElement;
		if ((previousElement = this.ridElementMap.put(recordId, object)) != null) {
			log.warn("RecordId(" + recordId + ") already exists in map. "
					+ "The previous is " + previousElement + ". "
					+ "The new is " + object + ".");
		}
		if ((previousElement = this.easElementMap.put(elementAlias, object)) != null) {
			log.warn("ElementAlias(" + elementAlias + ") already exists in map. "
					+ "The previous is " + previousElement + ". "
					+ "The new is " + object + ".");
		}
		this.elementList.add(object);
		return true;
	}


	private Object[] analyzeActionParameterObjects(final List<String> actionParameterList) {
		if (CollectionUtil.checkNullOrEmpty(actionParameterList)) {
			return null;
		}
		Object[] obja = new Object[actionParameterList.size()];
		for (int i = 0; i < actionParameterList.size(); i++) {
			obja[i] = analyzeQuotativeContent(actionParameterList.get(i));
		}
		return obja;
	}

	private Class<?>[] analyzeActionParameterObjectClasss(final Object[] objectArray) {
		if (CollectionUtil.checkNullOrEmpty(objectArray)) {
			return null;
		}
		Class<?>[] cla = new Class[objectArray.length];
		for (int i = 0; i < objectArray.length; i++) {
			cla[i] = objectArray[i].getClass();
		}
		return cla;
	}


	private Object analyzeQuotativeContent(final String content) {
		if (StringUtil.isEmpty(content)) {
			return null;
		}

		String str = content;
		boolean firstRoundFlag = true;
		StringBuilder sb = new StringBuilder();
		while (str.length() > 0) {
			int i = -1, j = -1;
			boolean flag = false;
			String quotativeFragment = null;
			Object obj = null;
			do {
				int k;
				do {
					i = str.indexOf(Record.QUOTATIVE_SYMBOL_LEFT, i + 1);
					if (i < 0) {
						break;
					}

					k = i - 1;
					while (k >= 0 && str.charAt(k) == Record.SYMBOL_ESCAPE_CHARACTER) {
						k--;
					}
				} while ((i - k) % 2 == 0);

				do {
					j = str.indexOf(Record.QUOTATIVE_SYMBOL_RIGHT, j + 1);
				} while (j >= 0 && j < i);
				if (i < 0 || j < 0) {
					break;
				}
				quotativeFragment = str.substring(i, j + 1);
			} while ((obj = findQuotativeContent(quotativeFragment)) == null);

			if (firstRoundFlag) {
				if (quotativeFragment == null) {
					return content;
				} else if (content.equals(quotativeFragment) && obj != null) {
					return obj;
				}
			}

			flag = i >= 0 && j > i;
			String nonQuotativeFragment = !flag ? str : str.substring(0, i);
			if (!StringUtil.isEmpty(nonQuotativeFragment)) {
				sb.append(nonQuotativeFragment);
			}
			if (flag) {
				sb.append(obj.toString());
			}
			str = str.substring(nonQuotativeFragment.length() + (!flag ? 0 : (j - i + 1)));
			firstRoundFlag = false;
		}

		return sb.toString();
	}

	private Object findQuotativeContent(final String symbolFragment) {
		String strippedFragment = SymbolUtil.verifyAndStripSingleOuterSymbol(symbolFragment, 
				Record.QUOTATIVE_SYMBOL_LEFT, Record.QUOTATIVE_SYMBOL_RIGHT);
		if (StringUtil.isEmpty(strippedFragment)) {
			return null;
		}

		if (strippedFragment.startsWith(Record.ATTRIBUTE_NAME_RECORD_ID + ":")) {
			String rid = strippedFragment.substring((Record.ATTRIBUTE_NAME_RECORD_ID + ":").length());
			return this.ridElementMap.get(StringUtil.longValue(rid));
		} else if (strippedFragment.startsWith(Record.ATTRIBUTE_NAME_ELEMENT_ALIAS + ":")) {
			String eas = strippedFragment.substring((Record.ATTRIBUTE_NAME_ELEMENT_ALIAS + ":").length());
			return this.easElementMap.get(eas);
		}
		return null;
	}


	private Class<?> getClass(final String source) throws ClassNotFoundException {
		String s;
		if (StringUtil.isEmpty(s = source)) {
			return null;
		}

		boolean arrayFlag = false;
		String prefix = "";
		while (s.endsWith("[]")) {
			arrayFlag = true;
			prefix += "[";
			s = s.substring(0, s.length() - 2);
		}

		Class<?> cl;
		if ((cl = PojoElementClassConstant.getElementClass(s)) != null) {
		} else if ((cl = ActionableElementClassConstant.getActionableClass(s)) != null) {
		} else if ((cl = PojoElementEnumConstant.getEnumClass(s)) != null) {
		} else if ((cl = SymbolConstant.getSymbolClass(s)) != null) {
		} else if ((cl = Thread.currentThread().getContextClassLoader().loadClass("java.lang." + s)) != null) {
		}

		return arrayFlag ? Class.forName(prefix + "L" + ((cl != null) ? cl.getName() : s) + ";") : cl;
	}

}
