package com.codejstudio.lim.pojo.i;

import java.util.Collection;
import java.util.List;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericElement;

/**
 * IGroupable.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public interface IGroupable<T extends GenericElement> {

	/* constants */

	static final String GROUP_KEY = "group";
	static final String GROUP_KEY_PREFIX_IEM = GROUP_KEY + WordSeparator.UNDERSCORE.getCharacter();


	/* getters & setters */

	/**
	 * The implemented classes should also contain:<br/>
	 * private List<BaseElement> innerGroupList;<br/>
	 * private void initInnerGroupList();<br/>
	 * private void destroyInnerGroupList();
	 */
	List<T> getInnerGroupList() throws LIMException;

	int size();


	/* CRUD for arrays, collections, maps, groups: group elements */

	boolean containGroupElement(T groupElement);

	boolean containGroupElement(String id);

	@SuppressWarnings("unchecked")
	boolean addGroupElement(T... groupElements) throws LIMException;

	boolean addGroupElement(Collection<? extends T> groupElementCollection) throws LIMException;

	boolean removeGroupElement(String id);

	boolean replaceGroupElement(BaseElement replacee, T replacer) throws LIMException;

}
