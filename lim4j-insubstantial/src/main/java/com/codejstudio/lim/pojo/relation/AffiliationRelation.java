package com.codejstudio.lim.pojo.relation;

import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AffiliationRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AffiliationRelation extends BaseRelation {

	/* constants */

	private static final long serialVersionUID = 5928198099628388201L;

	public static final String MASTER = "master";
	public static final String SLAVE = "slave";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AffiliationRelation() {
		super();
	}

	public AffiliationRelation(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public AffiliationRelation(boolean initIdFlag, AbstractRelationableInformationElement master, 
			Attribute slave) throws LIMException {
		super(initIdFlag, true);
		setMaster(master);
		setSlave(slave);
	}


	public AffiliationRelation(AbstractRelationableInformationElement master, Attribute slave) 
			throws LIMException {
		this(true, master, slave);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(AffiliationRelation.class);
		BaseRelation.registerSubPojoClassForInit(AffiliationRelation.class);
	}


	/* getters & setters */

	@Override
	public BaseElement getBasePrimaryElement() {
		return super.getIntegratedElementDelegate(MASTER);
	}

	@Override
	@XmlTransient
	public AbstractRelationableInformationElement getPrimaryElement() {
		return getMaster();
	}


	public AbstractRelationableInformationElement getMaster() {
		return (AbstractRelationableInformationElement) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(MASTER));
	}

	public boolean setMaster(final AbstractRelationableInformationElement master) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(MASTER);
		boolean flag = true;
		if (be != null && master != null) {
			if (!be.baseEquals(master)) {
				AbstractRelationableInformationElement arie = (AbstractRelationableInformationElement) 
						super.removeAndReplaceInnerElementDelegate(be, master);
				flag &= arie.removeRelation(this.id) 
						& master.addRelation(this) 
						& super.putIntegratedElementDelegate(MASTER, new BaseElement(master));
			}
		} else if (be != null) {
			AbstractRelationableInformationElement arie = (AbstractRelationableInformationElement) 
					super.getInnerElementDelegate(be);
			flag &= arie.removeRelation(this.id) 
					& super.removeInnerElementDelegate(be.getId()) 
					& super.removeIntegratedElementDelegate(MASTER);
		} else if (master != null) {
			be = new BaseElement(master);
			flag &= super.addInnerElementDelegate(be, master) 
					& super.putIntegratedElementDelegate(MASTER, be) 
					& master.addRelation(this);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setMaster(" 
					+ ((master == null) ? null : master.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setMaster(" 
					+ ((master == null) ? null : master.toBaseString()) + ")");
		}
		return flag;
	}


	@Override
	public BaseElement getBaseSecondaryElement() {
		return super.getIntegratedElementDelegate(SLAVE);
	}

	@Override
	@XmlTransient
	public AbstractRelationableInformationElement getSecondaryElement() {
		return getSlave();
	}


	public AbstractRelationableInformationElement getSlave() {
		return (AbstractRelationableInformationElement) super.getInnerElementDelegate(
				super.getIntegratedElementDelegate(SLAVE));
	}

	public boolean setSlave(final AbstractRelationableInformationElement slave) throws LIMException {
		BaseElement be = super.getIntegratedElementDelegate(SLAVE);
		boolean flag = true;
		if (be != null && slave != null) {
			if (!be.baseEquals(slave)) {
				AbstractRelationableInformationElement arie = (AbstractRelationableInformationElement) 
						super.removeAndReplaceInnerElementDelegate(be, slave);
				flag &= arie.removeRelation(this.id) 
						& slave.addRelation(this) 
						& super.putIntegratedElementDelegate(SLAVE, new BaseElement(slave));
			}
		} else if (be != null) {
			AbstractRelationableInformationElement arie = (AbstractRelationableInformationElement) 
					super.getInnerElementDelegate(be);
			flag &= arie.removeRelation(this.id) 
					& super.removeInnerElementDelegate(be.getId()) 
					& super.removeIntegratedElementDelegate(SLAVE);
		} else if (slave != null) {
			be = new BaseElement(slave);
			flag &= super.addInnerElementDelegate(be, slave) 
					& super.putIntegratedElementDelegate(SLAVE, be) 
					& slave.addRelation(this);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setSlave(" 
					+ ((slave == null) ? null : slave.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setSlave(" 
					+ ((slave == null) ? null : slave.toBaseString()) + ")");
		}
		return flag;
	}


	/* overridden methods */

	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		Map<String, BaseElement> item;
		if (CollectionUtil.checkNullOrEmpty(rootElementMap) 
				|| CollectionUtil.checkNullOrEmpty(item = super.getIntegratedElementMap())) {
			return;
		}

		BaseElement master = item.get(MASTER);
		if (master != null && master.getId() != null) {
			GenericElement ge = rootElementMap.get(master.getId());
			super.addInnerElementDelegate(master, ge);
		}
		BaseElement slave = item.get(SLAVE);
		if (slave != null && slave.getId() != null) {
			GenericElement ge = rootElementMap.get(slave.getId());
			super.addInnerElementDelegate(slave, ge);
		}
	}

}
