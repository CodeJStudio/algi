package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.argument.Validity;
import com.codejstudio.lim.pojo.argument.Validity.ValidityType;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.statement.Truth;
import com.codejstudio.lim.pojo.statement.Truth.TruthType;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementEnumConstant;

/**
 * LogicValue.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
@XmlRootElement(name = LogicValue.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LogicValue extends InformationUnit {

	/* enumeration */

	public enum BooleanValueType {
		TRUE(1, LogicValue.TRUE),
		FALSE(0, LogicValue.FALSE),
		;


		/* variables */

		private int value;

		private LogicValue logicValue;


		/* constructors */

		private BooleanValueType(int value, LogicValue logicValue) {
			this.value = value;
			this.logicValue = logicValue;
		}


		/* getters & setters */

		public int getValue() {
			return value;
		}

		public LogicValue getLogicValue() {
			return logicValue;
		}


		/* static methods */

		public static String getDescription(final int value) {
			for (BooleanValueType bvt : BooleanValueType.values()) {
				if (bvt.value == value) {
					return bvt.name();
				}
			}
			return null;
		}

	}


	/* constants */

	private static final long serialVersionUID = -1844357024860652266L;

	public static final String TYPE_NAME = "logic-value";

	public static final LogicValue TRUE;
	public static final LogicValue FALSE;


	/* variables */

	@XmlAttribute(name = "value", required = true)
	private double value;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public LogicValue() {
		super();
	}

	public LogicValue(LogicValue logicValue) throws LIMException {
		super(logicValue);
		load(logicValue);
	}

	public LogicValue(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public LogicValue(boolean initIdFlag, boolean initTypeFlag, double value, String description) 
			throws LIMException {
		this(initIdFlag, initTypeFlag);
		setValue(value, description);
	}

	public LogicValue(boolean initIdFlag, boolean initTypeFlag, double value) throws LIMException {
		this(initIdFlag, initTypeFlag);
		setValue(value);
	}

	public LogicValue(boolean initIdFlag, boolean initTypeFlag, boolean booleanValue) throws LIMException {
		this(initIdFlag, initTypeFlag);
		setValue(booleanValue);
	}


	public LogicValue(double value, String description) throws LIMException {
		this(true, false, value, description);
	}

	public LogicValue(double value) throws LIMException {
		this(true, false, value);
	}

	public LogicValue(boolean booleanValue) throws LIMException {
		this(true, false, booleanValue);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			TRUE = new LogicValue(true, false);
			FALSE = new LogicValue(true, false);
			TRUE.setValue(true);
			FALSE.setValue(false);

			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(LogicValue.class);
			JAXBBoundClassConstant.registerBoundClassForInit(LogicValue.class);
			PojoElementEnumConstant.registerEnumClassForInit(BooleanValueType.class);
			LogicValue.registerSubPojoClassForInit(LogicValue.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* getters & setters */

	@XmlTransient
	public double getValue() {
		return value;
	}

	public void setValue(final boolean booleanValue) throws LIMException {
		setValue(BooleanValueType.valueOf(Boolean.toString(booleanValue).toUpperCase()).value, 
				Boolean.toString(booleanValue));
	}

	public void setValue(final double value) throws LIMException {
		String dsc = null;
		if (this.type == null) {
			dsc = (value % 1 != 0) ? null : BooleanValueType.getDescription((int) value);
		} else if (this.type.equals(generateTypeFromClass(Validity.class))) {
			dsc = ValidityType.getDescription(value);
		} else if (this.type.equals(generateTypeFromClass(Truth.class))) {
			dsc = TruthType.getDescription(value);
		}
		setValue(value, dsc);
	}

	public void setValue(final double value, final String description) throws LIMException {
		this.value = value;
		setDescription(description);
		ElementTrace.log.info(this.toBaseString() + ": setValue(" + value + ")");
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(LogicValue.class) ? this : new LogicValue(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(LogicValue.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof LogicValue) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((LogicValue) convertible);
		return (IConvertible) this;
	}

	private void load(final LogicValue element) {
		if (element != null) {
			this.value = element.value;
		}
	}


	@Override
	public LogicValue cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(LogicValue.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (LogicValue) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		LogicValue clonedElement = (LogicValue) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public LogicValue cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof LogicValue) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof LogicValue)) 
				? null : cloneToElement((LogicValue) ce, null);
	}

	private LogicValue cloneToElement(final LogicValue clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.value = this.value;
		return clonedElement;
	}


	/* class methods */

	public boolean valueEquals(final LogicValue logicValue) {
		return (logicValue == null) ? false : (this.value == logicValue.value);
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (LogicValue.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}


	public static final LogicValue getLogicValue(final Object sourceValue) {
		return (sourceValue instanceof LogicValue) ? (LogicValue) sourceValue 
				: ((sourceValue instanceof Boolean) ? getLogicValue((boolean) sourceValue) : null);
	}

	public static final LogicValue getLogicValue(final String stringValue) throws LIMException {
		if (StringUtil.isEmpty(stringValue)) {
			return null;
		}

		try {
			return BooleanValueType.valueOf(stringValue.toUpperCase()).logicValue;
		} catch (Exception e) {}

		Double d = StringUtil.doubleValue(stringValue);
		return (d == null) ? null : new LogicValue(d);
	}

	public static final LogicValue getLogicValue(final boolean booleanValue) {
		BooleanValueType bvt = BooleanValueType.valueOf(Boolean.toString(booleanValue).toUpperCase());
		return (bvt == null) ? null : bvt.logicValue;
	}


	public static final boolean isBooleanValue(final LogicValue logicValue) {
		return LogicValue.TRUE.valueEquals(logicValue) || LogicValue.FALSE.valueEquals(logicValue);
	}

	public static final Boolean getBooleanValue(final LogicValue logicValue) {
		return LogicValue.TRUE.valueEquals(logicValue) ? Boolean.TRUE 
				: (LogicValue.FALSE.valueEquals(logicValue) ? Boolean.FALSE : null);
	}

}
