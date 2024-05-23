package com.codejstudio.lim.pojo.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.IGroupable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public final class GroupableUtil {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(GroupableUtil.class);


	/* variables: arrays, collections, maps, groups */

	private static final Map<Class<? extends GenericElement>, 
		Class<? extends IGroupable<? extends GenericElement>>> GROUPABLE_CLAZZ_MAP;


	/* initializers */

	static {
		try {
			GROUPABLE_CLAZZ_MAP = CollectionUtil.generateNewMap();
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* static methods */

	public static final void registerGroupableClassForInit(
			final Class<? extends GenericElement> elementClazz, 
			final Class<? extends IGroupable<? extends GenericElement>> groupableClazz) {
		GROUPABLE_CLAZZ_MAP.put(elementClazz, groupableClazz);
	}


	@SuppressWarnings("unchecked")
	public static final <G extends GenericElement> IGroupable<G> generateNewGroup(final Class<G> gClazz, 
			final boolean initFlag) throws LIMException {
		Class<IGroupable<?>> gcl = (Class<IGroupable<?>>) GROUPABLE_CLAZZ_MAP.get(gClazz);
		BaseElement be = !BaseElement.class.isAssignableFrom(gcl) 
				? null : BaseElement.newInstance((Class<? extends BaseElement>) gcl, initFlag);
		return (IGroupable<G>) be;
	}


	public static final <G extends GenericElement> IGroupable<G> convertToGroupOfSuperClass(
			final Class<G> gClazz, final IGroupable<? extends G> group, final boolean initFlag) 
					throws LIMException {
		if (checkNullOrEmpty(group)) {
			return null;
		}

		List<? extends G> gigl = group.getInnerGroupList();
		List<G> gl = CollectionUtil.generateNewList();
		gl.addAll(gigl);
		G[] ga = gl.toArray(CollectionUtil.generateNewArray(gClazz, 0));

		IGroupable<G> gg = generateNewGroup(gClazz, initFlag);
		gg.addGroupElement(ga);
		return gg;
	}

	@SuppressWarnings("unchecked")
	public static final <G extends GenericElement> IGroupable<G> convertToGroupOfSubClass(
			final Class<G> gClazz, final IGroupable<? super G> group, final boolean initFlag) 
					throws LIMException {
		if (checkNullOrEmpty(group)) {
			return null;
		}

		List<? super G> gigl = group.getInnerGroupList();
		List<G> gl = CollectionUtil.generateNewList();
		for (Object obj : gigl) {
			if (gClazz.isInstance(obj)) {
				gl.add((G) obj);
			}
		}
		G[] ga = gl.toArray(CollectionUtil.generateNewArray(gClazz, 0));

		IGroupable<G> gg = generateNewGroup(gClazz, initFlag);
		gg.addGroupElement(ga);
		return gg;
	}


	public static final boolean checkNullOrEmpty(final IGroupable<?> group) {
		return group == null || group.size() == 0;
	}

	public static final <G extends GenericElement> boolean onlyContains(
			final IGroupable<? extends G> group, final Collection<? extends G> elementCollection) 
					throws LIMException {
		return checkNullOrEmpty(group) ? false 
				: CollectionUtil.onlyContains(group.getInnerGroupList(), elementCollection);
	}

}
