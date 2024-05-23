package com.codejstudio.lim.pojo.role;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Proposer.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Proposer extends BaseRole {

	/* constants */

	private static final long serialVersionUID = -502068808206259261L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Proposer() {
		super();
	}

	public Proposer(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Proposer(boolean initIdFlag, boolean initTypeFlag, Entity entity) throws LIMException {
		super(initIdFlag, initTypeFlag, entity);
	}

	public Proposer(boolean initIdFlag, boolean initTypeFlag, Entity entity, Comment... comments) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, entity, comments);
	}


	public Proposer(Entity entity) throws LIMException {
		super(true, true, entity);
	}

	public Proposer(Entity entity, Comment... comments) throws LIMException {
		super(true, true, entity, comments);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Proposer.class);
		BaseRole.registerSubPojoClassForInit(Proposer.class);
	}


	/* CRUD for arrays, collections, maps, groups: proposed elements */

	public boolean containProposedElement(final OwnableInformationElement element) {
		Entity en = getEntity();
		return (en == null) ? false : en.containProposedElement(element);
	}

	public boolean containProposedElement(final String id) {
		Entity en = getEntity();
		return (en == null) ? false : en.containProposedElement(id);
	}

	public boolean addProposedElement(final OwnableInformationElement... elements) throws LIMException {
		Entity en = getEntity();
		return (en == null) ? false : en.addProposedElement(elements);
	}

	public boolean addProposedElement(final Collection<OwnableInformationElement> elementCollection) 
			throws LIMException {
		Entity en = getEntity();
		return (en == null) ? false : en.addProposedElement(elementCollection);
	}

	public boolean removeProposedElement(final String id) throws LIMException {
		Entity en = getEntity();
		return (en == null) ? false : en.removeProposedElement(id);
	}

}
