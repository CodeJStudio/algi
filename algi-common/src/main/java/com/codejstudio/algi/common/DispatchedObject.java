package com.codejstudio.algi.common;

import java.io.Serializable;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public class DispatchedObject implements Serializable {

	/* constants */

	private static final long serialVersionUID = 971480423137902212L;

	public static final String ATTRIBUTE_KEY_DATASTRING = "dataString";
	public static final String ATTRIBUTE_KEY_LANGUAGE = "language";
	public static final String ATTRIBUTE_KEY_JSON = "json";
	public static final String ATTRIBUTE_KEY_IMPORTFILE = "importFile";

	public static final String ATTRIBUTE_KEY_PREPROCESSORTYPE = "preprocessorType";
	public static final String ATTRIBUTE_KEY_PROCESSORTYPE = "processorType";
	public static final String ATTRIBUTE_KEY_OUTPUTTYPE = "outputType";

	public static final String ATTRIBUTE_KEY_NLP_POS = "pos";
	public static final String ATTRIBUTE_KEY_NLP_SEG = "seg";

	public static final String ATTRIBUTE_KEY_PERSISTENCE_OBJECT = "persistenceObject";


	/* variables: arrays, collections, maps, groups */

	private Map<String, Serializable> attributeMap;


	/* constructors */

	public DispatchedObject() throws LIMException {
		this.attributeMap = CollectionUtil.generateNewMap();
	}


	/* CRUD for arrays, collections, maps, groups: attributes */

	public Serializable getAttribute(final String key) {
		return this.attributeMap.get(key);
	}

	public void putAttribute(final String key, final Serializable value) {
		this.attributeMap.put(key, value);
	}

}
