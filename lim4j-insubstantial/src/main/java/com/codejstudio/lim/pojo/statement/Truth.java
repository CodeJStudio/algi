package com.codejstudio.lim.pojo.statement;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementEnumConstant;

/**
 * Truth.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Truth extends LogicValue {

	/* enumeration */

	public enum TruthType {
		TRUE(1),
		FALSE(0),
		HALF_TRUE_HALF_FALSE(0.5),
		ABSOLUTELY_TRUE(Long.MAX_VALUE),
		ABSOLUTELY_FALSE(Long.MIN_VALUE),
		TRUE_FALSE_GAP(-2),
		NO_DETERMINED(-3),
		MEANINGLESS(-4),
		;


		/* variables */

		private double truthValue;


		/* constructors */

		private TruthType(double truthValue) {
			this.truthValue = truthValue;
		}


		/* getters & setters */

		public double getTruthValue() {
			return truthValue;
		}


		/* static methods */

		public static String getDescription(final double truthValue) {
			for (TruthType tt : TruthType.values()) {
				if (tt.truthValue == truthValue) {
					return tt.name();
				}
			}
			return null;
		}

	}


	/* constants */

	private static final long serialVersionUID = 3118917197967855935L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Truth() {
		super();
	}

	public Truth(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}


	public Truth(double truthValue, String description) throws LIMException {
		super(true, true, truthValue, description);
	}

	public Truth(double truthValue) throws LIMException {
		super(true, true, truthValue, TruthType.getDescription(truthValue));
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Truth.class);
		PojoElementEnumConstant.registerEnumClassForInit(TruthType.class);
		LogicValue.registerSubPojoClassForInit(Truth.class);
	}

}
