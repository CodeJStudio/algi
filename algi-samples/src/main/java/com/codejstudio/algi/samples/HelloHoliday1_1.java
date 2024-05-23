package com.codejstudio.algi.samples;

import java.util.Arrays;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.dynamicAction.DynamicStatementAction;
import com.codejstudio.algi.action.dynamicAction.VerifySubElementAction;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.util.InputParameter;
import com.codejstudio.lim.pojo.util.PartDynamicalizedStaticInformation;

/**
 * sample: calculate: 1+1=? (via DynamicStatementAction)
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.algi.action.dynamicAction.DynamicStatementAction
 * @see     com.codejstudio.algi.action.dynamicAction.VerifySubElementAction
 * @since   algi_v1.0.0
 */
public class HelloHoliday1_1 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday1_1.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Concept c001 = new Concept("1");
		Concept c002 = new Concept("2");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c114 = new Concept("数字");
		Concept c115 = new Concept("算术表达式");
		Concept c116 = new Concept("计算");
		Concept c211 = new Concept("1+1");

		c001.addDefaultAttribute(c114);
		c002.addDefaultAttribute(c114);
		c211.addSubConceptWithPosition(c001, c101);
		c211.addDefaultAttribute(c115);


		Definition df11 = new Definition("1+1=2");

		df11.addConceptWithPosition(c211, c102, c002);


		ConditionGroup cdg1 = new ConditionGroup(new AttributeCondition(c114));


		Concept dc1 = new Concept("<$a>");//（属性：[备注：$a是数字]）
		Concept dc2 = new Concept("<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）
		Concept dc3 = new Concept("计算<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）

		dc1.setNetDynamicConditions(cdg1);
		dc2.addSubConceptWithPosition(c101, dc1);
		dc2.setNetDynamicConditions(cdg1, cdg1);
		dc3.addSubConceptWithPosition(c116, dc1, dc2);
		dc3.setNetDynamicConditions(cdg1, cdg1);


		Definition ddf1 = new Definition("<$a>+<$b>=<$c>");//（属性：[备注：$a、$b、$c都是数字]）

		ddf1.addConceptWithPosition(dc2, c102, dc1);
		ddf1.setNetDynamicConditions(cdg1, cdg1, cdg1);

		Definition ddf1_1 = (Definition) ddf1.substituteDynamicSymbolByParameterType(
				new DynamicParameterType[] {DynamicParameterType.INPUT, DynamicParameterType.INPUT, 
						DynamicParameterType.OUTPUT});//"<$a>+<$b>=<#c>"（属性：[备注：$a、$b、#c都是数字]）


//		EquivalenceRelation eqr11 = 
				new EquivalenceRelation(dc3, ddf1_1);



		// HelloHoliday1_1.java
		//DynamicStatementAction
		Action dsa1_1_1 = new DynamicStatementAction(ddf1_1);
		InputParameter[] ipa1_1_1 = InputParameter.generateInstances(DynamicStatementAction.PARAMETER_ELEMENT, 
				new Object[] {c001, c001});//1, 1
		Object[] dsaOutputArray1_1_1 = dsa1_1_1.execute(ipa1_1_1);
		System.out.println();
		System.out.println("DynamicStatementAction1_1_1.execute: " + ((dsaOutputArray1_1_1 == null) ? null : ""));
		for (int i = 0; (dsaOutputArray1_1_1 != null && i < dsaOutputArray1_1_1.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((dsaOutputArray1_1_1[i]) == null) ? null : dsaOutputArray1_1_1[i]));
		}

		System.out.println();
		for (int i = 0; (dsaOutputArray1_1_1 != null && i < dsaOutputArray1_1_1.length); i++) {
			if (dsaOutputArray1_1_1[i] instanceof PartDynamicalizedStaticInformation) {
				Action vsea1_1 = new VerifySubElementAction((PartDynamicalizedStaticInformation) dsaOutputArray1_1_1[i]);
				Object[] vseaOutputArray1_1 = vsea1_1.execute();
				System.out.println("VerifySubElementAction1_1_[" + i + "].execute: " 
						+ ((vseaOutputArray1_1 == null) ? null : Arrays.asList((Object[]) vseaOutputArray1_1)));
			}
		}



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
