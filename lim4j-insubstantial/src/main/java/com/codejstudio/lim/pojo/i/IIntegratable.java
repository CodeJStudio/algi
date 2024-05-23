package com.codejstudio.lim.pojo.i;

import java.util.Map;

import com.codejstudio.lim.pojo.BaseElement;

/**
 * IIntegratable.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public interface IIntegratable {

	/* constants */

	static final String INTEGRATED_ATTACHMENT_XMLATTRIBUTE_NAME = "itx-attributes";
	static final String INTEGRATED_ATTACHMENT_XMLELEMENT_NAME = "itx-elements";


	/* getters & setters */

	/**
	 * for JAXB usage of unmarshalling<p>
	 * The implemented classes should also contain:<br/>
	 * private Map<String, String> integratedAttributeMap;<br/>
	 * private void initIntegratedAttributeMap();<br/>
	 * private void destroyIntegratedAttributeMap();<br/>
	 * protected String getIntegratedAttributeDelegate(String key);<br/>
	 * protected boolean putIntegratedAttributeDelegate(String key, String value);<br/>
	 * protected boolean removeIntegratedAttributeDelegate(String key);
	 */
	Map<String, String> getIntegratedAttributeMap();

	/**
	 * for JAXB usage of unmarshalling<p>
	 * private Map<String, String> integratedAttributeMap;<br/>
	 * private void initIntegratedAttributeMap();<br/>
	 * private void destroyIntegratedAttributeMap();<br/>
	 * protected String getIntegratedAttributeDelegate(String key);<br/>
	 * protected boolean putIntegratedAttributeDelegate(String key, String value);<br/>
	 * protected boolean removeIntegratedAttributeDelegate(String key);
	 */
	void setIntegratedAttributeMap(Map<String, String> attributeMap);

	/**
	 * for JAXB usage of unmarshalling<p>
	 * The implemented classes should also contain:<br/>
	 * private Map<String, BaseElement> integratedElementMap;<br/>
	 * private void initIntegratedElementMap();<br/>
	 * private void destroyIntegratedElementMap();<br/>
	 * protected BaseElement getIntegratedElementDelegate(String key);<br/>
	 * protected boolean putIntegratedElementDelegate(String key, BaseElement value);<br/>
	 * protected boolean removeIntegratedElementDelegate(String key);
	 */
	Map<String, BaseElement> getIntegratedElementMap();

	/**
	 * for JAXB usage of unmarshalling<p>
	 * The implemented classes should also contain:<br/>
	 * private Map<String, BaseElement> integratedElementMap;<br/>
	 * private void initIntegratedElementMap();<br/>
	 * private void destroyIntegratedElementMap();<br/>
	 * protected BaseElement getIntegratedElementDelegate(String key);<br/>
	 * protected boolean putIntegratedElementDelegate(String key, BaseElement value);<br/>
	 * protected boolean removeIntegratedElementDelegate(String key);
	 */
	void setIntegratedElementMap(Map<String, BaseElement> elementMap);

}
