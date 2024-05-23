package com.codejstudio.lim.common.util;

import java.util.List;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class PropertiesClasses extends PropertiesClass {

	/* variables: arrays, collections, maps, groups */

	protected String[] clazzNameArray;


	/* constructors */

	public PropertiesClasses(String propertiesFilename, String clazzPropertyKey) throws LIMException {
		this.propertiesFilename = propertiesFilename;
		setClassPropertyKey(clazzPropertyKey);
		setClassPropertyValue(PropertiesLoader.getProperty(propertiesFilename, clazzPropertyKey));
	}


	/* getters & setters */

	public String[] getClassNameArray() {
		return clazzNameArray;
	}

	@Override
	protected void setClassPropertyValue(final String clazzPropertyValue) throws LIMException {
		this.clazzPropertyValue = clazzPropertyValue;
		if (!StringUtil.isEmpty(clazzPropertyValue)) {
			String[] cpva = clazzPropertyValue.split(PropertiesLoader.SEPARATOR_MULTI_VALUES);
			List<String> cnl = CollectionUtil.generateNewList();
			for (String cpv : cpva) {
				cnl.add(convertToPropertiesClassName(cpv));
			}
			this.clazzNameArray = cnl.toArray(new String[0]);

		}
	}

}
