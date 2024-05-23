package com.codejstudio.lim.pojo.util;

import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.util.XmlElementMapAdapter.XmlElementEntry;

/**
 * XmlElementMapAdapter.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     javax.xml.bind.annotation.adapters.XmlAdapter
 * @since   lim4j_v1.0.0
 */
public class XmlElementMapAdapter extends XmlAdapter<XmlElementEntry[], Map<String, BaseElement>> {

	/* overridden methods */

	@Override
	public Map<String, BaseElement> unmarshal(final XmlElementEntry[] elementEntryArray) throws Exception {
		if (CollectionUtil.checkNullOrEmpty(elementEntryArray)) {
			return null;
		}

		Map<String, BaseElement> sbem = CollectionUtil.generateNewMap();
		for (XmlElementEntry xee : elementEntryArray) {
			sbem.put(xee.key, new BaseElement(xee.id, xee.type));
		}
		return sbem;
	}

	@Override
	public XmlElementEntry[] marshal(final Map<String, BaseElement> elementMap) {
		if (CollectionUtil.checkNullOrEmpty(elementMap)) {
			return null;
		}

		int i = 0;
		XmlElementEntry[] xeea = new XmlElementEntry[elementMap.size()];
		for (Map.Entry<String, BaseElement> sbee : elementMap.entrySet()) {
			xeea[i++] = new XmlElementEntry(sbee.getKey(), sbee.getValue());
		}
		return xeea;
	}



	/* inner classes */

	static class XmlElementEntry {

		/* variables */

		@XmlAttribute
		private String key;

		@XmlAttribute
		private String id;

		@XmlAttribute
		private String type;


		/* constructors */

		public XmlElementEntry() {
			super();
		}

		public XmlElementEntry(String key, BaseElement element) {
			this.key = key;
			this.id = element.getId();
			this.type = element.getType();
		}

	}

}