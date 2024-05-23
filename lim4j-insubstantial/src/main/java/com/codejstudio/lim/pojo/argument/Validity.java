package com.codejstudio.lim.pojo.argument;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementEnumConstant;

/**
 * Validity.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Validity extends LogicValue {

	/* enumeration */

	public enum ValidityType {
		VALID(1),
		INVALID(0),
		HALF_VALID(0.5),
		ABSOLUTELY_VALID(Long.MAX_VALUE),
		ABSOLUTELY_INVALID(Long.MIN_VALUE),
		;


		/* variables */

		private double validityValue;


		/* constructors */

		ValidityType(double validityValue) {
			this.validityValue = validityValue;
		}


		/* getters & setters */

		public double getValidityValue() {
			return validityValue;
		}


		/* static methods */

		public static String getDescription(final double validityValue) {
			for (ValidityType vt : ValidityType.values()) {
				if (vt.validityValue == validityValue) {
					return vt.name();
				}
			}
			return null;
		}

	}


	/* constants */

	private static final long serialVersionUID = 110293350610259453L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Validity() {
		super();
	}

	public Validity(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}


	public Validity(double validityValue, String description) throws LIMException {
		super(true, true, validityValue, description);
	}

	public Validity(double validityValue) throws LIMException {
		super(true, true, validityValue, ValidityType.getDescription(validityValue));
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Validity.class);
		PojoElementEnumConstant.registerEnumClassForInit(ValidityType.class);
		LogicValue.registerSubPojoClassForInit(Validity.class);
	}

}
