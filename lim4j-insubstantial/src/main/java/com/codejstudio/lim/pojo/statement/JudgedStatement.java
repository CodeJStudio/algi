package com.codejstudio.lim.pojo.statement;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

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
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IValuable;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * JudgedStatement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = JudgedStatement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { 
	"baseTruth", 
})
public class JudgedStatement extends Statement implements IValuable {

	/* constants */

	private static final long serialVersionUID = -7796763046217470125L;

	public static final String TYPE_NAME = "judged-statement";

	public static final String TRUTH = "truth";


	/* variables */

	@XmlElement(name = "truth")
	protected BaseElement baseTruth;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public JudgedStatement() {
		super();
	}

	public JudgedStatement(JudgedStatement statement) throws LIMException {
		super(statement);
		load(statement);
	}

	public JudgedStatement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public JudgedStatement(boolean initIdFlag, boolean initTypeFlag, String description) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public JudgedStatement(String description) throws LIMException {
		this(true, false, description);
	}

	public JudgedStatement(Statement statement) throws LIMException {
		this(statement.getDescription());
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(JudgedStatement.class);
			JAXBBoundClassConstant.registerBoundClassForInit(JudgedStatement.class);
			JudgedStatement.registerSubPojoClassForInit(JudgedStatement.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* getters & setters */

	@Override
	public LogicValue getLogicValue() {
		return getTruth();
	}

	@XmlTransient
	public BaseElement getBaseTruth() {
		return baseTruth;
	}

	public void setBaseTruth(BaseElement baseTruth) {
		this.baseTruth = baseTruth;
	}

	@XmlTransient
	public Truth getTruth() {
		Attribute attribute = super.getAttributeByKey(TRUTH);
		return (attribute == null) ? null : (Truth) attribute.getValue();
	}

	public boolean setTruth(final Truth truth) throws LIMException {
		Truth t = getTruth();
		if (Objects.equals(t, truth)) {
			return true;
		}

		boolean flag = true;
		if (t != null) {
			this.baseTruth = null;
			flag &= super.removeInnerElementDelegate(t.getId()) 
					& super.removeAttributeByKey(TRUTH);
		}
		if (truth != null) {
			flag &= super.addIncompatibleAttribute(TRUTH, truth) 
					& super.addInnerElementDelegate(truth);
			this.baseTruth = new BaseElement(truth);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setTruth(" + truth + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setTruth(" + truth + ")");
		}
		return flag;
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(JudgedStatement.class) 
					? this : new JudgedStatement(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(JudgedStatement.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof JudgedStatement) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((JudgedStatement) convertible);
		return (IConvertible) this;
	}

	private void load(final JudgedStatement element) {
		if (element != null) {
			this.baseTruth = element.baseTruth;
		}
	}


	@Override
	public JudgedStatement cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(JudgedStatement.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (JudgedStatement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		JudgedStatement clonedElement = (JudgedStatement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public JudgedStatement cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof JudgedStatement) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof JudgedStatement)) 
				? null : cloneToElement((JudgedStatement) ce, null);
	}

	private JudgedStatement cloneToElement(final JudgedStatement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.baseTruth = (this.baseTruth != null) 
				? (BaseElement) this.baseTruth.cloneElement(clonedElementMap) : clonedElement.baseTruth;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (JudgedStatement.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
