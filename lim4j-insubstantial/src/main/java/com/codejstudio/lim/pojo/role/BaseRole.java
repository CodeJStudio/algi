package com.codejstudio.lim.pojo.role;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.comment.CommentGroup;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * BaseRole.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = BaseRole.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseEntity",
	"baseCommentGroup",
})
public class BaseRole extends GenericElement {

	/* constants */

	private static final long serialVersionUID = -6361736430748825626L;

	public static final String TYPE_NAME = "role";


	/* variables */

	@XmlElement(name = "entity", required = true)
	protected BaseElement baseEntity;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "comment-group")
	private BaseElement baseCommentGroup;

	private CommentGroup commentGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public BaseRole() {
		super();
	}

	public BaseRole(BaseRole role) throws LIMException {
		super(role);
		load(role);
	}

	public BaseRole(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public BaseRole(boolean initIdFlag, boolean initTypeFlag, Entity entity) throws LIMException {
		super(initIdFlag, initTypeFlag);
		setEntity(entity);
	}

	public BaseRole(boolean initIdFlag, boolean initTypeFlag, Entity entity, Comment... comments) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setEntity(entity);
		addComment(comments);
	}


	public BaseRole(Entity entity) throws LIMException {
		this(true, false, entity);
	}

	public BaseRole(Entity entity, Comment... comments) throws LIMException {
		this(true, false, entity, comments);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(BaseRole.class);
			JAXBBoundClassConstant.registerBoundClassForInit(BaseRole.class);
			BaseRole.registerSubPojoClassForInit(BaseRole.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initCommentGroup() throws LIMException {
		if (this.commentGroup == null) {
			this.commentGroup = new CommentGroup(true);
			super.addInnerElementDelegate(this.commentGroup);
			this.baseCommentGroup = new BaseElement(commentGroup);
		}
	}


	/* destroyers */

	private void destroyCommentGroup() {
		if (this.commentGroup != null && this.commentGroup.size() == 0) {
			this.baseCommentGroup = null;
			super.removeInnerElementDelegate(this.commentGroup.getId());
			this.commentGroup = null;
		}
	}


	/* getters & setters */

	@XmlTransient
	public BaseElement getBaseEntity() {
		return baseEntity;
	}

	public void setBaseEntity(BaseElement baseEntity) {
		this.baseEntity = baseEntity;
	}

	@XmlTransient
	public Entity getEntity() {
		return (this.baseEntity == null) ? null : (Entity) super.getInnerElementDelegate(this.baseEntity);
	}

	public boolean setEntity(final Entity entity) throws LIMException {
		if (entity == null) {
			throw new LIMException(new NullPointerException());
		}

		boolean flag = true;
		if (this.baseEntity != null) {
			if (!this.baseEntity.baseEquals(entity)) {
				flag &= super.replaceInnerElementDelegate(this.baseEntity, entity);
				this.baseEntity = new BaseElement(entity);
			}
		} else {
			this.baseEntity = new BaseElement(entity);
			flag &= super.addInnerElementDelegate(this.baseEntity, entity);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setEntity(" 
					+ ((entity == null) ? null : entity.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setEntity(" 
					+ ((entity == null) ? null : entity.toBaseString()) + ")");
		}
		return flag;
	}


	@XmlTransient
	public BaseElement getBaseCommentGroup() {
		return baseCommentGroup;
	}

	public void setBaseCommentGroup(BaseElement baseCommentGroup) {
		this.baseCommentGroup = baseCommentGroup;
	}

	public CommentGroup getCommentGroup() {
		return commentGroup;
	}


	/* CRUD for arrays, collections, maps, groups: comments */

	public boolean containComment(final Comment element) {
		return (this.commentGroup == null) ? false : this.commentGroup.containGroupElement(element);
	}

	public boolean containComment(final String id) {
		return (this.commentGroup == null) ? false : this.commentGroup.containGroupElement(id);
	}

	public boolean addComment(final Comment... elements) throws LIMException {
		return addComment((elements == null) ? null : Arrays.asList(elements));
	}

	public boolean addComment(final Collection<Comment> elementCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return false;
		}

		try {
			initCommentGroup();
			boolean flag = super.addInnerElementDelegate(elementCollection) 
					& this.commentGroup.addGroupElement(elementCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addComment(" 
						+ BaseElement.toBaseString(elementCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addComment(" 
						+ BaseElement.toBaseString(elementCollection) + ")");
			}
			return flag;
		} finally {
			destroyCommentGroup();
		}
	}

	public boolean removeComment(final String id) {
		if (id == null || !containComment(id)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeComment(" + id + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(id) 
					& this.commentGroup.removeGroupElement(id);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeComment(" + id + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeComment(" + id + ")");
			}
			return flag;
		} finally {
			destroyCommentGroup();
		}
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(BaseRole.class) ? this : new BaseRole(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(BaseRole.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof BaseRole) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((BaseRole) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final BaseRole element) {
		if (element != null) {
			this.baseEntity = element.baseEntity;
			this.baseCommentGroup = element.baseCommentGroup;
			this.commentGroup = element.commentGroup;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseEntity != null && this.baseEntity.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseEntity.getId());
			super.addInnerElementDelegate(this.baseEntity, ge);
		}
		if (this.baseCommentGroup != null && this.baseCommentGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseCommentGroup.getId());
			this.commentGroup = (ge instanceof CommentGroup) ? (CommentGroup) ge : this.commentGroup;
			super.addInnerElementDelegate(this.commentGroup);
		}
	}


	@Override
	public BaseRole cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(BaseRole.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (BaseRole) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		BaseRole clonedElement = (BaseRole) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public BaseRole cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof BaseRole) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof BaseRole)) 
				? null : cloneToElement((BaseRole) ce, null);
	}

	private BaseRole cloneToElement(final BaseRole clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.baseEntity = (this.baseEntity != null) 
				? (BaseElement) this.baseEntity.cloneElement(clonedElementMap) : clonedElement.baseEntity;

		clonedElement.baseCommentGroup = (this.baseCommentGroup != null) 
				? (BaseElement) this.baseCommentGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseCommentGroup;
		clonedElement.commentGroup = (this.commentGroup != null) 
				? (CommentGroup) this.commentGroup.cloneElement(clonedElementMap) 
				: clonedElement.commentGroup;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (BaseRole.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
