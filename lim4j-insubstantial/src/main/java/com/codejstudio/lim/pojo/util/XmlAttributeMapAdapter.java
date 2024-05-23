package com.codejstudio.lim.pojo.util;

import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.util.XmlAttributeMapAdapter.XmlAttributeEntry;

/**
 * XmlAttributeMapAdapter.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     javax.xml.bind.annotation.adapters.XmlAdapter
 * @since   lim4j_v1.0.0
 */
public class XmlAttributeMapAdapter extends XmlAdapter<XmlAttributeEntry[], Map<String, String>> {

	/* overridden methods */

	@Override
	public Map<String, String> unmarshal(final XmlAttributeEntry[] attributeEntryArray) throws Exception {
		if (CollectionUtil.checkNullOrEmpty(attributeEntryArray)) {
			return null;
		}

		Map<String, String> ssm = CollectionUtil.generateNewMap();
		for (XmlAttributeEntry xate : attributeEntryArray) {
			ssm.put(xate.key, xate.value);
		}
		return ssm;
	}

	@Override
	public XmlAttributeEntry[] marshal(final Map<String, String> attributeMap) {
		if (CollectionUtil.checkNullOrEmpty(attributeMap)) {
			return null;
		}

		int i = 0;
		XmlAttributeEntry[] xatea = new XmlAttributeEntry[attributeMap.size()];
		for (Map.Entry<String, String> sse : attributeMap.entrySet()) {
			xatea[i++] = new XmlAttributeEntry(sse.getKey(), sse.getValue());
		}
		return xatea;
	}



	/* inner classes */

	static class XmlAttributeEntry {

		/* variables */

		@XmlAttribute
		private String key;

		@XmlAttribute
		private String value;


		/* constructors */

		public XmlAttributeEntry() {
			super();
		}

		public XmlAttributeEntry(String key, String value) {
			this.key = key;
			this.value = value;
		}

	}

}